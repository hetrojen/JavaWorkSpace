package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the otomatikcevap database table.
 * 
 */
@Embeddable
public class OtomatikcevapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idSoru;

	private int idSektor;

	private int idFirmaTipi;

    public OtomatikcevapPK() {
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
	public int getIdFirmaTipi() {
		return this.idFirmaTipi;
	}
	public void setIdFirmaTipi(int idFirmaTipi) {
		this.idFirmaTipi = idFirmaTipi;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OtomatikcevapPK)) {
			return false;
		}
		OtomatikcevapPK castOther = (OtomatikcevapPK)other;
		return 
			(this.idSoru == castOther.idSoru)
			&& (this.idSektor == castOther.idSektor)
			&& (this.idFirmaTipi == castOther.idFirmaTipi);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSoru;
		hash = hash * prime + this.idSektor;
		hash = hash * prime + this.idFirmaTipi;
		
		return hash;
    }
}