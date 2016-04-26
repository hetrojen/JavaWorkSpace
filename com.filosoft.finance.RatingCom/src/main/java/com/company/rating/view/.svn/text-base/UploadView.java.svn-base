
package com.company.rating.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.rating.domain.Firmafile;
import com.company.rating.form.GelirBilancoViewData;
import com.company.rating.form.UploadViewData;
import com.company.rating.service.RatingService;
import com.company.rating.util.RasyoFileReader;
import com.company.rating.view.model.GelirBilancoExcelModel;
import com.company.rating.view.model.GelirbilancoModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class UploadView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Calendar cal = Calendar.getInstance();

	@Autowired
	private SesionBean sesionBean;
	@Autowired
	private RatingService ratingService;
	@Autowired
	private UploadViewData uploadViewData;
	@Autowired
	private GelirBilancoViewData bilancoViewData;
	@Autowired
	private GeneralData generalData;

	private String fileSep = System.getProperty("file.separator");
	private FacesContext faceCon = FacesContext.getCurrentInstance();
	private ExternalContext externalContext = faceCon.getExternalContext();
	private DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("tr_TR"));
	private NumberFormat formatter = new DecimalFormat("#,##0.00", symbols);
	private Panel secimPanel;
	private String secim = "1";
	private String uploadedFileName;

	private BaseFont bf;
	private Font contentFont;
	private Font contentFontB;
	private Font headerFont;
	private Font pageHeaderFont;

	private Firmafile rasyoFile;

	public UploadView() {
		// TODO Auto-generated constructor stub

		// createSecimPanel(cal.get(Calendar.YEAR), (month /3+1));
		try {
			bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, "CP1254", BaseFont.EMBEDDED);
			contentFont = new Font(bf, 8, Font.NORMAL);
			contentFontB = new Font(bf, 8, Font.BOLD);
			headerFont = new Font(bf, 8, Font.BOLD);
			pageHeaderFont = new Font(bf, 10, Font.BOLD);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SesionBean getSesionBean() {
		return sesionBean;
	}

	public void setSesionBean(SesionBean sesionBean) {
		this.sesionBean = sesionBean;
	}

	public List<SelectItem> getYears() {
		ArrayList<SelectItem> items = new ArrayList<SelectItem>();

		// int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		// int oldest= ratingService.getOldestYearFromKalitatifKayit();
		int oldest = 2005;
		int delta;
		if (year - oldest < 10 || oldest == -1) {
			delta = 10;
		} else {
			delta = year - oldest;
		}
		for (int i = year; delta >= 0; delta--) {
			SelectItem item = new SelectItem();
			item.setLabel(i + "");
			item.setValue(i--);
			items.add(item);
		}

		return items;
	}

	public Panel getSecimPanel() {

		return secimPanel;
	}

	public void setSecimPanel(Panel secimPanel) {
		this.secimPanel = secimPanel;
	}

	public String getSecim() {
		return secim;
	}

	public void setSecim(String secim) {
		this.secim = secim;
	}

	public String useUplodedFile() {
		try {
			bilancoViewData.setFromExistingKayit(false);
			bilancoViewData.setFromExcelUpload(true);
			RasyoFileReader fileReader = null;
			fileReader = new RasyoFileReader(uploadViewData.getFile().getInputstream());

			HashMap<String, GelirBilancoExcelModel> map = fileReader.read();
			String message = bilancoViewData.setGelirBilancoDataFromExcelFile(map);
			if (message != null) {

				uploadViewData.setCurrentPanel(UploadViewData.FILE_UPLOAD);
				putErrorMessage(message);
				return "";
			} else {
				uploadViewData.setCurrentPanel(UploadViewData.EXISTING_KAYIT);
				return "";
			}

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (e instanceof InvalidFormatException) {

				putErrorMessage("Yüklenen Excel'in dosya formatı hatalı (Excel versiyonu 2007 ve üzeri olmalı)");
			} else {
				putErrorMessage("Yüklenen Excel hatalı.");
			}
			uploadViewData.setCurrentPanel(UploadViewData.FILE_UPLOAD);

			return null;
		}

	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			bilancoViewData.setFromExistingKayit(false);
			bilancoViewData.setFromExcelUpload(true);
			uploadViewData.setFile(event.getFile());
			uploadedFileName = uploadViewData.getFile().getFileName();
			uploadViewData.setCurrentPanel(UploadViewData.SHOW_UPLOADED_FILE);
		} catch (Exception e) {

		}

	}

	public String deleteRecords() {
		// TODO Auto-generated method stub

		ratingService.deleteGelirBilancoAndKalitatifRecords(generalData.getYil(), generalData.getDonem(), generalData.getFirma().getIdFirma());
		rasyoFile = null;

		updatePanels();
		return "";
	}

	public String getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	public String dateChanged() {
		rasyoFile = null;
		updatePanels();
		return "";
	}

	public void updatePanels() {

		boolean gbkayitexist = ratingService.isGelirBilancoKayitExist(generalData.getYil(), generalData.getDonem(), generalData.getFirma().getIdFirma());

		if (!gbkayitexist) {

			uploadViewData.setCurrentPanel(UploadViewData.SELECT_DATA_CREATE_METHOD);
			bilancoViewData.setFromExistingKayit(false);

		} else {
			bilancoViewData.setFromExistingKayit(true);
			uploadViewData.setCurrentPanel(UploadViewData.EXISTING_KAYIT);

		}

		return;
	}

	public Firmafile getRasyoFile() {
		return rasyoFile;
	}

	public void setRasyoFile(Firmafile rasyoFile) {
		this.rasyoFile = rasyoFile;
	}

	public String uploadFile() {
		uploadedFileName = null;
		uploadViewData.setFile(null);
		uploadViewData.setCurrentPanel(UploadViewData.FILE_UPLOAD);
		return "";
	}

	public String startGelirBilancoEdit() {
       for(GelirbilancoModel model:bilancoViewData.getGelirBilancoAllVarlikList()){
    	   model.setOncekidonem(new BigDecimal(0));
    	   model.setCariDonem(new BigDecimal(0));
    	   model.setCariRevizyon(new BigDecimal(0));
    
       }
       bilancoViewData.setFromExistingKayit(false);
       bilancoViewData.setFromExcelUpload(false);
		return "gelirBil";
	}

	public String showBilancoGelir() {
		if (bilancoViewData.isFromExistingKayit()) {
			bilancoViewData.loadGelirBilancoDataFromDB(generalData.getDonem(), generalData.getYil(), generalData.getFirma().getIdFirma());
		}
		bilancoViewData.calculateGelirTableFormul();
		return "gelirBil";
	}

	public String showGelirBilanco() {

		Document document = new Document(PageSize.A4, 10, 10, 12, 12);

		String folderName = "F" + generalData.getFirmaId() + "F" + generalData.getDonem() + "D" + generalData.getYil() + "G" + generalData.getKullanici().getUsername();
		File folder = new File(externalContext.getRealPath(fileSep + folderName));
		folder.mkdir();

		String filePath = externalContext.getRealPath(fileSep + folderName + fileSep + "GelirBilanco.pdf");
		uploadViewData.setGelirbilancoLink(filePath);
		if (bilancoViewData.isFromExistingKayit()) {
			bilancoViewData.loadGelirBilancoDataFromDB(generalData.getDonem(), generalData.getYil(), generalData.getFirma().getIdFirma());
		}
		bilancoViewData.calculateGelirTableFormul();

		FileOutputStream f;
		try {
			f = new FileOutputStream(filePath);
			PdfWriter.getInstance(document, f);
			document.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<GelirbilancoModel> donenVarliklar = bilancoViewData.getDonenVarliklar();
		ArrayList<GelirbilancoModel> kisaVadeliYabanci = bilancoViewData.getKisaVadeliYabanci();
		ArrayList<GelirbilancoModel> duranVarliklar = bilancoViewData.getDuranVarliklar();
		ArrayList<GelirbilancoModel> ozkaynaklar = bilancoViewData.getOzkaynaklar();
		ArrayList<GelirbilancoModel> uzunVadeliYabancikaynaklar = bilancoViewData.getUzunVadeliYabancikaynaklar();
		ArrayList<GelirbilancoModel> gelirTablosu = bilancoViewData.getGelirTablosu();

		try {
			Paragraph p = (new Paragraph(new Chunk("AKTİF VARLIKLAR", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);

			p.setSpacingBefore(5f);
			p = (new Paragraph(new Chunk("Dönen Varliklar", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(p);

			PdfPTable table = getGelirBilancoTable(donenVarliklar);
			table.setSpacingBefore(8f);
			document.add(table);
			document.newPage();

			p = (new Paragraph(new Chunk("Duran Varliklar", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(p);

			table = getGelirBilancoTable(duranVarliklar);
			table.setSpacingBefore(2f);
			document.add(table);
			document.newPage();

			p = (new Paragraph(new Chunk("PASİF KAYNAKLAR", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);

			p.setSpacingBefore(5f);
			p = (new Paragraph(new Chunk("Kısa Vadeli Yabancı Kaynaklar", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(p);

			table = getGelirBilancoTable(kisaVadeliYabanci);
			table.setSpacingBefore(8f);
			document.add(table);
			document.newPage();

			p = (new Paragraph(new Chunk("Uzun Vadeli Yabancı Kaynaklar", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(p);

			table = getGelirBilancoTable(uzunVadeliYabancikaynaklar);
			table.setSpacingBefore(8f);
			document.add(table);
			document.newPage();

			p = (new Paragraph(new Chunk("Özkaynaklar", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(p);

			table = getGelirBilancoTable(ozkaynaklar);
			table.setSpacingBefore(8f);
			document.add(table);
			document.newPage();
			
		    p = (new Paragraph(new Chunk("GELİR TABLOSU", pageHeaderFont)));
			p.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p);
			table = getGelirBilancoTable(gelirTablosu);
			table.setSpacingBefore(8f);
			document.add(table);
			document.newPage();
			document.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private PdfPTable getGelirBilancoTable(ArrayList<GelirbilancoModel> list) {

		PdfPTable table = null;

		if (bilancoViewData.isFromExcelUpload() || bilancoViewData.isFromExistingKayit()) {
			float[] colsWidth = { 0.8f, 4.2f, 1.6f, 1.6f, 1.6f, 1.6f }; // Code
																		// 1
			table = new PdfPTable(colsWidth);

		} else {
			float[] colsWidth = { 1f, 4f, 2f, 2f }; // Code 1
			table = new PdfPTable(colsWidth);
		}

		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		PdfPCell codeH = new PdfPCell(new Paragraph(new Chunk(" ", headerFont)));
		PdfPCell varlikH = new PdfPCell(new Paragraph(new Chunk(" ", headerFont)));
		PdfPCell oncekiH = new PdfPCell(new Paragraph(new Chunk("Önceki Dönem", headerFont)));
		PdfPCell cariH = new PdfPCell(new Paragraph(new Chunk("Cari Dönem", headerFont)));
		PdfPCell revizyonH = new PdfPCell(new Paragraph(new Chunk("Cari Dönem(Revizyon)", headerFont)));
		PdfPCell revizeH = new PdfPCell(new Paragraph(new Chunk("Revizyon", headerFont)));

		oncekiH.setHorizontalAlignment(Element.ALIGN_CENTER);
		oncekiH.setBackgroundColor(BaseColor.GRAY);
		cariH.setHorizontalAlignment(Element.ALIGN_CENTER);
		cariH.setBackgroundColor(BaseColor.GRAY);
		revizyonH.setHorizontalAlignment(Element.ALIGN_CENTER);
		revizyonH.setBackgroundColor(BaseColor.GRAY);
		revizeH.setHorizontalAlignment(Element.ALIGN_CENTER);
		revizeH.setBackgroundColor(BaseColor.GRAY);

		table.addCell(codeH);
		table.addCell(varlikH);
		table.addCell(oncekiH);
		table.addCell(cariH);

		if (bilancoViewData.isFromExcelUpload() || bilancoViewData.isFromExistingKayit()) {
			table.addCell(revizyonH);
			table.addCell(revizeH);
		}

		for (GelirbilancoModel model : list) {
			Font contFont=null;
			if(model.getStyleClass().equalsIgnoreCase("outputLB")){
			 contFont=contentFontB;
			}else{
					contFont=contentFont;
			}
			
			PdfPCell codec = new PdfPCell(new Paragraph(new Chunk(model.getCodeText(), contentFont)));
			PdfPCell varlikc = new PdfPCell(new Paragraph(new Chunk(model.getGelirbilancocode().getName(), contFont)));
			PdfPCell oncekic = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getOncekidonem()), contentFont)));
			PdfPCell caric = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getCariDonem()), contentFont)));
			PdfPCell revizyonc = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getCariRevizyon()), contentFont)));
			PdfPCell revizec = new PdfPCell(new Paragraph(new Chunk(formatter.format(model.getRevizeCari()), contentFont)));
			codec.setHorizontalAlignment(Element.ALIGN_CENTER);
			varlikc.setHorizontalAlignment(Element.ALIGN_LEFT);
			oncekic.setHorizontalAlignment(Element.ALIGN_RIGHT);
			caric.setHorizontalAlignment(Element.ALIGN_RIGHT);
			revizyonc.setHorizontalAlignment(Element.ALIGN_RIGHT);
			revizec.setHorizontalAlignment(Element.ALIGN_RIGHT);
			// varlikc.setNoWrap(true);
			table.addCell(codec);
			table.addCell(varlikc);
			table.addCell(oncekic);
			table.addCell(caric);

			if (bilancoViewData.isFromExcelUpload() || bilancoViewData.isFromExistingKayit()) {
				table.addCell(revizyonc);
				table.addCell(revizec);
			}

		}
		return table;

	}

}
