/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;


public class APMMDataPeriod implements Comparable , Serializable  {

    private Integer period;
    private Integer year;

    private Integer miles;
    private Integer accidents;
    
    public APMMDataPeriod() {
        period = new Integer(0);
        year = new Integer(0);

        miles = new Integer(0);
        accidents = new Integer(0);
    }
    
    public APMMDataPeriod(Object[] data) {
        miles = new Integer (( (Long) data[0]).intValue());
        accidents = new Integer (( (Long) data[1]).intValue());
        period = (Integer) data[2];
        year = (Integer) data[3];
    }
    
    

    /**
     * @param period
     * @param year
     * @param miles
     * @param accidents
     */
    public APMMDataPeriod(Integer period, Integer year, Integer miles, Integer accidents) {
        this.period = period;
        this.year = year;
        this.miles = miles;
        this.accidents = accidents;
    }

    public Integer getAccidents() {
        return accidents;
    }

    public Integer getMiles() {
        return miles;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int compareTo(Object arg0) {
        APMMDataPeriod data = (APMMDataPeriod)arg0; 
        if(!year.equals(data.year)) {
            return year.compareTo(data.year);
        }
        if(!period.equals(data.period)) {
            return year.compareTo(data.period);
        }
        return 0;
    }

    public void addData(APMMDataPeriod apmm) {
        accidents = new Integer(accidents.intValue() + apmm.accidents.intValue());
        miles = new Integer(miles.intValue() + apmm.miles.intValue());
    }

}
