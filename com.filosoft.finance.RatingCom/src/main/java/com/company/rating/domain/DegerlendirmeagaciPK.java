package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the degerlendirmeagaci database table.
 * 
 */
@Embeddable
public class DegerlendirmeagaciPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idDegerlendirme;

	private int idParent;

	private int idSektor;

	private int idFirmatipi;

	private int secim;

    public DegerlendirmeagaciPK() {
    }
	public int getIdDegerlendirme() {
		return this.idDegerlendirme;
	}
	public void setIdDegerlendirme(int idDegerlendirme) {
		this.idDegerlendirme = idDegerlendirme;
	}
	public int getIdParent() {
		return this.idParent;
	}
	public void setIdParent(int idParent) {
		this.idParent = idParent;
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
	public int getSecim() {
		return this.secim;
	}
	public void setSecim(int secim) {
		this.secim = secim;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DegerlendirmeagaciPK)) {
			return false;
		}
		DegerlendirmeagaciPK castOther = (DegerlendirmeagaciPK)other;
		return 
			(this.idDegerlendirme == castOther.idDegerlendirme)
			&& (this.idParent == castOther.idParent)
			&& (this.idSektor == castOther.idSektor)
			&& (this.idFirmatipi == castOther.idFirmatipi)
			&& (this.secim == castOther.secim);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDegerlendirme;
		hash = hash * prime + this.idParent;
		hash = hash * prime + this.idSektor;
		hash = hash * prime + this.idFirmatipi;
		hash = hash * prime + this.secim;
		
		return hash;
    }
}