package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the kalitatifdegerlendirme_kayit database table.
 * 
 */
@Embeddable
public class KalitatifdegerlendirmeKayitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idSoru;

	private int idDerlendirme;

	@Column(name="Donem")
	private int donem;

	@Column(name="Yil")
	private int yil;

	private int idFirma;

	private String username;

	private int secim;

	private int idParentDegerlendirme;

    public KalitatifdegerlendirmeKayitPK() {
    }
	public int getIdSoru() {
		return this.idSoru;
	}
	public void setIdSoru(int idSoru) {
		this.idSoru = idSoru;
	}
	public int getIdDerlendirme() {
		return this.idDerlendirme;
	}
	public void setIdDerlendirme(int idDerlendirme) {
		this.idDerlendirme = idDerlendirme;
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
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		if (!(other instanceof KalitatifdegerlendirmeKayitPK)) {
			return false;
		}
		KalitatifdegerlendirmeKayitPK castOther = (KalitatifdegerlendirmeKayitPK)other;
		return 
			(this.idSoru == castOther.idSoru)
			&& (this.idDerlendirme == castOther.idDerlendirme)
			&& (this.donem == castOther.donem)
			&& (this.yil == castOther.yil)
			&& (this.idFirma == castOther.idFirma)
			&& this.username.equals(castOther.username)
			&& (this.secim == castOther.secim)
			&& (this.idParentDegerlendirme == castOther.idParentDegerlendirme);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSoru;
		hash = hash * prime + this.idDerlendirme;
		hash = hash * prime + this.donem;
		hash = hash * prime + this.yil;
		hash = hash * prime + this.idFirma;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.secim;
		hash = hash * prime + this.idParentDegerlendirme;
		
		return hash;
    }
}