package communication.external.instructors;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PointRestData {
	
	private Integer id;
	
	private String name;
	
	private ArrayList<ChecklistObservationData> observations=new ArrayList<ChecklistObservationData>();
	 
	 
	private ArrayList<ItemRestData> items;
	
	private Integer order;
	

	public ArrayList<ChecklistObservationData> getObservations() {
		return observations;
	}

	public void setObservations(ArrayList<ChecklistObservationData> observations) {
		this.observations = observations;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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

	public ArrayList<ItemRestData> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemRestData> items) {
		this.items = items;
	}
	
	
}
