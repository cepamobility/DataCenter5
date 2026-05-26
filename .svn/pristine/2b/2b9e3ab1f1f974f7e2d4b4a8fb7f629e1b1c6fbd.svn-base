package presentation.translator.web.alert;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.alert.AlertData;
import communication.alert.AlertToData;
import communication.datatype.tables.AlertFrecuencyDataType;
import communication.datatype.tables.AlertPriorityDataType;
import communication.datatype.tables.AlertTypeDataType;

public class AlertForm extends ActionForm {

    private static final long serialVersionUID = 1L;
	
    private String alertId;
    private String alertType;
	private String subject;
	private String body;
	
	private String attachment;
	private FormFile file;
	
    private String email1;
    private String email2;
    private String email3;
    private String email4;
    private String email5;
    
	private String startDate;
    private String startMonth;
    private String startYear;
    private String lastDate;
    private String lastMonth;
    private String lastYear;
    private String nextDate;
    private String nextMonth;
    private String nextYear;
    private String endDate;
    private String endMonth;
    private String endYear;
	private String corporation;
	private String priority;
	private String frecuency;
	private String dailyFrecuency;
	
	private String test;
    
    public AlertForm() {
    	file = null;
    }
    
    public AlertForm(AlertData alertData) {
    	file = null;
    	this.alertId = String.valueOf(alertData.getAlertId());
    	this.alertType = ( alertData.getAlertType() != null) ? alertData.getAlertType().getResourceBundleId():null;
    	this.subject = (alertData.getSubject() != null) ? alertData.getSubject():"";
    	this.body = (alertData.getBody() != null) ? alertData.getBody(): "";
    	String [] alertto = new String[5];
    	
    	if (alertData.getTo() != null && alertData.getTo().size() > 0){
    		Iterator iter = alertData.getTo().iterator();
    		AlertToData email;
    		int i=0;
    		while (iter.hasNext()) {
    			email = (AlertToData) iter.next();
    			alertto[i]= email.getEmail();
    			i++;
    		}
    	}
    	
    	this.email1 = alertto[0];
    	this.email2 = alertto[1];
    	this.email3 = alertto[2];
    	this.email4 = alertto[3];
    	this.email5 = alertto[4];
    	
    	if(alertData.getStartdate() != null) {
			Calendar startDate = Calendar.getInstance();
			startDate.setTime(alertData.getStartdate());
			this.startDate=String.valueOf(startDate.get(Calendar.DATE));
			this.startMonth=String.valueOf(startDate.get(Calendar.MONTH)+1);
			this.startYear=String.valueOf(startDate.get(Calendar.YEAR));
		}
    	if(alertData.getLastdate() != null) {
			Calendar lastDate = Calendar.getInstance();
			lastDate.setTime(alertData.getLastdate());
			this.lastDate=String.valueOf(lastDate.get(Calendar.DATE));
			this.lastMonth=String.valueOf(lastDate.get(Calendar.MONTH)+1);
			this.lastYear=String.valueOf(lastDate.get(Calendar.YEAR));
		}
    	if(alertData.getNextdate() != null) {
			Calendar nextDate = Calendar.getInstance();
			nextDate.setTime(alertData.getNextdate());
			this.nextDate=String.valueOf(nextDate.get(Calendar.DATE));
			this.nextMonth=String.valueOf(nextDate.get(Calendar.MONTH)+1);
			this.nextYear=String.valueOf(nextDate.get(Calendar.YEAR));
		}
    	
    	Calendar testCalendar = Calendar.getInstance();
    	testCalendar.set(Calendar.YEAR, 2100);
    	testCalendar.set(Calendar.MONTH,0);
    	testCalendar.set(Calendar.DATE,1);
    	
    	if (alertData.getNextdate().compareTo(testCalendar.getTime()) >= 0){
    		// Si nextDate >= 2100-01-01, estamos en una alerta con estado TEST
    		this.test = "TEST";
    	}else{
    		this.test = null;
    	}
    	
    	if(alertData.getEnddate() != null) {
			Calendar endDate = Calendar.getInstance();
			endDate.setTime(alertData.getEnddate());
			this.endDate=String.valueOf(endDate.get(Calendar.DATE));
			this.endMonth=String.valueOf(endDate.get(Calendar.MONTH)+1);
			this.endYear=String.valueOf(endDate.get(Calendar.YEAR));
		}
    	
    	this.corporation=String.valueOf(alertData.getCorporation());
    	this.priority=alertData.getPriority().getResourceBundleId();
    	this.frecuency=alertData.getFrecuency().getResourceBundleId();
    	this.dailyFrecuency=String.valueOf(alertData.getDailyFrecuency());
    	this.attachment = alertData.getAttachment();
    	
    	
    	
    }   
    
    
    public String getAlertId() {
		return alertId;
	}
	
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	
	
	public String getAlertType() {
		return alertType;
	}
	
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getEmail1() {
		return email1;
	}
	
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	
	public String getEmail2() {
		return email2;
	}
	
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public String getEmail3() {
		return email3;
	}
	
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	
	public String getEmail4() {
		return email4;
	}
	
	public void setEmail4(String email4) {
		this.email4 = email4;
	}
	
	public String getEmail5() {
		return email5;
	}
	
	public void setEmail5(String email5) {
		this.email5 = email5;
	}
	
    
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	
	
	public String getLastDate() {
		return lastDate;
	}
	
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}	

    
	public String getLastMonth() {
		return lastMonth;
	}
	
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	
	public String getLastYear() {
		return lastMonth;
	}
	
	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}
	
	public String getNextDate() {
		return nextDate;
	}
	
	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}	
    
	public String getNextMonth() {
		return nextMonth;
	}
	
	public void setNextMonth(String nextMonth) {
		this.nextMonth = nextMonth;
	}
	
	public String getNextYear() {
		return nextMonth;
	}
	
	public void setNextYear(String nextYear) {
		this.nextYear = nextYear;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}	
    
	public String getEndMonth() {
		return endMonth;
	}
	
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
	public String getEndYear() {
		return endMonth;
	}
	
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	
	public Date getStart() {
        return getDate(startDate,startMonth,startYear);
    }
    
    public Date getLast() {
        return getDate(lastDate,lastMonth,lastYear);
    }
    
    public Date getNext() {
        return getDate(nextDate,nextMonth,nextYear);
    }
    
    public Date getEnd() {
        return getDate(endDate,endMonth,endYear);
    }
    
    
    public String getCorporation() {
		return corporation;
	}
	
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	
	     
	public String getPriority(){
		return priority;
	}
		
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	
	public String getFrecuency() {
		return frecuency;
	}
	
	public void setFrecuency(String frecuency) {
		this.frecuency = frecuency;
	}
    
	public String getDailyFrecuency() {
		return dailyFrecuency;
	}
	
	public void setDailyFrecuency(String dailyFrecuency) {
		this.dailyFrecuency = dailyFrecuency;
	}
    
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}
    
    public Date getDate(String date,String month,String year) {
        if(isEmpty(date) && isEmpty(month) && isEmpty(year)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,Integer.parseInt(date));
        calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
        calendar.set(Calendar.YEAR,Integer.parseInt(year));

        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
    
public AlertData getAlertData(SystemAccess sys) throws RemoteException {
        
        AlertData alert = new AlertData();
        if(!Util.empty(alertId)) {
        	alert.setAlertId(new Integer(alertId));
        }
        alert.setAlertType(new AlertTypeDataType(alertType));
    	alert.setSubject(subject);
    	alert.setDescription(subject);
    	alert.setBody(body);
    	int corporationId=sys.getUserSessionData().getFilter().getCorporationId();
    	Collection alertTo = new LinkedList();
    	if ((email1!=null)&&(!email1.equals(""))){
    		alertTo.add(new AlertToData(null,corporationId,email1));
    	}else if((email2!=null)&&(!email2.equals(""))){
    		alertTo.add(new AlertToData(null,corporationId,email2));
    	}else if((email3!=null)&&(!email3.equals(""))){
    		alertTo.add(new AlertToData(null,corporationId,email3));
    	}else if((email4!=null)&&(!email4.equals(""))){
    		alertTo.add(new AlertToData(null,corporationId,email4));
    	}else if((email5!=null)&&(!email5.equals(""))){
    		alertTo.add(new AlertToData(null,corporationId,email5));
    	}
    	alert.setTo(alertTo);
    	
    	
    	
    	alert.setStartdate(Util.getDate(startDate,startMonth,startYear));
    	alert.setLastdate(Util.getDate(lastDate,lastMonth,lastYear));
    	alert.setNextdate(Util.getDate(nextDate,nextMonth,nextYear));
    	alert.setEnddate(Util.getDate(endDate,endMonth,endYear));
    	alert.setCorporation(sys.getUserSessionData().getFilter().getCorporationId());
    	if ((priority!=null)&&(!priority.equals(""))){
    	alert.setPriority(new AlertPriorityDataType(priority));
    	}else{
    		alert.setPriority(new AlertPriorityDataType("generic.alert.priority.na"));
    	}
    	alert.setFrecuency(new AlertFrecuencyDataType(frecuency));
    	alert.setDailyFrecuency(new Integer(dailyFrecuency));
    	alert.setAttachment(attachment);
    	
    	return alert;
    	
    }

	public String validate(SystemAccess sys) {
		Date dateStart;
		Date dateEnd;
		try {
				dateStart = Util.getDate(startDate,startMonth,startYear);
	            if(dateStart == null) {
	                return "generic.event.eventdata.dateEmpty";
	            }
	            if(!Util.checkDate(Integer.parseInt(startDate),Integer.parseInt(startMonth),Integer.parseInt(startYear))) {
	                return "generic.event.eventdata.dateInvalid";
	            }
	            Date today=new Date();
	            if (dateStart.before(today)){
	            	return "generic.event.eventdata.dateInvalid";
	            }      
	    }catch(Exception e) {
	    	return "generic.event.eventdata.dateInvalid";
	    }
	    try {
			dateEnd = Util.getDate(endDate,endMonth,endYear);
            if((dateEnd != null)&&(!Util.checkDate(Integer.parseInt(endDate),Integer.parseInt(endMonth),Integer.parseInt(endYear)))) {
                return "generic.event.eventdata.dateInvalid";
            }
            Date today = new Date();
            if (dateEnd.before(dateStart)){
            	return "generic.event.eventdata.dateInvalid";
            } 
            
	    }catch(Exception e) {
	    	return "generic.event.eventdata.dateInvalid";
	    }
	    try {
	    	int  i = new Integer(dailyFrecuency).intValue();
	    }catch(Exception e) {
	    	return "generic.alert.dailyfrecuency.invalidfrecuency";
	    }
	    
	return null;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	/**
	* Reset all properties to their default values.
	*
	* @param mapping The mapping used to select this instance
	* @param request The servlet request we are processing
	*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		file = null;
	}
	
	/**
	 * @param file
	 */
	public AlertForm(FormFile file) {
		super();
		this.file = file;
	}

	/**
	 * @return the test
	 */
	public String getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(String test) {
		this.test = test;
	}
	
	

}
   

    
