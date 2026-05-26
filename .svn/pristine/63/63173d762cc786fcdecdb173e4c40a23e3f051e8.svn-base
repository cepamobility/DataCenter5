package communication.activity.tables;

import java.io.Serializable;

import communication.administration.user.tables.UserData;

public class CEPAScheduleActivityInstructorData implements Serializable, Comparable<CEPAScheduleActivityInstructorData>{

	private static final long serialVersionUID = 1L;
	private CEPAActivityData activity;
	private UserData user;
	
	
	public CEPAScheduleActivityInstructorData(CEPAActivityData activity,
			UserData user) {
		super();
		this.activity = activity;
		this.user = user;
	}


	public CEPAScheduleActivityInstructorData() {
		super();
	}


	/**
	 * @return the activity
	 */
	public CEPAActivityData getActivity() {
		return activity;
	}


	/**
	 * @param activity the activity to set
	 */
	public void setActivity(CEPAActivityData activity) {
		this.activity = activity;
	}


	/**
	 * @return the user
	 */
	public UserData getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(UserData user) {
		this.user = user;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		CEPAScheduleActivityInstructorData other = (CEPAScheduleActivityInstructorData) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	public int compareTo(CEPAScheduleActivityInstructorData arg0) {
		String fullName1 = this.getUser().getFirstName().concat(" ").concat(this.getUser().getLastName());
		String fullName2 = arg0.getUser().getFirstName().concat(" ").concat(arg0.getUser().getLastName());
		return fullName1.compareToIgnoreCase(fullName2);
	}
	
	
	
	
	
}