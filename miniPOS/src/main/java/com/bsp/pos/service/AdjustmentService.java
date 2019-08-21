/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Adjustment;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Bianza
 */
public class AdjustmentService {
    
    public Adjustment save(Adjustment adjustment){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.save(adjustment);
        session.getTransaction().commit();
        session.close();
        
        return adjustment;
    }
    
    public List<Adjustment> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from Adjustment";
        List<Adjustment> adjustment = (List<Adjustment>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return adjustment;
    }
    
    public Adjustment getById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Adjustment adjustment = (Adjustment) session.get(Adjustment.class, id);
        session.getTransaction().commit();
        session.close();
        
        return adjustment;
    }
    
    public String delete(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Adjustment adjustment = this.getById(id);
        session.delete(adjustment);
        
        session.getTransaction().commit();
        session.close();
        
        return "Adjustment with id " + id + " deleted";
    }
    
    public Adjustment update(Adjustment adjustment){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(adjustment);
        
        session.getTransaction().commit();
        session.close();
        
        return adjustment;
    }
    
}
