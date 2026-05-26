/**
 * Created on 12-dic-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;
import java.util.Collection;

import communication.util.LabelValueData;

/**
 * @author jrodriguez
 */
public class GeoLabelAttributes implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String resourceKey;
    private Integer level;
    
    public GeoLabelAttributes(){
    	
    }
    /**
     * @param id
     * @param resourceKey
     * @param level
     */
    public GeoLabelAttributes(Integer id, String resourceKey, Integer level) {
        super();
        this.id = id;
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
     * @return Returns the resourceKey.
     */
    public String getResourceKey() {
        return resourceKey;
    }

    /**
     * @return Returns the level.
     */
    public Integer getLevel() {
        return level;
    }

    public LabelValueData getData(Integer idContainer, Collection data) {
        return new GeoLabelData(id,resourceKey,level,data);
    }

}
