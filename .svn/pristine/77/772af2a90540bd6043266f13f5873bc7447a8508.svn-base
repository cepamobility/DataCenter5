/**
 * 
 */
package communication.activity.tables;

import java.io.Serializable;

/**
 * @author gbenaderet
 *
 */
public class ItemValueStrData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemData item;
	private String itemValue;
	private String itemTag;
	
	/**
	 */
	public ItemValueStrData() {
		item = null;
		itemValue = null;
	}
	
	/**
	 * @param item
	 * @param value
	 */
	public ItemValueStrData(ItemData item, String value) {		
		this.item = item;
		this.itemValue = value;
		this.itemTag="0";
	} 
	
	/**
	 * @param item
	 * @param value
	 */
	public ItemValueStrData(ItemData item, String value,String tag) {		
		this.item = item;
		this.itemValue = value;
		this.itemTag=tag;
	} 
	
	
	public String getItemTag() {
		return itemTag;
	}

	public void setItemTag(String itemTag) {
		this.itemTag = itemTag;
	}

	/**
	 * @return Returns the item.
	 */
	public ItemData getItem() {
		return item;
	}
	/**
	 * @param item The item to set.
	 */
	public void setItem(ItemData item) {
		this.item = item;
	}
	/**
	 * @return Returns the value.
	 */
	public String getItemValue() {
		return itemValue;
	}
	/**
	 * @param value The value to set.
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	
	   public boolean equals(Object obj) {
	    	
	    	if(item.getId()!=null && obj!=null){
	    		ItemValueStrData i=(ItemValueStrData)obj;
	    		if(i.getItem().getId()!=null){
	    			return (i.getItem().getId().intValue() == item.getId().intValue());
	    		}
	    		else{
	    			return false; 
	    		}
	    	}
	    	return false;
	    }	
	
}
