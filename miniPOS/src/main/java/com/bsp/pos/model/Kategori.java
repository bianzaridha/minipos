package com.bsp.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_m_kategori")
public class Kategori {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="kategori_id", nullable = false, columnDefinition = "int")
	private int id;
	
	@Column(name="kategori_nama", columnDefinition = "varchar(100)")
	private String nama;
	
	public Kategori() {		
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
	
}
