package persistence.log.fleet;

import java.io.Serializable;

import communication.fleet.tables.VehicleFleetObservationData;

public class VehicleFleetLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VehicleFleetLogPK pk;
	private Integer vehicleId;
	
	
	public VehicleFleetLogPK getPk() {
		return pk;
	}

	public void setPk(VehicleFleetLogPK pk) {
		this.pk = pk;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * 
	 */
	public VehicleFleetLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param pk
	 * @param vehicleId
	 */
	public VehicleFleetLog(VehicleFleetLogPK pk, Integer vehicleId) {
		super();
		// TODO Auto-generated constructor stub
		this.pk = pk;
		this.vehicleId = vehicleId;
	}
	
	public VehicleFleetObservationData getVehicleFleetObservations() {
        return new VehicleFleetObservationData(
        		pk.getFleetId(),this.getVehicleId(),pk.getDate(),pk.getUserId());
    }

}
