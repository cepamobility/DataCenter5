/**
 * Created on 09-mar-2007
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.util.Date;

import persistence.fleet.tables.ConsumedFuel;

public class ConsumedFuelLog {

    private ConsumedFuelLogPK pk;

    private int action;
    private Integer fleet;

    private Integer period;
    private Integer year;

    private Integer corporation;
    private Integer country;
    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;

    private String typeVehicle;
    private Integer vehicles;
    private Integer miles;

    private String fuel;                        
    private Integer liters;

    public ConsumedFuelLog() {
    }

    public ConsumedFuelLog(ConsumedFuel data, String userId, Date date, int action) {
        pk = new ConsumedFuelLogPK(data.getId(),userId,date);
        this.action = action;
        fleet = data.getFleet().getId();
        period = data.getPeriod();
        year = data.getYear();
        corporation = data.getCorporation().getId();
        country = data.getCountry().getId();
        if(data.getDivOrg1() != null) {
            divOrg1 = data.getDivOrg1().getId();
        }
        if(data.getDivOrg2() != null) {
            divOrg2 = data.getDivOrg2().getId();
        }
        if(data.getDivOrg3() != null) {
            divOrg3 = data.getDivOrg3().getId();
        }
        
        if(data.getTypeVehicle() != null) {
            typeVehicle = data.getTypeVehicle().getResourceBundleId();
        }
        vehicles = data.getVehicles();
        miles = data.getMiles();

        if(data.getFuel() != null) {
            fuel = data.getFuel().getResourceBundleId();
        }
        liters = data.getLiters();
        
    }

    public Integer getCorporation() {
        return corporation;
    }

    public Integer getCountry() {
        return country;
    }

    public Integer getDivOrg1() {
        return divOrg1;
    }

    public Integer getDivOrg2() {
        return divOrg2;
    }

    public Integer getDivOrg3() {
        return divOrg3;
    }

    public Integer getFleet() {
        return fleet;
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

    public Integer getPeriod() {
        return period;
    }

    public ConsumedFuelLogPK getPk() {
        return pk;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public Integer getVehicles() {
        return vehicles;
    }

    public Integer getYear() {
        return year;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public void setDivOrg1(Integer divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(Integer divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(Integer divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setFleet(Integer fleet) {
        this.fleet = fleet;
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

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setPk(ConsumedFuelLogPK pk) {
        this.pk = pk;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
