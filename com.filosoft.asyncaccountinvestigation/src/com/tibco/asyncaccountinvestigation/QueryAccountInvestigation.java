package com.tibco.asyncaccountinvestigation;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

import javax.sql.DataSource;

import oracle.i18n.util.CharConvRepackage;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import asyncaccountinvestigation.accountinvestigation.DoAccountInvestigation;
import asyncaccountinvestigation.accountinvestigation.DoAccountInvestigationResponse;
import asyncaccountinvestigation.accountinvestigation.ObjectFactory;

import com.ingbank.credit.bpm.datamodel.customersearchv1.AccountInvestigationCustomerSearchInput;
import com.ingbank.credit.bpm.datamodel.customersearchv1.AccountInvestigationCustomerSearchOutput;
import com.ingbank.credit.bpm.datamodel.customersearchv1.DistraintCustomerSearchInput;
import com.ingbank.credit.bpm.datamodel.customersearchv1.DistraintCustomerSearchOutput;
import com.tibco.asyncaccountinvestigation.export.ExcelExporter;
import com.tibco.asyncaccountinvestigation.export.model.Result;
import com.tibco.asyncaccountinvestigation.export.model.Results;
import com.tibco.asyncaccountinvestigation.export.model.Row;
import com.tibco.asyncaccountinvestigation.util.StringHelper;


/**
 * Implementation of QueryAccountInvestigation component.
 *
 */
public class QueryAccountInvestigation
extends
AbstractQueryAccountInvestigation {
	
	public final static  String EXPENSE_CLIENT="Expense";
	public final static  String DISTRAINT_CLIENT="Distraint";
	private String pathOutput="";
	private static HashMap<String, List<String>> headerExcludeList=new HashMap<String, List<String>> ();
	static{
	   List<String> headerList=new ArrayList<>(); 
	   headerList.add("FILEDATA");  
	   headerExcludeList.put("VERISOFT.HESARA_KREDIKARTIEXTRELER", headerList);
	   CharConvRepackage.getCommand();
	}

	final Logger logger = LoggerFactory.getLogger("com.tibco.asyncaccountinvestigation");
	//List of stored procedures and related JDBC Datasource ID
	private final String[][] storedProcedures = {
			{"HESAPLAR","BankDB.SP_HESARA_HESAPLAR","1","BANKDB.HESARA_HESAPLAR"},
			{"YATIRIM","BankDB.SP_HESARA_YATIRIM","1","BANKDB.HESARA_YATIRIM"},
			{"BLOKE","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_BLOKE","1","BANKDB.HESARA_BLOKE"},
			{"EKHESAPLAR","FINANS.PK_BKR_HESARA.SP_EK_HESARA","2","finans.hesara_ekhesaplar"},
			{"BIREYSELKREDI","FINANS.PK_BKR_HESARA.SP_BKR_HESARA","2","finans.hesara_bireyselkredi,finans.hesara_bireyselkredi_kefil,finans.hesara_bireyselkredi_odmpln,finans.hesara_bireyselkredi_odmbilgi"},
			{"TICARIKREDI","BankDB.SP_HESARA_TICARIKREDI","1","BANKDB.hesara_ticarikredi_tkrd,BANKDB.hesara_ticarikredi_odeplan,BANKDB.hesara_ticarikredi_belge,BANKDB.hesara_ticarikredi_kefil,BANKDB.hesara_ticarikredi_odebilgi,BANKDB.hesara_ticarikredi_nkrd,BANKDB.hesara_ticarikredi_nkrd_hrkt"},
			{"DEBITKARTLARI","BankDB.SP_HESARA_DEBITKARTLARI","1","BANKDB.HESARA_DEBITKARTLARI"},
			{"KREDIKARTLARI","BankDB.SP_HESARA_KREDIKARTLARI","1","BANKDB.HESARA_KREDIKARTLARI"},
			{"KIRALIKKASA","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_KIRALIKKASA","1","BANKDB.HESARA_KIRALIKKASA"},
			{"GELENGIDENHAVALE","BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_GELENGIDENHAVALE","1","BANKDB.HESARA_GELENGIDENHAVALE"},
			{"GIDENEFT","BankDB.SP_HESARA_GIDENEFT","1","BANKDB.HESARA_GIDENEFT"},
			{"GELENEFT","BankDB.SP_HESARA_GELENEFT","1","BANKDB.HESARA_GELENEFT"},
			{"GELENHAVALE","BankDB.SP_HESARA_GELENHAVALE","1","BANKDB.HESARA_GELENHAVALE"},
			{"GIDENHAVALE","BankDB.SP_HESARA_GIDENHAVALE","1","BANKDB.HESARA_GIDENHAVALE"},
			{"VIRMAN","BankDB.SP_HESARA_VIRMAN","1","BANKDB.HESARA_VIRMAN"},
			{"HESAPHAREKETLERI","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_HESAPHAREKETLERI","1","BANKDB.HESARA_HESAPHAREKETLERI"},
			{"DEBITKARTHAREKETLERI","VERISOFTATM.SP_HESARA_DEBITKARTHAREKETLERI","3","VERISOFTATM.HESARA_DEBITKARTHAREKETLERI"},
			{"KREDIKARTIEXTRELER","VERISOFT.SP_HESARA_KREDIKARTIEXTRELER","4","VERISOFT.HESARA_KREDIKARTIEXTRELER"},
			{"WESTERNUNION","BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_WESTERNUNION","1","BANKDB.HESARA_WESTERNUNION"},
			{"NAKITYATAN","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_NAKITYATAN","1","BANKDB.HESARA_NAKITYATAN"},
			{"NAKITCEKILEN","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_NAKITCEKILEN","1","BANKDB.HESARA_NAKITCEKILEN"},
			{"TICARIKREDIHAR","BankDB.SP_HESARA_TICARIKREDIHAR","1","bankdb.hesara_ticarikredi_odebilgi,bankdb.hesara_ticarikredi_nkrd_hrkt,bankdb.hesara_ticarikredi_tmek_hrkt"},
			{"SENET","BankDB.SP_HESARA_SENET","1","BANKDB.HESARA_SENET"},
			{"CEK","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_CEK","1","BANKDB.HESARA_BNKCEKODEME,BANKDB.HESARA_TAKASALACAK,BANKDB.HESARA_TAKASYPALACAK,BANKDB.HESARA_DEPOBNKCEK,BANKDB.HESARA_BNKCEKPROVIZYON"},
			{"KASA","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_KASA","1","BANKDB.HESARA_KASA"},
			{"MEVDUAT","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_MEVDUAT","1","BANKDB.HESARA_MEVDUAT"},
			{"FON","BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_FON","1","BANKDB.HESARA_FON"},
			{"MENKUL","BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_MENKUL","1","BANKDB.HESARA_MENKUL_DTY,BANKDB.HESARA_MENKUL_TPLM"},
			{"HISSESENEDI","BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_HISSESENEDI","1","BANKDB.HESARA_HISSESENEDI_DTY,BANKDB.HESARA_HISSESENEDI_TPLM"},
			{"VOB","BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_VOB","1","BANKDB.HESARA_VOB_DTY,BANKDB.HESARA_VOB_TPLM"},
			{"TUREV","BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_TUREV","1","BANKDB.HESARA_TUREV_DTY,BANKDB.HESARA_TUREV_TPLM"},
			{"HESAPSIZHAVALE","BankDB.SP_HESARA_HESAPSIZHAVALE","1","BANKDB.HESARA_HESAPSIZHAVALE"},
			{"HESAPSIZEFT","BankDB.SP_HESARA_HESAPSIZ_EFT","1","BANKDB.HESARA_HESAPSIZ_EFT"},
			{"HESAPSIZCEK","BankDB.SP_HESARA_HESAPSIZCEK","1","BANKDB.HESARA_HESAPSIZCEK"},
			{"HESAPSIZSENET","BankDB.SP_HESARA_HESAPSIZSENET","1","BANKDB.HESARA_HESAPSIZSENET"},
			{"HESAPSIZPARAYATIR","BankDB.SP_HESARA_HESAPSIZPARAYATIR","1","BANKDB.HESARA_HESAPSIZPARAYATIR"},
			{"HESAPSIZPARACEK","BankDB.SP_HESARA_HESAPSIZPARACEK","1","BANKDB.HESARA_HESAPSIZPARACEK"},
			{"FAIZORAN","BankDB.SP_HESARA_FAIZORAN","1","BANKDB.HESARA_FAIZORAN"},
			{"BKMHAREKET","finans.pk_bkr_hesara.sp_bkr_masraf_ara","2","finans.hesara_masraf"}
	}; 

	private final String _queryProcedureList = "select distinct URUNKODU from BANKDB.HVMHESARADETAY where SURECID = ?";

	private SPResult[] results = new SPResult[storedProcedures.length];

	// Stored procedure distraintCustomerSearch (package name, storedProcedure Name)
	public static String[] storedProcedureDistraintCustomerSearch ={"BankDB.PKG_HACIZARA_OZLUK.arastir","BANKDB"};
	
	//Stored procedure accountInvestigationCustomerSearch (package name, storedProcedure Name)
	private String[] storedProcedureAccountInvestigationCustomerSearch ={"BankDB.PKG_HESARA_OZLUK.arastir","BANKDB"};
	
	//Query to retrieve PDF file
	private final String _queryPDF = "select surecid, filedata from verisoft.hesara_kredikartiextreler where surecid =?";
	
	@org.osoa.sca.annotations.Init
	public void init() {
		//Set the default locale needed for some stored procedures, otherwise DATES are wrongly parsed and oracle error is returned "ORA-01843: not a valid month".
	
		Locale.setDefault(Locale.forLanguageTag(getDefaultLocale()));
	}


	public synchronized void setResult(int storedProcedureIndex, SPResult result){

		//System.out.println("setResult: "+storedProcedureIndex+"-"+result);
		if (storedProcedureIndex> -1 && storedProcedureIndex< results.length) 
			results[storedProcedureIndex] = result;
		else
			logger.error("Wrong result index:"+storedProcedureIndex+"/"+results.length);
	}



	/**
	 * Implementation of the WSDL operation: doAccountInvestigation	 */
	public DoAccountInvestigationResponse doAccountInvestigation(
			DoAccountInvestigation parameters) {
		//PDF
		Boolean createPDF = false;
		String inputValue = parameters.getREFERENCEID();
		String clientSystem=parameters.getClientSystem();		
		boolean createExcelForClient=false;
		if(clientSystem==null || !clientSystem.equalsIgnoreCase(DISTRAINT_CLIENT)){
			createExcelForClient=false;
			logger.info("Excel creation has been disabled for the reference number..:"+inputValue+" ");
		}else{
			createExcelForClient=true;
		}
		
		logger.info("Received AccountInvestigation request from the client..:"+clientSystem+" for REFERENCEID:"+inputValue);

		//Filter procedure list based on reference number
		Integer[] procedureIds;
		try {
			procedureIds = getProcedureIds(inputValue);
		} catch (SQLException e1) {
			//stop execution and keep retry on EMS. Will retry a max number of times defined by policy set on component service.
			throw new RuntimeException(e1.getMessage());
		}
		if(procedureIds.length == 0)
			logger.warn("No storedprocedure to be invoked for REFERENCEID:"+inputValue);

		
		//Start Threads and execute queries
		CountDownLatch latch = new CountDownLatch(procedureIds.length);
		ThreadPoolExecutor tpe = getWorkersThreadPool();

		createPDF = false;
		for (int i=0; i<procedureIds.length;i++){

			String storedProcedure= storedProcedures[procedureIds[i]][1];
			
			//PDF
			if(storedProcedure.equals("VERISOFT.SP_HESARA_KREDIKARTIEXTRELER")){
				createPDF = true;
			}
			
			DataSource ds = null;
			String schemaName=null;
			int dsid = Integer.parseInt(storedProcedures[procedureIds[i]][2]);
			switch (dsid){
			case 2: 
				schemaName="FINANS";
				ds = getJDBCDataSource2();
				logger.debug("Accountinvestigation for REFERENCEID:"+inputValue+". Using connection pool#2:"+schemaName);
				break;
			case 3:
				schemaName="VERISOFTATM";
				ds = getJDBCDataSource3();
				logger.debug("Accountinvestigation for REFERENCEID:"+inputValue+". Using connection pool#3:"+schemaName);
				break;
			case 4:
				schemaName="VERISOFT";
				ds = getJDBCDataSource_VERISOFT2();
				logger.debug("Accountinvestigation for REFERENCEID:"+inputValue+". Using connection pool#3:"+schemaName);
				break;
			default:
				schemaName="BANKDB";
				ds = getJDBCDataSource();
				logger.debug("Accountinvestigation for REFERENCEID:"+inputValue+". Using connection pool#1:"+schemaName);		
			}

			WorkerThread worker = new WorkerThread (storedProcedure, schemaName, procedureIds[i], inputValue,ds ,latch, this);
			tpe.execute(worker);
		}

		
		//Wait for results
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		//Compute output result
		ObjectFactory of = new ObjectFactory();
		DoAccountInvestigationResponse out = of.createDoAccountInvestigationResponse();
		for (int j=0; j<procedureIds.length; j++){
			int i = procedureIds[j];
			logger.debug("SPResult ["+storedProcedures[i][1]+","+results[i]+"]");
			if (results[i] != null && results[i].PRETURNSTATUS != 0){
				logger.error("Accountinvestigation for REFERENCEID:"+inputValue+". Error reported calling stored procedure:"+storedProcedures[i][1]+" Error:"+results[i]);
				throw new RuntimeException(results[i].toString());
			}
		}


		 
				if (getCreateExcelFile() && createExcelForClient){
					
					//Create Excel File from matched tables
					 int  retryCount=3; 
					 long retryWaitTime=2000; //milisecond
					
					HSSFWorkbook excelFile=null;
					while(retryCount>0){
						logger.info("Creating Excel file for REFERENCEID:"+inputValue);
						try {
							excelFile=extractResultsaAndCreateExcelFile(inputValue,procedureIds);
							logger.info("Done creating Excel Workbook for REFERENCEID:"+inputValue);
							retryCount=0; // don't retry
						} catch (IOException e) {
							logger.error("Accountinvestigation for REFERENCEID:"+inputValue+". Error reported creating Excel Workbook Error Details:"+e.getMessage());
							e.printStackTrace();
							retryCount--;
							if(retryCount>0){
							try {
								  Thread.sleep(retryWaitTime);
								  logger.info("Accountinvestigation for REFERENCEID:"+inputValue+". Retrying  creating Excel Workbook...");
								} catch (InterruptedException ignored) {}
							}else{
								throw new RuntimeException(e);
							}
							
						} catch (SQLException e) {
							logger.error("Accountinvestigation for REFERENCEID:"+inputValue+". Error reported extracting results from result tables, Error:"+e.getMessage());
							e.printStackTrace();			
							retryCount=0; // don't retry
							//the service should be stopped with halted state
							throw new RuntimeException(e);
						}
					}
							
			        // begin Copying of the excel file to the all destinations
				    List<File> destPaths=getDestinationDirectories(inputValue);					
					for(int i=0;i<destPaths.size();i++){
						retryCount=3; 
						retryWaitTime=3000; //milisecond 
						
						while(retryCount>0){
						FileOutputStream  fos=null;	
						try{
							if(i==0){
						   	   File file=new File(destPaths.get(0),inputValue + ".xls");
			           		   fos=new FileOutputStream(file);
							   excelFile.write(fos);
							   fos.close();
							   fos=null;
							   logger.info("the file.:"+inputValue + ".xls ("+file.toPath()+") has been created"); 
							}else{
							   File sourcFile=new File(destPaths.get(0),inputValue + ".xls");	
							   File destFile= new File(destPaths.get(i),inputValue + ".xls");
							   Files.copy(sourcFile.toPath(), destFile.toPath(),REPLACE_EXISTING);
							   logger.info("The file.:"+inputValue+".xls has been copied"+ " ( from..:"+sourcFile.toPath()+" to..:"+destFile.toPath());
							   
							}
						 retryCount=0;
						}catch(Exception e){
							
							logger.error("Error when writng the excel file for the ref:"+inputValue+" Error details..:"+e.getMessage());
							retryCount--;
							if(retryCount>0){
							try { e.printStackTrace();
								  Thread.sleep(retryWaitTime);
								  logger.info("Retrying the writng the excel file for the ref"+inputValue);
								} catch (InterruptedException ignored) {}
							}else{
								//the service should be stopped with halted state
								 throw new RuntimeException(e);
							}
							
						}finally{
							if(fos!=null){
								try {
									fos.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					  }	// end of the while(retryCount>0)				  
				   }// end of the for of destination folder list
					
				// end Copying of the excel file to the all destinations
					
				
	 }  // End of create Excel  file

		//Create PDF
		if(createPDF){
			extractPdfFile(inputValue);
			
		}
		
		logger.info("Successfully end of AccountInvestigation execution for REFERENCEID:"+inputValue);
		out.setReturnCode(1);
		if(clientSystem!=null && clientSystem.equalsIgnoreCase(DISTRAINT_CLIENT)){
			out.setOutputPath(pathOutput);
		}else{
			out.setOutputPath(null);
		}
		logger.info("output value for Ref..:"+inputValue+" is..:"+out.getOutputPath());
		
		return out;
	}


	private List<File> getDestinationDirectories(String ref){
		List<File> directoryList=new ArrayList<File>();

		try {
			
		    Date date=new Date(); 
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    String year = cal.get(Calendar.YEAR)+"";
		    String month = (cal.get(Calendar.MONTH)+1)+"";
		    String day = cal.get(Calendar.DAY_OF_MONTH)+"";
		    
		    if(day.length()==1){
		    	day="0"+day;
		    }
		    if(month.length()==1){
		    	month="0"+month;
		    }
	
		    
		    List<String> rootList=new ArrayList<String>();
		    rootList.add(getExcelFileFolder());
		    rootList.add(getExcelFileFolder2());
		    String dayLabel="";
		    for(String root:rootList){
		    	
		    	 File yearParentDirectory=new File(root,year+"");
		    	 if(!yearParentDirectory.exists()){
				    	yearParentDirectory.mkdir();
				    }
		    	 
		    	 
		    	  dayLabel=year+""+month+""+day;
				    File dayParentDirectory=new File(yearParentDirectory,dayLabel);
				    
				    if(!dayParentDirectory.exists()){
				    	dayParentDirectory.mkdir(); 
				    }
				    
				 File directory = new File(dayParentDirectory, ref);
					if (!directory.exists() || directory.isFile()) {
						directory.mkdir();
					}
					
					
					directoryList.add(directory);
		
	     	    }
			
		    pathOutput="\\"+year+"\\"+dayLabel+"\\"+ref;

		} catch (Exception e) {
			
			logger.error("Error when creating destination folders for the ref:"+ref+" Error details..:"+e.getMessage());
			e.printStackTrace();
			
			// the service should be stopped with halted state
			throw new RuntimeException(e);

		}
		
      return directoryList;
		
	}
	private HSSFWorkbook extractResultsaAndCreateExcelFile(String referenceID, Integer[] procedureIds) throws IOException, SQLException {

		//Extract results from result tables
		Results results = getResults(referenceID, procedureIds);
		
		//Create Excel file
		ExcelExporter ee = new ExcelExporter();
	
		return ee.createExcelFileFromResults(results);

	}	
	
	
	
	//Create a Results object for the invoked stored procedures
	public Results getResults(String referenceID, Integer[] procedureIds) throws SQLException {
		logger.debug("Extracting results for REFERENCEID:"+referenceID);
		Results out = new Results();
		out.setReferenceID(referenceID);

		//create one result for each procedure
		for(Integer i: procedureIds){

			//Select the datasource and schema
			DataSource ds = null;
			String schemaName=null;
			int dsid = Integer.parseInt(storedProcedures[i][2]);
			switch (dsid){
			case 2: 
				schemaName="FINANS";
				ds = getJDBCDataSource2();
				
				break;
			case 3:
				schemaName="VERISOFTATM";
				ds = getJDBCDataSource3();
				break;
			case 4:
				schemaName="VERISOFT";
				ds = getJDBCDataSource_VERISOFT2();
				break;
			default:
				schemaName="BANKDB";
				ds = getJDBCDataSource();
			}
			logger.debug("Using datasource and schema:"+schemaName);

			//For each table extract result
			String[] tables = getTableNames(i);
			for (String table:tables){
				//Create one result per table
				Result result = new Result();
				result.setTableName(table);
				out.appendResult(result);

				//Query table and create rows
				fillResultWithRows(referenceID, result, table, ds, schemaName); 
			}
		}
		return out;
	}	

	private void fillResultWithRows(String referenceNumber, Result result, String table, DataSource ds,
			String schemaName) throws SQLException {
		Connection con = null;
		try{
		logger.debug("Filling results for table:"+table);
		con = ds.getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM "+table+" WHERE SURECID=?");
		st.setString(1, referenceNumber);
		ResultSet rs = st.executeQuery();

		//Create Headers
		ResultSetMetaData metaData = rs.getMetaData();
		int columns = metaData.getColumnCount();
		for (int i=1;i<= columns;i++){
			if(logger.isDebugEnabled())
				logger.debug("Header name:"+metaData.getColumnName(i));
			result.appendHeaderValue(metaData.getColumnName(i));
			}
		
		List<String> excludeList=headerExcludeList.get(table);

		//Create Row
		while(rs.next()){
			Row row = new Row();
			String columnName="";
			for (int i=1;i<=columns;i++){
				  columnName=result.getHeaderValues()[i-1];
				  if(excludeList!=null && excludeList.contains(columnName)){
					 row.appendValue(""); 
				  }else{
					 row.appendValue(rs.getString(i)); 
				  }				  
				}
			if(logger.isDebugEnabled())
				logger.debug("Created new row:"+row.getValues());
			result.appendRow(row);
		}
		}catch (SQLException e) {
			// TODO: handle exception
			throw e;
		}finally{
			if (con != null){
				try {
					con.close();
				} catch (Exception ignored){}
			}
		}
	}

	
	//Extract list of table names for the stored procedure with index indx
	public String[] getTableNames(Integer indx) {
		logger.debug("Extracting table names for :"+storedProcedures[indx][3]);
		ArrayList<String> names = new ArrayList<String>();
		String tables = storedProcedures[indx][3];
		StringTokenizer st = new StringTokenizer(tables,",",false);
		while (st.hasMoreTokens()){
			names.add(st.nextToken());
			}
		if (logger.isDebugEnabled())
			logger.debug("Names:"+ StringHelper.prettyPrinter(names.toArray(new String[0])));
		return names.toArray(new String[0]);
	}


	private Integer[] getProcedureIds(String inputValue) throws SQLException {
		Connection con = null;
		Hashtable<String, String> blocks = new Hashtable<String,String>();
		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
			con = getJDBCDataSource().getConnection();
			PreparedStatement ps = con.prepareStatement(_queryProcedureList);
			ps.setBigDecimal(1, new BigDecimal(inputValue));
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String block = rs.getString("URUNKODU");
				blocks.put(block, "");
				logger.debug("Accountinvestigation for REFERENCEID:"+inputValue+". Block found:"+ block);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Accountinvestigation for REFERENCEID:"+inputValue+". Error reported executing :"+_queryProcedureList+" Error:"+e.getMessage());
			e.printStackTrace();
			throw e;
		} finally{
			if (con != null){
				try {
					con.close();
				} catch (Exception ignored){}
			}
		}

		for (int i=0; i<storedProcedures.length;i++){
			if (blocks.get(storedProcedures[i][0])!=null){
				logger.debug("Accountinvestigation for REFERENCEID:"+inputValue+". Procedure to be invoked:"+storedProcedures[i][1]);
				list.add(i);
			}
		}
		return list.toArray(new Integer[0]);
	}

	


	@Override
	public DistraintCustomerSearchOutput distraintCustomerSearch(
			DistraintCustomerSearchInput distraintCustomerSearchRequest) {
		logger.debug("Start distraintCustomerSearch service");
		logger.debug("REFERENCEID:"+distraintCustomerSearchRequest.getProcessId());
		DistraintCustomerSearchOutput response = null;
		
		//Start Threads and execute queries
		CountDownLatch latch = new CountDownLatch(1);
		ThreadPoolExecutor tpe = getWorkersThreadPool();
		
		DataSource ds = getJDBCDataSourceDistraintCustomerSearch();
		
		String ProcessId = distraintCustomerSearchRequest.getProcessId();
		
		WorkerThread worker = new WorkerThread (storedProcedureDistraintCustomerSearch[0], storedProcedureDistraintCustomerSearch[1], 0, ProcessId,ds ,latch, this);
		tpe.execute(worker);
		
		//Wait for results
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// create response
		com.ingbank.credit.bpm.datamodel.customersearchv1.ObjectFactory of = new com.ingbank.credit.bpm.datamodel.customersearchv1.ObjectFactory();
		response =of.createDistraintCustomerSearchOutput();
		
		
		
		if(results.length >0 && results[0]!= null){
			response.setCode(""+results[0].getPRETURNCODE());
			response.setMessage(""+results[0].getPADDITIONALINFO());
			if(results[0].getPRETURNSTATUS() == 0){
				response.setSuccess(true);
				response.setCode(((SPResultCustomerSearchDistraint)results[0]).getIsCustomerFound()+"");
			}
				
			else
				throw new RuntimeException(results[0].toString());
		}
		else{
			logger.error("Error during the stored procedure call");
			response.setCode("99");
			response.setMessage("Error during the stored procedure:"+storedProcedureDistraintCustomerSearch[0]+", check the log file");
			response.setSuccess(false);
			throw new RuntimeException(results[0].toString());
		}
		logger.debug("RESPONSE - isSuccess:"+response.isSuccess()+" code:"+ response.getCode()+" Message:"+response.getMessage());
		logger.debug("End distraintCustomerSearch service");
		return response;
	}


	@Override
	public AccountInvestigationCustomerSearchOutput accountInvestigationCustomerSearch(
			AccountInvestigationCustomerSearchInput accountInvestigationCustomerSearchRequest) {
		logger.debug("Start accountInvestigationCustomerSearch service");
		logger.debug("REFERENCEID:"+accountInvestigationCustomerSearchRequest.getProcessId());
		AccountInvestigationCustomerSearchOutput response = null;
		
		//Start Threads and execute queries
		CountDownLatch latch = new CountDownLatch(1);
		ThreadPoolExecutor tpe = getWorkersThreadPool();
		
		DataSource ds = getJDBCDataSourceAccountInvestigationCustomerSearch();
		
		String ProcessId = accountInvestigationCustomerSearchRequest.getProcessId();
		
		WorkerThread worker = new WorkerThread (storedProcedureAccountInvestigationCustomerSearch[0], storedProcedureAccountInvestigationCustomerSearch[1], 0, ProcessId,ds ,latch, this);
		tpe.execute(worker);
		
		//Wait for results
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// create response
		com.ingbank.credit.bpm.datamodel.customersearchv1.ObjectFactory of = new com.ingbank.credit.bpm.datamodel.customersearchv1.ObjectFactory();
		response =of.createAccountInvestigationCustomerSearchOutput();
		
		
		
		if(results.length >0 && results[0]!= null){
			response.setCode(""+results[0].getPRETURNCODE());
			response.setMessage(""+results[0].getPADDITIONALINFO());
			if(results[0].getPRETURNSTATUS() == 0)
				response.setSuccess(true);
			else
				throw new RuntimeException(results[0].toString());
		}
		else{
			logger.error("Error during the stored procedure call");
			response.setCode("99");
			response.setMessage("Error during the stored procedure: "+storedProcedureAccountInvestigationCustomerSearch[0]+", check the log file");
			response.setSuccess(false);
			throw new RuntimeException(results[0].toString());
		}
		logger.debug("RESPONSE - isSuccess:"+response.isSuccess()+" code:"+ response.getCode()+" Message:"+response.getMessage());
		logger.debug("End accountInvestigationCustomerSearch service");
		return response;
	}
	
	//Create PDF
	protected void extractPdfFile(String referenceID) {
		Connection conn = null;
		InputStream inStream = null;
		File sourceFile = null;
		FileOutputStream fos = null;
		List<String> toBeCopiedFiles=new ArrayList<String>();
		try{
			DataSource ds =  getJDBCDataSource_VERISOFT2();
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(_queryPDF);
			stmt.setBigDecimal(1, BigDecimal.valueOf(Long.parseLong(referenceID)));
			stmt.execute();
			
			ResultSet rs =stmt.getResultSet();
			int count =0;
			
			int retryCount=3; 
			int retryWaitTime=3000; //milisecond 
			
			List<File> destinationList=null;
			
			while(rs.next()){
				Blob b = rs.getBlob("filedata");
			
				destinationList=getDestinationDirectories(referenceID);
				inStream = b.getBinaryStream();
				String fileName = referenceID;
				
				if(count >0){
					fileName =fileName+"_"+count+".pdf";
				}else{
					fileName=fileName+".pdf";
				}
			  retryCount=3;	
			  while(retryCount>0){
				try{				
					sourceFile = new File(destinationList.get(0),fileName);
					fos =  new FileOutputStream(sourceFile);
				
					byte[] buffer = new byte[1024];
					int length;
					while ((length = inStream.read(buffer)) > 0) {
						fos.write(buffer, 0, length);
					}
					logger.info("the file..:"+fileName+" has been created on the path..:"+sourceFile.toPath() );
					toBeCopiedFiles.add(fileName);
					retryCount=0;
				}
				catch (IOException e){
					logger.error("Error when writing the pdf file. refNo:"+referenceID+" Error details..:"+e.getMessage()+" destinationPath.:"+destinationList.get(0).toPath().toString()+" fileName.:"+fileName);
					retryCount--;
					if(retryCount>0){
					try { e.printStackTrace();
						  Thread.sleep(retryWaitTime);
						  logger.info("Retrying the writing the pdf file.  refNo:"+referenceID+" destinationPath.:"+destinationList.get(0).toPath().toString()+" fileName.:"+fileName);
						} catch (InterruptedException ignored) {}
					}else{
						//the service should be stopped with halted state
						 throw new RuntimeException(e);
					}
					
				}
				finally{
					if(fos!=null){
						  try {
							  fos.close();
						  } catch (IOException e) {
							  // TODO Auto-generated catch block
							  e.printStackTrace();
						  }
					  }
					  if(inStream != null){
						  try {
							inStream.close();
						  } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						  }
					  }
				}
				
				}// end of the while(retryCount>0){
				
				
				count++;
			} // end of the  while(rs.next()){
			
			
		   // begin Copying of the pdf files to the other destinations
		  
			if(destinationList!=null && destinationList.size()>1){
				for(int i=1;i<destinationList.size();i++){
					
					for(String fileName:toBeCopiedFiles){
						 retryCount=3; 
						 retryWaitTime=5000; //milisecond 
						
					  while(retryCount>0){
						try{
							File sourceFile1=new File(destinationList.get(0), fileName);
							File destFile=new File(destinationList.get(i),fileName);
							Files.copy(sourceFile1.toPath(), destFile.toPath(), REPLACE_EXISTING);
							logger.info("The file.:"+fileName+" has been copied"+ " ( from..:"+sourceFile1.toPath()+" to..:"+destFile.toPath());
							retryCount=0;
						}catch (Exception e) {
							logger.error("Error when writing the pdf file. refNo:"+referenceID+" Error details..:"+e.getMessage()+" destinationPath.:"+destinationList.get(i).toPath().toString()+" fileName.:"+fileName);
							retryCount--;
							if(retryCount>0){
							try { e.printStackTrace();
								  Thread.sleep(retryWaitTime);
								  logger.info("Retrying the writing the pdf file.  refNo:"+referenceID+" destinationPath.:"+destinationList.get(i).toPath().toString()+" fileName.:"+fileName);
								} catch (InterruptedException ignored) {}
							}else{
								//the service should be stopped with halted state
								 throw new RuntimeException(e);
							}
						} //end of the catch (Exception e) {
					  }// end of the while(retryCount>0){
					}// end of the (String fileName:toBeCopiedFiles){
				}
				   
				  
			} // end  of Copying of the pdf files to the other destinations
			
		}

		catch (Exception e){
			  logger.error("Error executing PDF Creation of Account Investigation refNo:"+referenceID+ "The message..:"+e.getMessage());
			  e.printStackTrace();
			  throw new RuntimeException(e);
		  }
		  finally {
			  
			  if (conn != null){
				  try {
					conn.close();
				} catch (SQLException ignored) {}
			  }
			  
		  }
	}

}



