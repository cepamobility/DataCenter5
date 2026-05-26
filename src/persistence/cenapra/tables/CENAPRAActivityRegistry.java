/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.tables;

import org.hibernate.classic.Session;

import communication.administration.user.UserSessionData;
import communication.cenapra.tables.ParticipantRegistryData;


public class CENAPRAActivityRegistry {

    private Integer id;

    private CENAPRAActivity activity;
    private CENAPRAParticipant participant;

    private String observations;

    private boolean result;
    
    private Integer note;
    private String number;
    
    public CENAPRAActivityRegistry() {
    }

    public CENAPRAActivityRegistry(Integer activityId, Integer participantId, String observations, Session session) {
        activity = (CENAPRAActivity)session.load(CENAPRAActivity.class,activityId);
        participant = (CENAPRAParticipant)session.load(CENAPRAParticipant.class,participantId);
        this.observations = observations;
    }

    public Integer getId() {
        return id;
    }

    public String getObservations() {
        return observations;
    }

    public CENAPRAParticipant getParticipant() {
        return participant;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setParticipant(CENAPRAParticipant participant) {
        this.participant = participant;
    }

    public CENAPRAActivity getActivity() {
        return activity;
    }

    public void setActivity(CENAPRAActivity activity) {
        this.activity = activity;
    }

    public ParticipantRegistryData getData(UserSessionData userSessionData) {
        return new ParticipantRegistryData(id,activity.getData(),participant.getMainAttributes(userSessionData),observations,result,note,number);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
