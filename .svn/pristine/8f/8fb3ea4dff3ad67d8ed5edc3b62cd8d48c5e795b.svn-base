/**
 * Created on 24-sep-2008
 * CEPA
 * DataCenter 5
 */
package persistence.vehicle.tables;

import java.io.Serializable;

import org.hibernate.Session;

public class VehicleTrailerCheckPK implements Serializable {

    private VehicleCheck vehicle;
    private TrailerCheck trailer;
    
    public VehicleTrailerCheckPK() {
    }

    public VehicleTrailerCheckPK(Integer vehicleId, Integer trailerId, Session session) {
        vehicle = (VehicleCheck)session.get(VehicleCheck.class,vehicleId);
        trailer = (TrailerCheck)session.get(TrailerCheck.class,trailerId);
    }

    public TrailerCheck getTrailer() {
        return trailer;
    }

    public VehicleCheck getVehicle() {
        return vehicle;
    }

    public void setTrailer(TrailerCheck trailer) {
        this.trailer = trailer;
    }

    public void setVehicle(VehicleCheck vehicle) {
        this.vehicle = vehicle;
    }
}
