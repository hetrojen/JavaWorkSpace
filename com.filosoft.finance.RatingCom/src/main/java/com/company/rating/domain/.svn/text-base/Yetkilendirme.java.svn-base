package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the yetkilendirme database table.
 * 
 */
@Entity
@Table(name="yetkilendirme")
public class Yetkilendirme implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private YetkilendirmePK id;

    public Yetkilendirme() {
    }

	public YetkilendirmePK getId() {
		return this.id;
	}

	public void setId(YetkilendirmePK id) {
		this.id = id;
	}
	
}