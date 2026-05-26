/**
 * Created on 24-sep-2008
 * CEPA
 * DataCenter 5
 */
package persistence.vehicle.tables;

import java.io.Serializable;

import org.hibernate.Session;

public class VehicleTrailerCheck {

    private VehicleTrailerCheckPK pk;
    
    public VehicleTrailerCheck() {
    }

    public VehicleTrailerCheck(Integer vehicleId, Integer trailerId, Session session) {
        pk = new VehicleTrailerCheckPK(vehicleId,trailerId,session);
    }

    public VehicleTrailerCheckPK getPk() {
        return pk;
    }

    public void setPk(VehicleTrailerCheckPK pk) {
        this.pk = pk;
    }

}
