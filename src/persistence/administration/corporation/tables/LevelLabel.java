/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables;

import communication.administration.corporation.tables.LabelItemAttribute;

/**
 * @author jrodriguez
 */
public class LevelLabel {

    private Integer id;
    private DivOrg divOrg;
    private String resourceKey;
    private Integer level;
    private Integer showLevel;
    
    public LevelLabel() {        
    }
    
    public LevelLabel(Integer id, DivOrg divOrg, String resourceKey, Integer level, Integer showLevel) {
        this.id = id;
        this.divOrg = divOrg;
        this.resourceKey = resourceKey;
        this.level = level;
        this.showLevel = showLevel;
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

    /**
     * @return
     */
    public LabelItemAttribute getAttributes() {
        return new LabelItemAttribute(id,resourceKey,showLevel);
    }
    
    public Integer getShowLevel() {
        return showLevel;
    }

    public void setShowLevel(Integer showLevel) {
        this.showLevel = showLevel;
    }
}
