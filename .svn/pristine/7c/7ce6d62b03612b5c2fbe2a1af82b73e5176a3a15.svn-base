/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.log.user;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import persistence.administration.tables.Permission;
import persistence.administration.tables.UserCorporation;


/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserCorporationLog implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private UserCorporationLogPK pk; 
	private Integer action;
	private Set permissions;
	private String role;
	public UserCorporationLog(){}
	
	public UserCorporationLog(UserCorporation userCorporation,String userName,Date date,int action, Session session)
		throws HibernateException{
		
	    this.pk=new UserCorporationLogPK(userCorporation.getPk().getUser().getLoginName(), userCorporation.getPk().getCorporation().getId(), userName, date);
	    this.action = Integer.valueOf(action);

        if(userCorporation != null) {

            role = userCorporation.getRole().getResourceKey();
            this.permissions=new HashSet(); 
            Iterator iter = userCorporation.getPermissions().iterator();
            while(iter.hasNext()) {
                permissions.add(((Permission)iter.next()).getKey());
            }
        }
	}
	
	/**
	 * @param pk The pk to set.
	 */
	public void setPk(UserCorporationLogPK pk) {
		this.pk = pk;
	}
	
	/**
	 * @return Returns the pk.
	 */
	public UserCorporationLogPK getPk() {
		return pk;
	}
	
    /**
     * @return Returns the action.
     */
    public Integer getAction() {
        return action;
    }
    /**
     * @param action The action to set.
     */
    public void setAction(Integer action) {
        this.action = action;
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
	
    /**
     * @return Returns the roleName.
     */
    public String getRole() {
        return role;
    }
    
    /**
     * @param roleName The roleName to set.
     */
    public void setRole(String role) {
        this.role = role;
    }
    
}
