package persistence.driver.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.Country;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.AssignedVehicle;
import persistence.datatype.tables.Contract;
import persistence.datatype.tables.EducationLevel;
import persistence.datatype.tables.MaritalStatus;
import persistence.datatype.tables.PuestoPepsico;
import persistence.datatype.tables.Sex;
import persistence.datatype.tables.TypeVehicle;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.datatype.tables.ContractDataType;
import communication.datatype.tables.EducationLevelDataType;
import communication.datatype.tables.MaritalStatusDataType;
import communication.datatype.tables.PuestoPepsicoDataType;
import communication.datatype.tables.SexDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.driver.report.DetailedDriverReportDataType;
import communication.driver.report.DetailedDriverReportWithVehiclesDataType;
import communication.driver.report.DetailedFOSADriverReportDataType;
import communication.driver.report.DriverReportDataType;
import communication.driver.report.FOSADriverReportDataType;
import communication.driver.tables.AlcoholControlData;
import communication.driver.tables.AptitudTestData;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.DriverPrizeData;

/**
 * @author jrodriguez
 */
public class Driver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private Date birthDate;
    private Sex sex;
    private String nationality;
    private String city;
    
    private String stateOrRegion; // estado o localidad donde se ubica la ciudad (ubicacion del conductor)
	
    private String personalAddress;
    private MaritalStatus maritalStatus; // Etado Civil
    private EducationLevel educationLevel; // Nivel de Estudios
    
    private CorporationCountry corpCountry;
    private GeoLevel1 geoLevel1; 
    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;

    private String licenseNumber;
    private Date licenseExpiry;
	private String licenseCategory;
    private Date licenseGet;
    private String licenseObs;
    
    private String licenseCoverage; // Pepsico, "cobertura de la licencia", Allergan: CPF
    private String federativeEntity; // Pepsico, "Entidad Federativa", Allergan: RG
    private String authorizateWho; // Pepsico, "Especificar quien autoriza la alta 
    private Date authorizateDate; // Pepsico "y la fecha en que fue solicitada la alta"
    
    
    private TypeVehicle vehicleAllowed;
	
    private String corporationId;
    private String email;
    private String phoneNumber;
    private String officeAddress;
    private Date inclusionDate;
    private Date exclusionDate;

    private boolean drives;
    private boolean employee;
    private Contract contract;

    private String bpsregistry;
    private Date healthExpiry;
    
    private Integer points;
    
    private String superviser;
    private String manager;
    
    private Set aptitudTestSet;
    private Set alcoholControlSet;
    private Set prizeSet;
    
    private boolean licenseNotExpiry; // No tiene Vencimiento la libreta de conducir
    
    private Date alertDate;
    private AssignedVehicle assignedVehicle;
    
    private PuestoPepsico puesto; // Pepsico
    private String idDallas; // telematica: Monsanto, Anglo, etc...
    
    /**
     * Conductor temporal creado desde la tablet:
     */
    protected boolean temporal;
    protected boolean createdFromTablet; 
    
    protected boolean director; 
    //Byer
    
    private boolean noHrd;
    private String noHrdComment;
    
    public Driver() {
		
	}
	
	/**
     * @param id
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param sex
     * @param nationality
     * @param city
     * @param personalAddress
     * @param corpCountry
     * @param geoLevel1
     * @param divOrg1
     * @param divOrg2
     * @param divOrg3
     * @param licenseNumber
     * @param licenseExpiry
     * @param licenseCategory
     * @param licenseGet
     * @param vehicleAllowed
     * @param corporationId
     * @param email
     * @param phoneNumber
     * @param officeAddress
     * @param inclusionDate
     * @param exclusionDate
     */
    public Driver(Integer id, String firstName, String lastName, Date birthDate, 
    		Sex sex, String nationality, String city, String personalAddress, 
    		CorporationCountry corpCountry, GeoLevel1 geoLevel1, 
    		DivOrgItemLevel1 divOrg1, DivOrgItemLevel1 divOrg2, 
    		DivOrgItemLevel1 divOrg3, String licenseNumber, Date licenseExpiry, 
    		String licenseCategory, String licenseObs, Date licenseGet, TypeVehicle vehicleAllowed, 
    		String corporationId, String email, String phoneNumber, 
    		String officeAddress, Date inclusionDate, Date exclusionDate, 
    		boolean drives, boolean employee, Contract contract, String bpsregistry,Date healthExpiry, Integer points, 
    		Set aptitudTestSet, Set alcoholControlSet, Set prizeSet, boolean licenseNotExpiry,
    		String superviser,String manager, MaritalStatus maritalStatus, 
    		EducationLevel educationLevel, PuestoPepsico puesto,
    		String licenseCoverage, String federativeEntity, String  authorizateWho,
    		Date authorizateDate, String idDallas,Boolean noHrd,String noHrdComment, boolean director) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.nationality = nationality;
        this.city = city;
        this.personalAddress = personalAddress;
        this.corpCountry = corpCountry;
        this.geoLevel1 = geoLevel1;
        this.divOrg1 = divOrg1;
        this.divOrg2 = divOrg2;
        this.divOrg3 = divOrg3;
        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
        this.licenseCategory = licenseCategory;
        this.licenseObs = licenseObs;
        this.licenseGet = licenseGet;
        this.vehicleAllowed = vehicleAllowed;
        this.corporationId = corporationId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.officeAddress = officeAddress;
        this.inclusionDate = inclusionDate;
        this.exclusionDate = exclusionDate;
        this.drives = drives;
        this.employee = employee;
        this.contract = contract;
        this.bpsregistry = bpsregistry;
        this.aptitudTestSet = aptitudTestSet;
        this.alcoholControlSet = alcoholControlSet;
        this.prizeSet = prizeSet;
        this.licenseNotExpiry = licenseNotExpiry;
        this.healthExpiry = healthExpiry;
        this.points = points;
        this.superviser = superviser;
        this.manager = manager;
        this.maritalStatus = maritalStatus;
        this.educationLevel = educationLevel;
        this.puesto = puesto;
        this.licenseCoverage = licenseCoverage;
        this.federativeEntity = federativeEntity;
        this.authorizateWho = authorizateWho;
        this.authorizateDate = authorizateDate;
        this.idDallas = idDallas;
        this.noHrd=noHrd;
        this.noHrdComment=noHrdComment;
        this.director = director;
    }


    /**
	 * @param data
	 */
	public Driver(DriverData data) {
		
        id = data.getId();
		firstName = data.getFirstName();
		lastName = data.getLastName();
		birthDate = data.getBirthDate();
	    nationality = data.getNationality();
        city = data.getCity();
        personalAddress = data.getPersonalAddress();

	    Session session = HibernateUtil.currentSession();
		sex = (Sex)session.load(Sex.class,data.getSex().getResourceBundleId());
	    
	    GeoItemData itemData1 = (GeoItemData)data.getCountry().getItemIterator().next();
	    GeoItemData itemData2 = null; 
	    while(itemData1.getSubItems() != null) {
	        itemData2 = itemData1;
	        itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
	    }
	    
	    Iterator it = data.getDivOrgsIterator();
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }
	    if(it.hasNext()) {
	        DivOrgData divOrg = (DivOrgData)it.next();
	        ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	        while(item1.getSubItems() != null) {
	            item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	        }
	        divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	    }
	    
        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),data.getCorporation().getId()));
        if(itemData2 != null) {
            geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
        }
        
        licenseNumber = data.getLicenseNumber();
        licenseExpiry = data.getLicenseExpiry();
        licenseCategory = data.getLicenseCategory();
        licenseObs = data.getLicenseObs();
        licenseGet = data.getLicenseGet();
        if(data.getVehicleAllowed() != null) {
            vehicleAllowed = (TypeVehicle)session.load(TypeVehicle.class,data.getVehicleAllowed().getResourceBundleId());
        }
        corporationId = data.getCorporationId();
        email = data.getEmail();
        phoneNumber = data.getPhoneNumber();
        officeAddress = data.getOfficeAddress();
        stateOrRegion = data.getStateOrRegion();
        inclusionDate = data.getInclusionDate();
        exclusionDate = data.getExclusionDate();
        drives = data.isDrives();
        employee = data.isEmployee();
        if(data.getContract() != null) {
            contract = (Contract)session.load(Contract.class,data.getContract().getResourceBundleId());
        }
        if(!Util.empty(data.getBpsregistry())) {
            this.bpsregistry = data.getBpsregistry();
        }
        this.healthExpiry = data.getHealthExpiry();
        this.points = data.getPoints();
        if(data.getAptitudTest() != null) {
            Iterator itAptitud = data.getAptitudTest().iterator();
            while(itAptitud.hasNext()) {
                AptitudTest aptitudTest = (AptitudTest)session.load(AptitudTest.class,new AptitudTestPK(((AptitudTestData)itAptitud.next()).getDate(),this));
                aptitudTestSet.add(aptitudTest);
            }
        }
        if(data.getAlcoholControl() != null) {
            Iterator itControl = data.getAlcoholControl().iterator();
            while(itControl.hasNext()) {
                AlcoholControl alcoholControl = (AlcoholControl)session.load(AlcoholControl.class,new AptitudTestPK(((AlcoholControlData)itControl.next()).getDate(),this));
                alcoholControlSet.add(alcoholControl);
            }
        }
        if(data.getPrizes() != null) {
            Iterator itPrizes = data.getPrizes().iterator();
            while(itPrizes.hasNext()) {
                DriverPrize prize = (DriverPrize)session.load(DriverPrize.class,new DriverPrizePK(((DriverPrizeData)itPrizes.next()).getDate(),this));
                prizeSet.add(prize);
            }
        }
        this.licenseNotExpiry = data.isLicenseNotExpiry();
        
        this.alertDate = data.getAlertDate();
        this.assignedVehicle = (data.getAssignedVehicle() == null) ? null : (AssignedVehicle) session.load(AssignedVehicle.class, data.getAssignedVehicle().getResourceBundleId());
        
        this.superviser = data.getSuperviser();
        this.manager = data.getManager();
        
        
        /* Campos para CIMSA */
        this.educationLevel  = 
        		(data.getEducationLevel() != null && data.getEducationLevel().getResourceBundleId() != null &&
        				data.getEducationLevel().getResourceBundleId().trim() != "")?
        						(EducationLevel)session.load(EducationLevel.class,
        								data.getEducationLevel().getResourceBundleId().trim()):null;
        this.maritalStatus  = 
        		(data.getMaritalStatus() != null && data.getMaritalStatus().getResourceBundleId() != null &&
        				data.getMaritalStatus().getResourceBundleId().trim() != "")?
        						(MaritalStatus)session.load(MaritalStatus.class,
        								data.getMaritalStatus().getResourceBundleId()):null;
       
        /* Campos para Pepsico */
        this.puesto = 
        		(data.getPuesto() != null && data.getPuesto().getResourceBundleId() != null &&
        				data.getPuesto().getResourceBundleId().trim() != "") ?
        						(PuestoPepsico)session.load(PuestoPepsico.class,
        								data.getPuesto().getResourceBundleId()):null;
        						
		this.licenseCoverage = data.getLicenseCoverage();
        this.federativeEntity = data.getFederativeEntity();
        this.authorizateWho = data.getAuthorizateWho();
        this.authorizateDate = data.getAuthorizateDate();
        
        // Telematic
        this.idDallas = data.getIdDallas();
        
        //Bayer
        this.noHrd=data.isNoHrd();
        this.noHrdComment=data.getNoHrdComment();
        this.director = data.isDirector();
	}

	
	
	public boolean isNoHrd() {
		return noHrd;
	}

	public void setNoHrd(boolean noHrd) {
		this.noHrd = noHrd;
	}

	public String getNoHrdComment() {
		return noHrdComment;
	}

	public void setNoHrdComment(String noHrdComment) {
		this.noHrdComment = noHrdComment;
	}

	/**
	 * @return Returs the id
	 */
	public Integer getId() {
		return id;
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
	public Corporation getCorporation() {
		return corpCountry.getPk().getCorporation();
	}
	
	/**
	 * @return Returns the country.
	 */
	public CorporationCountry getCorpCountry() {
		return corpCountry;
	}
	
	public Country getCountry() {
		return corpCountry.getPk().getCountry();
	}

	/**
     * @return Returns the geoLevel1.
     */
    public GeoLevel1 getGeoLevel1() {
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
	public Sex getSex() {
		return sex;
	}
	
	/**
	 * @return Returns the vehicleAllowed.
	 */
	public TypeVehicle getVehicleAllowed() {
		return vehicleAllowed;
	}

	/**
     * @return Returns the corporationId.
     */
    public String getCorporationId() {
        return corporationId;
    }

    /**
     * @return Returns the divOrg1.
     */
    public DivOrgItemLevel1 getDivOrg1() {
        return divOrg1;
    }

    /**
     * @return Returns the divOrg2.
     */
    public DivOrgItemLevel1 getDivOrg2() {
        return divOrg2;
    }

    /**
     * @return Returns the divOrg3.
     */
    public DivOrgItemLevel1 getDivOrg3() {
        return divOrg3;
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
     * @return Returns the contract.
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * @return Returns the drives.
     */
    public boolean isDrives() {
        return drives;
    }

    /**
     * @return Returns the employee.
     */
    public boolean isEmployee() {
        return employee;
    }

    public String getBpsregistry() {
        return bpsregistry;
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
	 * @param country The country to set.
	 */
	public void setCorpCountry(CorporationCountry country) {
		this.corpCountry = country;
	}
	
	/**
     * @param geoLevel1 The geoLevel1 to set.
     */
    public void setGeoLevel1(GeoLevel1 geoLevel1) {
        this.geoLevel1 = geoLevel1;
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
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
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
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/**
	 * @param vehicleAllowed The vehicleAllowed to set.
	 */
	public void setVehicleAllowed(TypeVehicle vehicleAllowed) {
		this.vehicleAllowed = vehicleAllowed;
	}

	/**
     * @param corporationId The corporationId to set.
     */
    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    /**
     * @param divOrg1 The divOrg1 to set.
     */
    public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
        this.divOrg1 = divOrg1;
    }

    /**
     * @param divOrg2 The divOrg2 to set.
     */
    public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
        this.divOrg2 = divOrg2;
    }

    /**
     * @param divOrg3 The divOrg3 to set.
     */
    public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
        this.divOrg3 = divOrg3;
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
    
    /**
     * @param contract The contract to set.
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }

    /**
     * @param drives The drives to set.
     */
    public void setDrives(boolean drives) {
        this.drives = drives;
    }

    /**
     * @param employee The employee to set.
     */
    public void setEmployee(boolean employee) {
        this.employee = employee;
    }


    public void setBpsregistry(String bpsregistry) {
        this.bpsregistry = bpsregistry;
    }

    /**
	 * @return
	 */
	public DriverData getDriverData() {
	    
	    CorporationData corporationData = corpCountry.getPk().getCorporation().getData();
	    
	    GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
	    GeoItemData countryData = (GeoItemData)corpCountry.getData();
	    Collection<GeoItemData> countries = new LinkedList<GeoItemData>();
	    countries.add(countryData);
	    GeoLabelData labelData = null; 
	    if(geoLevel1 != null) {
	        GeoItemData level1 = (GeoItemData)geoLevel1.getData();
		    Collection<GeoItemData> level1s = new LinkedList<GeoItemData>();
		    level1s.add(level1);
	        if(geoLevel1.getGeoLevel2() == null) {
		        level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
	        }else {
	            
		        level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
	            
		        GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
		        level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

		        Collection<GeoItemData> level2s = new LinkedList<GeoItemData>();
			    level2s.add(level2);
			    
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
	        }
	    }else {
	        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
	    }
	    
	    Collection<DivOrgData> divOrgs = new LinkedList<DivOrgData>();
	    if(divOrg1 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg1,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
	    if(divOrg2 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg2,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
	    if(divOrg3 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg3,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
        TypeVehicleDataType typeVehicle = (vehicleAllowed == null) ? null : (TypeVehicleDataType)vehicleAllowed.getData();
        ContractDataType contractData = (contract == null) ? null : (ContractDataType)contract.getData();
        AssignedVehicleDataType assignedVehicleData = (assignedVehicle == null) ? null : (AssignedVehicleDataType)assignedVehicle.getData();
        MaritalStatusDataType maritalStatusData = (maritalStatus == null ?null:(MaritalStatusDataType)maritalStatus.getData());
        EducationLevelDataType educationLevelData = (educationLevel == null)?null:(EducationLevelDataType)educationLevel.getData();
        PuestoPepsicoDataType puestoData = (puesto == null)?null:(PuestoPepsicoDataType)puesto.getData();
        
        DriverData driverData = new DriverData(id,firstName,lastName,labelData,birthDate,city,
        		(SexDataType)sex.getData()
                ,nationality,corpCountry.getPk().getCorporation().getData(),divOrgs,licenseExpiry
                ,licenseCategory,licenseObs,licenseGet,typeVehicle,officeAddress,email,personalAddress,licenseNumber
                ,corporationId,phoneNumber,inclusionDate,exclusionDate,drives,employee,contractData,bpsregistry,
                healthExpiry,this.licenseNotExpiry,points,assignedVehicleData,superviser,manager,
                maritalStatusData,educationLevelData, puestoData, 
                licenseCoverage, federativeEntity,authorizateWho,authorizateDate,idDallas,temporal, stateOrRegion,this.noHrd,this.noHrdComment,director);
        
        Iterator tests = aptitudTestSet.iterator();
        LinkedList<AptitudTestData> aptitudTestList = new LinkedList<AptitudTestData>();
        while(tests.hasNext()) {
            AptitudTest test = (AptitudTest)tests.next();
            aptitudTestList.add(test.getData());
        }        
        Collections.sort(aptitudTestList);
        Collections.reverse(aptitudTestList);
        driverData.getAptitudTest().addAll(aptitudTestList);
        
        Iterator controls = alcoholControlSet.iterator();
        LinkedList<AlcoholControlData> alcoholControlList = new LinkedList<AlcoholControlData>(); 
        while(controls.hasNext()) {
            AlcoholControl control = (AlcoholControl)controls.next();
            alcoholControlList.add(control.getData());
        }
        Collections.sort(alcoholControlList);
        Collections.reverse(alcoholControlList);
        driverData.getAlcoholControl().addAll(alcoholControlList);

        Iterator prizes = prizeSet.iterator();
        while(prizes.hasNext()) {
            DriverPrize prize = (DriverPrize)prizes.next();
            driverData.getPrizes().add(prize.getData());
        }
      
        driverData.setAlertDate(alertDate);
        
        return driverData;
	}

    /**
     * @param level1
     * @return
     */
    private DivOrgData getDivOrgData(DivOrgItemLevel1 level1,CorporationData corporationData) {
        DivOrgData divOrgData = corporationData.getDivOrgData(level1.getDivOrg().getId().intValue());
	    LabelItemAttribute[] labelAttrs = divOrgData.getLabels();
	    ItemData itemData = new ItemData(level1.getAttributes());
	    Collection<ItemData> items1 = new LinkedList<ItemData>();
	    items1.add(itemData);
	    LabelItemData labelData = null; 
	    if(level1.getDivOrgItemLevel2() != null) {
	        ItemData item2 = new ItemData(level1.getDivOrgItemLevel2().getAttributes());
		    Collection<ItemData> items2 = new LinkedList<ItemData>();
		    items2.add(item2);
	        if(level1.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
		        item2.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items1,labelAttrs[1].getShowLevel()));
		        labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items2,labelAttrs[0].getShowLevel());
	        }else {
	            
		        item2.setSubItems(new LabelItemData(labelAttrs[2].getId(),labelAttrs[2].getName(),items1,labelAttrs[2].getShowLevel()));
	            
		        ItemData item3 = new ItemData(level1.getDivOrgItemLevel2().getDivOrgItemLevel3().getAttributes());
		        item3.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items2,labelAttrs[1].getShowLevel()));

		        Collection<ItemData> items3 = new LinkedList<ItemData>();
			    items3.add(item3);
			    
		        labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items3,labelAttrs[0].getShowLevel());
	        }
	    }else {
	        labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items1,labelAttrs[0].getShowLevel());
	    }
	    divOrgData.setDivOrgLevels(labelData);
	    return divOrgData;
    }

    /**
     * @param userRequest 
     * @return
     */
    public DriverMainAttributes getPrincipalAttributes(UserSessionData userRequest) {
	    CorporationData corporationData = corpCountry.getPk().getCorporation().getData();
	    GeoLabelData labelData = null; 
	    GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
	    GeoItemData countryData = (GeoItemData)corpCountry.getData();
	    Collection<GeoItemData> countries = new LinkedList<GeoItemData>();
	    countries.add(countryData);
	    if(geoLevel1 != null) {
	        GeoItemData level1 = (GeoItemData)geoLevel1.getData();
		    Collection<GeoItemData> level1s = new LinkedList<GeoItemData>();
		    level1s.add(level1);
	        if(geoLevel1.getGeoLevel2() == null) {
		        level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
	        }else {
	            
		        level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
	            
		        GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
		        level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

		        Collection<GeoItemData> level2s = new LinkedList<GeoItemData>();
			    level2s.add(level2);
			    
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
	        }
	    }else {
	        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
	    }
        int[][] principal = userRequest.getFilter().getPrincipalDivOrgs();
        String[] st = new String[4]; 
        for(int i = 0; i < 4; i++) {
        	//st[i] = getDivOrg1(i,principal[i],userRequest);
        	st[i] = getDivOrg2(principal[i]);
        }
        return new DriverMainAttributes(id,firstName,lastName,labelData,birthDate,city,exclusionDate,st[0],st[1],st[2],st[3],this.corporationId,this.licenseExpiry);
    }
    
    // para usar con getDivOrg1
    public int getNivel(int index,UserSessionData userSessionData) {
        int nivel = 0;
        int[][] ppalDivOrg = userSessionData.getFilter().getPrincipalDivOrgs();
        int[] data = ppalDivOrg[index];
        int divOrg = data[0];
        int level = data[1];
        if(divOrg > 0) {
            int length = userSessionData.getFilter().getDivOrgLevel(divOrg-1);
            switch(length) {
                case 1:
                	nivel = 3;
                    break;
                case 2:
                    if(level == 1) {
                    	nivel= 2; 
                    }else {
                        nivel = 3; 
                    }
                    break;
                case 3:
                    if(level == 1) {
                    	nivel = 1; 
                    }else if(level == 2) {
                    	nivel = 2; 
                    }else {
                    	nivel = 3; 
                    }
                    break;
            }

        }
        return nivel;
    }
    
    /**
     * 
     * @param index
     * @param data
     * @param userSessionData
     * @return
     */
    @SuppressWarnings("unused")
	private String getDivOrg1(int index,int[] data, UserSessionData userSessionData) {
        if(data[0] == 0) {
            return "";
        }else {
            switch(data[0]) {
                case 1:
                    switch(getNivel(index,userSessionData)) {
                        case 3:
                            return divOrg1.getResourceKey();
                        case 2:
                            return divOrg1.getDivOrgItemLevel2().getResourceKey();
                        case 1:
                            return divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
                case 2:
                    switch(getNivel(index,userSessionData)) {
                        case 3:
                            return divOrg2.getResourceKey();
                        case 2:
                            return divOrg2.getDivOrgItemLevel2().getResourceKey();
                        case 1:
                            return divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
                case 3:
                    switch(getNivel(index,userSessionData)) {
                        case 3:
                            return divOrg3.getResourceKey();
                        case 2:
                            return divOrg3.getDivOrgItemLevel2().getResourceKey();
                        case 1:
                            return divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
            }
        }
        return null;
    }    
  
    /**
     * 
     * @param data
     * @param userSessionData
     * @return
     */
    @SuppressWarnings("unused")
	private String getDivOrg(int[] data, UserSessionData userSessionData) {
        if(data[0] == 0) {
            return "";
        }else {
            switch(data[0]) {
                case 1:
                    switch(getNivel(data[1],userSessionData)) {
                        case 1:
                            return divOrg1.getResourceKey();
                        case 2:
                            return divOrg1.getDivOrgItemLevel2().getResourceKey();
                        case 3:
                            return divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
                case 2:
                    switch(getNivel(data[1],userSessionData)) {
                        case 1:
                            return divOrg2.getResourceKey();
                        case 2:
                            return divOrg2.getDivOrgItemLevel2().getResourceKey();
                        case 3:
                            return divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
                case 3:
                    switch(getNivel(data[1],userSessionData)) {
                        case 1:
                            return divOrg3.getResourceKey();
                        case 2:
                            return divOrg3.getDivOrgItemLevel2().getResourceKey();
                        case 3:
                            return divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
            }
        }
        return null;
    }    
    
    
    private String getDivOrg2(int[] data) {
        if(data[0] == 0) {
            return "";
        }else {
            switch(data[0]) {
                case 1:
                    switch(data[1]) {
                    case 1:
                    	if (cantNiveles(divOrg1) == 1)
                    		return divOrg1.getResourceKey();
                    	if (cantNiveles(divOrg1) == 2)
                    		return divOrg1.getDivOrgItemLevel2().getResourceKey();
                    	if (cantNiveles(divOrg1) == 3)
                    		return divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                        	
                    case 2:
                    	if (cantNiveles(divOrg1) == 2)
                    		return divOrg1.getResourceKey();
                    	if (cantNiveles(divOrg1) == 3)
                    		return divOrg1.getDivOrgItemLevel2().getResourceKey();
                    case 3:
                        return divOrg1.getResourceKey();
                    }
                    return "";
                case 2:
                    switch(data[1]) {
                    case 1:
                    	if (cantNiveles(divOrg2) == 1)
                    		return divOrg2.getResourceKey();
                    	if (cantNiveles(divOrg2) == 2)
                    		return divOrg2.getDivOrgItemLevel2().getResourceKey();
                    	if (cantNiveles(divOrg2) == 3)
                    		return divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                        	
                    case 2:
                    	if (cantNiveles(divOrg2) == 2)
                    		return divOrg2.getResourceKey();
                    	if (cantNiveles(divOrg2) == 3)
                    		return divOrg2.getDivOrgItemLevel2().getResourceKey();
                    case 3:
                        return divOrg2.getResourceKey();
                    }
                    return "";
                case 3:
                    switch(data[1]) {
                    case 1:
                    	if (cantNiveles(divOrg3) == 1)
                    		return divOrg3.getResourceKey();
                    	if (cantNiveles(divOrg3) == 2)
                    		return divOrg1.getDivOrgItemLevel2().getResourceKey();
                    	if (cantNiveles(divOrg3) == 3)
                    		return divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                        	
                    case 2:
                    	if (cantNiveles(divOrg3) == 2)
                    		return divOrg3.getResourceKey();
                    	if (cantNiveles(divOrg3) == 3)
                    		return divOrg3.getDivOrgItemLevel2().getResourceKey();
                    case 3:
                        return divOrg3.getResourceKey();
                    }
                    return "";
            }
        }
        return null;
    }    
    
    // Devuelve la cantidad de niveles de la division que se le pasa como par�metro
    private int cantNiveles(DivOrgItemLevel1 divOrg) {
    	
    	if ((divOrg.getDivOrgItemLevel2() != null) && (divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3() != null)) {
    		return 3;
    	}
    	else 
    		if (divOrg.getDivOrgItemLevel2() != null) {
    			return 2;
    		}
    		else 
    			return 1;
    }
  
/*
    private String getDivOrg(int[] data) {
        if(data[0] == 0) {
            return "";
        }else {
            switch(data[0]) {
                case 1:
                    switch(data[1]) {
                        case 1:
                            return divOrg1.getResourceKey();
                        case 2:
                            return divOrg1.getDivOrgItemLevel2().getResourceKey();
                        case 3:
                            return divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
                case 2:
                    switch(data[1]) {
                        case 1:
                            return divOrg2.getResourceKey();
                        case 2:
                            return divOrg2.getDivOrgItemLevel2().getResourceKey();
                        case 3:
                            return divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
                case 3:
                    switch(data[1]) {
                        case 1:
                            return divOrg3.getResourceKey();
                        case 2:
                            return divOrg3.getDivOrgItemLevel2().getResourceKey();
                        case 3:
                            return divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    }
                    return "";
            }
        }
        return null;
    }
*/
    // habría que modificar el de arriba pero hasta analizar el impacto se creo otro método para cambiar el órden de las consultas
    /**
     * 
     */
    @SuppressWarnings("unused")
	private String getDivOrgTemp(int[] data) {
        if(data[0] == 0) {
            return "";
        }else {
            switch(data[0]) {
                case 1:
                    switch(data[1]) {
                        case 1:
                            return  divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                        case 2:
                            return divOrg1.getDivOrgItemLevel2().getResourceKey();
                        case 3:
                            return divOrg1.getResourceKey();
                    }
                    return "";
                case 2:
                    switch(data[1]) {
                    	case 1:
                    		return  divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    	case 2:
                    		return divOrg1.getDivOrgItemLevel2().getResourceKey();
                    	case 3:
                    		return divOrg1.getResourceKey();
                    }
                    return "";
                case 3:
                    switch(data[1]) {
                    	case 1:
                    		return  divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
                    	case 2:
                    		return divOrg1.getDivOrgItemLevel2().getResourceKey();
                    	case 3:
                    		return divOrg1.getResourceKey();
                    }
                    return "";
            }
        }
        return null;
    }

    
    public void updateData(DriverData data) {
        firstName = data.getFirstName();
        lastName = data.getLastName();
        birthDate = data.getBirthDate();
        nationality = data.getNationality();
        city = data.getCity();
        personalAddress = data.getPersonalAddress();
        stateOrRegion = data.getStateOrRegion();

        Session session = HibernateUtil.currentSession();
        sex = (Sex)session.load(Sex.class,data.getSex().getResourceBundleId());
        
        GeoItemData itemData1 = (GeoItemData)data.getCountry().getItemIterator().next();
        GeoItemData itemData2 = null; 
        while(itemData1.getSubItems() != null) {
            itemData2 = itemData1;
            itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
        }
        
        Iterator it = data.getDivOrgs().iterator();
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        
        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),data.getCorporation().getId()));
        if(itemData2 != null) {
            geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
        }

        licenseNumber = data.getLicenseNumber();
        licenseExpiry = data.getLicenseExpiry();
        licenseCategory = data.getLicenseCategory();
        licenseObs = data.getLicenseObs();
        licenseGet = data.getLicenseGet();
        if(data.getVehicleAllowed() != null) {
            vehicleAllowed = (TypeVehicle)session.load(TypeVehicle.class,data.getVehicleAllowed().getResourceBundleId());
        }
        corporationId = data.getCorporationId();
        email = data.getEmail();
        phoneNumber = data.getPhoneNumber();
        officeAddress = data.getOfficeAddress();
        inclusionDate = data.getInclusionDate();
        exclusionDate = data.getExclusionDate();
        drives = data.isDrives();
        employee = data.isEmployee();
        if(data.getContract() != null) {
            contract = (Contract)session.load(Contract.class,data.getContract().getResourceBundleId());
        }
        bpsregistry = data.getBpsregistry();
        healthExpiry = data.getHealthExpiry();
        points = data.getPoints();
        
        alertDate = data.getAlertDate();
        	
        this.licenseNotExpiry = data.isLicenseNotExpiry();
        this.assignedVehicle = (data.getAssignedVehicle() == null) ? null : (AssignedVehicle) session.load(AssignedVehicle.class, data.getAssignedVehicle().getResourceBundleId());
        
        this.superviser = data.getSuperviser();
        this.manager = data.getManager();
        
        /* Campos para CIMSA */
        maritalStatus =
        			(data.getMaritalStatus() != null && data.getMaritalStatus().getResourceBundleId() != null &&
        					data.getMaritalStatus().getResourceBundleId().trim() != "") ?
        			(MaritalStatus)session.load(MaritalStatus.class,data.getMaritalStatus().getResourceBundleId().trim() ): null;
        educationLevel =
        			(data.getEducationLevel() != null && data.getEducationLevel().getResourceBundleId() != null &&
        					data.getEducationLevel().getResourceBundleId().trim() != "")?
        			(EducationLevel)session.load(EducationLevel.class,data.getEducationLevel().getResourceBundleId().trim() ):null;
        
        /* Campos para Pepsico */
        puesto =
        		(data.getPuesto() != null && data.getPuesto().getResourceBundleId() != null &&
        				data.getPuesto().getResourceBundleId().trim() != "")?
        						(PuestoPepsico)session.load(PuestoPepsico.class,data.getPuesto().getResourceBundleId().trim() ):null;
        						  
        licenseCoverage = data.getLicenseCoverage();
        federativeEntity = data.getFederativeEntity();
        authorizateWho = data.getAuthorizateWho();
        authorizateDate = data.getAuthorizateDate(); 
        this.idDallas = data.getIdDallas();
        this.temporal = data.isTemporal();
        this.noHrd=data.isNoHrd();
        this.noHrdComment=data.getNoHrdComment();
        this.director = data.isDirector();
    }

    public void updateDataForestal(DriverData data) {
        firstName = data.getFirstName();
        lastName = data.getLastName();
        birthDate = data.getBirthDate();
        nationality = data.getNationality();
        city = data.getCity();
        personalAddress = data.getPersonalAddress();
        stateOrRegion = data.getStateOrRegion();

        Session session = HibernateUtil.currentSession();
        sex = (Sex)session.load(Sex.class,data.getSex().getResourceBundleId());
        
        licenseNumber = data.getLicenseNumber();
        licenseExpiry = data.getLicenseExpiry();
        licenseCategory = data.getLicenseCategory();
        licenseObs = data.getLicenseObs();
        licenseGet = data.getLicenseGet();
        if(data.getVehicleAllowed() != null) {
            vehicleAllowed = (TypeVehicle)session.load(TypeVehicle.class,data.getVehicleAllowed().getResourceBundleId());
        }
        corporationId = data.getCorporationId();
        email = data.getEmail();
        phoneNumber = data.getPhoneNumber();
        officeAddress = data.getOfficeAddress();
        inclusionDate = data.getInclusionDate();
        drives = data.isDrives();
        employee = data.isEmployee();
        if(data.getContract() != null) {
            contract = (Contract)session.load(Contract.class,data.getContract().getResourceBundleId());
        }
        bpsregistry = data.getBpsregistry();
        healthExpiry = data.getHealthExpiry();
        points = data.getPoints();
        
        alertDate = data.getAlertDate();
        	
        this.licenseNotExpiry = data.isLicenseNotExpiry();
        this.assignedVehicle = (data.getAssignedVehicle() == null) ? null : (AssignedVehicle) session.load(AssignedVehicle.class, data.getAssignedVehicle().getResourceBundleId());
        
        this.superviser = data.getSuperviser();
        this.manager = data.getManager();
        
        /* Campos para CIMSA */
        maritalStatus =
        			(data.getMaritalStatus() != null && data.getMaritalStatus().getResourceBundleId() != null &&
        					data.getMaritalStatus().getResourceBundleId().trim() != "") ?
        			(MaritalStatus)session.load(MaritalStatus.class,data.getMaritalStatus().getResourceBundleId().trim() ): null;
        educationLevel =
        			(data.getEducationLevel() != null && data.getEducationLevel().getResourceBundleId() != null &&
        					data.getEducationLevel().getResourceBundleId().trim() != "")?
        			(EducationLevel)session.load(EducationLevel.class,data.getEducationLevel().getResourceBundleId().trim() ):null;
        
        /* Campos para Pepsico */
        puesto =
        		(data.getPuesto() != null && data.getPuesto().getResourceBundleId() != null &&
        				data.getPuesto().getResourceBundleId().trim() != "")?
        						(PuestoPepsico)session.load(PuestoPepsico.class,data.getPuesto().getResourceBundleId().trim() ):null;
        						  
        licenseCoverage = data.getLicenseCoverage();
        federativeEntity = data.getFederativeEntity();
        authorizateWho = data.getAuthorizateWho();
        authorizateDate = data.getAuthorizateDate(); 
        this.idDallas = data.getIdDallas();
        this.temporal = data.isTemporal();
        this.director = data.isDirector();						  
    }
    /**
     * 
     * @param type = detailed always
     * @return
     */
    public DetailedDriverReportWithVehiclesDataType getDetailedDriverReportWithVehiclesData(){
    	  try {
              String divOrgName11 = null;
              String divOrgName12 = null;
              String divOrgName13 = null;
              if(divOrg1 != null) {
                  if(divOrg1.getDivOrgItemLevel2() != null) {
                      if(divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                          divOrgName11 = divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                          divOrgName12 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                          divOrgName13 = divOrg1.getResourceKey();
                      }else {
                          divOrgName11 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                          divOrgName12 = divOrg1.getResourceKey();
                      }
                  }else {
                      divOrgName11 = divOrg1.getResourceKey();
                  }
              }
              String divOrgName21 = null;
              String divOrgName22 = null;
              String divOrgName23 = null;
              if(divOrg2 != null) {
                  if(divOrg2.getDivOrgItemLevel2() != null) {
                      if(divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                          divOrgName21 = divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                          divOrgName22 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                          divOrgName23 = divOrg2.getResourceKey();
                      }else {
                          divOrgName21 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                          divOrgName22 = divOrg2.getResourceKey();
                      }
                  }else {
                      divOrgName21 = divOrg2.getResourceKey();
                  }
              }
              String divOrgName31 = null;
              String divOrgName32 = null;
              String divOrgName33 = null;
              if(divOrg3 != null) {
                  if(divOrg3.getDivOrgItemLevel2() != null) {
                      if(divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                          divOrgName31 = divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                          divOrgName32 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                          divOrgName33 = divOrg3.getResourceKey();
                      }else {
                          divOrgName31 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                          divOrgName32 = divOrg3.getResourceKey();
                      }
                  }else {
                      divOrgName31 = divOrg3.getResourceKey();
                  }
              }
              String divgeo2 = null;
              String divgeo3 = null;
              Iterator it = corpCountry.getGeoLevel1Set().iterator();
              if(it.hasNext()){
                  GeoLevel1 level1 = (GeoLevel1)it.next();
                  divgeo2 = level1.getResourceKey();
                  if(level1.getGeoLevel2() != null) {
                      divgeo3 = level1.getGeoLevel2().getResourceKey();
                  }
              }
              String contractName = (contract == null) ? "" : contract.getResourceBundleId();
             
              DetailedDriverReportWithVehiclesDataType data = 
            	  new DetailedDriverReportWithVehiclesDataType(id,firstName,
            			  lastName,corpCountry.getPk().getCountry().getResourceKey(),
            			  birthDate,exclusionDate,contractName,
            			  divOrgName11,divOrgName12,divOrgName13,
            			  divOrgName21,divOrgName22,divOrgName23,
            			  divOrgName31,divOrgName32,divOrgName33,
            			  divgeo2,divgeo3,this);
              data.setSex(sex.getResourceBundleId());
              data.setNationality(nationality);
              data.setCity(city);
              data.setLicenseExpiry(licenseExpiry);
              data.setLicenseCategory(licenseCategory);
              data.setLicenseObs(licenseObs);
              data.setLicenseGet(licenseGet);
              
              data.setLicenseCoverage(licenseCoverage); // Agregado para Allergan 
              data.setFederativeEntity(federativeEntity); // Agregado para Allergan 
              
              if(vehicleAllowed != null) {
                  data.setVehicleallowed(vehicleAllowed.getResourceBundleId());
              }
              data.setOfficeaddress(officeAddress);
              data.setEmail(email);
              data.setPersonaladdress(personalAddress);
              data.setLicensenumber(licenseNumber);
              data.setPhonenumber(phoneNumber);
              data.setInclusiondate(inclusionDate);
              data.setDrives(drives);
              data.setEmployee(employee);
              data.setBpsregitry(bpsregistry);
              data.setCorporationId(corporationId);
              data.setLicenseNotExpiry(this.licenseNotExpiry);
              data.setIdDallas(this.idDallas);
              data.setTemporal(this.temporal);
              data.setDirector(this.director);
              data.setSuperviser(this.superviser);
              data.setManager(this.getManager());
              return data;
             
          } catch (RuntimeException e) {
              e.printStackTrace();
          }
          return null;
    }
    
    /**
     * 
     * @param type
     * @return
     */
    public DriverReportDataType getDriverReportData(String type) {
        
        try {
            String divOrgName11 = null;
            String divOrgName12 = null;
            String divOrgName13 = null;
            if(divOrg1 != null) {
                if(divOrg1.getDivOrgItemLevel2() != null) {
                    if(divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName11 = divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                        divOrgName12 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName13 = divOrg1.getResourceKey();
                    }else {
                        divOrgName11 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName12 = divOrg1.getResourceKey();
                    }
                }else {
                    divOrgName11 = divOrg1.getResourceKey();
                }
            }
            String divOrgName21 = null;
            String divOrgName22 = null;
            String divOrgName23 = null;
            if(divOrg2 != null) {
                if(divOrg2.getDivOrgItemLevel2() != null) {
                    if(divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName21 = divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                        divOrgName22 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName23 = divOrg2.getResourceKey();
                    }else {
                        divOrgName21 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName22 = divOrg2.getResourceKey();
                    }
                }else {
                    divOrgName21 = divOrg2.getResourceKey();
                }
            }
            String divOrgName31 = null;
            String divOrgName32 = null;
            String divOrgName33 = null;
            if(divOrg3 != null) {
                if(divOrg3.getDivOrgItemLevel2() != null) {
                    if(divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName31 = divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                        divOrgName32 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName33 = divOrg3.getResourceKey();
                    }else {
                        divOrgName31 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName32 = divOrg3.getResourceKey();
                    }
                }else {
                    divOrgName31 = divOrg3.getResourceKey();
                }
            }
            String divgeo2 = null;
            String divgeo3 = null;
            Iterator it = corpCountry.getGeoLevel1Set().iterator();
            if(it.hasNext()){
                GeoLevel1 level1 = (GeoLevel1)it.next();
                divgeo2 = level1.getResourceKey();
                if(level1.getGeoLevel2() != null) {
                    divgeo3 = level1.getGeoLevel2().getResourceKey();
                }
            }
            String contractName = (contract == null) ? "" : contract.getResourceBundleId();
            if(type.equals("simple")) {
                return new DriverReportDataType(id,firstName,lastName,
                		corpCountry.getPk().getCountry().getResourceKey(),
                        birthDate,exclusionDate,contractName,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,this);
            }else {
                DetailedDriverReportDataType data = new DetailedDriverReportDataType(id,
                		firstName,lastName,corpCountry.getPk().getCountry().getResourceKey(),
                        birthDate,exclusionDate,contractName,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,this);
                data.setSex(sex.getResourceBundleId());
                data.setNationality(nationality);
                data.setCity(city);
                data.setLicenseExpiry(licenseExpiry);
                data.setLicenseCategory(licenseCategory);
                data.setLicenseObs(licenseObs);
                data.setLicenseGet(licenseGet);
                
                data.setLicenseCoverage(licenseCoverage); // Agregado para Allergan 
                data.setFederativeEntity(federativeEntity); // Agregado para Allergan 
                
                if(vehicleAllowed != null) {
                    data.setVehicleallowed(vehicleAllowed.getResourceBundleId());
                }
                data.setOfficeaddress(officeAddress);
                data.setEmail(email);
                data.setPersonaladdress(personalAddress);
                data.setLicensenumber(licenseNumber);
                data.setPhonenumber(phoneNumber);
                data.setInclusiondate(inclusionDate);
                data.setDrives(drives);
                data.setEmployee(employee);
                data.setBpsregitry(bpsregistry);
                data.setCorporationId(corporationId);
                data.setLicenseNotExpiry(this.licenseNotExpiry);
                data.setSuperviser(superviser);
                data.setManager(manager);
                data.setIddallas(idDallas);
                data.setAuthorizateWho(authorizateWho);
                data.setDirector(this.director);
                return data;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }
      
    /**
     * 
     * @param type
     * @return
     */
    public FOSADriverReportDataType getFOSADriverReportData(String type) {
        
        try {
            String divOrgName11 = null;
            String divOrgName12 = null;
            String divOrgName13 = null;
            if(divOrg1 != null) {
                if(divOrg1.getDivOrgItemLevel2() != null) {
                    if(divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName11 = divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                        divOrgName12 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName13 = divOrg1.getResourceKey();
                    }else {
                        divOrgName11 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName12 = divOrg1.getResourceKey();
                    }
                }else {
                    divOrgName11 = divOrg1.getResourceKey();
                }
            }
            String divOrgName21 = null;
            String divOrgName22 = null;
            String divOrgName23 = null;
            if(divOrg2 != null) {
                if(divOrg2.getDivOrgItemLevel2() != null) {
                    if(divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName21 = divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                        divOrgName22 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName23 = divOrg2.getResourceKey();
                    }else {
                        divOrgName21 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName22 = divOrg2.getResourceKey();
                    }
                }else {
                    divOrgName21 = divOrg2.getResourceKey();
                }
            }
            String divOrgName31 = null;
            String divOrgName32 = null;
            String divOrgName33 = null;
            if(divOrg3 != null) {
                if(divOrg3.getDivOrgItemLevel2() != null) {
                    if(divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName31 = divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                        divOrgName32 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName33 = divOrg3.getResourceKey();
                    }else {
                        divOrgName31 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                        divOrgName32 = divOrg3.getResourceKey();
                    }
                }else {
                    divOrgName31 = divOrg3.getResourceKey();
                }
            }
            String divgeo2 = null;
            String divgeo3 = null;
            Iterator it = corpCountry.getGeoLevel1Set().iterator();
            if(it.hasNext()){
                GeoLevel1 level1 = (GeoLevel1)it.next();
                divgeo2 = level1.getResourceKey();
                if(level1.getGeoLevel2() != null) {
                    divgeo3 = level1.getGeoLevel2().getResourceKey();
                }
            }
            String contractName = (contract == null) ? "" : contract.getResourceBundleId();
            if(type.equals("simple")) {
                return new FOSADriverReportDataType(id,firstName,lastName,corpCountry.getPk().getCountry().getResourceKey(),
                        birthDate,exclusionDate,contractName,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,this);
            }else {
                DetailedFOSADriverReportDataType data = new DetailedFOSADriverReportDataType(id,firstName,lastName,corpCountry.getPk().getCountry().getResourceKey(),
                        birthDate,exclusionDate,contractName,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,this);
                data.setSex(sex.getResourceBundleId());
                data.setNationality(nationality);
                data.setCity(city);
                data.setLicenseExpiry(licenseExpiry);
                data.setLicenseCategory(licenseCategory);
                data.setLicenseObs(licenseObs);
                data.setLicenseGet(licenseGet);
                if(vehicleAllowed != null) {
                    data.setVehicleallowed(vehicleAllowed.getResourceBundleId());
                }
                data.setOfficeaddress(officeAddress);
                data.setEmail(email);
                data.setPersonaladdress(personalAddress);
                data.setLicensenumber(licenseNumber);
                data.setPhonenumber(phoneNumber);
                data.setInclusiondate(inclusionDate);
                data.setDrives(drives);
                data.setEmployee(employee);
                data.setBpsregitry(bpsregistry);
                data.setCorporationId(corporationId);
                data.setLicenseNotExpiry(this.licenseNotExpiry);
                return data;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Devuelve los datos necesarios para el HRD.
     * @param hrdCondition
     * @return
     */
    public DriverReportDataType getDriverHRDData(String hrdCondition) {
    	DriverReportDataType result = this.getDriverReportData("simple");
    	result.setHrdCondition(hrdCondition);
    	result.setDriverPoints(points);
    	result.setNoHrd(this.noHrd);
    	result.setNoHrdComment(this.noHrdComment);
    	return result;
    }
    

    		   
    /**
     * Devuelve los datos necesarios para el PHRD.
     * @param hrdCondition
     * @return
     */
    public DriverReportDataType getDriverPHRDData(String phrdCondition) {
    	DriverReportDataType result = this.getDriverReportData("simple");
    	result.setPhrdCondition(phrdCondition);
    	return result;
    }    
    	   
    
    public DriverReportDataType getDriverReportData2(String type) {
        
        try {
            String divOrgName11 = null;
            String divOrgName12 = null;
            String divOrgName13 = null;
            if(divOrg1 != null) {
                divOrgName13 = divOrg1.getResourceKey();
                if(divOrg1.getDivOrgItemLevel2() != null) {
                    divOrgName12 = divOrg1.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName11 = divOrg1.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divOrgName21 = null;
            String divOrgName22 = null;
            String divOrgName23 = null;
            if(divOrg2 != null) {
                divOrgName23 = divOrg2.getResourceKey();
                if(divOrg2.getDivOrgItemLevel2() != null) {
                    divOrgName22 = divOrg2.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName21 = divOrg2.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divOrgName31 = null;
            String divOrgName32 = null;
            String divOrgName33 = null;
            if(divOrg3 != null) {
                divOrgName33 = divOrg3.getResourceKey();
                if(divOrg3.getDivOrgItemLevel2() != null) {
                    divOrgName32 = divOrg3.getDivOrgItemLevel2().getResourceKey();                
                    if(divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                        divOrgName31 = divOrg3.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    }
                }
            }
            String divgeo2 = null;
            String divgeo3 = null;
            Iterator it = corpCountry.getGeoLevel1Set().iterator();
            if(it.hasNext()){
                GeoLevel1 level1 = (GeoLevel1)it.next();
                divgeo2 = level1.getResourceKey();
                if(level1.getGeoLevel2() != null) {
                    divgeo3 = level1.getGeoLevel2().getResourceKey();
                }
            }
            String contractName = (contract == null) ? "" : contract.getResourceBundleId();
            if(type.equals("simple")) {
                return new DriverReportDataType(id,firstName,lastName,corpCountry.getPk().
                		getCountry().getResourceKey(),
                        birthDate,exclusionDate,contractName,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,this);
            }else {
                DetailedDriverReportDataType data = new DetailedDriverReportDataType(id,
                		firstName,lastName,corpCountry.getPk().getCountry().getResourceKey(),
                        birthDate,exclusionDate,contractName,
                        divOrgName11,divOrgName12,divOrgName13,
                        divOrgName21,divOrgName22,divOrgName23,
                        divOrgName31,divOrgName32,divOrgName33,
                        divgeo2,divgeo3,this);
                data.setSex(sex.getResourceBundleId());
                data.setNationality(nationality);
                data.setCity(city);
                data.setLicenseExpiry(licenseExpiry);
                data.setLicenseCategory(licenseCategory);
                data.setLicenseObs(licenseObs);
                data.setLicenseGet(licenseGet);
                if(vehicleAllowed != null) {
                    data.setVehicleallowed(vehicleAllowed.getResourceBundleId());
                }
                data.setOfficeaddress(officeAddress);
                data.setEmail(email);
                data.setPersonaladdress(personalAddress);
                data.setLicensenumber(licenseNumber);
                data.setPhonenumber(phoneNumber);
                data.setInclusiondate(inclusionDate);
                data.setDrives(drives);
                data.setEmployee(employee);
                data.setBpsregitry(bpsregistry);
                data.setLicenseNotExpiry(this.licenseNotExpiry);
                data.setCorporationId(this.corporationId);
                data.setSuperviser(superviser);
                data.setManager(manager);
                data.setIddallas(idDallas);
                data.setDirector(this.director);
                data.setAuthorizateWho(authorizateWho);
                return data;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set getAptitudTestSet() {
        return aptitudTestSet;
    }

    public void setAptitudTestSet(Set aptitudTestSet) {
        this.aptitudTestSet = aptitudTestSet;
    }

    public Set getAlcoholControlSet() {
        return alcoholControlSet;
    }

    public void setAlcoholControlSet(Set alcoholControlSet) {
        this.alcoholControlSet = alcoholControlSet;
    }

    public Set getPrizeSet() {
        return prizeSet;
    }

    public void setPrizeSet(Set prizeSet) {
        this.prizeSet = prizeSet;
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

	public Date getHealthExpiry() {
		return healthExpiry;
	}

	public void setHealthExpiry(Date healthExpiry) {
		this.healthExpiry = healthExpiry;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * @return the alertDate
	 */
	public Date getAlertDate() {
		return alertDate;
	}

	/**
	 * @param alertDate the alertDate to set
	 */
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}
	
	public AssignedVehicle getAssignedVehicle() {
		return assignedVehicle;
	}

	public void setAssignedVehicle(AssignedVehicle assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
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

	/**
	 * @return the maritalStatus
	 */
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the educationLevel
	 */
	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	/**
	 * @param educationLevel the educationLevel to set
	 */
	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	/**
	 * @return the puesto
	 */
	public PuestoPepsico getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(PuestoPepsico puesto) {
		this.puesto = puesto;
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
	 * @return the authorizateDate
	 */
	public Date getAuthorizateDate() {
		return authorizateDate;
	}

	/**
	 * @param authorizateDate the authorizateDate to set
	 */
	public void setAuthorizateDate(Date authorizateDate) {
		this.authorizateDate = authorizateDate;
	}

	public String getIdDallas() {
		return idDallas;
	}

	public void setIdDallas(String idDallas) {
		this.idDallas = idDallas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void updateDivisions(Integer[] divOrgs,Session session) {
		if(divOrgs[0] != 0) {
	        divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,divOrgs[0]);
		}
		if(divOrgs[1] != 0) {
	        divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,divOrgs[1]);
		}
		if(divOrgs[2] != 0) {
	        divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,divOrgs[2]);
		}
	}

	/**
	 * @return the temporal
	 */
	public boolean getTemporal() {
		return temporal;
	}

	/**
	 * @param temporal the temporal to set
	 */
	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

	/**
	 * @return the createdFromTablet
	 */
	public boolean isCreatedFromTablet() {
		return createdFromTablet;
	}

	/**
	 * @param createdFromTablet the createdFromTablet to set
	 */
	public void setCreatedFromTablet(boolean createdFromTablet) {
		this.createdFromTablet = createdFromTablet;
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

	public String getLicenseObs() {
		return licenseObs;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}

	public boolean isBrasilCountry() {
		return corpCountry.getPk().getCountry().getId().equals(CountryData.BRASIL_ID);
	}

	public boolean isDirector() {
		return director;
	}

	public void setDirector(boolean director) {
		this.director = director;
	}
}
