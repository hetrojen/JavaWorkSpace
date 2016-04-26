package com.banking.sca.exceptionhandler.filter;

import java.io.File;

import noNamespace.Attribute;
import noNamespace.Event;

import org.osoa.sca.annotations.Destroy;
import org.osoa.sca.annotations.Init;

import com.banking.sca.exceptionhandler.util.LoggerUtil;

import tr.com.ingbank.bpm.sca.schemas.configuration.exceptionHandler.filterConfiguration.EventFilterDocument.EventFilter;
import tr.com.ingbank.bpm.sca.schemas.configuration.exceptionHandler.filterConfiguration.FilterConfigurationDocument;

/**
 * Implementation of EventFilterComp component.
 * 
 */
public class EventFilterComp extends AbstractEventFilterComp {
	private static FilterConfigurationDocument filterConfiguration;

	/**
	 * /** Initialization of EventFilterComp component.
	 */
	@Init
	public void init() {
		// Component initialization code.
		// All properties are initialized and references are injected.
		if(filterConfiguration==null){
			loadConfiguration();
		}
		
	}
	private void loadConfiguration(){
		String configFilepath = getFilterConfigFilePath();
		if (filterConfiguration == null) {
			try {
				
				File xmlFile = new File(configFilepath);
				LoggerUtil.logger.info("FilterConfiguration is being reading from "+configFilepath);
				
				filterConfiguration = FilterConfigurationDocument.Factory
						.parse(xmlFile);
				LoggerUtil.logger.info("Loading FilterConfiguration has been successful.");
			} catch (Exception e) {
				  //    Define define default filter configuration in case of configuration loads failure
				LoggerUtil.logger.error("Error while loading FilterConfiguration. ",e.getMessage());
				e.printStackTrace();
				
			}

		}
	}

	/**
	 * Disposal of EventFilterComp component.
	 */
	@Destroy
	public void destroy() {
		// Component disposal code.
		// All properties are disposed.
		filterConfiguration=null;
	}

	@Override
	public void receiveEvent(Event parameters) {
		// TODO Auto-generated method stub
		
		if(filterConfiguration==null){
			loadConfiguration();
		}
		
		if(filterConfiguration==null || filterConfiguration.getFilterConfiguration()==null
				|| filterConfiguration.getFilterConfiguration().getEventFilterArray().length==0 ){
			LoggerUtil.logger.error("Filter Configuration cannot be empty.");
			return;
		}
		

		LoggerUtil.logger.info("System Exception Handler has received a event, MessageId...:"+parameters.getMessageId());

		// set moduleName
		String moduleName = "";
		
		if (parameters.getAttributeArray() != null
				&& parameters.getAttributeArray().length > 0) {
			
			for (Attribute attribute : parameters.getAttributeArray()) {
				
				LoggerUtil.logger.debug("Attirubute Name.."+attribute.getAttributeName());
				LoggerUtil.logger.debug("Attirubute Value.."+attribute.getValue());
				
				if (attribute.getAttributeName().equals("moduleName")) {
					moduleName = attribute.getValue();
					break;
				}

			}
		}

		if(moduleName.trim().equals("")){
			LoggerUtil.logger.error("Event module name should not be empty.");
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
			LoggerUtil.logger.error("There is no rule defined for  matched, messageId...:" + parameters.getMessageId() +" moduleName..:"+moduleName);
			return;
		}
		
		LoggerUtil.logger.info("Rule has been  matched, messageId...:" + parameters.getMessageId() +" moduleName..:"+moduleName);

		// pass the event to next Component
		getExeptionHandler().receiveEvent(parameters);
	}

}
