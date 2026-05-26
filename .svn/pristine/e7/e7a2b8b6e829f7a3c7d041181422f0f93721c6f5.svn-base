package persistence.log.fleet;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import persistence.util.Util;

public class FleetLogData extends FleetLogMainAttributes {

    public static final int INDIVIDUAL = 1;
    public static final int COLECTIVE = 2;
    
    public static final int ACTIVE = 0;
    public static final int EXCLUDED = 1;
    public static final int ALL = 2;
    
    protected Collection divisions;
    private Collection vehicles;
    
    public FleetLogData() {
        super();
    }

    public FleetLogData(FleetLogPK pk, String name, String description, 
    		boolean active, Integer period, Integer type, 
    		Collection divisions, Date exclusionDate, 
    		Set vehicleIdSet, int fleetType) {
        super(pk,name,description,active,period,type,exclusionDate,vehicleIdSet,fleetType);
        this.divisions = divisions; 
    }
    
    public FleetLogData(FleetLogPK pk) {
        this.pk = pk;
    }

    /**
     * @return Returns the divisions.
     */
    public Collection getDivisions() {
        return divisions;
    }

    /**
     * @param divisions The divisions to set.
     */
    public void setDivisions(Collection divisions) {
        this.divisions = divisions;
    }

    public String validate() {
        if(Util.empty(name)) {
            return "fleet.error.emptyname";
        }
        if(Util.empty(description)) {
            return "fleet.error.emptydescription";
        }
        if(period == null) {
            return "fleet.error.emptyperiod";
        }
        return null;
    }

    /**
     * @return Returns the vehicles.
     */
    public Collection getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles The vehicles to set.
     */
    public void setVehicles(Collection vehicles) {
        this.vehicles = vehicles;
    }

    public Iterator getDivisionsIterator() {
        Collections.sort((List)divisions);
        return divisions.iterator();
    }
}
