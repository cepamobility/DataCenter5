package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

import communication.datatype.tables.VehicleContractDataType;

public class VehicleContractData implements Serializable {

    private Integer id;
    private Date date;
    private VehicleContractDataType type;
	private boolean saved;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the type
	 */
	public VehicleContractDataType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(VehicleContractDataType type) {
		this.type = type;
	}
	
	public VehicleContractData(Integer id, Date date,
			VehicleContractDataType type, boolean saved) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.saved = saved;
	}
	
	public VehicleContractData() {
		super();
	}
	
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
    
    
}
