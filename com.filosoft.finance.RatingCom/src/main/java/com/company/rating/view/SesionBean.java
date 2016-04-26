package com.company.rating.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.rating.domain.Cevap;
import com.company.rating.domain.Degerlendirmeagaci;
import com.company.rating.domain.Firmatipi;
import com.company.rating.domain.Kalitatifdegerlendirme;
import com.company.rating.domain.KalitatifdegerlendirmeKayit;
import com.company.rating.domain.Kantitatifdegerlendirme;
import com.company.rating.domain.Kriter;
import com.company.rating.domain.Otomatikcevap;
import com.company.rating.domain.Puanlar;
import com.company.rating.form.GelirBilancoViewData;
import com.company.rating.form.RatingViewData;
import com.company.rating.service.RatingService;
import com.company.rating.util.RasyoFileReader;
import com.company.rating.util.TrendFormulUtil;
import com.company.rating.view.model.GelirbilancoModel;
import com.company.rating.view.model.KalitatifTableModel;
import com.company.rating.view.model.KalitatifTreeModel;
import com.company.rating.view.model.KantitatifTableModel;
import com.company.rating.view.model.KantitatifTreeModel;
import com.company.rating.view.model.RootInfo;
//@Component("sesionBean")
//@Scope("session") 
public class SesionBean implements Serializable {
	private RatingService ratingService;
  //  private  BigDecimal autoAnswersComparationTotal=new  BigDecimal(0);
 
	private RatingViewData ratingViewData = new RatingViewData();

	@Autowired
	private GeneralData generalData;
	@Autowired
	private GelirBilancoViewData gelirBilancoViewData;

	@Autowired
	private ApplicationData applicationData;

  private Locale  trlocal=new Locale("tr_TR");
  RasyoFileReader rasyoFileReader = null;
	private HashMap<String, KalitatifTableModel> kalitatiftables = new HashMap<String, KalitatifTableModel>();
	private HashMap<String, KantitatifTableModel> kantitatiftables = new HashMap<String, KantitatifTableModel>();

	private ArrayList<Kantitatifdegerlendirme> kantitatifdegerlendirmeler = new ArrayList<Kantitatifdegerlendirme>();
	private ArrayList<Kalitatifdegerlendirme> kalitatifdegerlendirmeler = new ArrayList<Kalitatifdegerlendirme>();
	private ArrayList<Degerlendirmeagaci> degerlendirmeagaci = new ArrayList<Degerlendirmeagaci>();
	private KantitatifTreeModel kantitatifTreeModel = new KantitatifTreeModel();
	private KalitatifTreeModel kalitatifTreeModel = new KalitatifTreeModel();
	private  ArrayList<Otomatikcevap> otoCevaplar=new ArrayList<Otomatikcevap>();
    private boolean isFromKayit;
//	private ArrayList<Degerlendirmeagaciagirlik> degerlendirmeagaciagirliklar = new ArrayList<Degerlendirmeagaciagirlik>();
	private ArrayList<Puanlar> puanlar = new ArrayList<Puanlar>();
	private ArrayList<KantitatifTableModel> noktaAnaliziDegerlendirmeler = new ArrayList<KantitatifTableModel>();
	private ArrayList<KalitatifdegerlendirmeKayit>  kalitatifdegerlendirmeKayitlar;
	@Autowired
	public SesionBean(RatingService ratingService) {
		this.ratingService = ratingService;
     }
 
	public void loadData(int donem,int yil,int firmaId) {

		kalitatifdegerlendirmeKayitlar = (ArrayList<KalitatifdegerlendirmeKayit>) ratingService
		.getKalitatifDegerlendirmeKayitlari(generalData.getFirma().getIdFirma(), yil,
				donem, generalData.getKullanici().getUsername());
        isFromKayit = true;
		
		degerlendirmeagaci = (ArrayList<Degerlendirmeagaci>) ratingService.getDegerlendirmeagaci(generalData.getFirma().getIdFirmaTip(), generalData.getFirma().getIdSektor(),generalData.getFirma().getIhracatYatirimCode());
		kantitatifdegerlendirmeler = (ArrayList<Kantitatifdegerlendirme>) ratingService.getKantitatifdegerlendirmeler(generalData.getFirma().getIdFirmaTip(), generalData.getFirma().getIdSektor());
		puanlar = (ArrayList<Puanlar>) ratingService.getPuanlar(generalData.getFirma().getIdSektor());
		kalitatifdegerlendirmeler = (ArrayList<Kalitatifdegerlendirme>) ratingService.getKalitatifdegerlendirmeler(generalData.getFirma().getIdFirmaTip(), generalData.getFirma().getIdSektor(), generalData.getFirma().getIhracatYatirimCode());
	
		otoCevaplar=applicationData.getOtoCevapNameList(generalData.getFirma().getIdFirmaTip(), generalData.getFirma().getIdSektor());
	}



//	public void loadDataFromKayit(int donem, int yil) {
//
//		kalitatifdegerlendirmeKayitlar = (ArrayList<KalitatifdegerlendirmeKayit>) ratingService
//				.getKalitatifDegerlendirmeKayitlari(generalData.getFirma().getIdFirma(), yil,
//						donem, generalData.getKullanici().getUsername());
//		isFromKayit = true;
//
//	}


	public ArrayList<KantitatifTableModel> getKantitatifDegerlendirmelerByDegerlendirme(RootInfo rootInfo) {
		ArrayList<KantitatifTableModel> list = new ArrayList<KantitatifTableModel>();

		for (Kantitatifdegerlendirme kantitatifdegerlendirme : kantitatifdegerlendirmeler) {
			if (kantitatifdegerlendirme.getId().getIdDerlendirme() == rootInfo.getDegerlendirmeRootId()
					&& kantitatifdegerlendirme.getId().getIdParentDegerlendirme() == rootInfo.getParentDegerlendirmeId()) {
				KantitatifTableModel model = new KantitatifTableModel(kantitatifdegerlendirme);
				Kriter kriter = applicationData.getKriterById(model.getId().getIdKriter());
				BigDecimal d1 = new BigDecimal(1.2);
				d1.setScale(3, BigDecimal.ROUND_HALF_EVEN);
				BigDecimal d2 = new BigDecimal(2.1);
				d2.setScale(3, BigDecimal.ROUND_HALF_EVEN);
				model.setRasyoOncekiyil(d1);
				model.setRasyoBuyil(d2);
				
//
				if (kriter.getFormulilkyil() != null&& !kriter.getFormulilkyil().trim().equalsIgnoreCase("")) {
					model.setRasyoOncekiyil(gelirBilancoViewData.evalFormula(kriter.getFormulilkyil(),GelirbilancoModel.ONCEKI_DONEM));
					model.setRasyoBuyil(gelirBilancoViewData.evalFormula(kriter.getFormulilkyil(),GelirbilancoModel.CARI_DONEM));
				}
			
				model.setKriterName(kriter.getKriterName());
				list.add(model);
			}
		}
		return list;
	}
 
 

	public ArrayList<KalitatifTableModel> getKalitatifDegerlendirmelerByDegerlendirme(RootInfo rootInfo) {
		ArrayList<KalitatifTableModel> list = new ArrayList<KalitatifTableModel>();
		for (Kalitatifdegerlendirme kalitatifdegerlendirme : kalitatifdegerlendirmeler) {
			if (kalitatifdegerlendirme.getId().getIdDerlendirme() == rootInfo.getDegerlendirmeRootId()
					&& kalitatifdegerlendirme.getId().getIdParentDegerlendirme() == rootInfo.getParentDegerlendirmeId()) {
				KalitatifTableModel model = new KalitatifTableModel(kalitatifdegerlendirme);
				if (isFromKayit) {
					for (KalitatifdegerlendirmeKayit kayit : kalitatifdegerlendirmeKayitlar) {
						if (kayit.getId().getIdDerlendirme() == rootInfo.getDegerlendirmeRootId() && kalitatifdegerlendirme.getId().getIdParentDegerlendirme() == rootInfo.getParentDegerlendirmeId()
								&& kayit.getId().getIdSoru() == kalitatifdegerlendirme.getId().getIdSoru()) {
							model.setSelectedCevapValue(kayit.getCevapDeger().intValue() + "");
						}
					}
				}else{
					for(Cevap cevap:applicationData.getCevaplar()){
						    if(model.getIdCevapGrup()==cevap.getId().getIdGrup() && cevap.getId().getCevapName().equalsIgnoreCase("evet")){
						    	  model.setSelectedCevapValue(cevap.getCevapDeger().intValue()+"");
						    }
					}
				}
				model.setSoruName(applicationData.getSoruName(kalitatifdegerlendirme.getId().getIdSoru()));
				list.add(model);
			}
		}
		return list;
	}


    private void calculatePuanlar(KantitatifTableModel model){
		ArrayList<Puanlar> list = getPuanlarByKriter(model.getId().getIdKriter());
		BigDecimal fark;
		int direction=0;
		boolean rasyobuyil=false,rasyoOncekiyil=false;
		for (int i = 0; i < list.size() - 1; i++) {
		    fark=list.get(i).getDeger().subtract(list.get(i+1).getDeger());
			direction=fark.compareTo(new BigDecimal(0));
			if(direction!=0){
				break;
			}
		}
		
		for (int i = 0; i < list.size() - 1; i++) {
			if(model.getRasyoBuyil().compareTo(list.get(i).getDeger())== 0 ){
				model.setPuan(model.getAgirlik().multiply(list.get(i).getId().getPuan()));
				model.setPuanByKriter(list.get(i).getId().getPuan());
				rasyobuyil=true;
				break;
			}
		}
		for (int i = 0; i < list.size() - 1; i++) {
			if(model.getRasyoOncekiyil().compareTo(list.get(i).getDeger()) == 0){
				model.setPuanByKriterOnceki(list.get(i).getId().getPuan());
				rasyoOncekiyil=true;
				break;
			}
		}
		
		if (direction < 0) {
			if (!rasyobuyil) {
				if(model.getRasyoBuyil().compareTo(list.get(0).getDeger()) < 0){
					model.setPuan(new BigDecimal(0));
					model.setPuanByKriter(new BigDecimal(0));
				}else{
					for (int i = 0; i < list.size() - 1; i++) {
						
						
						if (model.getRasyoBuyil().compareTo(list.get(i).getDeger()) > 0 && model.getRasyoBuyil().compareTo(list.get(i + 1).getDeger())<0) {
							model.setPuan(model.getAgirlik().multiply(list.get(i).getId().getPuan()));
							model.setPuanByKriter(list.get(i).getId().getPuan());
	                        rasyobuyil=true;   
							
						}
						if(!rasyobuyil &&  i==(list.size()-2)){
							model.setPuan(model.getAgirlik().multiply(list.get(i+1).getId().getPuan()));
							model.setPuanByKriter(list.get(i+1).getId().getPuan());
						}
					}
				}
			
			}

			if (!rasyoOncekiyil) {
				if(model.getRasyoOncekiyil().compareTo(list.get(0).getDeger()) < 0 ){
					model.setPuanByKriterOnceki(new BigDecimal(0));
				}else{
					for (int i = 0; i < list.size() - 1; i++) {
						if (model.getRasyoOncekiyil().compareTo(list.get(i).getDeger()) > 0 && model.getRasyoOncekiyil().compareTo(list.get(i + 1).getDeger()) <0) {
							model.setPuanByKriterOnceki(list.get(i).getId().getPuan());
							rasyoOncekiyil=true;
						}
						if(!rasyoOncekiyil && i==(list.size()-2)){
							model.setPuanByKriterOnceki(list.get(i+1).getId().getPuan());
						}
					}
				}
				
			
			}
		}
		if(direction>0){
			if (!rasyobuyil) {
				if(model.getRasyoBuyil().compareTo(list.get(0).getDeger()) > 0){
					model.setPuan(new BigDecimal(0));
					model.setPuanByKriter(new BigDecimal(0));
				}else{
				for (int i = 0; i < list.size() - 1; i++) {
					if (model.getRasyoBuyil().compareTo(list.get(i).getDeger()) < 0 && model.getRasyoBuyil().compareTo(list.get(i + 1).getDeger()) >0) {
						model.setPuan(model.getAgirlik().multiply(list.get(i).getId().getPuan()));
						model.setPuanByKriter(list.get(i).getId().getPuan());
                        rasyobuyil=true;   
						
					}
					if(!rasyobuyil &&  i==(list.size()-2)){
						model.setPuan(model.getAgirlik().multiply(list.get(i+1).getId().getPuan()));
						model.setPuanByKriter(list.get(i+1).getId().getPuan());
					}
				}
				}
				
			}

			if (!rasyoOncekiyil) {
				if(model.getRasyoOncekiyil().compareTo(list.get(0).getDeger()) > 0 ){
					model.setPuanByKriterOnceki(new BigDecimal(0));
				}else{
				for (int i = 0; i < list.size() - 1; i++) {
					if (model.getRasyoOncekiyil().compareTo(list.get(i).getDeger()) < 0 && model.getRasyoOncekiyil().compareTo(list.get(i + 1).getDeger()) >0) {
						model.setPuanByKriterOnceki(list.get(i).getId().getPuan());
						rasyoOncekiyil=true;
					}
					if(!rasyoOncekiyil && i==(list.size()-2)){
						model.setPuanByKriterOnceki(list.get(i+1).getId().getPuan());
					}
				}
				}
				
			}
		}
		

    }
   
	private void calculateRatingNoktaAnaliz(KantitatifTreeModel kantitatifTreeModel) {
		if (kantitatifTreeModel.getDegerlendirmeler() != null) {
			BigDecimal tempSum = new BigDecimal(0);
			if (kantitatifTreeModel.getParents().indexOf(KantitatifTreeModel.NOKATA_ANALIZ_DEGERLENDIRMEID) == 1
					|| kantitatifTreeModel.getDegerlendirmeId() == KantitatifTreeModel.NOKATA_ANALIZ_DEGERLENDIRMEID) {

				for (KantitatifTableModel model : kantitatifTreeModel.getDegerlendirmeler()) {
					calculatePuanlar(model);
					tempSum = tempSum.add(model.getPuan());
				}

				
                tempSum=tempSum.multiply(new BigDecimal(100));
				kantitatifTreeModel.setTotalPuan(tempSum.multiply(kantitatifTreeModel.getAgirlik()));
				noktaAnaliziDegerlendirmeler.addAll(kantitatifTreeModel.getDegerlendirmeler());
				tempSum = new BigDecimal(0);
			}
		}
		if (kantitatifTreeModel.getChilds() != null) {
			for (KantitatifTreeModel treeModel : kantitatifTreeModel.getChilds()) {
				calculateRatingNoktaAnaliz(treeModel);
			}
			BigDecimal tempSum = new BigDecimal(0);
			for (KantitatifTreeModel treeModel : kantitatifTreeModel.getChilds()) {
				tempSum = tempSum.add(treeModel.getTotalPuan());
			}
			kantitatifTreeModel.setTotalPuan(tempSum.multiply(kantitatifTreeModel.getAgirlik()));
		
		}
	}

	public void calculateRatings(KantitatifTreeModel kantitatifTreeModel) {

		calculateRatingNoktaAnaliz(kantitatifTreeModel);
		if (kantitatifTreeModel.getParents().indexOf(KantitatifTreeModel.NOKATA_ANALIZ_DEGERLENDIRMEID) == 1
				|| kantitatifTreeModel.getDegerlendirmeId() == KantitatifTreeModel.NOKATA_ANALIZ_DEGERLENDIRMEID) {
			return;
		}

		if (kantitatifTreeModel.getDegerlendirmeler() != null) {
			BigDecimal tempSum = new BigDecimal(0);
			if (kantitatifTreeModel.getParents().indexOf(KantitatifTreeModel.TREND_ANALIZ_DEGERLENDIRMEID) == -1
					&& kantitatifTreeModel.getDegerlendirmeId() != KantitatifTreeModel.TREND_ANALIZ_DEGERLENDIRMEID) {

				for (KantitatifTableModel model : kantitatifTreeModel.getDegerlendirmeler()) {
					calculatePuanlar(model);
					tempSum = tempSum.add(model.getPuan());
				}
				tempSum=tempSum.multiply(new BigDecimal(100));
				kantitatifTreeModel.setTotalPuan(tempSum.multiply(kantitatifTreeModel.getAgirlik()));
				tempSum = new BigDecimal(0);
			}

			else {// trend analizi
				tempSum = new BigDecimal(0);
				for (KantitatifTableModel model : kantitatifTreeModel.getDegerlendirmeler()) {
					for (KantitatifTableModel tableModel : noktaAnaliziDegerlendirmeler) {
						if (tableModel.getId().getIdKriter() == model.getId().getIdKriter()) {
							model.setSon(tableModel.getPuanByKriter());
							model.setIlk(tableModel.getPuanByKriterOnceki());
							Kriter kriter = applicationData.getKriterById(model.getId().getIdKriter());
							model.setPuan(TrendFormulUtil.getTrendPuan(kriter, tableModel, tableModel.getPuan()));
							break;
						}
						
					}
					tempSum = tempSum.add(model.getPuan().multiply(model.getAgirlik()));
				} 
				tempSum=tempSum.multiply(new BigDecimal(100));
				kantitatifTreeModel.setTotalPuan(tempSum.multiply(kantitatifTreeModel.getAgirlik()));
				tempSum = new BigDecimal(0);
			}

		}
		if (kantitatifTreeModel.getChilds() != null) {
			for (KantitatifTreeModel treeModel : kantitatifTreeModel.getChilds()) {
				calculateRatings(treeModel);
			}
			BigDecimal tempSum = new BigDecimal(0);
			for (KantitatifTreeModel treeModel : kantitatifTreeModel.getChilds()) {
				tempSum = tempSum.add(treeModel.getTotalPuan());
			}
			if(kantitatifTreeModel.getDegerlendirmeId()==1)//KantitatifKök
			{
				kantitatifTreeModel.setTotalPuan(tempSum);
			}else{
				kantitatifTreeModel.setTotalPuan(tempSum.multiply(kantitatifTreeModel.getAgirlik()));
			}
		

		}
	}

	public void calculateRatings(KalitatifTreeModel kalitatifTreeModel) {
		if (kalitatifTreeModel.getDegerlendirmeler() != null) {
			BigDecimal selectedVal;
			BigDecimal tempSum = new BigDecimal(0);
			for (KalitatifTableModel model : kalitatifTreeModel.getDegerlendirmeler()) {
				try {
					selectedVal = new BigDecimal(model.getSelectedCevapValue());
					if (model.getSelectedCevapValue().equalsIgnoreCase("-1")) {
						selectedVal = new BigDecimal("0");
					}
				} catch (Exception e) {
					// se TODO: hasendle exception
					selectedVal = new BigDecimal("0");
				}

				model.setPuan(selectedVal.multiply(model.getAgirlik()));
				tempSum = tempSum.add(model.getPuan());
				System.out.println(model.getSoruName()+"--"+model.getPuan());
			}
			tempSum=tempSum.multiply(new BigDecimal(100));
			kalitatifTreeModel.setTotalPuan(tempSum.multiply(kalitatifTreeModel.getAgirlik()));
			System.out.println(tempSum.toString()+"--"+kalitatifTreeModel.getAgirlik());
			tempSum = new BigDecimal(0);

		}
		if (kalitatifTreeModel.getChilds() != null) {
			for (KalitatifTreeModel treeModel : kalitatifTreeModel.getChilds()) {
				calculateRatings(treeModel);
			}
			BigDecimal tempSum = new BigDecimal(0);
			for (KalitatifTreeModel treeModel : kalitatifTreeModel.getChilds()) {
				tempSum = tempSum.add(treeModel.getTotalPuan());
			}
			if(kalitatifTreeModel.getDegerlendirmeId()==9)//Kalitatif Kök
			{
				kalitatifTreeModel.setTotalPuan(tempSum);
			}else{
				kalitatifTreeModel.setTotalPuan(tempSum.multiply(kalitatifTreeModel.getAgirlik()));
			}
			
			System.out.println(tempSum.toString()+"--"+kalitatifTreeModel.getAgirlik());
		}

	}

	private KalitatifTreeModel getKalitatifTreeModelById(int degerlendirmeId, KalitatifTreeModel model) {

		if (model.getDegerlendirmeId() == degerlendirmeId) {
			return model;
		} else if (model.getChilds() != null) {
			for (KalitatifTreeModel m : model.getChilds()) {
				KalitatifTreeModel mo = getKalitatifTreeModelById(degerlendirmeId, m);
				if (mo != null) {
					return mo;
				}
			}
		}

		return null;
	}

	public void setAutoAnswer(KalitatifTreeModel kalitatifTreeModel2) {
		// TODO Auto-generated method stub
		if (generalData.getFirma().getIdFirmaTip() == Firmatipi.FAK_LEAS_ID) {
			if (kalitatifTreeModel2.getDegerlendirmeId() == 20) {

//		
//				// 49,50
//				if (autoAnswersComparationTotal.compareTo(new BigDecimal(0)) == 0) {
//					for (KalitatifTableModel m : kalitatifTreeModel2.getDegerlendirmeler()) {
//						
//						m.setSelectedCevapValue("-1");
//					}
//				} else {

					for (KalitatifTableModel m : kalitatifTreeModel2.getDegerlendirmeler()) {
						if(m.getId().getIdSoru()==67){//Ekonomik ve siyasal gelişmelerden çabuk etkileniyor mu?
							m.setSelectedCevapValue(0+"");
						}else{
							m.setSelectedCevapValue(1+"");
						}
					
					
					}
//				}

				//

			}
		}      
		//Otomatik cevaplar tablosunundan getir
		if (generalData.getFirma().getIdFirmaTip() == Firmatipi.URETIM_FIRMATIP_ID || generalData.getFirma().getIdFirmaTip() == Firmatipi.HIZMET_FIRMATIP_ID) {
			if (kalitatifTreeModel2.getDegerlendirmeId() == 19 ||kalitatifTreeModel2.getDegerlendirmeId() == 20) {

			
//				// 
//				if (autoAnswersComparationTotal.compareTo(new BigDecimal(0)) == 0) {
//					for (KalitatifTableModel m : kalitatifTreeModel2.getDegerlendirmeler()) {
//						m.setSelectedCevapValue("-1");
//					}
//				} else {
					for (KalitatifTableModel m : kalitatifTreeModel2.getDegerlendirmeler()) {
						String cvp=getOtoCevapName(m.getId().getIdSoru());
						BigDecimal cvpVal=applicationData.getCevapValueByName(cvp, m.getIdCevapGrup());
						m.setSelectedCevapValue(cvpVal.intValue()+"");
					
					}
//				}

				//

			}
	
		}   
		if(kalitatifTreeModel2.getChilds()!=null){
			for(KalitatifTreeModel kalitatifTreeModel:kalitatifTreeModel2.getChilds()){
				setAutoAnswer(kalitatifTreeModel);
			}
		}

	}

	public void calculateRatings() {
		calculateRatings(kalitatifTreeModel);
		calculateRatings(kantitatifTreeModel);

	}

	private ArrayList<Puanlar> getPuanlarByKriter(int kriterId) {
		ArrayList<Puanlar> puanlar = new ArrayList<Puanlar>();
		for (Puanlar puan : this.puanlar) {
			if (kriterId == puan.getId().getIdKriter()) {
				puanlar.add(puan);
			}
			java.util.Collections.sort(puanlar);
		}
		return puanlar;
	}


	public BigDecimal getAgirlik(RootInfo rootInfo) {
		for (Degerlendirmeagaci degerlendirmeagaci : this.degerlendirmeagaci) {
			if (degerlendirmeagaci.getId().getIdDegerlendirme() == rootInfo.getDegerlendirmeRootId() &&  degerlendirmeagaci.getId().getIdParent()==rootInfo.getParentDegerlendirmeId()) {
				return degerlendirmeagaci.getAgirlik();
			}
		}
		return null;
	}

	public void createKantitatifTreeModel(RootInfo rootInfo, KantitatifTreeModel treeModel) {

		treeModel.setDegerlendirmeId(rootInfo.getDegerlendirmeRootId());
		treeModel.setAgirlik(getAgirlik(rootInfo));
		treeModel.setName(rootInfo.getSiraDizi() + "-" + applicationData.getDegerlendirmeName(rootInfo.getDegerlendirmeRootId()));

		for (Degerlendirmeagaci degerlendirmeagaci : getDegerlendirmeagaci()) {
			int degerlendirmeId = degerlendirmeagaci.getId().getIdDegerlendirme();
            int parentDegerlendirme=degerlendirmeagaci.getId().getIdParent();
            
            RootInfo p_rootInfo=new RootInfo();
            p_rootInfo.setDegerlendirmeRootId(degerlendirmeId);
            p_rootInfo.setParentDegerlendirmeId(parentDegerlendirme);
            
			if (degerlendirmeagaci.getId().getIdParent() == rootInfo.getDegerlendirmeRootId()) {
				String newSiraDizi = rootInfo.getSiraDizi() + "." + degerlendirmeagaci.getSira();
				p_rootInfo.setSiraDizi(newSiraDizi);
				if (treeModel.getChilds() == null) {
					ArrayList<KantitatifTreeModel> newChilds = new ArrayList<KantitatifTreeModel>();
					treeModel.setChilds(newChilds);

				}
				KantitatifTreeModel model = new KantitatifTreeModel();

				for (Integer d : treeModel.getParents()) {
					model.getParents().add(d);
				}
				model.getParents().add(rootInfo.getDegerlendirmeRootId());

				if (isContainDegerlendirmeInKantitatif(p_rootInfo)) {

					model.setDegerlendirmeId(degerlendirmeId);
					ArrayList<KantitatifTableModel> list = getKantitatifDegerlendirmelerByDegerlendirme(p_rootInfo);
					model.setDegerlendirmeler(list);
					model.setName(newSiraDizi + "-" + applicationData.getDegerlendirmeName(degerlendirmeId));
					model.setAgirlik(degerlendirmeagaci.getAgirlik());
					treeModel.getChilds().add(model);
				

					// getKantitatiftables().put(degerlendirmeId+"",list);

				} else {

					treeModel.getChilds().add(model);
					createKantitatifTreeModel(p_rootInfo, model);

				}

			}

		}

	}

	public void createKalitatifTreeModel(RootInfo rootInfo, KalitatifTreeModel treeModel) {

		treeModel.setDegerlendirmeId(rootInfo.getDegerlendirmeRootId());
		treeModel.setAgirlik(getAgirlik(rootInfo));

		treeModel.setName(rootInfo.getSiraDizi() + "-" + applicationData.getDegerlendirmeName(rootInfo.getDegerlendirmeRootId()));

		for (Degerlendirmeagaci degerlendirmeagaci : getDegerlendirmeagaci()) {
			int degerlendirmeId = degerlendirmeagaci.getId().getIdDegerlendirme();
			 int parentDegerlendirme=degerlendirmeagaci.getId().getIdParent();
	            
	            RootInfo p_rootInfo=new RootInfo();
	            p_rootInfo.setDegerlendirmeRootId(degerlendirmeId);
	            p_rootInfo.setParentDegerlendirmeId(parentDegerlendirme);
	            
			if (degerlendirmeagaci.getId().getIdParent() == rootInfo.getDegerlendirmeRootId()) {
				String newSiraDizi = rootInfo.getSiraDizi() + "." + degerlendirmeagaci.getSira();
				p_rootInfo.setSiraDizi(newSiraDizi);
				if (treeModel.getChilds() == null) {
					ArrayList<KalitatifTreeModel> newChilds = new ArrayList<KalitatifTreeModel>();
					treeModel.setChilds(newChilds);

				}
				KalitatifTreeModel model = new KalitatifTreeModel();
				for (Integer d : treeModel.getParents()) {
					model.getParents().add(d);
				}
				model.getParents().add(rootInfo.getDegerlendirmeRootId());

				if (isContainDegerlendirmeInKalitatif(p_rootInfo)) {

					model.setDegerlendirmeId(degerlendirmeId);
					ArrayList<KalitatifTableModel> list = getKalitatifDegerlendirmelerByDegerlendirme(p_rootInfo);
					model.setDegerlendirmeler(list);
					model.setName(newSiraDizi + "-" + applicationData.getDegerlendirmeName(degerlendirmeId));

					model.setAgirlik(degerlendirmeagaci.getAgirlik());
					treeModel.getChilds().add(model);
					for (KalitatifTableModel kalitatifTableModel : list) {
						getKalitatiftables().put(parentDegerlendirme+"$"+degerlendirmeId + "$" + kalitatifTableModel.getId().getIdSoru(), kalitatifTableModel);
					}

				} else {

					// treeModel.setAgirlik(degerlendirmeagaci.getAgirlik());
					treeModel.getChilds().add(model);
					createKalitatifTreeModel(p_rootInfo, model);
				}

			}

		}

	}

	private boolean isContainDegerlendirmeInKantitatif(RootInfo rootInfo) {
		for (Kantitatifdegerlendirme kantitatifdegerlendirme : getKantitatifdegerlendirmeler()) {
			if (kantitatifdegerlendirme.getId().getIdDerlendirme() == rootInfo.getDegerlendirmeRootId()
					&& kantitatifdegerlendirme.getId().getIdParentDegerlendirme() == rootInfo.getParentDegerlendirmeId()) {
				return true;
			}
		}
		return false;
	}

	private boolean isContainDegerlendirmeInKalitatif(RootInfo rootInfo) {
		for (Kalitatifdegerlendirme kalitatifdegerlendirme : getKalitatifdegerlendirmeler()) {
			if (kalitatifdegerlendirme.getId().getIdDerlendirme() == rootInfo.getDegerlendirmeRootId()
					&& kalitatifdegerlendirme.getId().getIdParentDegerlendirme() == rootInfo.getParentDegerlendirmeId()) {
				return true;
			}
		}
		return false;
	}

	public HashMap<String, KalitatifTableModel> getKalitatiftables() {
		return kalitatiftables;
	}

	public void setKalitatiftables(HashMap<String, KalitatifTableModel> kalitatiftables) {
		this.kalitatiftables = kalitatiftables;
	}



	public ArrayList<Kantitatifdegerlendirme> getKantitatifdegerlendirmeler() {
		return kantitatifdegerlendirmeler;
	}

	public void setKantitatifdegerlendirmeler(ArrayList<Kantitatifdegerlendirme> kantitatifdegerlendirmeler) {
		this.kantitatifdegerlendirmeler = kantitatifdegerlendirmeler;
	}

	public ArrayList<Degerlendirmeagaci> getDegerlendirmeagaci() {
		return degerlendirmeagaci;
	}

	public void setDegerlendirmeagaci(ArrayList<Degerlendirmeagaci> degerlendirmeagaci) {
		this.degerlendirmeagaci = degerlendirmeagaci;
	}




	public KantitatifTreeModel getKantitatifTreeModel() {
		return kantitatifTreeModel;
	}

	public void setKantitatifTreeModel(KantitatifTreeModel kantitatifTreeModel) {
		this.kantitatifTreeModel = kantitatifTreeModel;
	}

	public ArrayList<Kalitatifdegerlendirme> getKalitatifdegerlendirmeler() {
		return kalitatifdegerlendirmeler;
	}

	public void setKalitatifdegerlendirmeler(ArrayList<Kalitatifdegerlendirme> kalitatifdegerlendirmeler) {
		this.kalitatifdegerlendirmeler = kalitatifdegerlendirmeler;
	}

	public KalitatifTreeModel getKalitatifTreeModel() {
		return kalitatifTreeModel;
	}

	public void setKalitatifTreeModel(KalitatifTreeModel kalitatifTreeModel) {
		this.kalitatifTreeModel = kalitatifTreeModel;
	}

	public RatingViewData getRatingViewForm() {
		return ratingViewData;
	}

	public void setRatingViewForm(RatingViewData ratingViewData) {
		this.ratingViewData = ratingViewData;
	}


	public Locale getTrlocal() {
		return trlocal;
	}

	public void setTrlocal(Locale trlocal) {
		this.trlocal = trlocal;
	}
    public boolean  isUserLogedIn(){
    	if(generalData.getKullanici()==null || generalData.getFirma()==null){
    		return false;
    	}
    	return true;
    }

	public HashMap<String, KantitatifTableModel> getKantitatiftables() {
		return kantitatiftables;
	}

	public void setKantitatiftables(HashMap<String, KantitatifTableModel> kantitatiftables) {
		this.kantitatiftables = kantitatiftables;
	}

    public  BigDecimal getToplamPuan(){
      	 KalitatifTreeModel kalitatifTreeModel= getKalitatifTreeModel();
      	 BigDecimal kalitatifAgirlik=kalitatifTreeModel.getAgirlik();
      	 KantitatifTreeModel kantitatifTreeModel=getKantitatifTreeModel();
      	 BigDecimal  kanitatifAgirlik=kantitatifTreeModel.getAgirlik();
      	 
      	return   (kalitatifTreeModel.getTotalPuan().multiply(kalitatifAgirlik)).add((kantitatifTreeModel.getTotalPuan().multiply(kanitatifAgirlik))); 
      	 
      	 
       }
   	
//
//	public void setAutoAnswersComparationTotal() {
//		autoAnswersComparationTotal = getKalitatifTreeModelById(10, kalitatifTreeModel).getTotalPuan();
//		autoAnswersComparationTotal = autoAnswersComparationTotal.add(getKalitatifTreeModelById(13, kalitatifTreeModel).getTotalPuan());
//		autoAnswersComparationTotal = autoAnswersComparationTotal.add(getKalitatifTreeModelById(17, kalitatifTreeModel).getTotalPuan());
//		
//	}

	private String  getOtoCevapName(int idSoru){
		      for(Otomatikcevap otomatikcevap:otoCevaplar){
		    	  if(otomatikcevap.getId().getIdSoru()==idSoru){
		    		  return otomatikcevap.getCevap();
		    	  }
		      }
		      return null;
	}


}