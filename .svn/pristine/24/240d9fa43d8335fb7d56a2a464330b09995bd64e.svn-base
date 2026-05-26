/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.classic.Session;

import persistence.vehicle.tables.Vehicle;

import communication.administration.user.UserSessionData;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetMainAttributes;

public class IndividualFleet extends AbstractFleet {

    private Set vehicleSet;
    
    public IndividualFleet() {
        super();
    }

    public IndividualFleet(FleetData data, Session session, UserSessionData userSessionData) {
        super(data.getId(),data.getName(),data.getDescription(),data.isActive(),data.getPeriod(),data.getDivisions(),session,userSessionData);
    }

    /**
     * @return Returns the vehicleSet.
     */
    public Set getVehicleSet() {
        return vehicleSet;
    }
    
    public Collection getVehiclesIds()
    {
    	Vehicle vehicle;
    	Collection ids = new ArrayList();
    	Iterator it = vehicleSet.iterator();
    	while (it.hasNext())
    	{
    		vehicle = (Vehicle) it.next();
    		ids.add(vehicle.getId());
       	}
    	return ids;
    }
    
    public void addVehicle(Vehicle vehicle)
    {
    	if (!vehicleSet.contains(vehicle))
    	{
    		vehicleSet.add(vehicle);
    	}
    }

    /**
     * @param vehicleSet The vehicleSet to set.
     */
    public void setVehicleSet(Set vehicleSet) {
        this.vehicleSet = vehicleSet;
    }

    public FleetMainAttributes getMainAttributes() {
        FleetMainAttributes fleet = super.getMainAttributes();
        fleet.setType(new Integer(FleetData.INDIVIDUAL));
        return fleet;
    }
    
    public FleetData getFleetData() {
        FleetData fleet = super.getFleetData();
        fleet.setType(new Integer(FleetData.INDIVIDUAL));
        Collection vehicles = new LinkedList();
        Iterator it = vehicleSet.iterator();
        while(it.hasNext()) {
            vehicles.add(((Vehicle)it.next()).getPrincipalAttributes());
        }
        fleet.setVehicles(vehicles);
        return fleet;
    }

    public FleetData getFleetData(Integer driver) {
        FleetData fleet = super.getFleetData();
        fleet.setType(new Integer(FleetData.INDIVIDUAL));
        Collection vehicles = new LinkedList();
        Iterator it = vehicleSet.iterator();
        while(it.hasNext()) {
            Vehicle vehicle = (Vehicle)it.next();
            if(vehicle.getDriver() != null && vehicle.getDriver().getId().equals(driver)) {
                vehicles.add(vehicle.getPrincipalAttributes());
            }
        }
        fleet.setVehicles(vehicles);
        return fleet;
    }
    
    public void updateData(FleetData data, Session session, 
    		UserSessionData userRequest)
	{
		super.updateData(data,session,userRequest);
		// this.vehicleSet = new HashSet(data.getVehicles());
	}
}
