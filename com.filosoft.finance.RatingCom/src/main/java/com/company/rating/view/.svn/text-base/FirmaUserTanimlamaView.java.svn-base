package com.company.rating.view;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.domain.Firma;
import com.company.rating.domain.Firmatipi;
import com.company.rating.domain.Kullanici;
import com.company.rating.domain.Sektor;
import com.company.rating.domain.Yetkilendirme;
import com.company.rating.domain.YetkilendirmePK;
import com.company.rating.service.RatingService;

@Component("firmaUserView")
@Scope("view")
public class FirmaUserTanimlamaView  extends AbstractView {
@Autowired
private ApplicationData applicationData; 
@Autowired
private SesionBean sesionBean;
@Autowired
private RatingService ratingService;
private String firmaName;
private ArrayList<SelectItem>  sektorList=new ArrayList<SelectItem>();
private ArrayList<SelectItem>  firmaTipiList=new ArrayList<SelectItem>();
private String selectedSektor="-1";
private String selectedFirmaTipi="-1";
private String selectedIhracatV="2";
private String selectedYatirimV="2";
private  String username;
private String sifre;
private  boolean admin;
@PostConstruct
public void  init(){
          // loadSektorCombo();
	   SelectItem item2=new SelectItem();
	   item2.setValue("-1");
	   item2.setLabel("Seçiniz");
	   firmaTipiList.add(item2);
	   SelectItem item1=new SelectItem();
	   item1.setValue("-1");
	   item1.setLabel("Seçiniz");
	   sektorList.add(item1);
	   for(Firmatipi firmatipi:applicationData.getFirmaTipleri()){
	
		   SelectItem item=new SelectItem();
		   item.setValue(firmatipi.getIdFirmatipi()+"");
		   item.setLabel(firmatipi.getFirmaName());
		   firmaTipiList.add(item);
	   }
	
}
public   String   validatFirmaName(){
	if(firmaName==null || firmaName.trim().equals("")){
		return "Firma  adı boş olmamalı";
	}
	if(firmaName.length()>=150){
		return "Firma  adı 150 karakterden az olmalı";
	}
	    for(Firma firma:applicationData.getFirmalar()){
	    	if( firma.getFirmaName().trim().toLowerCase(sesionBean.getTrlocal()).equals(firmaName.trim().toLowerCase(sesionBean.getTrlocal())) ){
	    		
	    		return "Firma önceden tanımlanmış";
	    	}
	    }
	
	return null;
}
public  String validateKullaniciSifre(){
	   if(username==null  || username.trim().equals("")){
		   return  "Kullanıcı ismi Girilmeli";
		   
	   }
	   if (username.length()>=40) {
		   return  "Kullanıcı ismi 40 karakterden uzun olmalıdır";
	   }
	   Kullanici  kullanici  =ratingService.getKullanici(username);
	   if(kullanici!=null){
		   return  "Kullanıcı adı daha önce kullanılmış";
	   }
	   if(sifre==null  || sifre.trim().equals("")){
		   return "şifre girilmemiş";
	   }
	   if(sifre.length()>8){
		   return "şifre 8 karakterden uzun olmamalı";
	   }
	   return null;
}
public String saveFirma(){
	String msg=validatFirmaName();
	if(msg!=null){
		putErrorMessage(msg);
		return null;
	}
	if(!selectedYatirimV.equals("1") &&  !selectedYatirimV.equals("2")){
		putErrorMessage("Sabit Yatırım durumunu seçiniz");
		return null;
	}
	if(!selectedIhracatV.equals("1") &&  !selectedIhracatV.equals("2")){
		putErrorMessage("ihracat durumunu seçiniz");
		return null;
	}
	if(selectedFirmaTipi==null  || selectedFirmaTipi.equals("-1")){
		putErrorMessage("Firma tipini seçiniz");
		return null;
	}
	if(selectedSektor==null  ||  selectedSektor.equals("-1")){
		putErrorMessage("Sektör tipini seçiniz");
		return null;
	}
	msg=validateKullaniciSifre();
	if(msg!=null){
		putErrorMessage(msg);
		return null;
	}
	
	Firma firma=new Firma();
	firma.setIdFirma(generateFirmaId());
	firma.setFirmaName(firmaName);
	if(Integer.valueOf(selectedFirmaTipi)==Firmatipi.HIZMET_FIRMATIP_ID  ||  Integer.valueOf(selectedFirmaTipi)==Firmatipi.URETIM_FIRMATIP_ID){
	if(selectedIhracatV.equals("1") &&  selectedYatirimV.equals("1")){
		firma.setIhracatYatirimCode(Firma.IHRACAT_VAR_SABIT_YATIRIM_VAR_SECIM);
		
	}
	if(selectedIhracatV.equals("1") &&  selectedYatirimV.equals("2")){
		firma.setIhracatYatirimCode(Firma.IHRACAT_VAR_SABIT_YATIRIM_YOK_SECIM);
	}
	if(selectedIhracatV.equals("2") &&  selectedYatirimV.equals("1")){
		firma.setIhracatYatirimCode(Firma.IHRACAT_YOK_SABIT_YATIRIM_VAR_SECIM);
	}
	if(selectedIhracatV.equals("2") &&  selectedYatirimV.equals("2")){
		firma.setIhracatYatirimCode(Firma.IHRACAT_YOK_SABIT_YATIRIM_YOK_SECIM);
	}
	}

	firma.setIdSektor(Integer.valueOf(selectedSektor));
	firma.setIdFirmaTip(Integer.valueOf(selectedFirmaTipi));
	Kullanici  kullanici=new Kullanici();
	kullanici.setUsername(username);
	kullanici.setSifre(sifre);
	if(admin){
		kullanici.setRole(Kullanici.ADMIN_ROLE);
	}
	YetkilendirmePK yetkilendirmePK=new YetkilendirmePK();
	yetkilendirmePK.setIdFirma(firma.getIdFirma());
	yetkilendirmePK.setUsername(username);
	Yetkilendirme yetkilendirme=new Yetkilendirme();
	yetkilendirme.setId(yetkilendirmePK);
	
	try{
		ratingService.saveFirmaUser(firma, kullanici, yetkilendirme);
		applicationData.getFirmalar().add(firma);
		putInfoMessage(firmaName+ "  kaydedildi");
	}catch (Exception e) {
		// TODO: handle exception
		putErrorMessage(firmaName+ " kaydetme başarısız");
	}
	
	return  null;
}
public String getFirmaName() {
	
	return firmaName;
}
public void setFirmaName(String firmaName) {
	this.firmaName = firmaName;
}
public ArrayList<SelectItem> getSektorList() {
	return sektorList;
}
public void setSektorList(ArrayList<SelectItem> sektorList) {
	this.sektorList = sektorList;
}
public ArrayList<SelectItem> getFirmaTipiList() {
	return firmaTipiList;
}
public void setFirmaTipiList(ArrayList<SelectItem> firmaTipiList) {
	this.firmaTipiList = firmaTipiList;
}
public String getSelectedSektor() {
	return selectedSektor;
}
public void setSelectedSektor(String selectedSektor) {
	this.selectedSektor = selectedSektor;
}
public String getSelectedFirmaTipi() {
	return selectedFirmaTipi;
}
public void setSelectedFirmaTipi(String selectedFirmaTipi) {
	this.selectedFirmaTipi = selectedFirmaTipi;
}
public String getSelectedIhracatV() {
	return selectedIhracatV;
}
public void setSelectedIhracatV(String selectedIhracatV) {
	this.selectedIhracatV = selectedIhracatV;
}
public String getSelectedYatirimV() {
	return selectedYatirimV;
}
public void setSelectedYatirimV(String selectedYatirimV) {
	this.selectedYatirimV = selectedYatirimV;
}

private  int  generateFirmaId(){
	int max=0;
	for(Firma firma:applicationData.getFirmalar()){
		if(firma.getIdFirma()>max){
			max=firma.getIdFirma();
		}
	}
	max+=1;
	return max;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getSifre() {
	return sifre;
}
public void setSifre(String sifre) {
	this.sifre = sifre;
}

public void sektorYukle(AjaxBehaviorEvent event) {
	// TODO Auto-generated method stub
	loadSektorCombo();
}
private void  loadSektorCombo(){
	sektorList.clear();
	   SelectItem item1=new SelectItem();
	   item1.setValue("-1");
	   item1.setLabel("Seçiniz");
	   sektorList.add(item1);
	   for(Sektor sektor:applicationData.getSektorler()){
		   if(sektor.getIdSektor()==-1 || sektor.getIdSektor()==-2){
			   continue;
		   }
		   if(sektor.getIdFirmaTpi()==Integer.valueOf(selectedFirmaTipi)){
			     SelectItem item=new SelectItem();
		   item.setValue(sektor.getIdSektor()+"");
		   item.setLabel(sektor.getSektorName());
		   sektorList.add(item);
		   }
		 
	   }
}
public boolean isAdmin() {
	return admin;
}
public void setAdmin(boolean admin) {
	this.admin = admin;
}

}
