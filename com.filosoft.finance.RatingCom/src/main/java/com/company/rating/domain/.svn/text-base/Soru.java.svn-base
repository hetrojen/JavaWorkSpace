package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the soru database table.
 * 
 */
@Entity
@Table(name="soru")
public class Soru implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSoru;

	@Column(name="SoruName")
	private String soruName;

    public Soru() {
    }

	public int getIdSoru() {
		return this.idSoru;
	}

	public void setIdSoru(int idSoru) {
		this.idSoru = idSoru;
	}

	public String getSoruName() {
		return this.soruName;
	}

	public void setSoruName(String soruName) {
		this.soruName = soruName;
	}

}