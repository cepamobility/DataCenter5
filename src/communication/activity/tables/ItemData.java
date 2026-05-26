package communication.activity.tables;

import java.io.Serializable;

import communication.datatype.tables.GeneralDTData;

public class ItemData implements Serializable,Comparable {
    
    private static final long serialVersionUID = 1L;
    
    protected Integer id;
    protected String text;	
    protected String textReport;	
    protected Integer itemOrder;
    protected Integer type;
	
    /**
     * Item Types:
     * Default null es check para los viejos formularios (previos al 2012)
     */
    public static final Integer CHECK = 1;
    public static final Integer RADIO = 2;
    public static final Integer TEXTO = 3;
    
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
     * @param id
     * @param text
     * @param textReport
     * @param itemOrder
     */
    public ItemData(Integer id, String text, String textReport, Integer itemOrder) {
        super();
        this.id = id;
        this.text = text;
        this.textReport = textReport;
        this.itemOrder = itemOrder;
    }

    /**
     * @param id
     * @param text
     * @param textReport
     * @param itemOrder
     */
    public ItemData(Integer id, String text, String textReport, Integer itemOrder, Integer type,  
    		Integer idRadio, String disabledItems) {
        super();
        this.id = id;
        this.text = text;
        this.textReport = textReport;
        this.itemOrder = itemOrder;
        this.type = type;
        this.idRadio = idRadio;
        this.disabledItems = disabledItems;
    }
    
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

	public ItemData() {
		super();	
	}
	
	public ItemData(GeneralDTData data){
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
    		ItemData i=(ItemData)obj;
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
	
	public ItemData(Integer id) {
	    this.id=id;
	}
	
	public ItemData(Integer id,String text) {
	    this.id=id;
	    this.text = text;
	}	

	/**
	 * @return Returns the itemOrder.
	 */
	public Integer getItemOrder() {
		return itemOrder;
	}

	/**
	 * @param itemOrder The itemOrder to set.
	 */
	public void setItemOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

    public int compareTo(Object o) {
        return itemOrder.compareTo(((ItemData)o).itemOrder);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
