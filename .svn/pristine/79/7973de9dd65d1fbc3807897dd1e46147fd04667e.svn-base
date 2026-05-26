/*
 * Created on 15-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.TypeDistanceUnitDataType;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TypeDistanceUnit extends GeneralDataType{
	public TypeDistanceUnit() {
		super();
	}


	public TypeDistanceUnit(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new TypeDistanceUnitDataType(resourceBundleId);
	}
	
	public boolean equals(Object o)
	{
		if (TypeDistanceUnit.class.isInstance(o))
		{
			return super.equals((TypeDistanceUnit)o);
		}else
			return false;
	}
	

}
