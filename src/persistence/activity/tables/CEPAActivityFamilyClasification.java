package persistence.activity.tables;

import java.io.Serializable;

import communication.activity.tables.CEPAActivityFamilyClasificationData;

public class CEPAActivityFamilyClasification implements Serializable{
	
	
	private String name;
	private Integer id;
	
	
	/**
	 * 
	 */
	public CEPAActivityFamilyClasification() {
		super();		
	}
	
	/**
	 * 
	 * @param name
	 * @param id
	 */
	public CEPAActivityFamilyClasification(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public CEPAActivityFamilyClasificationData getClasificationData(){
		return new CEPAActivityFamilyClasificationData(name,id);
	}

	

}
