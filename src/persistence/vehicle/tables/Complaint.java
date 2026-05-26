package persistence.vehicle.tables;

import java.util.Date;

import org.hibernate.classic.Session;

import persistence.datatype.tables.ComplaintCallType;

import communication.datatype.tables.ComplaintCallDataType;
import communication.vehicle.tables.ComplaintData;

public class Complaint {

	private Integer complaintId;

	private Date date;
	private Date hour;
	private String location;        
	private String description;        
	
	private ComplaintCallType callType;
	private String specification;        
	private Date reportDate;
	private String ticket;        
	private String phoneId;        
	private String agent;        
	         
	private String reporterName;                      
	private String reporterPhone;                      
	private String reporterMobile;                      
	private String reporterEmail;                      
	
	private Vehicle vehicle;
	
	private String options;
	private String actions;
	
	public Complaint() {
		super();
	}

	public Complaint(ComplaintData data, Session session) {
		super();
		setData(data,session);
	}

	/**
	 * 
	 * @param data
	 * @param session
	 */
	public void setData(ComplaintData data, Session session) {
		this.date = data.getDate();
		this.hour = data.getHour();
		this.location = data.getLocation();
		this.description = data.getDescription();
		
		if (data.getCallType() == null || 
				data.getCallType().getResourceBundleId().trim().equalsIgnoreCase("")){
			this.callType = null;
		}else{
			this.callType = (ComplaintCallType)session.load(ComplaintCallType.class,
	    		data.getCallType().getResourceBundleId());
		}
		
		this.specification = data.getSpecification();
		this.reportDate = data.getReportDate();
		this.ticket = data.getTicket();
		this.phoneId = data.getPhoneId();
		this.agent = data.getAgent();
		this.reporterName = data.getReporterName();
		this.reporterPhone = data.getReporterPhone();
		this.reporterMobile = data.getReporterMobile();
		this.reporterEmail = data.getReporterEmail();
		this.vehicle = (Vehicle)session.load(Vehicle.class, data.getVehicle().getId());
		this.options = data.getOptions();
		this.actions = data.getActions();
	}

	public Integer getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
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

	public ComplaintCallType getCallType() {
		return callType;
	}

	public void setCallType(ComplaintCallType callType) {
		this.callType = callType;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * Get Data
	 * @return
	 */
	public ComplaintData getData() {
		ComplaintCallDataType callType2 = (callType != null)?
				new ComplaintCallDataType(callType.getResourceBundleId()):null;
		return new ComplaintData(complaintId, date, hour, location, description, 
				callType2,specification, reportDate, ticket, phoneId, agent, reporterName,
				reporterPhone, reporterMobile, reporterEmail, vehicle.getVehicleData(),
				options,actions);
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

}
