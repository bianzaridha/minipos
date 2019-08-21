package com.bsp.pos.service;

import java.util.List;

import org.hibernate.Session;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Merek;

public class MerekService {
	public Merek getMerekById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Merek merek = (Merek) session.get(Merek.class, id);
        session.getTransaction().commit();
        session.close();
        
        return merek;
	}
    public List<Merek> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from Merek";
        List<Merek> merek = (List<Merek>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return merek;
    }
}
