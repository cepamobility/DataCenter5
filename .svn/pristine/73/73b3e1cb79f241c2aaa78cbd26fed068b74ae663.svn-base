/**
 * Created on 21-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.event;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jrodriguez
 */
public class EventLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer event;
	private String userId;
	private Date date;

	public EventLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public EventLogPK(Integer id, String userId, Date date) {
        this.event = id;
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
    public Integer getEvent() {
        return event;
    }
    /**
     * @param id The id to set.
     */
    public void setEvent(Integer event) {
        this.event = event;
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