/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import org.hibernate.Session;

import communication.administration.user.UserSessionData;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetMainAttributes;

public class ColectiveFleet extends AbstractFleet {

    public ColectiveFleet() {
    }

    public ColectiveFleet(FleetData data, Session session, UserSessionData userSessionData) {
        super(data.getId(),data.getName(),data.getDescription(),data.isActive(),data.getPeriod(),data.getDivisions(),session,userSessionData);
    }

    public FleetMainAttributes getMainAttributes() {
        FleetMainAttributes fleet = super.getMainAttributes();
        fleet.setType(new Integer(FleetData.COLECTIVE));
        return fleet;
    }

    public FleetData getFleetData() {
        FleetData fleet = super.getFleetData();
        fleet.setType(new Integer(FleetData.COLECTIVE));
        return fleet;
    }
}
