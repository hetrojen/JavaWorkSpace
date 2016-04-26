package com.banking.bpm.sca.IngBpmUtil;

import org.osoa.sca.annotations.Property;
import org.osoa.sca.annotations.Reference;
import com.tibco.bds.services.BusinessDataServices;
import com.ingbank.bpm.soa.ingBpmUtil.IngBpmUtil;
import com.tibco.bds.api.GetCaseModelResponseDocument;
import com.tibco.bds.api.GetDataViewDetailsResponseDocument;
import com.tibco.bds.api.FindCaseByCriteriaResponseDocument;
import com.tibco.bds.api.document.DeleteOrphanedFoldersRequestDocument;
import com.tibco.bds.api.UpdateDBScriptsResponseDocument;
import com.tibco.bds.api.GetDataViewCategoriesResponseDocument;
import com.tibco.bds.api.DeleteCaseByCIDRequestDocument;
import com.tibco.bds.api.GetCaseModelRequestDocument;
import com.tibco.bds.api.FindCaseByExampleRequestDocument;
import com.tibco.bds.api.GetAuditInfoResponseDocument;
import com.tibco.bds.api.document.DeleteDocumentResponseDocument;
import com.tibco.bds.api.FindCaseByExampleResponseDocument;
import com.tibco.bds.api.NotifyDDLExecutionResponseDocument;
import com.tibco.bds.api.NavigateCaseRequestDocument;
import com.tibco.bds.api.ReadCaseResponseDocument;
import com.tibco.bds.api.DeleteDataViewRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameDocument;
import com.tibco.bds.api.NotifyDDLExecutionRequestDocument;
import com.tibco.bds.api.document.DeleteOrphanedFoldersResponseDocument;
import com.tibco.bds.api.EditDataViewRequestDocument;
import com.tibco.bds.services.DocumentService;
import com.tibco.bds.api.DeleteDataViewResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.GetEnviromentNameResponseDocument;
import com.tibco.bds.api.ReadCaseRequestDocument;
import com.tibco.bds.api.document.UnlinkDocumentRequestDocument;
import com.tibco.bds.api.FindAllCasesResponseDocument;
import com.tibco.bds.api.GetCaseClassInfoRequestDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesRequestDocument;
import com.tibco.bds.api.document.MoveDocumentRequestDocument;
import com.tibco.bds.api.GetCaseReferencesForDataViewResponseDocument;
import com.tibco.bds.api.UpdateDBScriptsRequestDocument;
import com.tibco.bds.api.EditDataViewResponseDocument;
import com.tibco.bds.api.document.CreateDocumentResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.RetrieveEmailAdressesResponseDocument;
import com.tibco.bds.api.NavigateCaseByCriteriaRequestDocument;
import com.tibco.bds.api.DeleteCaseByRefResponseDocument;
import com.tibco.bds.api.FindCaseByCIDResponseDocument;
import com.tibco.bds.api.GetDataViewDetailsRequestDocument;
import com.tibco.bds.api.CreateDataViewRequestDocument;
import com.tibco.bds.api.FindAllCasesRequestDocument;
import com.tibco.bds.services.BusinessDataServicesAdminService;
import com.tibco.bds.api.CreateCaseRequestDocument;
import com.tibco.bds.api.CreateCaseResponseDocument;
import com.tibco.bds.api.NavigateCaseResponseDocument;
import com.tibco.bds.api.LinkCaseRequestDocument;
import com.tibco.bds.api.document.GetDocumentMetadataRequestDocument;
import com.tibco.bds.api.document.FindDocumentsResponseDocument;
import com.tibco.bds.api.FindCaseByCriteriaRequestDocument;
import com.tibco.bds.api.document.UnlinkDocumentResponseDocument;
import com.tibco.bds.api.document.LinkDocumentResponseDocument;
import com.tibco.bds.api.document.FindDocumentsRequestDocument;
import com.tibco.bds.api.GetCaseSummaryResponseDocument;
import com.tibco.bds.api.LinkCaseResponseDocument;
import com.tibco.bds.api.DeleteCaseByRefRequestDocument;
import com.tibco.bds.api.UnlinkCaseRequestDocument;
import com.tibco.bds.api.GetCaseSummaryRequestDocument;
import com.tibco.bds.api.NavigateCaseByCriteriaResponseDocument;
import com.tibco.bds.api.document.LinkDocumentRequestDocument;
import com.tibco.bds.api.document.GetDocumentMetadataResponseDocument;
import com.tibco.bds.api.document.GetFolderContentResponseDocument;
import com.tibco.bds.api.document.DeleteDocumentRequestDocument;
import com.tibco.bds.api.GetCaseReferencesForDataViewRequestDocument;
import com.tibco.bds.api.DeleteCaseByCIDResponseDocument;
import com.tibco.bds.api.document.GetDocumentContentRequestDocument;
import com.tibco.bds.api.document.MoveDocumentResponseDocument;
import com.tibco.bds.api.FindCaseByCIDRequestDocument;
import com.tibco.bds.api.document.GetFolderContentRequestDocument;
import com.tibco.bds.api.document.CreateDocumentRequestDocument;
import com.tibco.bds.api.GetDataViewCategoriesRequestDocument;
import com.tibco.bds.api.UpdateCaseRequestDocument;
import com.tibco.bds.api.CreateDataViewResponseDocument;
import com.tibco.bds.api.GetCaseClassInfoResponseDocument;
import com.tibco.bds.api.document.GetDocumentContentResponseDocument;
import com.tibco.bds.api.GetCaseModelBasicInfoRequestDocument;
import com.tibco.bds.api.UnlinkCaseResponseDocument;
import com.tibco.bds.api.UpdateCaseResponseDocument;
import com.ingbank.bpm.soa.ingBpmUtil.SendEmailRequestDocument;
import com.tibco.bds.api.GetAuditInfoRequestDocument;
import com.tibco.bds.api.GetCaseModelBasicInfoResponseDocument;

/**
 * Abstract interface generated for component "retrieveEmailAdresses".
 *
 * This class will be completely generated, add custom code to the subclass: 
 * {@link com.banking.bpm.sca.IngBpmUtil.AbstractRetrieveEmailAdresses AbstractRetrieveEmailAdresses}
 *
 * @Generated TEMPL003
 */
public abstract class AbstractRetrieveEmailAdresses implements IngBpmUtil {

	private java.lang.String emailProfileCaseType;

	@Property(name = "emailProfileCaseType")
	public void setEmailProfileCaseType(java.lang.String emailProfileCaseType) {
		this.emailProfileCaseType = emailProfileCaseType;
	}

	public java.lang.String getEmailProfileCaseType() {
		return emailProfileCaseType;
	}
	private java.lang.String emailProfileCaseDataVersion;

	@Property(name = "emailProfileCaseDataVersion")
	public void setEmailProfileCaseDataVersion(
			java.lang.String emailProfileCaseDataVersion) {
		this.emailProfileCaseDataVersion = emailProfileCaseDataVersion;
	}

	public java.lang.String getEmailProfileCaseDataVersion() {
		return emailProfileCaseDataVersion;
	}
	private java.lang.String exceptionEmailFrom;

	@Property(name = "exceptionEmailFrom")
	public void setExceptionEmailFrom(java.lang.String exceptionEmailFrom) {
		this.exceptionEmailFrom = exceptionEmailFrom;
	}

	public java.lang.String getExceptionEmailFrom() {
		return exceptionEmailFrom;
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

	private BusinessDataServices BusinessDataServices;

	@Reference(name = "BusinessDataServices")
	public void setBusinessDataServices(
			BusinessDataServices BusinessDataServices) {
		this.BusinessDataServices = BusinessDataServices;
	}

	public BusinessDataServices getBusinessDataServices() {
		return this.BusinessDataServices;
	}

}
