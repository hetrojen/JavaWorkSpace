package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the firmatipi database table.
 * 
 */
@Entity
@Table(name="firmatipi")
public class Firmatipi implements Serializable {
	
	public final  static int HIZMET_FIRMATIP_ID=2;
	public final static int URETIM_FIRMATIP_ID=1;
	public final static int FAK_LEAS_ID=3;
	public final static int TAAHUT_FIRMATIPI_ID = 4;
	private static final long serialVersionUID = 1L;

	@Id
	private int idFirmatipi;

	@Column(name="FirmaName")
	private String firmaName;

    public Firmatipi() {
    }

	public int getIdFirmatipi() {
		return this.idFirmatipi;
	}

	public void setIdFirmatipi(int idFirmatipi) {
		this.idFirmatipi = idFirmatipi;
	}

	public String getFirmaName() {
		return this.firmaName;
	}

	public void setFirmaName(String firmaName) {
		this.firmaName = firmaName;
	}

}