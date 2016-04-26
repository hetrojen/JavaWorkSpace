package com.oley.payment.mobile.request.processor.util;

import java.math.BigDecimal;

public class PaymentOptions {
	public static  int SALE_WITH_CONFIRM=1;
	public static  int SALE_WITHOUT_CONFIRM=2;	
	public static  int AVEA=1;
	public static int TURKCELL=2; 
     private   BigDecimal paymentLimitForEachRequest;
     private   BigDecimal totalPaymentLimitForEachGsm;
     private   int        periodLenght;
     private   int        numberOfAllowedDifferentGsmNum;
     private   int        saleMethod;
	public BigDecimal getPaymentLimitForEachRequest() {
		return paymentLimitForEachRequest;
	}
	public void setPaymentLimitForEachRequest(BigDecimal paymentLimitForEachRequest) {
		this.paymentLimitForEachRequest = paymentLimitForEachRequest;
	}
	public BigDecimal getTotalPaymentLimitForEachGsm() {
		return totalPaymentLimitForEachGsm;
	}
	public void setTotalPaymentLimitForEachGsm(
			BigDecimal totalPaymentLimitForEachGsm) {
		this.totalPaymentLimitForEachGsm = totalPaymentLimitForEachGsm;
	}
	public int getPeriodLenght() {
		return periodLenght;
	}
	public void setPeriodLenght(int periodLenght) {
		this.periodLenght = periodLenght;
	}
	public int getNumberOfAllowedDifferentGsmNum() {
		return numberOfAllowedDifferentGsmNum;
	}
	public void setNumberOfAllowedDifferentGsmNum(int numberOfAllowedDifferentGsmNum) {
		this.numberOfAllowedDifferentGsmNum = numberOfAllowedDifferentGsmNum;
	}
         
     public int getSaleMethod() {
		return saleMethod;
	}

	private  static PaymentOptions aveaPaymentOptions;
     private  static PaymentOptions turkcellPaymentOptions;
	public static PaymentOptions getAveaPaymentOptions() {
		return aveaPaymentOptions;
	}
	public static PaymentOptions getTurkcellPaymentOptions() {
		return turkcellPaymentOptions;
	}
     
}
