package com.oley.payment.mobile.request.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class UserProfile {
   private List<UserPreviousPaymentItem> userPaymentHistoryAvea;
   private List<UserPreviousPaymentItem> userPaymentHistoryTurkcell;

   
public List<UserPreviousPaymentItem> getUserPaymentHistoryAvea() {
	return userPaymentHistoryAvea;
}
public void setUserPaymentHistoryAvea(
		List<UserPreviousPaymentItem> userPaymentHistoryAvea) {
	this.userPaymentHistoryAvea = userPaymentHistoryAvea;
}
public List<UserPreviousPaymentItem> getUserPaymentHistoryTurkcell() {
	return userPaymentHistoryTurkcell;
}
public void setUserPaymentHistoryTurkcell(
		List<UserPreviousPaymentItem> userPaymentHistoryTurkcell) {
	this.userPaymentHistoryTurkcell = userPaymentHistoryTurkcell;
}
public List<String> getDifferentGsmNumbers(int operatorType){
	return null;
	
}
public BigDecimal getTotalAmountOfPaymentByNumber(int operatorType, String gsmNumber){
	
   return null;
}
   
}
