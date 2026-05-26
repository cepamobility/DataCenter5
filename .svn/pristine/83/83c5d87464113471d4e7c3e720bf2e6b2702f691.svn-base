package persistence.vehicle.tables;

import java.io.Serializable;

import org.hibernate.classic.Session;

import persistence.datatype.tables.VehicleContractType;

import communication.datatype.tables.VehicleContractDataType;
import communication.vehicle.tables.VehicleContractData;

public class VehicleContract implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private VehicleContractPK pk;
	private VehicleContractType type;
	private boolean saved;
	
	/**
	 * @return the pk
	 */
	public VehicleContractPK getPk() {
		return pk;
	}
	/**
	 * @param pk the pk to set
	 */
	public void setPk(VehicleContractPK pk) {
		this.pk = pk;
	}
	/**
	 * @return the type
	 */
	public VehicleContractType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(VehicleContractType type) {
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleContract other = (VehicleContract) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public VehicleContractData getData() {
        return new VehicleContractData(pk.getVehicle().getId(),pk.getDate(),
        		new VehicleContractDataType(type.getResourceBundleId()),saved);
	}
	
	/**
	 * 
	 * @param pk
	 * @param type
	 */
	public VehicleContract(VehicleContractPK pk, VehicleContractType type) {
		super();
		this.pk = pk;
		this.type = type;
	}
	
	/**
	 * 
	 * @param vehicleId
	 * @param data
	 * @param session
	 */
	public  VehicleContract(Integer vehicleId, VehicleContractData data, Session session) {
		Vehicle vehicle=(Vehicle)session.load(Vehicle.class, vehicleId);
		pk = new VehicleContractPK(vehicle,data.getDate());
		type = new VehicleContractType(data.getType().getResourceBundleId());
		saved = data.isSaved();
	}
	
	
	public VehicleContract() {
		super();
	}
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	
	
	
}
