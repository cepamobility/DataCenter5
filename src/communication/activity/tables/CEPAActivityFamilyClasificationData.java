package communication.activity.tables;

import java.io.Serializable;

public class CEPAActivityFamilyClasificationData implements Serializable{
	
	
	private String name;
	private Integer id;
	
	
	/**
	 * 
	 */
	public CEPAActivityFamilyClasificationData() {
		super();		
	}
	
	/**
	 * 
	 * @param name
	 * @param id
	 */
	public CEPAActivityFamilyClasificationData(String name, Integer id) {
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
	
	


	

}
