/*
 * Created on 09-feb-2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

/**
 * @author fperez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccidentClasificationByCorporation {
	
	protected String resourceBundleId;
	
	protected Integer corporationId;
	
	protected Integer id;	
    
    protected boolean active;
	
	public AccidentClasificationByCorporation() {		
	}

	
    public AccidentClasificationByCorporation(Integer id, String resourceBundleId,Integer corporationId) {
        this.id = id;       
        this.resourceBundleId = resourceBundleId;
        this.corporationId = corporationId;     
    }
    
    public AccidentClasificationByCorporation(Integer id, String resourceBundleId,Integer corporationId,boolean active) {
        this.id = id;       
        this.resourceBundleId = resourceBundleId;
        this.corporationId = corporationId;     
        this.active = active;
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


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

}
