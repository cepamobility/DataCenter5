package communication.alert;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import communication.datatype.tables.AlertFrecuencyDataType;
import communication.datatype.tables.AlertPriorityDataType;
import communication.datatype.tables.AlertTypeDataType;


public class AlertData implements Serializable {
	
    protected Integer alertId;
	
	// tipo de alerta
	protected AlertTypeDataType alertType;
	
	private String subject;
	private String body;
	private Collection to;
	private Collection extradata;
	private Date startdate;
	private Date lastdate;
	private Date nextdate;
	private Date enddate;
	private Integer corporation;
	private AlertPriorityDataType priority;
	private AlertFrecuencyDataType frecuency;
	private Integer dailyFrecuency;
	private String description;
	private Integer fleetId;
	private String fleetList; // lista de ids de flota separados por coma
	protected String attachment; // path del archivo a adjuntar o null
	
	public AlertData()
	{ }
	
	/**
	 * 
	 * @param alertId
	 * @param alertType
	 * @param subject
	 * @param to
	 * @param extradata
	 * @param startdate
	 * @param lastdate
	 * @param nextdate
	 * @param enddate
	 * @param body
	 * @param corporation
	 * @param priority
	 * @param frecuency
	 * @param dailyFrecuency
	 * @param parentType
	 */
	public AlertData(Integer alertId,AlertTypeDataType alertType,String subject,
			Collection to,Collection extradata,Date startdate,
			Date lastdate,Date nextdate,Date enddate,String body,
			Integer corporation, AlertPriorityDataType priority,
			AlertFrecuencyDataType frecuency,Integer dailyFrecuency,String description,
			Integer fleetId, String fleetList, String attachment) {
		this.alertId = alertId;
		this.alertType= alertType;
		this.subject = subject;
		this.body = body;
		this.to = to;
		this.extradata = extradata;
		this.corporation=corporation;
		this.startdate =startdate;
		this.lastdate= lastdate;
		this.nextdate =nextdate;
		this.enddate= enddate;
		this.priority=priority;
		this.frecuency=frecuency;
		this.dailyFrecuency=dailyFrecuency;
		this.description=description;
		this.fleetId = fleetId;
		this.fleetList = fleetList;
		this.attachment = attachment;
	}
	
	/**
	 * 
	 * @param data
	 */
	public AlertData(Object[] data) {
		this.alertId =(Integer) data[0];
		String typealert =(String)data[1];
		this.alertType=new AlertTypeDataType(typealert);
		String priorityalert =(String)data[2];
		this.priority =new AlertPriorityDataType(priorityalert);
		this.startdate =(Date) data[3];
		this.lastdate =(Date) data[4];
		this.subject =(String) data[5];
		this.enddate =(Date) data[6];
		
	}
	
	
	public Date getStartdate() {
		return startdate;
	}
	
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	public Date getNextdate() {
		return nextdate;
	}
	
	public void setNextdate(Date nextdate) {
		this.nextdate = nextdate;
	}
	
	public Date getEnddate() {
		return enddate;
	}
	
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	

	public Integer getAlertId() {
		return alertId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}
	
	public Integer getCorporation() {
		return corporation;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	public AlertTypeDataType getAlertType() {
		return alertType;
	}

	
	public void setAlertType(AlertTypeDataType alertType) {
		this.alertType = alertType;
	}
	
	public void setPriority(AlertPriorityDataType priority) {
		this.priority = priority;
	}
	
	public AlertPriorityDataType getPriority() {
		return priority;
	}

	public void setFrecuency(AlertFrecuencyDataType frecuency) {
		this.frecuency = frecuency;
	}
	
	public Integer getDailyFrecuency() {
		return dailyFrecuency;
	}
	
	public void setDailyFrecuency(Integer dailyFrecuency) {
		this.dailyFrecuency = dailyFrecuency;
	}
	
	
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public AlertFrecuencyDataType getFrecuency() {
		return frecuency;
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

	public Collection getTo() {
		return to;
	}

	public void setTo(Collection to) {
		this.to = to;
	}
	
	public Collection getExtradata() {
		return extradata;
	}

	public void setExtradata(Collection extradata) {
		this.extradata = extradata;
	}
	
	public Collection getTargets(Integer corporation) {
		
		Iterator iter = to.iterator();
		AlertToData alertTo;
	    Collection result = new LinkedList();
		while (iter.hasNext()) {
			alertTo = (AlertToData) iter.next();
			if (alertTo.getCorporation().equals(corporation)) {
				result.add(alertTo.getEmail());
			}	
		}
		return result;
	}
	
	public Collection getTargets() {
			
			Iterator iter = to.iterator();
			AlertToData alertTo;
		    Collection result = new LinkedList();
			while (iter.hasNext()) {
				alertTo = (AlertToData) iter.next();
				result.add(alertTo.getEmail());
			}
			return result;
		}

	/**
	 * @return the fleetId
	 */
	public Integer getFleetId() {
		return fleetId;
	}

	/**
	 * @param fleetId the fleetId to set
	 */
	public void setFleetId(Integer fleetId) {
		this.fleetId = fleetId;
	}

	/**
	 * @return the fleetList
	 */
	public String getFleetList() {
		return fleetList;
	}

	/**
	 * @param fleetList the fleetList to set
	 */
	public void setFleetList(String fleetList) {
		this.fleetList = fleetList;
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
	
	
	
	
}