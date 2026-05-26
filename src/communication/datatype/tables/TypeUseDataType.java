/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.datatype.tables;

import java.io.Serializable;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TypeUseDataType extends GeneralDTData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String INDIVIDUAL = "persistence.datatype.typeuse.individual";
    public static final String INSTITUTIONAL = "persistence.datatype.typeuse.institutional";

    public TypeUseDataType(){super("");}
    
	/**
	 * @param resourceBundleId
	 */
	public TypeUseDataType(String resourceBundleId) {
		super(resourceBundleId);
	}

}
