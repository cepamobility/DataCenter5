package communication.vehicle.tables;

import java.io.Serializable;

public class DPAGoalData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private Integer year;
	private Integer month;
	private String regiao;
	private Integer value;
	
	public DPAGoalData(Integer id,Integer year,Integer month,String regiao,Integer value){
		this.id=id;
		this.year=year;
		this.regiao=regiao;
		this.month=month;
		this.value=value;
	}
	
	public DPAGoalData(){
		
	}
	
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
    
	
}
