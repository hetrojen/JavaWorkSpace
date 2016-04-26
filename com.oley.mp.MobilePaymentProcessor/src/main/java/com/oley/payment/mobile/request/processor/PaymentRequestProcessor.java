package com.oley.payment.mobile.request.processor;

import com.oley.payment.mobile.request.model.PaymentRequest;

public abstract class PaymentRequestProcessor {
   private PaymentRequest paymentRequest;
	  public PaymentRequestProcessor(PaymentRequest paymentRequest) {
		// TODO Auto-generated constructor stub
		  this.paymentRequest=paymentRequest;
	}
	
	abstract  void validate();
	abstract void  process();
}
