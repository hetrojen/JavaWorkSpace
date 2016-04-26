package com.company.rating.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the puanlar database table.
 * 
 */
@Entity
@Table(name="puanlar")
public class Puanlar implements Serializable, Comparable<Puanlar> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PuanlarPK id;

	private BigDecimal deger;

	

    public Puanlar() {
    }

	public PuanlarPK getId() {
		return this.id;
	}

	public void setId(PuanlarPK id) {
		this.id = id;
	}
	
	public BigDecimal getDeger() {
		return this.deger;
	}

	public void setDeger(BigDecimal deger) {
		this.deger = deger;
	}


 
    public int compareTo(Puanlar o) {
    	// TODO Auto-generated method stub
    
    	return this.id.getPuan().compareTo(o.getId().getPuan());
    }
}