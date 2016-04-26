package com.tibco.asyncaccountinvestigation.export.model;

import java.util.ArrayList;

public class Results {
private String referenceID="";
ArrayList<Result> results = new ArrayList<Result>();


public void appendResult(Result result){
	results.add(result);
}

public Result[] getResults(){
	return results.toArray(new Result[0]);
}


public String getReferenceID() {
	return referenceID;
}

public void setReferenceID(String referenceID) {
	this.referenceID = referenceID;
}
}
