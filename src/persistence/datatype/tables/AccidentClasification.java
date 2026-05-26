/*
 * Created on 27-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccidentClasification extends GeneralDataType {
	
	public AccidentClasification() {
	}
	
	public AccidentClasification(String resourceKey) {
		super(resourceKey);
	}
	
	public GeneralDTData getData() {
		return new AccidentClasificationDataType(resourceBundleId);
	}

}
