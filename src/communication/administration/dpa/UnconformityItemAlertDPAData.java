package communication.administration.dpa;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author fcarriquiry
 *
 */
public class UnconformityItemAlertDPAData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String unconformityId;
	private String type;
	private String comment;
	private String expirity;
	private String unconformityType;
	private String referencia;
	private String driverId;
	private String divOrgItem1;

	private Boolean firstNotify;
	
	private String vehicleId;
	private String date;
	private String email;
	
	
	public String getDivOrgItem1() {
		return divOrgItem1;
	}
	public void setDivOrgItem1(String divOrgItem1) {
		this.divOrgItem1 = divOrgItem1;
	}
	
	public Boolean getFirstNotify() {
		return firstNotify;
	}
	public void setFirstNotify(Boolean firstNotify) {
		this.firstNotify = firstNotify;
	}
	public String getUnconformityId() {
		return unconformityId;
	}
	public void setUnconformityId(String unconformityId) {
		this.unconformityId = unconformityId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getExpirity() {
		return expirity;
	}
	public void setExpirity(String expirity) {
		this.expirity = expirity;
	}
	public String getUnconformityType() {
		return unconformityType;
	}
	public void setUnconformityType(String unconformityType) {
		this.unconformityType = unconformityType;
	}

	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	 
	
	
	
	
}
