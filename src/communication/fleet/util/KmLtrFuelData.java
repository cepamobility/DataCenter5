/**
 * Created on 12-ene-2007
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;




public class KmLtrFuelData extends APMMReportData implements Serializable {

    private Integer vehicleId;
    private String vehicleType;
    private String plate;
    private String trade;
    private String model;
    private String fuel;
    private Integer liters;

    
    public KmLtrFuelData() {
    }
    
    public KmLtrFuelData(Integer vehicleId,Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id, String divOrg4Name, 
            Integer miles, Integer liters, String fuel, String vehicleType, String plate, String trade, String model) {
        super(divOrg1Id,divOrg1Name,divOrg2Id,divOrg2Name,divOrg3Id,divOrg3Name,divOrg4Id,divOrg4Name,miles,new Integer(0),new Integer(0),new Integer(0));
        this.vehicleId = vehicleId;
        this.fuel = fuel;
        this.liters = liters;
        this.vehicleType = vehicleType;
        this.plate = plate;
        this.trade = trade;
        this.model = model;
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

    public boolean equalsData(KmLtrFuelData data) {
        return (vehicleId.compareTo(data.vehicleId) == 0);
    }
    
    public void addData(KmLtrFuelData data) {
        liters = new Integer(liters.intValue() + data.getLiters().intValue());
        miles = new Integer(miles.intValue() + data.getMiles().intValue());
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public String getTrade() {
        return trade;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int compareTo(Object arg0) {
        KmLtrFuelData data = (KmLtrFuelData)arg0;
        Double prom1 = miles.doubleValue() / liters.doubleValue();
        Double prom2 = data.miles.doubleValue() / data.liters.doubleValue();
        return prom1.compareTo(prom2);
    }
}
