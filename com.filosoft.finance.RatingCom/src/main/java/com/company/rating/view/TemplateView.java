package com.company.rating.view;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.rating.domain.Firmatipi;
import com.company.rating.form.GelirBilancoViewData;
import com.company.rating.form.GrafikData;
import com.company.rating.service.RatingService;
import com.company.rating.view.model.KalitatifTreeModel;
import com.company.rating.view.model.KantitatifTreeModel;
import com.company.rating.view.model.RootInfo;

public class TemplateView extends AbstractView implements Serializable{
	
	@Autowired
	private RatingService ratingService;
@Autowired
private SesionBean sesionBean;

@Autowired
private GrafikData  grafikData;

@Autowired
private GeneralData generalData;
@Autowired
private  GelirBilancoViewData bilancoViewData;


public  String yatayAnaliz(){
	  if(grafikData.getDonenVarliklarGrafik()==null){
		  grafikData.initiateYatayAnaliz();
	  }
	  generalData.setCurrentPage(GeneralData.YATAY_ANALIZ_PAGE);
	  generalData.setActiveMenuGroupId(2);
		generalData.setActiveSubMenuId(1);
	return "yatayAnaliz";
}
public String dikeyAnaliz(){
	generalData.setActiveMenuGroupId(2);

	generalData.setCurrentPage(GeneralData.DIKEY_ANALIZ_PAGE);
	generalData.setActiveMenuGroupId(2);
	generalData.setActiveSubMenuId(0);
	return "dikeyAnaliz";
}
public  String rasyoAnaliz_grafik(){

	generalData.setCurrentPage(GeneralData.RASYO_ANALZI_GRAFIK_PAGE);
	generalData.setActiveMenuGroupId(2);
	generalData.setActiveSubMenuId(2);
	return "rasyoAnalizGrafik";
}
public  String rasyoAnaliz_rasyo(){

	generalData.setCurrentPage(GeneralData.RASYO_ANALZI_RASYO_PAGE);
	generalData.setActiveMenuGroupId(2);
	generalData.setActiveSubMenuId(3);
	return "rasyoAnalizRasyo";
}
public   String  startFirmaTanimlama(){
	generalData.setCurrentPage(GeneralData.FIRMA_TANIMLAMA_PAGE);
	if(generalData.isDegerlendirmeStarted()){
			generalData.setActiveMenuGroupId(4);

	}else{
		generalData.setActiveMenuGroupId(1);
	
	}
	generalData.setActiveSubMenuId(0);
	return  "firmaTanimlama";
}
public String startKantitatifDegerlendirme(){
	if(generalData.getCurrentPage()==GeneralData.KANTITATIF_DEGERLENDIRME_PAGE){
		return null;
	}
	generalData.setCurrentPage(GeneralData.KANTITATIF_DEGERLENDIRME_PAGE);
	generalData.setActiveMenuGroupId(1);
	generalData.setActiveSubMenuId(0);
	return "kantitatifDegerlendirme";
}
public String startKalitatifDegerlendirme(){
	generalData.setCurrentPage(GeneralData.KALITATIF_DEGERLENDIRME_PAGE);
	 
	
	 if(generalData.getFirma().getIdFirmaTip()!=Firmatipi.TAAHUT_FIRMATIPI_ID){
		  //sesionBean.setAutoAnswersComparationTotal();
		  sesionBean.setAutoAnswer(sesionBean.getKalitatifTreeModel());
		
	  }
	  generalData.setActiveMenuGroupId(1);
		generalData.setActiveSubMenuId(1);
	
	return "kalitatifDegerlendirme";
}
public String startSonucDegerlendirme(){
	generalData.setCurrentPage(GeneralData.DEGERLENDIRME_SONUCU_PAGE);
	generalData.setActiveMenuGroupId(1);
	generalData.setActiveSubMenuId(2);
	return "sonucDegerlendirme";
}
public String  raporUret(){
		if (grafikData.getDonenVarliklarGrafik() == null) {
			grafikData.initiateYatayAnaliz();
		}
		if (grafikData.getDonenVarliklarGrafikD() == null) {
			grafikData.initiateDikeyAnaliz();
		}
		if (grafikData.getBorcOdemeGucu() == null) {
			grafikData.initiateRasyoAnaliz();
		}
	generalData.setCurrentPage(GeneralData.RAPOR_URET_PAGE);
	generalData.setActiveMenuGroupId(3);
	generalData.setActiveSubMenuId(0);
	return "raporUret";
}
public   String  cikis(){
	FacesContext context=FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 

	request.getSession().invalidate(); 
	context.getApplication().getNavigationHandler().handleNavigation(context, null, "login");
	return  null;
}
public   String  startGelirBilancoCreate(){
	generalData.setActiveMenuGroupId(0);
	generalData.setActiveSubMenuId(0);
	generalData.setDegerlendirmeStarted(false);
	return  "uploadPage";
}
public String  startDegerlendirme(){

boolean gbkayitexist = ratingService.isGelirBilancoKayitExist(generalData.getYil(),generalData.getDonem(),generalData.getFirma().getIdFirma());
if(!gbkayitexist){
	 putErrorMessage("Gelir bilanço Bilgileri Oluşturulmamış");
	 return "";
}

	bilancoViewData.loadGelirBilancoDataFromDB(generalData.getDonem(),generalData.getYil(),generalData.getFirma().getIdFirma());
	bilancoViewData.calculateGelirTableFormul();    
   
    sesionBean.getRatingViewForm().setKalitatifToplam(new BigDecimal(0));
    sesionBean.getRatingViewForm().setKantitatifHesButDis(false);
    sesionBean.getRatingViewForm().setKantitatifToplam(new BigDecimal(0));
    grafikData.initiateDikeyAnaliz();
    grafikData.initiateRasyoAnaliz();
    grafikData.initiateYatayAnaliz();
    
sesionBean.loadData(generalData.getDonem(),generalData.getYil(),generalData.getFirma().getIdFirma());


	KalitatifTreeModel kaliTreeModel = new KalitatifTreeModel();
	RootInfo kaliRootInfo = new RootInfo();
	kaliRootInfo.setDegerlendirmeRootId(9); //9 kalitatif ağacın kökü
	kaliRootInfo.setParentDegerlendirmeId(0);
	kaliRootInfo.setSiraDizi(2 + "");
	sesionBean.createKalitatifTreeModel(kaliRootInfo, kaliTreeModel);
	
	sesionBean.setKalitatifTreeModel(kaliTreeModel);


	RootInfo kantiRootInfo = new RootInfo();
	kantiRootInfo.setDegerlendirmeRootId(1); //1 kantitatif ağacın kökü
	kantiRootInfo.setParentDegerlendirmeId(0);
	kantiRootInfo.setSiraDizi(1 + "");

	KantitatifTreeModel treeModel = new KantitatifTreeModel();
	sesionBean.createKantitatifTreeModel(kantiRootInfo, treeModel);
	sesionBean.setKantitatifTreeModel(treeModel);
	// sesionBean.calculateRatings();
	sesionBean.calculateRatings(sesionBean.getKantitatifTreeModel());
	generalData.setCurrentPage(GeneralData.KANTITATIF_DEGERLENDIRME_PAGE);
	generalData.setDegerlendirmeStarted(true);
	generalData.setActiveMenuGroupId(1);
	generalData.setActiveSubMenuId(0);
	return "kantitatifDegerlendirme";
}
public  String getActiveLink(){
	return  generalData.getActiveMenuGroupId()+""+generalData.getActiveSubMenuId();
}
}
