package com.ericsson.mobilysoem.reporting.model;

public class HopDetailReportModel extends BaseModel{
 private ConfigDataMiniLinkTnGeneric configDataMiniLinkTnGeneric=new ConfigDataMiniLinkTnGeneric();
 private ConfigDataMiniLinkTnOSPF configDataMiniLinkTnOSPF= new ConfigDataMiniLinkTnOSPF();
 private ConfigDataMiniLinkTnMMU2BC configDataMiniLinkTnMMU2BC= new ConfigDataMiniLinkTnMMU2BC();

public ConfigDataMiniLinkTnGeneric getConfigDataMiniLinkTnGeneric() {
	return configDataMiniLinkTnGeneric;
}
public void setConfigDataMiniLinkTnGeneric(
		ConfigDataMiniLinkTnGeneric configDataMiniLinkTnGeneric) {
	this.configDataMiniLinkTnGeneric = configDataMiniLinkTnGeneric;
}
public ConfigDataMiniLinkTnOSPF getConfigDataMiniLinkTnOSPF() {
	return configDataMiniLinkTnOSPF;
}
public void setConfigDataMiniLinkTnOSPF(
		ConfigDataMiniLinkTnOSPF configDataMiniLinkTnOSPF) {
	this.configDataMiniLinkTnOSPF = configDataMiniLinkTnOSPF;
}
public ConfigDataMiniLinkTnMMU2BC getConfigDataMiniLinkTnMMU2BC() {
	return configDataMiniLinkTnMMU2BC;
}
public void setConfigDataMiniLinkTnMMU2BC(
		ConfigDataMiniLinkTnMMU2BC configDataMiniLinkTnMMU2BC) {
	this.configDataMiniLinkTnMMU2BC = configDataMiniLinkTnMMU2BC;
}

 
}
