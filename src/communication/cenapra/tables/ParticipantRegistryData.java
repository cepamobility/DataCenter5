/**
 * Created on 20-ago-2008
 * CEPA
 * DataCenter 5
 */
package communication.cenapra.tables;

import java.io.Serializable;


public class ParticipantRegistryData implements Serializable {

	public static final int REGULAR = 1;
	public static final int ACEPTABLE = 2;
	public static final int BUENO = 3;

	private Integer id;
    private ActivityData activityData;
    private ParticipantMainAttributes participantData;
    private String observations;
    private String number;
    private Integer note;
    private boolean hasResult;

    public ParticipantRegistryData() {
        super();
    }

    public ParticipantRegistryData(Integer id, ActivityData activityData, ParticipantMainAttributes participantData, String observations, boolean hasResult, Integer note, String number) {
        this.id = id;
        this.activityData = activityData;
        this.participantData = participantData;
        this.observations = observations;
        this.hasResult = hasResult;
        this.note = note;
        this.number = number;
    }

    public ActivityData getActivityData() {
        return activityData;
    }

    public Integer getId() {
        return id;
    }

    public String getObservations() {
        return observations;
    }

    public ParticipantMainAttributes getParticipantData() {
        return participantData;
    }

    public void setActivityData(ActivityData activityData) {
        this.activityData = activityData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setParticipantData(ParticipantMainAttributes participantData) {
        this.participantData = participantData;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

}
