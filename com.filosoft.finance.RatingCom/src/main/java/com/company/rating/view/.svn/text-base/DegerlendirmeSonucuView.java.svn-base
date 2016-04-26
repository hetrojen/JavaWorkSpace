package com.company.rating.view;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.view.model.FirmaDerecelendirme;

@Component("degerlendimeSonuc")
@Scope("request")
public class DegerlendirmeSonucuView extends AbstractView{
	@Autowired
	private SesionBean sesionBean;
	@Autowired
	private GeneralData generalData;
	private String derecelendirmeStyle;
	private FirmaDerecelendirme genelDereceInfo;
@PostConstruct
public void  load(){
	  int p= getToplamPuan().intValue();
      genelDereceInfo=FirmaDerecelendirme.getFirmaDerecelendirme(p);   
	
}
public String getKalitatifDerece() {
	  int p= sesionBean.getRatingViewForm().getKalitatifToplam().intValue();
    return FirmaDerecelendirme.getFirmaDerecelendirme(p).getNot();
}
public String getKantitatifDerece() {
	int p= sesionBean.getRatingViewForm().getKantitatifToplam().intValue();
	 return  FirmaDerecelendirme.getFirmaDerecelendirme(p).getNot();
}
public String getDerecelendirmePanelHeader() {
	
	
	return generalData.getFirma().getFirmaName()+ " Derecelendirme Notu";
}
public  String  getToplamDerece(){
	
	String not=genelDereceInfo.getNot();
	if(not.contains("E") || not.contains("e")){
		derecelendirmeStyle="derecelendirmeE";
	}
	if(not.contains("D") || not.contains("d")){
		derecelendirmeStyle="derecelendirmeD";
	}
	if(not.contains("C") || not.contains("c")){
		derecelendirmeStyle="derecelendirmeC";
	}
	if(not.contains("B") || not.contains("b")){
		derecelendirmeStyle="derecelendirmeB";
	}
	if(not.contains("A") || not.contains("a")){
		derecelendirmeStyle="derecelendirmeA";
	}
	return  not;
}
    public  BigDecimal getToplamPuan(){
 
   	 
   	return   sesionBean.getToplamPuan();
   	 
   	 
    }
	

	

	public  String  getDescriptionNot(){
		return genelDereceInfo.getDescriptionNot();
	}
	public  String  getDescriptionFirma(){
		return  genelDereceInfo.getDescriptionFirma();
	}
	public String getDerecelendirmeStyle() {
		return derecelendirmeStyle;
	}
	public void setDerecelendirmeStyle(String derecelendirmeStyle) {
		this.derecelendirmeStyle = derecelendirmeStyle;
	}
	
	
}
