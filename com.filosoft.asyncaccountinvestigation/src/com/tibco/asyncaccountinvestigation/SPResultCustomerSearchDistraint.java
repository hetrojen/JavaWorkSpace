package com.tibco.asyncaccountinvestigation;

public class SPResultCustomerSearchDistraint extends SPResult {

	private  int isCustomerFound;  
	public SPResultCustomerSearchDistraint(int pRETURNSTATUS, int pRETURNCODE,
			String pADDITIONALINFO,int pCustomerFound) {
		super(pRETURNSTATUS, pRETURNCODE, pADDITIONALINFO);
		
		this.isCustomerFound=pCustomerFound;
		// TODO Auto-generated constructor stub
	}
	public int getIsCustomerFound() {
		return isCustomerFound;
	}
	public void setIsCustomerFound(int isCustomerFound) {
		this.isCustomerFound = isCustomerFound;
	}
	
}
