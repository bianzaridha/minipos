/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BSP
 */
/**
 * @author Aldi Dewangga
 *
 */
@Entity
@Table(name= "tb_m_produk")
public class Produk {
    
    @Id
    @Column(name="produk_id", nullable =false, columnDefinition = "int")
    private Integer id;
    
    @Column(name="produk_nama", columnDefinition = "varchar(100)")
    private String nama;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="kategori_id")
    private Kategori kategori;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="merek_id")
    private Merek merek;
    
    @Column(name="produk_tipe", columnDefinition = "char(1) default '1'")
    private String tipe;
    
    @Column(name="produk_kuantitas", columnDefinition = "int default 0")
    private Integer kuantitas;
    
    @Column(name="produk_harga", columnDefinition = "int default 0")
    private Integer harga;
    
    @Column(name="produk_status", columnDefinition = "char(1) default 'Y'")
    private String status;
    
    @Column(name="produk_image", columnDefinition = "varchar(100)")
    private String image;
    
    @Column(name="produk_stok", columnDefinition = "int default 0")
    private Integer stok;
    
    @Column(name="created_by", columnDefinition = "varchar(100)")
    private String created_by;
    
    @Column(name="created_dt", columnDefinition = "datetime")
    private Date created_dt;
    
    @Column(name="updated_by", columnDefinition = "varchar(100)")
    private String updated_by;
    
    @Column(name="updated_dt", columnDefinition = "datetime")
    private Date updated_dt;

    public Produk() {
    }
    
    public Produk(Kategori byId, Merek merekById, Integer produk_id, String nama2, String tipe2, Integer kuantitas2,
			Integer harga2, String status2, String created_by2, Date created_dt2, String updated_by2,
			Date updated_dt2, String image) {
		this.kategori = byId;
		this.merek = merekById;
		this.id = produk_id;
		this.nama = nama2;
		this.tipe = tipe2;
		this.kuantitas = kuantitas2;
		this.harga = harga2;
		this.status = status2;
		this.created_by = created_by2;
		this.created_dt = created_dt2;
		this.updated_by = updated_by2;
		this.updated_dt = updated_dt2;
		this.image = image;		
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Merek getMerek() {
        return merek;
    }

    public void setMerek(Merek merek) {
        this.merek = merek;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Integer getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }
    
    public Integer getHarga() {
		return harga;
	}

	public void setHarga(Integer harga) {
		this.harga = harga;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Integer getStok() {
		return stok;
	}

	public void setStok(Integer stok) {
		this.stok = stok;
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

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}    
    
}
