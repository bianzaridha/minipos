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
 * @author Bianza
 */
@Entity
@Table(name= "tb_m_opname")
public class Opname {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="opname_id", nullable =false, columnDefinition = "int")
    private int id;
    
    @Column(name="tanggal", columnDefinition = "datetime")
    private Date tanggal;
    
    @Column(name="no_referensi", columnDefinition = "varchar(100)")
    private String noref;
    
    @Column(name="tipe", columnDefinition = "char(1)")
    private String tipe;

    public Opname() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getNoref() {
        return noref;
    }

    public void setNoref(String noref) {
        this.noref = noref;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
    
    
}
