package com.ericsson.mobilysoem.reporting.model;

public class ConfigDataMiniLinkTnGeneric extends BaseModel{
	
	
	//NEID INTEGER, Name VARCHAR(100) ,DCN_Host_Address VARCHAR(30),DCN_Subnet_Mask VARCHAR(30),DCN_Default_Gateway VARCHAR(30));";
	//private String nodeName;
	
	private int neid;
	
	//private String neAlias;
	
	private String name="";
	
//	private String location;
//	private String contact;
//	private String dcn_Host_Name;
	
	
	private String dcn_Host_Address="";

	private String dcn_Subnet_Mask="";
	
	private String dcn_Default_Gateway="";
	
//	private String dcn_Domain;
//	private String dcn_DNS_Server1;
//	private String dcn_DNS_Server2;
//	private String dcn_DNS_Server3;
//	private String dcn_NTP_Server;
//	private String dcn_DHCP_Address;
//	private String validity;
	 private String _ammType="";
	 private String _mpuType="";
	


	public int getNeid() {
		return neid;
	}
	public void setNeid(int neid) {
		this.neid = neid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDcn_Host_Address() {
		return dcn_Host_Address;
	}
	public void setDcn_Host_Address(String dcn_Host_Address) {
		this.dcn_Host_Address = dcn_Host_Address;
	}
	public String getDcn_Subnet_Mask() {
		return dcn_Subnet_Mask;
	}
	public void setDcn_Subnet_Mask(String dcn_Subnet_Mask) {
		this.dcn_Subnet_Mask = dcn_Subnet_Mask;
	}
	public String getDcn_Default_Gateway() {
		return dcn_Default_Gateway;
	}
	public void setDcn_Default_Gateway(String dcn_Default_Gateway) {
		this.dcn_Default_Gateway = dcn_Default_Gateway;
	}
	public String get_ammType() {
		return _ammType;
	}
	public void set_ammType(String _ammType) {
		this._ammType = _ammType;
	}
	public String get_mpuType() {
		return _mpuType;
	}
	public void set_mpuType(String _mpuType) {
		this._mpuType = _mpuType;
	}
	
	
	
	
	
}
