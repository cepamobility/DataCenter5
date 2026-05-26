/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.log.corporation;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.log.IdLogPK;


/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorporationCountryLog implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private CorporationCountryLogPK pk; 
	private boolean active;
	private Set geoLevel1Set;
	
	public CorporationCountryLog(){}
	
	public CorporationCountryLog(CorporationCountry corporationCountry,String user,Date date, Session session)
		throws HibernateException{
		pk=new CorporationCountryLogPK(corporationCountry.getPk().getCountry().getId(),corporationCountry.getPk().getCorporation().getId(),user,date);
		active=corporationCountry.isActive();
		
		geoLevel1Set=new HashSet();
		if(corporationCountry.getGeoLevel1Set() != null) {
			Iterator it = corporationCountry.getGeoLevel1Set().iterator();
			while(it.hasNext()) {
                GeoLevel1 level1 = (GeoLevel1)it.next();
			    GeoLevel1Log geoLevel1Log = (GeoLevel1Log)session.load(GeoLevel1Log.class,new IdLogPK(level1.getId(),user,date));
                if(geoLevel1Log == null) {
                    geoLevel1Log = new GeoLevel1Log((GeoLevel1)it.next(),user,date,session);
                    session.save(geoLevel1Log);
                }                
                geoLevel1Set.add(geoLevel1Log);
			}
		}
	}

    /**
     * @return Returns the pk.
     */
    public CorporationCountryLogPK getPk() {
        return pk;
    }
    
    /**
     * @param pk The pk to set.
     */
    public void setPk(CorporationCountryLogPK pk) {
        this.pk = pk;
    }

    /**
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * @return Returns the geoLevel1.
     */
    public Set getGeoLevel1Set() {
        return geoLevel1Set;
    }
    
    /**
     * @param geoLevel1 The geoLevel1 to set.
     */
    public void setGeoLevel1Set(Set geoLevel1) {
        this.geoLevel1Set = geoLevel1;
    }

}
