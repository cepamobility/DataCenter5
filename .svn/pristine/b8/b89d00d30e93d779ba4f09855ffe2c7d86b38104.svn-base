/**
 * Created on 12-dic-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;

import communication.util.ItemValueData;
import communication.util.LabelValueData;


/**
 * @author jrodriguez
 */
public class GeoItemData extends GeoItemAttribute implements ItemValueData, Serializable  {

    private static final long serialVersionUID = 1L;

    private GeoLabelData subItems;
    
    /**
     * 
     */
    public GeoItemData() {
        super();
    }

    /**
     * @param id
     * @param name
     * @param active
     * @param data
     * @param units 
     */
    public GeoItemData(Integer id, String name, boolean active, GeoLabelData data) {
        super(id, name, active);
        subItems = data;
    }
    
    public GeoItemData(Integer id) {
        this.id = id;
    }

    public GeoLabelData getSubItems() {
        return subItems;
    }

    public void setComponents(LabelValueData component) {
        subItems = (GeoLabelData) component;
    }



    
}
