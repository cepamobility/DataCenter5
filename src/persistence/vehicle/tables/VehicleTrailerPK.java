/**
 * Created on 24-sep-2008
 * CEPA
 * DataCenter 5
 */
package persistence.vehicle.tables;

import java.io.Serializable;

import org.hibernate.Session;

public class VehicleTrailerPK implements Serializable {

    private Vehicle vehicle;
    private Trailer trailer;
    
    public VehicleTrailerPK() {
    }

    public VehicleTrailerPK(Integer vehicleId, Integer trailerId, Session session) {
        vehicle = (Vehicle)session.get(Vehicle.class,vehicleId);
        trailer = (Trailer)session.get(Trailer.class,trailerId);
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
