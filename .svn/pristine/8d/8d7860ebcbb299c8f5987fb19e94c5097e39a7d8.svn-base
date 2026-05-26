/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.tables;

import java.io.Serializable;

import persistence.util.Util;



public class FleetDivisionDetailData implements Serializable {

    private FleetDivisionData fleetDivision;

    private Integer period;
    private Integer year;

    private Integer vehicles;
    private Integer miles;

    private Integer accidents;
    private Integer incidents;
	private Integer nearmiss;
    
    private Double APMM;

    private Float vehicleAVG;

    public FleetDivisionDetailData() {
    }

    public FleetDivisionDetailData(FleetDivisionData fleetDivision) {
    	this.fleetDivision = fleetDivision;
    	vehicles = 0;
    	miles = 0;
    	accidents = 0;
    	incidents = 0;
    	nearmiss = 0;
    }

    public Integer getAccidents() {
        return accidents;
    }

    public Double getAPMM() {
        return APMM;
    }

    public FleetDivisionData getFleetDivision() {
        return fleetDivision;
    }

    public Integer getIncidents() {
        return incidents;
    }

    public Integer getMiles() {
        return miles;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getVehicles() {
        return vehicles;
    }

    public Integer getYear() {
        return year;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
    }

    public void setAPMM(Double apmm) {
        APMM = apmm;
    }

    public void setFleetDivision(FleetDivisionData fleetDivision) {
        this.fleetDivision = fleetDivision;
    }

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setVehicleAVG(Float vehicleAVG) {
        this.vehicleAVG = vehicleAVG;
    }

    public Float getVehicleAVG() {
        return vehicleAVG;
    }

	public Integer getNearmiss() {
		return nearmiss;
	}

	public void setNearmiss(Integer nearmiss) {
		this.nearmiss = nearmiss;
	}

}
