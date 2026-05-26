package presentation.translator.web.driver;



import org.apache.struts.action.ActionForm;

import business.alert.handler.Util;

import communication.driver.tables.TelematicControlData;



public class DPATelematicControlListForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;

	private String type;
	
	private String startDay;
	private String startMonth;
	private String startYear;
	
	private String endDay;
	private String endMonth;
	private String endYear;
	
	private String text;
	
	public DPATelematicControlListForm(){
		type= String.valueOf(TelematicControlData.TYPE_EXCESSIVE_SPEED);
	}
	
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	 

	
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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
	
	public communication.driver.report.DPATelematicControlFilter getFilter(){
		return new communication.driver.report.DPATelematicControlFilter(Util.getDate(getStartDay(), getStartMonth(), getStartYear()), Util.getDate(getEndDay(), getEndMonth(), getEndYear()), type,text);
	}

}
