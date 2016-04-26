	package com.tibco.asyncaccountinvestigation.export;
	
	import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tibco.asyncaccountinvestigation.export.model.Result;
import com.tibco.asyncaccountinvestigation.export.model.Results;
import com.tibco.asyncaccountinvestigation.export.model.Row;
import com.tibco.asyncaccountinvestigation.util.StringHelper;
	
	
	public class ExcelExporter {
		private static int MAXIMUM_ALLOWED_ROW_IN_SHEET=65535;
		public HSSFWorkbook createExcelFileFromResults(Results results) throws IOException{			
			
			logger.info("AccountInvestigation creating excel file for REFERENCEID:"+results.getReferenceID());
			
			
			HSSFWorkbook workbook = new HSSFWorkbook();
		
			for(Result res:results.getResults()){
				
				int numberOfSheet=0;
				if(res.getRows().length>0){
				    numberOfSheet=res.getRows().length/MAXIMUM_ALLOWED_ROW_IN_SHEET;
				    int exceedNumber=res.getRows().length%MAXIMUM_ALLOWED_ROW_IN_SHEET;
					if(exceedNumber>0){
						numberOfSheet=numberOfSheet+1;
					}
				}else{
					numberOfSheet=1; // They also want to see empty result as a empty sheet
				}
				 
				for(int index=0;index<numberOfSheet;index++){
					createSheet(workbook, res,index);
				}
				
				
			}
			
			logger.info("AccountInvestigation done creating excel file for REFERENCEID:"+results.getReferenceID());
			return workbook;
		}
		
		private void createSheet(HSSFWorkbook workbook, Result res,int sheetIndex) {
			String sheetName = res.getTableName();
			int indx;
			if (sheetName != null && ((indx = sheetName.lastIndexOf("."))>0)){
				sheetName = sheetName.substring(indx+1);
			} 
			
			if(sheetIndex>0){
				sheetName=sheetName+"_"+sheetIndex;	
			}
			
			if (logger.isDebugEnabled())
				logger.debug("AccountInvestigation creating sheet:"+sheetName);
			
			HSSFSheet sheet=workbook.getSheet(sheetName);			
			if(sheet!=null){
				logger.warn("The workbook already contains a sheet of this name..:"+sheetName);
				logger.info("Skipping the sheet creation for the name..:"+sheetName+" it is already exsist.");
				return;
			}			
			
			sheet = workbook.createSheet(sheetName);
			sheet.createFreezePane(0, 1);
			String[] headers =res.getHeaderValues();
			
			if (logger.isTraceEnabled())
				logger.trace("AccountInvestigation creating headers:"+ StringHelper.prettyPrinter(headers));
			createHeader(workbook, sheet, headers, headers.length, 0);
			HSSFCellStyle style = getStyle(workbook);
			Row[] rows = res.getRows();
			int startDataIndex=sheetIndex*MAXIMUM_ALLOWED_ROW_IN_SHEET; 			
		    int endDataIndex=0;			
		    if(rows.length<((sheetIndex+1)*MAXIMUM_ALLOWED_ROW_IN_SHEET)){
		    	endDataIndex=rows.length;
		    }else{
		    	endDataIndex=(sheetIndex+1)*MAXIMUM_ALLOWED_ROW_IN_SHEET;
		    }
			int rowNumber=0;
		    for(int i=startDataIndex; i< endDataIndex; i++){
				if (logger.isTraceEnabled())
					logger.trace("AccountInvestigation creating row:"+(i+1)+":"+StringHelper.prettyPrinter(rows[i].getValues()));
				createRow(workbook, sheet, rows[i].getValues(), rowNumber+1, style);
				rowNumber++;
		    }
			
			for(int i=0;i<headers.length;i++)
				sheet.autoSizeColumn(i);
		}


		//Style for body rows
		private HSSFCellStyle getStyle(HSSFWorkbook workbook) {
			HSSFCellStyle style = workbook.createCellStyle();
	        style.setWrapText(true);
	        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style.setFillForegroundColor(HSSFColor.WHITE.index);
	        HSSFFont arialFont = workbook.createFont();
	        arialFont.setFontName("Arial");
	        arialFont.setFontHeightInPoints((short) 10);
	        style.setFont(arialFont);
			return style;
		}
	
		
		private void createRow(HSSFWorkbook workbook, HSSFSheet sheet,
				String[] values, int rowNum,  HSSFCellStyle style) {
			HSSFRow row = sheet.createRow(rowNum);
			for( int i = 0; i < values.length ; i++ ) {
		        HSSFCell cell = row.createCell(i);
		        String text = values[i];
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setCellValue(text);            	
		        cell.setCellStyle(style);
			}
			
		}
	
	
		private void createHeader(HSSFWorkbook workbook, HSSFSheet sheet1, String[] header, int columns, int rowNum) {     
			
			HSSFRow row = sheet1.createRow(rowNum);
			for( int i = 0; i < header.length ; i++ ) {
		        HSSFCell cell = row.createCell(i);
		        String text = header[i];
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setCellValue(text);            
		        HSSFCellStyle style = workbook.createCellStyle();
		        style.setWrapText(true);
		        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		        //style.setFillPattern(HSSFCellStyle.BORDER_THIN);
		        HSSFFont arialBoldFont = workbook.createFont();
		        arialBoldFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		        arialBoldFont.setFontName("Arial");
		        arialBoldFont.setFontHeightInPoints((short) 12);
		        style.setFont(arialBoldFont);
		        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		        style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		        cell.setCellStyle(style);           
		    }
		}   
		
		final Logger logger = LoggerFactory.getLogger("com.tibco.asyncaccountinvestigation");
	

	
	}
	
