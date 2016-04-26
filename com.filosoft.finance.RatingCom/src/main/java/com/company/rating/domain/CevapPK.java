package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the cevap database table.
 * 
 */
@Embeddable
public class CevapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idGrup;

	private String cevapName;

    public CevapPK() {
    }
	public int getIdGrup() {
		return this.idGrup;
	}
	public void setIdGrup(int idGrup) {
		this.idGrup = idGrup;
	}
	public String getCevapName() {
		return this.cevapName;
	}
	public void setCevapName(String cevapName) {
		this.cevapName = cevapName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CevapPK)) {
			return false;
		}
		CevapPK castOther = (CevapPK)other;
		return 
			(this.idGrup == castOther.idGrup)
			&& this.cevapName.equals(castOther.cevapName);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGrup;
		hash = hash * prime + this.cevapName.hashCode();
		
		return hash;
    }
}