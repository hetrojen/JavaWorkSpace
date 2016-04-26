package com.banking.sca.exceptionhandler;

import java.util.HashMap;

import noNamespace.Attribute;
import noNamespace.Event;

import org.osoa.sca.annotations.Destroy;
import org.osoa.sca.annotations.Init;

import tr.com.ingbank.schemas.BPMInsertExceptionLogCompositionRequestDocument;
import tr.com.ingbank.schemas.BPMInsertExceptionLogCompositionResponseDocument;

import com.banking.sca.exceptionhandler.util.EmailCounter;
import com.banking.sca.exceptionhandler.util.LoggerUtil;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.SendEmailRequestDocument;

/**
 * Implementation of ExceptionHandler component.
 *
 */
public class ExceptionHandler extends AbstractExceptionHandler {

	private static int SYSTEM_EXCEPTION_HANDLER_PROFILE = 1;
	private HashMap<String, String> attributeMap=new HashMap<String,String>();
    private static HashMap<String, EmailCounter> emailCounterMap;
    private String taskName="";
    private String moduleName=""; 
    private String messageId="";

	/**
	 * Initialization of ExceptionHandler component.
	 */
	@Init
	public void init() {
		// Component initialization code.
		// All properties are initialized and references are injected.
		// The Reference cannot be invoked unless "Start Service First" policy has been applied on it.
		// Component initialization code.
		if(emailCounterMap==null){
			emailCounterMap=new HashMap<String, EmailCounter> ();
		}
	}

	/**
	 * Disposal of ExceptionHandler component.
	 */
	@Destroy
	public void destroy() {
		// Component disposal code.
		// All properties are disposed.
	}

	/**
	 * Implementation of the WSDL operation: receiveEvent	 */
	public void receiveEvent(Event parameters) {
		// Add the business logic here
		
		for(Attribute attirubute:parameters.getAttributeArray()) {
	          attributeMap.put(attirubute.getAttributeName(), attirubute.getValue());
		 }
	
		  try{
			  this.messageId=parameters.getMessageId();
		      this.moduleName=attributeMap.get("moduleName");
		      if(attributeMap.get("applicationActivityName")!=null 
		  	    && !attributeMap.get("applicationActivityName").trim().equals(""))
		      {
		        this.taskName=attributeMap.get("applicationActivityName");
		      }else{
		        this.taskName=attributeMap.get("managedObjectName");  
		      }
		    	  		     
		    }catch(Exception e){
		    	
		    }
		  
		if(moduleName==null || moduleName.trim().equals("") || taskName==null || taskName.trim().equals("")){
			LoggerUtil.logger.error("ModuleName or TaskName  can not be empty.");
			 return;
		}
		  
		  
		LoggerUtil.logger.info("Inserting the Log to Komposition.");
		insertLog(parameters);	
		
		LoggerUtil.logger.info("Retrieving Email Adresses..");
		RetrieveEmailAdressesResponseDocument retrieveEmailAdressesResponse=setEmailAdresses();	
		
		LoggerUtil.logger.info("Sending Error Email...");
		sendEmail(retrieveEmailAdressesResponse);
		return;
	}
	 private RetrieveEmailAdressesResponseDocument setEmailAdresses(){
		  
	    	RetrieveEmailAdressesRequestDocument requestDoc=RetrieveEmailAdressesRequestDocument.Factory.newInstance();
	    	requestDoc.addNewRetrieveEmailAdressesRequest();
			requestDoc.getRetrieveEmailAdressesRequest().setModuleName(this.moduleName);
			requestDoc.getRetrieveEmailAdressesRequest().setTaskName(this.taskName);			
			requestDoc.getRetrieveEmailAdressesRequest().setProfileType(SYSTEM_EXCEPTION_HANDLER_PROFILE);
			RetrieveEmailAdressesResponseDocument retrieveEmailResp=getIngBpmUtil().retrieveEmailAdresses(requestDoc);
			return retrieveEmailResp;
	    }
	  private void sendEmail(RetrieveEmailAdressesResponseDocument parameter){
		  
		  if(parameter==null){
			  LoggerUtil.logger.error("There is no email profile definition for the moduleName:"+moduleName+" taskName:"+taskName);
			  return;
		  }
		  
		  int numberOfEmailsPerhour=parameter.getRetrieveEmailAdressesResponse().getNumberOfEmailsPerHour();
		  
		  if(sendEmailLimitExceed(numberOfEmailsPerhour)){
			  LoggerUtil.logger.error("Send email limit has exceed for the moduleName:"+moduleName+" taskName:"+taskName);
			  return;
		  }
		  
		  
		  String subjectTemplate=parameter.getRetrieveEmailAdressesResponse().getSubjectTemplate();
		  String messageTemplate=parameter.getRetrieveEmailAdressesResponse().getMessageTemplate();
		  String toList=parameter.getRetrieveEmailAdressesResponse().getTo();
		  String ccList=parameter.getRetrieveEmailAdressesResponse().getCc();
		  String mailFrom=parameter.getRetrieveEmailAdressesResponse().getFrom();
		
		  String subject="";
		  String message="";
       
		  subject=convertTemplate(subjectTemplate);
		  message=convertTemplate(messageTemplate);
		  subject+=" EnviromentName..:"+getEnviromentName();
		  message+="\n EnviromentName..:"+getEnviromentName(); 
		  
		  
		  try{
		  SendEmailRequestDocument  sendEmailRequest= SendEmailRequestDocument.Factory.newInstance();
		  sendEmailRequest.addNewSendEmailRequest();
		  sendEmailRequest.getSendEmailRequest().setSubject(subject);
		  sendEmailRequest.getSendEmailRequest().setMessage(message);
		  sendEmailRequest.getSendEmailRequest().setFrom(mailFrom);
		  sendEmailRequest.getSendEmailRequest().setToList(toList);
		  sendEmailRequest.getSendEmailRequest().setCcList(ccList);
		  getIngBpmUtil().sendEmail(sendEmailRequest);
		  increaseEmailCounter();		  
		  }catch(Exception e){
			  LoggerUtil.logger.error("Error on Sending Email.");  			  
			}
		  		  
	    }
	  private boolean sendEmailLimitExceed(int limit){
		  String key=moduleName.trim()+taskName;
		  if(emailCounterMap.get(key)!=null){
			  if(emailCounterMap.get(key).getNumberOfEmailsPerHour()>=limit){
				  return true;
			  }
		  }
		  
		  return false;
	  }
	  private void increaseEmailCounter(){
		  String key=moduleName.trim()+taskName;
		  if(emailCounterMap.get(key)==null){
			EmailCounter emailCounter=new EmailCounter();
			emailCounter.increaseCounter();
			emailCounter.start();
			emailCounterMap.put(key, emailCounter);
		  }else{
			  emailCounterMap.get(key).increaseCounter();
		  }
		  
	  }
	  private void insertLog(Event parameters){
	
		String stackTrace = "";
		String extendedMessage = "";

		try {
			stackTrace = attributeMap.get("stackTrace");
			extendedMessage = attributeMap.get("extendedMessage");

		} catch (Exception e) {

		}
		   
		    try{
			BPMInsertExceptionLogCompositionRequestDocument request=BPMInsertExceptionLogCompositionRequestDocument.Factory.newInstance();
			request.addNewBPMInsertExceptionLogCompositionRequest().addNewBody();
			request.getBPMInsertExceptionLogCompositionRequest().getBody().setTibcoErrorCode(this.messageId);
			request.getBPMInsertExceptionLogCompositionRequest().getBody().setTibcoErrorDetail(extendedMessage+stackTrace);
			request.getBPMInsertExceptionLogCompositionRequest().getBody().setProcessName(this.moduleName);
			request.getBPMInsertExceptionLogCompositionRequest().getBody().setWorkItemID("WORKITEMID");
			request.getBPMInsertExceptionLogCompositionRequest().getBody().setReferenceNumber("00000");
			BPMInsertExceptionLogCompositionResponseDocument resp=getISyncReply().bPMInsertExceptionLogCompositionRequest(request);
			     
			    try{
			    	if(!resp.getBPMInsertExceptionLogCompositionResponse().getHeader().getSuccess()){
			    		LoggerUtil.logger.error("Error on Inserting logs to Composition."); 
			    		LoggerUtil.logger.error("Composition Response..."+resp.getBPMInsertExceptionLogCompositionResponse().getHeader().xmlText());
			    	}
			    }catch (Exception e) {
					// TODO: handle exception
			    	e.printStackTrace();
				}
			 
			
			    LoggerUtil.logger.info("Insert log to Composition has been successfull"); 
		    }
		    catch(Exception e){
		    	
		    	LoggerUtil.logger.error(" Error on Inserting logs to Composition."); 
		    	e.printStackTrace();
		    }
	  }
	  
	  private String convertTemplate(String template){
		  StringBuffer  stringBuffer=new StringBuffer();
		  int charIndex=0;
		  while(charIndex<template.length()){
			char temp = template.charAt(charIndex);

			if (temp != '[') {
				stringBuffer.append(temp);
				charIndex++;
			} else {

				int indexOf = template.indexOf("]", charIndex + 1);
				if (indexOf < 0) {
					charIndex++;
					stringBuffer.append(temp);
					continue;
				}

				String key = template.substring(charIndex + 1, indexOf);
				LoggerUtil.logger.debug("Key  found  in the template. key..:" + key);

				if (key.equals("nl")) // new line
				{
					stringBuffer.append('\n');
					charIndex = indexOf + 1;
					continue;
				}

				String value = attributeMap.get(key);
				if (value != null) {
					stringBuffer.append(value);
					charIndex = indexOf + 1;
					continue;
				} else {

					stringBuffer.append(template.substring(charIndex,
							indexOf + 1));
					charIndex = indexOf + 1;
					continue;
				}
			}
		}
		 return stringBuffer.toString();
	  }
	  private String getEnviromentName(){
		String envName="";
		  try{
		    GetEnviromentNameDocument req=GetEnviromentNameDocument.Factory.newInstance();
		    req.addNewGetEnviromentName().setIn("");
		    envName=getIngBpmUtil().getEnviromentName(req).getGetEnviromentNameResponse().getEnviromentName();
		  }catch(Exception e){
			  
			  
		  }
		return envName;
	  }
}
