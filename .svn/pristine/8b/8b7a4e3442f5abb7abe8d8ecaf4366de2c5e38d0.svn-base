package persistence.report.tables;

import java.io.Serializable;

import communication.report.JJCrashSubtypesData;

/**
 * @author fcarriquiry
 */
public class JJCrashSubtypes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private JJSnapshot snapshot;
	
	private String collition;
	
	private String subtype;
	
	private Double percentage;
	
	private Integer count;
	
	private Integer cost;
	
	private Integer order;

	
	public JJCrashSubtypes() {
		
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

	
	public String getCollition() {
		return collition;
	}


	public void setCollition(String collition) {
		this.collition = collition;
	}


	public String getSubtype() {
		return subtype;
	}


	public void setSubtype(String subtype) {
		this.subtype = subtype;
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

	public JJCrashSubtypesData getData() {
		JJCrashSubtypesData data=new JJCrashSubtypesData();
		data.setCollition(collition);
		data.setCost(cost);
		data.setCount(count);
		data.setId(id);
		data.setOrder(order);
		data.setPercentage(percentage);
		data.setSubtype(subtype);
		
		return data;
	}
}
