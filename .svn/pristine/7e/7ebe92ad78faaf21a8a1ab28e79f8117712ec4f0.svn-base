/**
 * Created on 12-dic-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.util.LabelValueData;

/**
 * @author jrodriguez
 */
public class GeoLabelData extends GeoLabelAttributes implements LabelValueData, Serializable {

    private static final long serialVersionUID = 1L;

    private Collection items;
    
    public boolean equals(Object o)
    {
    	if (GeoLabelData.class.isInstance(o))
    	{
    		GeoLabelData gld = (GeoLabelData)o;
    		if (gld.getId().equals(this.getId()))
    		{
    			return true;
			}
    		else
    			return false;
    		
    	}else
    		return false;
    }
    
    /**
     * @param id
     * @param resourceKey
     * @param level
     */
    public GeoLabelData(Integer id, String resourceKey, Integer level,Collection items) {
        super(id, resourceKey, level);
        this.items = items;
    }
    
    public GeoLabelData(){
    	
    }
    
    public Collection getItems() {
        return items;
    }

    public Iterator getItemIterator() {
        if(items != null) {
            Collections.sort((List)items);
            return items.iterator();
        }
        return new LinkedList().iterator();
    }

    /**
     * @return
     */
    public boolean isEmpty() {
        return items.size() == 0;
    }
    
    public GeoItemData getItem(Integer id) {
        Iterator it = items.iterator();
        while(it.hasNext()) {
            GeoItemData item1 = (GeoItemData)it.next();
            if(item1.getId().equals(id)) {
                return item1;
            }
        }
        return null;
    }
    
    
    /*
     * Devuelve true si la Coleccion de items
     * tiene el item cuyo id es el que se pasa
     * por parametro.
     */
    public boolean hasItem(Integer id)
    {
    	Iterator it = items.iterator();
    	while (it.hasNext())
    	{
    		GeoItemData item1 = (GeoItemData)it.next();
    		if (item1.getId().equals(id)){
    			return true;
    		}
    	}
    	return false;
    }
}
