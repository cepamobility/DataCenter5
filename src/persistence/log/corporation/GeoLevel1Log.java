/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.log.corporation;

import java.util.Date;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.GeoLevel1;
import persistence.log.IdLogPK;


/**
 * @author jrodriguez
 */
public class GeoLevel1Log {

	private IdLogPK pk;
    private String resourceKey;
    private GeoLevel2Log geoLevel2;
    
    public GeoLevel1Log() {        
    }
        
    /**
     * @param session 
     * @param id
     * @param resourceKey
     * @param divOrgItemLevel2
     */
    public GeoLevel1Log(GeoLevel1 level, String user, Date date, Session session) {
        pk = new IdLogPK(level.getId(),user,date);
        resourceKey = level.getResourceKey();
        if(level.getGeoLevel2() != null) {
            geoLevel2 = (GeoLevel2Log)session.load(GeoLevel2Log.class,new IdLogPK(level.getGeoLevel2().getId(),user,date));
            if(geoLevel2 == null) {
                geoLevel2 = new GeoLevel2Log(level.getGeoLevel2(),user,date);
                session.save(geoLevel2);
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
    
    /**
     * @return Returns the parent.
     */
    public GeoLevel2Log getGeoLevel2() {
        return geoLevel2;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setGeoLevel2(GeoLevel2Log geoLevel2) {
        this.geoLevel2 = geoLevel2;
    }
}
