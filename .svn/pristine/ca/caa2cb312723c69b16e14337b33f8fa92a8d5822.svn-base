/**
 * Created on 03-feb-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.tables;

import java.io.Serializable;

import communication.administration.user.PermissionData;

/**
 * @author jrodriguez
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private String module;
    private Integer index;
    private Integer corporationId;
    
    /**
     * Default constructor 
     */
    public Permission() {
    }
    
    /**
     * Constructor with name of the permission
     */
    public Permission(String key) {
        this.key = key;
    }
        
    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    public Integer getIndex() {
        return index;
    }

    public String getModule() {
        return module;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public PermissionData getData() {
        return new PermissionData(key,module,index,corporationId);
    }

	/**
	 * @return Returns the corporationId.
	 */
	public Integer getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId The corporationId to set.
	 */
	public void setCorporationId(Integer corporationId) {
		this.corporationId = corporationId;
	}

    
}
