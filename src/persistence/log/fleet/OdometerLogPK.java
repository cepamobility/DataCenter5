/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.io.Serializable;
import java.util.Date;

import persistence.vehicle.tables.Vehicle;


public class OdometerLogPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fleet;
    private String userId;
    private Date date;
    private Integer vehicle;
    private Integer period;
    private Integer year;

    public OdometerLogPK() {
    }

    /**
     * @param fleet
     * @param period
     * @param year
     */
    public OdometerLogPK(Integer fleet, Integer vehicle, Integer period, Integer year,String userId,Date date) {
        this.fleet = fleet;
        this.vehicle = vehicle;
        this.period = period;
        this.year = year;
        this.userId = userId;
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

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
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
