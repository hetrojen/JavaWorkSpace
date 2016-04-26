 package com.ericsson.mobilysoem.reporting.model;

public class HwModuleInventory extends BaseModel{
	//NE_ID INTEGER,AMMPosition VARCHAR(30),ProductNumber VARCHAR(30),Version,ID VARCHAR(30),TypeOfUnit VARCHAR(30)

	
//	private String NodeName;
	

	private String id="";
	
	
	private String ammPosition="";
	private int ammPositionNumber;
//	private String assetId;
	
	
	private String typeOfUnit="";
	
	private String productNumber="";
	
	private String version="";
	
//	private String serialNumber;
//	private String productionDate;	
//	private String elapsedRunTime;
	
	
	private int ne_id;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAmmPosition() {
		return ammPosition;
	}
	public void setAmmPosition(String ammPosition) {
		this.ammPosition = ammPosition;
	}

	public String getTypeOfUnit() {
		return typeOfUnit;
	}
	public void setTypeOfUnit(String typeOfUnit) {
		this.typeOfUnit = typeOfUnit;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


	public int getAmmPositionNumber() {
		return ammPositionNumber;
	}
	public void setAmmPositionNumber(int ammPositionNumber) {
		this.ammPositionNumber = ammPositionNumber;
	}
	public int getNe_id() {
		return ne_id;
	}
	public void setNe_id(int ne_id) {
		this.ne_id = ne_id;
	}
	
	
}
