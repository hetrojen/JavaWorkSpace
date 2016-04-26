package com.banking.sca.exceptionhandler.filter;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import tr.com.ingbank.sca.exeptionHandler.ExeptionHandler;
import noNamespace.Event;

/**
 * Abstract interface generated for component "EventFilterComp".
 *
 * This class will be completely generated, add custom code to the subclass: 
 * {@link com.banking.sca.exceptionhandler.filter.AbstractEventFilter AbstractEventFilter}
 *
 * @Generated TEMPL003
 */
public abstract class AbstractEventFilter implements ExeptionHandler {

	private java.lang.String FilterConfigFilePath;

	@Property(name = "FilterConfigFilePath")
	public void setFilterConfigFilePath(java.lang.String FilterConfigFilePath) {
		this.FilterConfigFilePath = FilterConfigFilePath;
	}

	public java.lang.String getFilterConfigFilePath() {
		return FilterConfigFilePath;
	}

	/**
	 * Implementation of the WSDL operation: receiveEvent	 */
	public abstract void receiveEvent(Event parameters);

	private ExeptionHandler ExeptionHandler;

	@Reference(name = "ExeptionHandler")
	public void setExeptionHandler(ExeptionHandler ExeptionHandler) {
		this.ExeptionHandler = ExeptionHandler;
	}

	public ExeptionHandler getExeptionHandler() {
		return this.ExeptionHandler;
	}

}
