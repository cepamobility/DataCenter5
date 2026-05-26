package presentation.translator.web.travel;


import java.util.Calendar;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;

import communication.travel.tables.TravelDataType;

/**
 * @author jrodriguez
 */
public class TravelCloseForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String travel;
    
    private String date;
	private String month;
	private String year;

    private String hour;  
    private String minute;
    
    private String local;
    private String monitor;

    private String comment;
   	
    private String id1;
    private String date1;
	private String month1;
	private String year1;

    private String hour1;  
    private String minute1;
    
    private String local1;
    private String way1;
    private String communication1;
    private String result1;
    
    private String comment1;


    public TravelCloseForm() {
    }

    public TravelCloseForm(TravelDataType travelData) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(travelData.getCloseDate());
        date1 = String.valueOf(c.get(Calendar.DATE));
    	month1 = String.valueOf(c.get(Calendar.MONTH)+1);
    	year1 = String.valueOf(c.get(Calendar.YEAR));

    	c.setTime(travelData.getCloseHour());
        hour1 = String.valueOf(c.get(Calendar.HOUR));  
        minute1 = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
        
        local1 = travelData.getCloseLocal();
        way1 = travelData.getCloseWay();
        communication1 = travelData.getCloseCommunication();
	    result1 = (travelData.isTravelResult()) ? "2" : "1";

        comment1 = travelData.getCloseComment();
        
    	c.setTime(travelData.getRegistryDate());
        date = String.valueOf(c.get(Calendar.DATE));
    	month = String.valueOf(c.get(Calendar.MONTH)+1);
    	year = String.valueOf(c.get(Calendar.YEAR));
    	c.setTime(travelData.getRegistryHour());
        hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));  
        minute = (c.get(Calendar.MINUTE) < 10) ? "0"+c.get(Calendar.MINUTE):String.valueOf(c.get(Calendar.MINUTE));
	    local = travelData.getRegistryLocal();
	    monitor = travelData.getRegistryMonitor();
	    comment = travelData.getRegistryComment();
    }

	public String getTravel() {
		return travel;
	}


	public void setTravel(String travel) {
		this.travel = travel;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
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


	public String getHour() {
		return hour;
	}


	public void setHour(String hour) {
		this.hour = hour;
	}


	public String getMinute() {
		return minute;
	}


	public void setMinute(String minute) {
		this.minute = minute;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}


	public String getMonitor() {
		return monitor;
	}


	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getId1() {
		return id1;
	}


	public void setId1(String id1) {
		this.id1 = id1;
	}


	public String getDate1() {
		return date1;
	}


	public void setDate1(String date1) {
		this.date1 = date1;
	}


	public String getMonth1() {
		return month1;
	}


	public void setMonth1(String month1) {
		this.month1 = month1;
	}


	public String getYear1() {
		return year1;
	}


	public void setYear1(String year1) {
		this.year1 = year1;
	}


	public String getHour1() {
		return hour1;
	}


	public void setHour1(String hour1) {
		this.hour1 = hour1;
	}


	public String getMinute1() {
		return minute1;
	}


	public void setMinute1(String minute1) {
		this.minute1 = minute1;
	}


	public String getLocal1() {
		return local1;
	}


	public void setLocal1(String local1) {
		this.local1 = local1;
	}


	public String getWay1() {
		return way1;
	}


	public void setWay1(String way1) {
		this.way1 = way1;
	}


	public String getCommunication1() {
		return communication1;
	}


	public void setCommunication1(String communication1) {
		this.communication1 = communication1;
	}


	public String getResult1() {
		return result1;
	}


	public void setResult1(String result1) {
		this.result1 = result1;
	}


	public String getComment1() {
		return comment1;
	}


	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public TravelDataType getTravelData() {
		TravelDataType travelDataType = new TravelDataType();
		travelDataType.setId(new Integer(travel));
		
		travelDataType.setCloseDate(Util.getDate(date1, month1, year1));
		travelDataType.setCloseHour(Util.getTime(hour1, minute1));
		travelDataType.setCloseLocal(local1);
		travelDataType.setCloseWay(way1);
		travelDataType.setCloseCommunication(communication1);
		travelDataType.setCloseComment(comment1);
		
		travelDataType.setRegistryDate(Util.getDate(date, month, year));
		travelDataType.setRegistryHour(Util.getTime(hour, minute));
		travelDataType.setRegistryLocal(local);
		travelDataType.setRegistryMonitor(monitor);
		travelDataType.setRegistryComment(comment);
		
		return travelDataType;
	}

	public String validate() {
		if(!Util.checkDate(date1, month1, year1)) {
    		return "travel.error.invalidclosedate";
    	}
    	if(Util.empty(hour1) || Util.empty(minute1) || !Util.checkHour(hour1, minute1)) {
    		return "travel.error.invalidclosehour";
    	}
		if(Util.empty(result1)) {
    		return "travel.error.invalidresult";						
		}
    	if(!Util.checkDate(date, month, year)) {
    		return "travel.error.invalidregistrydate";
    	}
    	if(Util.empty(hour) || Util.empty(minute) || !Util.checkHour(hour, minute)) {
    		return "travel.error.invalidregistryhour";
    	}
		return null;
	}
}
