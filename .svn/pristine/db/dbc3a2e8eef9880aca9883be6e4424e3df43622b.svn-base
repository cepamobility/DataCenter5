
package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class VehicleSMFData implements Serializable{

	private Integer vehicleSMFid;
	
	private Integer orderNumber;
	private String corporationSMF; 
	private Date instalationDateSMF;
	private String responsibleTechnical;
	private String localInstallation;
	private String observations;
	private String ustContact;
	
	public VehicleSMFData(){
		
	}
	
	public VehicleSMFData(Integer id,Integer orderNumber,String corporation,
			Date instalationDateSMF,String responsibleTechnical,String localInstallation,
			String observations,String ustContact){
		
		this.vehicleSMFid = id;
		
		this.orderNumber = orderNumber;
		this.corporationSMF = corporation;
		this.instalationDateSMF = instalationDateSMF;
		this.responsibleTechnical = responsibleTechnical;
		this.localInstallation = localInstallation;
		this.observations = observations;
		this.ustContact = ustContact;
		
	}
	
	public VehicleSMFData getVehicleSMFData(){
		return new VehicleSMFData(vehicleSMFid, orderNumber, corporationSMF,
				instalationDateSMF, responsibleTechnical, localInstallation,
				observations, ustContact);		
	}
	
	public Integer getVehicleSMFId() {
		return vehicleSMFid;
	}


	public void setVehicleSMFId(Integer id) {
		this.vehicleSMFid = id;
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

	public void setCorporationSMF(String corporation) {
		this.corporationSMF = corporation;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getUstContact() {
		return ustContact;
	}

	public void setUstContact(String ustContact) {
		this.ustContact = ustContact;
	}
	
	public String getDateInstalationYYYYMMDD(Date instalationDateSMF) {
		if(instalationDateSMF == null) {
            return "";}
		else{
		Calendar instalationSMF = Calendar.getInstance();
		instalationSMF.setTime(instalationDateSMF);
					
		String month = (instalationSMF.get(Calendar.MONTH)+1 < 10) ? 
    			"0"+ String.valueOf(instalationSMF.get(Calendar.MONTH)+1):
    				String.valueOf(instalationSMF.get(Calendar.MONTH)+1);
		
    	String day =(instalationSMF.get(Calendar.DAY_OF_MONTH)+1 < 10) ? 
    			"0"+ String.valueOf(instalationSMF.get(Calendar.DAY_OF_MONTH)+1):
    				String.valueOf(instalationSMF.get(Calendar.DAY_OF_MONTH)+1);
    	
    			return day+"-"+month+"-"+instalationSMF.get(Calendar.YEAR);
		}
	}
	
}