/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.datatype.tables;

import communication.datatype.tables.EducationDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author jrodriguez
 */
public class Education extends GeneralDataType {

	public Education() {
		super();
	}


	public Education(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new EducationDataType(resourceBundleId);
	}
	
	

}
