/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.log.corporation;


import java.util.Date;

import org.hibernate.Session;

import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.log.IdLogPK;

/**
 * @author jrodriguez
 */
public class DivOrgItemLevel1Log {

	private IdLogPK pk;
    private DivOrgLog divOrg;
    private String resourceKey;
    private boolean active;
    private DivOrgItemLevel2Log divOrgItemLevel2;
    
    public DivOrgItemLevel1Log() {        
    }
        
	/**
     * @param level1
     * @param user
     * @param date
     */
    public DivOrgItemLevel1Log(DivOrgItemLevel1 level1, String user, Date date, DivOrgLog divOrgLog, Session session) {
        pk = new IdLogPK(level1.getId(),user,date);
        divOrg = divOrgLog;
        resourceKey = level1.getResourceKey();
        active = level1.isActive();
        if(level1.getDivOrgItemLevel2() != null) {
            divOrgItemLevel2=(DivOrgItemLevel2Log)session.get(DivOrgItemLevel2Log.class,new IdLogPK(level1.getDivOrgItemLevel2().getId(),user,date));
            if(divOrgItemLevel2 == null) {
                divOrgItemLevel2 = new DivOrgItemLevel2Log(level1.getDivOrgItemLevel2(),user,date,session);
                session.save(divOrgItemLevel2);
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
     * @return Returns the divOrg.
     */
    public DivOrgLog getDivOrg() {
        return divOrg;
    }
    
    /**
     * @param divOrg The divOrg to set.
     */
    public void setDivOrg(DivOrgLog divOrg) {
        this.divOrg = divOrg;
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
    public DivOrgItemLevel2Log getDivOrgItemLevel2() {
        return divOrgItemLevel2;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setDivOrgItemLevel2(DivOrgItemLevel2Log divOrgItemLevel2) {
        this.divOrgItemLevel2 = divOrgItemLevel2;
    }
}
