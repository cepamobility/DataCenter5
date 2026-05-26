package communication.activity.tables;

import java.io.Serializable;

import persistence.activity.tables.CEPAActivityFamily;

public class CEPAActivitySubFamilyData implements Serializable {

private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer id;
	private CEPAActivityFamilyData familyData;
	private boolean isTheoretical;
	private boolean isPractical;	
	/**
	 * No todas las subfamilias tienen un curso en datacenter.
	 * Cuando exista el curso en datacenter, entonces la 
	 * subfamilia es ENABLED = TRUE, de lo contrario
	 * ENABLED = FALSE;
	 */
	private boolean enabled;
	
	


	/**
	 * 
	 * @param resourcebundleid
	 */
	public CEPAActivitySubFamilyData(Integer id) {
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
	public CEPAActivitySubFamilyData(String name, Integer id,
			CEPAActivityFamilyData familyData, boolean isTheoretical,
			boolean isPractical, boolean enabled) {
		super();
		this.name = name;
		this.id = id;
		this.familyData = familyData;
		this.isTheoretical = isTheoretical;
		this.isPractical = isPractical;
		this.enabled = enabled;
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
	public CEPAActivityFamilyData getFamilyData() {
		return familyData;
	}

	/**
	 * @param family the family to set
	 */
	public void setFamilyData(CEPAActivityFamilyData familyData) {
		this.familyData = familyData;
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
	
	

	

}
