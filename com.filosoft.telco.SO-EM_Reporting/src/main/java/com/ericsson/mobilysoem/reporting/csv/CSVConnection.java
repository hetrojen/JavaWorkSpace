package com.ericsson.mobilysoem.reporting.csv;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ericsson.mobilysoem.reporting.main.SoemReportingMain;



public class CSVConnection {
	public static final Logger logger = Logger.getLogger(CSVConnection.class);
	private static HashMap<String, CSVConnection> instance = new  HashMap<String, CSVConnection>();
	private Connection conn;

	private CSVConnection(String location) {
		
	    String dbDriver=null;

		Properties propCsvResource = new Properties();
		Properties propCsvJdbc = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream("/com/ericsson/mobilysoem/reporting/properties/CSVResources.properties");
			propCsvResource.load(in);
			
			InputStream inCsvJdbc = getClass().getResourceAsStream("/com/ericsson/mobilysoem/reporting/properties/csvjdbcSetting.properties");
			propCsvJdbc.load(inCsvJdbc);

		
			dbDriver=propCsvResource.getProperty("dbDriver");
		
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			SoemReportingMain.logger.error("",e);
		}

		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection("jdbc:relique:csv:"+location,propCsvJdbc );

		} catch (ClassNotFoundException cnfErr) {
			cnfErr.printStackTrace();
			SoemReportingMain.logger.error("",cnfErr);
		} catch (SQLException err) {
			err.printStackTrace();
			SoemReportingMain.logger.error("",err);
		}

	}

	public static CSVConnection getInstance(String key,String location) {
		
		// we used csvjdbc api to retrieve data with sql, 
		//this api consider file directory as a database and consider csv files as a table.
		// So we need different single connection per each directory
		
		  if(instance.get(key)==null){
			  instance.put(key, new CSVConnection(location));
		  }
		  


		return instance.get(key);

	}

	public Connection getConn() {
		return conn;
	}

	


}
