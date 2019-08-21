package com.bsp.pos.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Penjualan;
import com.bsp.pos.model.ProdukSuspend;
import com.bsp.pos.model.Suspend;

public class SuspendService {
	
    public Suspend save(Suspend suspend){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(suspend);
        session.getTransaction().commit();
        session.close();
        
        return suspend;
    }
    public List<Suspend> getAll(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query = "from Suspend";
        List<Suspend> results = (List<Suspend>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        
        return results;
    }
    public Suspend getById(int id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Suspend suspend = (Suspend) session.get(Suspend.class, id);

		session.getTransaction().commit();
		session.close();
		
		return suspend;
    }
    public String delete(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Suspend suspend = this.getById(id);
        session.delete(suspend);
        
        session.getTransaction().commit();
        session.close();
        
        return "Penjualan with id " + id + " deleted";
    }
}
