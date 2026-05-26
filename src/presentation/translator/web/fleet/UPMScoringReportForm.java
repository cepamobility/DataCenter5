package presentation.translator.web.fleet;



import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import business.SystemAccess;
import business.alert.handler.Util;

import communication.administration.corporation.util.DPAUnconformityItemFilter;
import communication.administration.dpa.UnconformityTypeDPADataType;



public class UPMScoringReportForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;

	private Integer contractorId;
	
	private String startDay;
	private String startMonth;
	private String startYear;
	
	private String endDay;
	private String endMonth;
	private String endYear;
	
	
	public UPMScoringReportForm(){
		 
	}


	public Integer getContractorId() {
		return contractorId;
	}


	public void setContractorId(Integer contractorId) {
		this.contractorId = contractorId;
	}


	public String getStartDay() {
		return startDay;
	}


	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}


	public String getStartMonth() {
		return startMonth;
	}


	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}


	public String getStartYear() {
		return startYear;
	}


	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}


	public String getEndDay() {
		return endDay;
	}


	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}


	public String getEndMonth() {
		return endMonth;
	}


	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}


	public String getEndYear() {
		return endYear;
	}


	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	
	
	
	
}
