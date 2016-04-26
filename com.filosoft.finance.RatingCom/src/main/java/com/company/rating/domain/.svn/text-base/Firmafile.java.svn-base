package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;


/**
 * The persistent class for the firmafiles database table.
 * 
 */
@Entity
@Table(name="firmafiles")
public class Firmafile implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FirmafilePK id;

	private String contentType;

    @Lob()
	private byte[] file;

	private String filename;

	private int size;

    public Firmafile() {
    }

	public FirmafilePK getId() {
		return this.id;
	}

	public void setId(FirmafilePK id) {
		this.id = id;
	}
	
	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}