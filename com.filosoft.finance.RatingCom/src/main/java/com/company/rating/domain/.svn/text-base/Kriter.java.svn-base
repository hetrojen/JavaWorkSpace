package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the kriter database table.
 * 
 */
@Entity
@Table(name="kriter")
public class Kriter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idKriter;

	private String formulbuyil;

	private String formulilkyil;

	private int formulTip;

	@Column(name="KriterName")
	private String kriterName;

    public Kriter() {
    }

	public int getIdKriter() {
		return this.idKriter;
	}

	public void setIdKriter(int idKriter) {
		this.idKriter = idKriter;
	}

	public String getFormulbuyil() {
		return this.formulbuyil;
	}

	public void setFormulbuyil(String formulbuyil) {
		this.formulbuyil = formulbuyil;
	}

	public String getFormulilkyil() {
		return this.formulilkyil;
	}

	public void setFormulilkyil(String formulilkyil) {
		this.formulilkyil = formulilkyil;
	}

	public int getFormulTip() {
		return this.formulTip;
	}

	public void setFormulTip(int formulTip) {
		this.formulTip = formulTip;
	}

	public String getKriterName() {
		return this.kriterName;
	}

	public void setKriterName(String kriterName) {
		this.kriterName = kriterName;
	}

}