package com.company.rating.util;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.company.rating.view.model.GelirBilancoExcelModel;

public class RasyoFileReader {
	public static  final String BILANCO_SHEET_NAME="";
	public static  final String GELIR_SHEET_NAME="";
	
  InputStream excelFile;
  Workbook workBook=null;
        public RasyoFileReader(InputStream excelFile) throws Exception{
			// TODO Auto-generated constructor stub
        	this.excelFile=excelFile;
         
            	workBook = WorkbookFactory.create(excelFile);
    		
		}
        
   private BigDecimal getValuOfFormule(String formul){
	   BigDecimal val=new BigDecimal(0);
	   try{
	
		//Workbook wb=null;
	//or new XSSFWorkbook("d:/Rating_Import")
	       if(workBook==null){
	    	   return val;
	       }
		Sheet sheet = workBook.getSheetAt(0);
		FormulaEvaluator evaluator = workBook.getCreationHelper().createFormulaEvaluator();

		// suppose your formula is in B3
		CellReference cellReference = new CellReference("A1"); 
		Row row = sheet.getRow(cellReference.getRow());
		Cell cell = row.getCell(cellReference.getCol()); 
		System.out.println(cell);
		cell.setCellFormula(formul);
		CellValue cellValue = evaluator.evaluate(cell);

		switch (cellValue.getCellType()) {
		    case Cell.CELL_TYPE_BOOLEAN:
		        System.out.println(cellValue.getBooleanValue());
		        break;
		    case Cell.CELL_TYPE_NUMERIC:
		      
		        val=new BigDecimal(cellValue.getNumberValue());
		        val=val.setScale(3,BigDecimal.ROUND_HALF_EVEN);
		        break;
		    case Cell.CELL_TYPE_STRING:
		        System.out.println(cellValue.getStringValue());
		        break;
		    case Cell.CELL_TYPE_BLANK:
		        break;
		    case Cell.CELL_TYPE_ERROR:
		        break;

		    // CELL_TYPE_FORMULA will never happen
		    case Cell.CELL_TYPE_FORMULA: 
		        break;
		}
	   }catch (Exception e) {
		return val;
	}
		return val;
   }
   
   public  ArrayList<String> codeList=new ArrayList<String>();
	
	
	private List<GelirBilancoExcelModel>  parseToGelirBilancoModel(Row row){
		int i=0;
		List<GelirBilancoExcelModel> list=new ArrayList<GelirBilancoExcelModel>();
		for (Cell cell : row) {
			// Do something here
			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC ){
				BigDecimal dd=new BigDecimal(cell.getNumericCellValue());
				GelirBilancoExcelModel model=new GelirBilancoExcelModel();
				model.setCode(dd.intValue());
                   
			
					Cell  ce=row.getCell(i+1);
					if(ce==null){
						continue;
					}
					   if(ce.getCellType()==Cell.CELL_TYPE_STRING){
						   Cell  onceki=row.getCell(i+2);
						   Cell  cari=row.getCell(i+3);
						   if(onceki==null || cari==null){
							   continue;
						   }
						   BigDecimal  oncekiDe=new BigDecimal(0);
						   BigDecimal   cariDe=new BigDecimal(0);
						   model.setCari(cariDe);
						   model.setOnceki(oncekiDe);
						
						   if(onceki.getCellType()==Cell.CELL_TYPE_NUMERIC){
							   oncekiDe=new BigDecimal(onceki.getNumericCellValue());
							   model.setOnceki(oncekiDe.setScale(4, RoundingMode.HALF_UP));
						   }
						   if(cari.getCellType()==Cell.CELL_TYPE_NUMERIC){
							   cariDe=new BigDecimal(cari.getNumericCellValue());
							   model.setCari(cariDe.setScale(4, RoundingMode.HALF_UP));
						   
						   }
						   list.add(model);
					   }
				
			}
			i++;
		}
      return list;
		
	}
public HashMap<String, GelirBilancoExcelModel> read(){

	HashMap<String, GelirBilancoExcelModel> map=new HashMap<String, GelirBilancoExcelModel>();

		HSSFSheet bilanco = (HSSFSheet) workBook.getSheet("BILANCO");

		for (Row row : bilanco) {
			for(GelirBilancoExcelModel excelModel:parseToGelirBilancoModel(row)){
				map.put(excelModel.getCode()+"", excelModel);
			}
			
		}
		
		HSSFSheet gelirTablosu = (HSSFSheet) workBook.getSheet("GELIR_TABLOSU");

		for (Row row : gelirTablosu) {
			for(GelirBilancoExcelModel excelModel:parseToGelirBilancoModel(row)){
				map.put(excelModel.getCode()+"", excelModel);
			}
			
		}
		
		
return map;
}
}
