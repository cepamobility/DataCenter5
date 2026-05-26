package persistence.log.vehicle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adocarmo
 */
public class VehicleOptionalInfoLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer optionalInfo;
	private String userId;
	private Date date;

	public VehicleOptionalInfoLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public VehicleOptionalInfoLogPK(Integer id, String userId, Date date) {
        this.optionalInfo = id;
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
    public Integer getOptionalInfo() {
        return optionalInfo;
    }
    /**
     * @param id The id to set.
     */
    public void setOptionalInfo(Integer id) {
        this.optionalInfo = id;
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