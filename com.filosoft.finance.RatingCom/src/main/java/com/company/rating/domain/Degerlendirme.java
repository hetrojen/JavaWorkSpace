package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the degerlendirme database table.
 * 
 */
@Entity
@Table(name="degerlendirme")
public class Degerlendirme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDegerlendirme;

	@Column(name="DegerlendirmeName")
	private String degerlendirmeName;

    public Degerlendirme() {
    }

	public int getIdDegerlendirme() {
		return this.idDegerlendirme;
	}

	public void setIdDegerlendirme(int idDegerlendirme) {
		this.idDegerlendirme = idDegerlendirme;
	}

	public String getDegerlendirmeName() {
		return this.degerlendirmeName;
	}

	public void setDegerlendirmeName(String degerlendirmeName) {
		this.degerlendirmeName = degerlendirmeName;
	}

}