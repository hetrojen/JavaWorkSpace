package com.oley.payment.mobile.request.processor;

import java.math.BigDecimal;
import java.util.List;

import com.oley.payment.mobile.error.PaymentRequestProcessErrorCodes;
import com.oley.payment.mobile.error.PaymentRequestProcessException;
import com.oley.payment.mobile.request.model.PaymentRequest;
import com.oley.payment.mobile.request.processor.util.PaymentOptions;

public class AveaPaymentRequestProcessor extends PaymentRequestProcessor {

	public AveaPaymentRequestProcessor(PaymentRequest paymentRequest) {
		super(paymentRequest);
		// TODO Auto-generated constructor stub
	}

	
	protected void validate() throws PaymentRequestProcessException{
		// TODO Auto-generated method stub
		super.validate();
		BigDecimal limitOfPAyment=PaymentOptions.getAveaPaymentOptions().getPaymentLimitForEachRequest();
		if(paymentRequest.getAmountOfPayment().compareTo(limitOfPAyment)==1){
			PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.EXCEED_PAYMENT_LIMIT);
			throw processException;
		}
		
		loadPaymentRequestUserProfile();
		List<String>  previousGmsNubers=paymentRequest.getUserProfile().getDifferentGsmNumbers(PaymentOptions.AVEA);
		
		if(previousGmsNubers.size()>PaymentOptions.getAveaPaymentOptions().getNumberOfAllowedDifferentGsmNum()){
			PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.EXCEED_ALLOWED_DIFFERENT_PHONE_NUMBER);
			throw processException;
		}
		
		if(previousGmsNubers.size()==PaymentOptions.getAveaPaymentOptions().getNumberOfAllowedDifferentGsmNum()){
			  if(!previousGmsNubers.contains(paymentRequest.getSenderGSM())){
					PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.EXCEED_ALLOWED_DIFFERENT_PHONE_NUMBER);
					throw processException; 
			  }
		}
		String gsmNumber=paymentRequest.getSenderGSM(); 
		BigDecimal  paymentLimitForEachGsm=PaymentOptions.getAveaPaymentOptions().getTotalPaymentLimitForEachGsm();
		
		BigDecimal  totalPaymentForGSm=paymentRequest.getUserProfile().getTotalAmountOfPaymentByNumber(PaymentOptions.AVEA, gsmNumber);
	
		BigDecimal sumOfPaymentForgsm= totalPaymentForGSm.add(paymentRequest.getAmountOfPayment());
		
		if(sumOfPaymentForgsm.compareTo(paymentLimitForEachGsm)==1){
			PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.EXCEED_TOTAL_PAYMENT_AMOUNT);
			throw processException;
		}
		
	}

	@Override
	public void process() {
		
		try {
		    validate();
		    if(PaymentOptions.getAveaPaymentOptions().getSaleMethod()==PaymentOptions.SALE_WITH_CONFIRM){
		    	saleWithConfirm();
		    }else if(PaymentOptions.getAveaPaymentOptions().getSaleMethod()==PaymentOptions.SALE_WITHOUT_CONFIRM){
		    	saleWihoutConfirm();
		    }
		
			moveProcessedRequest();
			} 
		
			catch (PaymentRequestProcessException e) {
				// TODO: handle exception
			}
			catch (Exception e) {
				// TODO: handle exception
			}	
		

	}
	
	
	
	

}
