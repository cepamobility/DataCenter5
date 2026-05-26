/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.log.corporation;

import java.util.Date;

import org.hibernate.Session;

import persistence.administration.corporation.tables.DivOrgItemLevel2;
import persistence.log.IdLogPK;

/**
 * @author jrodriguez
 */
public class DivOrgItemLevel2Log {

	private IdLogPK pk;
    private String resourceKey;
    private boolean active;
    private DivOrgItemLevel3Log divOrgItemLevel3;
    
    public DivOrgItemLevel2Log() {        
    }
        
	/**
     * @param divOrgItemLevel2
     * @param user
     * @param date
	 * @param session 
     */
    public DivOrgItemLevel2Log(DivOrgItemLevel2 level2, String user, Date date, Session session) {
        pk = new IdLogPK(level2.getId(),user,date);
        resourceKey = level2.getResourceKey();
        active = level2.isActive();
        if(level2.getDivOrgItemLevel3() != null) {
            divOrgItemLevel3=(DivOrgItemLevel3Log)session.get(DivOrgItemLevel3Log.class,new IdLogPK(level2.getDivOrgItemLevel3().getId(),user,date));
            if(divOrgItemLevel3 == null) {
                divOrgItemLevel3 = new DivOrgItemLevel3Log(level2.getDivOrgItemLevel3(),user,date);
                session.save(divOrgItemLevel3);
            }
            
        }
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

    /**
     * @return Returns the parent.
     */
    public DivOrgItemLevel3Log getDivOrgItemLevel3() {
        return divOrgItemLevel3;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setDivOrgItemLevel3(DivOrgItemLevel3Log divOrgItemLevel3) {
        this.divOrgItemLevel3 = divOrgItemLevel3;
    }
}
