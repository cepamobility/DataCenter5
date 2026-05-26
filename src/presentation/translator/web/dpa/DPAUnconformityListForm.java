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

import communication.administration.corporation.util.DPAUnconformityFilter;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityItemTypeDPADataType;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.datatype.DataTypeConstants;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.util.ListResult;



public class DPAUnconformityListForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;

	private Integer	pageNum=1;
	private Integer	maxResult=10;
	private Boolean all=false;
	private String unconformityType;
	
	private String startDay;
	private String startMonth;
	private String startYear;
	
	private String endDay;
	private String endMonth;
	private String endYear;
	
	private String text;
	
	private ListResult result;
	
	public DPAUnconformityListForm(){
		result=new ListResult(new ArrayList(), 0);
		unconformityType= UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL;
	}
	
	
	public Integer getMaxResult() {
		return maxResult;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}


	public Integer getPageNum() {
		return pageNum;
	}


	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}


	public Boolean getAll() {
		return all;
	}


	public void setAll(Boolean all) {
		this.all = all;
	}


	public Collection getUnconformities() {
		Collection unconformities=new ArrayList();
		if(result!=null && result.getElements()!=null){
			unconformities=result.getElements();
		}
		return unconformities;
	}

	public void resetUnconformities() {
		Collection unconformities=new ArrayList();
		result=new ListResult(unconformities,0);
	}

	 

	public ListResult getResult() {
		return result;
	}


	public void setResult(ListResult result) {
		this.result = result;
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
	
	public DPAUnconformityFilter getFilter(){
		return new DPAUnconformityFilter(pageNum, maxResult, all, Util.getDate(getStartDay(), getStartMonth(), getStartYear()), Util.getDate(getEndDay(), getEndMonth(), getEndYear()), unconformityType,text);
	}

}
