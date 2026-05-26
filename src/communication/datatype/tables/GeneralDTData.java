/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.datatype.tables;

import java.io.Serializable;

/**
 * @author jrodriguez
 */
public class GeneralDTData implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;

    private String resourceBundleId;
    
    public GeneralDTData(){};
    
    /**
     * 
     */
    public boolean equals(Object o)
    {
    	if (GeneralDTData.class.isInstance(o))
    	{
    		GeneralDTData gdtd = (GeneralDTData)o;
    		if (gdtd.resourceBundleId.equals(this.resourceBundleId))
    		{
    			return true;
    		}
    		else
    			return false;
    	}else
    		return false;
    }
	
    /**
     * 
     * @param resourceBundleId
     */
	public GeneralDTData(String resourceBundleId) {
		this.resourceBundleId = resourceBundleId;
	}

	/**
	 * @return Returns the resourceBundleId.
	 */
	public String getResourceBundleId() {
		return resourceBundleId;
	}

	/**
	 * 
	 */
    public int compareTo(Object o) {
        return resourceBundleId.compareTo(((GeneralDTData)o).getResourceBundleId());
    }
}
