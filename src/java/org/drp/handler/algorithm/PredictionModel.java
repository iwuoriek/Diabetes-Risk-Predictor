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
 * 
 * This class implements the prediction functions and calculates the diabetes
 * risk score, making use of WEKA supervised machine learning libraries.
 */
@Service
public class PredictionModel {

    private static final double ERROR_CODE = 903.0;
    
    /**
     * @return returns the classifier for the training dataset
     * @throws Exception to catch any system error that may result in classifying data
     */
    private Classifier model() throws Exception {
        Instances trainData = AppUtils.getTrainingData().getDataSet();
        trainData.setClassIndex(trainData.numAttributes() - 1);
        Instances testData = AppUtils.getTestingData().getDataSet();
        testData.setClassIndex(testData.numAttributes() - 1);

        Classifier classifier = new Logistic();
        classifier.buildClassifier(trainData);
        return classifier;
    }

    /**
     * This method handles instance classification and calculates the risk score for the given user
     * @param dto is the argument used to create the instance to be classified.
     * @return the calculated risk score for the user, returns ERROR_CODE if invalid input is provided.
     */
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
}
