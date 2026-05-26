/**
 * 
 */
package communication.activity.tables;

import java.io.Serializable;

/**
 * @author gbenaderet
 *
 */
public class ItemValueData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemData item;
	private Integer itemValue;
	
	/**
	 */
	public ItemValueData() {
		item = null;
		itemValue = null;
	}
	
	/**
	 * @param item
	 * @param value
	 */
	public ItemValueData(ItemData item, Integer value) {		
		this.item = item;
		this.itemValue = value;
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
	public Integer getItemValue() {
		return itemValue;
	}
	/**
	 * @param value The value to set.
	 */
	public void setItemValue(Integer itemValue) {
		this.itemValue = itemValue;
	}
	
	
}
