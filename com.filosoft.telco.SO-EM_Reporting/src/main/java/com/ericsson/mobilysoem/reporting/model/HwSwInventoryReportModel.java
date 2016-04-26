package com.ericsson.mobilysoem.reporting.model;

public class HwSwInventoryReportModel extends BaseModel{
	 private ConfigDataMiniLinkTnGeneric configDataMiniLinkTnGeneric= new ConfigDataMiniLinkTnGeneric();
	 private HwModuleInventory hwModuleInventory=new HwModuleInventory();
	 private SwModuleInventory swModuleInventory=new SwModuleInventory();
	public ConfigDataMiniLinkTnGeneric getConfigDataMiniLinkTnGeneric() {
		return configDataMiniLinkTnGeneric;
	}
	public void setConfigDataMiniLinkTnGeneric(
			ConfigDataMiniLinkTnGeneric configDataMiniLinkTnGeneric) {
		this.configDataMiniLinkTnGeneric = configDataMiniLinkTnGeneric;
	}
	public HwModuleInventory getHwModuleInventory() {
		return hwModuleInventory;
	}
	public void setHwModuleInventory(HwModuleInventory hwModuleInventory) {
		this.hwModuleInventory = hwModuleInventory;
	}
	public SwModuleInventory getSwModuleInventory() {
		return swModuleInventory;
	}
	public void setSwModuleInventory(SwModuleInventory swModuleInventory) {
		this.swModuleInventory = swModuleInventory;
	}
	 
	 
	 
}
