package com.tibco.asyncaccountinvestigation;

public class TestPDFCreation {

	public static void main(){
		QueryAccountInvestigation t = new QueryAccountInvestigation();
		t.extractPdfFile("1001");
	}
}
