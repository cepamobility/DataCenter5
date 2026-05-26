/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class AlcoholControlPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Driver driver;
    private Date date;

    public AlcoholControlPK() {
    }

    public AlcoholControlPK(Date date, Driver driver) {
        this.date = date;
        this.driver = driver;
    }

    public AlcoholControlPK(Integer driverId, String date) {
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
}
