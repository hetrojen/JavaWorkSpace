package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the degerlendirmeagaci database table.
 * 
 */
@Entity
@Table(name="degerlendirmeagaci")
public class Degerlendirmeagaci implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DegerlendirmeagaciPK id;

	private BigDecimal agirlik;

	private int sira;

    public Degerlendirmeagaci() {
    }

	public DegerlendirmeagaciPK getId() {
		return this.id;
	}

	public void setId(DegerlendirmeagaciPK id) {
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