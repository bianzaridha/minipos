package com.bsp.pos.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bsp.pos.config.HibernateUtil;
import com.bsp.pos.model.Produk;
import com.bsp.pos.model.ProdukPenjualan;
import com.bsp.pos.model.ProdukSuspend;

public class ProdukSuspendService {
	public ProdukSuspend save(ProdukSuspend produkSuspend){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(produkSuspend);
		session.getTransaction().commit();
		session.close();

		return produkSuspend;
	}
	public List<ProdukSuspend> getByIdSuspend(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Query query = (Query) session.createQuery("from ProdukSuspend where id_suspend = :id_suspend");
		query.setParameter("id_suspend", id);
		List<ProdukSuspend> result = query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return result; 
	}
	public String delete(ProdukSuspend produksuspend){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        session.delete(produksuspend);
        
        session.getTransaction().commit();
        session.close();
        
        return "deleted";
    }
	public void batchDeleteBySuspend (Integer id){
        List<ProdukSuspend> list = this.getByIdSuspend(id);
        for (ProdukSuspend kill : list) {
          this.delete(kill);
        }
    }
	public void batchDeleteByProduk (Integer id){
        List<ProdukSuspend> list = this.getByIdSuspend(id);
        for (ProdukSuspend kill : list) {
          this.delete(kill);
        }
    }
}
