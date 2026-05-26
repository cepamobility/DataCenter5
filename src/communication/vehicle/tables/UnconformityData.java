package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

public class UnconformityData implements Serializable {

	private Date date;
	
    private String origin;
    private String department;
    private Integer charge;
    private String observations;

    public UnconformityData() {
    }
    
    
    public UnconformityData(Date date, String origin, String department, 
    		Integer charge, String observations) {
		this.date = date;
		this.origin = origin;
		this.department = department;
		this.charge = charge;
		this.observations = observations;
	}


	public UnconformityData(Object[] data) {
		this.date = (Date)data[1];
		this.origin = (String)data[2];
		this.department = (String)data[3];
		this.charge = (Integer)data[4];
		this.observations = (String)data[5];
	}


	public Date getDate() {
		return date;
	}
	
    public void setDate(Date date) {
		this.date = date;
	}
	
    public String getOrigin() {
		return origin;
	}
	
    public void setOrigin(String origin) {
		this.origin = origin;
	}
	
    public String getDepartment() {
		return department;
	}
	
    public void setDepartment(String department) {
		this.department = department;
	}
	
    public Integer getCharge() {
		return charge;
	}
	
    public void setCharge(Integer charge) {
		this.charge = charge;
	}
	
    public String getObservations() {
		return observations;
	}
	
    public void setObservations(String observations) {
		this.observations = observations;
	}

}
