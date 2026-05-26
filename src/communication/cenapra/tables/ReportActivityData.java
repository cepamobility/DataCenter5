/**
 * Created on 21-ago-2008
 * CEPA
 * DataCenter 5
 */
package communication.cenapra.tables;

import java.io.Serializable;

import communication.activity.tables.CEPAActivityConstants;

public class ReportActivityData implements Comparable,Serializable  {
   
    private Integer stateId;
    private String stateName;    
    
    private Integer[] activities;
    private Integer[] participants;

    public ReportActivityData() {
    }

    public ReportActivityData(Integer stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.activities = new Integer[4];
        this.participants = new Integer[4];
        for(int i = 0; i < 4; i++) {
        	activities[i] = new Integer(0);
        	participants[i] = new Integer(0);
        }
    }

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer[] getActivities() {
		return activities;
	}

	public void setActivities(Integer[] activities) {
		this.activities = activities;
	}

	public Integer[] getParticipants() {
		return participants;
	}

	public void setParticipants(Integer[] participants) {
		this.participants = participants;
	}

	public void setActivities(String type,Integer count) {
		int index = CEPAActivityConstants.MULTIPLICADORES;
		if(type.equals(CEPAActivityConstants.CENAPRA_FORMADORES) || type.equals(CEPAActivityConstants.CENAPRA_FORMADORES_VIEJO)) {
			index = CEPAActivityConstants.FORMADORES;
		}
		if(type.equals(CEPAActivityConstants.CENAPRA_RELICADORES)) {
			index = 3;
		}
		if(type.equals(CEPAActivityConstants.CENAPRA_POBLACION_META) || type.equals(CEPAActivityConstants.CENAPRA_POBLACION_META_OLD)) {
			index = CEPAActivityConstants.POBLACION_META;
		}
		activities[index] += count;
	}

	public void setParticipants(String type,Integer count) {
		int index = CEPAActivityConstants.MULTIPLICADORES;
		if(type.equals(CEPAActivityConstants.CENAPRA_FORMADORES) || type.equals(CEPAActivityConstants.CENAPRA_FORMADORES_VIEJO)) {
			index = CEPAActivityConstants.FORMADORES;
		}
		if(type.equals(CEPAActivityConstants.CENAPRA_RELICADORES)) {
			index = 3;
		}
		if(type.equals(CEPAActivityConstants.CENAPRA_POBLACION_META) || type.equals(CEPAActivityConstants.CENAPRA_POBLACION_META_OLD)) {
			index = CEPAActivityConstants.POBLACION_META;
		}
		participants[index] += count;
	}

	public String getTypeName(int typeId) {
		switch(typeId) {
			case CEPAActivityConstants.MULTIPLICADORES:
				return CEPAActivityConstants.CENAPRA_MULTIPLICADORES;
			case CEPAActivityConstants.FORMADORES:
				return CEPAActivityConstants.CENAPRA_FORMADORES;
			case 3:
				return CEPAActivityConstants.CENAPRA_RELICADORES;
			case CEPAActivityConstants.POBLACION_META:
				return CEPAActivityConstants.CENAPRA_POBLACION_META;
		}
		return "";
	}

	public int compareTo(Object o) {
		return stateName.compareTo(((ReportActivityData)o).stateName);
	}
}
