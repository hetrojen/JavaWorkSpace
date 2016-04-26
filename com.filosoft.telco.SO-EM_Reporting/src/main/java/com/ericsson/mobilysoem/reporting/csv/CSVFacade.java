package com.ericsson.mobilysoem.reporting.csv;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ericsson.mobilysoem.reporting.model.CSVFileInfo;
import com.ericsson.mobilysoem.reporting.model.ConfigDataMiniLinkTnGeneric;
import com.ericsson.mobilysoem.reporting.model.ConfigDataMiniLinkTnMMU2BC;
import com.ericsson.mobilysoem.reporting.model.ConfigDataMiniLinkTnOSPF;
import com.ericsson.mobilysoem.reporting.model.HwModuleInventory;
import com.ericsson.mobilysoem.reporting.model.SwModuleInventory;

public class CSVFacade {
	public static final Logger logger = Logger.getLogger(CSVFacade.class);
	private static List<HwModuleInventory> hwModuleInventories;
    private static List<SwModuleInventory> swModuleInventories;
    private static List<ConfigDataMiniLinkTnGeneric>  configDataMiniLinkTnGenerics;
    private static List<ConfigDataMiniLinkTnMMU2BC> configDataMiniLinkTnMMU2BCs;
    private static List<ConfigDataMiniLinkTnOSPF> configDataMiniLinkTnOSPFs;
    private static   String daystp="";
    
	public static void loadHwModuleInventory(String filename, Connection connection)throws Exception {
		logger.info("Data is loding for"+filename);
	 ArrayList<HwModuleInventory> list=new  ArrayList<HwModuleInventory>();
	   
	  try {
		Statement stmt = connection.createStatement();
		ResultSet results = stmt.executeQuery("select NE_ID,AMMPosition,ProductNumber,Version,ID,TypeOfUnit From "+filename+" Order by NE_ID");
		while (results.next()) {
			// retrieve and print the values for the current row
		
			int neid = Integer.valueOf(results.getString("NE_ID").trim());
			String ids = results.getString("ID").trim();
			String typeOfUnit = results.getString("TypeOfUnit").trim();
			String ammPosition = results.getString("AMMPosition").trim();
			String productNumber = results.getString("ProductNumber").trim();
			String version = results.getString("Version").trim();
			HwModuleInventory ob = new HwModuleInventory();
			int num=Integer.MAX_VALUE;
			if(ids.contains("_N")){
				if(ammPosition.contains("/")){
					int index=ammPosition.indexOf("/");
				    String numString=ammPosition.substring(index+1);
				    try{
				    	num=Integer.valueOf(numString);
				    }catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			
			ob.setNe_id(neid);
			ob.setId(ids);
			ob.setTypeOfUnit(typeOfUnit);
			ob.setAmmPosition(ammPosition);
			ob.setProductNumber(productNumber);
			ob.setAmmPositionNumber(num);
			ob.setVersion(version);
			list.add(ob);

		}
	  
		hwModuleInventories=list;
	  } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw e;
	}
	  
	}
	public static void loadSwModuleInventory(String filename, Connection connection) throws Exception{
		logger.info("Data is loding for"+filename);
		 ArrayList<SwModuleInventory> list=new  ArrayList<SwModuleInventory>();

		  try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("select ID,MinimumSWVersion,TypeOfSWUnit,SWProductNumber,VersionOfSW,HW_Module_ID From " +filename +" Order by ID");
			while (results.next()) {
				// retrieve and print the values for the current row
			
				String minimumSWVersion = results.getString("MinimumSWVersion").trim().trim();
				String ids = results.getString("ID");
				String typeOfSWUnit = results.getString("TypeOfSWUnit").trim();
				String swproductNumber = results.getString("SWProductNumber").trim();
				String versionOfSW = results.getString("VersionOfSW").trim();
				String hw_Module_ID = results.getString("HW_Module_ID").trim();
				SwModuleInventory ob = new SwModuleInventory();
				
				
				
				ob.setMinimumSWVersion(minimumSWVersion);
				ob.setId(ids);
				ob.setTypeOfSWUnit(typeOfSWUnit);				
				ob.setSwProductNumber(swproductNumber);
				ob.setVersionOfSW(versionOfSW);
				ob.setHw_Module_ID(hw_Module_ID);
				
				list.add(ob);

			}
		  
			swModuleInventories=list;
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public static void loadConfigDataMiniLinkTnGenerics(String filename, Connection connection) throws Exception {
		logger.info("Data is loding for"+filename);
		
		 ArrayList<ConfigDataMiniLinkTnGeneric> list=new  ArrayList<ConfigDataMiniLinkTnGeneric>();

		  try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("Select NEID,Name,DCN_Host_Address,DCN_Subnet_Mask,DCN_Default_Gateway From "+filename+" order by NEID");
			while (results.next()) {
				// retrieve and print the values for the current row
			
				int neid = Integer.valueOf(results.getString("NEID").trim());
				String name = results.getString("Name").trim();
				String dcnHostAddress = results.getString("DCN_Host_Address").trim();
				String dcn_Subnet_Mask = results.getString("DCN_Subnet_Mask").trim();
				String dcn_Default_Gateway = results.getString("DCN_Default_Gateway");
				ConfigDataMiniLinkTnGeneric ob = new ConfigDataMiniLinkTnGeneric();
				ob.setNeid(neid);				
				ob.setName(name);
				ob.setDcn_Host_Address(dcnHostAddress);
				ob.setDcn_Subnet_Mask(dcn_Subnet_Mask);
				ob.setDcn_Default_Gateway(dcn_Default_Gateway);
				list.add(ob);

			}
		  
			configDataMiniLinkTnGenerics=list;
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
			
		}
	}
	public static void loadConfigDataMiniLinkTnMMU2BC(String filename, Connection connection) throws Exception{
		logger.info("Data is loding for"+filename);
		 ArrayList<ConfigDataMiniLinkTnMMU2BC> list=new  ArrayList<ConfigDataMiniLinkTnMMU2BC>();
		 
		  try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("Select NEID,NEAlias,Type,Protection_Mode_Admin_Status,Radio_Terminal_Name,Terminal_ID,Far_End_Terminal_Name," +
					"Far_End_ID,Capacity,Modulation,Freq_Band_Ra1,Freq_Index_Ra1,Base_TX_Frequency_RF1,Base_RX_Frequency_RF1,Current_Output_Power_RF1," +
					"Current_Input_Power_RF1,ATPC_Selected_Input_Power_Far_RF1,Input_Alarm_Threshold_RF1 From "+filename+" Order by NEID ");
			while (results.next()) {
				// retrieve and print the values for the current row
				String neAlias=results.getString("NEAlias");
				int neid =Integer.valueOf(results.getString("NEID").trim());
				String type =results.getString("Type").trim();  //Column F
				String protection_Mode_Admin_Status=results.getString("Protection_Mode_Admin_Status").trim(); // Column G 
				String radio_Terminal_Name =results.getString("Radio_Terminal_Name").trim();  // Column D
				String terminal_ID =results.getString("Terminal_ID").trim();  //Column E
				String far_End_Terminal_Name =results.getString("Far_End_Terminal_Name").trim(); //Column L
				String far_End_ID =results.getString("Far_End_ID").trim();   // Column M
				String capacity = results.getString("Capacity").trim();  // Column H
				String modulation=results.getString("Modulation").trim();  // Column J
				String freq_Band_Ra1=results.getString("Freq_Band_Ra1").trim(); // Column AI
				String freq_Index_Ra1= results.getString("Freq_Index_Ra1").trim(); // Column AJ
				String base_TX_Frequency_RF1 =results.getString("Base_TX_Frequency_RF1").trim(); // Column AX
				String base_RX_Frequency_RF1= results.getString("Base_RX_Frequency_RF1").trim();  //Column AY
				String current_Output_Power_RF1 = results.getString("Current_Output_Power_RF1").trim();  // Column BF
				String current_Input_Power_RF1 = results.getString("Current_Input_Power_RF1").trim();  // Column BO
				String atpc_Selected_Input_Power_Far_RF1=results.getString("ATPC_Selected_Input_Power_Far_RF1").trim();  // Column BM
				String input_Alarm_Threshold_RF1=results.getString("Input_Alarm_Threshold_RF1").trim();
		
				ConfigDataMiniLinkTnMMU2BC ob = new ConfigDataMiniLinkTnMMU2BC();
				ob.setNeid(neid);
				ob.setType(type);
				ob.setProtection_Mode_Admin_Status(protection_Mode_Admin_Status);
				ob.setRadio_Terminal_Name(radio_Terminal_Name);
				ob.setTerminal_ID(terminal_ID);
				ob.setFar_End_Terminal_Name(far_End_Terminal_Name);
				ob.setFar_End_ID(far_End_ID);
				ob.setCapacity(capacity);
				ob.setModulation(modulation);
				ob.setFreq_Band_Ra1(freq_Band_Ra1);
				ob.setFreq_Index_Ra1(freq_Index_Ra1);
				ob.setBase_TX_Frequency_RF1(base_TX_Frequency_RF1);
				ob.setBase_RX_Frequency_RF1(base_RX_Frequency_RF1);
				ob.setCurrent_Output_Power_RF1(current_Output_Power_RF1);
				ob.setAtpc_Selected_Input_Power_Far_RF1(atpc_Selected_Input_Power_Far_RF1);
				ob.setCurrent_Input_Power_RF1(current_Input_Power_RF1);
				ob.setInput_Alarm_Threshold_RF1(input_Alarm_Threshold_RF1);
				ob.setNeAlias(neAlias);
				
				list.add(ob);

			}
		  
			configDataMiniLinkTnMMU2BCs=list;
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public static void loadConfigDataMiniLinkTnOSPF(String filename, Connection connection) throws Exception{
		logger.info("Data is loding for"+filename);
		ArrayList<ConfigDataMiniLinkTnOSPF> list=new  ArrayList<ConfigDataMiniLinkTnOSPF>();

		  try {
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("Select NodeName,NEID,Net_Address,Subnet_Mask,Area_ID,Area_Type,Validity From "+filename+" order by NEID");
		
			int index=0;
			ConfigDataMiniLinkTnOSPF ospf_temp=null;
			while (results.next()) {
				// retrieve and print the values for the current row
			    
			
				
				int neid = Integer.valueOf(results.getString("NEID").trim());
				String nodeName = results.getString("NodeName").trim();
				String net_Address = results.getString("Net_Address").trim();
				String subnet_Mask = results.getString("Subnet_Mask").trim();
				String area_ID = results.getString("Area_ID").trim();
				String area_Type = results.getString("Area_Type").trim();
				String validity = results.getString("Validity").trim();
				
				ConfigDataMiniLinkTnOSPF ob = new ConfigDataMiniLinkTnOSPF();
				
				if(index>0){
					ospf_temp=list.get(index-1);
					if(ospf_temp.getNeid()==neid){
						if(!ospf_temp.getNet_Address().equals(net_Address)) {
							if(ospf_temp.getNet_Address_List()==null){
								ospf_temp.setNet_Address_List(new ArrayList<String>());
							}
							ospf_temp.getNet_Address_List().add(net_Address);
						}
						
						if(!ospf_temp.getSubnet_Mask().equals(subnet_Mask)) {
							if(ospf_temp.getSubnet_Mask_List()==null){
								ospf_temp.setSubnet_Mask_List(new ArrayList<String>());
							}
							ospf_temp.getSubnet_Mask_List().add(subnet_Mask);
						}
						if(!ospf_temp.getArea_ID().equals(area_ID)) {
							if(ospf_temp.getArea_ID_List()==null){
								ospf_temp.setArea_ID_List(new ArrayList<String>());
							}
							ospf_temp.getArea_ID_List().add(area_ID);
						}
						
						if(!ospf_temp.getArea_Type().equals(area_Type)) {
							if(ospf_temp.getArea_Type_List()==null){
								ospf_temp.setArea_Type_List(new ArrayList<String>());
							}
							ospf_temp.getArea_Type_List().add(area_Type);
						}
						
						continue;
					}
				}
				ob.setNeid(neid);				
				ob.setNodeName(nodeName);
				ob.setNet_Address(net_Address);
				ob.setSubnet_Mask(subnet_Mask);
				ob.setArea_ID(area_ID);
				ob.setArea_Type(area_Type);
				ob.setValidity(validity);
				list.add(ob);
                index++;
			}
		  
			configDataMiniLinkTnOSPFs=list;
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
	
	}
	

	public static List<CSVFileInfo> load(String	csvLocation, Connection connection) throws Exception{
		
		logger.info("CSV file loading has been started...");
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		};
		
		 List<CSVFileInfo> listToBeProcessed=new ArrayList<CSVFileInfo>();
		 List<CSVFileInfo> sourceList=new ArrayList<CSVFileInfo>();
		
		 File dir = new File(csvLocation);
		
		 if(!dir.isDirectory()){
			
			 throw new Exception(csvLocation+ "is not existing directory on system");
		
			 
		 }
		
		
		String[] csvFiles = dir.list(filter);

		for (String file : csvFiles) {
			CSVFileInfo csvFileInfo=new CSVFileInfo();
			csvFileInfo.setFileName(file.replace(".csv", ""));
			String  dayStamp=csvFileInfo.getDayStamp();
			String  timeStamp=csvFileInfo.getTimeStamp();
			if(dayStamp==null || timeStamp==null){
				continue;
			}
			
		    Date date= csvFileInfo.getFileDate(dayStamp+timeStamp);
		    csvFileInfo.setDate(date);
			if(date==null){
				continue;
			}
		    
			sourceList.add(csvFileInfo);
		}
		
		if(sourceList.size()<5){
			throw new Exception("One or More Raw csv file are missing in "+csvLocation+" to generate Reporting ");
			
		}
		
		// sort by date
		Collections.sort(sourceList);
		
		//get Latest file day
		daystp=sourceList.get(0).getDayStamp();
		
		// get  files which are latest
		for(CSVFileInfo  fileInfo:sourceList){
			if(daystp.equals(fileInfo.getDayStamp())){
				listToBeProcessed.add(fileInfo);
			}
		}
		sourceList=null;
		boolean b1=false,b2=false,b3=false,b4=false,b5=false;
		for (CSVFileInfo file : listToBeProcessed) {
			if(file.getFileName().contains("SW_Module_Inventory_")){			
				
				if(!b1){
					file.setDataModelType(CSVFileInfo.SW_MODULE_INVENTORY);	
					b1=true;
				}
			    continue;
			}
			if(file.getFileName().contains("HW_Module_Inventory_")){
				
				if(!b2){
				file.setDataModelType(CSVFileInfo.HW_MODULE_INVENTORY);
				b2=true;
				}
				continue;
			}
			if(file.getFileName().contains("Config_Data_MINI-LINK_TN_GENERIC_")){
				if(!b3){
				file.setDataModelType(CSVFileInfo.CONFIG_DATA_MINILINKTN_GENERIC);
				b3=true;
				}
				continue;
			}
			if(file.getFileName().contains("Config_Data_MINI-LINK_TN_OSPF_")){
				if(!b4){
				file.setDataModelType(CSVFileInfo.CONFIG_DATAMINILINKTN_OSPF);
				b4=true;
				}
				continue; 
			}
			if(file.getFileName().contains("Config_Data_MINI-LINK_TN_MMU2B_C_")){
				if(!b5){
				file.setDataModelType(CSVFileInfo.CONFIG_DATAMINILINKTN_MMU2BC);
				b5=true;
				}
				continue;
			}
		}
		
		    if( !(b1&&b2&&b3&&b4&&b5)){
		    
                 throw new Exception("One or more raw file does not exists for day...:"+daystp +" in"+csvLocation);
		    	
		    }
		
		    configDataMiniLinkTnGenerics=null;
		    configDataMiniLinkTnMMU2BCs=null;
		    configDataMiniLinkTnOSPFs=null;
		    hwModuleInventories=null;
		    swModuleInventories=null;  
		
		for(CSVFileInfo fileInfo:listToBeProcessed){
			if(fileInfo.getDataModelType()==CSVFileInfo.CONFIG_DATA_MINILINKTN_GENERIC){
				loadConfigDataMiniLinkTnGenerics(fileInfo.getFileName(),connection);
			}
			if(fileInfo.getDataModelType()==CSVFileInfo.CONFIG_DATAMINILINKTN_MMU2BC){
				loadConfigDataMiniLinkTnMMU2BC(fileInfo.getFileName(),connection);
			}
			if(fileInfo.getDataModelType()==CSVFileInfo.CONFIG_DATAMINILINKTN_OSPF){
				loadConfigDataMiniLinkTnOSPF(fileInfo.getFileName(),connection);
			}
			if(fileInfo.getDataModelType()==CSVFileInfo.HW_MODULE_INVENTORY){
				loadHwModuleInventory(fileInfo.getFileName(),connection);
			}
			if(fileInfo.getDataModelType()==CSVFileInfo.SW_MODULE_INVENTORY){
				loadSwModuleInventory(fileInfo.getFileName(),connection);
			}
		}
		
		
		return listToBeProcessed;
		
		
		
		
		
		
     }

	public static List<ConfigDataMiniLinkTnGeneric> getConfigDataMiniLinkTnGenerics() {
		return configDataMiniLinkTnGenerics;
	}
	public static List<HwModuleInventory> getHwModuleInventories() {
		return hwModuleInventories;
	}
	public static List<SwModuleInventory> getSwModuleInventories() {
		return swModuleInventories;
	}
	public static List<ConfigDataMiniLinkTnMMU2BC> getConfigDataMiniLinkTnMMU2BCs() {
		return configDataMiniLinkTnMMU2BCs;
	}
	public static List<ConfigDataMiniLinkTnOSPF> getConfigDataMiniLinkTnOSPFs() {
		return configDataMiniLinkTnOSPFs;
	}

	public static void setHwModuleInventories(
			List<HwModuleInventory> hwModuleInventories) {
		CSVFacade.hwModuleInventories = hwModuleInventories;
	}
	public static void setSwModuleInventories(
			List<SwModuleInventory> swModuleInventories) {
		CSVFacade.swModuleInventories = swModuleInventories;
	}
	
	public static void setConfigDataMiniLinkTnGenerics(
			List<ConfigDataMiniLinkTnGeneric> configDataMiniLinkTnGenerics) {
		CSVFacade.configDataMiniLinkTnGenerics = configDataMiniLinkTnGenerics;
	}
	public static void setConfigDataMiniLinkTnMMU2BCs(
			List<ConfigDataMiniLinkTnMMU2BC> configDataMiniLinkTnMMU2BCs) {
		CSVFacade.configDataMiniLinkTnMMU2BCs = configDataMiniLinkTnMMU2BCs;
	}
	public static void setConfigDataMiniLinkTnOSPFs(
			List<ConfigDataMiniLinkTnOSPF> configDataMiniLinkTnOSPFs) {
		CSVFacade.configDataMiniLinkTnOSPFs = configDataMiniLinkTnOSPFs;
	}
	public static String getDaystp() {
		return daystp;
	}
	
	
}
