package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the gelirbilancocode database table.
 * 
 */
@Entity
@Table(name="gelirbilancocode")
public class Gelirbilancocode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int kod;

	private String name;

    public Gelirbilancocode() {
    }

	public int getKod() {
		return this.kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}