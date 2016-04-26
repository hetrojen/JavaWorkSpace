package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the firmasektor database table.
 * 
 */
@Embeddable
public class FirmasektorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idSektor;

	private int idFirmatipi;

    public FirmasektorPK() {
    }
	public int getIdSektor() {
		return this.idSektor;
	}
	public void setIdSektor(int idSektor) {
		this.idSektor = idSektor;
	}
	public int getIdFirmatipi() {
		return this.idFirmatipi;
	}
	public void setIdFirmatipi(int idFirmatipi) {
		this.idFirmatipi = idFirmatipi;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FirmasektorPK)) {
			return false;
		}
		FirmasektorPK castOther = (FirmasektorPK)other;
		return 
			(this.idSektor == castOther.idSektor)
			&& (this.idFirmatipi == castOther.idFirmatipi);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSektor;
		hash = hash * prime + this.idFirmatipi;
		
		return hash;
    }
}