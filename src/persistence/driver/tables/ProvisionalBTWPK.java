/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class ProvisionalBTWPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer driver;
    private Date expiry;

    public ProvisionalBTWPK() {
    }

    public ProvisionalBTWPK(Integer driverId, Date expiry) {
        driver = driverId;
        this.expiry = expiry;
    }

    public Date getDate() {
        return expiry;
    }

    public Integer getDriver() {
        return driver;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

	public Date getExpiry() {
		return expiry;
	}
}
