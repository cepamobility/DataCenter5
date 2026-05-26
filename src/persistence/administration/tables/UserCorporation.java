/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.tables;


import java.io.Serializable;
import java.util.Set;

import org.hibernate.HibernateException;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.user.tables.User;

import communication.administration.corporation.tables.CorporationAttribute;


/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserCorporation implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private UserCorporationPK pk; 
	
	private Set permissions;
	
	private Role role;
	
	public UserCorporation(){}
	
	
	public UserCorporation(User user,Corporation corporation)
	throws HibernateException{
		this.pk=new UserCorporationPK(user,corporation);		
	}
	
	/**
	 * @param pk The pk to set.
	 */
	public void setPk(UserCorporationPK pk) {
		this.pk = pk;
	}
	
	/**
	 * @return Returns the pk.
	 */
	public UserCorporationPK getPk() {
		return pk;
	}
	
	/**
	 * @return Returns the permissions.
	 */
	public Set getPermissions() {
		return permissions;
	}
	
	/**
	 * @param permissions The permissions to set.
	 */
	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg0) {
		UserCorporation userCorporation=(UserCorporation)arg0;
		String userId1=this.pk.getUser().getLoginName();
		String userId2=userCorporation.getPk().getUser().getLoginName();
		Integer corporationId1=this.pk.getCorporation().getId();
		Integer corporationId2=userCorporation.getPk().getCorporation().getId();
		if(userId1.equals(userId2) && corporationId1.equals(corporationId2)){
			return true;
		}
		else{
			return false;
		}
	}
	
    /**
     * @return Returns the roleName.
     */
    public Role getRole() {
        return role;
    }
    
    /**
     * @param roleName The roleName to set.
     */
    public void setRole(Role role) {
        this.role = role;
    }
    
    /**
     * @return
     */
    public CorporationAttribute getCorporationAttribute() {
        return new CorporationAttribute(pk.getCorporation().getId(),pk.getCorporation().getName(),pk.getCorporation().getType());
    }
        
}
