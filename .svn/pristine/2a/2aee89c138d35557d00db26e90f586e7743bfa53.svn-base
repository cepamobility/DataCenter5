/**
 * Created on 14-nov-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author jrodriguez
 */
public class LabelItemData extends LabelItemAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private Collection items;

    public boolean equals(Object o)
    {
    	if (LabelItemData.class.isInstance(o))
    	{
    		LabelItemData lit = (LabelItemData)o;
    		if (this.items.equals(lit.items))
    			return true;
    		else
    			return false;
    	}
    	else
    		return false;
    }
    
    /*
     * Constructor por Defecto  
     */
    public LabelItemData() {
        super();
    }

    /**
     * @param id
     * @param name
     */
    public LabelItemData(Integer id, String name, Collection items, Integer showLevel) {
        super(id, name, showLevel);
        this.items = items;
    }
    
    public Collection getItems() {
        return items;
    }

    public Iterator getItemIterator() {
        Collections.sort((List)items);
        return items.iterator();
    }

    /**
     * @param data
     */
    public void addItem(ItemData data) {
        items.add(data);
    }

    public ItemData getItem(Integer id) {
        Iterator it = items.iterator();
        while(it.hasNext()) {
            ItemData item1 = (ItemData)it.next();
            if(item1.getId().equals(id)) {
                return item1;
            }
        }
        return null;
    }
}
