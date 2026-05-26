package persistence.activity.tables;

import java.io.Serializable;

import org.hibernate.Session;

import communication.activity.tables.CEPAActivityDefinitionData;
import communication.activity.tables.CEPAActivityFamilyData;
import communication.activity.tables.CEPAActivitySubFamilyData;

public class CEPAActivitySubFamily implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer id;
	private CEPAActivityFamily family;
	private boolean isTheoretical;
	private boolean isPractical;	
	/**
	 * No todas las subfamilias tienen un curso en datacenter.
	 * Cuando exista el curso en datacenter, entonces la 
	 * subfamilia es ENABLED = TRUE, de lo contrario
	 * ENABLED = FALSE;
	 */
	private boolean enabled;
	
	


	public CEPAActivitySubFamily() {
		super();
	}

	/**
	 * 
	 * @param resourcebundleid
	 */
	public CEPAActivitySubFamily(Integer id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @param name
	 * @param id
	 * @param family
	 * @param isTheoretical
	 * @param isPractical
	 * @param enabled
	 */
	public CEPAActivitySubFamily(String name, Integer id,
			CEPAActivityFamily family, boolean isTheoretical,
			boolean isPractical, boolean enabled) {
		super();
		this.name = name;
		this.id = id;
		this.family = family;
		this.isTheoretical = isTheoretical;
		this.isPractical = isPractical;
		this.enabled = enabled;
	}


	/**
	 * 
	 * @param data
	 * @param session
	 */
	public CEPAActivitySubFamily(CEPAActivitySubFamilyData data, Session session) {
		super();
		this.name = data.getName();
		this.id = data.getId();
		if (data.getFamilyData() != null){
			this.family = (CEPAActivityFamily)
				session.load(CEPAActivityFamily.class, data.getFamilyData().getId());
		}
		this.isTheoretical = data.getIsTheoretical();
		this.isPractical = data.getIsPractical();
		this.enabled = data.getEnabled();
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
	 * @return the family
	 */
	public CEPAActivityFamily getFamily() {
		return family;
	}

	/**
	 * @param family the family to set
	 */
	public void setFamily(CEPAActivityFamily family) {
		this.family = family;
	}

	/**
	 * @param isTheoretical the isTheoretical to set
	 */
	public void setTheoretical(boolean isTheoretical) {
		this.isTheoretical = isTheoretical;
	}

	/**
	 * @param isPractical the isPractical to set
	 */
	public void setPractical(boolean isPractical) {
		this.isPractical = isPractical;
	}

	/**
	 * @return the isTheoretical
	 */
	public boolean getIsTheoretical() {
		return isTheoretical;
	}

	/**
	 * @param isTheoretical the isTheoretical to set
	 */
	public void setIsTheoretical(boolean isTheoretical) {
		this.isTheoretical = isTheoretical;
	}

	/**
	 * @return the isPractical
	 */
	public boolean getIsPractical() {
		return isPractical;
	}

	/**
	 * @param isPractical the isPractical to set
	 */
	public void setIsPractical(boolean isPractical) {
		this.isPractical = isPractical;
	}


	/**
	 * @return the enabled
	 */
	public boolean getEnabled() {
		return enabled;
	}


	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public CEPAActivitySubFamilyData getCEPAActivitySubFamilyData(){
		CEPAActivityFamilyData familyData = family.getFamilyData();
		return new CEPAActivitySubFamilyData(name, id,
					familyData, isTheoretical, isPractical, enabled);
	}
	
	

}
