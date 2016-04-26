package com.banking.bpm.sca.IngBpmUtil;

import org.osoa.sca.annotations.Property;
import javax.mail.Session;
import com.ingbank.bpm.soa.ingBpmUtil.IngBpmUtil;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameDocument;
import com.ingbank.bpm.soa.ingBpmUtil.SendEmailRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesResponseDocument;

/**
 * Abstract interface generated for component "SendEmail".
 *
 * This class will be completely generated, add custom code to the subclass: 
 * {@link com.banking.bpm.sca.IngBpmUtil.AbstractSendEmail AbstractSendEmail}
 *
 * @Generated TEMPL003
 */
public abstract class AbstractSendEmail implements IngBpmUtil {

	private Session smtpservice;

	@Property(name = "smtpservice")
	public void setSmtpservice(Session smtpservice) {
		this.smtpservice = smtpservice;
	}

	public Session getSmtpservice() {
		return smtpservice;
	}

	/**
	 * Implementation of the WSDL operation: getEnviromentName	 */
	public abstract GetEnviromentNameResponseDocument getEnviromentName(
			GetEnviromentNameDocument parameters);
	/**
	 * Implementation of the WSDL operation: sendEmail	 */
	public abstract void sendEmail(SendEmailRequestDocument parameters);
	/**
	 * Implementation of the WSDL operation: retrieveEmailAdresses	 */
	public abstract RetrieveEmailAdressesResponseDocument retrieveEmailAdresses(
			RetrieveEmailAdressesRequestDocument parameters);

}
