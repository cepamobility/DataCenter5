/*
 * Created on 17-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.datatype;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTypeAccess implements Serializable {
	
    private static final long serialVersionUID = 1L;

    /**
	 * This map content all data types
	 * Key: is a static value defined in DataTypeConstants
	 * Object: is a GeneralDTData
	 */
	private HashMap datatypes;
	
	
	public DataTypeAccess(HashMap datatypes) {
		this.datatypes = datatypes;
	}
	
	
	public void setDatatypes(HashMap datatypes) {
		this.datatypes = datatypes;
	}
	
	/**
	 * Id is a static value defined in DataTypeConstants
	 * @param id
	 * @return Collection of GeneralDTData
	 * 
	 */
	public Collection finAll(int id){
		return (Collection)datatypes.get(new Integer(id));
	}
	
}
