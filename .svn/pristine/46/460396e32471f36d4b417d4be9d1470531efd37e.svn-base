package persistence.report.tables;

import java.io.Serializable;

import communication.report.JJCrashByChampionData;

/**
 * @author fcarriquiry
 */
public class JJCrashByChampion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private JJSnapshot snapshot;
	
	private String operatingGroup;
	
	//Type
	private String linelabel;
	
	private String champion;
	
	private Double percentage;
	
	private Integer count;
	
	private Integer order;
	
	private Integer total;

	
	public JJCrashByChampion() {
		
	}



	public String getChampion() {
		return champion;
	}



	public void setChampion(String champion) {
		this.champion = champion;
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



	public String getOperatingGroup() {
		return operatingGroup;
	}



	public void setOperatingGroup(String operatingGroup) {
		this.operatingGroup = operatingGroup;
	}



	public String getLinelabel() {
		return linelabel;
	}



	public void setLinelabel(String linelabel) {
		this.linelabel = linelabel;
	}



	public Integer getTotal() {
		return total;
	}



	public void setTotal(Integer total) {
		this.total = total;
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



	public Integer getOrder() {
		return order;
	}



	public void setOrder(Integer order) {
		this.order = order;
	}

	public JJCrashByChampionData getData(){
		JJCrashByChampionData data=new JJCrashByChampionData();
		
		data.setId(id);

		data.setChampion(champion);
		
		data.setOperatingGroup(operatingGroup);
		
		data.setLinelabel(linelabel);
		
		data.setPercentage(percentage);
		
		data.setCount(count);
		
		data.setOrder(order);
		
		data.setTotal(total);
		
		return data;
	}
}
