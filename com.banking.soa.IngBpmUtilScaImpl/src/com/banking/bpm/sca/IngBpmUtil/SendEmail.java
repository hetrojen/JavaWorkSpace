package com.banking.bpm.sca.IngBpmUtil;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.osoa.sca.annotations.Destroy;
import org.osoa.sca.annotations.Init;

import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameDocument;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.SendEmailRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.SendEmailRequestDocument.SendEmailRequest;

/**
 * Implementation of SendEmail component.
 * 
 */
public class SendEmail extends AbstractSendEmail {

	/**
	 * Initialization of SendEmail component.
	 */
	@Init
	public void init() {
		// Component initialization code.
		// All properties are initialized and references are injected.
	}

	/**
	 * Disposal of SendEmail component.
	 */
	@Destroy
	public void destroy() {
		// Component disposal code.
		// All properties are disposed.
	}

	/**
	 * Implementation of the WSDL operation: getEnviromentName
	 */
	public GetEnviromentNameResponseDocument getEnviromentName(
			GetEnviromentNameDocument parameters) {
		// Add the business logic here
		return null;
	}

	/**
	 * Implementation of the WSDL operation: sendEmail
	 */
	public void sendEmail(SendEmailRequestDocument parameters) {
		// Add the business logic here
		SendEmailRequest request = parameters.getSendEmailRequest();

		Transport transport = null;
		try {
			Session session = getSmtpservice();
			transport = session.getTransport();
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(request.getFrom()));
			
			InternetAddress dests[] = convertEmailList(request.getToList());
			message.setRecipients(Message.RecipientType.TO, dests);

			InternetAddress destsCC[] =convertEmailList(request.getCcList());
			message.setRecipients(Message.RecipientType.CC, destsCC);

			message.setSubject(request.getSubject());
			message.setDataHandler(new DataHandler(new ByteArrayDataSource(
					request.getMessage(), "text/plain")));
			transport.connect();

			LoggerUtil.logger.info("Email is sending...:");
			LoggerUtil.logger.info("Email from...:" + request.getFrom());
			LoggerUtil.logger.info("Email to..:" + request.getToList());
			LoggerUtil.logger.info("Email cc..:" + request.getCcList());
			LoggerUtil.logger.info("Email subject..:" + request.getSubject());

			transport.sendMessage(message, dests);			
			
			return;
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if (transport != null)
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}

		}

	}
	
	private InternetAddress[] convertEmailList(String address) throws Exception{
		
		if(address.indexOf(";")<0){
			
			InternetAddress dest[]=new InternetAddress[]{
					new InternetAddress(address)
			};
			return dest; 
		}
		
		
		StringTokenizer addressTokenStr = new StringTokenizer(address, ";");
		
		ArrayList<InternetAddress> tempList=new ArrayList<InternetAddress>();
		
		while (addressTokenStr.hasMoreElements()) {
			String token=(String) addressTokenStr.nextElement();
			if(token!=null && !token.trim().equals("")){
				tempList.add(new InternetAddress(token));
			}
			
		}
		
		InternetAddress dest[]=new InternetAddress[tempList.size()];
		
		return tempList.toArray(dest);
		
	}

	@Override
	public RetrieveEmailAdressesResponseDocument retrieveEmailAdresses(
			RetrieveEmailAdressesRequestDocument parameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
