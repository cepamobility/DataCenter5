/**
 * Created on 14-nov-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;


/**
 * @author jrodriguez
 */
public class ItemData extends ItemAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private LabelItemData subItems;
    
    /**
     * 
     */
    public ItemData() {
        super();
    }
    
    /**
     * @param id
     * @param name
     */
    public ItemData(Integer id, String name, boolean active, LabelItemData subItems) {
        super(id, name, active);
        this.subItems = subItems;
    }
    
    /**
     * @param id
     * @param name
     */
    public ItemData(Integer id) {
        super(id);
    }    
    
    /**
     * @param attributes
     */
    public ItemData(ItemAttribute attributes) {
        super(attributes.getId(), attributes.getName(), attributes.isActive());
    }

    public LabelItemData getSubItems() {
        return subItems;
    }

    /**
     * 
     */
    public void modifyActive() {
        active = !active;
    }

    /**
     * @param data
     */
    public void setSubItems(LabelItemData data) {
        subItems = data; 
    }


    public void setId(Integer id){
    	this.id = id;
    }
}
