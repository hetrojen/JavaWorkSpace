package com.ericsson.mobilysoem.reporting.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;

import com.ericsson.mobilysoem.reporting.csv.CSVFacade;
import com.ericsson.mobilysoem.reporting.model.CSVFileInfo;
import com.ericsson.mobilysoem.reporting.model.HopDetailReportModel;
import com.ericsson.mobilysoem.reporting.model.HwSwInventoryReportModel;

public class ExcelReporting {
	public static final Logger logger = Logger.getLogger(ExcelReporting.class);
     public final static int  MAX_ROW_NUMBER=65532;
   
	
    public static String   createReportFile(String source) throws Exception{
    
		String reportFileTemp = SoemConfig.getReportFileTemplate();
		String reportFileDestination=SoemConfig.getReportFileDestination();
		File destinationDirectory = new File(reportFileTemp);
		
		if(!destinationDirectory.exists()){
			destinationDirectory.mkdir();
		}
		
		 Calendar now = Calendar.getInstance();
		 String  reportFileName= reportFileDestination+"/SoemReport_"+source+"_"+CSVFacade.getDaystp()+"_"+now.get(Calendar.YEAR);
		  
		  int month=now.get(Calendar.MONTH);
		  month=month+1;
		  int day=now.get(Calendar.DATE);
		  int hour=now.get(Calendar.HOUR_OF_DAY);
		  int minute=now.get(Calendar.MINUTE);
		  
		  if(month<=9) {
			   reportFileName+="0"+month;
		  }else{
			  reportFileName+=month;
		  }
		  if(day<=9){
			  reportFileName+="0"+day+"_";
		  }else{
			  reportFileName+=day+"_";
		  }
		  if(hour<=9){
			  reportFileName+="0"+hour;
		  }else{
			  reportFileName+=hour;
		  }
		  if(minute<=9){
			  reportFileName+="0"+minute;
		  }else{
			  reportFileName+=minute;
		  }
		  reportFileName+=".xls";
		  
		  
		  
		  
			File templateReportFile = new File(reportFileTemp);
			File reportFile = new File(reportFileName);
			try {
				FileUtils.copyFile(templateReportFile, reportFile);
				return reportFileName;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				throw e;
				
			}
			
    }
    private static void addCommentToCell(Drawing drawing,CreationHelper factory, Cell cell, Row row, String commentText){
    	ClientAnchor anchor = factory.createClientAnchor();
    	RichTextString str = factory.createRichTextString(commentText);
      	anchor.setCol1(cell.getColumnIndex());
     	anchor.setCol2(cell.getColumnIndex()-1);
     	anchor.setRow1(row.getRowNum());
     	anchor.setRow2(row.getRowNum()+3);            	
     	Comment comment = drawing.createCellComment(anchor);
     	comment.setString(str);
     	cell.setCellComment(comment);
    }
	public static void  fillHopDetailsReport(List<HopDetailReportModel> list, List<CSVFileInfo> listF, String reportFileName) throws Exception{
		
	
	
		 InputStream fileIn = null; 
         FileOutputStream fileOut = null; 
         HSSFWorkbook       wb= null;
         HSSFSheet sheetG= null;
        
         try 
         { 
             //fileIn = new FileInputStream(""); 
        	 
        	
			
			fileIn = new FileInputStream(reportFileName);
			 POIFSFileSystem fileSystem = null;
        	 fileSystem = new POIFSFileSystem (fileIn);
			// InputStream inp = new FileInputStream("workbook.xlsx");

			wb = new HSSFWorkbook (fileSystem);
			
			  	CreationHelper factory = wb.getCreationHelper();
		
			  	    int indexOfHopDetail=wb.getSheetIndex("Hop Details");
				    sheetG = wb.getSheetAt(indexOfHopDetail);		   
				   
				
			
						fillHopDetailsSheet(list, sheetG, listF, factory);
					

				

             
              fileOut = new FileOutputStream(reportFileName);
             wb.write(fileOut);
             fileOut.close();
         } 
         catch (Exception e) {
			// TODO: handle exception
        	
        	 e.printStackTrace();
        	 throw e;
		}
         finally {
             if (fileOut != null) 
                 fileOut.close(); 
             if (fileIn != null) 
                 fileIn.close(); 
              wb=null;   
              sheetG=null;
              System.gc();
         } 
     } 
	private static void fillHopDetailsSheet(List<HopDetailReportModel> list, HSSFSheet sheet,List<CSVFileInfo> listF,	CreationHelper factory) throws Exception {
		 HSSFRow   titeRow=sheet.getRow(0);
		 HSSFRow   dataRow=sheet.getRow(4);
		Short dataHeight=dataRow.getHeight();
		
		for(CSVFileInfo fileInfo:listF){
			if(fileInfo.getDataModelType()==CSVFileInfo.CONFIG_DATA_MINILINKTN_GENERIC){
				Cell miniLinkTnGenericTitle=titeRow.getCell(1);
				miniLinkTnGenericTitle.setCellType(Cell.CELL_TYPE_STRING);
				miniLinkTnGenericTitle.setCellValue(fileInfo.getFileName());
				
				miniLinkTnGenericTitle=titeRow.getCell(23);
				miniLinkTnGenericTitle.setCellType(Cell.CELL_TYPE_STRING);
				miniLinkTnGenericTitle.setCellValue(fileInfo.getFileName());
			}
			if(fileInfo.getDataModelType()==CSVFileInfo.CONFIG_DATAMINILINKTN_MMU2BC){
				Cell miniLinkTnMmu2bcTitle=titeRow.getCell(6);
				miniLinkTnMmu2bcTitle.setCellType(Cell.CELL_TYPE_STRING);
				miniLinkTnMmu2bcTitle.setCellValue(fileInfo.getFileName());
				
			}
			if(fileInfo.getDataModelType()==CSVFileInfo.CONFIG_DATAMINILINKTN_OSPF){
				Cell miniLinkTnOspfTitle=titeRow.getCell(26);
				miniLinkTnOspfTitle.setCellType(Cell.CELL_TYPE_STRING);
				miniLinkTnOspfTitle.setCellValue(fileInfo.getFileName());
			}
			
		}
		
		
		
		
		
		
	
		
		

		HSSFCellStyle cellStyleData = null;
         try{
          cellStyleData=sheet.getRow(4).getCell(1).getCellStyle();
         }catch (Exception e) {
			// TODO: handle exception
        	 throw e;
		}
         
         HSSFCellStyle cellStyleGrey = null;
        try{
        	cellStyleGrey=sheet.getRow(4).getCell(0).getCellStyle();
        }catch (Exception e) {
			// TODO: handle exception
       	 throw e;
		}
        
        Drawing drawing = ((org.apache.poi.ss.usermodel.Sheet) sheet).createDrawingPatriarch();
        int index=4;
       	
         
        
         for(HopDetailReportModel model:list){
        	
        	 HSSFRow row=sheet.getRow(index);
        	 if(row==null){
        		 row=sheet.createRow(index);
        	 }
        	 row.setHeight(dataHeight);
        	 
        	 
        	 HSSFCell cellA=row.getCell(0);
          	if(cellA==null){
          		cellA = row.createCell(0);
          	}
        	cellA.setCellStyle(cellStyleGrey); 
          	
          	
        	 HSSFCell cellB=row.getCell(1);
         	if(cellB==null){
         		cellB = row.createCell(1);
         	}
         	cellB.setCellStyle(cellStyleData);
         	cellB.setCellType(HSSFCell.CELL_TYPE_STRING);
     
        	String neid="";
        	if(model.getConfigDataMiniLinkTnGeneric().getNeid()!=0){
        		neid=model.getConfigDataMiniLinkTnGeneric().getNeid()+"";
        	}
            cellB.setCellValue(neid);
   
         	
         
    
             
             HSSFCell cellC=row.getCell(2);
         	if(cellC==null){
         		cellC = row.createCell(2);
         	}
         	cellC.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellC.setCellValue(model.getConfigDataMiniLinkTnGeneric().getName());
             cellC.setCellStyle(cellStyleData);
             HSSFCell cellD=row.getCell(3);
         	if(cellD==null){
         		cellD = row.createCell(3);
         	}
         	cellD.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellD.setCellValue(model.getConfigDataMiniLinkTnGeneric().get_ammType());
             cellD.setCellStyle(cellStyleData);
             
             HSSFCell cellE=row.getCell(4);
         	if(cellE==null){
         		cellE = row.createCell(4);
         	}
         	cellE.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellE.setCellValue(model.getConfigDataMiniLinkTnGeneric().get_mpuType());
             cellE.setCellStyle(cellStyleData);
             
             HSSFCell cellF=row.getCell(5);
         	if(cellF==null){
         		cellF = row.createCell(5);
         	}
         	cellF.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellF.setCellValue(model.getConfigDataMiniLinkTnGeneric().getDcn_Host_Address());
             cellF.setCellStyle(cellStyleData);  
             
             
             HSSFCell cellG=row.getCell(6);
         	if(cellG==null){
         		cellG = row.createCell(6);
         	}
         	cellG.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellG.setCellValue(model.getConfigDataMiniLinkTnGeneric().getName());
             cellG.setCellStyle(cellStyleData);


             
             HSSFCell cellH=row.getCell(7);
          	if(cellH==null){
          		cellH = row.createCell(7);
          	}
          	cellH.setCellType(HSSFCell.CELL_TYPE_STRING);
              cellH.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().get_siteB());
              cellH.setCellStyle(cellStyleData);
             
             
             
             HSSFCell cellI=row.getCell(8);
         	if(cellI==null){
         		cellI = row.createCell(8);
         	}
         	cellI.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellI.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getType());
             cellI.setCellStyle(cellStyleData);
             
             HSSFCell cellJ=row.getCell(9);
         	if(cellJ==null){
         		cellJ = row.createCell(9);
         	}
         	cellJ.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellJ.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getProtection_Mode_Admin_Status());
             cellJ.setCellStyle(cellStyleData);
             HSSFCell cellK=row.getCell(10);
         	if(cellK==null){
         		cellK = row.createCell(10);
         	}
         	cellK.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellK.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getRadio_Terminal_Name());
             cellK.setCellStyle(cellStyleData);
             HSSFCell cellL=row.getCell(11);
         	if(cellL==null){
         		cellL = row.createCell(11);
         	}
         	cellL.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellL.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getTerminal_ID());
             cellL.setCellStyle(cellStyleData);
             HSSFCell cellM=row.getCell(12);
         	if(cellM==null){
         		cellM = row.createCell(12);
         	}
         	cellM.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellM.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getFar_End_Terminal_Name());
             cellM.setCellStyle(cellStyleData);
             HSSFCell cellN=row.getCell(13);
         	if(cellN==null){
         		cellN = row.createCell(13);
         	}
         	cellN.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellN.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getFar_End_ID());
             cellN.setCellStyle(cellStyleData);
             HSSFCell cellO=row.getCell(14);
         	if(cellO==null){
         		cellO = row.createCell(14);
         	}
         	cellO.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellO.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getCapacity());
             cellO.setCellStyle(cellStyleData);
             HSSFCell cellP=row.getCell(15);
         	if(cellP==null){
         		cellP = row.createCell(15);
         	}
         	cellP.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellP.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getModulation());
             cellP.setCellStyle(cellStyleData);
             HSSFCell cellQ=row.getCell(16);
         	if(cellQ==null){
         		cellQ = row.createCell(16);
         	}
         	cellQ.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellQ.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getFreq_Band_Ra1());
             cellQ.setCellStyle(cellStyleData);
             HSSFCell cellR=row.getCell(17);
         	if(cellR==null){
         		cellR = row.createCell(17);
         	}
         	cellR.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellR.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getFreq_Index_Ra1());
             cellR.setCellStyle(cellStyleData);
             
             HSSFCell cellS=row.getCell(18);
         	if(cellS==null){
         		cellS = row.createCell(18);
         	}
         	cellS.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellS.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getBase_TX_Frequency_RF1());
             cellS.setCellStyle(cellStyleData);
             HSSFCell cellT=row.getCell(19);
         	if(cellT==null){
         		cellT = row.createCell(19);
         	}
         	cellT.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellT.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getBase_RX_Frequency_RF1());
             cellT.setCellStyle(cellStyleData);
             HSSFCell cellU=row.getCell(20);
         	if(cellU==null){
         		cellU = row.createCell(20);
         	}
         	cellU.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellU.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getCurrent_Output_Power_RF1());
             cellU.setCellStyle(cellStyleData);
             HSSFCell cellV=row.getCell(21);
         	if(cellV==null){
         		cellV = row.createCell(21);
         	}
         	cellV.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellV.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getCurrent_Input_Power_RF1());
             cellV.setCellStyle(cellStyleData);
             HSSFCell cellW=row.getCell(22);
         	if(cellW==null){
         		cellW = row.createCell(22);
         	}
         	cellW.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellW.setCellValue(model.getConfigDataMiniLinkTnMMU2BC().getAtpc_Selected_Input_Power_Far_RF1());
             cellW.setCellStyle(cellStyleData);
             HSSFCell cellX=row.getCell(23);
         	if(cellX==null){
         		cellX = row.createCell(23);
         	}
         	cellX.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellX.setCellValue(model.getConfigDataMiniLinkTnGeneric().getDcn_Host_Address());
             cellX.setCellStyle(cellStyleData);
             HSSFCell cellY=row.getCell(24);
         	if(cellY==null){
         		cellY = row.createCell(24);
         	}
         	cellY.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellY.setCellValue(model.getConfigDataMiniLinkTnGeneric().getDcn_Subnet_Mask());
             cellY.setCellStyle(cellStyleData);
             
             HSSFCell cellZ=row.getCell(25);
         	if(cellZ==null){
         		cellZ = row.createCell(25);
         	}
         	cellZ.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellZ.setCellValue(model.getConfigDataMiniLinkTnGeneric().getDcn_Default_Gateway());
             cellZ.setCellStyle(cellStyleData);
             
             
             
             
             HSSFCell cellAA=row.getCell(26);
         	if(cellAA==null){
         		cellAA = row.createCell(26);
         	}
         	            	
         	
         
         	
         	cellAA.setCellType(HSSFCell.CELL_TYPE_STRING);
         	
         	 if(model.getConfigDataMiniLinkTnOSPF().getNet_Address().contains("/")){
         		cellAA.setCellValue("Multiple Value");
         		addCommentToCell(drawing, factory, cellAA, row, model.getConfigDataMiniLinkTnOSPF().getNet_Address());
         	 }else{
         		   cellAA.setCellValue(model.getConfigDataMiniLinkTnOSPF().getNet_Address());
             
         	 }
           cellAA.setCellStyle(cellStyleData);
            
             
             HSSFCell cellAB=row.getCell(27);
         	if(cellAB==null){
         		cellAB = row.createCell(27);
         	}
         	
         	
         	
         	cellAB.setCellType(HSSFCell.CELL_TYPE_STRING);
         	
         	
         	 if(model.getConfigDataMiniLinkTnOSPF().getSubnet_Mask().contains("/")){
         		cellAB.setCellValue("Multiple Value");
         		addCommentToCell(drawing, factory, cellAB, row, model.getConfigDataMiniLinkTnOSPF().getSubnet_Mask());
         	 } else{
         		  cellAB.setCellValue(model.getConfigDataMiniLinkTnOSPF().getSubnet_Mask());
             
         	 }
         	 cellAB.setCellStyle(cellStyleData);
         	 
            
             HSSFCell cellAC=row.getCell(28);
         	if(cellAC==null){
         		cellAC = row.createCell(28);
         	}
         	cellAC.setCellType(HSSFCell.CELL_TYPE_STRING);
         	
         	 if(model.getConfigDataMiniLinkTnOSPF().getArea_ID().contains("/")){
          		cellAC.setCellValue("Multiple Value");
          		addCommentToCell(drawing, factory, cellAC, row, model.getConfigDataMiniLinkTnOSPF().getArea_ID());
          	 } else{
          		  cellAC.setCellValue(model.getConfigDataMiniLinkTnOSPF().getArea_ID());
              
          	 }
         	cellAC.setCellStyle(cellStyleData);
          
             HSSFCell cellAD=row.getCell(29);
         	if(cellAD==null){
         		cellAD = row.createCell(29);
         	}
         	cellAD.setCellStyle(cellStyleData);
         	cellAD.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellAD.setCellValue(model.getConfigDataMiniLinkTnOSPF().getArea_Type());
             
             index++;
        	 
         }
	}
	private static void fillHwSwInventorySheet(List<HwSwInventoryReportModel> list,int first,int second, HSSFSheet sheet,List<CSVFileInfo> listF) throws Exception{
		

	      HSSFRow   titeRow=sheet.getRow(0);
	      HSSFRow   dataRow=sheet.getRow(4);
	      Short dataHeight=dataRow.getHeight();
		Cell miniLinkTnGenericTitle=titeRow.getCell(1);
		
		for(CSVFileInfo fileInfo:listF){
			if(fileInfo.getDataModelType()==CSVFileInfo.CONFIG_DATA_MINILINKTN_GENERIC){
				miniLinkTnGenericTitle.setCellType(Cell.CELL_TYPE_STRING);
				miniLinkTnGenericTitle.setCellValue(fileInfo.getFileName());
			}
		
			if(fileInfo.getDataModelType()==CSVFileInfo.HW_MODULE_INVENTORY){
				Cell hwInventoryTitle=titeRow.getCell(6);
				hwInventoryTitle.setCellType(Cell.CELL_TYPE_STRING);
				hwInventoryTitle.setCellValue(fileInfo.getFileName());
			}
			if(fileInfo.getDataModelType()==CSVFileInfo.SW_MODULE_INVENTORY){
				Cell swInventory=titeRow.getCell(11);
				swInventory.setCellType(Cell.CELL_TYPE_STRING);
				swInventory.setCellValue(fileInfo.getFileName());
			}
		}

		HSSFCellStyle cellStyle = null;
		try {
			cellStyle = sheet.getRow(4).getCell(1).getCellStyle();
		} catch (Exception e) {
			// TODO: handle exception
        	 throw e;
		}
		
		HSSFCellStyle cellStyleGrey = null;
        try{
        	cellStyleGrey=sheet.getRow(4).getCell(0).getCellStyle();
        }catch (Exception e) {
			// TODO: handle exception
       	    throw e;
		}
        
		
		int index=4;
        for(int i=first;i<second;i++){
        	HwSwInventoryReportModel	model= list.get(i);
       	
        	
        	HSSFRow row=sheet.getRow(index);
       	 if(row==null){
       		 row=sheet.createRow(index);
       	 }
       	 row.setHeight(dataHeight);
       	 
          	 
       	 HSSFCell cellA=row.getCell(0);
         	if(cellA==null){
         		cellA = row.createCell(0);
         	}
       	cellA.setCellStyle(cellStyleGrey); 
       	
       	
       	 HSSFCell cellB=row.getCell(1);
        	if(cellB==null){
        		cellB = row.createCell(1);
        	}
        	cellB.setCellStyle(cellStyle);
        	cellB.setCellType(HSSFCell.CELL_TYPE_STRING);
    
        	String neid="";
        	if(model.getConfigDataMiniLinkTnGeneric().getNeid()!=0){
        		neid=model.getConfigDataMiniLinkTnGeneric().getNeid()+"";
        	}
            cellB.setCellValue(neid);
   
            
            HSSFCell cellC=row.getCell(2);
        	if(cellC==null){
        		cellC = row.createCell(2);
        	}
        	cellC.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellC.setCellValue(model.getConfigDataMiniLinkTnGeneric().getName());
            cellC.setCellStyle(cellStyle);
            HSSFCell cellD=row.getCell(3);
        	if(cellD==null){
        		cellD = row.createCell(3);
        	}
        	cellD.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellD.setCellValue(model.getConfigDataMiniLinkTnGeneric().get_ammType());
            cellD.setCellStyle(cellStyle);
            
            HSSFCell cellE=row.getCell(4);
        	if(cellE==null){
        		cellE = row.createCell(4);
        	}
        	cellE.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellE.setCellValue(model.getConfigDataMiniLinkTnGeneric().get_mpuType());
            cellE.setCellStyle(cellStyle);
            
            HSSFCell cellF=row.getCell(5);
        	if(cellF==null){
        		cellF = row.createCell(5);
        	}
        	cellF.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellF.setCellValue(model.getConfigDataMiniLinkTnGeneric().getDcn_Host_Address());
            cellF.setCellStyle(cellStyle);  
            
            
            HSSFCell cellG=row.getCell(6);
        	if(cellG==null){
        		cellG = row.createCell(6);
        	}
        	cellG.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellG.setCellValue(model.getHwModuleInventory().getId());
            cellG.setCellStyle(cellStyle);

            
            HSSFCell cellH=row.getCell(7);
         	if(cellH==null){
         		cellH = row.createCell(7);
         	}
         	cellH.setCellType(HSSFCell.CELL_TYPE_STRING);
             cellH.setCellValue(model.getHwModuleInventory().getAmmPosition());
             cellH.setCellStyle(cellStyle);
            
            HSSFCell cellI=row.getCell(8);
        	if(cellI==null){
        		cellI = row.createCell(8);
        	}
        	cellI.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellI.setCellValue(model.getHwModuleInventory().getTypeOfUnit());
            cellI.setCellStyle(cellStyle);
            
            HSSFCell cellJ=row.getCell(9);
        	if(cellJ==null){
        		cellJ = row.createCell(9);
        	}
        	cellJ.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellJ.setCellValue(model.getHwModuleInventory().getVersion());
            cellJ.setCellStyle(cellStyle);
            HSSFCell cellK=row.getCell(10);
        	if(cellK==null){
        		cellK = row.createCell(10);
        	}
        	cellK.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellK.setCellValue(model.getHwModuleInventory().getProductNumber());
            cellK.setCellStyle(cellStyle);
            HSSFCell cellL=row.getCell(11);
        	if(cellL==null){
        		cellL = row.createCell(11);
        	}
        	cellL.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellL.setCellValue(model.getSwModuleInventory().getId());
            cellL.setCellStyle(cellStyle);
            HSSFCell cellM=row.getCell(12);
        	if(cellM==null){
        		cellM = row.createCell(12);
        	}
        	cellM.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellM.setCellValue(model.getSwModuleInventory().getTypeOfSWUnit());
            cellM.setCellStyle(cellStyle);
            HSSFCell cellN=row.getCell(13);
        	if(cellN==null){
        		cellN = row.createCell(13);
        	}
        	cellN.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellN.setCellValue(model.getSwModuleInventory().getSwProductNumber());
            cellN.setCellStyle(cellStyle);
           
            HSSFCell cellO=row.getCell(14);
        	if(cellO==null){
        		cellO = row.createCell(14);
        	}
        	cellO.setCellType(HSSFCell.CELL_TYPE_STRING);
            cellO.setCellValue(model.getSwModuleInventory().getVersionOfSW());
            cellO.setCellStyle(cellStyle);
           
        
            
            index++;
    
            
        }
	}
	public static void  fillHwSwInventoryReport(List<HwSwInventoryReportModel> list,List<CSVFileInfo> listF,String reportFileName)throws Exception{
		 InputStream fileIn = null; 
         FileOutputStream fileOut = null; 
         HSSFWorkbook       wb= null;
         HSSFSheet sheetG= null;
        try 
         {   fileIn = new FileInputStream(reportFileName);
			 POIFSFileSystem fileSystem = null;
        	 fileSystem = new POIFSFileSystem (fileIn);
		
			wb = new HSSFWorkbook (fileSystem);
			
		   int indexOfHwInventory=wb.getSheetIndex("HW SW Inventory");
		    sheetG = wb.getSheetAt(indexOfHwInventory);		   
		   
			int div=list.size()/MAX_ROW_NUMBER;
			int add=list.size()%MAX_ROW_NUMBER;
			
	    	int SheetNumber=div;
			
			if(add>0){
				SheetNumber++; 
			}
			
			HSSFSheet sheet=null;
			
			int indexOfShhet=indexOfHwInventory;
		
			int first,second;
			for (int i = 1; i < SheetNumber; i++) {
				sheet = wb.cloneSheet(indexOfHwInventory);
				wb.setSheetName(wb.getSheetIndex(sheet), "HW SW Inventory_"+ i);
			}
			 sheet=sheetG;
			for (int i = 0; i < SheetNumber; i++) {

				first = (i * MAX_ROW_NUMBER);
				if (((i + 1) * MAX_ROW_NUMBER)<=list.size()) {
					second = (i + 1) * MAX_ROW_NUMBER;
				} else {
					second = (i * MAX_ROW_NUMBER) + add;
				}

				if (i > 0) {
					sheet = wb.getSheet("HW SW Inventory_"+ i);

				}
			
				fillHwSwInventorySheet(list,first,second, sheet, listF);
				indexOfShhet++;

			}
			
	        
			
			fileOut = new FileOutputStream(reportFileName);
            wb.write(fileOut);
            fileOut.close();
            
         } 
         catch (Exception e) {
			// TODO: handle exception
        	 e.printStackTrace();
        	 throw e;
        	
		}
         finally { 
             if (fileOut != null) 
                 fileOut.close(); 
             if (fileIn != null) 
                 fileIn.close(); 
             wb=null;   
             sheetG=null;
         } 
	}
}
