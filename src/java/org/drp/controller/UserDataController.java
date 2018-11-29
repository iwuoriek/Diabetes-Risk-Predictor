/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.controller;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.drp.handler.UserAccountHandler;
import org.drp.handler.UserDataHandler;
import org.drp.handler.algorithm.PredictionModel;
import org.drp.handler.utils.AppUtils;
import org.drp.model.UserAccount;
import org.drp.model.UserData;
import org.drp.model.dto.UserAccountDto;
import org.drp.model.dto.UserDataDto;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Osazee
 *
 * This class handles all interactions between the user interface and the
 * system's application logic, including database interactions and prediction
 * models.
 */
@Named("dataBean")
@SessionScoped
public class UserDataController extends BeanClass implements java.io.Serializable {

    private LineChartModel lineModel;
    @Autowired
    private UserDataHandler handler;
    @Autowired
    private UserAccountHandler userHandler;
    @Autowired
    private PredictionModel model;

    /**
     * This method calls the prediction class to calculate diabetes risk score
     * for the user
     *
     * @return the calculated risk score to the UI
     */
    public String predictScore() {
        try {
            UserDataDto dto = new UserDataDto();
            dto.setAge(getAge());
            dto.setBloodPressure(getBloodPressure());
            dto.setBmi(AppUtils.calculateBMI(getWeight(), getHeight()));
            dto.setGlucose(getGlucose());
            dto.setHeight(getHeight());
            dto.setInsulin(getInsulin());
            dto.setPedigree(getPedigree());
            dto.setPregnancies(getPregnancies());
            dto.setSkinThickness(getSkinThickness());
            dto.setWeight(getWeight());

            setRiskScore(model.predict(dto));
            setMessage(resultMessage(model.predict(dto)));
            return "score?faces-redirect=true";
        } catch (NumberFormatException e) {
            setErrorMessage("Error: Please ensure that all entries are in Number Format");
            return "error?faces-redirect=true";
        }
    }

    /**
     * Searches database to validate user's record in database
     *
     * @return User detail if user exists, message if user does not exist
     */
    public String searchUser() {
        UserAccount user = userHandler.getUser(getEmail());
        if (user != null) {
            setUser(user);
        } else {
            setValidationMessage("User was not found.");
        }
        return "score";
    }

    /**
     * Returns a message depending on the users risk score
     *
     * @param riskScore
     * @return
     */
    public String resultMessage(double riskScore) {
        if (riskScore <= 30) {
            return "YOUR RISK IS LOW";
        } else if (riskScore > 30 && riskScore < 50) {
            return "YOUR RISK IS NOT SO LOW";
        } else if (riskScore >= 50 && riskScore < 70) {
            return "YOUR RISK IS NOT SO HIGH";
        } else if (riskScore >= 70 && riskScore <= 100){
            return "YOUR RISK SCORE IS HIGH";
        } else {
            return "Opps! There is a problem with the system.";
        }
    }

    /**
     * Saves users records to database to help monitor progress
     *
     * @return
     */
    public String saveRecord() {
        UserDataDto dto = new UserDataDto();
        dto.setAge(getAge());
        dto.setBloodPressure(getBloodPressure());
        dto.setBmi(AppUtils.calculateBMI(getWeight(), getHeight()));
        dto.setGlucose(getGlucose());
        dto.setHeight(getHeight());
        dto.setInsulin(getInsulin());
        dto.setPedigree(getPedigree());
        dto.setPregnancies(getPregnancies());
        dto.setSkinThickness(getSkinThickness());
        dto.setWeight(getWeight());
        dto.setRiskScore(getRiskScore());

        UserAccountDto userDto = new UserAccountDto();
        userDto.setEmail(getEmail());
        userDto.setFirstname(getFirstname());
        userDto.setSurname(getSurname());

        handler.addData(dto, userDto);
        return "test_home?faces-redirect=true";
    }
    
    /**
     * Sets the graph model required to display information about the patient data
     * @return the line chart model showing graph distribution of the patient data
     */
    public String userDevelopment() {
        try {
            lineModel = getModel();
            lineModel.setTitle("Distribution For " + getFirstname() + " " + getSurname());
            lineModel.setLegendPosition("e");
            //lineModel.setShowPointLabels(true);
            lineModel.getAxes().put(AxisType.X, new CategoryAxis("Test Attributes"));
            Axis yAxis = lineModel.getAxis(AxisType.Y);
            yAxis.setLabel("Scores");
            yAxis.setMin(0);
            yAxis.setMax(400);
            yAxis.setTickCount(10);

            return "distribution?faces-redirect=true";
        } catch (Exception e) {
            System.err.println(e);
            setErrorMessage("Information for this user cannot be found. Please ensure user exists in database.");
            return "error?faces-redirect=true";
        }
    }

    /**
     * Creates the graph model, and orders the patient data according to their attributes
     * @return graph model of the ordered patient data to be visualized
     * @throws Exception to be caught if dataList is null
     */
    private LineChartModel getModel() throws Exception {
        final LineChartModel chartModel = new LineChartModel();
        int index = 1;
        List<UserData> dataList = modelDistribution();
        for (UserData userData : dataList) {
            ChartSeries chart = new ChartSeries();
            chart.setLabel("Test " + index);
            chart.set("Age", userData.getAge());
            chart.set("Pregnancies", userData.getPregnancies());
            chart.set("Glucose", userData.getGlucose());
            chart.set("Blood Pressure", userData.getBloodPressure());
            chart.set("Skin", userData.getSkinThickness());
            chart.set("Insulin", userData.getInsulin());
            chart.set("BMI", userData.getBmi());
            chart.set("Pedigree", userData.getPedigree());
            chartModel.addSeries(chart);
            index++;
        }
        return chartModel;
    }

    /**
     * Retrieves patient records from database, arranging the record from the
     * most recent to the least recent test
     * @return List containing last five tests done by patient
     */
    public List<UserData> modelDistribution() {
        List<UserData> dataList = handler.getUserRecord(getEmail());
        if (dataList != null) {
            List<UserData> dataToReturn = new ArrayList();
            int index = dataList.size() - 1;
            if (index == 0) {
                return dataList;
            } else if (index < 5) {
                for (int i = index; i >= 0; i--) {
                    UserData data = dataList.get(i);
                    dataToReturn.add(data);
                }
            } else if (index >= 5) {
                int endpoint = index - 4;
                for (int i = index; i >= endpoint; i--) {
                    UserData data = dataList.get(i);
                    dataToReturn.add(data);
                }
            }
            return dataToReturn;
        } else {
            return null;
        }
    }

    /**
     * Sets the lineChartModel to be used for visualization by the UI
     * @return lineChartModel
     */
    public LineChartModel getLineModel() {
        return lineModel;
    }

    /**
     * Set user details when a user is successfully queried from the database
     * @param user 
     */
    public void setUser(UserAccount user) {
        setFirstname(user.getFirstname());
        setSurname(user.getLastname());
    }
}
