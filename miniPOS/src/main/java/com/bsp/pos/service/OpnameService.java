/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Opname;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Bianza
 */
public class OpnameService {
    
    public Opname save(Opname opname){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.save(opname);
        session.getTransaction().commit();
        session.close();
        
        return opname;
    }
    
    public List<Opname> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from Opname";
        List<Opname> opname = (List<Opname>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return opname;
    }
    
    public Opname getById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Opname opname = (Opname) session.get(Opname.class, id);
        session.getTransaction().commit();
        session.close();
        
        return opname;
    }
    
    public String delete(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Opname opname = this.getById(id);
        session.delete(opname);
        
        session.getTransaction().commit();
        session.close();
        
        return "Opname with id " + id + " deleted";
    }
    
    public Opname update(Opname opname){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(opname);
        
        session.getTransaction().commit();
        session.close();
        
        return opname;
    }
}
