package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the firma database table.
 * 
 */
@Entity
@Table(name="firma")
public class Firma implements Serializable {
	public final static int IHRACAT_VAR_SABIT_YATIRIM_VAR_SECIM=1;
    public final static int IHRACAT_VAR_SABIT_YATIRIM_YOK_SECIM=4;
    
	public final static int IHRACAT_YOK_SABIT_YATIRIM_YOK_SECIM=3;
	
	public final static int IHRACAT_YOK_SABIT_YATIRIM_VAR_SECIM=2;
	public final static  int  YATIRIMLI=1;
	public final static int YATIRIMSIZ=3;

	private static final long serialVersionUID = 1L;

	@Id
	private int idFirma;

	@Column(name="FirmaName")
	private String firmaName;

	private int idFirmaTip;

	private int idSektor;
    private  int ihracatYatirimCode;
    public Firma() {
    }

	public int getIdFirma() {
		return this.idFirma;
	}

	public void setIdFirma(int idFirma) {
		this.idFirma = idFirma;
	}

	public String getFirmaName() {
		return this.firmaName;
	}

	public void setFirmaName(String firmaName) {
		this.firmaName = firmaName;
	}

	public int getIdFirmaTip() {
		return this.idFirmaTip;
	}

	public void setIdFirmaTip(int idFirmaTip) {
		this.idFirmaTip = idFirmaTip;
	}

	public int getIdSektor() {
		return this.idSektor;
	}

	public void setIdSektor(int idSektor) {
		this.idSektor = idSektor;
	}

	public int getIhracatYatirimCode() {
		return ihracatYatirimCode;
	}

	public void setIhracatYatirimCode(int ihracatYatirimCode) {
		this.ihracatYatirimCode = ihracatYatirimCode;
	}

}