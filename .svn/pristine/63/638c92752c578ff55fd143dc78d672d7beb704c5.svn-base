/**
 * Created on 24-oct-2006
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Date;

import persistence.driver.tables.Driver;

public class DetailedDriverReportDataType extends DriverReportDataType implements Serializable {

    protected String sex;
    protected String nationality;
    protected String city;
    protected Date licenseExpiry;
    protected String licenseCategory;
    protected Date licenseGet;
    protected String vehicleallowed;
    protected String officeaddress;
    protected String email;
    protected String personaladdress;
    protected String licensenumber;
    protected Date inclusiondate;
    protected boolean drives;
    protected boolean employee;
    protected String bpsregistry;
    protected String corporationId;
    protected boolean licenseNotExpiry;
    protected String superviser;
    protected String manager;
    protected String iddallas;
    
    /*
     * Agregado para Allergan y Astrazeneca 05/10/2011
     */
    protected String licenseCoverage; // CPF
    protected String federativeEntity; // RG
    protected String authorizateWho; // Pepsico, "Especificar quien autoriza la alta 
    protected String licenseObs;
    protected boolean director;
    
    public DetailedDriverReportDataType(Integer id, String firstname, String lastname,
            String country, Date birthdate, Date exclusiondate,
            String contract, String divorg11, String divorg12, String divorg13,
            String divorg21, String divorg22, String divorg23, String divorg31,
            String divorg32, String divorg33, String divgeo2, String divgeo3, Driver driver) {
        super(id, firstname, lastname, country, birthdate, exclusiondate, contract, divorg11,
                divorg12, divorg13, divorg21, divorg22, divorg23, divorg31,
                divorg32, divorg33, divgeo2, divgeo3, driver);
    }
    
         

    public DetailedDriverReportDataType(String sex, String nationality,
			String city, Date licenseExpiry, String licenseCategory, String licenseObs,
			Date licenseGet, String vehicleallowed, String officeaddress,
			String email, String personaladdress, String licensenumber,
			String phonenumber, Date inclusiondate, boolean drives,
			boolean employee, String bpsregistry, String corporationId,
			boolean licenseNotExpiry, String licenseCoverage,
			String federativeEntity, String superviser, String manager, 
			String iddallas, String authorizateWho, boolean director) {
		super();
		this.sex = sex;
		this.nationality = nationality;
		this.city = city;
		this.licenseExpiry = licenseExpiry;
		this.licenseCategory = licenseCategory;
		this.licenseObs = licenseObs;
		this.licenseGet = licenseGet;
		this.vehicleallowed = vehicleallowed;
		this.officeaddress = officeaddress;
		this.email = email;
		this.personaladdress = personaladdress;
		this.licensenumber = licensenumber;
		this.phonenumber = phonenumber;
		this.inclusiondate = inclusiondate;
		this.drives = drives;
		this.employee = employee;
		this.bpsregistry = bpsregistry;
		this.corporationId = corporationId;
		this.licenseNotExpiry = licenseNotExpiry;
		this.licenseCoverage = licenseCoverage;
		this.federativeEntity = federativeEntity;
		this.superviser = superviser;
		this.manager = manager;
		this.iddallas = iddallas;
		this.authorizateWho = authorizateWho; 
		this.director = director;
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

	/**
	 * @return the licenseCoverage
	 */
	public String getLicenseCoverage() {
		return licenseCoverage;
	}

	/**
	 * @param licenseCoverage the licenseCoverage to set
	 */
	public void setLicenseCoverage(String licenseCoverage) {
		this.licenseCoverage = licenseCoverage;
	}

	/**
	 * @return the federativeEntity
	 */
	public String getFederativeEntity() {
		return federativeEntity;
	}

	/**
	 * @param federativeEntity the federativeEntity to set
	 */
	public void setFederativeEntity(String federativeEntity) {
		this.federativeEntity = federativeEntity;
	}

	public String getSuperviser() {
		return superviser;
	}

	public void setSuperviser(String superviser) {
		this.superviser = superviser;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getIddallas() {
		return iddallas;
	}

	public void setIddallas(String iddallas) {
		this.iddallas = iddallas;
	}

	public String getAuthorizateWho() {
		return authorizateWho;
	}

	public void setAuthorizateWho(String authorizateWho) {
		this.authorizateWho = authorizateWho;
	}

	public String getLicenseObs() {
		return licenseObs;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}

	public boolean isDirector() {
		return director;
	}

	public void setDirector(boolean director) {
		this.director = director;
	}
}
