package com.ericsson.mobilysoem.reporting.main;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.ericsson.mobilysoem.reporting.csv.CSVConnection;


public class SoemConfig {
	
private static String csvClientDirectoryJeddah;
private static String csvClientDirectoryRiyadh;
private static String reportFileTemplate;
private static String reportFileDestination;
//private static String ftpServer;
//private static String ftpUser;
//private static String ftpPassword;
//private static String ftpLogFile;
public static final Logger logger = Logger.getLogger(SoemConfig.class);
	public static void init(Properties propResource) throws Exception{
		
		
		csvClientDirectoryJeddah=propResource.getProperty("csvClientDirectoryJeddah");
		csvClientDirectoryRiyadh=propResource.getProperty("csvClientDirectoryRiyadh");
		reportFileTemplate=propResource.getProperty("reportFileTemplate");
		reportFileDestination=propResource.getProperty("reportFileDestination");
		if(csvClientDirectoryJeddah==null || csvClientDirectoryJeddah.trim().equals("")){
		
			throw new Exception("csvClientDirectoryJeddah should be given in Config File");
		}else{
			csvClientDirectoryJeddah=csvClientDirectoryJeddah.trim();
		}
		if(csvClientDirectoryRiyadh==null || csvClientDirectoryRiyadh.trim().equals("")){
			
			throw new Exception("csvClientDirectoryRiyadh should be given in Config File");
		}else{
			csvClientDirectoryRiyadh=csvClientDirectoryRiyadh.trim();
		}
		if(reportFileTemplate==null || reportFileTemplate.trim().equals("")){
			
			throw new Exception("reportFileTemplate should be given in Config File");
		}else{
			reportFileTemplate=reportFileTemplate.trim();
		}
		if(reportFileDestination==null || reportFileDestination.trim().equals("")){
			
			throw new Exception("reportFileDestination should be given in Config File");
		}else{
			reportFileDestination=reportFileDestination.trim();
		}
	
		
		
		logger.info("csvClientDirectoryJeddah..:"+csvClientDirectoryJeddah);
		logger.info("csvClientDirectoryRiyadh..:"+csvClientDirectoryRiyadh);
		logger.info("reportFileTemplate..:"+reportFileTemplate);
		logger.info("reportFileDestination..:"+reportFileDestination);
		
//		logger.info("ftpServer..:"+ftpServer);
//		logger.info("ftpUser..:"+ftpUser);
//		logger.info("ftpPassword..:"+ftpPassword);
//		logger.info("ftpLogFile..:"+ftpLogFile);
		
	}

	
	public static String getCsvClientDirectoryJeddah() {
		return csvClientDirectoryJeddah;
	}
	public static String getCsvClientDirectoryRiyadh() {
		return csvClientDirectoryRiyadh;
	}
	public static String getReportFileTemplate() {
		return reportFileTemplate;
	}
	public static String getReportFileDestination() {
		return reportFileDestination;
	}
//	public static String getFtpServer() {
//		return ftpServer;
//	}
//	public static String getFtpUser() {
//		return ftpUser;
//	}
//	public static String getFtpPassword() {
//		return ftpPassword;
//	}
//	public static String getFtpLogFile() {
//		return ftpLogFile;
//	}
//	
//	
	
}
