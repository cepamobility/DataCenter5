/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class DriverPointHistoryPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Driver driver;
    private Integer period;
    private Integer year;

    public DriverPointHistoryPK() {
    }

	public DriverPointHistoryPK(Driver driver, Integer period, Integer year) {
		super();
		this.driver = driver;
		this.period = period;
		this.year = year;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
