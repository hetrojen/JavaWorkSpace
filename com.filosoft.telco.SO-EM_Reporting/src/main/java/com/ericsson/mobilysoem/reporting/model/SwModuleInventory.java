package com.ericsson.mobilysoem.reporting.model;

public class SwModuleInventory extends BaseModel{
	private String nodeName;
	private String id;
	private String minimumSWVersion;
	private String typeOfSWUnit;
	private String swProductNumber;
	private String versionOfSW;
	private String hw_Module_ID;
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMinimumSWVersion() {
		return minimumSWVersion;
	}
	public void setMinimumSWVersion(String minimumSWVersion) {
		this.minimumSWVersion = minimumSWVersion;
	}
	public String getTypeOfSWUnit() {
		return typeOfSWUnit;
	}
	public void setTypeOfSWUnit(String typeOfSWUnit) {
		this.typeOfSWUnit = typeOfSWUnit;
	}
	public String getSwProductNumber() {
		return swProductNumber;
	}
	public void setSwProductNumber(String swProductNumber) {
		this.swProductNumber = swProductNumber;
	}
	public String getVersionOfSW() {
		return versionOfSW;
	}
	public void setVersionOfSW(String versionOfSW) {
		this.versionOfSW = versionOfSW;
	}
	public String getHw_Module_ID() {
		return hw_Module_ID;
	}
	public void setHw_Module_ID(String hw_Module_ID) {
		this.hw_Module_ID = hw_Module_ID;
	}

}
