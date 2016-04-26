package com.ericsson.mobilysoem.reporting.model;

public class ConfigDataMiniLinkTnMMU2BC extends BaseModel {
	
//	NEID INTEGER,Type VARCHAR(50),Protection_Mode_Admin_Status VARCHAR(30),Radio_Terminal_Name VARCHAR(50),Terminal_ID VARCHAR(50),Far_End_Terminal_Name VARCHAR(30)," +
//	"Far_End_ID VARCHAR(30),Capacity VARCHAR(30),Modulation VARCHAR(30),Freq_Band_Ra1 VARCHAR(30),Freq_Index_Ra1 VARCHAR(30),Base_TX_Frequency_RF1 VARCHAR(30),Base_RX_Frequency_RF1 VARCHAR(30),Current_Output_Power_RF1 VARCHAR(30)," +
//	"Current_Input_Power_RF1 VARCHAR(30),ATPC_Selected_Input_Power_Far_RF1 VARCHAR(30)
	
	
//	private String nodeName;
	
	
	private int neid;
	
private String neAlias="";

	private String radio_Terminal_Name="";
	
	private String terminal_ID="";
	
	
	private String type="";
	
	
	private String protection_Mode_Admin_Status="";
	
	
	private String capacity="";
	
//	private String capacity_Capability;
	

	private String modulation="";
	
//	private String access_Server_Port;
	
	
	private String far_End_Terminal_Name="";
	

	private String far_End_ID="";
	
//	private String far_End_Type;
//	private String remote_ID_Check;
//	private String Ber_Alarm_Threshold;
//	private String fade_Notification_Timer;
//	private String output_Power_Admin_Status;
//	private String atpc_Capability;
//	private String protection_RAU1_Instance;
//	private String protection_RAU2_Instance;
//	private String active_TX_Radio;
//	private String switch_Revertive_TX;
//	private String equipment_Protection_Active_Unit;
//	private String equipment_Protection_Mode;
//	private String switch_Interface_Name;
//	private String switch_Admin_Status;
//	private String switch_Line_Protection_Status;
//	private String switch_Line_Protection_Working_RX;
//	private String instance_Ra1;
//	private String admin_Status_Ra1;
//	private String atpc_Capability_Ra1;
//	private String local_Ctrl_Capability_Ra1;
//	private String modulation_Capability_Ra1;
	
	
	private String freq_Band_Ra1="";
	
	
	private String freq_Index_Ra1="";
//	private String temperature_Ra1;
//	private String protection_Path_Ra1;
//	private String instance_Ra2;
//	private String admin_Status_Ra2;
//	private String atpc_Capability_Ra2;
//	private String local_Ctrl_Capability_Ra2;
//	private String modulation_Capability_Ra2;
//	private String freq_Band_Ra2;
//	private String freq_Index_Ra2;
//	private String temperature_Ra2;
//	private String protection_Path_Ra2;
//	private String instance_RF1;
//	private String admin_Status_RF1;
	
	
	private String base_TX_Frequency_RF1="";
	

	private String base_RX_Frequency_RF1="";
	
//	private String step_Size_RF1;
//	private String step_Limit_Low_RF1;
//	private String step_Limit_High_RF1;
//	private String current_Step_Number_RF1;
//	private String tx_Oper_Status_RF1;
//	private String tx_Admin_Status_RF1;
	
	private String current_Output_Power_RF1="";
	
//	private String min_Output_Power_RF1;
//	private String max_Output_Power_RF1;
//	private String selected_Output_Power_RF1;
//	private String atpc_Min_Output_Power_RF1;
//	private String atpc_Max_Output_Power_RF1;
//	private String atpc_Min_Input_Power_Far_End_RF1;
	
	
	private String atpc_Selected_Input_Power_Far_RF1="";
	
//	private String attenuator_RF1;

	private String current_Input_Power_RF1="";
	
	private String input_Alarm_Threshold_RF1="";
//	private String instance_RF2;
//	private String admin_Status_RF2;
//	private String base_TX_Frequency_RF2;
//	private String base_RX_Frequency_RF2;
//	private String step_Size_RF2;
//	private String step_Limit_Low_RF2;
//	private String step_Limit_High_RF2;
//	private String current_Step_Number_RF2;
//	private String tx_Oper_Status_RF2;
//	private String tx_Admin_Status_RF2;
//	private String current_Output_Power_RF2;
//	private String min_Output_Power_RF2;
//	private String max_Output_Power_RF2;
//	private String selected_Output_Power_RF2;
//	private String atpc_Min_Output_Power_RF2;
//	private String atpc_Max_Output_Power_RF2;
//	private String atpc_Min_Input_Power_Far_End_RF2;
//	private String atpc_Selected_Input_Power_Far_RF2;
//	private String attenuator_RF2;
//	private String current_Input_Power_RF2;
//	private String input_Alarm_Threshold_RF2;
//	private String interface_Name_RAU_IF1;
//	private String admin_Status_RAU_IF1;
//	private String interface_Name_RAU_IF2;
//	private String admin_Status_RAU_IF2;
//	private String validity;
//	private String current_Step_Number_RX_RF1;
//	private String current_Step_Number_RX_RF2;
	private String _siteB="";

	public String getInput_Alarm_Threshold_RF1() {
		return input_Alarm_Threshold_RF1;
	}
	public void setInput_Alarm_Threshold_RF1(String input_Alarm_Threshold_RF1) {
		this.input_Alarm_Threshold_RF1 = input_Alarm_Threshold_RF1;
	}


	public String getRadio_Terminal_Name() {
		return radio_Terminal_Name;
	}
	public void setRadio_Terminal_Name(String radio_Terminal_Name) {
		this.radio_Terminal_Name = radio_Terminal_Name;
	}
	public String getTerminal_ID() {
		return terminal_ID;
	}
	public void setTerminal_ID(String terminal_ID) {
		this.terminal_ID = terminal_ID;
	}
	public String getType() {
		return checkNull(type);
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProtection_Mode_Admin_Status() {
		return protection_Mode_Admin_Status;
	}
	public void setProtection_Mode_Admin_Status(String protection_Mode_Admin_Status) {
		this.protection_Mode_Admin_Status = protection_Mode_Admin_Status;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public String getModulation() {
		return modulation;
	}
	public void setModulation(String modulation) {
		this.modulation = modulation;
	}

	public String getFar_End_Terminal_Name() {
		return far_End_Terminal_Name;
	}
	public void setFar_End_Terminal_Name(String far_End_Terminal_Name) {
		this.far_End_Terminal_Name = far_End_Terminal_Name;
	}
	public String getFar_End_ID() {
		return far_End_ID;
	}
	public void setFar_End_ID(String far_End_ID) {
		this.far_End_ID = far_End_ID;
	}
	
	public String getFreq_Band_Ra1() {
		return freq_Band_Ra1;
	}
	public void setFreq_Band_Ra1(String freq_Band_Ra1) {
		this.freq_Band_Ra1 = freq_Band_Ra1;
	}
	
	public String getBase_TX_Frequency_RF1() {
		return base_TX_Frequency_RF1;
	}
	public void setBase_TX_Frequency_RF1(String base_TX_Frequency_RF1) {
		this.base_TX_Frequency_RF1 = base_TX_Frequency_RF1;
	}
	public String getBase_RX_Frequency_RF1() {
		return base_RX_Frequency_RF1;
	}
	public void setBase_RX_Frequency_RF1(String base_RX_Frequency_RF1) {
		this.base_RX_Frequency_RF1 = base_RX_Frequency_RF1;
	}
	
	public String getCurrent_Output_Power_RF1() {
		return current_Output_Power_RF1;
	}
	public void setCurrent_Output_Power_RF1(String current_Output_Power_RF1) {
		this.current_Output_Power_RF1 = current_Output_Power_RF1;
	}
	
	public String getAtpc_Selected_Input_Power_Far_RF1() {
		return atpc_Selected_Input_Power_Far_RF1;
	}
	public void setAtpc_Selected_Input_Power_Far_RF1(
			String atpc_Selected_Input_Power_Far_RF1) {
		this.atpc_Selected_Input_Power_Far_RF1 = atpc_Selected_Input_Power_Far_RF1;
	}

	public String getCurrent_Input_Power_RF1() {
		return current_Input_Power_RF1;
	}
	public void setCurrent_Input_Power_RF1(String current_Input_Power_RF1) {
		this.current_Input_Power_RF1 = current_Input_Power_RF1;
	}
	public String getFreq_Index_Ra1() {
		return freq_Index_Ra1;
	}
	public void setFreq_Index_Ra1(String freq_Index_Ra1) {
		this.freq_Index_Ra1 = freq_Index_Ra1;
	}
	public String getNeAlias() {
		return neAlias;
	}
	public void setNeAlias(String neAlias) {
		this.neAlias = neAlias;
	}
	public String get_siteB() {
		return _siteB;
	}
	public void set_siteB(String _siteB) {
		this._siteB = _siteB;
	}
	public int getNeid() {
		return neid;
	}
	public void setNeid(int neid) {
		this.neid = neid;
	}
	

}
