/**
 * 
 */
package persistence.activity.tables;

import java.io.Serializable;

import communication.activity.tables.ItemValueStrData;

/**
 * @author gbenaderet
 *
 */
public class ItemValueStr implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item item;
	private String itemValue;
	private String itemTag;
	
	/**
	 */
	public ItemValueStr() {
		item = null;
		itemValue = null;
		itemTag="0";
	}
	
	/**
	 * @param item
	 * @param value
	 */
	public ItemValueStr(Item item, String value) {		
		this.item = item;
		this.itemValue = value;
		itemTag="0";
	}
	
	public ItemValueStr(Item item, String value, String tag) {		
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
	public Item getItem() {
		return item;
	}
	/**
	 * @param item The item to set.
	 */
	public void setItem(Item item) {
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
	    		ItemValueStr i=(ItemValueStr)obj;
	    		if(i.getItem().getId()!=null){
	    			return (i.getItem().getId().intValue() == item.getId().intValue());
	    		}
	    		else{
	    			return false; 
	    		}
	    	}
	    	return false;
	    }

    public ItemValueStrData getData() {
        return new ItemValueStrData(item.getItemData(),itemValue,this.itemTag);
    }	
	
}
