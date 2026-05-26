/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class AptitudTestPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Driver driver;
    private Date date;

    public AptitudTestPK() {
    }

    public AptitudTestPK(Date date, Driver driver) {
        this.date = date;
        this.driver = driver;
    }

    public AptitudTestPK(Integer driverId, String date) {
        driver = new Driver();
        driver.setId(driverId);
        this.date = new Date(Long.parseLong(date));
    }

    public Date getDate() {
        return date;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
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
		AptitudTestPK other = (AptitudTestPK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		return true;
	}
    
    
}
