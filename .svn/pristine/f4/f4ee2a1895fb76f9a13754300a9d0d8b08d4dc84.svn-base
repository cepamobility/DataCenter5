package persistence.datatype.tables;

import communication.datatype.tables.CsvEntityTypeDataType;
import communication.datatype.tables.GeneralDTData;


public class CsvEntityType extends GeneralDataType {

	public CsvEntityType() {
		super();
	}

 
	public CsvEntityType(String id) {
		super(id);
	}
	
	public CsvEntityType(CsvEntityTypeDataType data){
		super(data.getResourceBundleId());
	}
	
	
	/* (non-Javadoc)
	 * @see persistence.datatype.tables.GeneralDataType#getData()
	 */
	public GeneralDTData getData() {
		return new CsvEntityTypeDataType(resourceBundleId);
	}
	
	

}
