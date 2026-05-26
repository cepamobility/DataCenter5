package communication.vehicle.tables;

import java.util.Date;


public class VehicleStateHistoryData  {

	private Integer id;
	private Date stateDate;
    private String state;
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
	public Date getStateDate() {
		return stateDate;
	}
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
	 
    
	
}
