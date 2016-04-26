package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the kantitatifdegerlendirme database table.
 * 
 */
@Embeddable
public class KantitatifdegerlendirmePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idKriter;

	private int idSektor;

	private int idFirmatipi;

	private int idDerlendirme;

	private int idParentDegerlendirme;

    public KantitatifdegerlendirmePK() {
    }
	public int getIdKriter() {
		return this.idKriter;
	}
	public void setIdKriter(int idKriter) {
		this.idKriter = idKriter;
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
		if (!(other instanceof KantitatifdegerlendirmePK)) {
			return false;
		}
		KantitatifdegerlendirmePK castOther = (KantitatifdegerlendirmePK)other;
		return 
			(this.idKriter == castOther.idKriter)
			&& (this.idSektor == castOther.idSektor)
			&& (this.idFirmatipi == castOther.idFirmatipi)
			&& (this.idDerlendirme == castOther.idDerlendirme)
			&& (this.idParentDegerlendirme == castOther.idParentDegerlendirme);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idKriter;
		hash = hash * prime + this.idSektor;
		hash = hash * prime + this.idFirmatipi;
		hash = hash * prime + this.idDerlendirme;
		hash = hash * prime + this.idParentDegerlendirme;
		
		return hash;
    }
}