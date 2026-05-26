package persistence.activity.tables;

import java.io.Serializable;

import persistence.administration.user.tables.User;

import communication.activity.tables.CEPAScheduleActivityInstructorData;

public class CEPAScheduleActivityInstructor implements Serializable{
	
	private CEPAScheduleActivityInstructorPK pk;
	private boolean addedResults;
	
	/**
	 * 
	 * @return
	 */
	public CEPAScheduleActivityInstructorData getData(){
		CEPAScheduleActivityInstructorData data = null;
		
		if (pk != null && pk.getActivity() != null &&
				pk.getActivity().getCEPAActivityData() != null &&
				pk.getUser() != null && pk.getUser().getUserData(null) != null){
				
				data = new CEPAScheduleActivityInstructorData(
						pk.getActivity().getCEPAActivityData(), pk.getUser().getUserData(null));
		}
		return data;
	}
	
	/**
	 * 
	 * @param activity
	 * @param user
	 */
	public CEPAScheduleActivityInstructor(CEPAScheduleActivity activity, User user) {
		super();
		pk = new CEPAScheduleActivityInstructorPK();
		pk.setActivity(activity);
		pk.setUser(user);
	}
	
	
	

	/**
	 * @return the pk
	 */
	public CEPAScheduleActivityInstructorPK getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(CEPAScheduleActivityInstructorPK pk) {
		this.pk = pk;
	}

	public CEPAScheduleActivityInstructor(CEPAScheduleActivityInstructorPK pk) {
		super();
		this.pk = pk;
	}

	public CEPAScheduleActivityInstructor() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		CEPAScheduleActivityInstructor other = (CEPAScheduleActivityInstructor) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	/**
	 * @return the addedResults
	 */
	public boolean isAddedResults() {
		return addedResults;
	}

	/**
	 * @param addedResults the addedResults to set
	 */
	public void setAddedResults(boolean addedResults) {
		this.addedResults = addedResults;
	}
	
	
	
	
}