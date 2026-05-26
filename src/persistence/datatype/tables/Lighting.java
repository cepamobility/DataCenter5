/*
 * Created on 27-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.LightingDataType;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Lighting extends GeneralDataType {

	/**
	 * 
	 */
	public Lighting() {

		// TODO Auto-generated constructor stub
	}	
	
	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		// TODO Auto-generated method stub
		return new LightingDataType(resourceBundleId);
	}

}
