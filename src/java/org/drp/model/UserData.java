/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Osaki
 * 
 * An entity class modeled to be the blueprint for the database table UserData.
 * Which stores information of health information entered by the user/patient.
 * Get and Set methods allow for interaction with the class without directly modifying
 * class variables.
 */
@Entity
@Table("UserData")
public class UserData implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    @Column(name="PREGNANCIES")
    private int pregnancies;
    @Column(name="HEIGHT")
    private double height;
    @Column(name="WEIGHT")
    private double weight;
    @Column(name="BMI")
    private double bmi;
    @Column(name="SKIN_THICKNESS")
    private double skinThickness;
    @Column(name="INSULIN")
    private int insulin;
    @Column(name="AGE")
    private int age;
    @Column(name="BLOOD_PRESSURE")
    private int bloodPressure;
    @Column(name="PEDIGREE")
    private double pedigree;
    @Column(name="GLUCOSE")
    private double glucose;
    @Column(name="RISK_SCORE")
    private double riskScore;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = true)
    private UserAccount user;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

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
    
    /**
     * @return the riskScore
     */
    public double getRiskScore() {
        return riskScore;
    }

    /**
     * @param riskScore the riskScore to set
     */
    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    /**
     * @return the user
     */
    public UserAccount getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserAccount user) {
        this.user = user;
    }
    
}
