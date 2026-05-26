package persistence.activity.tables;

import java.io.Serializable;

import org.hibernate.Session;

import communication.activity.tables.CEPAActivityFamilyClasificationData;
import communication.activity.tables.CEPAActivityFamilyData;

public class CEPAActivityFamily  implements Serializable{
	

	private Integer id;
	private String name;
	private CEPAActivityFamilyClasification clasification;	
		
	
	/**
	 * 
	 */
	public CEPAActivityFamily() {
		super();
	}



	/**
	 * 
	 * @param id
	 * @param name
	 * @param clasification
	 */
	public CEPAActivityFamily(Integer id, String name,
			CEPAActivityFamilyClasification clasification) {
		super();
		this.id = id;
		this.name = name;
		this.clasification = clasification;
	}

	/**
	 * 
	 * @param data
	 * @param session
	 */
	public CEPAActivityFamily(CEPAActivityFamilyData data, Session session) {
		super();
		this.id = data.getId();
		this.name = data.getName();		
		if (data.getClasificationData() != null){
			this.clasification = (CEPAActivityFamilyClasification)
				session.load(CEPAActivityFamilyClasification.class, data.getClasificationData().getId());
		}
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
	public CEPAActivityFamilyClasification getClasification() {
		return clasification;
	}



	/**
	 * @param clasification the clasification to set
	 */
	public void setClasification(CEPAActivityFamilyClasification clasification) {
		this.clasification = clasification;
	}
	
	
	public CEPAActivityFamilyData getFamilyData(){
		CEPAActivityFamilyClasificationData clasificationData = clasification.getClasificationData();
		return new CEPAActivityFamilyData(id,name,clasificationData);
		
	}
	
	

}
