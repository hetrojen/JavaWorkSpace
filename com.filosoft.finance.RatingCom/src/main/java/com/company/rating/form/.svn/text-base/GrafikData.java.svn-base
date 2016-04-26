package com.company.rating.form;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;

import com.company.rating.domain.Kriter;
import com.company.rating.util.RatingFormulaParser;
import com.company.rating.view.ApplicationData;
import com.company.rating.view.GeneralData;
import com.company.rating.view.model.DikeyAnalizGrafikModel;
import com.company.rating.view.model.GelirbilancoModel;
import com.company.rating.view.model.GrafikModel;
import com.company.rating.view.model.RasyoAnalizGrafikModel;
import com.company.rating.view.model.YatayAnalizGrafikModel;


//@Component("grafikData")
//@Scope("session")
public class GrafikData {
	private ArrayList<YatayAnalizGrafikModel> donenVarliklarGrafik;
	private ArrayList<YatayAnalizGrafikModel> duranVarliklarGrafik;
	private ArrayList<YatayAnalizGrafikModel> kisaVadeliYabanciGrafik;
	private ArrayList<YatayAnalizGrafikModel> uzunVadeliYabanciGrafik;
	private ArrayList<YatayAnalizGrafikModel> ozkanyaklarGrafik;
	private DikeyAnalizGrafikModel donenVarliklarGrafikD;
	private DikeyAnalizGrafikModel duranVarliklarGrafikD;
	private DikeyAnalizGrafikModel kisaVadeliYabanciGrafikD;
	private DikeyAnalizGrafikModel uzunVadeliYabanciGrafikD;
	private DikeyAnalizGrafikModel ozkanyaklarGrafikD;
	private ArrayList<RasyoAnalizGrafikModel> borcOdemeGucu;
	private ArrayList<RasyoAnalizGrafikModel> faaliyet;
	private ArrayList<RasyoAnalizGrafikModel> finansalYapi;
	private ArrayList<RasyoAnalizGrafikModel> kararlilik;
	private ArrayList<RasyoAnalizGrafikModel> gelirYaratma;
	private String borcOdemeGucuGrafik;
	private String faaliyetGrafik;
	private String  finansalYapiGrafik;
	private String kararlilikGrafik;
	private String borcOdemeGucuGrafikRapor;
	private String faaliyetGrafikRapor;
	private String  finansalYapiGrafikRapor;
	private String kararlilikGrafikRapor;
	private String fileSep = "/";
    private FacesContext faceCon = FacesContext.getCurrentInstance ();
    private ExternalContext externalContext= faceCon.getExternalContext();
    
    private  Font labelFont=new Font("SansSerif",Font.ITALIC,11);
    private  Font catagoryFont=new Font("SansSerif",Font.BOLD,12);
   
	@Autowired
	private GelirBilancoViewData gelirBilancoViewData;
    @Autowired
    private ApplicationData applicationData;
    @Autowired
    private GeneralData generalData;
@PostConstruct
public  void init(){
	 
}
 
	 public GrafikData() {
		// TODO Auto-generated constructor stub
		
	 }
	private void loadModels(int varlikId) {
		GelirbilancoModel bilancoModel = gelirBilancoViewData.getCodeModelMap().get(RatingFormulaParser.CODE_PREFIX + "" + varlikId);
		ArrayList<YatayAnalizGrafikModel> temp = new ArrayList<YatayAnalizGrafikModel>();
		YatayAnalizGrafikModel grafikModel = new YatayAnalizGrafikModel(bilancoModel);
		BigDecimal percent = null;
		try {
		
			percent = (bilancoModel.getCariDonem().subtract(bilancoModel.getOncekidonem())).divide(bilancoModel.getCariDonem(), 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));

		} catch (ArithmeticException e) {
			// TODO: handle exception
			percent = new BigDecimal(0);
		}
		grafikModel.setPercent(percent.toString());
		temp.add(grafikModel);
		
		// temp.add(e)
		for (GelirbilancoModel model : bilancoModel.getChilds()) {
			grafikModel = new YatayAnalizGrafikModel(model);

			percent = null;
			try {
				percent = (model.getCariDonem().subtract(model.getOncekidonem())).divide(model.getCariDonem(), 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100));

			} catch (ArithmeticException e) {
				// TODO: handle exception
				percent = new BigDecimal(0);
			}
			grafikModel.setPercent(percent.toString());
			temp.add(grafikModel);
		}
		if (varlikId == GelirBilancoViewData.DONEN_VARLIKLAR) {
			donenVarliklarGrafik = temp;
		}
		if (varlikId == GelirBilancoViewData.DURAN_VARLIKLAR) {
			duranVarliklarGrafik = temp;
		}
		if (varlikId == GelirBilancoViewData.KISA_VADELI_YABANCI) {
			kisaVadeliYabanciGrafik = temp;
		}
		if (varlikId == GelirBilancoViewData.UZUN_VADELI_YABANCI_KAYNAKLAR) {
			uzunVadeliYabanciGrafik = temp;
		}
		if (varlikId == GelirBilancoViewData.OZKAYNAKLAR) {
			ozkanyaklarGrafik = temp;
		}
	}
	private PieDataset createDatasetForBilancoDikey(int varlikId) {
		BigDecimal   hungered=new BigDecimal(100);
		DefaultPieDataset dataset = new DefaultPieDataset();
        GelirbilancoModel bilancoModel=gelirBilancoViewData.getCodeModelMap().get(RatingFormulaParser.CODE_PREFIX+""+varlikId);
		BigDecimal  total=bilancoModel.getCariDonem();
		DikeyAnalizGrafikModel temp=new DikeyAnalizGrafikModel();
		temp.setGrafikModels(new ArrayList<GrafikModel>());
		
		for(GelirbilancoModel model:bilancoModel.getChilds()){
			GrafikModel gfM = new GrafikModel(model);
			BigDecimal percentv = new BigDecimal(0);
			if (total.compareTo(new BigDecimal(0)) != 0) {
				percentv = model.getCariDonem().multiply(hungered).divide(total, 2, BigDecimal.ROUND_HALF_EVEN);
			}

			gfM.setPercent(percentv.toString());
			temp.getGrafikModels().add(gfM);
			if (percentv.compareTo(new BigDecimal(0)) == 0) {
				continue;
			}
			dataset.setValue(model.getGelirbilancocode().getName(), percentv);

		}
		if(varlikId==GelirBilancoViewData.DONEN_VARLIKLAR){
			donenVarliklarGrafikD=temp;
		}
		if(varlikId==GelirBilancoViewData.DURAN_VARLIKLAR){
			duranVarliklarGrafikD=temp;
		}
		if(varlikId==GelirBilancoViewData.KISA_VADELI_YABANCI){
			kisaVadeliYabanciGrafikD=temp;
		}
		if(varlikId==GelirBilancoViewData.UZUN_VADELI_YABANCI_KAYNAKLAR){
			uzunVadeliYabanciGrafikD=temp;
		}
		if(varlikId==GelirBilancoViewData.OZKAYNAKLAR){
			ozkanyaklarGrafikD=temp;
		}
		return dataset;
		}

	public void initiateYatayAnaliz() {
		loadModels(GelirBilancoViewData.DONEN_VARLIKLAR);
		loadModels(GelirBilancoViewData.DURAN_VARLIKLAR);
		loadModels(GelirBilancoViewData.KISA_VADELI_YABANCI);
		loadModels(GelirBilancoViewData.UZUN_VADELI_YABANCI_KAYNAKLAR);
		loadModels(GelirBilancoViewData.OZKAYNAKLAR);

	}
	public  void  initiateRasyoAnaliz(){
		  ArrayList<Integer>  kriterIds=new ArrayList<Integer>();
		    kriterIds.add(2);
		    kriterIds.add(1);
		    kriterIds.add(39);
		    borcOdemeGucu=loadGrafikModels(kriterIds);
		    kriterIds.clear();
		    kriterIds.add(3);
		    kriterIds.add(18);
		    kriterIds.add(4);
		    faaliyet=loadGrafikModels(kriterIds);
		    kriterIds.clear();
		    kriterIds.add(19);
		    kriterIds.add(20);
		    kriterIds.add(21);
		    kriterIds.add(22);
		    kriterIds.add(40);
		    kriterIds.add(24);
		    kriterIds.add(25);
		    kriterIds.add(26);
		    kriterIds.add(27);
		    finansalYapi=loadGrafikModels(kriterIds);
		    kriterIds.clear();
		    kriterIds.add(33);
		    kriterIds.add(34);
		    kriterIds.add(35);
		    kriterIds.add(36);
		    kriterIds.add(37);
		
		    kararlilik=loadGrafikModels(kriterIds);
		    
		    kriterIds.clear();
		    kriterIds.add(28);
		    kriterIds.add(29);
		    kriterIds.add(30);
		    kriterIds.add(31);
		    kriterIds.add(32);
		    
		    gelirYaratma=loadGrafikModels(kriterIds);
		    
		    createBorcOdemeGucuGrafik();
		    createFinansalYapiGrafik();
		    createFaaliyetGrafik();
		    createKararlilikOraniGrafik();
	}
	private ArrayList<RasyoAnalizGrafikModel> loadGrafikModels( ArrayList<Integer> kriterIds){

		ArrayList<RasyoAnalizGrafikModel> gModel=new ArrayList<RasyoAnalizGrafikModel>();
		
		for(Integer kriterId:kriterIds){
			Kriter kriter=applicationData.getKriterById(kriterId);
			RasyoAnalizGrafikModel model=new RasyoAnalizGrafikModel(kriter);
			BigDecimal result= gelirBilancoViewData.evalFormula(model.getKriter().getFormulilkyil(),GelirbilancoModel.CARI_DONEM);
		    model.setResult(result);
		    gModel.add(model);
		}
		return gModel;

	}

	public void initiateDikeyAnaliz() {
		File chartFile;
		try {
			JFreeChart jfreechart = ChartFactory.createPieChart3D("Dönen Varlıklar", createDatasetForBilancoDikey(GelirBilancoViewData.DONEN_VARLIKLAR), true, true, false);
				
		
		
		  
			String fileName= generalData.getFolderName()+fileSep+"donenVchart.png";  
			 chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
			
			
			PiePlot plot = (PiePlot) jfreechart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.getIgnoreZeroValues();
			
			
			StandardPieSectionLabelGenerator  label=new StandardPieSectionLabelGenerator("{1} %");
			plot.setLabelGenerator(label);
			
			plot.setLabelFont(labelFont);
			plot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
		
			ChartUtilities.saveChartAsPNG(chartFile, jfreechart,420,350);
			donenVarliklarGrafikD.setGrafik(fileSep+fileName);
			donenVarliklarGrafikD.setGrafikForRapor(fileSep+fileName);
			jfreechart = ChartFactory.createPieChart3D("Duran Varlıklar", createDatasetForBilancoDikey(GelirBilancoViewData.DURAN_VARLIKLAR), true, true, false);
             
		 
			 fileName= generalData.getFolderName()+fileSep+"duranVchart.png";  
			 chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
			

			 plot = (PiePlot) jfreechart.getPlot();
			 plot.setBackgroundPaint(Color.WHITE);
			 

			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} %"));
			plot.setLabelFont(labelFont);
			plot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
			ChartUtilities.saveChartAsPNG(chartFile, jfreechart,420,350);
			duranVarliklarGrafikD.setGrafik(fileSep+fileName);
			duranVarliklarGrafikD.setGrafikForRapor(fileSep+fileName);
			
			 jfreechart = ChartFactory.createPieChart3D("Uzun Vadeli Yabancı Kaynaklar", createDatasetForBilancoDikey(GelirBilancoViewData.UZUN_VADELI_YABANCI_KAYNAKLAR), true, true, false);
			
			 fileName= generalData.getFolderName()+fileSep+"uzunVchart.png";  
			 chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
		

			 plot = (PiePlot) jfreechart.getPlot();
			 plot.setBackgroundPaint(Color.WHITE);
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} %"));
			plot.setLabelFont(labelFont);
			plot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
			ChartUtilities.saveChartAsPNG(chartFile, jfreechart,420,350);
			uzunVadeliYabanciGrafikD.setGrafik(fileSep+fileName);
			uzunVadeliYabanciGrafikD.setGrafikForRapor(fileSep+fileName);
			 jfreechart = ChartFactory.createPieChart3D("Kısa Vadeli Yabancı Kaynaklar", createDatasetForBilancoDikey(GelirBilancoViewData.KISA_VADELI_YABANCI), true, true, false);
			 
			 fileName= generalData.getFolderName()+fileSep+"kisaVchart.png";  
			 chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
		

			 plot = (PiePlot) jfreechart.getPlot();
			 plot.setBackgroundPaint(Color.WHITE);
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} %"));
			plot.setLabelFont(labelFont);
			plot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
			
			ChartUtilities.saveChartAsPNG(chartFile, jfreechart,420,350);
			 kisaVadeliYabanciGrafikD.setGrafik(fileSep+fileName);
			 kisaVadeliYabanciGrafikD.setGrafikForRapor(fileSep+fileName);
			
			 jfreechart = ChartFactory.createPieChart3D("Özkaynaklar", createDatasetForBilancoDikey(GelirBilancoViewData.OZKAYNAKLAR), true, true, false);

			 fileName= generalData.getFolderName()+fileSep+"ozkaynakVchart.png";  
			 chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();


			 plot = (PiePlot) jfreechart.getPlot();
			 plot.setBackgroundPaint(Color.WHITE);
			plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1} %"));
			plot.setLabelFont(labelFont);
			plot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
			ChartUtilities.saveChartAsPNG(chartFile, jfreechart,420,350);
			ozkanyaklarGrafikD.setGrafik(fileSep+fileName);
			ozkanyaklarGrafikD.setGrafikForRapor(fileSep+fileName); 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private DefaultCategoryDataset getDataSet(ArrayList<RasyoAnalizGrafikModel>  gModel){
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(RasyoAnalizGrafikModel model:gModel){
			dataset.addValue(model.getResult(), model.getKriter().getKriterName(), model.getKriter().getKriterName());
		}
		return  dataset;
	}
	private DefaultCategoryDataset getDataSetXY(ArrayList<RasyoAnalizGrafikModel>  gModel,String rowName){
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(RasyoAnalizGrafikModel model:gModel){
			dataset.addValue(model.getResult(), rowName, model.getKriter().getKriterName());
		}
		return  dataset;
	}
	private void createBorcOdemeGucuGrafik(){
		JFreeChart chart = ChartFactory.createBarChart(
				"Borç Ödeme Gücü", // chart title
				"", // domain axis label
				"", // range axis label
				getDataSet(borcOdemeGucu), // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips?
				false // URLs?
				);
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		 CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
		 xAxis.setLabelFont(catagoryFont);
			BarRenderer renderer  = new StackedBarRenderer();
		      renderer.setMaximumBarWidth(.1);
		        renderer.setMinimumBarLength(.1);
		plot.setRenderer(renderer);
		
	

		 

		try {
			String fileName=generalData.getFolderName()+fileSep+"borcOdeme.png";
			 File chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
			ChartUtilities.saveChartAsPNG(chartFile, chart,500,350);
			borcOdemeGucuGrafik=fileSep+fileName;
			borcOdemeGucuGrafikRapor=fileSep+fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	private void createFinansalYapiGrafik(){
		JFreeChart chart = ChartFactory.createBarChart(
				"Finansal Yapı", // chart title
				"", // domain axis label
				"", // range axis label
				getDataSet(finansalYapi), // data
				PlotOrientation.HORIZONTAL, // orientation
				false, // include legend
				true, // tooltips?
				false // URLs?
				);
		
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		 CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
		 xAxis.setLabelFont(catagoryFont);
		BarRenderer renderer  = new StackedBarRenderer();
	      renderer.setMaximumBarWidth(.1);
	        renderer.setMinimumBarLength(.1);
		plot.setRenderer(renderer);

		try {
			String fileName=generalData.getFolderName()+fileSep+"finansalYapi.png";  
			 File chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
			ChartUtilities.saveChartAsPNG(chartFile, chart,500,570);
			finansalYapiGrafik=fileSep+fileName;
			finansalYapiGrafikRapor=fileSep+fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	private void createKararlilikOraniGrafik(){
		JFreeChart chart = ChartFactory.createBarChart(
				"Karlılık Oranları", // chart title
				"", // domain axis label
				"", // range axis label
				getDataSet(kararlilik), // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips?
				false // URLs?
				);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		 CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
	
		         xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		         xAxis.setLabelFont(catagoryFont);
		BarRenderer renderer  = new StackedBarRenderer();
	      renderer.setMaximumBarWidth(.1);
	        renderer.setMinimumBarLength(.1);

         plot.setRenderer(renderer);
		

		try {
			String fileName= fileSep+"KararlilikOrani.png";  
			 File chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
			ChartUtilities.saveChartAsPNG(chartFile, chart,600,350);
			kararlilikGrafik=fileSep+fileName;
			kararlilikGrafikRapor=fileSep+fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	private void createFaaliyetGrafik(){
		JFreeChart chart = ChartFactory.createLineChart(
				"Faaliyet Oranı", // chart title
				"", // domain axis label
				"", // range axis label
				getDataSetXY(faaliyet,"rasyolar"), // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips
				false // urls
				);

	
	
		 

		try {
			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.setBackgroundPaint(Color.lightGray);
			plot.setRangeGridlinePaint(Color.white);
			// customise the range axis...
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		
			rangeAxis.setUpperBound(365);
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			
			// customise the renderer...
			LineAndShapeRenderer renderer
			= (LineAndShapeRenderer) plot.getRenderer();
			renderer.setShapesVisible(true);
			renderer.setDrawOutlines(true);
			renderer.setUseFillPaint(true);
			renderer.setFillPaint(Color.white);
			String fileName= 
				generalData.getFolderName()+fileSep+"faaliyetGrafik.png";  
			 File chartFile = new File(externalContext.getRealPath(fileName));
			 chartFile.createNewFile();
			ChartUtilities.saveChartAsPNG(chartFile, chart,600,350);
			faaliyetGrafik=fileSep+fileName;
			faaliyetGrafikRapor=fileSep+fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	public ArrayList<YatayAnalizGrafikModel> getDonenVarliklarGrafik() {
		return donenVarliklarGrafik;
	}

	public void setDonenVarliklarGrafik(ArrayList<YatayAnalizGrafikModel> donenVarliklarGrafik) {
		this.donenVarliklarGrafik = donenVarliklarGrafik;
	}

	public ArrayList<YatayAnalizGrafikModel> getDuranVarliklarGrafik() {
		return duranVarliklarGrafik;
	}

	public void setDuranVarliklarGrafik(ArrayList<YatayAnalizGrafikModel> duranVarliklarGrafik) {
		this.duranVarliklarGrafik = duranVarliklarGrafik;
	}

	public ArrayList<YatayAnalizGrafikModel> getKisaVadeliYabanciGrafik() {
		return kisaVadeliYabanciGrafik;
	}

	public void setKisaVadeliYabanciGrafik(ArrayList<YatayAnalizGrafikModel> kisaVadeliYabanciGrafik) {
		this.kisaVadeliYabanciGrafik = kisaVadeliYabanciGrafik;
	}

	public ArrayList<YatayAnalizGrafikModel> getUzunVadeliYabanciGrafik() {
		return uzunVadeliYabanciGrafik;
	}

	public void setUzunVadeliYabanciGrafik(ArrayList<YatayAnalizGrafikModel> uzunVadeliYabanciGrafik) {
		this.uzunVadeliYabanciGrafik = uzunVadeliYabanciGrafik;
	}

	public ArrayList<YatayAnalizGrafikModel> getOzkanyaklarGrafik() {
		return ozkanyaklarGrafik;
	}

	public void setOzkanyaklarGrafik(ArrayList<YatayAnalizGrafikModel> ozkanyaklarGrafik) {
		this.ozkanyaklarGrafik = ozkanyaklarGrafik;
	}


	public DikeyAnalizGrafikModel getDonenVarliklarGrafikD() {
		return donenVarliklarGrafikD;
	}


	public void setDonenVarliklarGrafikD(DikeyAnalizGrafikModel donenVarliklarGrafikD) {
		this.donenVarliklarGrafikD = donenVarliklarGrafikD;
	}


	public DikeyAnalizGrafikModel getDuranVarliklarGrafikD() {
		return duranVarliklarGrafikD;
	}


	public void setDuranVarliklarGrafikD(DikeyAnalizGrafikModel duranVarliklarGrafikD) {
		this.duranVarliklarGrafikD = duranVarliklarGrafikD;
	}


	public DikeyAnalizGrafikModel getKisaVadeliYabanciGrafikD() {
		return kisaVadeliYabanciGrafikD;
	}


	public void setKisaVadeliYabanciGrafikD(DikeyAnalizGrafikModel kisaVadeliYabanciGrafikD) {
		this.kisaVadeliYabanciGrafikD = kisaVadeliYabanciGrafikD;
	}


	public DikeyAnalizGrafikModel getUzunVadeliYabanciGrafikD() {
		return uzunVadeliYabanciGrafikD;
	}


	public void setUzunVadeliYabanciGrafikD(DikeyAnalizGrafikModel uzunVadeliYabanciGrafikD) {
		this.uzunVadeliYabanciGrafikD = uzunVadeliYabanciGrafikD;
	}


	public DikeyAnalizGrafikModel getOzkanyaklarGrafikD() {
		return ozkanyaklarGrafikD;
	}


	public void setOzkanyaklarGrafikD(DikeyAnalizGrafikModel ozkanyaklarGrafikD) {
		this.ozkanyaklarGrafikD = ozkanyaklarGrafikD;
	}


	public GelirBilancoViewData getGelirBilancoViewData() {
		return gelirBilancoViewData;
	}


	public void setGelirBilancoViewData(GelirBilancoViewData gelirBilancoViewData) {
		this.gelirBilancoViewData = gelirBilancoViewData;
	}
	public ArrayList<RasyoAnalizGrafikModel> getBorcOdemeGucu() {
		return borcOdemeGucu;
	}
	public void setBorcOdemeGucu(ArrayList<RasyoAnalizGrafikModel> borcOdemeGucu) {
		this.borcOdemeGucu = borcOdemeGucu;
	}
	public ArrayList<RasyoAnalizGrafikModel> getFaaliyet() {
		return faaliyet;
	}
	public void setFaaliyet(ArrayList<RasyoAnalizGrafikModel> faaliyet) {
		this.faaliyet = faaliyet;
	}
	public ArrayList<RasyoAnalizGrafikModel> getFinansalYapi() {
		return finansalYapi;
	}
	public void setFinansalYapi(ArrayList<RasyoAnalizGrafikModel> finansalYapi) {
		this.finansalYapi = finansalYapi;
	}
	public ArrayList<RasyoAnalizGrafikModel> getKararlilik() {
		return kararlilik;
	}
	public void setKararlilik(ArrayList<RasyoAnalizGrafikModel> kararlilik) {
		this.kararlilik = kararlilik;
	}

	public String getBorcOdemeGucuGrafik() {
		return borcOdemeGucuGrafik;
	}

	public void setBorcOdemeGucuGrafik(String borcOdemeGucuGrafik) {
		this.borcOdemeGucuGrafik = borcOdemeGucuGrafik;
	}

	public String getFaaliyetGrafik() {
		return faaliyetGrafik;
	}

	public void setFaaliyetGrafik(String faaliyetGrafik) {
		this.faaliyetGrafik = faaliyetGrafik;
	}

	public String getFinansalYapiGrafik() {
		return finansalYapiGrafik;
	}

	public void setFinansalYapiGrafik(String finansalYapiGrafik) {
		this.finansalYapiGrafik = finansalYapiGrafik;
	}

	public String getKararlilikGrafik() {
		return kararlilikGrafik;
	}

	public void setKararlilikGrafik(String kararlilikGrafik) {
		this.kararlilikGrafik = kararlilikGrafik;
	}


	

	public String getBorcOdemeGucuGrafikRapor() {
		return borcOdemeGucuGrafikRapor;
	}

	public void setBorcOdemeGucuGrafikRapor(String borcOdemeGucuGrafikRapor) {
		this.borcOdemeGucuGrafikRapor = borcOdemeGucuGrafikRapor;
	}

	public String getFaaliyetGrafikRapor() {
		return faaliyetGrafikRapor;
	}

	public void setFaaliyetGrafikRapor(String faaliyetGrafikRapor) {
		this.faaliyetGrafikRapor = faaliyetGrafikRapor;
	}

	public String getFinansalYapiGrafikRapor() {
		return finansalYapiGrafikRapor;
	}

	public void setFinansalYapiGrafikRapor(String finansalYapiGrafikRapor) {
		this.finansalYapiGrafikRapor = finansalYapiGrafikRapor;
	}

	public String getKararlilikGrafikRapor() {
		return kararlilikGrafikRapor;
	}

	public void setKararlilikGrafikRapor(String kararlilikGrafikRapor) {
		this.kararlilikGrafikRapor = kararlilikGrafikRapor;
	}

	public ArrayList<RasyoAnalizGrafikModel> getGelirYaratma() {
		return gelirYaratma;
	}

	public void setGelirYaratma(ArrayList<RasyoAnalizGrafikModel> gelirYaratma) {
		this.gelirYaratma = gelirYaratma;
	}
	
	
}
