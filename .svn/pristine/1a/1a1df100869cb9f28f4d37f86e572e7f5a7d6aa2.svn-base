package persistence.datatype.tables;

import java.io.Serializable;

import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.datatype.tables.GeneralDTData;
/**
 * 
 * @author fcarriquiry
 *
 */

public class UnconformityTypeDPA extends GeneralDataType  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnconformityTypeDPA() {
		super();
	}


	public UnconformityTypeDPA(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new UnconformityTypeDPADataType(resourceBundleId);
	}
		
}
