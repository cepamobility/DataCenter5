/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.io.Serializable;
import java.util.Date;


public class FleetDivisionDetailLogPK implements Serializable {

	private Integer fleet;
    private Integer period;
    private Integer year;
    private String userId;
    private Date date;

    public FleetDivisionDetailLogPK() {
    }

    public FleetDivisionDetailLogPK(Integer fleet, Integer period, Integer year, String user, Date date) {
        this.fleet = fleet;
        this.period = period;
        this.year = year;
        this.userId = user;
        this.date = date;
    }

    public Integer getFleet() {
        return fleet;
    }

    
    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public void setFleet(Integer fleet) {
        this.fleet = fleet;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getDate() {
        return date;
    }

    public String getUserId() {
        return userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
