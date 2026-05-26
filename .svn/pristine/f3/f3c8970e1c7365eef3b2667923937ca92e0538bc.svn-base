/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;

import communication.administration.corporation.tables.PointsDefinitionData;

/**
 * @author fcarriquiry
 * 
 */
public class PointsDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Double amount;
	private String type;
	private Integer corporationId;
	private String reference;
	private String condition;
	private String description;
	private String description2;
	private Integer category;
	
	
	public PointsDefinition() {
	}

	public PointsDefinition(PointsDefinitionData data) {
		this.id=data.getId();
		this.corporationId=data.getCorporationId();
		this.type=data.getType();
		this.reference=data.getReference();
		this.description=data.getDescription();
		this.condition=data.getCondition();
		this.amount=data.getAmount();
		this.description2=data.getDescription2();
	}
	
	public void updateData(PointsDefinitionData data){
		setType(data.getType());
		setReference(data.getReference());
		setDescription(data.getDescription());
		setCondition(data.getCondition());
		setAmount(data.getAmount());
		if(this.amount.equals(5)){
			String s="";
		}
		
		setDescription2(data.getDescription2());
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

 
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PointsDefinitionData getData() {
		PointsDefinitionData data=new PointsDefinitionData();
		data.setId(id);
		data.setCorporationId(corporationId);
		data.setAmount(amount);
		data.setReference(reference);
		data.setCondition(condition);
		data.setType(type);
		data.setDescription(description);
		data.setDescription2(description2);
		data.setCategory(category);

		return data;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	
	

	
 

}