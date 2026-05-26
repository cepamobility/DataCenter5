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
public class ActivityLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer activity;
	private String userId;
	private Date date;

	public ActivityLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public ActivityLogPK(Integer id, String userId, Date date) {
        this.activity = id;
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
    public Integer getActivity() {
        return activity;
    }
    /**
     * @param id The id to set.
     */
    public void setActivity(Integer activity) {
        this.activity = activity;
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