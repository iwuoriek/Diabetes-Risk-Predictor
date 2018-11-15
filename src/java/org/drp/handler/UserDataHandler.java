/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.handler;

import java.util.List;
import org.drp.model.UserAccount;
import org.drp.model.UserData;
import org.drp.model.dto.UserAccountDto;
import org.drp.model.dto.UserDataDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Pramod
 * 
 * A service class to handle CRUD database transactions.
 */
@Service
@Transactional
public class UserDataHandler {
    
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserAccountHandler userHandler;
    
    /**
     * Saves user's provided health information to the database table
     * @param dto
     * @param userDto
     * @return
     */
    public String addData(UserDataDto dto, UserAccountDto userDto){
        UserAccount user = userHandler.getUser(userDto.getEmail());
        UserData userData = toUserData(dto);
        if (user == null){
            userHandler.saveUser(userDto);
            user = userHandler.getUser(userDto.getEmail());
        }
        userData.setUser(user);
        sessionFactory.getCurrentSession().save(userData);
        
        return "success";
    }
    
    /**
     * Get every provided record for a particular user in the database
     * @param email
     * @return 
     */
    public List<UserData> getUserRecord(String email){
        UserAccount user = userHandler.getUser(email);
        if(user != null){
            List<UserData> userRecord = sessionFactory.getCurrentSession()
                    .createCriteria(UserAccount.class)
                    .add(Restrictions.like("user", user))
                    .list();
            return userRecord;
        } else {
            return null;
        }
    }
    
    /**
     * Get every provided record in the database
     * @return 
     */
    public List<UserData> getDataList(){
        List<UserData> dataList = sessionFactory.getCurrentSession().createQuery("FROM UserData").list();
        return dataList;
    }
    
    /**
     * Converts a DTO (Data Transfer Object) to an Entity to be saved in database.
     * @param dto
     * @return 
     */
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
