package com.oley.payment.mobile.request.processor;

import com.mikro_odeme.services.sale.SaleWithoutConfirmResponse;
import com.oley.payment.mobile.error.PaymentRequestProcessErrorCodes;
import com.oley.payment.mobile.error.PaymentRequestProcessException;
import com.oley.payment.mobile.request.model.PaymentRequest;
import com.oley.payment.mobile.request.model.PaymentRequestProcessStep;
import com.oley.payment.mobile.request.processor.db.OleyDBFacade;
import com.oley.payment.mobile.service.MobilePaymentProcessorServiceFacade;


public abstract class PaymentRequestProcessor {
   protected PaymentRequest paymentRequest;
	  public PaymentRequestProcessor(PaymentRequest paymentRequest) {
		// TODO Auto-generated constructor stub
		  this.paymentRequest=paymentRequest;
	}
	
	private void  validateWithTcKimlik(String tcKimlik,String gsmNo) throws PaymentRequestProcessException{
		
		if( !OleyDBFacade.isTcKimlikBelongsToMember(tcKimlik)){
			PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.INVALID_TC_KIMLIK_NO);
			throw processException;
		}
		if( !OleyDBFacade.isPhoneNumberBelongToTcKimlik(tcKimlik, gsmNo)){
			PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.TC_KIMLIK_NO_DOES_NOT_MATCH_WITH_PHONE_NUMBER);
			throw processException;
		}
		
	}
	private void  validateWithOleyNumber(int oleyNumber,String gsmNo) throws PaymentRequestProcessException{
		
		if(!OleyDBFacade.isMemberIdBelongsToMember(oleyNumber)){
			PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.INVALID_MEMBER_ID);
			throw processException;
		}
		if(!OleyDBFacade.isPhoneNumberBelongToMember(oleyNumber, gsmNo)){
			PaymentRequestProcessException processException=new PaymentRequestProcessException(PaymentRequestProcessErrorCodes.MEMBER_ID_DOES_NOT_MATCH_WITH_PHONE_NUMBER);
			throw processException;
		}
		
	}
	
	protected void  validate() throws PaymentRequestProcessException{
		int  oleyNumber=paymentRequest.getOleyMemberId();
		String gsmNo=paymentRequest.getSenderGSM();
		String tcKimlik=paymentRequest.getTcKimlik();
		
		if(tcKimlik==null || tcKimlik.trim().equals("")){
			validateWithOleyNumber(oleyNumber,gsmNo);
		}else{
			validateWithTcKimlik(tcKimlik,gsmNo);
		}
     
		
		
		
	
		
		
		
		
	}
	public abstract void  process();
	protected void  saleWithConfirm() throws PaymentRequestProcessException{
	
	}
	
	protected void saleWihoutConfirm() throws PaymentRequestProcessException{
		SaleWithoutConfirmResponse saleWithoutConfirmResponse=null;
		try {
		saleWithoutConfirmResponse=MobilePaymentProcessorServiceFacade.saleWithoutConfirm(paymentRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void logAndRecord(PaymentRequestProcessStep paymentRequestProcessStep) throws PaymentRequestProcessException{
		
	}
	protected  void moveProcessedRequest() throws PaymentRequestProcessException{
		
	}
	protected void loadPaymentRequestUserProfile(){
		
	}
}
