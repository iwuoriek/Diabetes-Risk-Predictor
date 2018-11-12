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
 */
@Service
@Transactional
public class UserAccountHandler {
    @Autowired
    private SessionFactory sessionFactory;
    
    public void saveUser(UserAccountDto dto){
        UserAccount user = toUserAccount(dto);
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
    
    public UserAccount getUser(String email){
        Session session = sessionFactory.getCurrentSession();
        return (UserAccount) session.createQuery("FROM UserAccount WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult();
    }
    
    public List<UserAccount> getUsers(){
        List<UserAccount> userList = sessionFactory.getCurrentSession().createQuery("FROM UserAccount").list();
        return userList;
    }
    
    private UserAccount toUserAccount(UserAccountDto dto){
        UserAccount user = new UserAccount();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getSurname());
        user.setEmail(dto.getEmail());
        
        return user;
    }
}
