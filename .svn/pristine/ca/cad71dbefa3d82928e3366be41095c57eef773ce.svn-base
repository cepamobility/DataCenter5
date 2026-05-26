package persistence.vehicle.tables;

import communication.administration.user.UserSessionData;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;


public class DPAVehicleActive {

	private Integer id;
	private Integer year;
	private Integer month;
	private String regiao;
	private Integer value;
    
    
    public DPAVehicleActive() {
	}

    public DPAVehicleActive(DPAVehicleActiveData data) {
    	this.id=data.getId();
    	this.year=data.getYear();
    	this.regiao=data.getRegiao();
    	this.month=data.getMonth();
    	this.value=data.getValue();
    			
	}
    
    public DPAVehicleActiveData getData(UserSessionData ses){
    	return new DPAVehicleActiveData(id, year, month,regiao, value);
    }

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Integer getValue() {
		return value;
	}


	public void setValue(Integer value) {
		this.value = value;
	}
	
   
    

}
