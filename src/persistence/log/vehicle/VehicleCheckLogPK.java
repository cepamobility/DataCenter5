/**
 * Created on 21-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.vehicle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adocarmo
 */
public class VehicleCheckLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer vehicleCheckId;
	private String userId;
	private Date date;

	public VehicleCheckLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public VehicleCheckLogPK(Integer id, String userId, Date date) {
        this.vehicleCheckId = id;
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
    public Integer getVehicleCheckId() {
        return vehicleCheckId;
    }
    /**
     * @param id The id to set.
     */
    public void setVehicleCheckId(Integer vehicleCheckId) {
        this.vehicleCheckId = vehicleCheckId;
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