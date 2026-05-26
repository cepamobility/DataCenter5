package persistence.alert.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.datatype.tables.AlertFrecuency;
import persistence.datatype.tables.AlertPriority;
import persistence.datatype.tables.AlertType;

import communication.alert.AlertData;
import communication.alert.AlertToData;
import communication.datatype.tables.AlertFrecuencyDataType;
import communication.datatype.tables.AlertPriorityDataType;
import communication.datatype.tables.AlertTypeDataType;

public class Alert implements Serializable, Comparable<Alert> {	
	
	private Integer alertId;
	
	// tipo de alerta
	private String subject;
	private String body;
	private Set to;
	private Set extradata;
	private Date startdate;
	private Date lastdate;
	private Date nextdate;
	private Date enddate;
	private Integer corporation;
	private AlertPriority alertPriority;
	private AlertFrecuency alertFrecuency;
	private AlertType alertType;
	private Integer dailyFrecuency;
	private String description;
	private Integer fleetId;
	private String fleetList;
	private String attachment; // path del archivo a adjuntar
	
	public Alert() {
	}
	
	public Alert(AlertData data, Session session) {
		alertId = data.getAlertId();
		if(data.getAlertType() != null) {
			alertType = (AlertType)session.load(AlertType.class,data.getAlertType().getResourceBundleId()); 
		}
		if(data.getPriority().getResourceBundleId() != null) {
			alertPriority = (AlertPriority)session.load(AlertPriority.class,data.getPriority().getResourceBundleId()); 
		}
		if(data.getFrecuency().getResourceBundleId() != null) {
			alertFrecuency = (AlertFrecuency)session.load(AlertFrecuency.class,data.getFrecuency().getResourceBundleId()); 
		}
		subject = data.getSubject();
	    body = data.getBody();
	    corporation =data.getCorporation();
	    AlertToData alertTo;
		Iterator it = data.getTo().iterator();
		to = new HashSet();
		while (it.hasNext()) {
			alertTo = (AlertToData)it.next();
			to.add(new AlertTo(alertTo));
		}
		
		
		startdate=data.getStartdate();
		lastdate=data.getLastdate();
		nextdate=data.getNextdate();
		enddate=data.getEnddate();
		if(data.getDailyFrecuency() != null) {
			dailyFrecuency=data.getDailyFrecuency();
		}
		
		if(data.getDescription() != null) {
			description=data.getDescription();
		}
		
		fleetId = data.getFleetId();
		this.fleetList = data.getFleetList();
		this.attachment = data.getAttachment();
	}
	
	
	
	public void updateAlert(AlertData data, Session session) {
		//alertId = data.getAlertId();
		if(data.getAlertType() != null) {
			alertType = (AlertType)session.load(AlertType.class,data.getAlertType().getResourceBundleId()); 
		}
		
		if(data.getPriority() != null) {
			alertPriority = (AlertPriority)session.load(AlertPriority.class,data.getPriority().getResourceBundleId()); 
		}
		if(data.getFrecuency() != null) {
			alertFrecuency = (AlertFrecuency)session.load(AlertFrecuency.class,data.getFrecuency().getResourceBundleId()); 
		}
		subject = data.getSubject();
	    body = data.getBody();
	    corporation =data.getCorporation();
	    startdate=data.getStartdate();
		lastdate=data.getLastdate();
		nextdate=data.getNextdate();
		enddate=data.getEnddate();
		if(data.getDailyFrecuency() != null) {
			dailyFrecuency = data.getDailyFrecuency(); 
		}
		if(data.getDescription() != null) {
			description=data.getDescription();
		}
		fleetId = data.getFleetId();
		this.fleetList = data.getFleetList();
		this.attachment = data.getAttachment();
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}
	
	public AlertPriority getAlertPriority() {
		return alertPriority;
	}

	public void setAlertPriority(AlertPriority alertPriority) {
		this.alertPriority = alertPriority;
	}
	
	public AlertFrecuency getAlertFrecuency() {
		return alertFrecuency;
	}

	public void setDailyFrecuency(Integer dailyFrecuency) {
		this.dailyFrecuency = dailyFrecuency;
	}
	
	public Integer getDailyFrecuency() {
		return dailyFrecuency;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setAlertFrecuency(AlertFrecuency alertFrecuency) {
		this.alertFrecuency = alertFrecuency;
	}
	
	public Integer getCorporation() {
		return corporation;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
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

	public Set getTo() {
		return to;
	}

	public void setTo(Set to) {
		this.to = to;
	}
	
	public Set getExtradata() {
		return extradata;
	}

	public void setExtradata(Set extradata) {
		this.extradata = extradata;
	}

	public Integer getAlertId() {
		return alertId;
	}

	public void setAlertId(Integer id) {
		this.alertId = id;
	}
	
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
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
	
	
	public AlertData getAlertData() {
		
		Collection toData = null;
		
		if (to != null && to.size() > 0){
			Iterator it = to.iterator();
			AlertTo alertTo;
			toData = new LinkedList();
			while (it.hasNext()) {
				alertTo = (AlertTo)it.next();
				toData.add(alertTo.getAlertToData());
			}
		}else{
			// System.out.println("Alert.java: to null o vacio");
		}
		
		Collection dataAlert = new LinkedList();	
		
		
		AlertTypeDataType typeAlert = (alertType == null) ? null : 
			(AlertTypeDataType)alertType.getData();
		
		AlertPriorityDataType priorityAlert = (alertPriority == null) ? null : 
			(AlertPriorityDataType)alertPriority.getData();
		
		AlertFrecuencyDataType frecuencyAlert = (alertFrecuency == null) ? null : 
			(AlertFrecuencyDataType)alertFrecuency.getData();
		
		return new AlertData(alertId,typeAlert,subject,toData,dataAlert,
				startdate,lastdate,nextdate,enddate,body,corporation,
				priorityAlert,frecuencyAlert,dailyFrecuency,description,fleetId,
				this.fleetList,this.attachment);		
	}

	/**
	 * 
	 */
	public int compareTo(Alert arg0) {
		return this.alertId.compareTo(arg0.alertId);
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
