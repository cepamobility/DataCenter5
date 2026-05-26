/**
 * Created on 03-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import communication.administration.RoleData;



/**
 * @author jrodriguez
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String resourceKey;
    private boolean cepaUser;
    private Set permissions;
    
    /**
     * Default Constructor 
     */
    public Role() {
        permissions = new HashSet();
    }

    /**
     * Constructor with name 
     * @param cepaUser 
     */
    public Role(String resourceKey, boolean cepaUser) {
        this.resourceKey = resourceKey;
        this.cepaUser = cepaUser;
        permissions = new HashSet();
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
     * @return Returns the resourceKey.
     */
    public String getResourceKey() {
        return resourceKey;
    }
    
    /**
     * @param resourceKey The resourceKey to set.
     */
    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    /**
     * @return
     */
    public RoleData getData() {
        Collection permissionNames = new LinkedList();
        Iterator it = permissions.iterator();
        while(it.hasNext()) {
            permissionNames.add(((Permission)it.next()).getData());
        }
        return new RoleData(resourceKey,cepaUser,permissionNames);
    }

    /**
     * @return Returns the cepaUser.
     */
    public boolean isCepaUser() {
        return cepaUser;
    }

    /**
     * @param cepaUser The cepaUser to set.
     */
    public void setCepaUser(boolean cepaUser) {
        this.cepaUser = cepaUser;
    }
 
    /**
     * 
     */
    public boolean isContractor(){
    	return this.resourceKey.equals(RoleData.CONTRACTOR);
    }
}
