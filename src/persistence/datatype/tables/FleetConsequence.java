/*
 * Created on 27-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.FleetConsequenceDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FleetConsequence extends GeneralDataType {
	
	/**
	 * 
	 */
	public FleetConsequence() {

		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public FleetConsequence(String resourceKey) {
		super(resourceKey);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		// TODO Auto-generated method stub
		return new FleetConsequenceDataType(resourceBundleId);
	}

}
