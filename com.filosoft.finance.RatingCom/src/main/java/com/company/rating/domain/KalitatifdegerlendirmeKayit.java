package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the kalitatifdegerlendirme_kayit database table.
 * 
 */
@Entity
@Table(name="kalitatifdegerlendirme_kayit")
public class KalitatifdegerlendirmeKayit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KalitatifdegerlendirmeKayitPK id;

	private BigDecimal cevapDeger;

	private int idCevapGrup;

    public KalitatifdegerlendirmeKayit() {
    }

	public KalitatifdegerlendirmeKayitPK getId() {
		return this.id;
	}

	public void setId(KalitatifdegerlendirmeKayitPK id) {
		this.id = id;
	}
	
	public BigDecimal getCevapDeger() {
		return this.cevapDeger;
	}

	public void setCevapDeger(BigDecimal cevapDeger) {
		this.cevapDeger = cevapDeger;
	}

	public int getIdCevapGrup() {
		return this.idCevapGrup;
	}

	public void setIdCevapGrup(int idCevapGrup) {
		this.idCevapGrup = idCevapGrup;
	}
	

}