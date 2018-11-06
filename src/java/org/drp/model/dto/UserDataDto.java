/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.model.dto;

/**
 *
 * @author Osaki
 */
public class UserDataDto {
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

    /**
     * @return the pregnancies
     */
    public int getPregnancies() {
        return pregnancies;
    }

    /**
     * @param pregnancies the pregnancies to set
     */
    public void setPregnancies(int pregnancies) {
        this.pregnancies = pregnancies;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return the bmi
     */
    public double getBmi() {
        return bmi;
    }

    /**
     * @param bmi the bmi to set
     */
    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    /**
     * @return the skinThickness
     */
    public double getSkinThickness() {
        return skinThickness;
    }

    /**
     * @param skinThickness the skinThickness to set
     */
    public void setSkinThickness(double skinThickness) {
        this.skinThickness = skinThickness;
    }

    /**
     * @return the insulin
     */
    public int getInsulin() {
        return insulin;
    }

    /**
     * @param insulin the insulin to set
     */
    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the bloodPressure
     */
    public int getBloodPressure() {
        return bloodPressure;
    }

    /**
     * @param bloodPressure the bloodPressure to set
     */
    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    /**
     * @return the pedigree
     */
    public double getPedigree() {
        return pedigree;
    }

    /**
     * @param pedigree the pedigree to set
     */
    public void setPedigree(double pedigree) {
        this.pedigree = pedigree;
    }

    /**
     * @return the glucose
     */
    public double getGlucose() {
        return glucose;
    }

    /**
     * @param glucose the glucose to set
     */
    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }
    
    @Override
    public String toString(){
        return "[" + pregnancies + "," + glucose + "," + bloodPressure + "," + skinThickness + "," + insulin + "," + bmi + "," + pedigree + "," + age + "]";
    }
    
}
