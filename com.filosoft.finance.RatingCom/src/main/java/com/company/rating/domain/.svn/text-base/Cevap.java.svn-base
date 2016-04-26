package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the cevap database table.
 * 
 */
@Entity
@Table(name="cevap")
public class Cevap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CevapPK id;

	private BigDecimal cevapDeger;

    public Cevap() {
    }

	public CevapPK getId() {
		return this.id;
	}

	public void setId(CevapPK id) {
		this.id = id;
	}
	
	public BigDecimal getCevapDeger() {
		return this.cevapDeger;
	}

	public void setCevapDeger(BigDecimal cevapDeger) {
		this.cevapDeger = cevapDeger;
	}

}