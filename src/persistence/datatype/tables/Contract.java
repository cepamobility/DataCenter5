package persistence.datatype.tables;

import communication.datatype.tables.ContractDataType;
import communication.datatype.tables.GeneralDTData;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Contract extends GeneralDataType {

	public Contract() {
		super();
	}


	public Contract(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new ContractDataType(resourceBundleId);
	}
	
	

}
