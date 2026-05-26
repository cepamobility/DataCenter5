package persistence.log.vehicle;

import java.io.Serializable;
import java.util.Date;

import persistence.vehicle.tables.VehicleCheck;

public class VehicleCheckLog implements Serializable {

	private static final long serialVersionUID = 1L;

    private VehicleCheckLogPK pk;
	
    protected Integer action;
	protected Date checkDate;
	protected String name;
	protected String obs;
	protected String serie;	
	protected Integer vehicleKm;
	protected Integer vehicle;
	protected String licenseNumber;
	protected Date dateLicenseExpiry;
	protected Integer result;
	protected String inspectedby;
	protected String distrito;
	protected String grupo;

	protected String values;

	public VehicleCheckLog() {
	}

	public VehicleCheckLog(VehicleCheck check,String user,Date date,Integer action) {
		pk = new VehicleCheckLogPK(check.getVehicleCheckId(), user, date);
		this.action = action;
		this.checkDate = check.getDate();
		this.name = check.getName();
		this.obs = check.getObs();
		this.serie = check.getSerie();
		this.vehicleKm = check.getVehicleKm();
		this.vehicle = check.getVehicle().getId();
		this.licenseNumber = check.getLicenseNumber();
		this.dateLicenseExpiry = check.getDateLicenseExpiry();
		this.result = check.getResult();
		this.inspectedby = check.getInspectedby();
		this.distrito = check.getDistrito();
		this.grupo = check.getGrupo();
		this.values = check.getDescription();
	}

	public VehicleCheckLogPK getPk() {
		return pk;
	}

	public void setPk(VehicleCheckLogPK pk) {
		this.pk = pk;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Integer getVehicleKm() {
		return vehicleKm;
	}

	public void setVehicleKm(Integer vehicleKm) {
		this.vehicleKm = vehicleKm;
	}

	public Integer getVehicle() {
		return vehicle;
	}

	public void setVehicle(Integer vehicle) {
		this.vehicle = vehicle;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Date getDateLicenseExpiry() {
		return dateLicenseExpiry;
	}

	public void setDateLicenseExpiry(Date dateLicenseExpiry) {
		this.dateLicenseExpiry = dateLicenseExpiry;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getInspectedby() {
		return inspectedby;
	}

	public void setInspectedby(String inspectedby) {
		this.inspectedby = inspectedby;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}
	
}
