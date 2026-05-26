/**
 * Created on 24-oct-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Date;

import persistence.driver.tables.Driver;

public class DetailedFOSADriverReportDataType extends FOSADriverReportDataType implements Serializable {

	private Date health;
	
    private String sex;
    private String nationality;
    private String city;
    private Date licenseExpiry;
    private String licenseCategory;
    private Date licenseGet;
    private String vehicleallowed;
    private String officeaddress;
    private String email;
    private String personaladdress;
    private String licensenumber;
    private String phonenumber;
    private Date inclusiondate;
    private boolean drives;
    private boolean employee;
    private String bpsregistry;
    private String corporationId;
    private boolean licenseNotExpiry;

	private String licenseObs;

    public DetailedFOSADriverReportDataType(Integer id, String firstname, String lastname,
            String country, Date birthdate, Date exclusiondate,
            String contract, String divorg11, String divorg12, String divorg13,
            String divorg21, String divorg22, String divorg23, String divorg31,
            String divorg32, String divorg33, String divgeo2, String divgeo3, Driver driver) {
        super(id, firstname, lastname, country, birthdate, exclusiondate, contract, divorg11,
                divorg12, divorg13, divorg21, divorg22, divorg23, divorg31,
                divorg32, divorg33, divgeo2, divgeo3, driver);
        this.health = driver.getHealthExpiry();
    }

	public Date getHealth() {
		return health;
	}

	public void setHealth(Date health) {
		this.health = health;
	}
	
	public String getCity() {
        return city;
    }

    public boolean getDrives() {
        return drives;
    }

    public String getEmail() {
        return email;
    }

    public boolean getEmployee() {
        return employee;
    }

    public Date getInclusiondate() {
        return inclusiondate;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public Date getLicenseExpiry() {
        return licenseExpiry;
    }

    public Date getLicenseGet() {
        return licenseGet;
    }

    public String getLicensenumber() {
        return licensenumber;
    }

    public String getNationality() {
        return nationality;
    }

    public String getOfficeaddress() {
        return officeaddress;
    }

    public String getPersonaladdress() {
        return personaladdress;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public String getVehicleallowed() {
        return vehicleallowed;
    }

    public String getBpsregistry() {
        return bpsregistry;
    }
    
    public String getCorporationId() {
        return corporationId;
    }
    
    

    public void setBpsregistry(String bpsregistry) {
        this.bpsregistry = bpsregistry;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDrives(boolean drives) {
        this.drives = drives;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public void setInclusiondate(Date inclusionDate) {
        this.inclusiondate = inclusionDate;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public void setLicenseExpiry(Date licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
    }

    public void setLicenseGet(Date licenseGet) {
        this.licenseGet = licenseGet;
    }

    public void setLicensenumber(String licensenumber) {
        this.licensenumber = licensenumber;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setOfficeaddress(String officeaddress) {
        this.officeaddress = officeaddress;
    }

    public void setPersonaladdress(String personaladdress) {
        this.personaladdress = personaladdress;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setVehicleallowed(String vehicleallowed) {
        this.vehicleallowed = vehicleallowed;
    }

    public void setBpsregitry(String bpsregistry) {
        this.bpsregistry = bpsregistry;
    }
    
    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

	/**
	 * @return the licenseNotExpiry
	 */
	public boolean isLicenseNotExpiry() {
		return licenseNotExpiry;
	}

	/**
	 * @param licenseNotExpiry the licenseNotExpiry to set
	 */
	public void setLicenseNotExpiry(boolean licenseNotExpiry) {
		this.licenseNotExpiry = licenseNotExpiry;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}
    
}
