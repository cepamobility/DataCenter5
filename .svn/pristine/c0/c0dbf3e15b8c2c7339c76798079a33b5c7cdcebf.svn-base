package presentation.translator.web.vehicle;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import persistence.util.Util;



public class DPACheckListResumeQueryForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String day;
	private String month;
	private String year;
	private String dayEnd;
	private String monthEnd;
	private String yearEnd;
	private String divOrg1;
	private String divOrg2;
	private String divOrg3;
	

	
	public DPACheckListResumeQueryForm(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		day=String.valueOf(cal.get(Calendar.DATE));
		month=String.valueOf(cal.get(Calendar.MONTH)+1);
		year=String.valueOf(cal.get(Calendar.YEAR));
	}


	public String getDayEnd() {
		return dayEnd;
	}


	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
	}


	public String getMonthEnd() {
		return monthEnd;
	}


	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
	}


	public String getYearEnd() {
		return yearEnd;
	}


	public void setYearEnd(String yearEnd) {
		this.yearEnd = yearEnd;
	}

 
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDivOrg1() {
		return divOrg1;
	}

	public void setDivOrg1(String divOrg1) {
		this.divOrg1 = divOrg1;
	}

	public String getDivOrg2() {
		return divOrg2;
	}

	public void setDivOrg2(String divOrg2) {
		this.divOrg2 = divOrg2;
	}

	public String getDivOrg3() {
		return divOrg3;
	}

	public void setDivOrg3(String divOrg3) {
		this.divOrg3 = divOrg3;
	}


	public Date getEndDate() {
		return Util.getDate(yearEnd, monthEnd, dayEnd);
	}


	public Date getStartDate() {
		// TODO Auto-generated method stub
		return Util.getDate(year, month, day);
	}

	 
	 
	
	

}
