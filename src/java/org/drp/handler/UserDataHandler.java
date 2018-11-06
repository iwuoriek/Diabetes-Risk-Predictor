/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.handler;

import org.drp.model.UserData;
import org.drp.model.dto.UserDataDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pramod
 */
@Service
@Transactional
public class UserDataHandler {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public String addData(UserDataDto dto){
        UserData userData = toUserData(dto);
        Session session = sessionFactory.getCurrentSession();
        session.save(userData);
        return "success";
    }
    
    private UserData toUserData(UserDataDto dto){
        UserData userData = new UserData();
        userData.setAge(dto.getAge());
        userData.setBloodPressure(dto.getBloodPressure());
        userData.setBmi(dto.getBmi());
        userData.setGlucose(dto.getGlucose());
        userData.setHeight(dto.getHeight());
        userData.setInsulin(dto.getInsulin());
        userData.setPedigree(dto.getPedigree());
        userData.setPregnancies(dto.getPregnancies());
        userData.setSkinThickness(dto.getSkinThickness());
        userData.setWeight(dto.getWeight());
        return userData;
    }
}
