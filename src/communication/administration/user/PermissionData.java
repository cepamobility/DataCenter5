/**
 * Created on 11-dic-2006
 * CEPA
 * DataCenter 5
 */
package communication.administration.user;

import java.io.Serializable;

public class PermissionData implements Comparable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private String module;
    private Integer index;
    private Integer corporationId;
    
    public PermissionData() {
    }

    /**
     * @param name
     * @param module
     * @param index
     */
    public PermissionData(String name, String module, Integer index, Integer corporationId) {
        this.name = name;
        this.module = module;
        this.index = index;
        this.corporationId = corporationId;
    }
    
    public PermissionData(String name) {
        this.name = name;
	}

	public Integer getIndex() {
        return index;
    }

    public String getModule() {
        return module;
    }

    public String getName() {
        return name;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Object arg0) {
        PermissionData data = (PermissionData)arg0;
        if(module == null) {
            return 0;
        }
        if(module.equals(data.module)) {
            return index.compareTo(data.index);
        }else {
            return module.compareTo(data.module);
        }
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
