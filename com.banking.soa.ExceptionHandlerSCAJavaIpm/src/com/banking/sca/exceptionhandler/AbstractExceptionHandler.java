package com.banking.sca.exceptionhandler;

import org.osoa.sca.annotations.Reference;
import tr.com.ingbank.schemas.ISyncReply;
import com.ingbank.bpm.soa.ingBpmUtil.IngBpmUtil;
import tr.com.ingbank.sca.exeptionHandler.ExeptionHandler;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameResponseDocument;
import tr.com.ingbank.schemas.BPMInsertExceptionLogCompositionRequestDocument;
import noNamespace.Event;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameDocument;
import tr.com.ingbank.schemas.BPMInsertExceptionLogCompositionResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.SendEmailRequestDocument;

/**
 * Abstract interface generated for component "ExceptionHandler".
 *
 * This class will be completely generated, add custom code to the subclass: 
 * {@link com.banking.sca.exceptionhandler.AbstractExceptionHandler AbstractExceptionHandler}
 *
 * @Generated TEMPL003
 */
public abstract class AbstractExceptionHandler implements ExeptionHandler {

	/**
	 * Implementation of the WSDL operation: receiveEvent	 */
	public abstract void receiveEvent(Event parameters);

	private ISyncReply ISyncReply;

	@Reference(name = "ISyncReply")
	public void setISyncReply(ISyncReply ISyncReply) {
		this.ISyncReply = ISyncReply;
	}

	public ISyncReply getISyncReply() {
		return this.ISyncReply;
	}
	private IngBpmUtil IngBpmUtil;

	@Reference(name = "IngBpmUtil")
	public void setIngBpmUtil(IngBpmUtil IngBpmUtil) {
		this.IngBpmUtil = IngBpmUtil;
	}

	public IngBpmUtil getIngBpmUtil() {
		return this.IngBpmUtil;
	}

}
