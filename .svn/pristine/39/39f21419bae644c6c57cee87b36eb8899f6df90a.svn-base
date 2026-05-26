package persistence.driver.tables;

import java.util.Date;

import communication.driver.tables.DriverStateHistoryData;


public class DriverStateHistory {

	private Integer id;
	private String state;
	private Date stateDate;
	private String reasonDescription;
		
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


	public String getReasonDescription() {
		return reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}


	public DriverStateHistoryData getData(){
		DriverStateHistoryData data=new DriverStateHistoryData();
		data.setState(state);
		data.setStateDate(stateDate);
		data.setId(id);
		data.setReasonDescription(reasonDescription);
		
		return data;
	}
    

}
