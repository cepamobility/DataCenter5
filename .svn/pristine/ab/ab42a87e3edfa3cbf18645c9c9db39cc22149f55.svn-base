/**
 * Created on 12-ene-2007
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import communication.fleet.tables.FleetData;


public class APMMByDivisionData extends APMMReportData implements Serializable {

    private Float vehicles; // cantidad de vehiculos de la flota en esa division
    private float months; 
    private float totalVehiclesByQuarter;
    private float totalVehiclesByMonth;
    private Collection monthKeysByMonth;
    private Collection monthKeysByQuarter;
    private Integer injuries;
    private Collection monthWithData;
    
    public APMMByDivisionData() {
        super();
        monthKeysByMonth = new LinkedList();
        monthKeysByQuarter = new LinkedList();
        monthWithData = new LinkedList();
    }

    public APMMByDivisionData(Integer divOrg1Id, String divOrg1Name, Integer divOrg2Id, 
    		String divOrg2Name, Integer divOrg3Id, String divOrg3Name, Integer divOrg4Id,
    		String divOrg4Name, Integer miles, Integer accidents, Integer incidents,Integer nearmiss,int months) {
        super(divOrg1Id, divOrg1Name, divOrg2Id, divOrg2Name, divOrg3Id, 
        		divOrg3Name, divOrg4Id, divOrg4Name, miles, accidents, incidents, nearmiss);
        totalVehiclesByQuarter = 0;
        totalVehiclesByMonth = 0;
        this.months = months;
        monthKeysByMonth = new LinkedList();
        monthKeysByQuarter = new LinkedList();
        monthWithData = new LinkedList();
    }

    public Float getVehicles() {
        if(vehicles != null && monthWithData.size() > 0) {
            return new Float(vehicles / monthWithData.size());
        }else {
            if(monthKeysByQuarter.size() > 0) {
                return new Float(totalVehiclesByQuarter / monthKeysByQuarter.size());
            }
            if(monthKeysByMonth.size() > 0) {
                return new Float(totalVehiclesByMonth / monthKeysByMonth.size());
            }
            return new Float(totalVehiclesByMonth / months); 
        }
    }

    public void setVehicles(Float vehicles) {
        this.vehicles = vehicles;
    }

    public String getCountry() {
        return null;
    }
    
    public void setInjuries(Integer injuries){
    	this.injuries=injuries;
    }
    
    public Integer getInjuries(){
    	return injuries;
    }

    public boolean equalsData(APMMByDivisionData data) {
        if(divOrg1Id != null && !divOrg1Id.equals(data.divOrg1Id)) {
            return false;
        }
        if(divOrg2Id != null && !divOrg2Id.equals(data.divOrg2Id)) {
            return false;
        }
        if(divOrg3Id != null && !divOrg3Id.equals(data.divOrg3Id)) {
            return false;
        }
        if(divOrg4Id != null && !divOrg4Id.equals(data.divOrg4Id)) {
            return false;
        }
        return true;
    }

    public void addData(APMMByDivisionData other, Integer vehicle) {
//    	 if(period.equals(FleetData.BY_QUARTER)) {
//             totalVehiclesByQuarter++;
//         }else {
//             totalVehiclesByMonth++;
//         }
    	totalVehiclesByMonth++;
    	
    	if (vehicles != null){
    	        vehicles = vehicles + 1;
    	}else{
    			vehicles = new Float(1);
    	}
        
        miles = new Integer(miles.intValue() + other.miles.intValue());
        accidents = new Integer(accidents.intValue() + other.accidents.intValue());
        incidents = new Integer(incidents.intValue() + other.incidents.intValue());
        if(other.nearmiss != null)
        	nearmiss = new Integer(nearmiss.intValue() + other.nearmiss.intValue());
    }

    /**
     * 
     * @param other
     * @param key
     * @param period
     */
    public void addTotalData(APMMByDivisionData other,String key,Integer period) {
        totalVehiclesByMonth += other.totalVehiclesByMonth;
        totalVehiclesByQuarter += other.totalVehiclesByQuarter;
        if(period.equals(FleetData.BY_QUARTER)) {
            if(!monthKeysByQuarter.contains(key)) {
                monthKeysByQuarter.add(key);
            }
        }else {
            if(!monthKeysByMonth.contains(key)) {
                monthKeysByMonth.add(key);
            }
        }
        miles = new Integer(miles.intValue() + other.miles.intValue());
        accidents = new Integer(accidents.intValue() + other.accidents.intValue());
        incidents = new Integer(incidents.intValue() + other.incidents.intValue());
        nearmiss = new Integer(nearmiss.intValue() + other.nearmiss.intValue());
        float vehiclesTotal = 0;
        if(monthKeysByQuarter.size() > 0) {
            vehiclesTotal = totalVehiclesByQuarter / monthKeysByQuarter.size();
        }
        if(monthKeysByMonth.size() > 0) {
            vehiclesTotal += totalVehiclesByMonth / monthKeysByMonth.size();
        }
        vehicles = new Float(vehiclesTotal);
    }

    public void setTotalVehicles(float f,Integer period) {
        if(period.equals(FleetData.BY_QUARTER)) {
            totalVehiclesByQuarter = f;
        }else {
            totalVehiclesByMonth = f;
        }
        vehicles = f;
    }

    public void setMonths(float f) {
        months = f;
    }

    public void addMonth(String key,Integer period) {
        if(period.equals(FleetData.BY_QUARTER)) {
            monthKeysByQuarter.add(key);
        }else {
            monthKeysByMonth.add(key);            
        }
    }

	public void setMonthCount(String key) {
		if(!monthWithData.contains(key)) {
			monthWithData.add(key);
		}		
	}
}
