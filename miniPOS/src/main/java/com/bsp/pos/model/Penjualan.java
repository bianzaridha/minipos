/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BSP
 */

@Entity
@Table(name= "tb_r_penjualan")
public class Penjualan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="penjualan_id", nullable =false, columnDefinition = "int")
    private Integer id;
    
    @Column(name="penjualan_dt", nullable =false, columnDefinition = "datetime")
    private Date penjualan_dt;
    
    @Column(name="penjualan_by", columnDefinition = "varchar(50)")
    private String penjualan_by;
    
    @Column(name="penjualan_noref", columnDefinition = "varchar(50)")
    private String penjualan_noref;
     
    @Column(name="penjualan_biaya_pengiriman", columnDefinition = "int") 
    private Integer penjualan_biaya_pengiriman;
    
    @Column(name="penjualan_status", nullable =false, columnDefinition = "int default 0")
    private Integer penjualan_status;
     
    @Column(name="penjualan_total", nullable =false, columnDefinition = "int")
    private Integer penjualan_total;
    
    @Column(name="penjualan_grand_total", nullable =false, columnDefinition = "int default 0")
    private Integer penjualan_grand_total;
    
    @Column(name="penjualan_diskon", nullable =false, columnDefinition = "int default 0")
    private Integer penjualan_diskon;
    
    @Column(name="penjualan_bayar", nullable =false, columnDefinition = "int default 0")
    private Integer penjualan_bayar;
    
    @Column(name="penjualan_kembali", nullable =false, columnDefinition = "int default 0")
    private Integer penjualan_kembali;
    
    @Column(name="penjualan_catatan_staf", columnDefinition = "varchar(50)")
    private String penjualan_catatan_staf;
    
    @Column(name="penjualan_catatan_pembayaran", columnDefinition = "varchar(50)")
    private String penjualan_catatan_pembayaran;
    
    @Column(name="penjualan_catatan_penjualan", columnDefinition = "varchar(50)")
    private String penjualan_catatan_penjualan;
    
    @Column(name="created_by", columnDefinition = "varchar(100)")
    private String created_by;
    
    @Column(name="created_dt", columnDefinition = "datetime")
    private Date created_dt;
    
    @Column(name="updated_by", columnDefinition = "varchar(100)")
    private String updated_by;
    
    @Column(name="updated_dt", columnDefinition = "datetime")
    private Date updated_dt;

    public Penjualan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPenjualan_dt() {
        return penjualan_dt;
    }

    public void setPenjualan_dt(Date penjualan_dt) {
        this.penjualan_dt = penjualan_dt;
    }

    public String getPenjualan_by() {
        return penjualan_by;
    }

    public void setPenjualan_by(String penjualan_by) {
        this.penjualan_by = penjualan_by;
    }

    public String getPenjualan_noref() {
        return penjualan_noref;
    }

    public void setPenjualan_noref(String penjualan_noref) {
        this.penjualan_noref = penjualan_noref;
    }

    public Integer getPenjualan_biaya_pengiriman() {
        return penjualan_biaya_pengiriman;
    }

    public void setPenjualan_biaya_pengiriman(Integer penjualan_biaya_pengiriman) {
        this.penjualan_biaya_pengiriman = penjualan_biaya_pengiriman;
    }

    public Integer getPenjualan_status() {
        return penjualan_status;
    }

    public void setPenjualan_status(Integer penjualan_status) {
        this.penjualan_status = penjualan_status;
    }

    public Integer getPenjualan_total() {
        return penjualan_total;
    }

    public void setPenjualan_total(Integer penjualan_total) {
        this.penjualan_total = penjualan_total;
    }

    public Integer getPenjualan_grand_total() {
        return penjualan_grand_total;
    }

    public void setPenjualan_grand_total(Integer penjualan_grand_total) {
        this.penjualan_grand_total = penjualan_grand_total;
    }

    public Integer getPenjualan_diskon() {
        return penjualan_diskon;
    }

    public void setPenjualan_diskon(Integer penjualan_diskon) {
        this.penjualan_diskon = penjualan_diskon;
    }

    public Integer getPenjualan_bayar() {
        return penjualan_bayar;
    }

    public void setPenjualan_bayar(Integer penjualan_bayar) {
        this.penjualan_bayar = penjualan_bayar;
    }

    public Integer getPenjualan_kembali() {
        return penjualan_kembali;
    }

    public void setPenjualan_kembali(Integer penjualan_kembali) {
        this.penjualan_kembali = penjualan_kembali;
    }
    
    public String getPenjualan_catatan_staf() {
		return penjualan_catatan_staf;
	}

	public void setPenjualan_catatan_staf(String penjualan_catatan_staf) {
		this.penjualan_catatan_staf = penjualan_catatan_staf;
	}

	public String getPenjualan_catatan_pembayaran() {
		return penjualan_catatan_pembayaran;
	}

	public void setPenjualan_catatan_pembayaran(String penjualan_catatan_pembayaran) {
		this.penjualan_catatan_pembayaran = penjualan_catatan_pembayaran;
	}

	public String getPenjualan_catatan_penjualan() {
		return penjualan_catatan_penjualan;
	}

	public void setPenjualan_catatan_penjualan(String penjualan_catatan_penjualan) {
		this.penjualan_catatan_penjualan = penjualan_catatan_penjualan;
	}

	public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(Date created_dt) {
        this.created_dt = created_dt;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Date getUpdated_dt() {
        return updated_dt;
    }

    public void setUpdated_dt(Date updated_dt) {
        this.updated_dt = updated_dt;
    }
    
    
    
}
