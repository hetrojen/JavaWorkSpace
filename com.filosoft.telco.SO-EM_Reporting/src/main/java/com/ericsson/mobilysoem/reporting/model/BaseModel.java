package com.ericsson.mobilysoem.reporting.model;

public class BaseModel {

	public String checkNull(String input){
		if(input==null){ 
			return "";
		}
		return input;
	}
}
