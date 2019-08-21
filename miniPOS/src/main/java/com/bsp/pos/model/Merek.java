package com.bsp.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "tb_m_merek")
public class Merek {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="merek_id", nullable =false, columnDefinition = "int")
    private Integer id;
    
    @Column(name="merek_nama", columnDefinition = "varchar(100)")
    private String nama;
    
	public Merek() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
}
