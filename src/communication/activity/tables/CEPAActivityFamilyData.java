package communication.activity.tables;

import java.io.Serializable;

import persistence.activity.tables.CEPAActivityFamilyClasification;

public class CEPAActivityFamilyData implements Serializable{
	

	private Integer id;
	private String name;
	private CEPAActivityFamilyClasificationData clasificationData;	
		
	
	/**
	 * 
	 */
	public CEPAActivityFamilyData() {
		super();
	}



	/**
	 * 
	 * @param id
	 * @param name
	 * @param clasification
	 */
	public CEPAActivityFamilyData(Integer id, String name,
			CEPAActivityFamilyClasificationData clasificationData) {
		super();
		this.id = id;
		this.name = name;
		this.clasificationData = clasificationData;
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
	 * @return the clasification
	 */
	public CEPAActivityFamilyClasificationData getClasificationData() {
		return clasificationData;
	}



	/**
	 * @param clasification the clasification to set
	 */
	public void setClasificationData(CEPAActivityFamilyClasificationData clasificationData) {
		this.clasificationData = clasificationData;
	}
	
	
	
	
	

}
