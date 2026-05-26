
package persistence.log.vehicle;

import java.io.Serializable;
import java.util.Date;
import persistence.vehicle.tables.VehicleSMF;

public class VehicleSMFLog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private VehicleSMFLogPK pk;
	
    private Integer action;   
    private String comment; 
    
	private Integer orderNumber;
	private String corporationSMF; 
	private Date instalationDateSMF;
	private String responsibleTechnical;
	private String localInstallation;
	private String observationsSMF;
	private String ustContact;
	
	public VehicleSMFLog(){
		
	}
	
	public VehicleSMFLog (VehicleSMF data, String user, Date date, int action, String comment){
		
		pk = new VehicleSMFLogPK(data.getVehicleSMFid(),user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;		
   		
		this.orderNumber = data.getOrderNumber();
		this.corporationSMF = data.getCorporationSMF();
		this.instalationDateSMF = data.getInstalationDateSMF();
		this.responsibleTechnical = data.getResponsibleTechnical();
		this.localInstallation = data.getLocalInstallation();
		this.observationsSMF = data.getObservationsSMF();
		this.ustContact = data.getUstContact();
		
	}

	public VehicleSMFLogPK getPk() {
		return pk;
	}

	public void setPk(VehicleSMFLogPK pk) {
		this.pk = pk;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCorporationSMF() {
		return corporationSMF;
	}

	public void setCorporationSMF(String corporationSMF) {
		this.corporationSMF = corporationSMF;
	}

	public Date getInstalationDateSMF() {
		return instalationDateSMF;
	}

	public void setInstalationDateSMF(Date instalationDateSMF) {
		this.instalationDateSMF = instalationDateSMF;
	}

	public String getResponsibleTechnical() {
		return responsibleTechnical;
	}

	public void setResponsibleTechnical(String responsibleTechnical) {
		this.responsibleTechnical = responsibleTechnical;
	}

	public String getLocalInstallation() {
		return localInstallation;
	}

	public void setLocalInstallation(String localInstallation) {
		this.localInstallation = localInstallation;
	}

	public String getObservationsSMF() {
		return observationsSMF;
	}

	public void setObservationsSMF(String observationsSMF) {
		this.observationsSMF = observationsSMF;
	}

	public String getUstContact() {
		return ustContact;
	}

	public void setUstContact(String ustContact) {
		this.ustContact = ustContact;
	}
	
}
