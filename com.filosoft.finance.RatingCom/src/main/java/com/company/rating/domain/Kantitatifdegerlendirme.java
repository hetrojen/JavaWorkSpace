package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the kantitatifdegerlendirme database table.
 * 
 */
@Entity
@Table(name="kantitatifdegerlendirme")
public class Kantitatifdegerlendirme implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KantitatifdegerlendirmePK id;

	@Column(name="Agirlik")
	private BigDecimal agirlik;

	private int sira;

    public Kantitatifdegerlendirme() {
    }

	public KantitatifdegerlendirmePK getId() {
		return this.id;
	}

	public void setId(KantitatifdegerlendirmePK id) {
		this.id = id;
	}
	
	public BigDecimal getAgirlik() {
		return this.agirlik;
	}

	public void setAgirlik(BigDecimal agirlik) {
		this.agirlik = agirlik;
	}

	public int getSira() {
		return this.sira;
	}

	public void setSira(int sira) {
		this.sira = sira;
	}

}