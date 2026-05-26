package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.Country;
import persistence.datatype.tables.Contract;
import persistence.datatype.tables.Sex;
import persistence.datatype.tables.TypeVehicle;
import persistence.hibernate.HibernateUtil;

import communication.driver.report.DetailedDriverReportDataType;

/**
 * @author jrodriguez
 */
public class ClosedDriver implements Serializable {

	private ClosedDriverPK pk;
	private String firstName;
	private String lastName;
	private Date birthDate;
    private Sex sex;

    private String divOrg11;
    private Integer divOrgId11;
    private Boolean divOrgActive11;

    private String divOrg12;
    private Integer divOrgId12;
    private Boolean divOrgActive12;
    
    private String divOrg13;
    private Integer divOrgId13;
    private Boolean divOrgActive13;
    
    private String divOrg21;
    private Integer divOrgId21;
    private Boolean divOrgActive21;
    
    private String divOrg22;
    private Integer divOrgId22;
    private Boolean divOrgActive22;
    
    private String divOrg23;
    private Integer divOrgId23;
    private Boolean divOrgActive23;
    
    private String divOrg31;
    private Integer divOrgId31;
    private Boolean divOrgActive31;
    
    private String divOrg32;
    private Integer divOrgId32;
    private Boolean divOrgActive32;
    
    private String divOrg33;
    private Integer divOrgId33;
    private Boolean divOrgActive33;
    
    private Integer corporation;
    private Integer country;

    private String city; // estado o localidad donde se ubica la ciudad (ubicacion del conductor)
    private String corporationId;
    private String email;
    private String phoneNumber;
    private String personalAddress;
    private String officeAddress;
    private Date inclusionDate;
    private Date exclusionDate;
	
    private String licenseNumber;
    private Date licenseExpiry;
    private String licenseObs;
    
    private TypeVehicle vehicleAllowed;
	

    private boolean drives;
    private boolean employee;
    private Contract contract;

    private String superviser;
    private String manager;
    
    private boolean licenseNotExpiry; // No tiene Vencimiento la libreta de conducir

    protected boolean temporal;
    protected boolean createdFromTablet; 
    
    protected boolean director; 

    public ClosedDriver() {
		
	}

	public Boolean getDivOrgActive11() {
		return divOrgActive11;
	}

	public void setDivOrgActive11(Boolean divOrgActive11) {
		this.divOrgActive11 = divOrgActive11;
	}

	public Boolean getDivOrgActive12() {
		return divOrgActive12;
	}

	public void setDivOrgActive12(Boolean divOrgActive12) {
		this.divOrgActive12 = divOrgActive12;
	}

	public Boolean getDivOrgActive13() {
		return divOrgActive13;
	}

	public void setDivOrgActive13(Boolean divOrgActive13) {
		this.divOrgActive13 = divOrgActive13;
	}

	public Boolean getDivOrgActive21() {
		return divOrgActive21;
	}

	public void setDivOrgActive21(Boolean divOrgActive21) {
		this.divOrgActive21 = divOrgActive21;
	}

	public Boolean getDivOrgActive22() {
		return divOrgActive22;
	}

	public void setDivOrgActive22(Boolean divOrgActive22) {
		this.divOrgActive22 = divOrgActive22;
	}

	public Boolean getDivOrgActive23() {
		return divOrgActive23;
	}

	public void setDivOrgActive23(Boolean divOrgActive23) {
		this.divOrgActive23 = divOrgActive23;
	}

	public Boolean getDivOrgActive31() {
		return divOrgActive31;
	}

	public void setDivOrgActive31(Boolean divOrgActive31) {
		this.divOrgActive31 = divOrgActive31;
	}

	public Boolean getDivOrgActive32() {
		return divOrgActive32;
	}

	public void setDivOrgActive32(Boolean divOrgActive32) {
		this.divOrgActive32 = divOrgActive32;
	}

	public Boolean getDivOrgActive33() {
		return divOrgActive33;
	}

	public void setDivOrgActive33(Boolean divOrgActive33) {
		this.divOrgActive33 = divOrgActive33;
	}

	public Integer getDivOrgId11() {
		return divOrgId11;
	}

	public void setDivOrgId11(Integer divOrgId11) {
		this.divOrgId11 = divOrgId11;
	}

	public Integer getDivOrgId12() {
		return divOrgId12;
	}

	public void setDivOrgId12(Integer divOrgId12) {
		this.divOrgId12 = divOrgId12;
	}

	public Integer getDivOrgId13() {
		return divOrgId13;
	}

	public void setDivOrgId13(Integer divOrgId13) {
		this.divOrgId13 = divOrgId13;
	}

	public Integer getDivOrgId21() {
		return divOrgId21;
	}

	public void setDivOrgId21(Integer divOrgId21) {
		this.divOrgId21 = divOrgId21;
	}

	public Integer getDivOrgId22() {
		return divOrgId22;
	}

	public void setDivOrgId22(Integer divOrgId22) {
		this.divOrgId22 = divOrgId22;
	}

	public Integer getDivOrgId23() {
		return divOrgId23;
	}

	public void setDivOrgId23(Integer divOrgId23) {
		this.divOrgId23 = divOrgId23;
	}

	public Integer getDivOrgId31() {
		return divOrgId31;
	}

	public void setDivOrgId31(Integer divOrgId31) {
		this.divOrgId31 = divOrgId31;
	}

	public Integer getDivOrgId32() {
		return divOrgId32;
	}

	public void setDivOrgId32(Integer divOrgId32) {
		this.divOrgId32 = divOrgId32;
	}

	public Integer getDivOrgId33() {
		return divOrgId33;
	}

	public void setDivOrgId33(Integer divOrgId33) {
		this.divOrgId33 = divOrgId33;
	}

	public ClosedDriverPK getPk() {
		return pk;
	}

	public void setPk(ClosedDriverPK pk) {
		this.pk = pk;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getDivOrg11() {
		return divOrg11;
	}

	public void setDivOrg11(String divOrg11) {
		this.divOrg11 = divOrg11;
	}

	public String getDivOrg12() {
		return divOrg12;
	}

	public void setDivOrg12(String divOrg12) {
		this.divOrg12 = divOrg12;
	}

	public String getDivOrg13() {
		return divOrg13;
	}

	public void setDivOrg13(String divOrg13) {
		this.divOrg13 = divOrg13;
	}

	public String getDivOrg21() {
		return divOrg21;
	}

	public void setDivOrg21(String divOrg21) {
		this.divOrg21 = divOrg21;
	}

	public String getDivOrg22() {
		return divOrg22;
	}

	public void setDivOrg22(String divOrg22) {
		this.divOrg22 = divOrg22;
	}

	public String getDivOrg23() {
		return divOrg23;
	}

	public void setDivOrg23(String divOrg23) {
		this.divOrg23 = divOrg23;
	}

	public String getDivOrg31() {
		return divOrg31;
	}

	public void setDivOrg31(String divOrg31) {
		this.divOrg31 = divOrg31;
	}

	public String getDivOrg32() {
		return divOrg32;
	}

	public void setDivOrg32(String divOrg32) {
		this.divOrg32 = divOrg32;
	}

	public String getDivOrg33() {
		return divOrg33;
	}

	public void setDivOrg33(String divOrg33) {
		this.divOrg33 = divOrg33;
	}

	public Integer getCorporation() {
		return corporation;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPersonalAddress() {
		return personalAddress;
	}

	public void setPersonalAddress(String personalAddress) {
		this.personalAddress = personalAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}

	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Date getLicenseExpiry() {
		return licenseExpiry;
	}

	public void setLicenseExpiry(Date licenseExpiry) {
		this.licenseExpiry = licenseExpiry;
	}

	public String getLicenseObs() {
		return licenseObs;
	}

	public void setLicenseObs(String licenseObs) {
		this.licenseObs = licenseObs;
	}

	public TypeVehicle getVehicleAllowed() {
		return vehicleAllowed;
	}

	public void setVehicleAllowed(TypeVehicle vehicleAllowed) {
		this.vehicleAllowed = vehicleAllowed;
	}

	public boolean isDrives() {
		return drives;
	}

	public void setDrives(boolean drives) {
		this.drives = drives;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
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

	public boolean isLicenseNotExpiry() {
		return licenseNotExpiry;
	}

	public void setLicenseNotExpiry(boolean licenseNotExpiry) {
		this.licenseNotExpiry = licenseNotExpiry;
	}

	public boolean isTemporal() {
		return temporal;
	}

	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

	public boolean isCreatedFromTablet() {
		return createdFromTablet;
	}

	public void setCreatedFromTablet(boolean createdFromTablet) {
		this.createdFromTablet = createdFromTablet;
	}

	public boolean isDirector() {
		return director;
	}

	public void setDirector(boolean director) {
		this.director = director;
	}

	public DetailedDriverReportDataType getData() throws Exception {
		Session session = HibernateUtil.currentSession();
		Country c = (Country)session.load(Country.class, new Integer(country));
		DetailedDriverReportDataType data = new DetailedDriverReportDataType(pk.getId(),firstName,lastName,c.getResourceKey(), 
	    		birthDate, exclusionDate, contract.getResourceBundleId(), divOrg11, 
	    		divOrg12, divOrg13, divOrg21, divOrg22, 
	    		divOrg23, divOrg31, divOrg32, divOrg33, 
	    		null, null, null);
		data.setAssignedVehicle(vehicleAllowed.getResourceBundleId());
	    data.setSex(sex.getResourceBundleId());

	    data.setCity(city); // estado o localidad donde se ubica la ciudad (ubicacion del conductor)
	    data.setCorporationId(corporationId);
	    data.setEmail(email);
	    data.setPhonenumber(phoneNumber);
	    data.setPersonaladdress(personalAddress);
	    data.setOfficeaddress(officeAddress);
	    data.setInclusiondate(inclusionDate);
		
	    data.setLicensenumber(licenseNumber);
	    data.setLicenseExpiry(licenseExpiry);
	    data.setLicenseObs(licenseObs);
	    
	    data.setDrives(drives);
	    data.setEmployee(employee);

	    data.setSuperviser(superviser);
	    data.setManager(manager);
	    
	    data.setLicenseNotExpiry(licenseNotExpiry); // No tiene Vencimiento la libreta de conducir
	    data.setDirector(director); 
		return data;
	}
	
}
