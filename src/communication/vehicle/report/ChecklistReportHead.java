package communication.vehicle.report;

import java.io.Serializable;

public class ChecklistReportHead implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer vehicleId;
    private String transporter;
    private String plate;
    private String date;
    private Integer vehicleCheckId;
    private String observations;
    private String inspectedBy;
    private String result;
    private Integer unconformityId;
    private boolean withObservations;
    
	public boolean isWithObservations() {
		return withObservations;
	}
	public void setWithObservations(boolean withObservations) {
		this.withObservations = withObservations;
	}
	public Integer getUnconformityId() {
		return unconformityId;
	}
	public void setUnconformityId(Integer unconformityId) {
		this.unconformityId = unconformityId;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	 
	public String getTransporter() {
		return transporter;
	}
	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	 
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getVehicleCheckId() {
		return vehicleCheckId;
	}
	public void setVehicleCheckId(Integer vehicleCheckId) {
		this.vehicleCheckId = vehicleCheckId;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getInspectedBy() {
		return inspectedBy;
	}
	public void setInspectedBy(String inspectedBy) {
		this.inspectedBy = inspectedBy;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
    
    

}
