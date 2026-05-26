package communication.administration.corporation.tables;

import java.io.Serializable;

public class CorporationConfigData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
    private Integer corporationId;
	private String key;
	private String value;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCorporationId() {
		return corporationId;
	}
	public void setCorporationId(Integer corporationId) {
		this.corporationId = corporationId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
