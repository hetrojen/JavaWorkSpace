package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the firmafiles database table.
 * 
 */
@Embeddable
public class FirmafilePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int donem;

	private int yil;

	private int idFirma;

    public FirmafilePK() {
    }
	public int getDonem() {
		return this.donem;
	}
	public void setDonem(int donem) {
		this.donem = donem;
	}
	public int getYil() {
		return this.yil;
	}
	public void setYil(int yil) {
		this.yil = yil;
	}
	public int getIdFirma() {
		return this.idFirma;
	}
	public void setIdFirma(int idFirma) {
		this.idFirma = idFirma;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FirmafilePK)) {
			return false;
		}
		FirmafilePK castOther = (FirmafilePK)other;
		return 
			(this.donem == castOther.donem)
			&& (this.yil == castOther.yil)
			&& (this.idFirma == castOther.idFirma);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.donem;
		hash = hash * prime + this.yil;
		hash = hash * prime + this.idFirma;
		
		return hash;
    }
}