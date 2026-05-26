/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import java.io.Serializable;

import persistence.vehicle.tables.Vehicle;


public class OdometerPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IndividualFleet fleet;
    private Vehicle vehicle;
    private Integer period;
    private Integer year;

    public OdometerPK() {
    }

    /**
     * @param fleet
     * @param period
     * @param year
     */
    public OdometerPK(IndividualFleet fleet, Vehicle vehicle, Integer period, Integer year) {
        this.fleet = fleet;
        this.vehicle = vehicle;
        this.period = period;
        this.year = year;
    }

    public IndividualFleet getFleet() {
        return fleet;
    }

    
    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public void setFleet(IndividualFleet fleet) {
        this.fleet = fleet;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Vehicle getVehicle() {
        return vehicle;
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
		result = prime * result + ((fleet == null) ? 0 : fleet.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		OdometerPK other = (OdometerPK) obj;
		if (fleet == null) {
			if (other.fleet != null)
				return false;
		} else if (!fleet.equals(other.fleet))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
    
    
}
