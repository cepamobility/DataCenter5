/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.io.Serializable;
import java.util.Date;

import persistence.fleet.tables.Odometer;


public class OdometerLog implements Serializable {

    private OdometerLogPK pk;
    private Integer action;
        
    private Integer odometer;

    private Integer accidents;
    private Integer incidents;

    private Integer fuel;
    private String fuelType;

    public OdometerLog() {
    }

    /**
     * @param action
     * @param odometer
     * @param accidents
     * @param incidents
     * @param fuel
     * @param fuelType
     */
    public OdometerLog(Integer fleet,Integer vehicle,Integer period,Integer year,String userId,Date date,Integer action, Integer odometer, Integer accidents, Integer incidents, Integer fuel, String fuelType) {
        pk = new OdometerLogPK(fleet,vehicle,period,year,userId,date);
        this.action = action;
        this.odometer = odometer;
        this.accidents = accidents;
        this.incidents = incidents;
        this.fuel = fuel;
        this.fuelType = fuelType;
    }

    public OdometerLog(Odometer odometer,String userId,Date date,Integer action) {
        pk = new OdometerLogPK(odometer.getPk().getFleet().getId(),odometer.getPk().getVehicle().getId(),odometer.getPk().getPeriod(),odometer.getPk().getYear(),userId,date);
        this.action = action;
        this.odometer = odometer.getOdometer();
        this.accidents = odometer.getAccidents();
        this.incidents = odometer.getIncidents();
        this.fuel = odometer.getFuel();
        this.fuelType = (odometer.getFuelType() == null) ? "" : odometer.getFuelType().getResourceBundleId();
    }

    public Integer getAccidents() {
        return accidents;
    }

    public Integer getAction() {
        return action;
    }

    public Integer getFuel() {
        return fuel;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Integer getIncidents() {
        return incidents;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public OdometerLogPK getPk() {
        return pk;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public void setPk(OdometerLogPK pk) {
        this.pk = pk;
    }                        

}
