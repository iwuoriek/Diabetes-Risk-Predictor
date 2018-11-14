/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.drp.handler;

import java.util.List;
import org.drp.model.UserAccount;
import org.drp.model.dto.UserAccountDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class UserAccountHandler {
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * Saves users information of a user to the database table
     * @param dto 
     */
    public void saveUser(UserAccountDto dto){
        UserAccount user = toUserAccount(dto);
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
    
    /**
     * Search for a user in the database via user's email
     * @param email
     * @return information about the user and null if user doesn't exit
     */
    public UserAccount getUser(String email){
        Session session = sessionFactory.getCurrentSession();
        return (UserAccount) session.createQuery("FROM UserAccount WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult();
    }
    
    /**
     * Returns information on all users in the database
     * @return 
     */
    public List<UserAccount> getUsers(){
        List<UserAccount> userList = sessionFactory.getCurrentSession().createQuery("FROM UserAccount").list();
        return userList;
    }
    
    /**
     * Converts a DTO (Data Transfer Object) to an Entity to be saved in database.
     * @param dto
     * @return 
     */
    private UserAccount toUserAccount(UserAccountDto dto){
        UserAccount user = new UserAccount();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getSurname());
        user.setEmail(dto.getEmail());
        
        return user;
    }
}
