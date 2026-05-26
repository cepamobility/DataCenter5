/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.log.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserCorporationLogPK implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private String userId;	
	private Integer corporationId;
	private String loginUser;
	private Date date;
	
    /**
     * 
     */
    public UserCorporationLogPK() {
    }
    
    /**
     * @param userId
     * @param corporation
     * @param loginUser
     * @param date
     */
    public UserCorporationLogPK(String userId, Integer corporation,
            String loginUser, Date date) {
        this.userId = userId;
        this.corporationId = corporation;
        this.loginUser = loginUser;
        this.date = date;
    }
    
    /**
     * @return Returns the corporation.
     */
    public Integer getCorporationId() {
        return corporationId;
    }
    
    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * @return Returns the loginUser.
     */
    public String getLoginUser() {
        return loginUser;
    }
    
    /**
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param corporation The corporation to set.
     */
    public void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }
    
    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * @param loginUser The loginUser to set.
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
    
    /**
     * @param userId The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
