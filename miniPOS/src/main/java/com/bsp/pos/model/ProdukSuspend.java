package com.bsp.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_r_produk_suspend")
public class ProdukSuspend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="id_produk",
                nullable = false, updatable = false)
    private Produk produk;
    
    @ManyToOne
    @JoinColumn(name="id_suspend",
                nullable = false, updatable = false)
    private Suspend suspend;
    
    @Column(name="produk_suspend_kuantitas", columnDefinition = "int default 0")
    private Integer kuantitas;
    
	public ProdukSuspend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProdukSuspend(Produk produk, Suspend suspend, Integer kuantitas) {
		super();
		this.produk = produk;
		this.suspend = suspend;
		this.kuantitas = kuantitas;
	}

	public Produk getProduk() {
		return produk;
	}

	public void setProduk(Produk produk) {
		this.produk = produk;
	}

	public Suspend getSuspend() {
		return suspend;
	}

	public void setSuspend(Suspend suspend) {
		this.suspend = suspend;
	}

	public Integer getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(Integer kuantitas) {
		this.kuantitas = kuantitas;
	}
}
