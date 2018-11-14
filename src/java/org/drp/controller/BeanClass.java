/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.controller;

/**
 *
 * @author Kelechi
 * 
 * This class can never be instantiated by another class.
 * Attributes of this class can only be used via inheritance.
 */
public abstract class BeanClass{
    private int pregnancies;
    private double height;
    private double weight;
    private double bmi;
    private double skinThickness;
    private int insulin;
    private int age;
    private int bloodPressure;
    private double pedigree;
    private double glucose;
    private double riskScore;
    private String message;
    
    public int getPregnancies() {
        return pregnancies;
    }

    public void setPregnancies(int pregnancies) {
        this.pregnancies = pregnancies;
    }

    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getSkinThickness() {
        return skinThickness;
    }

    public void setSkinThickness(double skinThickness) {
        this.skinThickness = skinThickness;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getPedigree() {
        return pedigree;
    }

    public void setPedigree(double pedigree) {
        this.pedigree = pedigree;
    }

    public double getGlucose() {
        return glucose;
    }
    
    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }
    
    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
