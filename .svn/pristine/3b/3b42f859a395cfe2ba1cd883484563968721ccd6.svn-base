/**
 * Created on 27-jul-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;

import communication.datatype.tables.ComplaintCallDataType;
import communication.vehicle.tables.ComplaintData;
import communication.vehicle.tables.VehicleData;

public class VehicleComplaintForm  extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String id;
	private String vehicle;
    
	private String date;
	private String month;
	private String year;
	private String hour;
	private String minute;
	private String location;        
	private String description;        
	
	private String callType;
	private String specification;        
	private String reportDate;
	private String reportMonth;
	private String reportYear;
	private String ticket;        
	private String phoneId;        
	private String agent;        
	         
	private String reporterName;                      
	private String reporterPhone;                      
	private String reporterMobile;                      
	private String reporterEmail;                      
	
	private String options;
	private String actions;

    public VehicleComplaintForm() {
    }

    public VehicleComplaintForm(ComplaintData data) {
		id = String.valueOf(data.getComplaintId());
		vehicle = String.valueOf(data.getVehicle().getId());
		
		date = Util.getDay(data.getDate());
		month = Util.getMonth(data.getDate());
		year = Util.getYear(data.getDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data.getHour());
        hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));  
        minute = String.valueOf(calendar.get(Calendar.MINUTE));
		location = data.getLocation();
		description = data.getDescription();
		
		callType = (data.getCallType() != null) ?
				String.valueOf(data.getCallType().getResourceBundleId()): null;
		
		
		specification = data.getSpecification();
		reportDate = Util.getDay(data.getReportDate());
		reportMonth = Util.getMonth(data.getReportDate());
		reportYear = Util.getYear(data.getReportDate());
		ticket = data.getTicket();
		phoneId = data.getPhoneId();
		agent = data.getAgent();
		reporterName = data.getReporterName();
		reporterPhone = data.getReporterPhone();
		reporterMobile = data.getReporterMobile();
		reporterEmail = data.getReporterEmail();
		options = data.getOptions();
		actions = data.getActions();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getReporterPhone() {
		return reporterPhone;
	}

	public void setReporterPhone(String reporterPhone) {
		this.reporterPhone = reporterPhone;
	}

	public String getReporterMobile() {
		return reporterMobile;
	}

	public void setReporterMobile(String reporterMobile) {
		this.reporterMobile = reporterMobile;
	}

	public String getReporterEmail() {
		return reporterEmail;
	}

	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	/**
	 * 
	 * @return
	 */
	public ComplaintData getCreateData() {
		ComplaintCallDataType callType2 = (callType != null)?
				new ComplaintCallDataType(callType):null;
		return new ComplaintData(null, Util.getDate(date, month, year), 
				Util.getTime(hour, minute), location, description, 
				callType2,
				specification, Util.getDate(reportDate,reportMonth,reportYear), 
				ticket, phoneId, agent, reporterName,
				reporterPhone, reporterMobile, reporterEmail, 
				new VehicleData(new Integer(id)),options,actions);
	}

	public ComplaintData getUpdateData() {
		ComplaintCallDataType callType2 = (callType != null)?
				new ComplaintCallDataType(callType):null;
		return new ComplaintData(new Integer(id), Util.getDate(date, month, year), 
				Util.getTime(hour, minute), location, description, callType2,
				specification, Util.getDate(reportDate,reportMonth,reportYear), ticket, phoneId, agent, reporterName,
				reporterPhone, reporterMobile, reporterEmail, new VehicleData(new Integer(vehicle)),options,actions);
	}

	public String validate() {
        try {
            Date eventDate = Util.getDate(date,month,year);
            if(eventDate == null) {
                return "complaint.error.dateEmpty";
            }
            if(!Util.checkDate(Integer.parseInt(date),Integer.parseInt(month),Integer.parseInt(year))) {
                return "complaint.error.dateInvalid";
            }
        }catch(Exception e) {
            return "complaint.error.dateInvalid";
        }
        try {
            Date eventDate = Util.getDate(reportDate,reportMonth,reportYear);
            if(eventDate == null) {
                return "complaint.error.dateEmpty";
            }
            if(!Util.checkDate(Integer.parseInt(reportDate),Integer.parseInt(reportMonth),Integer.parseInt(reportYear))) {
                return "complaint.error.dateInvalid";
            }
        }catch(Exception e) {
            return "complaint.error.dateInvalid";
        }
		return null;
	}
    
}
