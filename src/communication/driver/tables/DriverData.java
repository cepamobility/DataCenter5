package communication.driver.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.corporation.util.Util;
import communication.administration.group.tables.GroupLineData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.datatype.tables.ContractDataType;
import communication.datatype.tables.EducationLevelDataType;
import communication.datatype.tables.MaritalStatusDataType;
import communication.datatype.tables.PuestoPepsicoDataType;
import communication.datatype.tables.SexDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.util.Validate;

public class DriverData extends DriverMainAttributes implements Serializable{

    private static final long serialVersionUID = 1L;

    public static final int TYPE_A = 0;
    public static final int TYPE_B = 1;
    public static final int TYPE_C = 2;
	public static final int TYPE_D = 3;

    public static final int ACTIVE = 0;
    public static final int EXCLUDED = 1;
    public static final int ALL = 2;
    
    public static final int WITH_VEHICLES = 0;
    public static final int WITHOUT_VEHICLES = 1;

    public static final int APT = 0;
    public static final int NOT_APT = 1;

    public static final int EXPIRED = 0;
    public static final int NOT_EXPIRED = 1;

    public static final int NEGATIVE = 0;
    public static final int POSITIVE = 1;
    
    public static final int DIV_ORG = 1;
    public static final int DIV_GEO = 2;

    public static final int ANY_POSITIVE = 0;
    public static final int WITHOUT_POSITIVE = 1;

    public static final String RESUMED = "1";
    public static final String DETAILED_HRD_EVENTS = "2";
    public static final String DETAILED_ALL_EVENTS = "3";

	public static final int UNAVAILABLE = 0;
	public static final int AVAILABLE = 1;

    public static final int ONLY_TEMPORAL = 1;
    public static final int ONLY_NOT_TEMPORAL = 2;

    private SexDataType sex;
    private String nationality;
    private CorporationData corporation;
    
	private Collection divOrgs;
	private Date licenseExpiry;
	private String licenseCategory;
	private String licenseObs;
	private Date licenseGet;

  	private String licenseCoverage; // Pepsico, "cobertura de la licencia"
    private String federativeEntity; // Pepsico, "Entidad Federativa"
    private String authorizateWho; // Pepsico, "Especificar quien autoriza la alta y la fecha en que fue solicitada" 
    private Date authorizateDate;

	private TypeVehicleDataType vehicleAllowed;
	private String officeAddress;
	private String email;
	private String personalAddress;
	private MaritalStatusDataType maritalStatus;
	private EducationLevelDataType educationLevel;
	
	private String stateOrRegion; // estado o localidad donde se ubica la ciudad (ubicacion del conductor)
	
	private PuestoPepsicoDataType puesto;

    private String licenseNumber;
    private String corporationId;

    private String phoneNumber;

    private Date inclusionDate;
    private String hrdConditions;

    private boolean drives;
    private boolean employee;

    private ContractDataType contract;

    private String bpsregistry;

    private Collection aptitudTest;
    private Collection alcoholControl;

    private Collection prizes;

    private boolean licenseNotExpiry;
    private Date healthExpiry;

	private Integer points;
	
	private Date alertDate;
	private AssignedVehicleDataType assignedVehicle;

	private String superviser;

	private String manager;
	
	private String idDallas; // telematic
	
	protected boolean temporal;
	
	private boolean noHrd;
	private String noHrdComment;

	private boolean director;
	private DriverLicenseImageData licenseImage;

	/**
     * @param id
     * @param firstName
     * @param lastName
     * @param country
     * @param birthDate
     * @param city
     * @param sex
     * @param nationality
     * @param corporation
     * @param divOrgs
     * @param licenseExpiry
     * @param licenseCategory
     * @param licenseGet
     * @param vehicleAllowed
     * @param officeAddress
     * @param email
     * @param personalAddress
     * @param licenseNumber
     * @param corporationId
     * @param phoneNumber
     * @param inclusionDate
	 * @param contract 
	 * @param employee 
	 * @param drives 
     */
    public DriverData(Integer id, String firstName, String lastName, GeoLabelData country, 
    		Date birthDate,String city, SexDataType sex, String nationality, 
    		CorporationData corporation, Collection divOrgs, 
    		Date licenseExpiry, String licenseCategory, String licenseObs,
    		Date licenseGet, TypeVehicleDataType vehicleAllowed, 
    		String officeAddress, String email, String personalAddress, 
    		String licenseNumber, String corporationId, String phoneNumber, 
    		Date inclusionDate, Date exclusionDate, boolean drives, 
    		boolean employee, ContractDataType contract, 
    		String bpsregistry, Date healthExpiry, boolean licenseNotExpiry, 
    		Integer points,AssignedVehicleDataType assignedVehicle,
    		String superviser,String manager, MaritalStatusDataType maritalStatus, 
    		EducationLevelDataType educationLevel, PuestoPepsicoDataType puesto,
    		String  licenseCoverage, String  federativeEntity, String  authorizateWho,
    		Date authorizateDate, String idDallas, boolean temporal, String stateOrRegion,Boolean noHrd,String noHrdComment, boolean director) {
        super(id, firstName, lastName, country, birthDate, city, exclusionDate);
        this.sex = sex;
        this.nationality = nationality;
        this.corporation = corporation;
        this.divOrgs = divOrgs;
        if(divOrgs != null) {
	        Iterator iter=divOrgs.iterator();
	        while(iter.hasNext()){//Acceso directo a la primera división
	        	DivOrgData d=(DivOrgData)iter.next();
	        	if(divOrgItem1==null){
	        		Iterator iter2=d.getDivOrgLevels().getItemIterator();
	        		ItemData item=((ItemData)iter2.next());
	        		divOrgItem1=item.getName();
	        		if(item.getSubItems()!=null){
		        		iter2=item.getSubItems().getItemIterator();
		        		if(iter2.hasNext()){
		        			if(item.getSubItems()!=null){
		        				iter2=item.getSubItems().getItemIterator();
		        				item=((ItemData)iter2.next());
			        			divOrgItem2=item.getName();
			        			if(item.getSubItems()!=null){
				        			iter2=item.getSubItems().getItemIterator();
				        			if(iter2.hasNext()){
					        			divOrgItem3=((ItemData)iter2.next()).getName();
					        		}
			        			}
		        			}
		        		}
	        		}
	        		
	        		
	        		break;
	        	}
	        }
        }
        this.licenseExpiry = licenseExpiry;
        this.licenseCategory = licenseCategory;
    	this.licenseObs = licenseObs;
        this.licenseGet = licenseGet;
        this.vehicleAllowed = vehicleAllowed;
        this.officeAddress = officeAddress;
        this.email = email;
        this.personalAddress = personalAddress;
        this.stateOrRegion = stateOrRegion;
        this.licenseNumber = licenseNumber;
        this.corporationId = corporationId;
        this.phoneNumber = phoneNumber;
        this.inclusionDate = inclusionDate;
        this.drives = drives;
        this.employee = employee;
        this.contract = contract;
        this.bpsregistry = bpsregistry;
        this.healthExpiry = healthExpiry;
        aptitudTest = new LinkedList();
        alcoholControl = new LinkedList();
        prizes = new LinkedList();
        this.licenseNotExpiry = licenseNotExpiry;
        this.points = points;
        this.assignedVehicle = assignedVehicle;
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
        this.temporal = temporal;
        super.licenceExpirity=licenseExpiry;
        this.noHrd=noHrd;
        this.noHrdComment=noHrdComment;
        this.director = director;
    }

    public DriverData(){}
    /**
	 * @param uid
	 */
	public DriverData(Integer id) {
		super(id);
        aptitudTest = new LinkedList();
        alcoholControl = new LinkedList();
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
	 * @return Returns the corporation.
	 */
	public CorporationData getCorporation() {
		return corporation;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
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
	public SexDataType getSex() {
		return sex;
	}

	/**
	 * @return Returns the vehicleAllowed.
	 */
	public TypeVehicleDataType getVehicleAllowed() {
		return vehicleAllowed;
	}

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getCorporationId() {
        return corporationId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    /**
     * @return Returns the divOrgs.
     */
    public Collection getDivOrgs() {
        return divOrgs;
    }

    public Integer[] getDivOrgIds() {
    	Integer[] ids = new Integer[3];
        Iterator it = getDivOrgsIterator();
        DivOrgData data = null;
        for(int i = 0; i < 3; i++) {
        	if(it.hasNext()) {
        		data = (DivOrgData)it.next();
                ItemData item = (ItemData)data.getDivOrgLevels().getItemIterator().next();
                while(item.getSubItems() != null){
                    item = (ItemData)item.getSubItems().getItemIterator().next();
                }
                ids[i] = item.getId();
        	}else {
        		ids[i] = 0;
        	}
        }
        return ids;
    }

    /**
	 * @param data
	 * @return null if is valid, String with message if is invalid
	 */
	public String validate(UserSessionData userRequest) {

		if (empty(firstName)) {
			return "generic.driver.driverdata.firstNameInvalid";
		}

		if (empty(lastName)) {
			return "generic.driver.driverdata.lastNameInvalid";
		}
		
		if (birthDate == null) {
			return "generic.driver.driverdata.birthDateInvalid";
		}

		if (sex == null || sex.getResourceBundleId() == null) {
			return "generic.driver.driverdata.sexInvalid";
		}

		if (corporation == null) {
			return "generic.driver.driverdata.corporationInvalid";
		}

		if (country == null) {
			return "generic.driver.driverdata.countryInvalid";
		}

        Iterator divOrgsIterator = getDivOrgsIterator();
        if(divOrgsIterator == null) {
            return "generic.driver.driverdata.divorgInvalid";
        }else {
            if(!userRequest.isDriver()) {
                String message = Validate.validateDivOrg(divOrgsIterator,userRequest);
                if(message != null) {
                    return message;
                }
            }
        }
        if(!Validate.validateDivGeo(country,userRequest)) {
            return "generic.driver.driverdata.divgeoInvalid";
        }
      
		return null;
	}

	private boolean empty(String value) {
		return (value == null || value.trim().length() == 0);
	}
	
    /**
     * @return
     */
    public Iterator getDivOrgsIterator() {
        if(divOrgs == null) {
            return null;
        }
        Collections.sort((List)divOrgs);
        return divOrgs.iterator();
    }

    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate; 
    }

    /**
     * @return Returns the contract.
     */
    public ContractDataType getContract() {
        return contract;
    }

    public String getBpsregistry() {
        return bpsregistry;
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

    public String getData(int type, int index, int j) {
        if(type == DIV_ORG) {
            Iterator it = getDivOrgsIterator();
            DivOrgData data = null;
            for(int i = 0; i < index; i++) {
                data = (DivOrgData)it.next();
            }
            LabelItemData label = data.getDivOrgLevels(); 
            for(int i = 1; i < j; i++) {
                label = ((ItemData)label.getItemIterator().next()).getSubItems();
            }
            return ((ItemData)label.getItemIterator().next()).getName();
        }else{
            GeoLabelData label = getCountry(); 
            for(int i = 1; i < j; i++) {
                label = ((GeoItemData)label.getItemIterator().next()).getSubItems();
            }
           return ((GeoItemData)label.getItemIterator().next()).getName();
        }
    }

    public void setContract(ContractDataType contract) {
        this.contract = contract;
    }

    public void setCorporation(CorporationData corporation) {
        this.corporation = corporation;
    }

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    public void setDivOrgs(Collection divOrgs) {
        this.divOrgs = divOrgs;
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

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public String getLicenseObs() {
		return licenseObs;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}

	public void setLicenseExpiry(Date licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
    }

    public void setLicenseGet(Date licenseGet) {
        this.licenseGet = licenseGet;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public void setPersonalAddress(String personalAddress) {
        this.personalAddress = personalAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(SexDataType sex) {
        this.sex = sex;
    }

    public void setVehicleAllowed(TypeVehicleDataType vehicleAllowed) {
        this.vehicleAllowed = vehicleAllowed;
    }

	/**
	 * @return Returns the hrdConditions.
	 */
	public String getHrdConditions() {
		return hrdConditions;
	}

	/**
	 * @param hrdConditions The hrdConditions to set.
	 */
	public void setHrdConditions(String hrdConditions) {
		this.hrdConditions = hrdConditions;
	}

    public void setBpsregistry(String bpsregistry) {
        this.bpsregistry = bpsregistry;
    }

    
	public Collection getAptitudTest() {
    	return aptitudTest;
    }

    /**
     * 
     * @param aptitudTest
     */
	public void setAptitudTest(Collection aptitudTest) {
    	this.aptitudTest = aptitudTest;
    }

    public Collection getAlcoholControl() {
        return alcoholControl;
    }

    public void setAlcoholControl(Collection alcoholControl) {
        this.alcoholControl = alcoholControl;
    }

    public Collection getPrizes() {
        return prizes;
    }

    public void setPrizes(Collection prizes) {
        this.prizes = prizes;
    }
    
	public Collection getGroupLine() {
        Collection elements = new LinkedList();
        Collections.sort((List)divOrgs);
        Iterator it = divOrgs.iterator();
        Collection[] items = new LinkedList[3];
        for(int i = 0; i < 3; i++) {
            items[i] = new LinkedList();
        }
        Collection countries = new LinkedList();
        int index = 0;
        while(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            LabelItemData lid1 = divOrg.getDivOrgLevels();
            switch(divOrg.getLabels().length) {
                case 1:
                    if(lid1 != null) {
                        Iterator it1 = lid1.getItemIterator();
                        while(it1.hasNext()) {
                            Collection itemsOut1 = new LinkedList();
                            itemsOut1.add(it1.next());
                            items[index].add(new LabelItemData(lid1.getId(),lid1.getName(),itemsOut1,lid1.getShowLevel()));
                        }
                    }
                    break;
                case 2:
                    if(lid1 != null) {
                        Iterator it1 = lid1.getItemIterator();
                        while(it1.hasNext()) {
                            ItemData itemIn1 = (ItemData)it1.next();
                            LabelItemData lid2 = itemIn1.getSubItems();
                            Iterator it2 = lid2.getItemIterator();
                            while(it2.hasNext()) {
                                Collection itemsOut2 = new LinkedList();
                                itemsOut2.add(it2.next());
                                Collection itemsOut1 = new LinkedList();
                                itemsOut1.add(new ItemData(itemIn1.getId(),itemIn1.getName(),itemIn1.isActive(),new LabelItemData(lid2.getId(),lid2.getName(),itemsOut2,lid2.getShowLevel())));
                                items[index].add(new LabelItemData(lid1.getId(),lid1.getName(),itemsOut1,lid1.getShowLevel()));
                            }
                        }
                    }
                    break;
                case 3:
                    if(lid1 != null) {
                        Iterator it1 = lid1.getItemIterator();
                        while(it1.hasNext()) {
                            ItemData itemIn1 = (ItemData)it1.next();
                            LabelItemData lid2 = itemIn1.getSubItems();
                            Iterator it2 = lid2.getItemIterator();
                            while(it2.hasNext()) {
                                ItemData itemIn2 = (ItemData)it2.next();
                                LabelItemData lid3 = itemIn2.getSubItems();
                                Iterator it3 = lid3.getItemIterator();
                                while(it3.hasNext()) {
                                    Collection itemsOut3 = new LinkedList();
                                    itemsOut3.add(it3.next());
                                    Collection itemsOut2 = new LinkedList();
                                    itemsOut2.add(new ItemData(itemIn2.getId(),itemIn2.getName(),itemIn2.isActive(),new LabelItemData(lid3.getId(),lid3.getName(),itemsOut3,lid3.getShowLevel())));
                                    Collection itemsOut1 = new LinkedList();
                                    itemsOut1.add(new ItemData(itemIn1.getId(),itemIn1.getName(),itemIn1.isActive(),new LabelItemData(lid2.getId(),lid2.getName(),itemsOut2,lid2.getShowLevel())));
                                    items[index].add(new LabelItemData(lid1.getId(),lid1.getName(),itemsOut1,lid1.getShowLevel()));
                                }
                            }
                        }
                    }
                    break;
            }
            index++;
        }
        
        switch(corporation.getGeoLabels().length) {
            case 1:
                if(country != null) {
                    Iterator it1 = country.getItemIterator();
                    while(it1.hasNext()) {
                        Collection itemsOut1 = new LinkedList();
                        itemsOut1.add(it1.next());
                        countries.add(new GeoLabelData(country.getId(),country.getResourceKey(),country.getLevel(),itemsOut1));
                    }
                }
                break;
            case 2:
                if(country != null) {
                    Iterator it1 = country.getItemIterator();
                    while(it1.hasNext()) {
                        GeoItemData itemIn1 = (GeoItemData)it1.next();
                        GeoLabelData lid2 = itemIn1.getSubItems();
                        Iterator it2 = lid2.getItemIterator();
                        while(it2.hasNext()) {
                            Collection itemsOut2 = new LinkedList();
                            itemsOut2.add(it2.next());
                            Collection itemsOut1 = new LinkedList();
                            itemsOut1.add(new GeoItemData(itemIn1.getId(),itemIn1.getName(),itemIn1.isActive(),new GeoLabelData(lid2.getId(),lid2.getResourceKey(),lid2.getLevel(),itemsOut2)));
                            countries.add(new GeoLabelData(country.getId(),country.getResourceKey(),country.getLevel(),itemsOut1));
                        }
                    }
                }
                break;
            case 3:
                if(country != null) {
                    Iterator it1 = country.getItemIterator();
                    while(it1.hasNext()) {
                        GeoItemData itemIn1 = (GeoItemData)it1.next();
                        GeoLabelData lid2 = itemIn1.getSubItems();
                        Iterator it2 = lid2.getItemIterator();
                        while(it2.hasNext()) {
                            GeoItemData itemIn2 = (GeoItemData)it2.next();
                            GeoLabelData lid3 = itemIn2.getSubItems();
                            Iterator it3 = lid3.getItemIterator();
                            while(it3.hasNext()) {
                                Collection itemsOut3 = new LinkedList();
                                itemsOut3.add(it3.next());
                                Collection itemsOut2 = new LinkedList();
                                itemsOut2.add(new GeoItemData(itemIn2.getId(),itemIn2.getName(),itemIn2.isActive(),new GeoLabelData(lid3.getId(),lid3.getResourceKey(),lid3.getLevel(),itemsOut3)));
                                Collection itemsOut1 = new LinkedList();
                                itemsOut1.add(new GeoItemData(itemIn1.getId(),itemIn1.getName(),itemIn1.isActive(),new GeoLabelData(lid2.getId(),lid2.getResourceKey(),lid2.getLevel(),itemsOut2)));
                                countries.add(new GeoLabelData(country.getId(),country.getResourceKey(),country.getLevel(),itemsOut1));
                            }
                        }
                    }
                }
                break;
        }
        
        Iterator it1 = items[0].iterator();
        Iterator it2 = items[1].iterator();
        Iterator it3 = items[2].iterator();
        Iterator it4 = countries.iterator();
        if(items[0].size() >= items[1].size() && items[0].size() >= items[2].size() && items[0].size() >= countries.size()) {
            LabelItemData lid2 = null;
            LabelItemData lid3 = null;
            GeoLabelData glid = null;
            while(it1.hasNext()) {
                LabelItemData lid1 = (LabelItemData)it1.next();
                if(it2.hasNext()) {
                    lid2 = (LabelItemData)it2.next();
                }
                if(it3.hasNext()) {
                    lid3 = (LabelItemData)it3.next();
                }
                if(it4.hasNext()) {
                    glid = (GeoLabelData)it4.next();
                }
                GroupLineData groupLine = new GroupLineData(null);
                LabelItemData[] lids = {lid1,lid2,lid3};
                groupLine.setDivOrgs(lids);
                groupLine.setCountry(glid);
                elements.add(groupLine);
            }
        }else if(items[1].size() >= items[0].size() && items[1].size() >= items[2].size() && items[1].size() >= countries.size()) {
            LabelItemData lid1 = null;
            LabelItemData lid3 = null;
            GeoLabelData glid = null;
            while(it2.hasNext()) {
                LabelItemData lid2 = (LabelItemData)it2.next();
                if(it1.hasNext()) {
                    lid1 = (LabelItemData)it1.next();
                }
                if(it3.hasNext()) {
                    lid3 = (LabelItemData)it3.next();
                }
                if(it4.hasNext()) {
                    glid = (GeoLabelData)it4.next();
                }
                GroupLineData groupLine = new GroupLineData(null);
                LabelItemData[] lids = {lid1,lid2,lid3};
                groupLine.setDivOrgs(lids);
                groupLine.setCountry(glid);
                elements.add(groupLine);
            }
        }else if(items[2].size() >= items[0].size() && items[2].size() >= items[1].size() && items[2].size() >= countries.size()) {
            LabelItemData lid1 = null;
            LabelItemData lid2 = null;
            GeoLabelData glid = null;
            while(it3.hasNext()) {
                LabelItemData lid3 = (LabelItemData)it3.next();
                if(it1.hasNext()) {
                    lid1 = (LabelItemData)it1.next();
                }
                if(it2.hasNext()) {
                    lid2 = (LabelItemData)it2.next();
                }
                if(it4.hasNext()) {
                    glid = (GeoLabelData)it4.next();
                }
                GroupLineData groupLine = new GroupLineData(null);
                LabelItemData[] lids = {lid1,lid2,lid3};
                groupLine.setDivOrgs(lids);
                groupLine.setCountry(glid);
                elements.add(groupLine);
            }
        }else {
            LabelItemData lid1 = null;
            LabelItemData lid2 = null;
            LabelItemData lid3 = null;
            while(it4.hasNext()) {
                GeoLabelData glid = (GeoLabelData)it4.next();
                if(it1.hasNext()) {
                    lid1 = (LabelItemData)it1.next();
                }
                if(it2.hasNext()) {
                    lid2 = (LabelItemData)it2.next();
                }
                if(it3.hasNext()) {
                    lid3 = (LabelItemData)it3.next();
                }
                GroupLineData groupLine = new GroupLineData(null);
                LabelItemData[] lids = {lid1,lid2,lid3};
                groupLine.setDivOrgs(lids);
                groupLine.setCountry(glid);
                elements.add(groupLine);
            }
        }
        return elements;
    }

	public Date getHealthExpiry() {
		return healthExpiry;
	}

	public void setHealthExpiry(Date healthExpiry) {
		this.healthExpiry = healthExpiry;
	}

	public String getDivOrgName(int divOrg,int index) {
        if(divOrgs == null) {
            return null;
        }
        Collections.sort((List)divOrgs);
        Iterator it = divOrgs.iterator();
        LabelItemData label = ((DivOrgData)it.next()).getDivOrgLevels();
        for(int i = 1; i < divOrg; i++) {
        	label = ((DivOrgData)it.next()).getDivOrgLevels();
        }
        if(label != null) {
        	ItemData item = null;
            for(int i = 0; i < index; i++) {
            	item = (ItemData)label.getItemIterator().next();
            	label = item.getSubItems();
            }
        	return item.getName();
        }
        return null;
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

	public AssignedVehicleDataType getAssignedVehicle() {
		return assignedVehicle;
	}

	public void setAssignedVehicle(AssignedVehicleDataType assignedVehicle) {
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
	public MaritalStatusDataType getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(MaritalStatusDataType maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the educationLevel
	 */
	public EducationLevelDataType getEducationLevel() {
		return educationLevel;
	}

	/**
	 * @param educationLevel the educationLevel to set
	 */
	public void setEducationLevel(EducationLevelDataType educationLevel) {
		this.educationLevel = educationLevel;
	}

	/**
	 * @return the puesto
	 */
	public PuestoPepsicoDataType getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(PuestoPepsicoDataType puesto) {
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
	
	public boolean isArgPyUy() {
		String countryName = getCountryName();
		return countryName.equals(CountryData.ARGENTINA) || countryName.equals(CountryData.PARAGUAY) || countryName.equals(CountryData.URUGUAY);
	}

	public String getKey() {
		String key = "";
		Iterator<DivOrgData> it = this.getDivOrgsIterator();
    	while(it.hasNext()) {		
    		DivOrgData divOrgData = (DivOrgData)it.next();
    		if(divOrgData.getDivOrgLevels() != null) {
    			ItemData item = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
        		while(item.getSubItems() != null) {
    				item = (ItemData)item.getSubItems().getItemIterator().next();
				} // end if(item2.getSubItems() != null)
				key += item.getId()+";";
    		}
    	}
	    GeoItemData itemData1 = (GeoItemData)country.getItemIterator().next();
	    while(itemData1.getSubItems() != null) {
	        itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
	    }
	    key += String.valueOf(itemData1.getId());
    	return key;
	}

	public void replaceDivOrg(int index, Integer item1, Integer item2, Integer item3) {
		LabelItemData lid = getLabelItemData(item1, item2, item3);  	

		Iterator<DivOrgData> it = this.getDivOrgsIterator();
		DivOrgData divOrgData = null;
    	for(int i = 1; i <= index && it.hasNext(); i++) {		
    		divOrgData = (DivOrgData)it.next();
    	}
    	divOrgData.setDivOrgLevels(lid);
	}

	private LabelItemData getLabelItemData(Integer item1, Integer item2,
			Integer item3) {
		Collection items1 = new LinkedList();
        if(item2 == null) {
            items1.add(new ItemData(item1,"",true,null));
        }else{
            if(item3 == null) {
                Collection items2 = new LinkedList();
                items2.add(new ItemData(item2,"",true,null));
                LabelItemData level1 = new LabelItemData(null,null,items2,null);
                items1.add(new ItemData(item1,"",true,level1));
            }else {
                Collection items3 = new LinkedList();
                items3.add(new ItemData(item3,"",true,null));
                LabelItemData level2 = new LabelItemData(null,null,items3,null);
                
                Collection items2 = new LinkedList();
                items2.add(new ItemData(item2,"",true,level2));
                LabelItemData level1 = new LabelItemData(null,null,items2,null);
                
                items1.add(new ItemData(item1,"",true,level1));
                
            }
        }
        LabelItemData lid = new LabelItemData(null,null,items1,null);
		return lid;
	}
	
	public void replaceDivGeo(Integer item1, Integer item2, Integer item3) {
		Collection items1 = new LinkedList();
        if(item2 == null) {
            items1.add(new GeoItemData(item1,"",true,null));
        }else{
            if(item3 == null) {
                Collection items2 = new LinkedList();
                items2.add(new GeoItemData(item2,"",true,null));
                GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
                items1.add(new GeoItemData(item1,"",true,level1));
            }else {
                Collection items3 = new LinkedList();
                items3.add(new GeoItemData(item3,"",true,null));
                GeoLabelData level2 = new GeoLabelData(null,null,null,items3);
                
                Collection items2 = new LinkedList();
                items2.add(new GeoItemData(item2,"",true,level2));
                GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
                
                items1.add(new GeoItemData(item1,"",true,level1));
            }
        }
        setCountry(new GeoLabelData(null,null,null,items1));       		
	}
	
	
	public void addDivOrg(Integer divOrgid, String divOrgName, Integer item1, Integer item2, Integer item3) {
		LabelItemData lid = getLabelItemData(item1, item2, item3);  	
    	getDivOrgs().add(new DivOrgData(divOrgid,divOrgName,lid));
	}

	public boolean isDirector() {
		return director;
	}

	public void setDirector(boolean director) {
		this.director = director;
	}

	public int getAge() {
		return Util.getAge(birthDate);
	}
	
	public int getLicenseAge() {
		return Util.getAge(licenseGet);
	}

	public int getComapanyAge() {
		return Util.getAge(inclusionDate);
	}
	

	public DriverLicenseImageData getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(DriverLicenseImageData licenseImage) {
		this.licenseImage = licenseImage;
	}
}
