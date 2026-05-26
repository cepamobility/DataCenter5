/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;

import communication.administration.corporation.tables.CorporationConfigData;

/**
 * @author fcarriquiry
 * 
 */
public class CorporationConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer corporationId;
	private String key;
	private String value;
	
	public CorporationConfig() {
	}

	public CorporationConfig(CorporationConfigData data) {
		this.id=data.getId();
		this.corporationId=data.getCorporationId();
		this.key=data.getKey();
		this.value=data.getValue();
	}

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

	public CorporationConfigData getData() {
		CorporationConfigData data=new CorporationConfigData();
		data.setId(id);
		data.setCorporationId(corporationId);
		data.setKey(key);
		data.setValue(value);
		
		return data;
	}

	
 

}