/**
 * Created on 15-ago-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.tables;

import java.io.Serializable;

public class OdometerValue implements Serializable, Comparable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer period;
    private Integer year;
    private Integer odometer;

    public OdometerValue(Integer period, Integer year, Integer odometer) {
        this.period = period;
        this.year = year;
        this.odometer = odometer;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int compareTo(Object arg0) {
        OdometerValue value = (OdometerValue) arg0;
        if(value.getYear().equals(year)) {
            return value.getPeriod().compareTo(period);
        }else {
            return value.getYear().compareTo(year);
        }
    }

    
}
