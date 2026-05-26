/**
 * Created on 21-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adocarmo
 */
public class ActivityRegistryLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer registry;
	private String userId;
	private Date date;

	public ActivityRegistryLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public ActivityRegistryLogPK(Integer id, String userId, Date date) {
        this.registry = id;
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
    public Integer getRegistry() {
        return registry;
    }
    /**
     * @param id The id to set.
     */
    public void setRegistry(Integer registry) {
        this.registry = registry;
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