package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class GeneralDataType {
	
	protected String resourceBundleId;
	
	protected Integer oldId;
	
	public boolean equals(Object o)
	{
		if (GeneralDataType.class.isInstance(o))
		{
			GeneralDataType gdt = (GeneralDataType)o;
			if (gdt.resourceBundleId == this.resourceBundleId
					&& gdt.oldId == this.oldId)
				return true;
			else
				return false;
		}else
			return false;
	}
	
	public GeneralDataType() {		
	}

	public GeneralDataType(String id) {
		resourceBundleId = id;
	}
	
	
	
	public GeneralDataType(String resourceBundleId, Integer oldId) {
		super();
		this.resourceBundleId = resourceBundleId;
		this.oldId = oldId;
	}
	

	/**
	 * @return Returns the resourceBundleId.
	 */
	public String getResourceBundleId() {
		return resourceBundleId;
	}
	
	/**
	 * @param resourceBundleId The resourceBundleId to set.
	 */
	public void setResourceBundleId(String resourceBundleId) {
		this.resourceBundleId = resourceBundleId;
	}

	/**
	 * @return
	 */
	public abstract GeneralDTData getData();
    
	public Integer getOldId() {
		return oldId;
	}
	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}
}
