/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

public class UnconformityPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Vehicle vehicle;
    private Date date;

    public UnconformityPK() {
    }

    public UnconformityPK(Date date, Vehicle vehicle) {
        this.date = date;
        this.vehicle = vehicle;
    }

    public UnconformityPK(Integer vehicleId, String date) {
    	vehicle = new Vehicle();
    	vehicle.setId(vehicleId);
        this.date = new Date(Long.parseLong(date));
    }

	public Date getDate() {
        return date;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
		UnconformityPK other = (UnconformityPK) obj;
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
