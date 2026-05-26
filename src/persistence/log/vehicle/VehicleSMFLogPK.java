package persistence.log.vehicle;

import java.io.Serializable;
import java.util.Date;

public class VehicleSMFLogPK implements Serializable {

	 private static final long serialVersionUID = 1L;

	 private Integer vehicleSMF;
	 private String userId;
	 private Date date;
	
	 public VehicleSMFLogPK(){
		 
	 }
	 
	 public VehicleSMFLogPK (Integer id, String userId, Date date){
		 this.vehicleSMF= id;
	     this.userId = userId;
	     this.date = date;
	 }

	public Integer getVehicleSMF() {
		return vehicleSMF;
	}

	public void setVehicleSMF(Integer vehicleSMF) {
		this.vehicleSMF = vehicleSMF;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	 	 
}
