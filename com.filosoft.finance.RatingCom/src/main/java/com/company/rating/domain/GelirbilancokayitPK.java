package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the gelirbilancokayit database table.
 * 
 */
@Embeddable
public class GelirbilancokayitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idFirma;

	private int yil;

	private int donem;

	private int kod;

    public GelirbilancokayitPK() {
    }
	public int getIdFirma() {
		return this.idFirma;
	}
	public void setIdFirma(int idFirma) {
		this.idFirma = idFirma;
	}
	public int getYil() {
		return this.yil;
	}
	public void setYil(int yil) {
		this.yil = yil;
	}
	public int getDonem() {
		return this.donem;
	}
	public void setDonem(int donem) {
		this.donem = donem;
	}
	public int getKod() {
		return this.kod;
	}
	public void setKod(int kod) {
		this.kod = kod;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GelirbilancokayitPK)) {
			return false;
		}
		GelirbilancokayitPK castOther = (GelirbilancokayitPK)other;
		return 
			(this.idFirma == castOther.idFirma)
			&& (this.yil == castOther.yil)
			&& (this.donem == castOther.donem)
			&& (this.kod == castOther.kod);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idFirma;
		hash = hash * prime + this.yil;
		hash = hash * prime + this.donem;
		hash = hash * prime + this.kod;
		
		return hash;
    }
}