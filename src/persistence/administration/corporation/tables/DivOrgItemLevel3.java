/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables;

import communication.administration.corporation.tables.ItemAttribute;

/**
 * @author jrodriguez
 */
public class DivOrgItemLevel3 {

    private Integer id;
    private String resourceKey;
    private boolean active;
    
    public DivOrgItemLevel3() {        
    }
        
    /**
     * @param id
     * @param resourceKey
     */
    public DivOrgItemLevel3(Integer id, String resourceKey,boolean active) {
        super();
        this.id = id;
        this.resourceKey = resourceKey;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ItemAttribute getAttributes() {
        return new ItemAttribute(id,resourceKey,active);
    }

    public boolean equals(Object obj) {
        DivOrgItemLevel3 object = (DivOrgItemLevel3)obj;
        if(object.getId() == null || id == null) {
            return object.getResourceKey().equals(resourceKey);
        }
        return (object.getId().intValue() == id.intValue());
    }
}
