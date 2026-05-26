package communication.activity.tables;

import java.io.Serializable;

public class CEPAScheduleActivityRegistryObservationSelectedData 
	implements Comparable<CEPAScheduleActivityRegistryObservationSelectedData>, 
	Serializable {

	private CEPAScheduleActivityRegistryObservationData observation;

	/**
	 * @return the observation
	 */
	public CEPAScheduleActivityRegistryObservationData getObservation() {
		return observation;
	}

	/**
	 * @param observation the observation to set
	 */
	public void setObservation(CEPAScheduleActivityRegistryObservationData observation) {
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
		CEPAScheduleActivityRegistryObservationSelectedData other =
			(CEPAScheduleActivityRegistryObservationSelectedData) obj;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		return true;
	}

	public CEPAScheduleActivityRegistryObservationSelectedData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CEPAScheduleActivityRegistryObservationSelectedData(
			CEPAScheduleActivityRegistryObservationData observation) {
		super();
		this.observation = observation;
	}

	/**
	 * 
	 * @param arg0
	 * @return
	 */
	public int compareTo(CEPAScheduleActivityRegistryObservationSelectedData arg0) {
		if (this.getObservation() != null && arg0 != null && arg0.getObservation() != null){
			Integer order = new Integer(this.getObservation().getObsOrder());
			Integer orderArg0 = new Integer(arg0.getObservation().getObsOrder());
			return order.compareTo(orderArg0);
		}		
		return 0;
	}
	
	
}
