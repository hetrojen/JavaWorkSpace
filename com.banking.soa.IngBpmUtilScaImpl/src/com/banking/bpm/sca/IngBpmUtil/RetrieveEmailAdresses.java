package com.banking.bpm.sca.IngBpmUtil;

import org.osoa.sca.annotations.Destroy;
import org.osoa.sca.annotations.Init;

import com.banking.bpm.util.genericExceptionHandling.emailProfileCaseData.BusinessProjectProfileElementDocument;
import com.banking.bpm.util.genericExceptionHandling.emailProfileCaseData.EmailProfile;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameDocument;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.SendEmailRequestDocument;
import com.tibco.bds.api.FindAllCasesRequestDocument;
import com.tibco.bds.api.FindAllCasesResponseDocument;
import com.tibco.bds.api.ReadCaseRequestDocument;
import com.tibco.bds.api.ReadCaseResponseDocument;
import com.tibco.bds.services.CaseDataAccessFaultException;
import com.tibco.bds.services.CaseModelReferenceFaultException;
import com.tibco.bds.services.CaseReferenceFaultException;
import com.tibco.bds.services.InternalServiceFaultException;
import com.tibco.bds.services.SecurityFaultException;

/**
 * Implementation of retrieveEmailAdresses component.
 *
 */
public class RetrieveEmailAdresses extends AbstractRetrieveEmailAdresses {
	 private static BusinessProjectProfileElementDocument[] emailConfiguration;
	 private static int BUSINESS_EXCEPTION_HANDLER_PROFILE=0;
	 private static int SYSTEM_EXCEPTION_HANDLER_PROFILE=1;
	 private static String DEFAULT_PROFILE_DESCRIPTION="DefaultProfile";
	/**
	 * Initialization of retrieveEmailAdresses component.
	 */
	@Init
	public void init() {
		// Component initialization code.
		// All properties are initialized and references are injected.
		if(emailConfiguration==null){
			loadConfiguration();
		}
		
	}
	private void loadConfiguration(){

		
           String emailProfileVersion=getEmailProfileCaseDataVersion();
           String emailProfileType=getEmailProfileCaseType();
           String caseRefList[]=null;
           
           
           FindAllCasesRequestDocument findAllCasesRequestDocument=FindAllCasesRequestDocument.Factory.newInstance();
           findAllCasesRequestDocument.addNewFindAllCasesRequest();
           findAllCasesRequestDocument.getFindAllCasesRequest().setCaseModelVersion(emailProfileVersion);
           findAllCasesRequestDocument.getFindAllCasesRequest().setCaseType(emailProfileType);
           
          //  findAllCasesRequestDocument.
		
          try {
			FindAllCasesResponseDocument response=getBusinessDataServices().findAllCases(findAllCasesRequestDocument);
			caseRefList=response.getFindAllCasesResponse().getCaseReferenceArray();
			
			
		} catch (CaseModelReferenceFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InternalServiceFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CaseDataAccessFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
           
        if(caseRefList!=null){
          
         	ReadCaseRequestDocument readCaseRequest=ReadCaseRequestDocument.Factory.newInstance();
         	readCaseRequest.addNewReadCaseRequest();
         	readCaseRequest.getReadCaseRequest().setCaseReferenceArray(caseRefList);
         	
         	try {
		   	ReadCaseResponseDocument response=getBusinessDataServices().readCase(readCaseRequest);
		   	emailConfiguration= (BusinessProjectProfileElementDocument[]) response.getReadCaseResponse().getCaseDataArray();	
				
			} catch (CaseModelReferenceFaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InternalServiceFaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityFaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CaseDataAccessFaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CaseReferenceFaultException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        }  
	}
	/**
	 * Disposal of retrieveEmailAdresses component.
	 */
	@Destroy
	public void destroy() {
		// Component disposal code.
		// All properties are disposed.
		emailConfiguration=null;
	}

	/**
	 * Implementation of the WSDL operation: getEnviromentName	 */
	public GetEnviromentNameResponseDocument getEnviromentName(
			GetEnviromentNameDocument parameters) {
		// Add the business logic here
		return null;
	}
	/**
	 * Implementation of the WSDL operation: sendEmail	 */
	public void sendEmail(SendEmailRequestDocument parameters) {
		// Add the business logic here
		return;
	}
	/**
	 * Implementation of the WSDL operation: retrieveEmailAdresses	 */
	public RetrieveEmailAdressesResponseDocument retrieveEmailAdresses(
			RetrieveEmailAdressesRequestDocument parameters) {

		if(emailConfiguration==null){
			loadConfiguration();
		}
		
		if(emailConfiguration==null	|| emailConfiguration.length==0 ){
			LoggerUtil.logger.error("Filter Configuration cannot be empty.");
			return null;
		}
		
		LoggerUtil.logger.info("Retrieve Email Adresses Request has been received..:"+parameters.xmlText());
		
		boolean ruleMatch = false;
		EmailProfile emailProfileResult=null;
		EmailProfile defaultEmailProfile=null;
		
		String requestedModuleName=parameters.getRetrieveEmailAdressesRequest().getModuleName();
		int    requestedProfileType=parameters.getRetrieveEmailAdressesRequest().getProfileType();
		
		for(BusinessProjectProfileElementDocument emailProfile:emailConfiguration){
			
			String[] moduleNames=emailProfile.getBusinessProjectProfileElement().getModuleNamesArray(); 
			
			if(emailProfile.getBusinessProjectProfileElement().getProjetcName().equalsIgnoreCase(DEFAULT_PROFILE_DESCRIPTION))
			   {
				 defaultEmailProfile=getEmailProfileByType(requestedProfileType, emailProfile);
				
			   }
			
			for(String moduleElement:moduleNames)
			  {    if(moduleElement.equalsIgnoreCase(requestedModuleName));
			           { 
				         ruleMatch=true;
				         emailProfileResult=getEmailProfileByType(requestedProfileType, emailProfile);
				         break;
			           }
			  }
			
			
		
		
		if(!ruleMatch){
			emailProfileResult=defaultEmailProfile;
		}
		
		if(emailProfileResult==null){
			LoggerUtil.logger.error("There is no rule defined for the moduleName..:"+parameters.getRetrieveEmailAdressesRequest().getModuleName());
		   return null; 
		 }
		}
		String subjectTemplate=emailProfileResult.getSubjectTemplate();
		String messageTemplate=emailProfileResult.getBodyTemplate();
    	String mailFrom=getExceptionEmailFrom();
		String tolist=emailProfileResult.getToList();
		String ccList=emailProfileResult.getCcList();
	    int  numberOfEmailsPerHour=emailProfileResult.getNumberOfEmailPerHour();  
		
		RetrieveEmailAdressesResponseDocument response=RetrieveEmailAdressesResponseDocument.Factory.newInstance();
		response.addNewRetrieveEmailAdressesResponse();
		
		response.getRetrieveEmailAdressesResponse().setFrom(mailFrom);
		response.getRetrieveEmailAdressesResponse().setTo(tolist);
		response.getRetrieveEmailAdressesResponse().setCc(ccList);
		if(parameters.getRetrieveEmailAdressesRequest().getProfileType()==SYSTEM_EXCEPTION_HANDLER_PROFILE){
		 response.getRetrieveEmailAdressesResponse().setNumberOfEmailsPerHour(numberOfEmailsPerHour);
		 response.getRetrieveEmailAdressesResponse().setSubjectTemplate(subjectTemplate);
		 response.getRetrieveEmailAdressesResponse().setMessageTemplate(messageTemplate);
		}		
		LoggerUtil.logger.info(response.xmlText());
		return response;
	}
	
	private EmailProfile  getEmailProfileByType(int profileType, BusinessProjectProfileElementDocument profile){
	    	EmailProfile  document=null;
		   if(profile!=null && profile.getBusinessProjectProfileElement()!=null  && profile.getBusinessProjectProfileElement().getEmailProfileArray()!=null){
			   for(int i=0;i<profile.getBusinessProjectProfileElement().getEmailProfileArray().length;i++)
			     { 
				   if(profile.getBusinessProjectProfileElement().getEmailProfileArray()[i].getProfileType()==profileType)
			              {
			    	         document=profile.getBusinessProjectProfileElement().getEmailProfileArray()[i];
			    	         break;			    	 
			              }
			     }
			   
			   if(document==null && profile.getBusinessProjectProfileElement().getEmailProfileArray().length>0){
				   document=profile.getBusinessProjectProfileElement().getEmailProfileArray()[0];
			   }
		   }
		
		return document;	
		
	}


}
