package com.bsp.pos.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Kategori;
import com.bsp.pos.model.Produk;

public class KategoriService {
	
	public Kategori save(Kategori Kategori){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.save(Kategori);
        session.getTransaction().commit();
        session.close();
        
        return Kategori;
    }
    
    public List<Kategori> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from Kategori";
        List<Kategori> Kategori = (List<Kategori>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return Kategori;
    }    
    public Kategori getById(Integer id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	Kategori kategori = (Kategori) session.get(Kategori.class, id);
    	session.getTransaction().commit();
    	session.close();
    	
    	return kategori;
    }
    
    public String delete(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Kategori Kategori = this.getById(id);
        session.delete(Kategori);
        
        session.getTransaction().commit();
        session.close();
        
        return "Kategori with id " + id + " deleted";
    }
    
    public String update(Kategori Kategori){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(Kategori);
        
        session.getTransaction().commit();
        session.close();
        
        return "Kategori with id " + Kategori.getId() + " updated";
    }   
}
