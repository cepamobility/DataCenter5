package persistence.activity.tables;

import communication.activity.tables.CEPAScheduleActivityRegistryObservationData;
import communication.activity.tables.CEPAScheduleActivityRegistryObservationSelectedData;

public class CEPAScheduleActivityRegistryObservationSelected implements Comparable {

	private CEPAScheduleActivityRegistryObservation observation;

	/**
	 * @return the observation
	 */
	public CEPAScheduleActivityRegistryObservation getObservation() {
		return observation;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(CEPAScheduleActivityRegistryObservation observation) {
		this.observation = observation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((observation == null) ? 0 : observation.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CEPAScheduleActivityRegistryObservationSelected other = (CEPAScheduleActivityRegistryObservationSelected) obj;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		return true;
	}

	public CEPAScheduleActivityRegistryObservationSelected() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CEPAScheduleActivityRegistryObservationSelected(
			CEPAScheduleActivityRegistryObservation observation) {
		super();
		this.observation = observation;
	}
	
	public CEPAScheduleActivityRegistryObservationSelected(
			CEPAScheduleActivityRegistryObservationSelectedData d) {
		CEPAScheduleActivityRegistryObservationData obsData  = d.getObservation();
		this.observation = new CEPAScheduleActivityRegistryObservation(obsData);
	}
	
	public CEPAScheduleActivityRegistryObservationSelectedData getData(){
		CEPAScheduleActivityRegistryObservationSelectedData data = new 
			CEPAScheduleActivityRegistryObservationSelectedData(this.observation.getData());
		return data;
	}

	@Override
	public int compareTo(Object arg0) {
		return new Integer(observation.getObsOrder()).compareTo(new Integer(((CEPAScheduleActivityRegistryObservationSelected)arg0).getObservation().getObsOrder()));
	}
	
}
