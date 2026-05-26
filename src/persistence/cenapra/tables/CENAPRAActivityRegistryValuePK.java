/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.tables;

import java.io.Serializable;

import org.hibernate.classic.Session;

import persistence.activity.tables.Item;


public class CENAPRAActivityRegistryValuePK implements Serializable {

    private CENAPRAActivityRegistry registry;
    private Item item;
    
    public CENAPRAActivityRegistryValuePK() {
    }

    public CENAPRAActivityRegistryValuePK(Integer registryId, Integer itemId, Session session) {
        registry = (CENAPRAActivityRegistry)session.load(CENAPRAActivityRegistry.class,registryId);
        item = (Item)session.load(Item.class,itemId);
    }

    public Item getItem() {
        return item;
    }

    public CENAPRAActivityRegistry getRegistry() {
        return registry;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setRegistry(CENAPRAActivityRegistry registry) {
        this.registry = registry;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((registry == null) ? 0 : registry.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CENAPRAActivityRegistryValuePK other = (CENAPRAActivityRegistryValuePK) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (registry == null) {
			if (other.registry != null)
				return false;
		} else if (!registry.equals(other.registry))
			return false;
		return true;
	}
    
    

}
