/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.log;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IdLogPK implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private Integer id;
	private String loginUser;
	private Date date;
	
    /**
     * 
     */
    public IdLogPK() {
    }
    
    /**
     * @param userId
     * @param corporation
     * @param loginUser
     * @param date
     */
    public IdLogPK(Integer id, String loginUser, Date date) {
        this.id = id;
        this.loginUser = loginUser;
        this.date = date;
    }
    
    /**
     * @return Returns the corporation.
     */
    public Integer getId() {
        return id;
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
     * @param corporation The corporation to set.
     */
    public void setId(Integer id) {
        this.id = id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((loginUser == null) ? 0 : loginUser.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdLogPK other = (IdLogPK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginUser == null) {
			if (other.loginUser != null)
				return false;
		} else if (!loginUser.equals(other.loginUser))
			return false;
		return true;
	}
    
    
}
