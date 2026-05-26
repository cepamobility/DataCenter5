/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.tables;

import java.io.Serializable;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.user.tables.User;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserCorporationPK implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private User user;
	private Corporation corporation;
	
	/**
	 * 
	 */
	public UserCorporationPK() {
	}
	
	public UserCorporationPK(User user, Corporation corporation) {
		this.user=user;
		this.corporation=corporation;
	}
	
	public UserCorporationPK(String loginName, Integer corporationId) {
		user=new User();
		user.setLoginName(loginName);
		corporation=new Corporation();
		corporation.setId(corporationId);
	}

	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @param user The user to set.
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * @return Returns the corporation.
	 */
	public Corporation getCorporation() {
		return corporation;
	}
	
	/**
	 * @param corporation The corporation to set.
	 */
	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((corporation == null) ? 0 : corporation.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserCorporationPK other = (UserCorporationPK) obj;
		if (corporation == null) {
			if (other.corporation != null)
				return false;
		} else if (!corporation.equals(other.corporation))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
