package com.oley.payment.mobile.request.model;

import java.util.List;

public class UserProfile {
   private List<UserPreviousPaymentItem> userPaymentHistory;

public List<UserPreviousPaymentItem> getUserPaymentHistory() {
	return userPaymentHistory;
}

public void setUserPaymentHistory(
		List<UserPreviousPaymentItem> userPaymentHistory) {
	this.userPaymentHistory = userPaymentHistory;
}
   
   
   
}
