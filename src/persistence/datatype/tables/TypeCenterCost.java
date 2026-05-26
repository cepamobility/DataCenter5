/*
 * Created on 15-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.TypeCenterCostDataType;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TypeCenterCost extends GeneralDataType{
	public TypeCenterCost() {
		super();
	}


	public TypeCenterCost(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new TypeCenterCostDataType(resourceBundleId);
	}
	

}
