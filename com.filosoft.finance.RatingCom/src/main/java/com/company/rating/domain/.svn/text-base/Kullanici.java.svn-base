package com.company.rating.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the kullanici database table.
 * 
 */
@Entity
@Table(name="kullanici")
public class Kullanici implements Serializable {
	private static final long serialVersionUID = 1L;
    public static final   int  ADMIN_ROLE=1;
 
	@Id
	private String username;
    
	private String sifre;
    private int role;
    public Kullanici() {
    }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSifre() {
		return this.sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}