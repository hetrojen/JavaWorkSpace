package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the firmasektor database table.
 * 
 */
@Entity
@Table(name="firmasektor")
public class Firmasektor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FirmasektorPK id;

    public Firmasektor() {
    }

	public FirmasektorPK getId() {
		return this.id;
	}

	public void setId(FirmasektorPK id) {
		this.id = id;
	}
	
}