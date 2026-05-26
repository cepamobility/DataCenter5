package persistence.activity.tables;

import java.io.Serializable;

import persistence.administration.user.tables.User;

public class CEPAScheduleActivityInstructorPK implements Serializable{
	
	private CEPAScheduleActivity activity;
	private User user;
	/**
	 * @return the activity
	 */
	public CEPAScheduleActivity getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(CEPAScheduleActivity activity) {
		this.activity = activity;
	}

	public CEPAScheduleActivityInstructorPK(CEPAScheduleActivity activity, User user) {
		super();
		this.activity = activity;
		this.user = user;
	}
	public CEPAScheduleActivityInstructorPK() {
		super();		
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
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
		CEPAScheduleActivityInstructorPK other = (CEPAScheduleActivityInstructorPK) obj;
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
	
	
	
	
	
	
}