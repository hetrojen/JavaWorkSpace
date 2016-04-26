package com.company.rating.view;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.form.GrafikData;
import com.company.rating.view.model.DikeyAnalizGrafikModel;
import com.company.rating.view.model.FirmaDerecelendirme;
import com.company.rating.view.model.GrafikModel;
import com.company.rating.view.model.RasyoAnalizGrafikModel;
import com.company.rating.view.model.YatayAnalizGrafikModel;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component("raporGenerator")
@Scope("request")
public class RaporGeneratorView extends AbstractView {
	private StreamedContent file;
	private FileOutputStream f;
	private boolean visibleRaporLink;
	private String link;
	private String filePath;
	private String fileSep = "/";
	private FacesContext faceCon = FacesContext.getCurrentInstance();
	private ExternalContext externalContext = faceCon.getExternalContext();
	private List<String> selectedRapors = new ArrayList<String>();

	private BaseFont bf;
	private Font contentFont;
	private Font headerFont;
	private Font pageHeaderFont;
	@Autowired
	private GrafikData grafikData;
	@Autowired
	private GeneralData generalData;
	@Autowired
	private SesionBean sesionBean;

	private DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("tr_TR"));

	private NumberFormat formatter = new DecimalFormat("#,##0.00", symbols);

	// private NumberConverter numberConverter;
	public RaporGeneratorView() {
		// TODO Auto-generated constructor stub
		// numberConverter=new NumberConverter();
		// numberConverter.setMaxFractionDigits(3);
		// numberConverter.setType("number");
		// numberConverter.setLocale(new Locale("tr_TR"));
		// numberConverter.setPattern("####0.000");

		try {
			bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, "CP1254", BaseFont.EMBEDDED);
			contentFont = new Font(bf, 9, Font.NORMAL);
			headerFont = new Font(bf, 10, Font.BOLD);
			pageHeaderFont = new Font(bf, 13, Font.BOLD);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PostConstruct
	public void init() {

	}

	public List<String> getSelectedRapors() {
		return selectedRapors;
	}

	public void setSelectedRapors(List<String> selectedRapors) {
		this.selectedRapors = selectedRapors;
	}

	public String raporGenerate() {

		// itemValue="1" itemLabel="Değerlendirme Sonucu"
		// itemValue="2" itemLabel="Dikey Analiz"
		// itemValue="3" itemLabel="Yatay Analiz"
		// itemValue="4" itemLabel="Rasyo Analiz"

		if (selectedRapors.isEmpty()) {
			putErrorMessage("En az bir tane seçmelisiniz");
			return null;
		}

		Document document = new Document(PageSize.A4, 10, 10, 12, 12);

		String folderName = generalData.getFolderName();
		filePath = externalContext.getRealPath(fileSep + folderName + fileSep + "Rapor.pdf");
		link = fileSep + externalContext.getContextName() + fileSep + folderName + fileSep + "Rapor.pdf";

		try {

		} catch (Exception e) {
			// handle exception
		}
		try {
			f = new FileOutputStream(filePath);
			PdfWriter.getInstance(document, f);
			document.open();
			if (selectedRapors.contains("1")) {
				Paragraph p = new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " DEĞERLENDİRME SONUCU", pageHeaderFont));

				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);
				PdfPTable table = createDegerlendirmeSonucu();
				table.setSpacingBefore(20f);
				document.add(table);
				document.newPage();
			}
			if (selectedRapors.contains("2")) {
				Paragraph p = new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " DİKEY ANALİZ", pageHeaderFont));
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);

				PdfPTable table = cretaDikeyanaliz();
				table.setSpacingBefore(13f);
				document.add(table);
				document.newPage();
			}
			if (selectedRapors.contains("3")) {
				Paragraph p = new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " YATAY ANALİZ", pageHeaderFont));
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);
				PdfPTable table = createYatayAnaliz();
				table.setSpacingBefore(13f);
				document.add(table);
				document.newPage();
			}
			if (selectedRapors.contains("4")) {
				Paragraph p = new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " RASYO ANALİZ GRAFİKLER", pageHeaderFont));
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);

				ArrayList<PdfPTable> rasyoGrafiks = rasyoAnalizGrafikRapor();
				int i = 0;
				for (PdfPTable pTable : rasyoGrafiks) {
					if (i == 0) {
						pTable.setSpacingBefore(13f);
						i++;
					}
					document.add(pTable);
				}
				document.newPage();
			}
			if (selectedRapors.contains("5")) {
				Paragraph p = new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " RASYO ANALİZ RASYOLAR", pageHeaderFont));
				p.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p);
				ArrayList<PdfPTable> rasyoGrafiks = rasyoAnalizRasyoRapor();
				int i = 0;
				for (PdfPTable pTable : rasyoGrafiks) {
					if (i == 0) {
						pTable.setSpacingBefore(13f);
						i++;
					}
					document.add(pTable);
				}
			}

			visibleRaporLink = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		return null;
	}

	private PdfPTable createDegerlendirmeSonucu() {

		Font f = null;

		BigDecimal toplamPuan = sesionBean.getToplamPuan();
		FirmaDerecelendirme genelDereceInfo = FirmaDerecelendirme.getFirmaDerecelendirme(toplamPuan.intValue());

		int klp = sesionBean.getRatingViewForm().getKalitatifToplam().intValue();
		String kalitatifNot = FirmaDerecelendirme.getFirmaDerecelendirme(klp).getNot();

		int knp = sesionBean.getRatingViewForm().getKantitatifToplam().intValue();
		String kantiNot = FirmaDerecelendirme.getFirmaDerecelendirme(knp).getNot();

		String header = generalData.getFirma().getFirmaName() + " Derecelendirme Notu";

		String not = genelDereceInfo.getNot();
		if (not.contains("E") || not.contains("e")) {

			f = new Font(bf, 25, Font.BOLD, BaseColor.RED);
		}
		if (not.contains("D") || not.contains("d")) {
			Color color = new Color(0xffc000);

			f = new Font(bf, 25, Font.BOLD, new BaseColor(color));
		}
		if (not.contains("C") || not.contains("c")) {

			f = new Font(bf, 25, Font.BOLD, BaseColor.YELLOW);
		}
		if (not.contains("B") || not.contains("b")) {
			Color color = new Color(0x00ee6c);

			f = new Font(bf, 25, Font.BOLD, new BaseColor(color));
		}
		if (not.contains("A") || not.contains("a")) {
			Color color = new Color(0x00b050);

			f = new Font(bf, 25, Font.BOLD, new BaseColor(color));
		}

		PdfPTable wraper = new PdfPTable(2);
		PdfPCell headerCell = new PdfPCell(new Paragraph(new Chunk(header, headerFont)));
		headerCell.setColspan(2);
		wraper.addCell(headerCell);

		PdfPTable dereceT = new PdfPTable(1);

		dereceT.addCell(new Paragraph(new Chunk(not, f)));
		dereceT.addCell(new Paragraph(new Chunk("Kantitatif Derece:" + kantiNot, contentFont)));
		dereceT.addCell(new Paragraph(new Chunk("Kalitatif Derece:" + kalitatifNot, contentFont)));

		PdfPTable descriptionTa = new PdfPTable(1);

		descriptionTa.addCell(new Paragraph(new Chunk(genelDereceInfo.getDescriptionNot(), headerFont)));
		descriptionTa.addCell(new Paragraph(new Chunk(genelDereceInfo.getDescriptionFirma(), contentFont)));

		wraper.addCell(dereceT);
		wraper.addCell(descriptionTa);

		return wraper;
	}

	private PdfPTable cretaDikeyanaliz() {
		PdfPTable dikeyAnalizT = new PdfPTable(1);

		PdfPCell headerDikeyA = new PdfPCell(new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " Aktif Dağılımı", headerFont)));
		headerDikeyA.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		headerDikeyA.setBackgroundColor(BaseColor.GRAY);

		dikeyAnalizT.addCell(headerDikeyA);

		DikeyAnalizGrafikModel donen = grafikData.getDonenVarliklarGrafikD();
		DikeyAnalizGrafikModel duran = grafikData.getDuranVarliklarGrafikD();
		DikeyAnalizGrafikModel kisav = grafikData.getKisaVadeliYabanciGrafikD();
		DikeyAnalizGrafikModel uzunv = grafikData.getUzunVadeliYabanciGrafikD();
		DikeyAnalizGrafikModel ozkaynaklar = grafikData.getOzkanyaklarGrafikD();

		dikeyAnalizT.addCell(dikeyAnalizTableGen(donen));
		dikeyAnalizT.addCell(dikeyAnalizTableGen(duran));

		PdfPCell headerDikeyP = new PdfPCell(new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " Pasif Dağılımı", headerFont)));
		headerDikeyP.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		headerDikeyP.setBackgroundColor(BaseColor.GRAY);
		dikeyAnalizT.addCell(headerDikeyP);

		dikeyAnalizT.addCell(dikeyAnalizTableGen(kisav));
		dikeyAnalizT.addCell(dikeyAnalizTableGen(uzunv));
		dikeyAnalizT.addCell(dikeyAnalizTableGen(ozkaynaklar));

		return dikeyAnalizT;

	}

	private PdfPTable createYatayAnaliz() {

		PdfPTable yatayAnalizT = new PdfPTable(1);
		PdfPCell headerDikeyA = new PdfPCell(new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " Aktif Dağılımı", headerFont)));
		headerDikeyA.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		headerDikeyA.setBackgroundColor(BaseColor.GRAY);

		yatayAnalizT.addCell(headerDikeyA);

		ArrayList<YatayAnalizGrafikModel> donen = grafikData.getDonenVarliklarGrafik();
		ArrayList<YatayAnalizGrafikModel> duran = grafikData.getDuranVarliklarGrafik();
		ArrayList<YatayAnalizGrafikModel> kisav = grafikData.getKisaVadeliYabanciGrafik();
		ArrayList<YatayAnalizGrafikModel> uzunv = grafikData.getUzunVadeliYabanciGrafik();
		ArrayList<YatayAnalizGrafikModel> ozkaynaklar = grafikData.getOzkanyaklarGrafik();

		yatayAnalizT.addCell(yatayAnalizTableGen(donen));
		yatayAnalizT.addCell(yatayAnalizTableGen(duran));

		PdfPCell headerYatayP = new PdfPCell(new Paragraph(new Chunk(generalData.getFirma().getFirmaName() + " Pasif Dağılımı", headerFont)));
		headerYatayP.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		headerYatayP.setBackgroundColor(BaseColor.GRAY);
		yatayAnalizT.addCell(headerYatayP);

		yatayAnalizT.addCell(yatayAnalizTableGen(kisav));
		yatayAnalizT.addCell(yatayAnalizTableGen(uzunv));
		yatayAnalizT.addCell(yatayAnalizTableGen(ozkaynaklar));
		return yatayAnalizT;
	}

	private ArrayList<PdfPTable> rasyoAnalizGrafikRapor() {
		ArrayList<PdfPTable> tableList = new ArrayList<PdfPTable>();
		ArrayList<RasyoAnalizGrafikModel> borcOdemeGucu = grafikData.getBorcOdemeGucu();
		ArrayList<RasyoAnalizGrafikModel> faaliyet = grafikData.getFaaliyet();
		ArrayList<RasyoAnalizGrafikModel> finansalYapi = grafikData.getFinansalYapi();
		ArrayList<RasyoAnalizGrafikModel> kararlilik = grafikData.getKararlilik();
		String borcOdemeGucuGrafik = grafikData.getBorcOdemeGucuGrafikRapor();
		String faaliyetGrafik = grafikData.getFaaliyetGrafikRapor();
		String finansalYapiGrafik = grafikData.getFinansalYapiGrafikRapor();
		String kararlilikGrafik = grafikData.getKararlilikGrafikRapor();

		PdfPTable table = new PdfPTable(1);
		table.addCell(rasyoAnalizTableGen(borcOdemeGucu, " Borç Ödeme Gücü Oranı", borcOdemeGucuGrafik));

		tableList.add(table);

		table = new PdfPTable(1);

		table.addCell(rasyoAnalizTableGen(faaliyet, " Faaliyet Oranları", faaliyetGrafik));
		tableList.add(table);

		table = new PdfPTable(1);

		table.addCell(rasyoAnalizTableGen(finansalYapi, " Finansal Yapı Oranları", finansalYapiGrafik));
		tableList.add(table);

		table = new PdfPTable(1);
		//

		table.addCell(rasyoAnalizTableGen(kararlilik, "Karlılık Oranları", kararlilikGrafik));
		tableList.add(table);

		return tableList;
	}

	private ArrayList<PdfPTable> rasyoAnalizRasyoRapor() {
		ArrayList<PdfPTable> tableList = new ArrayList<PdfPTable>();
		ArrayList<RasyoAnalizGrafikModel> borcOdemeGucu = grafikData.getBorcOdemeGucu();
		ArrayList<RasyoAnalizGrafikModel> faaliyet = grafikData.getFaaliyet();
		ArrayList<RasyoAnalizGrafikModel> finansalYapi = grafikData.getFinansalYapi();
		ArrayList<RasyoAnalizGrafikModel> kararlilik = grafikData.getKararlilik();
		ArrayList<RasyoAnalizGrafikModel> gelirYaratma = grafikData.getGelirYaratma();

		PdfPTable table = new PdfPTable(1);
		table.addCell(rasyoAnalizTableGen(borcOdemeGucu, "BorçÖdeme Gücü Oranı"));

		tableList.add(table);

		table = new PdfPTable(1);
		table.addCell(rasyoAnalizTableGen(faaliyet, " Faaliyet Oranları"));
		tableList.add(table);

		table = new PdfPTable(1);
		table.addCell(rasyoAnalizTableGen(finansalYapi, " Finansal Yapı Oranları"));
		tableList.add(table);

		table = new PdfPTable(1);
		table.addCell(rasyoAnalizTableGen(gelirYaratma, "Gelir Yaratma Kapasitesi Oranları"));
		tableList.add(table);

		//
		table = new PdfPTable(1);
		table.addCell(rasyoAnalizTableGen(kararlilik, "Karlılık Oranları"));
		tableList.add(table);

		return tableList;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public boolean isVisibleRaporLink() {
		return visibleRaporLink;
	}

	public void setVisibleRaporLink(boolean visibleRaporLink) {
		this.visibleRaporLink = visibleRaporLink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private PdfPTable yatayAnalizTableGen(ArrayList<YatayAnalizGrafikModel> list) {

		float[] colsWidth = { 0.6f, 3.2f, 2.0f, 2.0f, 1.2f }; // Code
		PdfPTable yatay = new PdfPTable(colsWidth);

		PdfPCell yonH = new PdfPCell(new Paragraph(new Chunk("Yön", headerFont)));
		PdfPCell varlikH = new PdfPCell(new Paragraph(new Chunk("Varlık", headerFont)));
		PdfPCell oncakiH = new PdfPCell(new Paragraph(new Chunk("Önceki Dönem", headerFont)));
		PdfPCell cariH = new PdfPCell(new Paragraph(new Chunk("Cari Dönem", headerFont)));
		PdfPCell degisimH = new PdfPCell(new Paragraph(new Chunk("Değişim(%)", headerFont)));

		yonH.setHorizontalAlignment(Element.ALIGN_CENTER);
		yonH.setBackgroundColor(BaseColor.GRAY);
		varlikH.setHorizontalAlignment(Element.ALIGN_CENTER);
		varlikH.setBackgroundColor(BaseColor.GRAY);
		oncakiH.setHorizontalAlignment(Element.ALIGN_CENTER);
		oncakiH.setBackgroundColor(BaseColor.GRAY);
		cariH.setHorizontalAlignment(Element.ALIGN_CENTER);
		cariH.setBackgroundColor(BaseColor.GRAY);
		degisimH.setHorizontalAlignment(Element.ALIGN_CENTER);
		degisimH.setBackgroundColor(BaseColor.GRAY);

		yatay.addCell(yonH);
		yatay.addCell(varlikH);
		yatay.addCell(oncakiH);
		yatay.addCell(cariH);
		yatay.addCell(degisimH);

		for (YatayAnalizGrafikModel model : list) {

			Image yonG = null;

			try {

				yonG = Image.getInstance(externalContext.getRealPath(model.getArrowImgPath()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			PdfPCell varlikc = new PdfPCell(new Paragraph(new Chunk(model.getName().toString(), contentFont)));
			PdfPCell oncekic = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getOnceki()), contentFont)));
			PdfPCell caricc = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getCari()), contentFont)));
			PdfPCell degisimc = new PdfPCell(new Paragraph(new Chunk(model.getPercent(), contentFont)));
			yonG.scaleAbsolute(16, 16);
			PdfPCell yonc = new PdfPCell();
			yonc.addElement(yonG);
		

			yonc.setHorizontalAlignment(Element.ALIGN_CENTER);			
			varlikc.setHorizontalAlignment(Element.ALIGN_LEFT);
			oncekic.setHorizontalAlignment(Element.ALIGN_RIGHT);
			caricc.setHorizontalAlignment(Element.ALIGN_RIGHT);
			degisimc.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
			yatay.addCell(yonc);
			yatay.addCell(varlikc);
			yatay.addCell(oncekic);
			yatay.addCell(caricc);
			yatay.addCell(degisimc);
		}

		return yatay;
	}

	private PdfPTable dikeyAnalizTableGen(DikeyAnalizGrafikModel list) {

		float[] colsWidth = { 5.0f, 3.2f, 1.4f }; // Code
		float[] dikeycol = { 1.0f, 1.3f }; // Code
		PdfPTable wrap = new PdfPTable(dikeycol);
		PdfPTable dikey = new PdfPTable(colsWidth);

		PdfPCell donenH = new PdfPCell(new Paragraph(new Chunk("Varlık", headerFont)));
		PdfPCell cariH = new PdfPCell(new Paragraph(new Chunk("Cari Dönem", headerFont)));
		PdfPCell percentH = new PdfPCell(new Paragraph(new Chunk("%", headerFont)));
		donenH.setHorizontalAlignment(Element.ALIGN_CENTER);
		donenH.setBackgroundColor(BaseColor.GRAY);
		cariH.setHorizontalAlignment(Element.ALIGN_CENTER);
		cariH.setBackgroundColor(BaseColor.GRAY);
		percentH.setHorizontalAlignment(Element.ALIGN_CENTER);
		percentH.setBackgroundColor(BaseColor.GRAY);

		dikey.addCell(donenH);
		dikey.addCell(cariH);
		dikey.addCell(percentH);
		for (GrafikModel model : list.getGrafikModels()) {
			PdfPCell donenc = new PdfPCell(new Paragraph(new Chunk(model.getName(), contentFont)));
			PdfPCell caric = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getCari()), contentFont)));
			PdfPCell percentc = new PdfPCell(new Paragraph(new Chunk(model.getPercent(), contentFont)));
			donenc.setHorizontalAlignment(Element.ALIGN_LEFT);
			caric.setHorizontalAlignment(Element.ALIGN_RIGHT);

			percentc.setHorizontalAlignment(Element.ALIGN_RIGHT);

			dikey.addCell(donenc);
			dikey.addCell(caric);
			dikey.addCell(percentc);
		}
          dikey.addCell("");
          dikey.addCell("");
          dikey.addCell("");
		Image dikeyG = null;
		try {

			dikeyG = Image.getInstance(externalContext.getRealPath(list.getGrafikForRapor()));

		} catch (BadElementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		wrap.addCell(dikey);
		wrap.addCell(dikeyG);

		return wrap;
	}

	private PdfPTable rasyoAnalizTableGen(ArrayList<RasyoAnalizGrafikModel> list, String header) {

		float[] colsWidth = { 3.5f, 1f }; // Code

		PdfPTable rasyo = new PdfPTable(colsWidth);

		PdfPCell varlikH = new PdfPCell(new Paragraph(new Chunk(header, headerFont)));
		PdfPCell degerH = new PdfPCell(new Paragraph(new Chunk("Rasyo", headerFont)));

		varlikH.setHorizontalAlignment(Element.ALIGN_CENTER);
		varlikH.setBackgroundColor(BaseColor.GRAY);
		degerH.setHorizontalAlignment(Element.ALIGN_CENTER);
		degerH.setBackgroundColor(BaseColor.GRAY);

		rasyo.addCell(varlikH);
		rasyo.addCell(degerH);

		for (RasyoAnalizGrafikModel model : list) {
			PdfPCell varlik = new PdfPCell(new Paragraph(new Chunk(model.getKriter().getKriterName(), contentFont)));
			PdfPCell degerc = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getResult()), contentFont)));

			varlik.setHorizontalAlignment(Element.ALIGN_LEFT);
			degerc.setHorizontalAlignment(Element.ALIGN_RIGHT);

			rasyo.addCell(varlik);
			rasyo.addCell(degerc);

		}

		return rasyo;

	}

	private PdfPTable rasyoAnalizTableGen(ArrayList<RasyoAnalizGrafikModel> list, String header, String img) {

		float[] colsWidth = { 3.5f, 1f }; // Code
		float[] wrapcols = { 1f, 1.4f }; // Code
		PdfPTable wrap = new PdfPTable(wrapcols);
		PdfPTable rasyo = new PdfPTable(colsWidth);

		PdfPCell varlikH = new PdfPCell(new Paragraph(new Chunk(header, headerFont)));
		PdfPCell degerH = new PdfPCell(new Paragraph(new Chunk("Rasyo", headerFont)));

		varlikH.setHorizontalAlignment(Element.ALIGN_CENTER);
		varlikH.setBackgroundColor(BaseColor.GRAY);
		degerH.setHorizontalAlignment(Element.ALIGN_CENTER);
		degerH.setBackgroundColor(BaseColor.GRAY);

		rasyo.addCell(varlikH);
		rasyo.addCell(degerH);

		for (RasyoAnalizGrafikModel model : list) {
			PdfPCell varlik = new PdfPCell(new Paragraph(new Chunk(model.getKriter().getKriterName(), contentFont)));
			PdfPCell degerc = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getResult()), contentFont)));

			varlik.setHorizontalAlignment(Element.ALIGN_LEFT);
			degerc.setHorizontalAlignment(Element.ALIGN_RIGHT);

			rasyo.addCell(varlik);
			rasyo.addCell(degerc);

		}
		rasyo.addCell("");
		rasyo.addCell("");
		Image rasyoG = null;
		try {

			rasyoG = Image.getInstance(externalContext.getRealPath(img));

		} catch (BadElementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		rasyo.setExtendLastRow(false, false);
		rasyo.setComplete(true);
		PdfPCell cell = new PdfPCell(rasyo);
        
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		wrap.addCell(cell);
		wrap.addCell(rasyoG);

		return wrap;

	}
}
