/**
 * Created on 21-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.driver;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jrodriguez
 */
public class ProvisionalBTWLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer driver;
    private Date expiry;
	private String userId;
	private Date date;

	public ProvisionalBTWLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public ProvisionalBTWLogPK(Integer id, Date expiry,String userId, Date date) {
        this.driver = id;
        this.expiry = expiry;
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
    public Integer getDriver() {
        return driver;
    }
    /**
     * @param id The id to set.
     */
    public void setDriver(Integer driver) {
        this.driver = driver;
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

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
}