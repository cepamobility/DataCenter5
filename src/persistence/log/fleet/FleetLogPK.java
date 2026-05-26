/**
 * 
 */
package persistence.log.fleet;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gbenaderet
 *
 */
public class FleetLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer fleet;
	private String userId;
	private Date date;

	public  FleetLogPK() {
	    
	}
	
    /**
     * @param id
     * @param user
     * @param date
     */
    public  FleetLogPK(Integer id, String userId, Date date) {
        this.fleet = id;
        this.userId = userId;
        this.date = date;
    }
    
    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @return Returns the id.
     */
    public Integer getFleet() {
        return fleet;
    }
    /**
     * @param id The id to set.
     */
    public void setFleet(Integer fleet) {
        this.fleet = fleet;
    }
    /**
     * @return Returns the user.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param user The user to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((fleet == null) ? 0 : fleet.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		FleetLogPK other = (FleetLogPK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (fleet == null) {
			if (other.fleet != null)
				return false;
		} else if (!fleet.equals(other.fleet))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
    
    

}
