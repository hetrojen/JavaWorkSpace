package com.tibco.asyncaccountinvestigation;

import org.osoa.sca.annotations.Property;
import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;
import asyncaccountinvestigation.accountinvestigation.AccountInvestigationPort;
import wsdl.ingbank_com.credit.bpm.datamodel.customersearchv1_0.CustomerSearchv1;
import asyncaccountinvestigation.accountinvestigation.DoAccountInvestigationResponse;
import com.ingbank.credit.bpm.datamodel.customersearchv1.AccountInvestigationCustomerSearchInput;
import com.ingbank.credit.bpm.datamodel.customersearchv1.AccountInvestigationCustomerSearchOutput;
import com.ingbank.credit.bpm.datamodel.customersearchv1.DistraintCustomerSearchOutput;
import com.ingbank.credit.bpm.datamodel.customersearchv1.DistraintCustomerSearchInput;
import asyncaccountinvestigation.accountinvestigation.DoAccountInvestigation;

/**
 * Abstract interface generated for component "QueryAccountInvestigation".
 *
 * This class will be completely generated, add custom code to the subclass: 
 * {@link com.tibco.asyncaccountinvestigation.AbstractQueryAccountInvestigation AbstractQueryAccountInvestigation}
 *
 * @Generated TEMPL003
 */
public abstract class AbstractQueryAccountInvestigation
		implements
			AccountInvestigationPort,
			CustomerSearchv1 {

	private DataSource JDBCDataSource;

	@Property(name = "JDBCDataSource")
	public void setJDBCDataSource(DataSource JDBCDataSource) {
		this.JDBCDataSource = JDBCDataSource;
	}

	public DataSource getJDBCDataSource() {
		return JDBCDataSource;
	}
	private ThreadPoolExecutor WorkersThreadPool;

	@Property(name = "WorkersThreadPool")
	public void setWorkersThreadPool(ThreadPoolExecutor WorkersThreadPool) {
		this.WorkersThreadPool = WorkersThreadPool;
	}

	public ThreadPoolExecutor getWorkersThreadPool() {
		return WorkersThreadPool;
	}
	private DataSource JDBCDataSource2;

	@Property(name = "JDBCDataSource2")
	public void setJDBCDataSource2(DataSource JDBCDataSource2) {
		this.JDBCDataSource2 = JDBCDataSource2;
	}

	public DataSource getJDBCDataSource2() {
		return JDBCDataSource2;
	}
	private DataSource JDBCDataSource3;

	@Property(name = "JDBCDataSource3")
	public void setJDBCDataSource3(DataSource JDBCDataSource3) {
		this.JDBCDataSource3 = JDBCDataSource3;
	}

	public DataSource getJDBCDataSource3() {
		return JDBCDataSource3;
	}
	private java.lang.String DefaultLocale;

	@Property(name = "DefaultLocale")
	public void setDefaultLocale(java.lang.String DefaultLocale) {
		this.DefaultLocale = DefaultLocale;
	}

	public java.lang.String getDefaultLocale() {
		return DefaultLocale;
	}
	private boolean createExcelFile;

	@Property(name = "createExcelFile")
	public void setCreateExcelFile(boolean createExcelFile) {
		this.createExcelFile = createExcelFile;
	}

	public boolean getCreateExcelFile() {
		return createExcelFile;
	}
	private java.lang.String ExcelFileFolder;

	@Property(name = "ExcelFileFolder")
	public void setExcelFileFolder(java.lang.String ExcelFileFolder) {
		this.ExcelFileFolder = ExcelFileFolder;
	}

	public java.lang.String getExcelFileFolder() {
		return ExcelFileFolder;
	}
	private DataSource JDBCDataSourceDistraintCustomerSearch;

	@Property(name = "JDBCDataSourceDistraintCustomerSearch")
	public void setJDBCDataSourceDistraintCustomerSearch(
			DataSource JDBCDataSourceDistraintCustomerSearch) {
		this.JDBCDataSourceDistraintCustomerSearch = JDBCDataSourceDistraintCustomerSearch;
	}

	public DataSource getJDBCDataSourceDistraintCustomerSearch() {
		return JDBCDataSourceDistraintCustomerSearch;
	}
	private DataSource JDBCDataSourceAccountInvestigationCustomerSearch;

	@Property(name = "JDBCDataSourceAccountInvestigationCustomerSearch")
	public void setJDBCDataSourceAccountInvestigationCustomerSearch(
			DataSource JDBCDataSourceAccountInvestigationCustomerSearch) {
		this.JDBCDataSourceAccountInvestigationCustomerSearch = JDBCDataSourceAccountInvestigationCustomerSearch;
	}

	public DataSource getJDBCDataSourceAccountInvestigationCustomerSearch() {
		return JDBCDataSourceAccountInvestigationCustomerSearch;
	}
	private DataSource JDBCDataSource_VERISOFT2;

	@Property(name = "JDBCDataSource_VERISOFT2")
	public void setJDBCDataSource_VERISOFT2(DataSource JDBCDataSource_VERISOFT2) {
		this.JDBCDataSource_VERISOFT2 = JDBCDataSource_VERISOFT2;
	}

	public DataSource getJDBCDataSource_VERISOFT2() {
		return JDBCDataSource_VERISOFT2;
	}
	private java.lang.String ExcelFileFolder2;

	@Property(name = "ExcelFileFolder2")
	public void setExcelFileFolder2(java.lang.String ExcelFileFolder2) {
		this.ExcelFileFolder2 = ExcelFileFolder2;
	}

	public java.lang.String getExcelFileFolder2() {
		return ExcelFileFolder2;
	}

	/**
	 * Implementation of the WSDL operation: doAccountInvestigation	 */
	public abstract DoAccountInvestigationResponse doAccountInvestigation(
			DoAccountInvestigation parameters);
	/**
	 * Implementation of the WSDL operation: DistraintCustomerSearch	 */
	public abstract DistraintCustomerSearchOutput distraintCustomerSearch(
			DistraintCustomerSearchInput distraintCustomerSearchRequest);
	/**
	 * Implementation of the WSDL operation: AccountInvestigationCustomerSearch	 */
	public abstract AccountInvestigationCustomerSearchOutput accountInvestigationCustomerSearch(
			AccountInvestigationCustomerSearchInput accountInvestigationCustomerSearchRequest);

}
