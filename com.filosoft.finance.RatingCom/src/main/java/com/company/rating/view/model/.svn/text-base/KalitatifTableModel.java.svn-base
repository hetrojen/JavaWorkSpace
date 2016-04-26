package com.company.rating.view.model;

import java.math.BigDecimal;

import com.company.rating.domain.Kalitatifdegerlendirme;

public class KalitatifTableModel extends Kalitatifdegerlendirme {
	private   String soruName;
	private BigDecimal puan;
	private String selectedCevapValue="-1";

	
	
	
	public KalitatifTableModel(Kalitatifdegerlendirme  kalitatifdegerlendirme) {
		// TODO Auto-generated constructor stub
		setAgirlik(kalitatifdegerlendirme.getAgirlik());
		setId(kalitatifdegerlendirme.getId());
		setIdCevapGrup(kalitatifdegerlendirme.getIdCevapGrup());
	}
	public String getSoruName() {
		//return soruName+"--"+super.getId().getIdSoru();
		return soruName;
	}
	public void setSoruName(String soruName) {
		this.soruName = soruName;
	}
	public BigDecimal getPuan() {
		return puan;
	}
	public void setPuan(BigDecimal puan) {
		this.puan = puan;
	}
	public String getSelectedCevapValue() {
		return selectedCevapValue;
	}
	public void setSelectedCevapValue(String selectedCevapValue) {
		this.selectedCevapValue = selectedCevapValue;
	}
	public String getAgirlikText() {
		
		BigDecimal s;
		s=super.getAgirlik().multiply(new BigDecimal(100));
		return s.intValue()+"%";
		
	}
	
}
