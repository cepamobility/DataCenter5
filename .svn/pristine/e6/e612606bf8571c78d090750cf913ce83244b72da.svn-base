/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.log.corporation;

import java.util.Date;

import persistence.administration.corporation.tables.GeoLevel2;
import persistence.log.IdLogPK;

/**
 * @author jrodriguez
 */
public class GeoLevel2Log {

	private IdLogPK pk;
    private String resourceKey;
    
    public GeoLevel2Log() {        
    }
        
	/**
     * @param geoLevel2
     * @param user
     * @param date
     */
    public GeoLevel2Log(GeoLevel2 geoLevel2, String user, Date date) {
        pk = new IdLogPK(geoLevel2.getId(),user,date);
        resourceKey = geoLevel2.getResourceKey();
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
}
