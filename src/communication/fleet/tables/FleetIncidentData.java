/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.tables;

import java.io.Serializable;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.datatype.tables.IncidentClasificationDataType;

public class FleetIncidentData implements Serializable {

    private Integer id;
    
    private FleetData fleet;

    private Integer period;
    private Integer year;

    private ItemData divOrg1;
    private ItemData divOrg2;
    private ItemData divOrg3;
    private GeoItemData country;
    private String[][] data;
    
    private IncidentClasificationDataType clasification;

    private Integer number;
    private Integer cost;

    public FleetIncidentData() {
        super();
    }

    public IncidentClasificationDataType getClasification() {
        return clasification;
    }

    public Integer getCost() {
        return cost;
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

    public FleetData getFleet() {
        return fleet;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public void setClasification(IncidentClasificationDataType clasification) {
        this.clasification = clasification;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
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

    public void setFleet(FleetData fleet) {
        this.fleet = fleet;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
