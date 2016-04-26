package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the gelirbilancokayit database table.
 * 
 */
@Entity
@Table(name="gelirbilancokayit")
public class Gelirbilancokayit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GelirbilancokayitPK id;

	private BigDecimal cari;

	private BigDecimal cariRevizyon;

	private BigDecimal onceki;

    public Gelirbilancokayit() {
    }

	public GelirbilancokayitPK getId() {
		return this.id;
	}

	public void setId(GelirbilancokayitPK id) {
		this.id = id;
	}
	
	public BigDecimal getCari() {
		return this.cari;
	}

	public void setCari(BigDecimal cari) {
		this.cari = cari;
	}

	public BigDecimal getCariRevizyon() {
		return this.cariRevizyon;
	}

	public void setCariRevizyon(BigDecimal cariRevizyon) {
		this.cariRevizyon = cariRevizyon;
	}

	public BigDecimal getOnceki() {
		return this.onceki;
	}

	public void setOnceki(BigDecimal onceki) {
		this.onceki = onceki;
	}

}