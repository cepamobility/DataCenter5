package communication.activity.tables;

import java.io.Serializable;
import java.util.Date;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.ActivityTypeDataType;

public class CEPAActivityData extends ActivityData implements Serializable {

    private static final long serialVersionUID = 1L;

	protected ActivityTypeDataType type;
	protected CEPAActivityDefinitionData definition;
	protected String observations;
	protected String theme;
	private Integer duration;
	private String file;

	
	public CEPAActivityData() {
		super();
	
	}
	
	public CEPAActivityData(Integer id) {
		super(id);
	}

	public CEPAActivityData(GeoLabelData countrygeo,CEPAActivityDefinitionData definition,
			String type,Integer activityId, CorporationData corporation,Date date,
			String city,Date dateExpiry,boolean isOld, int drivingExperience,
			String observations, Integer participants, String theme, Integer duration, String file) {
		super(countrygeo,activityId, corporation,date,city,
				dateExpiry,isOld,drivingExperience,participants);
		this.type = new ActivityTypeDataType(type);		
		this.definition=definition;
		this.observations = observations;
		this.theme = theme;
		this.duration = duration;
		this.file = file;
	}	
	
	public CEPAActivityDefinitionData getDefinition() {
		return definition;
	}

	/**
	 * @param definition The definition to set.
	 */
	public void setDefinition(CEPAActivityDefinitionData definition) {
		this.definition = definition;
	}

	/**
	 * @return Returns the type.
	 */
	public ActivityTypeDataType getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(ActivityTypeDataType type) {
		this.type = type;
	}

	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getFile() {
		return file;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
