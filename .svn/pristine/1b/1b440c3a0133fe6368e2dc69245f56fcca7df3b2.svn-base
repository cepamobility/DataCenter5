package communication.activity.tables;

import java.io.Serializable;

/*
 * Dado un activityregistry para una actividad/conductor
 * aca se almacena que observaciones se eligieron para
 * cada punto de la actividad.
 */
public class PointObservationSelectedData implements Serializable,
	Comparable<PointObservationSelectedData>{
	
	private PointData point;
	private PointObservationData observation;
	
	
	
	public PointObservationSelectedData() {
		super();
	}
	public PointObservationSelectedData(PointData point, PointObservationData observation) {
		super();
		this.point = point;
		this.observation = observation;
	}
	/**
	 * @return the point
	 */
	public PointData getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(PointData point) {
		this.point = point;
	}
	/**
	 * @return the observation
	 */
	public PointObservationData getObservation() {
		return observation;
	}
	/**
	 * @param observation the observation to set
	 */
	public void setObservation(PointObservationData observation) {
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
		result = prime * result + ((point == null) ? 0 : point.hashCode());
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
		PointObservationSelectedData other = (PointObservationSelectedData) obj;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
	
	
	public int compareTo(PointObservationSelectedData arg0) {
		return (new Integer (this.getPoint().getPointOrderReport())).compareTo(new Integer(arg0.getPoint().getPointOrderReport()));		
	}
	
	

}
