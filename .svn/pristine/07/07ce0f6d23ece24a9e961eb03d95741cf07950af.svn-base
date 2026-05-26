package persistence.log.fleet;

import java.io.Serializable;
import java.util.Date;

public class VehicleFleetLogPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fleetId;
	private String userId;
	private Date date;
	
	public VehicleFleetLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public VehicleFleetLogPK(Integer id, String userId, Date date) {
        this.fleetId = id;
        this.userId = userId;
        this.date = date;
    }
    
    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @return Returns the id.
     */
    public Integer getFleetId() {
        return fleetId;
    }
    /**
     * @param id The id to set.
     */
    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }
    /**
     * @return Returns the user.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param user The user to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}