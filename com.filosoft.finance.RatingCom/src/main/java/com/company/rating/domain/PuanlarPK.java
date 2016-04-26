package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

/**
 * The primary key class for the puanlar database table.
 * 
 */
@Embeddable
public class PuanlarPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idSektor;

	private int idKriter;
	private BigDecimal puan;
    public PuanlarPK() {
    }
	public int getIdSektor() {
		return this.idSektor;
	}
	public void setIdSektor(int idSektor) {
		this.idSektor = idSektor;
	}
	public int getIdKriter() {
		return this.idKriter;
	}
	public void setIdKriter(int idKriter) {
		this.idKriter = idKriter;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PuanlarPK)) {
			return false;
		}
		PuanlarPK castOther = (PuanlarPK)other;
		return 
			(this.idSektor == castOther.idSektor)
			&& (this.idKriter == castOther.idKriter);

    }
    
	public BigDecimal getPuan() {
		return puan;
	}
	public void setPuan(BigDecimal puan) {
		this.puan = puan;
	}
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSektor;
		hash = hash * prime + this.idKriter;
		
		return hash;
    }
}