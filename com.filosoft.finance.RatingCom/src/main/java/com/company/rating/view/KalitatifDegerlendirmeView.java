package com.company.rating.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.convert.NumberConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.primefaces.component.panel.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.domain.Cevap;
import com.company.rating.domain.Firmatipi;
import com.company.rating.domain.KalitatifdegerlendirmeKayit;
import com.company.rating.domain.KalitatifdegerlendirmeKayitPK;
import com.company.rating.domain.Kullanici;
import com.company.rating.form.GrafikData;
import com.company.rating.service.RatingService;
import com.company.rating.view.model.KalitatifTableModel;
import com.company.rating.view.model.KalitatifTreeModel;

@Component("kalitatifDegerlendirme")
@Scope("request")
public class KalitatifDegerlendirmeView extends AbstractView implements ActionListener {
	@Autowired
	private SesionBean sesionBean;
	@Autowired
	private ApplicationData applicationData;
	@Autowired
	private GeneralData generalData;
	@Autowired
	private GrafikData grafikData;
	@Autowired
	private RatingService ratingService;
	private int panelCount = 0, degerlendirmeHcout = 0;
	private final static int FALIYETLERIN_DEGERLENDIRILMESI_ID = 24;
	private final static int PROJELERIN_DEGERLENDIRILMESI_ID = 23;
	private NumberConverter numberConverter;
	private boolean projeTeklifKontrol = true;
	private HashMap<String, String> aratoplamlar = new HashMap<String, String>();

	public KalitatifDegerlendirmeView() {
		numberConverter = new NumberConverter();
		numberConverter.setMaxFractionDigits(3);
		numberConverter.setType("number");
		numberConverter.setLocale(new Locale("tr_TR"));
		numberConverter.setPattern("###0.00");
		
	}

	public Panel getKalitatifPanel() {
		Panel p = (Panel) getApplication().createComponent(Panel.COMPONENT_TYPE);
		p.setId("kalitatifPA");

		HtmlPanelGrid layout = (HtmlPanelGrid) getApplication().createComponent(HtmlPanelGrid.COMPONENT_TYPE);
		layout.setColumns(1);
		createKalitatifPanel(sesionBean.getKalitatifTreeModel(), layout);
		p.getChildren().add(layout);
		return p;
	}
	
	public void setKalitatifPanel(Panel p){
		
	}

	private void createKalitatifPanel(KalitatifTreeModel treeModel, HtmlPanelGrid root) {
//		if ((treeModel.getChilds() == null || treeModel.getChilds().isEmpty()) && (treeModel.getDegerlendirmeler() == null || treeModel.getDegerlendirmeler().isEmpty())) {
//			writeFile("degerlendirmeID..:"+treeModel.getDegerlendirmeId()+"--sektorId...:"+generalData.getFirma().getIdSektor()+"firmTipi..:"+generalData.getFirma().getIdFirmaTip());
//			return;
//		}

		HtmlOutputText degerlendirme = new HtmlOutputText();

		degerlendirme.setId("dhkanti" + (++degerlendirmeHcout));
		String lb = treeModel.getName() + "      ToplamPuan:" + treeModel.getTotalPuan().toPlainString() + "  Ağırlık:" + treeModel.getAgirlik().toPlainString();
		aratoplamlar.put(treeModel.toStringParentIds(), lb);
		if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {

			degerlendirme.setValueExpression("value", createValueEx("#{kalitatifDegerlendirme.aratoplamlar['" + treeModel.toStringParentIds() + "']}", String.class));
		} else {
			degerlendirme.setValue(treeModel.getName());
		}

		if (generalData.getFirma().getIdFirmaTip() == Firmatipi.TAAHUT_FIRMATIPI_ID) {
			if (treeModel.getDegerlendirmeId() == PROJELERIN_DEGERLENDIRILMESI_ID) {
				degerlendirme.setValueExpression("rendered", createValueEx("#{kalitatifDegerlendirme.projeTeklifKontrol}", boolean.class));

			}
		}
		degerlendirme.setStyleClass("header");

		root.getChildren().add(degerlendirme);
		if (generalData.getFirma().getIdFirmaTip() == Firmatipi.TAAHUT_FIRMATIPI_ID) {
			if (treeModel.getDegerlendirmeId() == FALIYETLERIN_DEGERLENDIRILMESI_ID) {
				HtmlPanelGrid grid = new HtmlPanelGrid();

				HtmlSelectBooleanCheckbox boolean1 = new HtmlSelectBooleanCheckbox();

				boolean1.setOnclick("jsf.ajax.request(this, event, {execute: this.id, render: 'degerlendirme:kalitatifP'}); return false;");
				boolean1.setId("asdaasdsad");
				boolean1.setValueExpression("value", createValueEx("#{kalitatifDegerlendirme.projeTeklifKontrol}", boolean.class));
				HtmlOutputLabel htmlOutputLabel = new HtmlOutputLabel();
				htmlOutputLabel.setValue("Teklif Projeye Yönelikse tıklayınız");
				grid.setColumns(2);
				grid.getChildren().add(boolean1);
				grid.getChildren().add(htmlOutputLabel);
				root.getChildren().add(grid);
			}
		}
		if (treeModel.getChilds() != null && !treeModel.getChilds().isEmpty()) {
			for (KalitatifTreeModel model : treeModel.getChilds()) {

				createKalitatifPanel(model, root);

			}
		} else if (treeModel.getDegerlendirmeler() != null && !treeModel.getDegerlendirmeler().isEmpty()) {
			getKalitatifTable(treeModel.getDegerlendirmeler(), treeModel.getDegerlendirmeId(), root);
		}

		return;
	}

	private void getKalitatifTable(ArrayList<KalitatifTableModel> list, int rootdegerlendirmeId, HtmlPanelGrid pan) {
		if (list == null || list.isEmpty()) {
			return;
		}

		String panelId = "pnG" + list.get(0).getId().getIdDerlendirme() + "pc" + (++panelCount);
		HtmlPanelGrid dataTable = (HtmlPanelGrid) getApplication().createComponent(HtmlPanelGrid.COMPONENT_TYPE);
		dataTable.setStyleClass("degerlendirmePanel");
		dataTable.setId(panelId);
		dataTable.setStyle("border: 1px solid #aaaaaa;");
		if (generalData.getFirma().getIdFirmaTip() == Firmatipi.TAAHUT_FIRMATIPI_ID) {
			if (rootdegerlendirmeId == PROJELERIN_DEGERLENDIRILMESI_ID) {
				dataTable.setValueExpression("rendered", createValueEx("#{kalitatifDegerlendirme.projeTeklifKontrol}", boolean.class));

			}
		}

		dataTable.setColumns(2);
		if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {
			dataTable.setColumns(4);
		}

		HtmlOutputLabel soruHeader = new HtmlOutputLabel();
		soruHeader.setId(panelId + "soruh");
		soruHeader.setValue("Soru");
		soruHeader.setStyleClass("header");
		dataTable.getChildren().add(soruHeader);
		HtmlOutputLabel cevapHeader = new HtmlOutputLabel();
		cevapHeader.setStyleClass("outputL");
		cevapHeader.setId(panelId + "cevapHeader");
		cevapHeader.setValue("Cevap");
		cevapHeader.setStyleClass("header");

		dataTable.getChildren().add(cevapHeader);
		if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {
			HtmlOutputLabel agirlikHeader = new HtmlOutputLabel();
			agirlikHeader.setStyleClass("outputL");
			agirlikHeader.setId(panelId + "agirlikH");
			agirlikHeader.setValue("Ağırlık");
			agirlikHeader.setStyleClass("header");

			HtmlOutputLabel puanHeader = new HtmlOutputLabel();
			puanHeader.setStyle("outputL");
			puanHeader.setId(panelId + "puanH");
			puanHeader.setValue("Puan");
			puanHeader.setStyleClass("header");
			dataTable.getChildren().add(agirlikHeader);
			dataTable.getChildren().add(puanHeader);
		}

		for (KalitatifTableModel model : list) {
			String kokId = "s" + model.getId().getIdSoru() + panelId;
			String bind = "#{sesionBean.kalitatiftables['" + model.getId().getIdParentDegerlendirme() + "$" + model.getId().getIdDerlendirme() + "$" + model.getId().getIdSoru() + "']";
			HtmlOutputText kriterText = new HtmlOutputText();
			kriterText.setStyleClass("outputL");
			HtmlPanelGrid sorp = new HtmlPanelGrid();
			sorp.setStyle("width:810px;");
			sorp.setBorder(0);
			sorp.setStyleClass("kriterPanel");

			kriterText.setId(kokId + "kriterT");
			dataTable.getChildren().add(sorp);
			kriterText.setValueExpression("value", createValueEx(bind + ".soruName}", String.class));
			sorp.getChildren().add(kriterText);

			HtmlSelectOneMenu htmlSelectOneMenu = (HtmlSelectOneMenu) getApplication().createComponent(HtmlSelectOneMenu.COMPONENT_TYPE);
			htmlSelectOneMenu.setId(kokId + "sob");
			if (rootdegerlendirmeId == 20 && generalData.getFirma().getIdFirmaTip() == Firmatipi.FAK_LEAS_ID) {
				htmlSelectOneMenu.setDisabled(true);
			}

			htmlSelectOneMenu.setValueExpression("value", createValueEx(bind + ".selectedCevapValue}", String.class));

			if (rootdegerlendirmeId == 20 && generalData.getFirma().getIdFirmaTip() == Firmatipi.FAK_LEAS_ID) {
				htmlSelectOneMenu.setDisabled(true);
				UISelectItem none = new UISelectItem();
				none.setItemLabel("-");
				none.setItemValue("-1");
				htmlSelectOneMenu.getChildren().add(none);
			}
			if ((generalData.getFirma().getIdFirmaTip() == Firmatipi.URETIM_FIRMATIP_ID || generalData.getFirma().getIdFirmaTip() == Firmatipi.HIZMET_FIRMATIP_ID)
					&& (rootdegerlendirmeId == 20 || rootdegerlendirmeId == 19)) {
				htmlSelectOneMenu.setDisabled(true);
				UISelectItem none = new UISelectItem();
				none.setItemLabel("-");
				none.setItemValue("-1");
				htmlSelectOneMenu.getChildren().add(none);
			}

			for (Cevap cvp : applicationData.getCevaplarByGroup(model.getIdCevapGrup())) {
				UISelectItem item = new UISelectItem();
				item.setItemValue(cvp.getCevapDeger().intValue() + "");
				item.setItemLabel(cvp.getId().getCevapName());
				htmlSelectOneMenu.getChildren().add(item);
			}
			dataTable.getChildren().add(htmlSelectOneMenu);
			if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {
				HtmlInputText agitlikText = new HtmlInputText();
				agitlikText.setId(kokId + "agirlikT");
				agitlikText.setStyle("text-align:right");
				// agitlikText.setConverter(numberConverter);
				agitlikText.setDisabled(true);
				agitlikText.setSize(10);
				agitlikText.setValue(model.getAgirlikText());

				HtmlInputText puanText = new HtmlInputText();
				puanText.setDisabled(true);
				puanText.setId(kokId + "puanT");
				puanText.setStyle("text-align:right");
				puanText.setSize(10);
				puanText.setConverter(numberConverter);
				puanText.setValueExpression("value", createValueEx(bind + ".puan}", BigDecimal.class));
				dataTable.getChildren().add(agitlikText);
				dataTable.getChildren().add(puanText);
			}

		}

		// dataTable.setBorder(1);
		pan.getChildren().add(dataTable);
		return;
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub

	}

	public String hesaplaKalitatif() {
		sesionBean.calculateRatings(sesionBean.getKalitatifTreeModel());
		if (generalData.getFirma().getIdFirmaTip() != Firmatipi.TAAHUT_FIRMATIPI_ID) {
			// sesionBean.setAutoAnswersComparationTotal();
			sesionBean.setAutoAnswer(sesionBean.getKalitatifTreeModel());
		}
		sesionBean.calculateRatings(sesionBean.getKalitatifTreeModel());
		sesionBean.getRatingViewForm().setKalitatifToplam(sesionBean.getKalitatifTreeModel().getTotalPuan());
		grafikData.initiateRasyoAnaliz();
		updatesAraToplamlar(sesionBean.getKalitatifTreeModel());
		return "";
	}
	
	private void updatesAraToplamlar(KalitatifTreeModel treeModel) {

		String aratoplam = aratoplamlar.get(treeModel.toStringParentIds());
		if (aratoplam != null) {

			String lb = treeModel.getName() + "      ToplamPuan:" + treeModel.getTotalPuan().toPlainString() + "  Ağırlık:" + treeModel.getAgirlik().toPlainString();
			aratoplamlar.put(treeModel.toStringParentIds(), lb);
		}
		if (treeModel.getChilds() != null) {
			for (KalitatifTreeModel model : treeModel.getChilds()) {
			
					updatesAraToplamlar(model);
			

			}

		}
	}
	public boolean isProjeTeklifKontrol() {
		return projeTeklifKontrol;
	}

	public void setProjeTeklifKontrol(boolean projeTeklifKontrol) {
		this.projeTeklifKontrol = projeTeklifKontrol;
	}

	public String temizleKalitatif() {

		sesionBean.getKalitatifTreeModel().setTotalPuan(new BigDecimal(0));
		sesionBean.getRatingViewForm().setKalitatifToplam(new BigDecimal(0));
		temizleKalitatif(sesionBean.getKalitatifTreeModel());
		if (generalData.getFirma().getIdFirmaTip() != Firmatipi.TAAHUT_FIRMATIPI_ID) {
			// sesionBean.setAutoAnswersComparationTotal();
			sesionBean.setAutoAnswer(sesionBean.getKalitatifTreeModel());
		}
		updatesAraToplamlar(sesionBean.getKalitatifTreeModel());
		return "";
	}

	private void temizleKalitatif(KalitatifTreeModel treeModel) {
		treeModel.setTotalPuan(new BigDecimal(0));
		if (treeModel.getDegerlendirmeler() != null) {
			for (KalitatifTableModel model : treeModel.getDegerlendirmeler()) {

				BigDecimal cvpVal = applicationData.getCevapValueByName("evet", model.getIdCevapGrup());
				model.setSelectedCevapValue(cvpVal.intValue() + "");
				model.setPuan(new BigDecimal(0));
			}
		} else if (treeModel.getChilds() != null) {
			for (KalitatifTreeModel kalitatifTreeModel : treeModel.getChilds()) {
				temizleKalitatif(kalitatifTreeModel);
			}
		}

	}

	public HashMap<String, String> getAratoplamlar() {
		return aratoplamlar;
	}

	public void setAratoplamlar(HashMap<String, String> aratoplamlar) {
		this.aratoplamlar = aratoplamlar;
	}
//	public void writeFile(String log) {
//	
//		
//	
//		try {
//			BufferedWriter out = new BufferedWriter(new FileWriter(
//					"d://degerlendirmeagaci.txt", true));
//			out.newLine();
//			out.write(log);
//			out.close();
//		} catch (IOException e) {
//			System.out.println("Exception ");
//
//		}
//	}
	public String kaydet() {
		try{
			ratingService.deleteKalitatifRecords(generalData.getYil(),generalData.getDonem(), generalData.getFirma().getIdFirma());
		saveDegerlendirme(sesionBean.getKalitatifTreeModel());
		}catch (Exception e) {
			// TODO: handle exception
			putErrorMessage("Kaydetme işlemi sırasında sorun oluştu");
			
			 return "";
		}
		 putInfoMessage(generalData.getYil()+" yılı "+generalData.getDonem()+".dönemi için hesaplamalar kaydedilmiştir.");
		return "";
	}
	private void saveDegerlendirme(KalitatifTreeModel kalitatifTreeModel) {
		
		ArrayList<KalitatifdegerlendirmeKayit> kayitlist = new ArrayList<KalitatifdegerlendirmeKayit>();
		if (kalitatifTreeModel.getDegerlendirmeler() != null) {
			for (KalitatifTableModel model : kalitatifTreeModel
					.getDegerlendirmeler()) {
				KalitatifdegerlendirmeKayit kalitatifdegerlendirmeKayit = new KalitatifdegerlendirmeKayit();
				KalitatifdegerlendirmeKayitPK kayitPK=new KalitatifdegerlendirmeKayitPK();
				kalitatifdegerlendirmeKayit.setId(kayitPK);
				int donem=generalData.getDonem(); int yil=generalData.getYil();
				kalitatifdegerlendirmeKayit.getId().setDonem(donem);
				kalitatifdegerlendirmeKayit.getId().setIdDerlendirme(kalitatifTreeModel.getDegerlendirmeId());
				kalitatifdegerlendirmeKayit.getId().setIdFirma(	generalData.getFirma().getIdFirma());
				kalitatifdegerlendirmeKayit.getId().setIdSoru(model.getId().getIdSoru());
				kalitatifdegerlendirmeKayit.getId().setSecim(model.getId().getSecim());
				kalitatifdegerlendirmeKayit.getId().setUsername(generalData.getKullanici().getUsername());
				kalitatifdegerlendirmeKayit.getId().setYil(yil);
				kalitatifdegerlendirmeKayit.setIdCevapGrup(model.getIdCevapGrup());
				kalitatifdegerlendirmeKayit.setCevapDeger(new BigDecimal(model.getSelectedCevapValue()));
				kayitlist.add(kalitatifdegerlendirmeKayit);
				//kalitatifdegerlendirmeKayit.setCevapDeger(new BigDecimal(model.getSelectedCevapValue()));
			}
		}if(kalitatifTreeModel.getChilds()!=null){
			for(KalitatifTreeModel treeModel:kalitatifTreeModel.getChilds()){
				saveDegerlendirme(treeModel);
			}
		}
		
		
		 if(!kayitlist.isEmpty()){
		ratingService.saveAllKalitatif(kayitlist);
		 }
	
	}
}
