package com.company.rating.view.model;

import java.math.BigDecimal;

import com.company.rating.domain.Kantitatifdegerlendirme;

public class KantitatifTableModel extends Kantitatifdegerlendirme{
	
	public KantitatifTableModel(Kantitatifdegerlendirme kantitatif) {
		// TODO Auto-generated constructor stub
		super.setAgirlik(kantitatif.getAgirlik());
		super.setId(kantitatif.getId());
		
	} 

	private BigDecimal rasyoOncekiyil=new BigDecimal(0);;
	private BigDecimal rasyoBuyil=new BigDecimal(0);;
	private BigDecimal  ilk=new BigDecimal(0);
	private BigDecimal son=new BigDecimal(0);
     private String agirlikText;
	private BigDecimal puan=new BigDecimal(0);
	private BigDecimal puanOnceki=new BigDecimal(0);
	  private BigDecimal puanByKriter=new BigDecimal(0);
	  private BigDecimal puanByKriterOnceki=new BigDecimal(0);
	private  String kriterName;
	public BigDecimal getRasyoOncekiyil() {
		return rasyoOncekiyil;
	}
	public void setRasyoOncekiyil(BigDecimal rasyoOncekiyil) {
		this.rasyoOncekiyil = rasyoOncekiyil;
	}
	public BigDecimal getRasyoBuyil() {
		return rasyoBuyil;
	}
	public void setRasyoBuyil(BigDecimal rasyoBuyil) {
		this.rasyoBuyil = rasyoBuyil;
	}
	public BigDecimal getPuan() {
		return puan;
	}
	public void setPuan(BigDecimal puan) {
		this.puan = puan;
	}
	public String getKriterName() {
		return kriterName;
	}
	public void setKriterName(String kriterName) {
		this.kriterName = kriterName;
	}

	public BigDecimal getSon() {
		return son;
	}
	public void setSon(BigDecimal son) {
		this.son = son;
	}

	public BigDecimal getPuanByKriter() {
		return puanByKriter;
	}
	public void setPuanByKriter(BigDecimal puanByKriter) {
		this.puanByKriter = puanByKriter;
	}
	public BigDecimal getIlk() {
		return ilk;
	}
	public void setIlk(BigDecimal ilk) {
		this.ilk = ilk;
	}
	public BigDecimal getPuanByKriterOnceki() {
		return puanByKriterOnceki;
	}
	public void setPuanByKriterOnceki(BigDecimal puanByKriterOnceki) {
		this.puanByKriterOnceki = puanByKriterOnceki;
	}
	public String getAgirlikText() {
		
		BigDecimal s;
		s=super.getAgirlik().multiply(new BigDecimal(100));
		return s.intValue()+"%";
		
	}
	public BigDecimal getPuanOnceki() {
		return puanOnceki;
	}
	public void setPuanOnceki(BigDecimal puanOnceki) {
		this.puanOnceki = puanOnceki;
	}
	
	
	

}
