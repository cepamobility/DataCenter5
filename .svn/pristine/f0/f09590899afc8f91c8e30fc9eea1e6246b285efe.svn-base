/**
 * Created on 24-sep-2008
 * CEPA
 * DataCenter 5
 */
package persistence.vehicle.tables;

import java.io.Serializable;

import org.hibernate.Session;

public class VehicleTrailer {

    private VehicleTrailerPK pk;
    
    public VehicleTrailer() {
    }

    public VehicleTrailer(Integer vehicleId, Integer trailerId, Session session) {
        pk = new VehicleTrailerPK(vehicleId,trailerId,session);
    }

    public VehicleTrailerPK getPk() {
        return pk;
    }

    public void setPk(VehicleTrailerPK pk) {
        this.pk = pk;
    }

}
