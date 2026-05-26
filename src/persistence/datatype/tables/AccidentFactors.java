/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.AccidentFactorsDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccidentFactors extends GeneralDataType {

	public AccidentFactors() {
		super();
	}


	public AccidentFactors(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new AccidentFactorsDataType(resourceBundleId);
	}
	
	

}
