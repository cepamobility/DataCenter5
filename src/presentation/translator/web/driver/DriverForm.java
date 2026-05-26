package presentation.translator.web.driver;


import java.util.Calendar;
import java.util.Date;

import presentation.translator.web.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
/**
 * @author jrodriguez
 */
public class DriverForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String birthMonth;
	private String birthYear;
    private String sex;
    private String nationality;
    private String corporation;
    
    private String city;
    private String licenseExpiryDate;
    private String licenseExpiryMonth;
    private String licenseExpiryYear;
	private String licenseCategory;
	private String licenseObs;
	private String licenseGetDate;
	private String licenseGetMonth;
	private String licenseGetYear;

	private String vehicleAllowed;
	private String officeAddress;
	private String email;
	private String personalAddress;
	
	private String stateOrRegion;

    private String corporationId;
    private String phoneNumber;
    private String licenseNumber;

    private String startDate;
    private String startMonth;
    private String startYear;

    private String drives;
    private String employee;
    private String contract;

    private String comment;

    private String bpsregistry;
      
    private String licenseNotExpiry;

    private String healthExpiryDate;
    private String healthExpiryMonth;
    private String healthExpiryYear;

    private String points;
    
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private String option7;

	private String assignedVehicle;

	private String superviser;

	private String manager;
	
	private String maritalStatus;
	private String educationLevel;
	private String puestoPepsico;
	
	private String licenseCoverage; // Pepsico, "cobertura de la licencia"
	private String federativeEntity; // Pepsico, "Entidad Federativa"
	private String authorizateWho; // Pepsico, "Especificar quien autoriza la alta y la fecha en que fue solicitada" 
	private String authorizateDateDay;  // Pepsico, "Especificar quien autoriza la alta y la fecha en que fue solicitada"
	private String authorizateDateMonth;
	private String authorizateDateYear;
	
	private String idDallas; // telematic
	private String all;
	private String pageNum;
	private String pageSize;
	private String count;
	private String noHrd;
	private String noHrdComment;
	
	private String temporal;
	private String director;

	
    public DriverForm() {
        super();
        drives = "true";
        employee = "true";
        director = "false";
        licenseNotExpiry = "1";
    }
    
    
    
    public String getNoHrd() {
		return noHrd;
	}



	public void setNoHrd(String noHrd) {
		this.noHrd = noHrd;
	}



	public String getNoHrdComment() {
		return noHrdComment;
	}



	public void setNoHrdComment(String noHrdComment) {
		this.noHrdComment = noHrdComment;
	}



	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the birthDate.
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate The birthDate to set.
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the corporation.
	 */
	public String getCorporation() {
		return corporation;
	}
	/**
	 * @param corporation The corporation to set.
	 */
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the licenseCategory.
	 */
	public String getLicenseCategory() {
		return licenseCategory;
	}
	/**
	 * @param licenseCategory The licenseCategory to set.
	 */
	public void setLicenseCategory(String licenseCategory) {
		this.licenseCategory = licenseCategory;
	}
	/**
	 * @return Returns the licenseExpiryDate.
	 */
	public String getLicenseExpiryDate() {
		return licenseExpiryDate;
	}
	/**
	 * @param licenseExpiryDate The licenseExpiryDate to set.
	 */
	public void setLicenseExpiryDate(String licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
	}
	/**
	 * @return Returns the licenseExpiryMonth.
	 */
	public String getLicenseExpiryMonth() {
		return licenseExpiryMonth;
	}
	/**
	 * @param licenseExpiryMonth The licenseExpiryMonth to set.
	 */
	public void setLicenseExpiryMonth(String licenseExpiryMonth) {
		this.licenseExpiryMonth = licenseExpiryMonth;
	}
	/**
	 * @return Returns the licenseExpiryYear.
	 */
	public String getLicenseExpiryYear() {
		return licenseExpiryYear;
	}
	/**
	 * @param licenseExpiryYear The licenseExpiryYear to set.
	 */
	public void setLicenseExpiryYear(String licenseExpiryYear) {
		this.licenseExpiryYear = licenseExpiryYear;
	}
	/**
	 * @return Returns the licenseGetDate.
	 */
	public String getLicenseGetDate() {
		return licenseGetDate;
	}
	/**
	 * @param licenseGetDate The licenseGetDate to set.
	 */
	public void setLicenseGetDate(String licenseGetDate) {
		this.licenseGetDate = licenseGetDate;
	}
	/**
	 * @return Returns the licenseGetMonth.
	 */
	public String getLicenseGetMonth() {
		return licenseGetMonth;
	}
	/**
	 * @param licenseGetMonth The licenseGetMonth to set.
	 */
	public void setLicenseGetMonth(String licenseGetMonth) {
		this.licenseGetMonth = licenseGetMonth;
	}
	/**
	 * @return Returns the licenseGetYear.
	 */
	public String getLicenseGetYear() {
		return licenseGetYear;
	}
	/**
	 * @param licenseGetYear The licenseGetYear to set.
	 */
	public void setLicenseGetYear(String licenseGetYear) {
		this.licenseGetYear = licenseGetYear;
	}
	/**
	 * @return Returns the nationality.
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality The nationality to set.
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return Returns the officeAddress.
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}
	/**
	 * @param officeAddress The officeAddress to set.
	 */
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	/**
	 * @return Returns the personalAddress.
	 */
	public String getPersonalAddress() {
		return personalAddress;
	}
    
    
	/**
     * @return Returns the corporationId.
     */
    public String getCorporationId() {
        return corporationId;
    }
    /**
	 * @param personalAddress The personalAddress to set.
	 */
	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}
    
	/**
	 * @return Returns the sex.
	 */
	public String getSex() {
		return sex;
	}
    
    /**
     * @return Returns the comment.
     */
    public String getComment() {
        return comment;
    }
    
	/**
	 * @param sex The sex to set.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return Returns the vehicleAllowed.
	 */
	public String getVehicleAllowed() {
		return vehicleAllowed;
	}
	/**
	 * @param vehicleAllowed The vehicleAllowed to set.
	 */
	public void setVehicleAllowed(String vehicleAllowed) {
		this.vehicleAllowed = vehicleAllowed;
	}
	/**
	 * @return Returns the birthMonth.
	 */
	public String getBirthMonth() {
		return birthMonth;
	}
	/**
	 * @param birthMonth The birthMonth to set.
	 */
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	/**
	 * @return Returns the birthYear.
	 */
	public String getBirthYear() {
		return birthYear;
	}
	/**
	 * @param birthYear The birthYear to set.
	 */
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
    
    public Date getBirth() {
        return getDate(birthDate,birthMonth,birthYear);
    }
    
    public Date getLicenseGet() {
        return getDate(licenseGetDate,licenseGetMonth,licenseGetYear);
    }
    
    public Date getLicenseExpiry() {
        return getDate(licenseExpiryDate,licenseExpiryMonth,licenseExpiryYear);
    }
    
    public Date getAuthorizateDate() {
        return getDate(authorizateDateDay,authorizateDateMonth,authorizateDateYear);
    }
    
    public Date getInclusionDate() {
        return getDate(startDate,startMonth,startYear);
    }
    
    public Date getDate(String date,String month,String year) {
        if(isEmpty(date) || isEmpty(month) || isEmpty(year)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,Integer.parseInt(date.trim()));
        calendar.set(Calendar.MONTH,Integer.parseInt(month.trim())-1);
        calendar.set(Calendar.YEAR,Integer.parseInt(year.trim()));

        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    public String validate(UserSessionData userRequest) {

	    if(isEmpty(getFirstName())) {
	        return "generic.driver.driverdata.firstNameInvalid";
	    }
	    if(isEmpty(getLastName())) {
	        return "generic.driver.driverdata.lastNameInvalid";
	    }
        try{
            Date birth = getBirth();
             if(birth == null) {
                return "generic.driver.driverdata.birthDateInvalid";
            }
        }catch(Exception e) {
            return "generic.driver.driverdata.birthDateInvalid";    
        }
        try{
            int day = Integer.parseInt(getBirthDate());
            int month= Integer.parseInt(getBirthMonth());
            int year =Integer.parseInt(getBirthYear());
            boolean checkBirth = Util.checkDate(day,month,year);
            if(checkBirth == false) {
                return "generic.driver.driverdata.birthDateInvalid";
            }
        }catch(Exception e) {
            return "generic.driver.driverdata.birthDateInvalid";    
        }
        
        if(userRequest.getCorporationId() == CorporationData.ALLERGAN) {
            try{
                int day = Integer.parseInt(getLicenseExpiryDate());
                int month= Integer.parseInt(getLicenseExpiryMonth());
                int year =Integer.parseInt(getLicenseExpiryYear());
                boolean checkBirth = Util.checkDate(day,month,year);
                if(checkBirth == false) {
                    return "driver.driverdata.licenseexpirydateinvalid";
                }
            }catch(Exception e) {
                return "driver.driverdata.licenseexpirydateinvalid";    
            }
        }
        
        // Authorizate Date, PEPSICO:
        try{
        	if (getAuthorizateDateDay() != null && getAuthorizateDateMonth() != null &&
        			getAuthorizateDateYear() != null && getAuthorizateDateDay().trim() != "" &&
        			getAuthorizateDateMonth().trim() != "" && getAuthorizateDateYear().trim() != "")
        	{
        		int day = Integer.parseInt(getAuthorizateDateDay());
        		int month= Integer.parseInt(getAuthorizateDateMonth());
        		int year =Integer.parseInt(getAuthorizateDateYear());
        		boolean checkAuthorizateDate = Util.checkDate(day,month,year);
        		if(checkAuthorizateDate == false) {
        			return "generic.driver.driverdata.autorizateDateInvalid";
        		}
            }
        }catch(Exception e) {
            return "generic.driver.driverdata.autorizateDateInvalid";    
        }
     
	    if(isEmpty(getSex())) {
	        return "generic.driver.driverdata.sexInvalid";
	    }
        for(int j = 0; j < userRequest.getFilter().getDivOrgCount(); j++) {
            for(int i = 1; i <= userRequest.getFilter().getDivOrgLevel(j); i++) {
                if(Util.empty(getDivOrg(j+1,i))) {
                    return "generic.driver.driverdata.emptyDivOrg";
                }
            }
        }
        if(Integer.parseInt(getCountry2()) == CountryData.URUGUAY_ID.intValue()) {
        	String ci = isValidCI();
        	if(ci != null)
        		return ci; 

        }
        
        if(isEmpty(getCountry1())) {
            return "generic.driver.driverdata.emptyDivGeo";
        }
        if(userRequest.getFilter().getGeoLevelCount() > 1 && isEmpty(getCountry2())) {
            return "generic.driver.driverdata.emptyDivGeo";
        }
        if(userRequest.getFilter().getGeoLevelCount() > 2 && isEmpty(getCountry3())) {
            return "generic.driver.driverdata.emptyDivGeo";
        }
       if(!isEmpty(getEmail())) {
	        try{
	           
	           boolean checkEmail= Util.checkEmail(getEmail());
	           if(checkEmail == false){
	        	   return "generic.driver.driverdata.invalidEmail"; 
	           }
	          }catch(Exception e) {
	        	return "generic.driver.driverdata.invalidEmail";
	        }
      
        }
       
      
	    return null;
	}

    private String isValidCI() {
		if(corporationId == null || corporationId.trim().length() == 0) {
			return "messages.error.emptyuruguayci";
		} else {
			for(int i = 0; i < corporationId.length(); i++) {
				if(!Character.isDigit(corporationId.charAt(i))) {
					return "messages.error.wronguruguayci";
				}
			}
		}
		return null;
	}



	private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}
    /**
     * @return
     */
    public String getLC() {
        return licenseCategory;
    }
    
    public String getDrives() {
        return drives;
    }
    
    public String getEmployee() {
        return employee;
    }

    public String getContract() {
        return contract;
    }

    public String getBpsregistry() {
        return bpsregistry;
    }

    /**
     * @param corporationId The corporationId to set.
     */
    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }
    
    /**
     * @return Returns the phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * @param phoneNumber The phoneNumber to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * @return Returns the licenseNumber.
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }
    /**
     * @param licenseNumber The licenseNumber to set.
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    /**
     * @return Returns the startDate.
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * @return Returns the startMonth.
     */
    public String getStartMonth() {
        return startMonth;
    }
    /**
     * @return Returns the startYear.
     */
    public String getStartYear() {
        return startYear;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * @param startMonth The startMonth to set.
     */
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }
    
    /**
     * @param startYear The startYear to set.
     */
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }
    
    /**
     * @param comment The comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * @param contract The contract to set.
     */
    public void setContract(String contract) {
        this.contract = contract;
    }
    /**
     * @param drives The drives to set.
     */
    public void setDrives(String drives) {
        this.drives = drives;
    }
    /**
     * @param employee The employee to set.
     */
    public void setEmployee(String employee) {
        this.employee = employee;
    } 
    
    /**
     * @param divOrg11
     * @param divOrg12
     * @param divOrg13
     * @param divOrg21
     * @param divOrg22
     * @param divOrg23
     * @param divOrg31
     * @param divOrg32
     * @param divOrg33
     * @param country1
     * @param country2
     * @param country3
     */
    public DriverForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }
    
	/**
	 * @return Returns the country1.
	 */
	public String getCountry1() {
		return country1;
	}
	/**
	 * @param country1 The country1 to set.
	 */
	public void setCountry1(String country1) {
		this.country1 = country1;
	}
	/**
	 * @return Returns the country2.
	 */
	public String getCountry2() {
		return country2;
	}
	/**
	 * @param country2 The country2 to set.
	 */
	public void setCountry2(String country2) {
		this.country2 = country2;
	}
	/**
	 * @return Returns the country3.
	 */
	public String getCountry3() {
		return country3;
	}
	/**
	 * @param country3 The country3 to set.
	 */
	public void setCountry3(String country3) {
		this.country3 = country3;
	}
    
    public void setBpsregistry(String bpsregistry) {
        this.bpsregistry = bpsregistry;
    }
	/**
	 * @return the licenseNotExpiry
	 */
	public String getLicenseNotExpiry() {
		return licenseNotExpiry;
	}
	
	/**
	 * @param licenseNotExpiry the licenseNotExpiry to set
	 */
	public void setLicenseNotExpiry(String licenseNotExpiry) {
		this.licenseNotExpiry = licenseNotExpiry;
	}
	
	public Date getHealth() {
		return getDate(healthExpiryDate,healthExpiryMonth,healthExpiryYear);
	}
    
	public void setHealth(Date health) {
		if(health != null) {
			Calendar healthDate = Calendar.getInstance();
			healthDate.setTime(health);
			healthExpiryDate = String.valueOf(healthDate.get(Calendar.DATE));
			healthExpiryMonth = String.valueOf(healthDate.get(Calendar.MONTH)+1);
			healthExpiryYear = String.valueOf(healthDate.get(Calendar.YEAR));
		}
	}

	public String getHealthExpiryDate() {
		return healthExpiryDate;
	}
	
	public void setHealthExpiryDate(String healthExpiryDate) {
		this.healthExpiryDate = healthExpiryDate;
	}
	
	public String getHealthExpiryMonth() {
		return healthExpiryMonth;
	}
	
	public void setHealthExpiryMonth(String healthExpiryMonth) {
		this.healthExpiryMonth = healthExpiryMonth;
	}
	
	public String getHealthExpiryYear() {
		return healthExpiryYear;
	}
	
	public void setHealthExpiryYear(String healthExpiryYear) {
		this.healthExpiryYear = healthExpiryYear;
	}
	
	public String getPoints() {
		return points;
	}
	
	public void setPoints(String points) {
		this.points = points;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getOption5() {
		return option5;
	}
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	public String getOption6() {
		return option6;
	}
	public void setOption6(String option6) {
		this.option6 = option6;
	}
	public String getOption7() {
		return option7;
	}
	public void setOption7(String option7) {
		this.option7 = option7;
	}
	
	public AssignedVehicleDataType getAssignedVehicleData() {
		return (Util.empty(assignedVehicle)) ? null : 
			new AssignedVehicleDataType(assignedVehicle);
	}
	public String getAssignedVehicle() {
		return assignedVehicle;
	}
	public void setAssignedVehicle(String assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
	}
	public String getSuperviser() {
		return superviser;
	}
	public String getManager() {
		return manager;
	}
	public void setSuperviser(String superviser) {
		this.superviser = superviser;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the educationLevel
	 */
	public String getEducationLevel() {
		return educationLevel;
	}
	/**
	 * @param educationLevel the educationLevel to set
	 */
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	/**
	 * @return the puestoPepsico
	 */
	public String getPuestoPepsico() {
		return puestoPepsico;
	}
	/**
	 * @param puestoPepsico the puestoPepsico to set
	 */
	public void setPuestoPepsico(String puestoPepsico) {
		this.puestoPepsico = puestoPepsico;
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
	/**
	 * @return the authorizateWho
	 */
	public String getAuthorizateWho() {
		return authorizateWho;
	}
	/**
	 * @param authorizateWho the authorizateWho to set
	 */
	public void setAuthorizateWho(String authorizateWho) {
		this.authorizateWho = authorizateWho;
	}
	/**
	 * @return the authorizateDateDay
	 */
	public String getAuthorizateDateDay() {
		return authorizateDateDay;
	}
	/**
	 * @param authorizateDateDay the authorizateDateDay to set
	 */
	public void setAuthorizateDateDay(String authorizateDateDay) {
		this.authorizateDateDay = authorizateDateDay;
	}
	/**
	 * @return the authorizateDateMonth
	 */
	public String getAuthorizateDateMonth() {
		return authorizateDateMonth;
	}
	/**
	 * @param authorizateDateMonth the authorizateDateMonth to set
	 */
	public void setAuthorizateDateMonth(String authorizateDateMonth) {
		this.authorizateDateMonth = authorizateDateMonth;
	}
	/**
	 * @return the authorizateDateYear
	 */
	public String getAuthorizateDateYear() {
		return authorizateDateYear;
	}
	/**
	 * @param authorizateDateYear the authorizateDateYear to set
	 */
	public void setAuthorizateDateYear(String authorizateDateYear) {
		this.authorizateDateYear = authorizateDateYear;
	}
	
	public String getIdDallas() {
		return (idDallas != null) ? idDallas.toUpperCase(): null;
	}
	public void setIdDallas(String idDallas) {
		this.idDallas = idDallas;
	}
	/**
	 * @return the temporal
	 */
	public String getTemporal() {
		return (temporal == null) ? "false": temporal;
	}
	/**
	 * @param temporal the temporal to set
	 */
	public void setTemporal(String temporal) {
		this.temporal = temporal;
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
	
	
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getLicenseObs() {
		return licenseObs;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
}
