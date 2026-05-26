/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables;

import java.util.Iterator;

import persistence.util.ItemValue;

import communication.administration.corporation.tables.GeoItemAttribute;
import communication.administration.corporation.tables.GeoItemData;
import communication.util.ItemValueData;


/**
 * @author jrodriguez
 */
public class GeoLevel2 implements ItemValue {

    private Integer id;
    private String resourceKey;
    
    public boolean equals(Object o)
    {
    	if (GeoLevel2.class.isInstance(o))
    	{
    		GeoLevel2 gl = (GeoLevel2)o;
    		if (this.id.equals(gl.id) && this.resourceKey.equals(gl.resourceKey))
    		{
    			return true;
    		}else
    			return false;
    	}else
    		return false;
    }
    public GeoLevel2() {        
    }
        
    /**
     * @param id
     * @param resourceKey
     * @param divOrgItemLevel2
     */
    public GeoLevel2(Integer id, String resourceKey) {
        this.id = id;
        this.resourceKey = resourceKey;
    }
    
    /**
     * @param dataLevel1
     */
    public GeoLevel2(GeoItemData data) {
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

    public boolean hasContainer() {
        return false;
    }

    public ItemValueData getData() {
        return new GeoItemData(id, resourceKey, true, null);
    }

    public Iterator getContainerIterator() {
        return null;
    }
    
    public GeoItemAttribute getItemAttribute() {
        return new GeoItemAttribute(id,resourceKey,true);
    }
    
}
