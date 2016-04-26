package com.tibco.asyncaccountinvestigation;

import java.math.BigDecimal;
import java.sql.*;
import java.util.concurrent.CountDownLatch;
import oracle.jdbc.OracleTypes;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerThread implements Runnable {

	private String storedProcedureName;
	  private int storedProcedureIndex;
	  private String storedProcedureParameter,schemaName; 
	  private DataSource ds; 
	  private CountDownLatch latch;
	  private QueryAccountInvestigation queryAccountInvestigation;
	  

	  public WorkerThread(String storedProcedureName, String schemaName, int storedProcedureIndex, String storedProcedureParameter, DataSource ds, CountDownLatch latch, QueryAccountInvestigation queryAccountInvestigation){
	      super();
		  this.storedProcedureName=storedProcedureName;
	      this.schemaName=schemaName;
	      this.storedProcedureIndex=storedProcedureIndex;
	      this.storedProcedureParameter=storedProcedureParameter;
	      this.ds=ds;
	      this.latch=latch;
	      this.queryAccountInvestigation=queryAccountInvestigation;
	  }

	  @Override
	  public void run() {
	     logger.info(Thread.currentThread().getName()+" START ["+storedProcedureName+","+storedProcedureParameter+"]");
	      //Execute stored procedure
	      SPResult result = processStoredProcedure();
	      //store with result
	      queryAccountInvestigation.setResult(storedProcedureIndex, result);
	      
	      //countdown latch
	      latch.countDown();
	      logger.info(Thread.currentThread().getName()+" END ["+storedProcedureName+","+storedProcedureParameter+","+result.PRETURNSTATUS+"]");
	      
	  }

	  private SPResult processStoredProcedure() {
		  Connection conn = null;
		  CallableStatement cstmt=null; 
		  try {
			  cstmt=null;
			  conn = ds.getConnection();
			 
			  if(storedProcedureName.equalsIgnoreCase(QueryAccountInvestigation.storedProcedureDistraintCustomerSearch[0])){
				  cstmt = conn.prepareCall ("{call "+storedProcedureName+" (?,?,?)}");
				  cstmt.registerOutParameter (2, OracleTypes.JAVA_STRUCT, schemaName+".TYPE_RETURN_HEADER");
				  cstmt.registerOutParameter (3, Types.INTEGER);
			  }else{
				  cstmt = conn.prepareCall ("{call "+storedProcedureName+" (?,?)}");
				  cstmt.registerOutParameter (2, OracleTypes.JAVA_STRUCT, schemaName+".TYPE_RETURN_HEADER");
			  }
			  cstmt.setBigDecimal(1, BigDecimal.valueOf(Long.parseLong(storedProcedureParameter)));
			  cstmt.execute ();
			  Struct result = (Struct) cstmt.getObject(2);
			 
			
			  
			  int returnCode=0;
			  String additionalInfo="";
			  
			  int returnStatus = ((BigDecimal)(result.getAttributes()[0])).intValue();				  
			  if(returnStatus!=0 && result.getAttributes()[1]!=null){
				returnCode = ((BigDecimal)(result.getAttributes()[1])).intValue(); //Business Exception [if return code not equal 0 ]
			  }		      
			  
			  logger.info(Thread.currentThread().getName()+"  ["+storedProcedureName+" has returned with status code..:"+returnStatus+"]");
              if(result.getAttributes()[2]!=null && (result.getAttributes()[2]) instanceof String ){
            	   additionalInfo = ((result.getAttributes()[2])).toString();  
            	   logger.info("The Additional info for the stored procedure ["+storedProcedureName+"] is.:", additionalInfo);
              }
              
              if(returnCode!=0){
            	  logger.error("Error executing stored procedure: "+storedProcedureName+ " ReturnCode: "+returnCode+"    "+ additionalInfo);
              }
			  //			 
              if(storedProcedureName.equalsIgnoreCase(QueryAccountInvestigation.storedProcedureDistraintCustomerSearch[0])){
				  cstmt.registerOutParameter (3, Types.INTEGER);
				  int isFound=(int) cstmt.getObject(3); 
				  return new SPResultCustomerSearchDistraint(returnStatus, returnCode, additionalInfo,isFound);
			  }
             
			  //PRETURNSTATUS
			  //PRETURNCODE 1 is OK, errorcode otherwise
			  //PADDITIONALINFO
			  //PPARAMS
			  //System.out.println("StoredProcedure ["+storedProcedureName+","+ returnStatus+","+returnCode+","+additionalInfo+"]");
	          return new SPResult(returnStatus, returnCode, additionalInfo);
		  }
		  catch (Exception e){
			  logger.error("Error executing stored procedure ["+storedProcedureName+"] ", e);
			  return new SPResult(-1, 0, storedProcedureName+": "+e.getMessage());
		  }
		  finally {
			  if (conn != null){
				  try {
					conn.close();
				} catch (SQLException ignored) {}
			  }
			  if(cstmt!=null){
				  try{
					  cstmt.close();
				  }catch (SQLException ignored) {}
				  
			  }
		  }
	  }
	  final Logger logger = LoggerFactory.getLogger("com.tibco.asyncaccountinvestigation");
  }

