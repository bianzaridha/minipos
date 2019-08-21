/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsp.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BSP
 */
@Entity
@Table(name="tb_r_produk_penjualan")
public class ProdukPenjualan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="id_produk",
                nullable = false, updatable = false)
    private Produk produk;
    
    @ManyToOne
    @JoinColumn(name="id_penjualan",
                nullable = false, updatable = false)
    private Penjualan penjualan;
        
    @Column(name="produk_penjualan_kuantitas", columnDefinition = "int")
    private Integer produk_penjualan_kuantitas;
    
    @Column(name="produk_penjualan_harga", columnDefinition = "int")
    private Integer produk_penjualan_harga;
    
    @Column(name="produk_penjualan_subtotal", columnDefinition = "int")
    private Integer produk_penjualan_subtotal;
    
    @Column(name="produk_penjualan_note", columnDefinition = "text")
    private String produk_penjualan_note;

    public ProdukPenjualan(Produk produk, Penjualan penjualan, Integer produk_penjualan_kuantitas, Integer produk_penjualan_harga, Integer produk_penjualan_subtotal, String produk_penjualan_note) {
        this.produk = produk;
        this.penjualan = penjualan;
        this.produk_penjualan_kuantitas = produk_penjualan_kuantitas;
        this.produk_penjualan_harga = produk_penjualan_harga;
        this.produk_penjualan_subtotal = produk_penjualan_subtotal;
        this.produk_penjualan_note = produk_penjualan_note;
    }

    public ProdukPenjualan() {
    }
    
    
    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
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
