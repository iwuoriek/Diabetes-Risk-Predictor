/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.drp.handler.UserDataHandler;
import org.drp.handler.algorithm.PredictionModel;
import org.drp.handler.utils.AppUtils;
import org.drp.model.dto.UserDataDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Osazee
 */
@Named("dataBean")
@SessionScoped
public class UserDataController extends BeanClass implements java.io.Serializable{

    @Autowired
    private UserDataHandler handler;
    @Autowired
    private PredictionModel model;
    
    public String predictScore(){
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
        
        System.out.println("Data" + dto.toString());
        handler.addData(dto);
        
        setRiskScore(model.predict(dto));
        setMessage(resultMessage(model.predict(dto)));
        return "score?faces-redirect=true";
    }
    
    public String resultMessage(double riskScore){
        if (riskScore <= 30) {
            return "LOW :)";
        } else if (riskScore > 30 && riskScore < 50){
            return "SOMEWHAT LOW :|";
        } else if (riskScore >= 50 && riskScore <= 100){
            return "HIGH :(";
        } else {
            return "Opps there is a problem with the system :(";
        }
    }
}
