package persistence.driver.tables;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import communication.driver.tables.DriverStateData;
import communication.driver.tables.DriverStateHistoryData;
import communication.vehicle.tables.VehicleStateData;
import communication.vehicle.tables.VehicleStateHistoryData;

public class DriverState {

	private Integer id;
	private String state;
	private Date stateDate;
	private String reasonDescription;
	private Driver driver;
	private Set<DriverStateHistory> history=new HashSet<DriverStateHistory>();
	
	
	public String getReasonDescription() {
		return reasonDescription;
	}
	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
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
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
    	
	public Set<DriverStateHistory> getHistory() {
		return history;
	}
	public void setHistory(Set<DriverStateHistory> history) {
		this.history = history;
	}
	public DriverStateData getData(){
		DriverStateData data=new DriverStateData();
		data.setState(state);
		data.setStateDate(stateDate);
		data.setReasonDescription(reasonDescription);
		
		ArrayList<DriverStateHistory> listSort=new ArrayList<DriverStateHistory>(history);
		Collections.sort(listSort, new Comparator<DriverStateHistory>(){
				public int compare(DriverStateHistory o1, DriverStateHistory o2){
					return o2.getStateDate().compareTo(o1.getStateDate());
				}
			}
		);
		ArrayList<DriverStateHistoryData> listData=new ArrayList<DriverStateHistoryData>();
		for(DriverStateHistory vh:listSort){
			listData.add(vh.getData());
		}
		data.setHistory(listData);
		
		return data;
	}
	
	
    

}
