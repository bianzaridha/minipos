package com.bsp.pos.proxy;

import java.util.List;

import com.bsp.pos.model.Penjualan;

public class PenjualanProxy {
	private Penjualan penjualan;
	private List<ProdukPenjualanProxy> produkpenjualanProxy;
	public PenjualanProxy() {
		super();
	}
	public PenjualanProxy(Penjualan penjualan, List<ProdukPenjualanProxy> produkpenjualanProxy) {
		super();
		this.penjualan = penjualan;
		this.produkpenjualanProxy = produkpenjualanProxy;
	}
	public Penjualan getPenjualan() {
		return penjualan;
	}
	public void setPenjualan(Penjualan penjualan) {
		this.penjualan = penjualan;
	}
	public List<ProdukPenjualanProxy> getProdukpenjualanProxy() {
		return produkpenjualanProxy;
	}
	public void setProdukpenjualanProxy(List<ProdukPenjualanProxy> produkpenjualanProxy) {
		this.produkpenjualanProxy = produkpenjualanProxy;
	}
	
}
