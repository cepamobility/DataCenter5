/**
 * Created on 22-may-2008
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import persistence.util.Util;

import communication.fleet.tables.FleetDivisionData;
import communication.fleet.tables.OdometerData;
import communication.vehicle.tables.VehicleData;

public class FleetUtil {

    public FleetUtil() {
        super();
    }

    /**
     * 
     * @param values
     * @param division
     * @param vehicles
     * @return
     */
    public String[][] findFleetOdometerValues(Hashtable values,FleetDivisionData division,
    		Collection vehicles) {
        Hashtable result = new Hashtable(); 
        Collection plates = new LinkedList(); 
        Iterator it = vehicles.iterator();
        int i = 0;
        while(it.hasNext()) {
            VehicleData vehicle = (VehicleData)it.next();
            if(division.containsVehicle(vehicle)) {
                OdometerData odometer = (OdometerData) values.get(vehicle.getId());
                String[] valueData = new String[13]; 
                valueData[0] = (vehicle.getId() != null) ? String.valueOf(vehicle.getId()): "";
                valueData[1] = vehicle.getMatricula();
                valueData[2] = (vehicle.getDriver() == null) ? "----" : vehicle.getDriver().getFirstName()+" "+vehicle.getDriver().getLastName();
                valueData[3] = (odometer != null && odometer.getLastOdometer() != null) ? String.valueOf(odometer.getLastOdometer()) : "";
                valueData[4] = (odometer != null && odometer.getOdometer() != null) ? String.valueOf(odometer.getOdometer()) : "";
                valueData[5] = "0";
                valueData[6] = "0";
                valueData[7] = (odometer != null && odometer.getAccidents() != null) ? String.valueOf(odometer.getAccidents()) : "";
                valueData[8] = (odometer != null && odometer.getIncidents() != null) ? String.valueOf(odometer.getIncidents()) : "";   
                valueData[9] = (odometer != null && odometer.getFuel() != null) ? String.valueOf(odometer.getFuel()) : "";
                valueData[10] = (odometer != null && odometer.getFuelType() != null) ? odometer.getFuelType().getResourceBundleId() : "";
                valueData[11] = (odometer != null && odometer.getNearmiss() != null) ? String.valueOf(odometer.getNearmiss()) : "";
                if(!Util.empty(valueData[3]) && !Util.empty(valueData[4])) {
                    if(division.getOdometer().getResourceBundleId().equals("datatype.distanceunit.miles")) {
                        valueData[5] = String.valueOf(Integer.parseInt(valueData[4]) - Integer.parseInt(valueData[3]));
                        valueData[6] = String.valueOf((int)(Integer.parseInt(valueData[5]) * Util.KMS_TO_MILES));
                    }else {
                        valueData[6] = String.valueOf(Integer.parseInt(valueData[4]) - Integer.parseInt(valueData[3]));
                        valueData[5] = String.valueOf((int)(Integer.parseInt(valueData[6]) / Util.KMS_TO_MILES));
                    }
                }
                valueData[12] = (vehicle.getDriver() != null &&
                			vehicle.getDriver().getId() != null) ? String.valueOf(vehicle.getDriver().getId()):"";
                
                result.put(vehicle.getMatricula(),valueData);
                plates.add(vehicle.getMatricula());
            }
        }
        Collections.sort((List)plates);
        it = plates.iterator();
        String[][] array = new String[plates.size()][11];
        while(it.hasNext()) {
            array[i] = (String[])result.get(it.next());
            i++;
        }
        return array;
    }
    
    /**
     * 
     * @param values
     * @param data
     * @param type
     * @return
     */
    public String[] findFleetOdometerValues(Hashtable values,Object[] data,String type) {
        OdometerData odometer = (OdometerData) values.get(data[0]);
        String[] valueData = new String[13]; 
        valueData[0] = (data[0] != null) ? String.valueOf(data[0]):"";
        valueData[1] = (data[1] != null) ? String.valueOf(data[1]):"";
        valueData[2] = (data[2] == null) ? "---" : String.valueOf(data[2])+" "+String.valueOf(data[3]);
        valueData[3] = (odometer != null && odometer.getLastOdometer() != null) ? String.valueOf(odometer.getLastOdometer()) : "";
        valueData[4] = (odometer != null && odometer.getOdometer() != null) ? String.valueOf(odometer.getOdometer()) : "";
        valueData[5] = "0";
        valueData[6] = "0";
        valueData[7] = (odometer != null && odometer.getAccidents() != null) ? String.valueOf(odometer.getAccidents()) : "";
        valueData[8] = (odometer != null && odometer.getIncidents() != null) ? String.valueOf(odometer.getIncidents()) : "";   
        valueData[9] = (odometer != null && odometer.getFuel() != null) ? String.valueOf(odometer.getFuel()) : "";
        valueData[10] = (odometer != null && odometer.getFuel() != null && odometer.getFuelType() != null) ? odometer.getFuelType().getResourceBundleId() : "";
        valueData[11] = (odometer != null && odometer.getNearmiss() != null) ? String.valueOf(odometer.getNearmiss()) : "";
        if(!Util.empty(valueData[3]) && !Util.empty(valueData[4])) {
            if(type.equals("datatype.distanceunit.miles")) {
                valueData[5] = String.valueOf(Integer.parseInt(valueData[4]) - Integer.parseInt(valueData[3]));
                valueData[6] = String.valueOf((int)(Integer.parseInt(valueData[5]) * Util.KMS_TO_MILES));
            }else {
                valueData[6] = String.valueOf(Integer.parseInt(valueData[4]) - Integer.parseInt(valueData[3]));
                valueData[5] = String.valueOf((int)(Integer.parseInt(valueData[6]) / Util.KMS_TO_MILES));
            }
        }
        valueData[12] =  (data.length >= 12 && data[11] != null) ? String.valueOf(data[11]):""; // idDriver
        return valueData;
    }

}
