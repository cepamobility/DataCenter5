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
public class DivOrgItemLevel1 {

    private Integer id;
    private DivOrg divOrg;
    private String resourceKey;
    private boolean active;
    private DivOrgItemLevel2 divOrgItemLevel2;
    
       
    public DivOrgItemLevel1() {        
    }
        
    /**
     * @param id
     * @param resourceKey
     * @param divOrgItemLevel2
     */
    public DivOrgItemLevel1(Integer id, DivOrg divOrg, String resourceKey,DivOrgItemLevel2 divOrgItemLevel2,boolean active) {
        this.id = id;
        this.divOrg = divOrg;
        this.resourceKey = resourceKey;
        this.divOrgItemLevel2 = divOrgItemLevel2;
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
     * @return Returns the divOrg.
     */
    public DivOrg getDivOrg() {
        return divOrg;
    }
    
    /**
     * @param divOrg The divOrg to set.
     */
    public void setDivOrg(DivOrg divOrg) {
        this.divOrg = divOrg;
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
    public DivOrgItemLevel2 getDivOrgItemLevel2() {
        return divOrgItemLevel2;
    }
    
    /**
     * @param parent The parent to set.
     */
    public void setDivOrgItemLevel2(DivOrgItemLevel2 divOrgItemLevel2) {
        this.divOrgItemLevel2 = divOrgItemLevel2;
    }
    
    public ItemAttribute getAttributes() {
        return new ItemAttribute(id,resourceKey,active);
    }
    
    public boolean equals(Object obj) {
        DivOrgItemLevel1 object = (DivOrgItemLevel1)obj;
        if(object.getId() == null || id == null) {
            return object.getResourceKey().equals(resourceKey);
        }
        return (object.getId().intValue() == id.intValue());
    }
 }
