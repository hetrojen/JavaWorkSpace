package com.company.rating.view;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.company.rating.domain.Kriter;
import com.company.rating.form.GelirBilancoViewData;
import com.company.rating.view.model.GelirbilancoModel;
import com.company.rating.view.model.RasyoAnalizGrafikModel;
@Scope("request")
@Component("rasyoAnalizGrafik")
public class RasyoAnalizGrafikView {
private ArrayList<RasyoAnalizGrafikModel> borcOdemeGucu;
private ArrayList<RasyoAnalizGrafikModel> faaliyet;
private ArrayList<RasyoAnalizGrafikModel> finansalYapi;
private ArrayList<RasyoAnalizGrafikModel> kararlilik;
private StreamedContent borcOdemeGucuGrafik;
private StreamedContent faaliyetGrafik;
private StreamedContent finansalYapiGrafik;
private StreamedContent kararlilikGrafik;
@Autowired
private ApplicationData applicationData;
@Autowired
private GelirBilancoViewData gelirBilancoViewData;
@PostConstruct
public  void  init(){
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
	    kriterIds.add(37);
	    kararlilik=loadGrafikModels(kriterIds);
	    
	    createBorcOdemeGucuGrafik();
	    createFinansalYapiGrafik();
	    createFaaliyetGrafik();
	    createKararlilikOraniGrafik();
}
public RasyoAnalizGrafikView() {
	// TODO Auto-generated constructor stub
  



}
public ArrayList<RasyoAnalizGrafikModel> loadGrafikModels( ArrayList<Integer> kriterIds){

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
public StreamedContent getBorcOdemeGucuGrafik() {
	return borcOdemeGucuGrafik;
}
public void setBorcOdemeGucuGrafik(StreamedContent borcOdemeGucuGrafik) {
	this.borcOdemeGucuGrafik = borcOdemeGucuGrafik;
}
public StreamedContent getFaaliyetGrafik() {
	return faaliyetGrafik;
}
public void setFaaliyetGrafik(StreamedContent faaliyetGrafik) {
	this.faaliyetGrafik = faaliyetGrafik;
}
public StreamedContent getFinansalYapiGrafik() {
	return finansalYapiGrafik;
}
public void setFinansalYapiGrafik(StreamedContent finansalYapiGrafik) {
	this.finansalYapiGrafik = finansalYapiGrafik;
}
public StreamedContent getKararlilikGrafik() {
	return kararlilikGrafik;
}
public void setKararlilikGrafik(StreamedContent kararlilikGrafik) {
	this.kararlilikGrafik = kararlilikGrafik;
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
			"Category", // domain axis label
			"0.10", // range axis label
			getDataSet(borcOdemeGucu), // data
			PlotOrientation.VERTICAL, // orientation
			true, // include legend
			true, // tooltips?
			false // URLs?
			);
	File chartFile = new File("duranVchart");

	 

	try {
		ChartUtilities.saveChartAsPNG(chartFile, chart,370,350);
		borcOdemeGucuGrafik=new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			
}
private void createFinansalYapiGrafik(){
	JFreeChart chart = ChartFactory.createBarChart(
			"Borç Ödeme Gücü", // chart title
			"Category", // domain axis label
			"0.10", // range axis label
			getDataSet(finansalYapi), // data
			PlotOrientation.HORIZONTAL, // orientation
			true, // include legend
			true, // tooltips?
			false // URLs?
			);
	File chartFile = new File("fi");

	 

	try {
		ChartUtilities.saveChartAsPNG(chartFile, chart,370,350);
		finansalYapiGrafik=new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			
}
private void createKararlilikOraniGrafik(){
	JFreeChart chart = ChartFactory.createBarChart(
			"Borç Ödeme Gücü", // chart title
			"Category", // domain axis label
			"0.10", // range axis label
			getDataSet(kararlilik), // data
			PlotOrientation.VERTICAL, // orientation
			true, // include legend
			true, // tooltips?
			false // URLs?
			);
	File chartFile = new File("kararlilik");

	 

	try {
		ChartUtilities.saveChartAsPNG(chartFile, chart,370,350);
		kararlilikGrafik=new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			
}
private void createFaaliyetGrafik(){
	JFreeChart chart = ChartFactory.createLineChart(
			"Finansal Yapi", // chart title
			"Release", // domain axis label
			"Class Count", // range axis label
			getDataSetXY(faaliyet,"rasyolar"), // data
			PlotOrientation.VERTICAL, // orientation
			false, // include legend
			true, // tooltips
			false // urls
			);
	File chartFile = new File("kjkj");

	 

	try {
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// customise the renderer...
		LineAndShapeRenderer renderer
		= (LineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setFillPaint(Color.white);
		ChartUtilities.saveChartAsPNG(chartFile, chart,370,350);
		faaliyetGrafik=new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			
}
}
