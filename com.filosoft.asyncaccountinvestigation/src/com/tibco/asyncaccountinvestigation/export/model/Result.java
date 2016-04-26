package com.tibco.asyncaccountinvestigation.export.model;

import java.util.ArrayList;

public class Result {
	String tableName=null;
	ArrayList<String> header = new ArrayList<String>();
	ArrayList<Row> rows = new ArrayList<Row>();

	public void appendHeaderValue(String value){
		header.add(value);
	}

	public String[] getHeaderValues(){
		return header.toArray(new String[0]);
	}
	
	public void appendRow(Row row){
		rows.add(row);
	}

	public Row[] getRows(){
		return rows.toArray(new Row[0]);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
