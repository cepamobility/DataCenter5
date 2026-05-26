/**
 * Created on 09-mar-2007
 * CEPA
 * DataCenter 5
 */
package communication.fleet.tables;

import java.io.Serializable;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.datatype.tables.TypeFuelDataType;
import communication.datatype.tables.TypeVehicleDataType;

public class ConsumedFuelData implements Serializable {

    private Integer id;

    private Integer period;
    private Integer year;

    private ItemData divOrg1;
    private ItemData divOrg2;
    private ItemData divOrg3;
    
    private GeoItemData country;

    private TypeVehicleDataType typeVehicle;
    private Integer vehicles;
    private Integer miles;

    private TypeFuelDataType fuel;                        
    private Integer liters;

    private String[][] data;

    public ConsumedFuelData() {
    }

    public GeoItemData getCountry() {
        return country;
    }

    public ItemData getDivOrg1() {
        return divOrg1;
    }

    public ItemData getDivOrg2() {
        return divOrg2;
    }

    public ItemData getDivOrg3() {
        return divOrg3;
    }

    public TypeFuelDataType getFuel() {
        return fuel;
    }

    public Integer getId() {
        return id;
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

    public TypeVehicleDataType getTypeVehicle() {
        return typeVehicle;
    }

    public Integer getVehicles() {
        return vehicles;
    }

    public Integer getYear() {
        return year;
    }

    public void setCountry(GeoItemData country) {
        this.country = country;
    }

    public void setDivOrg1(ItemData divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(ItemData divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(ItemData divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setFuel(TypeFuelDataType fuel) {
        this.fuel = fuel;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setTypeVehicle(TypeVehicleDataType typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public void setVehicles(Integer vehicles) {
        this.vehicles = vehicles;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public String[][] getData() {
        return data;
    }

    
}
