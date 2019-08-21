package com.bsp.pos.proxy;

import java.util.Date;

public class ProdukProxy {
	
	private Integer produk_id;
	private Integer kategori_id;
	private Integer merek_id;
	private String nama;
	private String tipe;
	private Integer kuantitas;
	private Integer harga;
	private String status;
	private String created_by;
	private Date created_dt;
	private String updated_by;
	private Date updated_dt;
	private String image;
	private Integer stok;
	
	public Integer getProduk_id() {
		return produk_id;
	}
	public void setProduk_id(Integer produk_id) {
		this.produk_id = produk_id;
	}
	public Integer getKategori_id() {
		return kategori_id;
	}
	public void setKategori_id(Integer kategori_id) {
		this.kategori_id = kategori_id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
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
	public Integer getMerek_id() {
		return merek_id;
	}
	public void setMerek_id(Integer merek_id) {
		this.merek_id = merek_id;
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
	
}
