/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.log.corporation;

import java.util.Date;

import persistence.administration.corporation.tables.DivOrgItemLevel3;
import persistence.log.IdLogPK;

/**
 * @author jrodriguez
 */
public class DivOrgItemLevel3Log {

	private IdLogPK pk;
    private String resourceKey;
    private boolean active;
    
    public DivOrgItemLevel3Log() {        
    }
        
    /**
     * @param divOrgItemLevel3
     */
    public DivOrgItemLevel3Log(DivOrgItemLevel3 level3,String user,Date date) {
        pk = new IdLogPK(level3.getId(),user,date);
        resourceKey = level3.getResourceKey();
        active = level3.isActive();
    }

    public IdLogPK getPk() {
		return pk;
	}
	
	public void setPk(IdLogPK pk) {
		this.pk = pk;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
