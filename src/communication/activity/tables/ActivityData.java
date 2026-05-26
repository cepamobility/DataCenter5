package communication.activity.tables;

import java.io.Serializable;
import java.util.Date;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoLabelData;

public class ActivityData extends ActivityPrincipalAttributes implements Serializable {
    
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Ctes para búsqueda
	public static final int DATE = 0;
	public static final int TYPE = 1;
	public static final int COUNTRY = 2;
	public static final int STATE = 3;

	public ActivityData() {
		// TODO Auto-generated constructor stub 
    	super();
	}
    
    public ActivityData(Integer id) {
		// TODO Auto-generated constructor stub
    	super(id);
	}
    
	public ActivityData(GeoLabelData countrygeo,Integer activityId, 
			CorporationData corpooration, Date date, String city,
			Date dateExpiry, boolean isOld, int drivingExperience,Integer participants) {
		super(countrygeo,activityId,corpooration,date,city,dateExpiry,isOld, drivingExperience,participants);
	}


}
