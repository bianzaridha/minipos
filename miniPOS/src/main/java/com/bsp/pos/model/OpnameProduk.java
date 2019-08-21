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
@Table(name="tb_r_opname_produk")
public class OpnameProduk {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="id_opname",
                nullable = false, updatable = false)
    private Opname opname;
    
    @ManyToOne
    @JoinColumn(name="id_produk",
                nullable = false, updatable = false)
    private Produk produk;
    
    
    @Column(name="terhitung", columnDefinition = "int")
    private Integer terhitung;

    public OpnameProduk() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Opname getOpname() {
        return opname;
    }

    public void setOpname(Opname opname) {
        this.opname = opname;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Integer getTerhitung() {
        return terhitung;
    }

    public void setTerhitung(Integer terhitung) {
        this.terhitung = terhitung;
    }
}
