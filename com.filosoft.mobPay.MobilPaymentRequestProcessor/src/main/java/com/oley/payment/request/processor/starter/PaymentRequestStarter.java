package com.oley.payment.request.processor.starter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mikro_odeme.services.sale.ArrayOfMSaleProduct;
import com.mikro_odeme.services.sale.MAuthToken;
import com.mikro_odeme.services.sale.MSaleInput;
import com.mikro_odeme.services.sale.MSaleOutput;
import com.mikro_odeme.services.sale.MSaleProduct;
import com.mikro_odeme.services.sale.MSaleServiceSoap;
import com.oley.payment.mobile.request.model.PaymentRequest;
import com.oley.payment.mobile.request.processor.AveaPaymentRequestProcessor;
import com.oley.payment.mobile.request.processor.PaymentRequestProcessor;
import com.oley.payment.mobile.request.processor.TurkcellPaymentRequestProcessor;
import com.oley.payment.mobile.request.processor.db.OleyDBFacade;
import com.oley.payment.mobile.request.processor.util.PaymentOptions;

/**
 * Servlet implementation class PaymentRequestStarter
 */
public class PaymentRequestStarter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MSaleServiceSoap saleService;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentRequestStarter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		   String value = request.getParameter("paymentRequestId");  
		   PaymentRequest paymentRequest=null;
		   int paymentRequestId=0;		   
		   if(value!=null && !value.trim().equals("")){
			   try{
				   paymentRequestId=Integer.valueOf(value); 
			   }catch(Exception e){
				   
			   }
		   }
		   
		   
		   
			  paymentRequest=OleyDBFacade.getPaymentRequest(paymentRequestId);
			  PaymentRequestProcessor  paymentRequestProcessor=null;
			  if(paymentRequest!=null){
				   if(paymentRequest.getGsmOperator()==PaymentOptions.TURKCELL){
					 paymentRequestProcessor=new TurkcellPaymentRequestProcessor(paymentRequest);
				   }else if(paymentRequest.getGsmOperator()==PaymentOptions.AVEA){
					   paymentRequestProcessor=new  AveaPaymentRequestProcessor(paymentRequest); 
					   
				   }
				   
			  }
			  
		      if(paymentRequestProcessor!=null){
		    	  paymentRequestProcessor.process();
		      }
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


}
