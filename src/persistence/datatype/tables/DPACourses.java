package persistence.datatype.tables;

import communication.datatype.tables.GeneralDTData;
import communication.datatype.tables.EducationLevelDataType;

public class DPACourses extends GeneralDataType {
	
	public DPACourses() {	}
	
	public DPACourses(String resourceKy) {
		super(resourceKy);		
	}
	
	public GeneralDTData getData() {
		return new EducationLevelDataType(resourceBundleId);
	}

}

