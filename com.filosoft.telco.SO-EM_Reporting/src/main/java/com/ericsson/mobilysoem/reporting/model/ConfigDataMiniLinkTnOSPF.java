package com.ericsson.mobilysoem.reporting.model;

import java.util.List;

public class ConfigDataMiniLinkTnOSPF extends BaseModel{
//create table configDataMiniLinkTnOSPFs (NodeName VARCHAR(100),NEID INTEGER, Net_Address VARCHAR(30),Subnet_Mask VARCHAR(30),Area_ID VARCHAR(30) ,Area_Type VARCHAR(30),Validity VARCHAR(30));";
	
	
	private String nodeName="";
	
	private int neid;
	
	private String net_Address="";
	
	private String subnet_Mask="";
	
	private String area_ID="";

	private String area_Type="";
	
	private List<String> net_Address_List;
	
	private List<String>  subnet_Mask_List;
	
	private List<String> area_ID_List;

	private List<String>  area_Type_List;
	
	private String validity;
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getNeid() {
		return neid;
	}
	public void setNeid(int neid) {
		this.neid = neid;
	}
	public String getNet_Address() {
		if(net_Address_List==null || net_Address_List.size()==0){
			return net_Address;
		}
		else{
			String tempt=net_Address;
			for(String net_ad:net_Address_List){
				tempt+="/"+net_ad;
			}
			return tempt;
		}
	}
	public void setNet_Address(String net_Address) {
		this.net_Address = net_Address;
	}
	public String getSubnet_Mask() {
		
		if(subnet_Mask_List==null || subnet_Mask_List.size()==0){
			return subnet_Mask;
		}
		else{
			String tempt=subnet_Mask;
			for(String sub_mask:subnet_Mask_List){
				tempt+="/"+sub_mask;
			}
			return tempt;
		}
	
	}
	public void setSubnet_Mask(String subnet_Mask) {
		this.subnet_Mask = subnet_Mask;
	}
	public String getArea_ID() {
		
		if(area_ID_List==null || area_ID_List.size()==0){
			return area_ID;
		}
		else{
			String tempt=area_ID;
			for(String area_id:area_ID_List){
				tempt+="/"+area_id;
			}
			return tempt;
		}
	
	}
	public void setArea_ID(String area_ID) {
		this.area_ID = area_ID;
	}
	public String getArea_Type() {
		if(area_Type_List==null || area_Type_List.size()==0){
			return area_Type;
		}
		else{
			String tempt=area_Type;
			for(String area_type:area_Type_List){
				tempt+="/"+area_type;
			}
			return tempt;
		}
	
	}
	public void setArea_Type(String area_Type) {
		this.area_Type = area_Type;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public List<String> getNet_Address_List() {
		return net_Address_List;
	}
	public void setNet_Address_List(List<String> net_Address_List) {
		this.net_Address_List = net_Address_List;
	}
	public List<String> getSubnet_Mask_List() {
		return subnet_Mask_List;
	}
	public void setSubnet_Mask_List(List<String> subnet_Mask_List) {
		this.subnet_Mask_List = subnet_Mask_List;
	}
	public List<String> getArea_ID_List() {
		return area_ID_List;
	}
	public void setArea_ID_List(List<String> area_ID_List) {
		this.area_ID_List = area_ID_List;
	}
	public List<String> getArea_Type_List() {
		return area_Type_List;
	}
	public void setArea_Type_List(List<String> area_Type_List) {
		this.area_Type_List = area_Type_List;
	}


	
	
}
