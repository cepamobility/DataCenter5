package communication.external.instructors;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class ItemRestData {

	private Integer id;
	
	private String name;
	private Integer type;
	protected Integer idRadio;
	private boolean checked;
	
	 
    /**
     * Lista de ids items separados por coma que son deshabilitados si se selecciona este item.
     */
    protected String disabledItems;
    private Integer order;
	
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

    
	public Integer getIdRadio() {
		return idRadio;
	}

	public void setIdRadio(Integer idRadio) {
		this.idRadio = idRadio;
	}

	public String getDisabledItems() {
		return disabledItems;
	}

	public void setDisabledItems(String disabledItems) {
		this.disabledItems = disabledItems;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
