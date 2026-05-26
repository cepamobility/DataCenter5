/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables;

import java.util.Collection;

import persistence.util.LabelValue;

import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.util.LabelValueData;


/**
 * @author jrodriguez
 */
public class GeoLabel implements LabelValue {

    private Integer id;
    private Corporation corporation;
    private String resourceKey;
    private Integer level;
    
    public GeoLabel() {        
    }
    
    public GeoLabel(Integer id, Corporation corporation, String resourceKey, Integer level) {
        this.id = id;
        this.corporation = corporation;
        this.resourceKey = resourceKey;
        this.level = level;
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
    public Corporation getCorporation() {
        return corporation;
    }
    
    /**
     * @param divOrg The divOrg to set.
     */
    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
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
    public GeoLabelAttributes getAttributes() {
        return new GeoLabelAttributes(id,resourceKey,level);
    }

    public LabelValueData getData(Object idContainer, Collection data) {
        return new GeoLabelData(id,resourceKey,level,data);
    }

}
