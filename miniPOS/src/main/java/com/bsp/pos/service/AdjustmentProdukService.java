/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.AdjustmentProduk;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Bianza
 */
public class AdjustmentProdukService {
    
    public AdjustmentProduk save(AdjustmentProduk adjustmentproduk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(adjustmentproduk);
        session.getTransaction().commit();
        session.close();
        
        return adjustmentproduk;
    }
    
    public List<AdjustmentProduk> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from AdjustmentProduk";
        List<AdjustmentProduk> adjustmentproduk = (List<AdjustmentProduk>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return adjustmentproduk;
    }
    
    public List<AdjustmentProduk> getByProduk(Integer id){
        String query = "from AdjustmentProduk where id_produk=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<AdjustmentProduk> adjustmentproduk = (List<AdjustmentProduk>) session.createQuery(
                                        query).setParameter("id", id).list();
        
        session.getTransaction().commit();
        session.close();
        
        return adjustmentproduk;
    }
    
    public List<AdjustmentProduk> getByAdjustment(Integer id){
        String query = "from AdjustmentProduk where id_adjustment=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<AdjustmentProduk> adjustmentproduk = (List<AdjustmentProduk>) session.createQuery(
                                        query).setParameter("id", id).list();
        
        session.getTransaction().commit();
        session.close();
        
        return adjustmentproduk;
    }
    
    
    public String delete(AdjustmentProduk adjustmentproduk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.delete(adjustmentproduk);
        
        session.getTransaction().commit();
        session.close();
        
        return "deleted";
    }
    
    public void batchDeleteByProduk (Integer id){
        List<AdjustmentProduk> list = this.getByProduk(id);
        for (AdjustmentProduk kill : list) {
          this.delete(kill);
        }
    }
    
    public void batchDeleteByAdjustment (Integer id){
        List<AdjustmentProduk> list = this.getByAdjustment(id);
        for (AdjustmentProduk kill : list) {
          this.delete(kill);
        }
    }
    
}