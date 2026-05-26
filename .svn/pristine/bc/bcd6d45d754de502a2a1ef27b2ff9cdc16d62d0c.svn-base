package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

import communication.driver.tables.DriverBayerData;

public class DriverBayer implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String corporationid; // codigo colaborador
	private String fullName; // Nombre completo
	
	// Datos a cargar por el conductor:
    private Date licenseExpiry;
	private String stateOrRegion; // estado o localidad donde se ubica la ciudad (ubicacion del conductor)
	private String city;
	private String email;
    private String phone;
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
	 * @return the corporationid
	 */
	public String getCorporationid() {
		return corporationid;
	}
	/**
	 * @param corporationid the corporationid to set
	 */
	public void setCorporationid(String corporationid) {
		this.corporationid = corporationid;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the stateOrRegion
	 */
	public String getStateOrRegion() {
		return stateOrRegion;
	}
	/**
	 * @param stateOrRegion the stateOrRegion to set
	 */
	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}
	
	public DriverBayer() {
		super();
	}
	
	public DriverBayer(DriverBayerData data) {
		this.id = data.getId();
		this.corporationid = data.getCorporationid();
		this.fullName = data.getFullName();
		this.email = data.getEmail();
		this.stateOrRegion = data.getStateOrRegion();
		this.city = data.getCity();
		this.licenseExpiry = data.getLicenseExpiry();
		this.phone = data.getPhone();
	}
	
	public DriverBayerData getDriverBayerData(){
		DriverBayerData data = new DriverBayerData(id,corporationid, fullName, licenseExpiry, 
				stateOrRegion, city, email, phone);
		return data;
	}
 
	 public void updateData(DriverBayerData data) {
		 	this.id = data.getId();
			this.corporationid = data.getCorporationid();
			this.fullName = data.getFullName();
			this.email = data.getEmail();
			this.stateOrRegion = data.getStateOrRegion();
			this.city = data.getCity();
			this.licenseExpiry = data.getLicenseExpiry();
			this.phone = data.getPhone();
	 }
	public DriverBayer(Integer id, String corporationid, String fullName,
			Date licenseExpiry, String stateOrRegion, String city,
			String email, String phone) {
		super();
		this.id = id;
		this.corporationid = corporationid;
		this.fullName = fullName;
		this.licenseExpiry = licenseExpiry;
		this.stateOrRegion = stateOrRegion;
		this.city = city;
		this.email = email;
		this.phone = phone;
	}
	public Date getLicenseExpiry() {
		return licenseExpiry;
	}
	public void setLicenseExpiry(Date licenseExpiry) {
		this.licenseExpiry = licenseExpiry;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
}