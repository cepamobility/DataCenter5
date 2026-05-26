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
public class VehicleLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer vehicle;
	private String userId;
	private Date date;

	public VehicleLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public VehicleLogPK(Integer id, String userId, Date date) {
        this.vehicle = id;
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
    public Integer getVehicle() {
        return vehicle;
    }
    /**
     * @param id The id to set.
     */
    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
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