package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;

public class VehicleContractPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Vehicle vehicle;
    private Date date;
	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	public VehicleContractPK(Integer vehicleId, Date date, Session session) {
		this.vehicle = (Vehicle) session.load(Vehicle.class, vehicleId);
		this.date = date;
	}

	public VehicleContractPK(Vehicle vehicle, Date date) {
		super();
		this.vehicle = vehicle;
		this.date = date;
	}
	
	 public VehicleContractPK(Integer vehicleId, String date) {
	    	vehicle = new Vehicle();
	    	vehicle.setId(vehicleId);
	        this.date = new Date(Long.parseLong(date));
	    }
	
	public VehicleContractPK() {
		super();	
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleContractPK other = (VehicleContractPK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}
    
    
}
