package persistence.report.tables;

import java.io.Serializable;

import communication.report.JJCrashSubtypesData;
import communication.report.JJIncidentByTypeData;

/**
 * @author fcarriquiry
 */
public class JJIncidentByType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private JJSnapshot snapshot;
	
	private String type;
	
	private Double percentage;
	
	private Integer count;
	
	private Integer cost;
	
	private Integer order;

	
	public JJIncidentByType() {
		
	}


	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public JJSnapshot getSnapshot() {
		return snapshot;
	}


	public void setSnapshot(JJSnapshot snapshot) {
		this.snapshot = snapshot;
	}

 	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Double getPercentage() {
		return percentage;
	}


	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public Integer getCost() {
		return cost;
	}


	public void setCost(Integer cost) {
		this.cost = cost;
	}


	public JJIncidentByTypeData getData() {
		JJIncidentByTypeData data=new JJIncidentByTypeData();
		data.setType(type);
		data.setCost(cost);
		data.setCount(count);
		data.setId(id);
		data.setOrder(order);
		data.setPercentage(percentage);
		
		return data;
	}

}
