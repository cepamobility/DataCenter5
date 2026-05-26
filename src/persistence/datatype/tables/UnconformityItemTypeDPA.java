package persistence.datatype.tables;

import java.io.Serializable;

import communication.administration.dpa.UnconformityItemTypeDPADataType;
import communication.datatype.tables.GeneralDTData;

/**
 * 
 * @author fcarriquiry
 *
 */
public class UnconformityItemTypeDPA extends GeneralDataType  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnconformityItemTypeDPA() {
		super();
	}


	public UnconformityItemTypeDPA(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new UnconformityItemTypeDPADataType(resourceBundleId);
	}
		
}
