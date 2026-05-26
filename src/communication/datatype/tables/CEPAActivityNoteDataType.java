package communication.datatype.tables;

import java.io.Serializable;
public class CEPAActivityNoteDataType  extends GeneralDTData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String APRUVED = "generic.activity.note.apruved";
	public static final String NOT_APRUVED = "generic.activity.note.notapruved";
	public static final String APRUVED_WITH_OBSERVATIONS = "generic.activity.note.apruvedwithobservations";
	
	public CEPAActivityNoteDataType(String resourceBundleId) {
		super(resourceBundleId);		
	}

	 
}
