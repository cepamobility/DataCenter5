package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;


import communication.vehicle.tables.VehicleOptionalInfoData;
import communication.vehicle.tables.VehicleSMFData;

public class VehicleSMF implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer vehicleSMFid;
		
	private Integer orderNumber;
	private String corporationSMF; 
	private Date instalationDateSMF;
	private String responsibleTechnical;
	private String localInstallation;
	private String observationsSMF;
	private String ustContact;
	
	public VehicleSMF(){
		
	}
	
	public VehicleSMF(VehicleSMFData data){
		
		if(data!=null){
			
		this.vehicleSMFid = data.getVehicleSMFId();
		
		this.orderNumber = data.getOrderNumber();
		this.corporationSMF = data.getCorporationSMF();
		this.instalationDateSMF = data.getInstalationDateSMF();
		this.responsibleTechnical = data.getResponsibleTechnical();
		this.localInstallation = data.getLocalInstallation();
		this.observationsSMF = data.getObservations();
		this.ustContact = data.getUstContact();
		
		}
	}
	public VehicleSMF (Integer vehicleSMFid, Integer orderNumber,String corporationSMF, Date instalationSMF,
			String responsableTechnical,String localInstalation,String observationSMF,String ustContact){
		
		this.vehicleSMFid = vehicleSMFid;
		
		this.orderNumber = orderNumber;
		this.corporationSMF = corporationSMF;
		this.instalationDateSMF = instalationSMF;
		this.responsibleTechnical = responsableTechnical;
		this.localInstallation = localInstalation;
		this.observationsSMF = observationSMF;
		this.ustContact = ustContact;
		
	}
	
	public VehicleSMFData getVehicleSMFData(){
		return new VehicleSMFData (vehicleSMFid,orderNumber,corporationSMF,instalationDateSMF,
				responsibleTechnical,localInstallation,observationsSMF,ustContact);
	}
	
	/**
	 * Actualiza el vehicle SMF con los datos que vienen del DATA
	 * @param data
	 * @param session
	 */
	public void updateData(VehicleSMFData data, Session session)
	{
		if (data != null){			
			orderNumber = data.getOrderNumber();
			corporationSMF = data.getCorporationSMF();
			instalationDateSMF = data.getInstalationDateSMF();
			responsibleTechnical = data.getResponsibleTechnical();
			localInstallation = data.getLocalInstallation();
			observationsSMF = data.getObservations();
			ustContact = data.getUstContact();
		}
		
	 }
	
	public Integer getVehicleSMFid() {
		return vehicleSMFid;
	}

	public void setVehicleSMFid(Integer vehicleSMFid) {
		this.vehicleSMFid = vehicleSMFid;
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