package com.tibco.asyncaccountinvestigation.export.model;

import java.util.ArrayList;

public class Row {
	ArrayList<String> values = new ArrayList<String>();
	
	public void appendValue(String value){
		values.add(value);
	}

	public String[] getValues(){
		return values.toArray(new String[0]);
	}

	
}
