package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.EducationLevelDataType;

public class EducationLevel extends GeneralDataType {
	
	public EducationLevel() {	}
	
	public EducationLevel(String resourceKy) {
		super(resourceKy);		
	}
	
	public GeneralDTData getData() {
		return new EducationLevelDataType(resourceBundleId);
	}

}

