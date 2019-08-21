/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Produk;

/**
 *
 * @author BSP
 */
public class ProdukService {
    
    public Produk save(Produk produk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.save(produk);
        session.getTransaction().commit();
        session.close();
        
        return produk;
    }
    
    public List<Produk> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from Produk";
        List<Produk> produk = (List<Produk>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return produk;
    }
    
    public List<Produk> getAllByMerekId(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        Query query = (Query) session.createQuery("from Produk where merek_id = :merek_id");
        query.setParameter("merek_id", id);
        List<Produk> produk = query.list();
        
        session.getTransaction().commit();
        session.close();
        return produk;
    }
    
    public List<Produk> getAllByKategoriId(Integer id){
    	Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        Query query = (Query) session.createQuery("from Produk where kategori_id = :kategori_id");
        query.setParameter("kategori_id", id);
        List<Produk> produk = query.list();
        
        session.getTransaction().commit();
        session.close();
        return produk;
    }
    
    public Produk getById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Produk produk = (Produk) session.get(Produk.class, id);
        session.getTransaction().commit();
        session.close();
        
        return produk;
    }
    
    public String delete(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Produk produk = this.getById(id);
        session.delete(produk);
        
        session.getTransaction().commit();
        session.close();
        
        return "Produk with id " + id + " deleted";
    }
    
    public String update(Produk produk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(produk);
        
        session.getTransaction().commit();
        session.close();
        
        return "Produk with id " + produk.getId() + " updated";
    }
    
}
