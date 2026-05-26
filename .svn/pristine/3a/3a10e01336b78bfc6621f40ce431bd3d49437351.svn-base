/**
 * Created on 21-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jrodriguez
 */
public class UserLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
	private String loginUser;
	private Date date;

	public UserLogPK() {
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public UserLogPK(String userId, String loginUser, Date date) {
        this.userId = userId;
        this.loginUser = loginUser;
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
    public String getUserId() {
        return userId;
    }
    /**
     * @param id The id to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return Returns the user.
     */
    public String getLoginUser() {
        return loginUser;
    }
    /**
     * @param user The user to set.
     */
    public void setLoginUser(String userId) {
        this.loginUser = userId;
    }
}