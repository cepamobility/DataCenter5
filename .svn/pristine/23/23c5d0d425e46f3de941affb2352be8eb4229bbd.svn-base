/**
 * Created on 12-ene-2007
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;



public class ReportConsumedFuelData extends APMMReportData implements Serializable {

    private String vehicleType;
    private String fuel;
    private Integer vehicles;
    private Integer miles;
    private Integer liters;
    private Collection vehicleIds;
    
    public ReportConsumedFuelData() {
        super();
        vehicleIds = new LinkedList();
    }
    
    public ReportConsumedFuelData(Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name, Integer miles, Integer accidents, Integer incidents) {
        super(divOrg1Id,divOrg1Name,divOrg2Id,divOrg2Name,divOrg3Id,divOrg3Name,divOrg4Id,divOrg4Name,miles,accidents,incidents,new Integer(0));
        vehicleIds = new LinkedList();
    }
    
    public String getFuel() {
        return fuel;
    }
    
    public Integer getLiters() {
        return liters;
    }
    
    public Integer getMiles() {
        return miles;
    }
    
    public Integer getVehicles() {
        return vehicles;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
    
    public void setLiters(Integer liters) {
        this.liters = liters;
    }
    
    public void setMiles(Integer miles) {
        this.miles = miles;
    }
    
    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean equalsData(ReportConsumedFuelData apmm) {
        if(compareTo(apmm) != 0) {
           return false; 
        }
        if(!apmm.getFuel().equals(fuel)) {
            return false;
        }
        return true;
    }
    
    public Collection getVehicleIds() {
        return vehicleIds;
    }

    public Integer getVehicleId() {
        return (Integer)vehicleIds.iterator().next();
    }

    public void setVehicleIds(Collection vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
    
    
}
