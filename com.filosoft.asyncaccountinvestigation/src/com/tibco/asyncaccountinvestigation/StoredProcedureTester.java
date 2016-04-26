package com.tibco.asyncaccountinvestigation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

public class StoredProcedureTester {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		String[] storedProcedureNames = {"BankDB.SP_HESARA_VIRMAN"};
		
//		String[] storedProcedureNames = {
//				"BankDB.SP_HESARA_HESAPLAR",
//				"BankDB.SP_HESARA_YATIRIM",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_BLOKE",
//				"OYDB.PK_BKR_HESARA.SP_EK_HESARA",
//				"OYDB.PK_BKR_HESARA.SP_BKR_HESARA",
//				"OYDB.PK_BKR_HESARA.SP_BKR_MASRAF_ARA",
//				"BankDB.SP_HESARA_TICARIKREDI",
//				"BankDB.SP_HESARA_DEBITKARTLARI",
//				"BankDB.SP_HESARA_KREDIKARTLARI", 
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_KIRALIKKASA",
//				"BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_GELENGIDENHAVALE",
//				"BankDB.SP_HESARA_GIDENEFT",
//				"BankDB.SP_HESARA_GELENEFT", 
//				"BankDB.SP_HESARA_GELENHAVALE",
//				"BankDB.SP_HESARA_GIDENHAVALE",
//				"BankDB.SP_HESARA_VIRMAN",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_HESAPHAREKETLERI",
//				"ATMDB.SP_HESARA_DEBITKARTHAREKETLERI",
//				"BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_WESTERNUNION",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_NAKITYATAN",
//				"BANKDB.SP_HESARA_NAKITYATAN_GRKL",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_NAKITCEKILEN",
//				"BANKDB.SP_HESARA_NAKITCEKILEN_GRKL",
//				"BankDB.SP_HESARA_TICARIKREDIHAR",
//				"BankDB.SP_HESARA_SENET",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_CEK",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_KASA",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_MEVDUAT",
//				"BankDB.PK_OPRDON_YATIRIMURUNLERI.SP_HESARA_FON",
//				"BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_MENKUL",
//				"BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_HISSESENEDI",
//				"BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_VOB",
//				"BankDB.PK_OPRDON_HAZINEURUNLERI.SP_HESARA_TUREV",
//				"BankDB.SP_HESARA_HESAPSIZHAVALE",
//				"BankDB.SP_HESARA_HESAPSIZEFT",
//				"BankDB.SP_HESARA_HESAPSIZCEK",
//				"BankDB.SP_HESARA_HESAPSIZSENET",
//				"BankDB.SP_HESARA_HESAPSIZPARAYATIR",
//				"Bankdb.sp_hesara_hesapsizpyatir_grkl",
//				"BankDB.SP_HESARA_HESAPSIZPARACEK",
//				"BankDB.sp_hesara_hesapsizpcek_grkl",
//				"BankDB.SP_HESARA_FAIZORAN"
//		};
		String value = "368547";
		//Properties props = new Properties();
//        String url = "jdbc:oracle:thin:@10.104.58.14:4521:ATMTEST";
//        String userName = "OPTRAN";
//        String password = "QQws_3294";

//		String url = "jdbc:oracle:thin:@coretestdb:4521:OYTEST";
//        String userName = "OPTRAN";
//        String password = "QQws_3294";
		
	    String url = "jdbc:oracle:thin:@coretestdb:4521:FSTEST";
        String userName = "OPTRAN";
        String password = "Hd03_23dS1";
		
		//preprod
//		String url = "jdbc:oracle:thin:@10.104.88.16:4301:DBTPRE";
//		String userName = "DILFURUZT";
//		String password = "NEEGJ85u4";
        
//        String url = "jdbc:oracle:thin:@10.104.58.14:4522:krdtest";
//        String userName = "OPTRAN";
//        String password = "ReDs_3251";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
      
        for(String procname:storedProcedureNames){
        
        Properties p = new Properties();
        p.put("user", userName);
        p.put("password", password);
        
        Locale.setDefault(Locale.forLanguageTag("fr-FR"));
        //Locale.setDefault(Locale.forLanguageTag("en-US"));
        
        Connection conn=DriverManager.getConnection(url, p);
        
		  try {
			  CallableStatement cstmt = conn.prepareCall ("{call "+procname+" (?, ?)}");
			  cstmt.registerOutParameter (2, OracleTypes.JAVA_STRUCT, "BANKDB.TYPE_RETURN_HEADER");
			  //cstmt.registerOutParameter (2, OracleTypes.JAVA_STRUCT, "VERISOFTATM.TYPE_RETURN_HEADER");
			  //cstmt.registerOutParameter (2, OracleTypes.JAVA_STRUCT, "TYPE_RETURN_HEADER");
			  
			  
			  cstmt.setBigDecimal(1, BigDecimal.valueOf(Long.parseLong(value)));
			  cstmt.execute ();
			  Struct result = (Struct) cstmt.getObject(2);
			  cstmt.close();
			  conn.close();
			  //PRETURNSTATUS
			  //PRETURNCODE
			  //PADDITIONALINFO
			  //PPARAMS
			System.out.println(">>>>>>>>>>>>OK Additional Info: "+procname+"-"+result.getAttributes()[1].getClass());
			  String additionalInfo="";
			   if(result.getAttributes()[2]!=null && (result.getAttributes()[2]) instanceof String ){
            	   additionalInfo = ((result.getAttributes()[2])).toString();  
            	   System.out.println("The Additional info for the stored procedure ["+procname+"] is.:"+additionalInfo);
              }
			  
			  
		  } catch (Exception e){
			 System.out.println(">>>>>>>>Error: "+procname+" - "+e.getMessage());
			 // e.printStackTrace();
	         
		  }
		  finally {
			  if (conn != null){
				  try {
					conn.close();
				} catch (SQLException ignored) {}
			  }
		  }
		  }
		  
		 
        
        // test pdf
//       String _queryPDF = "select surecid, filedata from verisoft.hesara_kredikartiextreler where surecid =?";
//        Connection conn = null;
//		InputStream inStream = null;
//		File targetFile = null;
//		FileOutputStream fos = null;
//		Properties p = new Properties();
//		 p.put("user", userName);
//	        p.put("password", password);
//			conn=DriverManager.getConnection(url, p);
//			PreparedStatement stmt = conn.prepareStatement(_queryPDF);
//			stmt.setBigDecimal(1, BigDecimal.valueOf(Long.parseLong("1001")));
//			stmt.execute();
//			
//			ResultSet rs =stmt.getResultSet();
//			while(rs.next()){
//			Blob b = rs.getBlob("filedata");
//			
//			
//			String directory = "C:\\Users\\Andrea\\Downloads";
//			 
//			inStream = b.getBinaryStream();
//			targetFile = new File(directory+"/1001.pdf");
//			
//			fos =  new FileOutputStream(targetFile);
//			
//			byte[] buffer = new byte[1024];
//	        int length;
//	        while ((length = inStream.read(buffer)) > 0) {
//	        	fos.write(buffer, 0, length);
//	        }
//			}
//			  
//			  if (conn != null){
//				  try {
//					conn.close();
//				} catch (SQLException ignored) {}
//			  }
//			  if(fos!=null){
//				  try {
//					  fos.close();
//				  } catch (IOException e) {
//					  // TODO Auto-generated catch block
//					  e.printStackTrace();
//				  }
//			  }
//			  if(inStream != null){
//				  try {
//					inStream.close();
//				  } catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				  }
//			  }
		  
	}

}
