package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the kalitatifdegerlendirme database table.
 * 
 */
@Entity
@Table(name="kalitatifdegerlendirme")
public class Kalitatifdegerlendirme implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KalitatifdegerlendirmePK id;

	@Column(name="Agirlik")
	private BigDecimal agirlik;

	private int idCevapGrup;

	private int sira;

    public Kalitatifdegerlendirme() {
    }

	public KalitatifdegerlendirmePK getId() {
		return this.id;
	}

	public void setId(KalitatifdegerlendirmePK id) {
		this.id = id;
	}
	
	public BigDecimal getAgirlik() {
		return this.agirlik;
	}

	public void setAgirlik(BigDecimal agirlik) {
		this.agirlik = agirlik;
	}

	public int getIdCevapGrup() {
		return this.idCevapGrup;
	}

	public void setIdCevapGrup(int idCevapGrup) {
		this.idCevapGrup = idCevapGrup;
	}

	public int getSira() {
		return this.sira;
	}

	public void setSira(int sira) {
		this.sira = sira;
	}

}