package communication.vehicle.report;

import java.io.Serializable;
import java.util.Date;

import communication.vehicle.tables.VehicleData;

public class VehicleContractReportDataType implements Serializable, Comparable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected VehicleData vehicle;
    protected Date contractDate;
    protected String contractType;
    
	public VehicleData getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleData vehicle) {
		this.vehicle = vehicle;
	}
	public Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public VehicleContractReportDataType(VehicleData vehicle,
			Date contractDate, String contractType) {
		super();
		this.vehicle = vehicle;
		this.contractDate = contractDate;
		this.contractType = contractType;
	}
	
	
	
	/**
	 * Constructor por copia
	 * @param vContract
	 */
	public VehicleContractReportDataType(VehicleContractReportDataType vContract) {
		this.vehicle = new VehicleData(vContract.getVehicle());
		this.contractDate = vContract.getContractDate();
		this.contractType = vContract.getContractType();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contractDate == null) ? 0 : contractDate.hashCode());
		result = prime * result
				+ ((contractType == null) ? 0 : contractType.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleContractReportDataType other = (VehicleContractReportDataType) obj;
		if (contractDate == null) {
			if (other.contractDate != null)
				return false;
		} else if (!contractDate.equals(other.contractDate))
			return false;
		if (contractType == null) {
			if (other.contractType != null)
				return false;
		} else if (!contractType.equals(other.contractType))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}
	public int compareTo(Object arg0) {
		if (VehicleContractReportDataType.class.isInstance(arg0)){
			VehicleContractReportDataType v = (VehicleContractReportDataType) arg0;
			return contractDate.compareTo(v.contractDate);
		}
		return 0;
	}

	
	
	
    
}
    