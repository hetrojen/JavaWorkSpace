package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the sektor database table.
 * 
 */
@Entity
@Table(name="sektor")
public class Sektor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSektor;

	private int idFirmaTpi;

	@Column(name="SektorName")
	private String sektorName;

    public Sektor() {
    }

	public int getIdSektor() {
		return this.idSektor;
	}

	public void setIdSektor(int idSektor) {
		this.idSektor = idSektor;
	}

	public int getIdFirmaTpi() {
		return this.idFirmaTpi;
	}

	public void setIdFirmaTpi(int idFirmaTpi) {
		this.idFirmaTpi = idFirmaTpi;
	}

	public String getSektorName() {
		return this.sektorName;
	}

	public void setSektorName(String sektorName) {
		this.sektorName = sektorName;
	}

}