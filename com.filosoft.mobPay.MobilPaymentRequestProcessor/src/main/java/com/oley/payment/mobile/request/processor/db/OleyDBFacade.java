package com.oley.payment.mobile.request.processor.db;

import com.oley.payment.mobile.request.model.PaymentRequest;

public class OleyDBFacade {

	public  static boolean isMemberIdBelongsToMember(int oleyNumber){
		return false;
	}
	public  static boolean isTcKimlikBelongsToMember(String tcKimlik){
		return false;
	}
	public static  boolean isPhoneNumberBelongToMember(int oleyNumber, String gsmNo){
	
		return false;
	}
	public static  boolean isPhoneNumberBelongToTcKimlik(String tcKimlik, String gsmNo){
		
		return false;
	}
	public static PaymentRequest  getPaymentRequest(int paymentRequestId){
		
		return null;
				
	}
}
