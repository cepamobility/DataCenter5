/**
 * Created on 12-ene-2007
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;



public class IndividualConsumedFuelData extends APMMReportData implements Serializable {

    private String vehicleType;
    private String fuel;
    private Integer liters;
    private Collection vehicleIds;
    private String fuelType;
    
    public IndividualConsumedFuelData() {
        super();
        vehicleIds = new LinkedList();
    }
    
    public IndividualConsumedFuelData(Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name, Integer miles, Integer liters, String fuel, String vehicleType, Integer vehicleId) {
        super(divOrg1Id,divOrg1Name,divOrg2Id,divOrg2Name,divOrg3Id,divOrg3Name,divOrg4Id,divOrg4Name,miles,new Integer(0),new Integer(0),new Integer(0));
        this.fuel = fuel;
        this.liters = liters;
        this.vehicleType = vehicleType;
        vehicleIds = new LinkedList();
        vehicleIds.add(vehicleId);
    }
    
    public String getFuel() {
        return fuel;
    }
    
    public Integer getLiters() {
        return liters;
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
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean equalsData(IndividualConsumedFuelData apmm) {
        if(compareTo(apmm) != 0) {
           return false; 
        }
        if(!apmm.getFuel().equals(fuel)) {
            return false;
        }
        if(!apmm.getVehicleType().equals(vehicleType)) {
            return false;
        }
        return true;
    }
    
    public void addData(IndividualConsumedFuelData apmm, Integer vehicleId) {
        if(!vehicleIds.contains(vehicleId)) {
            vehicleIds.add(vehicleId);
        }
        liters = new Integer(liters.intValue() + apmm.getLiters().intValue());
        miles = new Integer(miles.intValue() + apmm.getMiles().intValue());
    }

    public Collection getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(Collection vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public Integer getVehicleCount() {
        return new Integer(vehicleIds.size());
    }
}
