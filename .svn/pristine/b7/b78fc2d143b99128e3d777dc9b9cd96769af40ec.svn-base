/**
 * 
 */
package persistence.activity.tables;

import java.io.Serializable;

import communication.activity.tables.ItemValueData;

/**
 * @author gbenaderet
 *
 */
public class ItemValue implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item item;
	private Integer itemValue;
	
	/**
	 */
	public ItemValue() {
		item = null;
		itemValue = null;
	}
	
	/**
	 * @param item
	 * @param value
	 */
	public ItemValue(Item item, Integer value) {		
		this.item = item;
		this.itemValue = value;
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
	public Integer getItemValue() {
		return itemValue;
	}
	/**
	 * @param value The value to set.
	 */
	public void setItemValue(Integer itemValue) {
		this.itemValue = itemValue;
	}

    public ItemValueData getItemValueData() {
        return new ItemValueData(item.getItemData(),itemValue);
    }
	
	
}
