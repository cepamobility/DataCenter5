/*
 * Created on 27-oct-2005
 */
package persistence.log.driver;

import java.util.Date;

import persistence.driver.tables.Driver;

import communication.util.ObservationData;

/**
 * @author jrodriguez
 */
public class DriverLog {

    private DriverLogPK pk;
    
    private Integer action;
    private String comment;
    
	private String firstName;
	private String lastName;
	private Date birthDate;
    private String sex;
    private String nationality;
    private String city;
    private String personalAddress;

    private Integer corporation;
    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;
    private Integer country;
    private Integer geoLevel1; 
    
    private String corporationId;
    private Date inclusionDate;
    private String email;
    private String phoneNumber;
    private String officeAddress;
    private Date exclusionDate;

    private String licenseNumber;
    private Date licenseExpiry;
	private String licenseCategory;
	private Date licenseGet;
	private String vehicleAllowed;

	public DriverLog() {
	}
	
	public DriverLog(Driver driver,String user,Date date,int action,String comment) {
		pk = new DriverLogPK(driver.getId(),user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;
		this.firstName = driver.getFirstName();
		this.lastName = driver.getLastName();
		this.birthDate = driver.getBirthDate();
		this.sex = driver.getSex().getResourceBundleId();
		this.nationality = driver.getNationality();
		this.corporation = driver.getCorporation().getId();
		this.country = driver.getCorpCountry().getId();
		this.city = driver.getCity();
		this.divOrg1 = (driver.getDivOrg1() != null) ? driver.getDivOrg1().getId():null;
		this.divOrg2 = (driver.getDivOrg2() != null) ? driver.getDivOrg2().getId():null;
		this.divOrg3 = (driver.getDivOrg3() != null) ? driver.getDivOrg3().getId():null;
		this.licenseExpiry = driver.getLicenseExpiry();
		this.licenseCategory = driver.getLicenseCategory();
		this.licenseGet = driver.getLicenseGet();
        if(driver.getVehicleAllowed() != null) {
            this.vehicleAllowed = driver.getVehicleAllowed().getResourceBundleId();
        }
		this.officeAddress = driver.getOfficeAddress();
		this.email = driver.getEmail();
		this.personalAddress = driver.getPersonalAddress();
        this.corporationId = driver.getCorporationId();
        this.inclusionDate = driver.getInclusionDate();
        this.phoneNumber = driver.getPhoneNumber();
        this.exclusionDate = driver.getExclusionDate();
        this.licenseNumber = driver.getLicenseNumber();
	}
	
    /**
     * @return Returns the pk.
     */
    public DriverLogPK getPk() {
        return pk;
    }
    
    /**
     * @return Returns the action.
     */
    public Integer getAction() {
        return action;
    }
    
    /**
     * @return Returns the comment.
     */
    public String getComment() {
        return comment;
    }
    
    /**
	 * @return Returns the birthDate.
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @return Returns the corporation.
	 */
	public Integer getCorporation() {
		return corporation;
	}
	
	/**
	 * @return Returns the country.
	 */
	public Integer getCountry() {
		return country;
	}
	
	/**
     * @return Returns the geoLevel1.
     */
    public Integer getGeoLevel1() {
        return geoLevel1;
    }
    
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @return Returns the licenseCategory.
	 */
	public String getLicenseCategory() {
		return licenseCategory;
	}
	
	/**
	 * @return Returns the licenseExpiry.
	 */
	public Date getLicenseExpiry() {
		return licenseExpiry;
	}
	
	/**
	 * @return Returns the licenseGet.
	 */
	public Date getLicenseGet() {
		return licenseGet;
	}
	
	/**
	 * @return Returns the nationality.
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * @return Returns the officeAddress.
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}
	
	/**
	 * @return Returns the personalAddress.
	 */
	public String getPersonalAddress() {
		return personalAddress;
	}
	
	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * @return Returns the vehicleAllowed.
	 */
	public String getVehicleAllowed() {
		return vehicleAllowed;
	}

	/**
     * @param pk The pk to set.
     */
    public void setPk(DriverLogPK pk) {
        this.pk = pk;
    }

    /**
     * @param action The action to set.
     */
    public void setAction(Integer action) {
        this.action = action;
    }
    
    /**
     * @param comment The comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /**
	 * @param birthDate The birthDate to set.
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
    
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
    
	/**
	 * @param corporation The corporation to set.
	 */
	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}
	
	/**
	 * @param country The country to set.
	 */
	public void setCountry(Integer country) {
		this.country = country;
	}
	
	/**
     * @param geoLevel1 The geoLevel1 to set.
     */
    public void setGeoLevel1(Integer geoLevel1) {
        this.geoLevel1 = geoLevel1;
    }
    
	/**
	 * @return Returns the divOrg1.
	 */
	public Integer getDivOrg1() {
		return divOrg1;
	}

	/**
	 * @param divOrg1 The divOrg1 to set.
	 */
	public void setDivOrg1(Integer divOrg1) {
		this.divOrg1 = divOrg1;
	}

	/**
	 * @return Returns the divOrg2.
	 */
	public Integer getDivOrg2() {
		return divOrg2;
	}

	/**
	 * @param divOrg2 The divOrg2 to set.
	 */
	public void setDivOrg2(Integer divOrg2) {
		this.divOrg2 = divOrg2;
	}

	/**
	 * @return Returns the divOrg3.
	 */
	public Integer getDivOrg3() {
		return divOrg3;
	}

	/**
	 * @param divOrg3 The divOrg3 to set.
	 */
	public void setDivOrg3(Integer divOrg3) {
		this.divOrg3 = divOrg3;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
    
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
	/**
	 * @param licenseCategory The licenseCategory to set.
	 */
	public void setLicenseCategory(String licenseCategory) {
		this.licenseCategory = licenseCategory;
	}
    
	/**
	 * @param licenseExpiry The licenseExpiry to set.
	 */
	public void setLicenseExpiry(Date licenseExpiry) {
		this.licenseExpiry = licenseExpiry;
	}
    
	/**
	 * @param licenseGet The licenseGet to set.
	 */
	public void setLicenseGet(Date licenseGet) {
		this.licenseGet = licenseGet;
	}
    
	/**
	 * @param nationality The nationality to set.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
    
	/**
	 * @param officeAddress The officeAddress to set.
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
    
	/**
	 * @param personalAddress The personalAddress to set.
	 */
	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}
    
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @param vehicleAllowed The vehicleAllowed to set.
	 */
	public void setVehicleAllowed(String vehicleAllowed) {
		this.vehicleAllowed = vehicleAllowed;
	}

    /**
     * @return Returns the corporationId.
     */
    public String getCorporationId() {
        return corporationId;
    }

    /**
     * @return Returns the exclusionDate.
     */
    public Date getExclusionDate() {
        return exclusionDate;
    }

    /**
     * @return Returns the inclusionDate.
     */
    public Date getInclusionDate() {
        return inclusionDate;
    }

    /**
     * @return Returns the licenseNumber.
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * @return Returns the phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param corporationId The corporationId to set.
     */
    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    /**
     * @param exclusionDate The exclusionDate to set.
     */
    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
    }

    /**
     * @param inclusionDate The inclusionDate to set.
     */
    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    /**
     * @param licenseNumber The licenseNumber to set.
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * @param phoneNumber The phoneNumber to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ObservationData getDriverObservations() {
        return new ObservationData(pk.getDriver(),pk.getDate(),pk.getUserId(),action,comment);
    }

    
}
