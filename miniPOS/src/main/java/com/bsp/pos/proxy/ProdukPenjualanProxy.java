/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.proxy;

import java.sql.Date;

/**
 *
 * @author BSP
 */
public class ProdukPenjualanProxy {

    private Integer id;
    private Integer id_produk;
    private Integer id_penjualan;
    private Integer produk_penjualan_kuantitas;
    private Integer produk_penjualan_harga;
    private Integer produk_penjualan_subtotal;
    private String produk_penjualan_note;

    public Integer getId() {
        return id;
    }

    public Integer getId_produk() {
        return id_produk;
    }

    public void setId_produk(Integer id_produk) {
        this.id_produk = id_produk;
    }

    public Integer getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(Integer id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    public Integer getProduk_penjualan_kuantitas() {
        return produk_penjualan_kuantitas;
    }

    public void setProduk_penjualan_kuantitas(Integer produk_penjualan_kuantitas) {
        this.produk_penjualan_kuantitas = produk_penjualan_kuantitas;
    }

    public Integer getProduk_penjualan_harga() {
        return produk_penjualan_harga;
    }

    public void setProduk_penjualan_harga(Integer produk_penjualan_harga) {
        this.produk_penjualan_harga = produk_penjualan_harga;
    }

    public Integer getProduk_penjualan_subtotal() {
        return produk_penjualan_subtotal;
    }

    public void setProduk_penjualan_subtotal(Integer produk_penjualan_subtotal) {
        this.produk_penjualan_subtotal = produk_penjualan_subtotal;
    }

    public String getProduk_penjualan_note() {
        return produk_penjualan_note;
    }

    public void setProduk_penjualan_note(String produk_penjualan_note) {
        this.produk_penjualan_note = produk_penjualan_note;
    }
    
    
    
}
