/**
 * Created on 26-sep-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;
import java.util.Hashtable;

import communication.administration.user.UserSessionData;
import communication.vehicle.tables.VehicleData;

public class FleetMileageTotal extends FleetMileage implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float months;

    public FleetMileageTotal(Object[] mileage,float months) {
        super();
        this.period = new Integer(0);
        this.year = new Integer(0);

        int i = 0;
        this.vehicles = new Integer(((Long) mileage[i]).intValue());
        i++;
        this.miles = new Integer(((Long) mileage[i]).intValue());
        i++;
        this.accidents = new Integer(((Long) mileage[i]).intValue());
        i++;
        this.incidents = new Integer(((Long) mileage[i]).intValue());
        i++;
        this.nearmiss = new Integer(((Long) mileage[i]).intValue());
        i++;

        this.country = (String) mileage[i];
        i++;
        this.vehicleType = (String) mileage[i];
        i++;
        
        if(mileage.length > i) {
            this.divOrg1Id = (Integer) mileage[i];
            i++;
            this.divOrg1Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg2Id = (Integer) mileage[i];
            i++;
            this.divOrg2Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg3Id = (Integer) mileage[i];
            i++;
            this.divOrg3Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg4Id = (Integer) mileage[i];
            i++;
            this.divOrg4Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg5Id = (Integer) mileage[i];
            i++;
            this.divOrg5Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg6Id = (Integer) mileage[i];
            i++;
            this.divOrg6Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg7Id = (Integer) mileage[i];
            i++;
            this.divOrg7Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg8Id = (Integer) mileage[i];
            i++;
            this.divOrg8Name = (String) mileage[i];
            i++;
        }
        if(mileage.length > i) {
            this.divOrg9Id = (Integer) mileage[i];
            i++;
            this.divOrg9Name = (String) mileage[i];
            i++;
        }

        this.months = months;
    }


    public FleetMileageTotal(String key, VehicleData data, Integer integer, Integer integer2, Integer integer3, Integer integer4,Integer integer5,float months,Hashtable divisions,Integer fleet,UserSessionData userSessionData) {
        super(key,data,integer,integer2,integer3,integer4,integer5,divisions,fleet,userSessionData);
        this.period = new Integer(0);
        this.year = new Integer(0);
        this.months = months;
    }
    
    public void addData(FleetMileage other) {
        vehicles = new Integer(vehicles.intValue() + other.vehicles.intValue());
        miles = new Integer(miles.intValue() + other.miles.intValue());
        accidents = new Integer(accidents.intValue() + other.accidents.intValue());
        incidents = new Integer(incidents.intValue() + other.incidents.intValue());
    }
    
    public Float getVehicles() {
        return new Float(vehicles.floatValue() / months);
    }

}
