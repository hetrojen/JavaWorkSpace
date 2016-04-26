package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the kalitatifdegerlendirme database table.
 * 
 */
@Embeddable
public class KalitatifdegerlendirmePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idSoru;

	private int idSektor;

	private int idFirmatipi;

	private int idDerlendirme;

	private int secim;

	private int idParentDegerlendirme;

    public KalitatifdegerlendirmePK() {
    }
	public int getIdSoru() {
		return this.idSoru;
	}
	public void setIdSoru(int idSoru) {
		this.idSoru = idSoru;
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
	public int getIdDerlendirme() {
		return this.idDerlendirme;
	}
	public void setIdDerlendirme(int idDerlendirme) {
		this.idDerlendirme = idDerlendirme;
	}
	public int getSecim() {
		return this.secim;
	}
	public void setSecim(int secim) {
		this.secim = secim;
	}
	public int getIdParentDegerlendirme() {
		return this.idParentDegerlendirme;
	}
	public void setIdParentDegerlendirme(int idParentDegerlendirme) {
		this.idParentDegerlendirme = idParentDegerlendirme;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KalitatifdegerlendirmePK)) {
			return false;
		}
		KalitatifdegerlendirmePK castOther = (KalitatifdegerlendirmePK)other;
		return 
			(this.idSoru == castOther.idSoru)
			&& (this.idSektor == castOther.idSektor)
			&& (this.idFirmatipi == castOther.idFirmatipi)
			&& (this.idDerlendirme == castOther.idDerlendirme)
			&& (this.secim == castOther.secim)
			&& (this.idParentDegerlendirme == castOther.idParentDegerlendirme);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSoru;
		hash = hash * prime + this.idSektor;
		hash = hash * prime + this.idFirmatipi;
		hash = hash * prime + this.idDerlendirme;
		hash = hash * prime + this.secim;
		hash = hash * prime + this.idParentDegerlendirme;
		
		return hash;
    }
}