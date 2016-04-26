package com.oley.payment.mobile.request.test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oley.payment.mobile.request.dao.PaymentRequestDAO;
import com.oley.payment.mobile.request.model.PaymentRequest;

public class InsertPaymentRequestTester {
	
	public static void testInsert(PaymentRequestDAO paymentRequestDAO) {
//		ApplicationContext context = 
//	    		new ClassPathXmlApplicationContext("beans.xml");
		
		Date   today=new Date();
		long today_milisec=today.getTime();
//		PaymentRequestDAO paymentRequestDAO=(PaymentRequestDAO)context.getBean("PaymentRequestDAO");
	
	   PaymentRequest paymentRequest=new PaymentRequest();
	   paymentRequest.setReceivedSMSId("b5b45c87-82b8-4f04-a7eb-b2db0cd51885");
//	   paymentRequest.setDateReceived(new Timestamp(today_milisec));
//	   paymentRequest.setDateRequestReceived(new Timestamp(today_milisec));
//	   paymentRequest.setUpdateTime(new Timestamp(today_milisec));
	   paymentRequest.setDateReceived(null);
	   paymentRequest.setDateRequestReceived(null);
	   paymentRequest.setUpdateTime(null);
	   paymentRequest.setOleyMemberId(11122288);
	   paymentRequest.setGsmType(1);
	   paymentRequest.setGsmOperator(2);
	   paymentRequest.setFixedContent("aksdlkjsalkdasd laksdlkjaslk");
	   paymentRequest.setOriginalContent("serkan hell 4334");
	   paymentRequest.setAmountOfPayment(new BigDecimal("23.22"));
	   paymentRequest.setSenderGSM("053226655985");
	   paymentRequest.setReceiverGSM("39289389283");
	   paymentRequest.setState((short) 3);
	   
	paymentRequestDAO.insertPaymentRequest(paymentRequest);
	}

}
