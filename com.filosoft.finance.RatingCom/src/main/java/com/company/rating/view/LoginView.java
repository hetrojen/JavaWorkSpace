package com.company.rating.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.domain.Firma;
import com.company.rating.domain.Kullanici;
import com.company.rating.domain.Yetkilendirme;
import com.company.rating.service.RatingService;

@Component("loginView")
@Scope("request")
public class LoginView extends AbstractView implements Serializable {

	private RatingService ratingService;
	@Autowired
	private ApplicationData applicationData;
	@Autowired
	private GeneralData generalData;
	private Kullanici yetkili;

	private Kullanici kullanici = new Kullanici();

	private List<SelectItem> firmalar;

	public Kullanici getKullanici() {
		return kullanici;
	}

	

	public LoginView() {
		// TODO Auto-generated constructor stub

	}

	@Autowired
	public LoginView(RatingService ratingService) {
		// TODO Auto-generated constructor stub
		this.ratingService = ratingService;

	}

	public String login() {

		yetkili = ratingService.getKullanici(kullanici.getUsername());
		if (yetkili != null && yetkili.getSifre().equals(kullanici.getSifre())) {
			generalData.setKullanici(yetkili);
            loadFirmalar();
			String  firmaId= (String) firmalar.get(0).getValue();
			
			Firma fir = applicationData.getFirma(Integer.valueOf(firmaId));
			
			generalData.setFirma(fir);
			generalData.setFirmaId(fir.getIdFirma()+"");
			return "uploadPage";
		}else {
			putErrorMessage("Kullanıcı adı veya şifre hatalı");
			return "";
		}

		
		
		
		
	}

//	public String devam() {
//
//		Firma fir = applicationData.getFirma(Integer.valueOf(generalData.getFirmaId()));
//		generalData.setFirma(fir);
//
//		return "uploadPage";
//	}

	public List<SelectItem> getFirmalar() {

		return firmalar;
	}
	private void loadFirmalar(){
		if(kullanici==null || kullanici.getUsername()==null || kullanici.getUsername().trim().equals("")){
			return;
		}
		ArrayList<Yetkilendirme> yetkiler = (ArrayList<Yetkilendirme>) ratingService.getYetkiliFirmalar(kullanici.getUsername());
		firmalar = new ArrayList<SelectItem>();
		if (!kullanici.getUsername().trim().equals("")) {
			for (Yetkilendirme yetkilendirme : yetkiler) {
				if (yetkilendirme.getId().getUsername().equals(kullanici.getUsername())) {
					SelectItem item = new SelectItem();
					item.setValue(yetkilendirme.getId().getIdFirma() + "");
					item.setLabel(applicationData.getFirmaName(yetkilendirme.getId().getIdFirma()));
					firmalar.add(item);
				}
			}
		}
	}

	public void setFirmalar(List<SelectItem> firmalar) {
		this.firmalar = firmalar;
	}



}
