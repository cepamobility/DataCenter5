/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ActivityTypeByCorporation {
	
	protected String resourceBundleId;
	
	protected Integer corporationId;
	
	protected Integer id;	
	

	public ActivityTypeByCorporation() {		
	}

	public ActivityTypeByCorporation(Integer id, String resourceBundleId,Integer corporationId) {
		this.id = id;		
		this.resourceBundleId = resourceBundleId;
		this.corporationId = corporationId;		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceBundleId() {
		return resourceBundleId;
	}

	/**
	 * @param resourceBundleId The resourceBundleId to set.
	 */
	public void setResourceBundleId(String resourceBundleId) {
		this.resourceBundleId = resourceBundleId;
	}


	public Integer getCorporationId() {
		return corporationId;
	}
	public void setCorporationId(Integer corporationId) {
		this.corporationId = corporationId;
	}
}
