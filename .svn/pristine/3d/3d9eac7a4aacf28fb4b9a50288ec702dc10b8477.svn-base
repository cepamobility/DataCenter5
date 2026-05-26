package communication.fleet.tables;


import java.io.Serializable;
import java.util.Date;

public class VehicleFleetObservationData implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fleetId;
	private Integer vehicleId;
    private Date date;
    private String user;

    public VehicleFleetObservationData(Integer fleetId, Integer vehicleId, Date date, String user) {
    	this.fleetId = fleetId;
    	this.vehicleId = vehicleId;
        this.date = date;
        this.user = user;
    }

    
    public Integer getFleetId() {
		return fleetId;
	}


	public void setFleetId(Integer fleetId) {
		this.fleetId = fleetId;
	}


	

    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return Returns the driverId.
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * @return Returns the user.
     */
    public String getUser() {
        return user;
    }


    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param driverId The driverId to set.
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * @param user The user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

}

