package com.ericsson.mobilysoem.reporting.main;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ericsson.mobilysoem.reporting.csv.CSVConnection;
import com.ericsson.mobilysoem.reporting.csv.CSVFacade;
import com.ericsson.mobilysoem.reporting.model.CSVFileInfo;
import com.ericsson.mobilysoem.reporting.model.HopDetailReportModel;
import com.ericsson.mobilysoem.reporting.model.HwSwInventoryReportModel;

;

public class SoemReportingMain {
	public static final Logger logger = Logger.getLogger(SoemReportingMain.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Server hsqlServer=null;
	 List<CSVFileInfo> jeddahCSVFiles;
	 List<CSVFileInfo> riyadhCSVFiles;
	try{
	
		
		if(args==null || args.length==0 || args[0].trim().equals("")){
			  throw new Exception("Config File Name Must be given to SoemReportingMain as a program argument");
		}
		// Program expects config file name as a parameter
		String configFileName=args[0].trim();
		
		
    	InputStream configFile=null;
		try {
			logger.info("ConfigFile...:"+configFileName);
			
			configFile = new FileInputStream(configFileName);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			
			throw e2;
		}
	
		Properties propResource = new Properties();
		 try {
			propResource.load(configFile);
			SoemConfig.init(propResource);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
			throw e1;
		}
		String reportFileName=null;
	
        try {
        	String  fileLocationJ=SoemConfig.getCsvClientDirectoryJeddah();
    		
        	
    		Connection connectionj=CSVConnection.getInstance("jeddah", fileLocationJ).getConn();    		
    		jeddahCSVFiles=CSVFacade.load(fileLocationJ,connectionj);    		
     
            List<HopDetailReportModel> list= ReportingModelCreator.getHopDetailReportModels();
            List<HwSwInventoryReportModel> list1= ReportingModelCreator.getHwSwInventoryReportModel(); 
        	 reportFileName= ExcelReporting.createReportFile("jeddah");
      		ExcelReporting.fillHopDetailsReport(list,jeddahCSVFiles,reportFileName);
			ExcelReporting.fillHwSwInventoryReport(list1,jeddahCSVFiles,reportFileName);
			for(CSVFileInfo finfo:jeddahCSVFiles){
				moveFile(fileLocationJ, finfo.getFileName());
			}
			
			logger.info(  "Report has been created with name ... :" + reportFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			deleteFile(reportFileName); 
			logger.error(e);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			deleteFile(reportFileName); 
			logger.error(e);
			
		}
		
		
		reportFileName="";
	        try {
	        	String  fileLocationR=SoemConfig.getCsvClientDirectoryRiyadh();		
	    		Connection connectionr=CSVConnection.getInstance("riyadh",fileLocationR).getConn();
	    		riyadhCSVFiles=CSVFacade.load(fileLocationR, connectionr);
	    		List<HopDetailReportModel> list= ReportingModelCreator.getHopDetailReportModels();
	    		List<HwSwInventoryReportModel>    list1= ReportingModelCreator.getHwSwInventoryReportModel(); 
	        	 reportFileName= ExcelReporting.createReportFile("riyadh");
	        	
				ExcelReporting.fillHopDetailsReport(list,riyadhCSVFiles,reportFileName);
				ExcelReporting.fillHwSwInventoryReport(list1,riyadhCSVFiles,reportFileName);
				for(CSVFileInfo finfo:riyadhCSVFiles){
					moveFile(fileLocationR, finfo.getFileName());
				}
				
				logger.info(  "Report has been created with name ... :" + reportFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				deleteFile(reportFileName); 				
			
				throw e;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				deleteFile(reportFileName); 
				
				throw e;
			}
		
	  }
      catch (Exception e) {
		// TODO: handle exception
    	 
    	  logger.error(e);
	}    

  
	}
    public static void deleteFile(String file){
    	if(file==null){
    		return;
    	}
    	try{
    	File reFile=new File(file);
		if(reFile.exists() &&  reFile.isFile()){
			reFile.delete(); 
		}
    	}catch (Exception e) {
			// TODO: handle exception
    		return;
		}
    }
	public static void moveFile(String csv_input, String file) throws Exception{
 
        try{
        	File moveF = new File(csv_input + System.getProperty("file.separator") + file+".csv");

    		File fol = new File(csv_input + System.getProperty("file.separator") + "processedFiles");
    			if (!fol.exists()) {
    				fol.mkdir();
    			}
    			
    			File tar=new File(fol, moveF.getName());
    			if(tar.exists()){
    				tar.delete();
    			}
    			boolean success = moveF.renameTo(new File(fol, moveF.getName()));
    			if (!success) {
    				int i=0;
    			}
        	
        }catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}
}
