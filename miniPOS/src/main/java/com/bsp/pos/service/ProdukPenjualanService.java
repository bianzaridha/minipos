/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.service;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.ProdukPenjualan;
import com.bsp.pos.proxy.ProdukPenjualanProxy;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author BSP
 */
public class ProdukPenjualanService {
    
    
    public ProdukPenjualan save(ProdukPenjualan produkpenjualan){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(produkpenjualan);
        session.getTransaction().commit();
        session.close();
        
        return produkpenjualan;
    }
    
    public List<ProdukPenjualan> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        String query = "from ProdukPenjualan";
        List<ProdukPenjualan> produkpenjualan = (List<ProdukPenjualan>) session.createQuery(query).list();
        
        session.getTransaction().commit();
        session.close();
        return produkpenjualan;
    }
    
    public List<ProdukPenjualan> getByProduk(Integer id){
        String query = "from ProdukPenjualan where id_produk=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<ProdukPenjualan> produkpenjualan = (List<ProdukPenjualan>) session.createQuery(
                                        query).setParameter("id", id).list();
        
        session.getTransaction().commit();
        session.close();
        
        return produkpenjualan;
    }
    
    public List<ProdukPenjualan> getByPenjualan(Integer id){
        String query = "from ProdukPenjualan where id_penjualan=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<ProdukPenjualan> produkpenjualan = (List<ProdukPenjualan>) session.createQuery(
                                        query).setParameter("id", id).list();
        
        session.getTransaction().commit();
        session.close();
        
        return produkpenjualan;
    }
    
    
    public String delete(ProdukPenjualan produkpenjualan){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.delete(produkpenjualan);
        
        session.getTransaction().commit();
        session.close();
        
        return "deleted";
    }
    
    public void batchDeleteByProduk (Integer id){
        List<ProdukPenjualan> list = this.getByProduk(id);
        for (ProdukPenjualan kill : list) {
          this.delete(kill);
        }
    }
    
    public void batchDeleteByPenjualan (Integer id){
        List<ProdukPenjualan> list = this.getByPenjualan(id);
        for (ProdukPenjualan kill : list) {
          this.delete(kill);
        }
    }
    
    public String update(ProdukPenjualanProxy produkpenjualanProxy){
        String query = "update ProdukPenjualan set " +
                "produk_penjualan_harga = :pph, " +
                "produk_penjualan_kuantitas = :ppk, "+
                "produk_penjualan_note = :note, " +
                "produk_penjualan_subtotal = :pps "+
                "where id=:id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.createQuery(query).setParameter("pph", produkpenjualanProxy.getProduk_penjualan_harga())
                .setParameter("ppk", produkpenjualanProxy.getProduk_penjualan_kuantitas())
                .setParameter("note", produkpenjualanProxy.getProduk_penjualan_note())
                .setParameter("pps", produkpenjualanProxy.getProduk_penjualan_subtotal())
                .setParameter("id", produkpenjualanProxy.getId())
                .executeUpdate();
        
        session.getTransaction().commit();
        session.close();
        
        return "Produk Penjualan with id "+ produkpenjualanProxy.getId() + " updated";
    }
}
