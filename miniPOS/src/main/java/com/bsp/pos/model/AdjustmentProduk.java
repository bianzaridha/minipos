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
 * @author Bianza
 */
@Entity
@Table(name="tb_r_adjustment_produk")
public class AdjustmentProduk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="id_adjustment",
                nullable = false, updatable = false)
    private Adjustment adjustment;
    
    @ManyToOne
    @JoinColumn(name="id_produk",
                nullable = false, updatable = false)
    private Produk produk;
    
    @Column(name="jenis", columnDefinition = "char(1)")
    private String jenis;
    
    @Column(name="kuantitas", columnDefinition = "int")
    private Integer kuantitas;

    public AdjustmentProduk() {
    }

    public AdjustmentProduk(Adjustment adjustment, Produk produk, String jenis, Integer kuantitas) {
        this.adjustment = adjustment;
        this.produk = produk;
        this.jenis = jenis;
        this.kuantitas = kuantitas;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Adjustment getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(Adjustment adjustment) {
        this.adjustment = adjustment;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Integer getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }
    
    
    
    
}
