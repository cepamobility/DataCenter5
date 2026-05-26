package communication.driver.report;

import java.io.Serializable;
import java.util.Date;

import persistence.driver.tables.Driver;

import communication.vehicle.tables.VehicleData;

public class DetailedDriverReportWithVehiclesDataType 
		extends DetailedDriverReportDataType implements Serializable {

    /*
     * Datos del Vehiculo Asociado
     */
    protected VehicleData vehicleData;
    
    /* Otros Campos Monsanto BR */
    protected String idDallas;
    protected Integer lastOdometer;
    
    /* Campo para Tablets */
    protected boolean temporal;


    /*
     * 
     */
	public DetailedDriverReportWithVehiclesDataType(Integer id,
			String firstname, String lastname, String country, Date birthdate,
			Date exclusiondate, String contract, String divorg11,
			String divorg12, String divorg13, String divorg21, String divorg22,
			String divorg23, String divorg31, String divorg32, String divorg33,
			String divgeo2, String divgeo3, Driver driver, VehicleData vehicleData) {
		super(id, firstname, lastname, country, birthdate, exclusiondate,
				contract, divorg11, divorg12, divorg13, divorg21, divorg22,
				divorg23, divorg31, divorg32, divorg33, divgeo2, divgeo3,
				driver);
		this.vehicleData = vehicleData;
		this.lastOdometer = null;
	}
	
	/**
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param country
	 * @param birthdate
	 * @param exclusiondate
	 * @param contract
	 * @param divorg11
	 * @param divorg12
	 * @param divorg13
	 * @param divorg21
	 * @param divorg22
	 * @param divorg23
	 * @param divorg31
	 * @param divorg32
	 * @param divorg33
	 * @param divgeo2
	 * @param divgeo3
	 * @param driver
	 */
	public DetailedDriverReportWithVehiclesDataType(Integer id,
			String firstname, String lastname, String country, Date birthdate,
			Date exclusiondate, String contract, String divorg11,
			String divorg12, String divorg13, String divorg21, String divorg22,
			String divorg23, String divorg31, String divorg32, String divorg33,
			String divgeo2, String divgeo3, Driver driver){
		super(id, firstname, lastname, country, birthdate, exclusiondate,
				contract, divorg11, divorg12, divorg13, divorg21, divorg22,
				divorg23, divorg31, divorg32, divorg33, divgeo2, divgeo3,
				driver);
		this.vehicleData = null;
		this.lastOdometer = null;
	}
	
	/**
	 * Constructor por copia
	 * @param dd
	 */
	public DetailedDriverReportWithVehiclesDataType(DetailedDriverReportWithVehiclesDataType dd){
		
		super(dd.id,dd.firstname,dd.lastname,dd.country, dd.birthdate,dd.exclusiondate,
				dd.contract, dd.divorg11,dd.divorg12,dd.divorg13,dd.divorg21,dd.divorg22,
				dd.divorg23,dd.divorg31,dd.divorg32,dd.divorg33,dd.divgeo2,dd.divgeo3,dd.getDriver());
		
		this.vehicleData = dd.getVehicleData();
		this.nationality = dd.getNationality();
		this.sex = dd.getSex(); 
		this.city = dd.getCity();
		this.licenseExpiry = dd.getLicenseExpiry();
		this.licenseCategory = dd.getLicenseCategory();
		this.licenseGet = dd.getLicenseGet(); 
		this.vehicleallowed = dd.getVehicleallowed(); 
		this.officeaddress = dd.getOfficeaddress();
		this.email = dd.getEmail(); 
		this.personaladdress = dd.getPersonaladdress(); 
		this.licensenumber = dd.getLicensenumber();
		this.phonenumber = dd.getPhonenumber(); 
		this.inclusiondate = dd.getInclusiondate(); 
		this.drives = dd.getDrives();
		this.employee = dd.getEmployee(); 
		this.bpsregistry = dd.getBpsRegistry(); 
		this.corporationId = dd.getCorporationId();
		this.licenseCoverage = dd.getLicenseCoverage();
		this.federativeEntity = dd.getFederativeEntity();
		this.lastOdometer = dd.getLastOdometer();
		this.idDallas = dd.getIdDallas();
		this.temporal = dd.isTemporal();
		this.superviser = dd.getSuperviser();
		this.manager = dd.getManager();
	}

	/**
	 * @return the vehicle
	 */
	public VehicleData getVehicleData() {
		return vehicleData;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicleData(VehicleData vehicleData) {
		this.vehicleData = vehicleData;
	}

	/**
	 * @return the idDallas
	 */
	public String getIdDallas() {
		return idDallas;
	}

	/**
	 * @param idDallas the idDallas to set
	 */
	public void setIdDallas(String idDallas) {
		this.idDallas = idDallas;
	}

	/**
	 * @return the lastOdometer
	 */
	public Integer getLastOdometer() {
		return lastOdometer;
	}

	/**
	 * @param lastOdometer the lastOdometer to set
	 */
	public void setLastOdometer(Integer lastOdometer) {
		this.lastOdometer = lastOdometer;
	}

	/**
	 * @return the temporal
	 */
	public boolean isTemporal() {
		return temporal;
	}

	/**
	 * @param temporal the temporal to set
	 */
	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

}
