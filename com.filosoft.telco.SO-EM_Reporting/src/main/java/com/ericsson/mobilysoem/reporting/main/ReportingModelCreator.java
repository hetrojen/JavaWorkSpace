package com.ericsson.mobilysoem.reporting.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ericsson.mobilysoem.reporting.csv.CSVFacade;
import com.ericsson.mobilysoem.reporting.model.ConfigDataMiniLinkTnGeneric;
import com.ericsson.mobilysoem.reporting.model.ConfigDataMiniLinkTnMMU2BC;
import com.ericsson.mobilysoem.reporting.model.ConfigDataMiniLinkTnOSPF;
import com.ericsson.mobilysoem.reporting.model.HopDetailReportModel;
import com.ericsson.mobilysoem.reporting.model.HwModuleInventory;
import com.ericsson.mobilysoem.reporting.model.HwSwInventoryReportModel;
import com.ericsson.mobilysoem.reporting.model.SwModuleInventory;

public class ReportingModelCreator {
	public static final Logger logger = Logger.getLogger(ReportingModelCreator.class);
	
public static List<HopDetailReportModel> getHopDetailReportModels() throws Exception{
	List<HopDetailReportModel>  list=new ArrayList<HopDetailReportModel>();
	try{
	
	
	   /* logic A:
	   This logic finds "AMM Type" and "NPU type"  for each NEID in ConfigDataMiniLinkTnGenerics
	     AMM Type rule: Find rows in HwModuleInventories of which NE_ID are equal to NEID of ConfigDataMiniLinkTnGenerics
	     For  that rows  find the row  of which ID  contains "_B"  then set  AMMType  to TypeOfUnit of this row. 
		
	   NPU Type rule: Find rows in HwModuleInventories of which NE_ID are equal to NEID of ConfigDataMiniLinkTnGenerics
		   For  that rows  find the row  of which ID  contains "_N"  then set  NPUType  to TypeOfUnit of this row.
		 If there are more than one row that contains "_N" use the which have max AmmPosition Number
		
	
		*/
	   for(ConfigDataMiniLinkTnGeneric generic: CSVFacade.getConfigDataMiniLinkTnGenerics()){
		    		  
	    		  int max=0; boolean isSettedN=false, isSettedB=false;		    		  
	    		  
	 	    	   for(HwModuleInventory hwModuleInventory:CSVFacade.getHwModuleInventories()){
	 		    	   if(generic.getNeid()==hwModuleInventory.getNe_id()){
	 		    		   
	 		    		   if(hwModuleInventory.getId().contains("_B")){
	 		    			   generic.set_ammType(hwModuleInventory.getTypeOfUnit());
	 		    			   isSettedB=true;
	 		    		       continue;
	 		    		   }			 		    		  
	 		    		   if(hwModuleInventory.getId().contains("_N")){
	 		    			    if(hwModuleInventory.getAmmPositionNumber()>max){
	 		    				   max=hwModuleInventory.getAmmPositionNumber();
	 		    				  generic.set_mpuType(hwModuleInventory.getTypeOfUnit());
	 		    			     }
	 		    			}
	 		    		   
	 		    		  isSettedN=true;
	 		    	   }
	 		    	  if(generic.getNeid()!=hwModuleInventory.getNe_id() && isSettedN && isSettedB){
	 		    	   
	 		    	   break;
	 		         }
	 		    	}
		
	   } // end of logic A
	
	
	
	
	     for(ConfigDataMiniLinkTnMMU2BC configDataMiniLinkTnMMU2BC:CSVFacade.getConfigDataMiniLinkTnMMU2BCs()){
	    	 
	    	 HopDetailReportModel model = new HopDetailReportModel();
	    	 
	    	
	    	 
	    	 /*  logic B
	    	  This logic for generating  SiteB field in  to be generated report file Sheet "Hop Details".
	    	  for all rows of  ConfigDataMiniLinkTnMMU2BC try to find corresponding row  from  ConfigDataMiniLinkTnMMU2BC 
	    	  and use NEAlias of this row as a SiteB
	    	  The row rule of finding the row is below in if statement;
	    	      
	    	
	    	 
	    	 SQL equivalent of this logic: (we didn't use any database so we couldn't use SQL, it is just for information )

             select   a.*  ,b.Terminal_ID,b.NEAlias as SiteB ,
                b.type, b.Base_TX_Frequency_RF1, b.Base_RX_Frequency_RF1, b.Current_Output_Power_RF1
             from  test.configdataminilinktnmmu2bc   AS a LEFT OUTER JOIN  test.configdataminilinktnmmu2bc as b  on 
              (   (a.Far_End_ID=b.Terminal_ID )  and    (a.Base_TX_Frequency_RF1=b.Base_RX_Frequency_RF1) and 
               ( b.Base_TX_Frequency_RF1=a.Base_RX_Frequency_RF1  ) 
             and  ( a.type =b.type)   and   ( (a.Current_Output_Power_RF1 = -99  or b.Current_Output_Power_RF1=-99  )
             or (a.Current_Output_Power_RF1 =  b.Current_Output_Power_RF1)) )  Order by a.Far_End_ID, a.NEID, b.NEID
	    	
	    	  */
	    	 
	    	 for(ConfigDataMiniLinkTnMMU2BC linkTnMMU2BCTar:CSVFacade.getConfigDataMiniLinkTnMMU2BCs()){
	    		 
	    		    if( configDataMiniLinkTnMMU2BC.getFar_End_ID().equals(linkTnMMU2BCTar.getTerminal_ID()) &&
	    		    	configDataMiniLinkTnMMU2BC.getBase_TX_Frequency_RF1().equals(linkTnMMU2BCTar.getBase_RX_Frequency_RF1()) &&
	    		    	configDataMiniLinkTnMMU2BC.getBase_RX_Frequency_RF1().equals(linkTnMMU2BCTar.getBase_TX_Frequency_RF1()) && 
	    		    	configDataMiniLinkTnMMU2BC.getType().equals(linkTnMMU2BCTar.getType()) && 
                        ( (configDataMiniLinkTnMMU2BC.getCurrent_Output_Power_RF1().equals("-99") || linkTnMMU2BCTar.getCurrent_Output_Power_RF1().equals("-99") )
                        		|| 	(configDataMiniLinkTnMMU2BC.getCurrent_Output_Power_RF1().equals(linkTnMMU2BCTar.getCurrent_Output_Power_RF1())))  ){	    		        
	    		    	
	    		    	
	    		    	configDataMiniLinkTnMMU2BC.set_siteB(linkTnMMU2BCTar.getNeAlias());
	    		        break;
	    	      }
	    	 
	    	 }
	    		    model.setConfigDataMiniLinkTnMMU2BC(configDataMiniLinkTnMMU2BC);
	    		    
	    	// End of Logic B	    
	    		    
	    	    /* Logic C:   find the row from ConfigDataMiniLinkTnGenerics for all rows in ConfigDataMiniLinkTnMMU2BC.
	            The  rule is ConfigDataMiniLinkTnMMU2BC.neid=ConfigDataMiniLinkTnGenerics.neid
	    		
	    		 */
	    	   for(ConfigDataMiniLinkTnGeneric generic: CSVFacade.getConfigDataMiniLinkTnGenerics()){
	    		   
	    		   if(configDataMiniLinkTnMMU2BC.getNeid()==generic.getNeid()){
			    		  model.setConfigDataMiniLinkTnGeneric(generic);
			    	
	    		      break;
	    	      }
	    	   }
	    	 //  end of Logic C
	    	   
	    	   
	    	   
	    	    /* Logic D:   find the row from ConfigDataMiniLinkTnOSPF for all rows in ConfigDataMiniLinkTnMMU2BC.
	            The  rule is ConfigDataMiniLinkTnMMU2BC.neid=ConfigDataMiniLinkTnOSPF.neid
	    		
	    		 */
	    	   for(ConfigDataMiniLinkTnOSPF dataMiniLinkTnOSPF:CSVFacade.getConfigDataMiniLinkTnOSPFs()){
			    	  if(configDataMiniLinkTnMMU2BC.getNeid()==dataMiniLinkTnOSPF.getNeid()){
			    		  model.setConfigDataMiniLinkTnOSPF(dataMiniLinkTnOSPF);
			    		  break;
			    	  }
			     }
	    	   //  end of Logic D
	    	   
	    	  list.add(model);
	     }
	
	
	     
	
	
	
	
	
	
	
	}catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	
	
	
     
	return list;
}
public static List<HwSwInventoryReportModel> getHwSwInventoryReportModel() throws Exception{
	List<HwSwInventoryReportModel> list=new ArrayList<HwSwInventoryReportModel>();
	 
	try{
	             for(HwModuleInventory  hwModuleInventory:CSVFacade.getHwModuleInventories()){
	            	 HwSwInventoryReportModel model=new HwSwInventoryReportModel();
	            	 model.setHwModuleInventory(hwModuleInventory);
	           
	            	 
	            	  /* Logic E:   find the row from SwModuleInventory for all rows in HwModuleInventory
	 	            The  rule is SwModuleInventory.Hw_Module_ID=HwModuleInventory.id
	 	            */
	            	 for(SwModuleInventory swModuleInventory:CSVFacade.getSwModuleInventories()){
	            	
	            		 if(hwModuleInventory.getId().equals(swModuleInventory.getHw_Module_ID())){
	            			 model.setSwModuleInventory(swModuleInventory);
	            			 break;
	            		 }
	            	 }// end of Logic E
	            
	            	 
	            	 /*logic F
	            	  find the row from ConfigDataMiniLinkTnGeneric for all rows in HwModuleInventory
	 	            The  rule is ConfigDataMiniLinkTnGeneric.neid=HwModuleInventory.ne_id
	            	  
	            	  
	            	  */
	            	 	 for( ConfigDataMiniLinkTnGeneric generic: CSVFacade.getConfigDataMiniLinkTnGenerics()){
		           
	            	 		 if(hwModuleInventory.getNe_id()==generic.getNeid()){
		            			 model.setConfigDataMiniLinkTnGeneric(generic);
		            		     break;
		            		 }
		            	 }// End of Logic F
	            	 	

	            	 list.add(model);
	             }
	
	}catch(Exception e){
		throw e;
	}
	             
	return list;
}
}
