package com.company.rating.view.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class KalitatifTreeModel {
       private String name; 
       private BigDecimal agirlik=new BigDecimal(0);
       private int degerlendirmeId;
	   private ArrayList<KalitatifTreeModel> childs;
	   private ArrayList<KalitatifTableModel> degerlendirmeler;
	   private ArrayList<Integer>  parents=new ArrayList<Integer>();
	   private BigDecimal totalPuan=new BigDecimal(0);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<KalitatifTreeModel> getChilds() {
		return childs;
	}
	public void setChilds(ArrayList<KalitatifTreeModel> childs) {
		this.childs = childs;
	}
	public ArrayList<KalitatifTableModel> getDegerlendirmeler() {
		return degerlendirmeler;
	}
	public void setDegerlendirmeler(ArrayList<KalitatifTableModel> degerlendirmeler) {
		this.degerlendirmeler = degerlendirmeler;
	}
	public BigDecimal getAgirlik() {
		return agirlik;
	}
	public void setAgirlik(BigDecimal agirlik) {
		this.agirlik = agirlik;
	}
	public int getDegerlendirmeId() {
		return degerlendirmeId;
	}
	public void setDegerlendirmeId(int degerlendirmeId) {
		this.degerlendirmeId = degerlendirmeId;
	}
	public BigDecimal getTotalPuan() {
		return totalPuan;
	}
	public void setTotalPuan(BigDecimal totalPuan) {
		this.totalPuan = totalPuan;
	}
	public ArrayList<Integer> getParents() {
		return parents;
	}
	public void setParents(ArrayList<Integer> parents) {
		this.parents = parents;
	}

	public String toStringParentIds(){
		String ret="";
		for(Integer s:parents){
			ret+="$"+s.toString();
		}
		return  ret+"$"+degerlendirmeId;
	}
	   
	
}
