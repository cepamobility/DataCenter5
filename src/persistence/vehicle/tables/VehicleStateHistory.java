package persistence.vehicle.tables;

import java.util.Date;

import communication.vehicle.tables.VehicleStateHistoryData;


public class VehicleStateHistory {

	private Integer id;
	private String state;
	private Date stateDate;
	private Integer hours;
	private String reasonDescription;
	private Integer reasonCode;
	private Integer typeOfPenalty;
	
	
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public String getReasonDescription() {
		return reasonDescription;
	}
	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}
	public Integer getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}
	public Integer getTypeOfPenalty() {
		return typeOfPenalty;
	}
	public void setTypeOfPenalty(Integer typeOfPenalty) {
		this.typeOfPenalty = typeOfPenalty;
	}
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getStateDate() {
		return stateDate;
	}
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}
	 
    	
	public VehicleStateHistoryData getData(){
		VehicleStateHistoryData data=new VehicleStateHistoryData();
		data.setState(state);
		data.setStateDate(stateDate);
		data.setId(id);
		data.setHours(hours);
		data.setReasonCode(reasonCode);
		data.setReasonDescription(reasonDescription);
		data.setTypeOfPenalty(typeOfPenalty);
		
		return data;
	}
    

}
