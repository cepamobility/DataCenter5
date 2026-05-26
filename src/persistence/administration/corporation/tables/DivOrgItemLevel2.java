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
public class DivOrgItemLevel2 {

    private Integer id;
    private String resourceKey;
    private boolean active;
    private DivOrgItemLevel3 divOrgItemLevel3;
    
    public DivOrgItemLevel2() {        
    }
        
    /**
     * @param id
     * @param resourceKey
     * @param divOrgItemLevel3
     */
    public DivOrgItemLevel2(Integer id, String resourceKey,DivOrgItemLevel3 divOrgItemLevel3, boolean active) {
        this.id = id;
        this.resourceKey = resourceKey;
        this.divOrgItemLevel3 = divOrgItemLevel3;
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

    /**
     * @return Returns the parent.
     */
    public DivOrgItemLevel3 getDivOrgItemLevel3() {
        return divOrgItemLevel3;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setDivOrgItemLevel3(DivOrgItemLevel3 divOrgItemLevel3) {
        this.divOrgItemLevel3 = divOrgItemLevel3;
    }
    
    public ItemAttribute getAttributes() {
        return new ItemAttribute(id,resourceKey,active);
    }
    
    public boolean equals(Object obj) {
        DivOrgItemLevel2 object = (DivOrgItemLevel2)obj;
        if(object.getId() == null || id == null) {
            return object.getResourceKey().equals(resourceKey);
        }
        return (object.getId().intValue() == id.intValue());
    }
}
