package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * The primary key class for the yetkilendirme database table.
 * 
 */
@Embeddable
public class YetkilendirmePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String username;

	private int idFirma;

    public YetkilendirmePK() {
    }
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIdFirma() {
		return this.idFirma;
	}
	public void setIdFirma(int idFirma) {
		this.idFirma = idFirma;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof YetkilendirmePK)) {
			return false;
		}
		YetkilendirmePK castOther = (YetkilendirmePK)other;
		return 
			this.username.equals(castOther.username)
			&& (this.idFirma == castOther.idFirma);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.idFirma;
		
		return hash;
    }
}