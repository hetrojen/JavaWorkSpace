package com.oley.payment.mobile.request.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import tr.com.mikro_odeme.cservices.mapping.generated.CReceiveMOMessageInput;

public class PaymentRequest {
	
	
	//private int   paymentRequestId;
    private String receivedSMSId;
   
    private String senderGSM="";
  
    private String receiverGSM="";
   
    private String originalContent="";
   
    private String fixedContent="";
  
	private Date dateReceived;
    private Date  dateRequestReceived;
    private Date   updateTime;
    private  int oleyMemberId;
    private BigDecimal amountOfPayment; 
    private short state;
    private int gsmOperator;
     
    private int gsmType;

//	public int getPaymentRequestId() {
//		return paymentRequestId;
//	}
//
//	public void setPaymentRequestId(int paymentRequestId) {
//		this.paymentRequestId = paymentRequestId;
//	}

	public String getReceivedSMSId() {
		return receivedSMSId;
	}

	public void setReceivedSMSId(String receivedSMSId) {
		this.receivedSMSId = receivedSMSId;
	}

	public String getSenderGSM() {
		return senderGSM;
	}

	public void setSenderGSM(String senderGSM) {
		this.senderGSM = senderGSM;
	}

	public String getReceiverGSM() {
		return receiverGSM;
	}

	public void setReceiverGSM(String receiverGSM) {
		this.receiverGSM = receiverGSM;
	}

	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public String getFixedContent() {
		return fixedContent;
	}

	public void setFixedContent(String fixedContent) {
		this.fixedContent = fixedContent;
	}




	public int getOleyMemberId() {
		return oleyMemberId;
	}

	public void setOleyMemberId(int oleyMemberId) {
		this.oleyMemberId = oleyMemberId;
	}





	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public int getGsmOperator() {
		return gsmOperator;
	}

	public void setGsmOperator(int gsmOperator) {
		this.gsmOperator = gsmOperator;
	}

	public int getGsmType() {
		return gsmType;
	}

	public void setGsmType(int gsmType) {
		this.gsmType = gsmType;
	}
	
	
	

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Date getDateRequestReceived() {
		return dateRequestReceived;
	}

	public void setDateRequestReceived(Date dateRequestReceived) {
		this.dateRequestReceived = dateRequestReceived;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getAmountOfPayment() {
		return amountOfPayment;
	}

	public void setAmountOfPayment(BigDecimal amountOfPayment) {
		this.amountOfPayment = amountOfPayment;
	}

	public static  PaymentRequest convertSMStoObject(CReceiveMOMessageInput input){
		PaymentRequest  paymentRequest=new PaymentRequest();

		paymentRequest.setReceivedSMSId(input.getReceivedSMSId());
		paymentRequest.setReceiverGSM(input.getReceiverGSM());

		XMLGregorianCalendar xcal = input.getDateReceived();
		java.util.Date dt = xcal.toGregorianCalendar().getTime();
		java.sql.Date sqlDt = new java.sql.Date(dt.getTime());

		paymentRequest.setDateReceived(sqlDt);
		
		paymentRequest.setDateRequestReceived(new java.sql.Date((new java.util.Date()).getTime()));
		
		paymentRequest.setUpdateTime(new java.sql.Date((new java.util.Date()).getTime()));
		paymentRequest.setFixedContent(input.getFixedContent());
		paymentRequest.setGsmOperator(input.getGsmOperator());
		paymentRequest.setGsmType(input.getGsmType());
		paymentRequest.setOriginalContent(input.getOriginalContent());
		paymentRequest.setSenderGSM(input.getSenderGSM());
		return paymentRequest; 
		
	}
}
