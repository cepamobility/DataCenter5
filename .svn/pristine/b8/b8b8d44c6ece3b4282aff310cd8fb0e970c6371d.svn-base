package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.TypeVolumeUnitDataType;
import communication.datatype.tables.UnconformityDataType;

public class UnconformityType {
	
	protected Integer id;	
	protected String resourceBundleId;
	protected Integer corporationId;
	
	public UnconformityType() {		
	}

	
    public UnconformityType(Integer id, String resourceBundleId,Integer corporationId) {
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

	public GeneralDTData getData() {
		return new UnconformityDataType(resourceBundleId);
	}
}
