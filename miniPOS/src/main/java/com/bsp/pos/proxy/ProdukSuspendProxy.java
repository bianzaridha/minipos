package com.bsp.pos.proxy;

import java.util.List;

import com.bsp.pos.model.Produk;

public class ProdukSuspendProxy {
	private int id;
	private String nama;
	private List<Produk> produk;
	
	public ProdukSuspendProxy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProdukSuspendProxy(int id,String nama, List<Produk> produk) {
		super();
		this.id = id;
		this.nama = nama;
		this.produk = produk;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public List<Produk> getProduk() {
		return produk;
	}
	public void setProduk(List<Produk> produk) {
		this.produk = produk;
	}
	
	
}
