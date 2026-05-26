/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import persistence.util.ItemValue;

import communication.administration.corporation.tables.GeoItemAttribute;
import communication.administration.corporation.tables.GeoItemData;
import communication.util.ItemValueData;


/**
 * @author jrodriguez
 */
public class GeoLevel1 implements ItemValue {

    private Integer id;
    private String resourceKey;
    private GeoLevel2 geoLevel2;
    
    
    public boolean equals(Object o)
    {
    	if (GeoLevel1.class.isInstance(o))
    	{
    		GeoLevel1 gl = (GeoLevel1)o;
    		if (id.equals(gl.id) && resourceKey.equals(gl.resourceKey) 
    				&& geoLevel2.equals(gl.geoLevel2))
    		{
    			return true;
    		}
    		else
    			return false;
    	}else
    		return false;
    }
    public GeoLevel1() {        
    }
        
    /**
     * @param id
     * @param resourceKey
     * @param divOrgItemLevel2
     */
    public GeoLevel1(Integer id, String resourceKey,GeoLevel2 geoLevel2) {
        this.id = id;
        this.resourceKey = resourceKey;
        this.geoLevel2 = geoLevel2;
    }
    
    /**
     * @param dataLevel1
     */
    public GeoLevel1(GeoItemData data) {
        this.id = data.getId();
        this.resourceKey = data.getName();
    }

    /**
     * @return Returns the id.
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id The id to set.
     */
    public void setId(Integer id) {
        this.id = id;
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
    public GeoLevel2 getGeoLevel2() {
        return geoLevel2;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setGeoLevel2(GeoLevel2 geoLevel2) {
        this.geoLevel2 = geoLevel2;
    }

    public boolean hasContainer() {
        return geoLevel2 != null;
    }

    public ItemValueData getData() {
        return new GeoItemData(id,resourceKey,true,null);
    }

    public Iterator getContainerIterator() {
        Collection list = new LinkedList();
        list.add(geoLevel2);
        return list.iterator();
    }

    public GeoItemAttribute getItemAttribute() {
        return new GeoItemAttribute(id,resourceKey,true);
    }
    
}
