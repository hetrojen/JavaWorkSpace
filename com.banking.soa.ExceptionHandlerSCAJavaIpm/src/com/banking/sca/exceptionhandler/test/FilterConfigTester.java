package com.banking.sca.exceptionhandler.test;
import java.io.File;

import noNamespace.Attribute;
import noNamespace.Event;
import tr.com.ingbank.bpm.sca.schemas.configuration.exceptionHandler.filterConfiguration.EventFilterDocument.EventFilter;
import tr.com.ingbank.bpm.sca.schemas.configuration.exceptionHandler.filterConfiguration.FilterConfigurationDocument;


public class FilterConfigTester {
	private static FilterConfigurationDocument filterConfiguration;
	
	private static void testFilterConfig(Event parameters){
		
		
		

		System.out.println("Event Received...:" + parameters.getMessageId());

		// set moduleName
		String moduleName = "";
		
		if (parameters.getAttributeArray() != null
				&& parameters.getAttributeArray().length > 0) {
			
			for (Attribute attribute : parameters.getAttributeArray()) {
				
				System.out.print("Attirubute Name.."+attribute.getAttributeName());
				System.out.println(" Attirubute Value.."+attribute.getValue());
				
				if (attribute.getAttributeName().equals("moduleName")) {
					moduleName = attribute.getValue();
					break;
				}

			}
		}

		if(moduleName.trim().equals("")){
			System.out.println("Event module name should not be empty.");
			return;
		}
		
		boolean ruleMatch = false;

		for (EventFilter filter : filterConfiguration.getFilterConfiguration()
				.getEventFilterArray()) {
			// ModuleName Rule control
			if (filter.getModuleName().equals("*")) {
				ruleMatch = true;
			} else if (moduleName.contains(filter.getModuleName())) {
				ruleMatch = true;
			}

			// MessageId Rule control
			if (!ruleMatch) {
				continue; // nextRule
			}

			//If code reaches here module name rule has been matched 
			ruleMatch=false;
			
			// If ModuleName match check MessageId
			for (String messageId : filter.getMessageIdArray()) {
				if (messageId.equals("*")) {
					ruleMatch = true;
					break;
				} else if (parameters.getMessageId().equals(messageId)) {
					ruleMatch = true;
					break;
				}
			}
			
			if(ruleMatch){
				break;
			}

		}

		if (!ruleMatch) {
			System.out.println("There is no rule defined for  matched, messageId...:" + parameters.getMessageId() +" moduleName..:"+moduleName);
			return;
		}
		
		System.out.println("Rule has been  matched, messageId...:" + parameters.getMessageId() +" moduleName..:"+moduleName);

		
	}
	
	public static void main(String[] args) {
		if(filterConfiguration==null){
			loadConfiguration();
		}
	  Event event= Event.Factory.newInstance();
	  event.setMessageId("BX_TASK_REPORTED_ERRORyy");
	  event.addNewAttribute();
	  event.getAttributeArray(0).setValue("PromissoryNotes");
	  event.getAttributeArray(0).setAttributeName("moduleName");
	 testFilterConfig(event);
	}
	
	private static void loadConfiguration(){
		String configFilepath = "c:/FilterConfiguration.xml";
		if (filterConfiguration == null) {
			try {
				
				File xmlFile = new File(configFilepath);
		        System.out.println("FilterConfiguration is being reading from "+configFilepath);
				
				filterConfiguration = FilterConfigurationDocument.Factory
						.parse(xmlFile);
				System.out.println("Loading FilterConfiguration has been successful.");
			} catch (Exception e) {
				  //    Define define default filter configuration in case of configuration loads failure
				System.out.println("Error while loading FilterConfiguration.");
				e.printStackTrace();
				
			}

		}
	}

}
