/*
 * Created on 15-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.TypeVehicleDataType;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TypeVehicle extends GeneralDataType{
	
	public static final String NO_VEHICLE = "datatype.vehicletype.novehicle";


	public TypeVehicle() {
		super();
	}


	public TypeVehicle(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new TypeVehicleDataType(resourceBundleId);
	}
	
	
	public boolean equals(Object o)
	{
		if (TypeVehicle.class.isInstance(o))
		{
			return super.equals((TypeVehicle) o);
		}
		else
			return false;
	}

}
