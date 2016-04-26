package com.company.rating.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.convert.NumberConverter;

import org.primefaces.component.panel.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.domain.Kullanici;
import com.company.rating.view.model.KantitatifTableModel;
import com.company.rating.view.model.KantitatifTreeModel;

@Component("kantitatifDegerlendirme")
@Scope("request")
public class KantitatifDegerlendirmeView extends AbstractView {

	@Autowired
	private SesionBean sesionBean;
	@Autowired
	private GeneralData generalData;
	private int degerlendirmeHcout = 0;
	private int panelCount = 0;
	private int idNoktaG = 0, idTrendG = 0;
	private NumberConverter numberConverter;
	private NumberConverter rasyoConverter;

	public KantitatifDegerlendirmeView() {
		// TODO Auto-generated constructor stub
		numberConverter = new NumberConverter();
		numberConverter.setMaxFractionDigits(3);
		numberConverter.setType("number");
		numberConverter.setLocale(new Locale("tr_TR"));
		numberConverter.setPattern("####0.000");

		rasyoConverter = new NumberConverter();
		rasyoConverter.setMaxFractionDigits(2);
		rasyoConverter.setType("number");
		rasyoConverter.setLocale(new Locale("tr_TR"));
		rasyoConverter.setPattern("###0.00");
	}

	public Panel getKantitatifPanel() {
		Panel p = (Panel) getApplication().createComponent(Panel.COMPONENT_TYPE);

		p.setId("kantitatifPA");
		HtmlPanelGrid layout = (HtmlPanelGrid) getApplication().createComponent(HtmlPanelGrid.COMPONENT_TYPE);
		layout.setColumns(1);
		createKantitatifPanel(sesionBean.getKantitatifTreeModel(), layout);
		p.getChildren().add(layout);
		return p;
	}
	
	public void setKantitatifPanel(Panel p){
		
	}
	

	private void createKantitatifPanel(KantitatifTreeModel treeModel, HtmlPanelGrid root) {

		HtmlOutputText degerlendirme = new HtmlOutputText();
		degerlendirme.setStyleClass("header");
		degerlendirme.setId("dhkanti" + (++degerlendirmeHcout));
		String detailLab = treeModel.getName() + "      Toplam Puan:" + treeModel.getTotalPuan().toPlainString() + "  Ağırlık:" + treeModel.getAgirlik().toPlainString();
		if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {
			degerlendirme.setValue(detailLab);
		} else {
			degerlendirme.setValue(treeModel.getName());
		}

		root.getChildren().add(degerlendirme);
		if (treeModel.getChilds() != null) {
			for (KantitatifTreeModel model : treeModel.getChilds()) {
				createKantitatifPanel(model, root);
			}
		} else if (treeModel.getDegerlendirmeler() != null) {
			boolean isTr = (treeModel.getParents().indexOf(KantitatifTreeModel.TREND_ANALIZ_DEGERLENDIRMEID) != -1)
					|| treeModel.getDegerlendirmeId() == KantitatifTreeModel.TREND_ANALIZ_DEGERLENDIRMEID;
			getKantitatifTable(treeModel.getDegerlendirmeler(), isTr, root);
		}

		return;
	}

	private void getKantitatifTable(ArrayList<KantitatifTableModel> list, boolean trend, HtmlPanelGrid pan) {
		if (list == null || list.isEmpty()) {
			return;
		}

		String panelId = "tb" + list.get(0).getId().getIdDerlendirme() + "pn" + (++panelCount);
		HtmlPanelGrid dataTable = (HtmlPanelGrid) getApplication().createComponent(HtmlPanelGrid.COMPONENT_TYPE);
		dataTable.setId(panelId);

		dataTable.setColumns(3);
		if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {
			dataTable.setColumns((dataTable.getColumns() + 2));
		}

		HtmlOutputLabel kriterHeader = new HtmlOutputLabel();
		kriterHeader.setId(panelId + "kriterH");
		kriterHeader.setValue("Kriter");
		kriterHeader.setStyleClass("header");
		kriterHeader.setStyleClass("header");

		HtmlOutputLabel ilkyHeader = new HtmlOutputLabel();
		ilkyHeader.setId(panelId + "ilkyHeader");
		ilkyHeader.setValue("Önceki Yıl");
		ilkyHeader.setStyleClass("header");

		HtmlOutputLabel buyHeader = new HtmlOutputLabel();
		buyHeader.setId(panelId + "buyHeader");
		buyHeader.setValue("Bu yıl");
		buyHeader.setStyleClass("header");
		dataTable.getChildren().add(kriterHeader);
		dataTable.getChildren().add(ilkyHeader);
		dataTable.getChildren().add(buyHeader);
		if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {
			HtmlOutputLabel agirlikHeader = new HtmlOutputLabel();
			agirlikHeader.setId(panelId + "agirlikH");
			agirlikHeader.setValue("Ağırlık");
			agirlikHeader.setStyleClass("header");

			HtmlOutputLabel puanHeader = new HtmlOutputLabel();
			puanHeader.setId(panelId + "puanHeader");
			puanHeader.setValue("Puan");
			puanHeader.setStyleClass("header");
			dataTable.getChildren().add(agirlikHeader);
			dataTable.getChildren().add(puanHeader);
		}

		for (KantitatifTableModel model : list) {
			String kokId = "k" + model.getId().getIdKriter() + panelId;

			HtmlPanelGrid sorp = new HtmlPanelGrid();
			sorp.setStyle("width: 400px;");
			HtmlOutputText kriterText = new HtmlOutputText();
			kriterText.setStyleClass("outputL");
			kriterText.setId(kokId + "kriterT");
			kriterText.setValue(model.getKriterName());
			sorp.getChildren().add(kriterText);
			sorp.setBorder(0);
			sorp.setStyleClass("kriterPanel");
			dataTable.getChildren().add(sorp);

			HtmlInputText oncekiYilInput = new HtmlInputText();
			oncekiYilInput.setId(kokId + "oncekiiylT");
			oncekiYilInput.setStyle("text-align:right");
			oncekiYilInput.setSize(10);
			oncekiYilInput.setConverter(rasyoConverter);
			oncekiYilInput.setDisabled(true);
			oncekiYilInput.setValue(model.getRasyoOncekiyil());
			dataTable.getChildren().add(oncekiYilInput);

			HtmlPanelGrid wrap = new HtmlPanelGrid();
			wrap.setColumns(2);
			wrap.setStyleClass("kriterPanel");
			HtmlInputText buYilInput = new HtmlInputText();
			buYilInput.setId(kokId + "buYilInput");
			buYilInput.setStyle("text-align:right");
			buYilInput.setSize(10);
			buYilInput.setDisabled(true);
			buYilInput.setConverter(rasyoConverter);
			buYilInput.setValue(model.getRasyoBuyil());

			wrap.getChildren().add(buYilInput);

			if (trend) {

				wrap.getChildren().add(getTrendAnaliziGrafik(model));
			} else {// nokta analizi
				wrap.getChildren().add(getNoktaAnaliziGrafik(model));
			}
			dataTable.getChildren().add(wrap);
			if (generalData.getKullanici().getRole() == Kullanici.ADMIN_ROLE) {
				HtmlInputText agitlikText = new HtmlInputText();
				agitlikText.setId(kokId + "agirlikT");
				agitlikText.setStyle("text-align:right");
				agitlikText.setSize(10);
				agitlikText.setValue(model.getAgirlikText());
				agitlikText.setDisabled(true);
				dataTable.getChildren().add(agitlikText);

				HtmlInputText puanText = new HtmlInputText();
				puanText.setId(kokId + "puanT");
				puanText.setStyle("text-align:right");
				puanText.setSize(10);
				puanText.setConverter(numberConverter);
				puanText.setDisabled(true);
				puanText.setValue(model.getPuan());
				dataTable.getChildren().add(puanText);

			}

		}

		dataTable.setStyleClass("degerlendirmePanel");
		pan.getChildren().add(dataTable);
		return;

	}

	private HtmlPanelGroup getTrendAnaliziGrafik(KantitatifTableModel model) {

		HtmlPanelGroup pn = new HtmlPanelGroup();
		pn.setLayout("block");

		BigDecimal zeropone = new BigDecimal(0.1000);
		zeropone = zeropone.setScale(2, BigDecimal.ROUND_HALF_EVEN);

		pn.setId("trendG" + idTrendG);
		HtmlInputHidden color = new HtmlInputHidden();
		color.setId("c" + idTrendG);

		idTrendG++;
		BigDecimal fark = model.getSon().subtract(model.getIlk());
		if ((fark.compareTo(zeropone) == 0) || (fark.compareTo(new BigDecimal(0)) == 0)) {
			color.setValue("yellow");
		} else if (fark.compareTo(zeropone) > 0) {
			color.setValue("green");
		} else if (fark.compareTo(new BigDecimal(0)) < 0) {
			color.setValue("red");
		} else {
			System.out.println(fark);
		}
		pn.getChildren().add(color);
		pn.setStyleClass("kriterPanel");
		pn.setValueExpression("rendered", createValueEx("#{sesionBean.ratingViewForm.kantitatifHesButDis}", boolean.class));

		return pn;
	}

	private HtmlPanelGrid getNoktaAnaliziGrafik(KantitatifTableModel model) {
		HtmlPanelGrid grid = new HtmlPanelGrid();

		grid.setId("g" + idNoktaG);
		grid.setColumns(3);
		grid.setBorder(0);
		grid.setStyleClass("kriterPanel");

		HtmlPanelGroup p = new HtmlPanelGroup();
		p.setLayout("block");
		p.setId("noktaG" + idNoktaG);

		grid.getChildren().add(p);
		BigDecimal ten = new BigDecimal(10);
		int ilkYil = (model.getPuanByKriterOnceki().multiply(ten)).intValue();
		int buYil = (model.getPuanByKriter().multiply(ten)).intValue();
		HtmlInputHidden onceki = new HtmlInputHidden();
		onceki.setId("o" + idNoktaG);
		HtmlInputHidden bu = new HtmlInputHidden();
		bu.setId("b" + idNoktaG);
		onceki.setValue(ilkYil);
		bu.setValue(buYil);
		grid.getChildren().add(onceki);
		grid.getChildren().add(bu);
		idNoktaG++;

		grid.setValueExpression("rendered", createValueEx("#{sesionBean.ratingViewForm.kantitatifHesButDis}", boolean.class));
		return grid;
	}

	public int getIdNoktaG() {

		return idNoktaG;
	}

	public int getIdTrendG() {
		return idTrendG;
	}

	public void setIdNoktaG(int idNoktaG) {
		this.idNoktaG = idNoktaG;
	}

	public void setIdTrendG(int idTrendG) {
		this.idTrendG = idTrendG;
	}

	public String hesaplaKantitatif() {
		sesionBean.calculateRatings(sesionBean.getKantitatifTreeModel());
		sesionBean.getRatingViewForm().setKantitatifToplam(sesionBean.getKantitatifTreeModel().getTotalPuan());
		sesionBean.getRatingViewForm().setKantitatifHesButDis(true);
		return "";
	}
}
