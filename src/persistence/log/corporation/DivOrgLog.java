/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.log.corporation;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.DivOrg;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.log.IdLogPK;

/**
 * @author jrodriguez
 */
public class DivOrgLog {

	private IdLogPK pk;
    private CorporationLog corporation;
    private String resourceKey;
    private Set itemLevel1Set;
    private Set levelLabelSet;
    
    public DivOrgLog() {
    }

    /**
     * @param org
     * @param user
     * @param date
     * @param session 
     */
    public DivOrgLog(DivOrg org, String user, Date date, CorporationLog corporationLog, Session session) {
        pk = new IdLogPK(org.getId(),user,date);
        corporation = corporationLog;
        resourceKey = org.getResourceKey();
        itemLevel1Set = new HashSet();
        Iterator it = org.getItemLevel1Set().iterator();
        while(it.hasNext()) {
            itemLevel1Set.add(new DivOrgItemLevel1Log((DivOrgItemLevel1)it.next(),user,date,this,session));
        }
    }

	public IdLogPK getPk() {
		return pk;
	}
	
	public void setPk(IdLogPK pk) {
		this.pk = pk;
	}
    
    /**
     * @return Returns the corporation.
     */
    public CorporationLog getCorporation() {
        return corporation;
    }
    /**
     * @param corporation The corporation to set.
     */
    public void setCorporation(CorporationLog corporation) {
        this.corporation = corporation;
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
     * @return Returns the items.
     */
    public Collection getItemLevel1Set() {
        return itemLevel1Set;
    }
    
    /**
     * @param items The items to set.
     */
    public void setItemLevel1Set(Set itemLevel1Set) {
        this.itemLevel1Set = itemLevel1Set;
    }
    
    /**
     * @return Returns the labels.
     */
    public Collection getLevelLabelSet() {
        return levelLabelSet;
    }
    
    /**
     * @param labels The labels to set.
     */
    public void setLevelLabelSet(Set levelLabelSet) {
        this.levelLabelSet = levelLabelSet;
    }
}
