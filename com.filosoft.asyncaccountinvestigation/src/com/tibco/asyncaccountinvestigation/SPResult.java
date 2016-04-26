package com.tibco.asyncaccountinvestigation;

public class SPResult {
	  int PRETURNSTATUS;
	  int PRETURNCODE;
	  String PADDITIONALINFO;
	  
	  public SPResult(int pRETURNSTATUS, int pRETURNCODE, String pADDITIONALINFO) {
		super();
		PRETURNSTATUS = pRETURNSTATUS;
		PRETURNCODE = pRETURNCODE;
		PADDITIONALINFO = pADDITIONALINFO;
	}
	//PPARAMS
	public int getPRETURNSTATUS() {
		return PRETURNSTATUS;
	}
	public void setPRETURNSTATUS(int pRETURNSTATUS) {
		PRETURNSTATUS = pRETURNSTATUS;
	}
	public int getPRETURNCODE() {
		return PRETURNCODE;
	}
	public void setPRETURNCODE(int pRETURNCODE) {
		PRETURNCODE = pRETURNCODE;
	}
	public String getPADDITIONALINFO() {
		return PADDITIONALINFO;
	}
	public void setPADDITIONALINFO(String pADDITIONALINFO) {
		PADDITIONALINFO = pADDITIONALINFO;
	}
	
	public String toString(){
		return "Stored Procedure Result: ["+PRETURNSTATUS+":"+PRETURNCODE+":"+PADDITIONALINFO+"]";
	}
}
