package communication.external.instructors;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverRestData {
	
	private Integer id;
	private Integer corporation;
	private String firstName;
	private String lastName;
	private String email;
	private String sex;
	private String corporationid;
	private String countryName;
	private String divOrg1Name;
	private String divOrg2Name;
	private String divOrg3Name;
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getDivOrg1Name() {
		return divOrg1Name;
	}
	public void setDivOrg1Name(String divOrg1Name) {
		this.divOrg1Name = divOrg1Name;
	}
	public String getDivOrg2Name() {
		return divOrg2Name;
	}
	public void setDivOrg2Name(String divOrg2Name) {
		this.divOrg2Name = divOrg2Name;
	}
	public String getDivOrg3Name() {
		return divOrg3Name;
	}
	public void setDivOrg3Name(String divOrg3Name) {
		this.divOrg3Name = divOrg3Name;
	}
	public Integer getCorporation() {
		return corporation;
	}
	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}
	public String getCorporationid() {
		return corporationid;
	}
	public void setCorporationid(String corporationid) {
		this.corporationid = corporationid;
	}
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
	

}
