package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the otomatikcevap database table.
 * 
 */
@Entity
@Table(name="otomatikcevap")
public class Otomatikcevap implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OtomatikcevapPK id;

	private String cevap;

    public Otomatikcevap() {
    }

	public OtomatikcevapPK getId() {
		return this.id;
	}

	public void setId(OtomatikcevapPK id) {
		this.id = id;
	}
	
	public String getCevap() {
		return this.cevap;
	}

	public void setCevap(String cevap) {
		this.cevap = cevap;
	}

}