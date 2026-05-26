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
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SexDataType extends GeneralDTData implements Serializable {

    private static final long serialVersionUID = 1L;
	public static final String FEMALE = "datatype.sex.female";

	public SexDataType(){
		
	}
    /**
	 * @param resourceBundleId
	 */
	public SexDataType(String resourceBundleId) {
		super(resourceBundleId);
	}

}
