/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.handler.utils;

import java.math.BigDecimal;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Osazee
 * 
 * This is an utilities class that provides functions/resources that can be reused
 * throughout the program, eliminating code duplication and littering.
 */
public class AppUtils {
    private static Instances instances;
    
    private static final String HOME = System.getProperty("user.home") + "\\data\\";
    
    public static DataSource getTrainingData() throws Exception {
        return new DataSource(HOME + "trainData.arff");
    }
    
    public static DataSource getTestingData() throws Exception {
        return new DataSource(HOME + "testData.arff");
    }
    
    public static double calculateBMI(double weight, double height){
        double weightKg = weight/2.20462;
        double heightM = height/3.28084;
        double bmi = weightKg/(heightM * heightM);
        
        BigDecimal bd = new BigDecimal(Double.toString(bmi));
        return bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static double roundUp(double value){
        BigDecimal bd = new BigDecimal(Double.toString(value * 100));
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
