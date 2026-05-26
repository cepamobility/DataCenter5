package presentation.translator.web.dpa;



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



public class DPAUnconformityItemListForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;

	private String unconformityType;
	
	private String startDay;
	private String startMonth;
	private String startYear;
	
	private String endDay;
	private String endMonth;
	private String endYear;
	
	private String text;
	private String dateType;
	
	public DPAUnconformityItemListForm(){
		unconformityType= UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK;
	}
	
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	 

	public String getUnconformityType() {
		return unconformityType;
	}
	
	public void setUnconformityType(String unconformityType) {
		this.unconformityType = unconformityType;
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
	
	public DPAUnconformityItemFilter getFilter(){
		DPAUnconformityItemFilter filter =new DPAUnconformityItemFilter(Util.getDate(getStartDay(), getStartMonth(), getStartYear()), Util.getDate(getEndDay(), getEndMonth(), getEndYear()), unconformityType,text);
		try {
			filter.setDateType(new Integer(dateType));
		}catch (Exception e) {
		}
		return filter;
	}


	public String getDateType() {
		return dateType;
	}


	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

}
