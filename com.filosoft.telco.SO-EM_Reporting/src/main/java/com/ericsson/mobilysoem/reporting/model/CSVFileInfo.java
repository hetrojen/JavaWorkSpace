package com.ericsson.mobilysoem.reporting.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVFileInfo implements Comparable<CSVFileInfo>{
   public static int HW_MODULE_INVENTORY=1;
   public static int SW_MODULE_INVENTORY=2;
   public static int CONFIG_DATA_MINILINKTN_GENERIC=3;
   public static int CONFIG_DATAMINILINKTN_MMU2BC=4;
   public static int CONFIG_DATAMINILINKTN_OSPF=5;
   private Date date;
private String fileName="";
private int dataModelType;
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public int getDataModelType() {
	return dataModelType;
}
public void setDataModelType(int dataModelType) {
	this.dataModelType = dataModelType;
}
public String getDayStamp(){
	int index=fileName.lastIndexOf("_"); 
	if(index==-1){
		return null;
	}
	String str=fileName.substring(0, index);
	int index2=str.lastIndexOf("_");
	if(index2==-1){
		return null;
	}
	return fileName.substring(index2+1,index);
}
public String getTimeStamp(){
	int index=fileName.lastIndexOf("_"); 
	if(index==-1){
		return null;
	}
	return fileName.substring(index+1); 
}
public Date  getFileDate(String stamp){
	DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	Date date;
	try {
		date = format.parse(stamp);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return date;
}
public int compareTo(CSVFileInfo p) {

	return -1*this.date.compareTo(p.getDate());
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

}
