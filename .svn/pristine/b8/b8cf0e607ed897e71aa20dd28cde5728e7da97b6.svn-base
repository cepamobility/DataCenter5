package persistence.vehicle.tables;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import communication.vehicle.tables.VehicleStateData;
import communication.vehicle.tables.VehicleStateHistoryData;

public class VehicleState {

	private Integer id;
	private String state;
	private Date stateDate;
	private Integer hours=0;
	private String reasonDescription="ACTIVO";
	private Integer reasonCode=-1;//SIN MOTIVO, SE USA pARA ACTIVO
	private Integer typeOfPenalty=-1;//SIN MOTIVO, SE USA pARA ACTIVO
	private Vehicle vehicle;
	
	private Set<VehicleStateHistory> history=new HashSet<VehicleStateHistory>();
	
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
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
    	
	public Set<VehicleStateHistory> getHistory() {
		return history;
	}
	public void setHistory(Set<VehicleStateHistory> history) {
		this.history = history;
	}
	public VehicleStateData getData(){
		VehicleStateData data=new VehicleStateData();
		data.setState(state);
		data.setStateDate(stateDate);
		data.setHours(hours);
		data.setReasonCode(reasonCode);
		data.setReasonDescription(reasonDescription);
		data.setTypeOfPenalty(typeOfPenalty);
		
		
		ArrayList<VehicleStateHistory> listSort=new ArrayList<VehicleStateHistory>(history);
		Collections.sort(listSort, new Comparator<VehicleStateHistory>(){
				public int compare(VehicleStateHistory o1, VehicleStateHistory o2){
					return o2.getStateDate().compareTo(o1.getStateDate());
				}
			}
		);
		ArrayList<VehicleStateHistoryData> listData=new ArrayList<VehicleStateHistoryData>();
		for(VehicleStateHistory vh:listSort){
			listData.add(vh.getData());
		}
		data.setHistory(listData);
		
		return data;
	}
	
	public void activate() {
		 
		this.setState(VehicleStateData.ACTIVE);
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.HOUR, 4);//Hora de uruguay
		this.setStateDate(cal.getTime());
		this.setHours(0);
		this.setReasonCode(-1);
		this.setReasonDescription("ACTIVO");
		this.setTypeOfPenalty(-1);
	}
    

}
