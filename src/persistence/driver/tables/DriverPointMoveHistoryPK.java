/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class DriverPointMoveHistoryPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Driver driver;
    private Integer period;
    private Integer year;
    private Integer move;

    public DriverPointMoveHistoryPK() {
    }

	public DriverPointMoveHistoryPK(Driver driver, Integer period, Integer year, Integer move) {
		super();
		this.driver = driver;
		this.period = period;
		this.year = year;
		this.move = move;
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

	public Integer getMove() {
		return move;
	}

	public void setMove(Integer move) {
		this.move = move;
	}

}
