package persistence.activity.tables;

import java.io.Serializable;
import java.util.LinkedList;

import communication.activity.tables.ItemData;
import communication.datatype.tables.GeneralDTData;

public class Item implements Serializable, Comparable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Item Types:
     * Default null es check para los viejos formularios (previos al 2012)
     */
    public static final Integer CHECK = 1;
    public static final Integer RADIO = 2;
    public static final Integer TEXTO = 3;
    
    protected Integer id;
    protected String text;	
    protected String textReport;	
    protected int itemOrder = -1;
    protected Integer type;
    
    /**
     * si el type es Radio, podemos agregr un idRadio para
     * que se repita este idRdio en otro item que haga efecto radiobuttom con este
     */
    protected Integer idRadio; 
    /**
     * Lista de ids items separados por coma que son deshabilitados si se selecciona este item.
     */
    protected String disabledItems;
	
	/**
	 * @return Returns the textReport.
	 */
	public String getTextReport() {
		return textReport;
	}

	/**
	 * @param textReport The textReport to set.
	 */
	public void setTextReport(String textReport) {
		this.textReport = textReport;
	}

	public Item() {
		super();	
	}
	
	public Item(Object[] data) {
		this.id =(Integer) data[0];
		this.text=(String)data[1];
		this.textReport =(String)data[2];
		this.itemOrder =(Integer) data[3];
		this.type =(Integer) data[4];	
	}
	
	public Item(GeneralDTData data){
		text=data.getResourceBundleId();
	}

	public String getText() {
		return text;
	}

	public void setText(String resourceKeyText) {
		this.text = resourceKeyText;
	}
	
    public boolean equals(Object obj) {
    	
    	if(text!=null && obj!=null){
    		Item i=(Item)obj;
    		if(i.getText()!=null){
    			return i.getText().equals(this.getText());
    		}
    		else{
    			return false; 
    		}
    	}
    	return false;
    }

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}	
	
	public Item(Integer id) {
	    this.id=id;
	}
	
	public Item(Integer id,String text) {
	    this.id=id;
	    this.text = text;
	}	

	/**
	 * @return Returns the itemOrder.
	 */
	public int getItemOrder() {
		return itemOrder;
	}

	/**
	 * @param itemOrder The itemOrder to set.
	 */
	public void setItemOrder(int itemOrder) {
		this.itemOrder = itemOrder;
	}

    public ItemData getItemData() {
        return new ItemData(id,text,textReport,new Integer(itemOrder),type,idRadio,disabledItems);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }   

    public int compareTo(Object o) {
        return new Integer(itemOrder).compareTo(new Integer(((Item)o).itemOrder));
    }

	/**
	 * @return the idRadio
	 */
	public Integer getIdRadio() {
		return idRadio;
	}

	/**
	 * @param idRadio the idRadio to set
	 */
	public void setIdRadio(Integer idRadio) {
		this.idRadio = idRadio;
	}

	/**
	 * @return the disabledItems
	 */
	public String getDisabledItems() {
		return disabledItems;
	}

	/**
	 * @param disabledItems the disabledItems to set
	 */
	public void setDisabledItems(String disabledItems) {
		this.disabledItems = disabledItems;
	}
    
    

}
