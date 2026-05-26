/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.log.corporation;

import java.util.Date;

import persistence.administration.corporation.tables.GeoLabel;
import persistence.log.IdLogPK;


/**
 * @author jrodriguez
 */
public class GeoLabelLog {

	private IdLogPK pk;
    private CorporationLog corporation;
    private String resourceKey;
    private Integer level;
    
    public GeoLabelLog() {        
    }
    
	/**
     * @param label
     * @param user
     * @param date
     */
    public GeoLabelLog(GeoLabel label, String user, Date date, CorporationLog log) {
        pk = new IdLogPK(label.getId(),user,date);
        corporation = log;
        resourceKey = label.getResourceKey();
        level = label.getLevel();
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
    public CorporationLog getCorporation() {
        return corporation;
    }
    
    /**
     * @param divOrg The divOrg to set.
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
     * @return Returns the level.
     */
    public Integer getLevel() {
        return level;
    }
    
    /**
     * @param level The level to set.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}
