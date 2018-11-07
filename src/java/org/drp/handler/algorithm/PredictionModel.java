/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.handler.algorithm;

import org.drp.handler.utils.AppUtils;
import org.drp.model.dto.UserDataDto;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Kelechi
 */
@Service
public class PredictionModel {

    private static final double ERROR_CODE = 903.0;
    
    private Classifier model() throws Exception {
        Instances trainData = AppUtils.getTrainingData().getDataSet();
        trainData.setClassIndex(trainData.numAttributes() - 1);
        Instances testData = AppUtils.getTestingData().getDataSet();
        testData.setClassIndex(testData.numAttributes() - 1);

        Classifier classifier = new Logistic();
        classifier.buildClassifier(trainData);
        return classifier;
    }

    public double predict(UserDataDto dto) {
        try {
            Instances trainData = AppUtils.getTrainingData().getDataSet();
            trainData.setClassIndex(trainData.numAttributes() - 1);

            Classifier classifier = new Logistic();
            classifier.buildClassifier(trainData);

            Instance instance = new Instance(trainData.numAttributes());
            instance.setWeight(1.0);
            instance.setDataset(trainData);
            instance.setValue(trainData.attribute(0), dto.getPregnancies());
            instance.setValue(trainData.attribute(1), dto.getGlucose());
            instance.setValue(trainData.attribute(2), dto.getBloodPressure());
            instance.setValue(trainData.attribute(3), dto.getSkinThickness());
            instance.setValue(trainData.attribute(4), dto.getInsulin());
            instance.setValue(trainData.attribute(5), dto.getBmi());
            instance.setValue(trainData.attribute(6), dto.getPedigree());
            instance.setValue(trainData.attribute(7), dto.getAge());

            double[] probabilityDistribution = model().distributionForInstance(instance);
            double prediction = model().classifyInstance(instance);
            double probability = AppUtils.roundUp(probabilityDistribution[1]);
            
            return probability;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return ERROR_CODE;
        }
    }
    
    public static void main(String[] args) throws Exception{
        UserDataDto dto = new UserDataDto();
        dto.setAge(49);
        dto.setBloodPressure(88);
        dto.setWeight(175);
        dto.setHeight(6.1);
        dto.setBmi(AppUtils.calculateBMI(dto.getWeight(), dto.getHeight()));
        dto.setGlucose(126);
        dto.setInsulin(108);
        dto.setPedigree(0.349);
        dto.setPregnancies(8);
        dto.setSkinThickness(36);
        
        System.out.println(new PredictionModel().predict(dto));
    }
}
