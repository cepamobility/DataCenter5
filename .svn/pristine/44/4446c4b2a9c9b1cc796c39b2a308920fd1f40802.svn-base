/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.util.Date;

import communication.fleet.tables.FleetDivisionDetailData;


public class FleetDivisionDetailLog {

    private FleetDivisionDetailLogPK pk;

    private Integer vehicles;
    private Integer miles;
    
    private Integer accidents;
    private Integer incidents;
    
    private Double APMM;
    
    public FleetDivisionDetailLog() {
    }

    public FleetDivisionDetailLog(FleetDivisionDetailData detail, String user, Date date) {
        
        pk = new FleetDivisionDetailLogPK(detail.getFleetDivision().getId(),detail.getPeriod(),detail.getYear(),user,date);

        vehicles = detail.getVehicles();
        miles = detail.getMiles();
        
        accidents = detail.getAccidents();
        incidents = detail.getIncidents();
        
        APMM = detail.getAPMM();
    }

    public Integer getAccidents() {
        return accidents;
    }

    public Double getAPMM() {
        return APMM;
    }

    public Integer getIncidents() {
        return incidents;
    }

    public Integer getMiles() {
        return miles;
    }

    public FleetDivisionDetailLogPK getPk() {
        return pk;
    }

    public Integer getVehicles() {
        return vehicles;
    }

    public void setAccidents(Integer accidents) {
        this.accidents = accidents;
    }

    public void setAPMM(Double apmm) {
        APMM = apmm;
    }

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public void setPk(FleetDivisionDetailLogPK pk) {
        this.pk = pk;
    }

    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

}
