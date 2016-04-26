package com.oley.payment.mobile.request.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public  class PaymentRequest {
	private List<PaymentRequestProcessStep> paymentRequestProcessings;
  private UserProfile userProfile;
 // private int oleyNumber;
  private String tcKimlik;
 // private String gsmNo;
  private BigDecimal amountOfPayment;
  //private int  operatorType;
  
  private String receivedSMSId;
  
  private String senderGSM="";

  private String receiverGSM="";
 
  private String originalContent="";
 
  private String fixedContent="";
private String productDescription;
	private Date dateReceived;
  private Date  dateRequestReceived;
  private Date   updateTime;
  private  int oleyMemberId;
 // private BigDecimal amount; 
  private short state;
  private int gsmOperator;
   
  private int gsmType;
public UserProfile getUserProfile() {
	return userProfile;
}

public void setUserProfile(UserProfile userProfile) {
	this.userProfile = userProfile;
}

public List<PaymentRequestProcessStep> getPaymentRequestProcessings() {
	return paymentRequestProcessings;
}

public void setPaymentRequestProcessings(
		List<PaymentRequestProcessStep> paymentRequestProcessings) {
	this.paymentRequestProcessings = paymentRequestProcessings;
}



public String getTcKimlik() {
	return tcKimlik;
}

public void setTcKimlik(String tcKimlik) {
	this.tcKimlik = tcKimlik;
}


public BigDecimal getAmountOfPayment() {
	return amountOfPayment;
}

public void setAmountOfPayment(BigDecimal amountOfPayment) {
	this.amountOfPayment = amountOfPayment;
}

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

public String getProductDescription() {
	return productDescription;
}

public void setProductDescription(String productDescription) {
	this.productDescription = productDescription;
}


  
}
