/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;


import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;

import persistence.util.ItemValue;

import communication.administration.corporation.tables.GeoItemAttribute;
import communication.administration.corporation.tables.GeoItemData;
import communication.util.ItemValueData;


/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorporationCountry implements Serializable, ItemValue {
	
    private static final long serialVersionUID = 1L;

    private CorporationCountryPK pk; 
	private boolean active;
	private Set geoLevel1Set;
	
	public CorporationCountry(){}
	
	
	public CorporationCountry(Country country, Corporation corporation, boolean active, Set geoLevel1)
	throws HibernateException{
		this.pk=new CorporationCountryPK(country,corporation);
		this.active=active;
		this.geoLevel1Set=geoLevel1;
	}
	
    /**
     * @param id
     * @param id2
     */
    public CorporationCountry(Integer corporationId, Integer countryId, boolean active, Set geoLevel1) {
		Country country = new Country();
		country.setId(countryId);
		Corporation corporation = new Corporation();
		corporation.setId(corporationId);
        this.pk=new CorporationCountryPK(country,corporation);
		this.active=active;
		this.geoLevel1Set=geoLevel1;
    }


    /**
     * @return Returns the pk.
     */
    public CorporationCountryPK getPk() {
        return pk;
    }
    
    /**
     * @param pk The pk to set.
     */
    public void setPk(CorporationCountryPK pk) {
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


    /**
     * @return
     */
    public GeoItemAttribute getItemAttribute() {
        return new GeoItemAttribute(pk.getCountry().getId(),pk.getCountry().getResourceKey(),active);
    }


    public boolean hasContainer() {
        return !((geoLevel1Set == null) || (geoLevel1Set.size() == 0));
    }


    public ItemValueData getData() {
        return new GeoItemData(pk.getCountry().getId(),pk.getCountry().getResourceKey(),active,null);
    }

    public Integer getId() {
        return pk.getCountry().getId();
    }


    public Iterator getContainerIterator() {
        return geoLevel1Set.iterator();
    }


}
