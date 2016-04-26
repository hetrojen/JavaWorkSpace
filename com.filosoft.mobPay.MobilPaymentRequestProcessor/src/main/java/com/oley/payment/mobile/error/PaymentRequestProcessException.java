package com.oley.payment.mobile.error;

public class PaymentRequestProcessException extends Exception {
	  private String message;
	  private int errorCode;
	public PaymentRequestProcessException(int errNo){
	
		this.errorCode=errNo;
	}
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	  
	
	
}
