/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.UserPOS;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author BSP
 */
public class UserService {
    public UserPOS getByUsername(String username){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        UserPOS user = (UserPOS) session.get(UserPOS.class, username);
        session.getTransaction().commit();
        session.close();
        
        return user;
    }
    
    public UserPOS save(UserPOS user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        session.save(user);
        
        session.getTransaction().commit();
        session.close();
        
        return user;
    }
}
