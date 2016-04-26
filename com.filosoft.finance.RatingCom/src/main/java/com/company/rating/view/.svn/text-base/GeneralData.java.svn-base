package com.company.rating.view;

import java.io.File;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.company.rating.domain.Firma;
import com.company.rating.domain.Kullanici;

//@Component("generalData")
//@Scope("session")
public class GeneralData implements Serializable{
private int donem;
private int yil;
private Firma firma;
private String firmaId;
private String donem_combo;
private String yil_combo;
private Kullanici kullanici;
private  boolean visibleFirmaSelect;
private   boolean degerlendirmeCompleted;

private int currentPage;
public static  final int KANTITATIF_DEGERLENDIRME_PAGE=1; 
public static  final int KALITATIF_DEGERLENDIRME_PAGE=2; 
public static  final int FIRMA_TANIMLAMA_PAGE=3; 
public static  final int DEGERLENDIRME_SONUCU_PAGE=4; 
public static  final int  DIKEY_ANALIZ_PAGE=5; 
public static  final int YATAY_ANALIZ_PAGE=6; 
public static  final int RASYO_ANALZI_GRAFIK_PAGE=7; 
public static  final int RASYO_ANALZI_RASYO_PAGE=11; 
public static  final int GELIR_BILANCO_PAGE=8; 
public static  final int FILE_UPLOAD_PAGE=9; 
public static  final int RAPOR_URET_PAGE=10; 
private  int  activeMenuGroupId=0;
private  int  activeSubMenuId=0;
private  String folderName;
private boolean  degerlendirmeStarted;
private FacesContext faceCon = FacesContext.getCurrentInstance ();
private ExternalContext externalContext= faceCon.getExternalContext();
private String fileSep = System.getProperty( "file.separator");
public int getDonem() {
	if(donem_combo!=null){
		return Integer.valueOf(donem_combo);
	}
	return donem;
}

public int getYil() {
	if(yil_combo!=null){
		return Integer.valueOf(yil_combo);
	}
	return yil;
}

public Firma getFirma() {
	return firma;
}
public void setFirma(Firma firma) {
	if(folderName==null){
		folderName="F"+firma.getIdFirma()+"F"+donem+"D"+yil+"Y"+kullanici.getUsername();
		
	
	   File  folder=new   File(externalContext.getRealPath(fileSep+folderName));	  
	   if(!folder.exists()){
		     folder.mkdir();
	   }
	
	}
	  
	this.firma = firma;
}
public String getFirmaId() {
	return firmaId;
}
public void setFirmaId(String firmaId) {
	this.firmaId = firmaId;
}
public String getDonem_combo() {
	return donem_combo;
}
public void setDonem_combo(String donem_combo) {
	this.donem_combo = donem_combo;
}
public String getYil_combo() {
	return yil_combo;
}
public void setYil_combo(String yil_combo) {
	this.yil_combo = yil_combo;
}

public boolean isVisibleFirmaSelect() {
	return visibleFirmaSelect;
}

public void setVisibleFirmaSelect(boolean visibleFirmaSelect) {
	this.visibleFirmaSelect = visibleFirmaSelect;
}

public boolean isDegerlendirmeCompleted() {
	return degerlendirmeCompleted;
}

public void setDegerlendirmeCompleted(boolean degerlendirmeCompleted) {
	this.degerlendirmeCompleted = degerlendirmeCompleted;
}


public Kullanici getKullanici() {
	return kullanici;
}

public void setKullanici(Kullanici kullanici) {
	this.kullanici = kullanici;
}

public boolean isUserAdmin() {
	if(kullanici!=null  && kullanici.getRole()==Kullanici.ADMIN_ROLE){
		return true;
	}else{
		return false;
	}
	
}

public int getCurrentPage() {
	return currentPage;
}

public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}

public int getActiveMenuGroupId() {
	return activeMenuGroupId;
}

public void setActiveMenuGroupId(int activeMenuGroupId) {
	this.activeMenuGroupId = activeMenuGroupId;
}

public int getActiveSubMenuId() {
	return activeSubMenuId;
}

public void setActiveSubMenuId(int activeSubMenuId) {
	this.activeSubMenuId = activeSubMenuId;
}

public boolean isDegerlendirmeStarted() {
	return degerlendirmeStarted;
}

public void setDegerlendirmeStarted(boolean degerlendirmeStarted) {
	this.degerlendirmeStarted = degerlendirmeStarted;
}

public String getFolderName() {
	return folderName;
}

public void setFolderName(String folderName) {
	this.folderName = folderName;
}


}
