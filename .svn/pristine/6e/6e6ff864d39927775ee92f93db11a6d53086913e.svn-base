/**
 * 
 */
package communication.activity.report;

import java.io.Serializable;

import communication.driver.report.DriverReportDataType;

/**
 * @author gbenaderet
 *
 */
public class ActivityReportCommentaryDriveDataType implements Comparable, Serializable{

	private String lastName;
	private String firstName;
	private Integer id;
	private String country;
	private String division;
	private String unidad;
	private String subDivision;
	private Integer count; // Cantidad de Commentary Drives que tiene
	
    private String divorg11;
    private String divorg12;
    private String divorg13;
    private String divorg21;
    private String divorg22;
    private String divorg23;
    private String divorg31;
    private String divorg32;
    private String divorg33;	
    
	protected DriverReportDataType driver;    
	
	
	/**
	 * @param lastName
	 * @param firstName
	 * @param id
	 * @param country
	 * @param division	 * 
	 * @param subDivision
	 * @param unidad
	 * @param count
	 */
	/*
	public ActivityReportCommentaryDriveDataType(String lastName, 
			String firstName, Integer id, String country, 
			String division, String subDivision, String unidad, 
			Integer count) {		
		this.lastName = lastName;
		this.firstName = firstName;
		this.id = id;
		this.country = country;
		this.division = division;
		this.subDivision = subDivision;
		this.unidad = unidad;
		this.count = count;
	}

	*/
	
	public ActivityReportCommentaryDriveDataType(DriverReportDataType driver,Integer count) {		
		this.driver = driver;
		this.count = count;
	}	
	
	public ActivityReportCommentaryDriveDataType() {	
	}

	public String getCompleteName()
	{
		return this.driver.getLastname() + " "+ this.driver.getFirstname();
	}

	/**
	 * @return Returns the unidad.
	 */
	public String getUnidad() {
		return unidad;
	}

	/**
	 * @param unidad The unidad to set.
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	/**
	 * @return Returns the count.
	 */
	public Integer getCount() {
		return count;
	}



	/**
	 * @param count The count to set.
	 */
	public void setCount(Integer count) {
		this.count = count;
	}



	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}



	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}



	/**
	 * @return Returns the division.
	 */
	public String getDivision() {
		return division;
	}



	/**
	 * @param division The division to set.
	 */
	public void setDivision(String division) {
		this.division = division;
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
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return Returns the subDivision.
	 */
	public String getSubDivision() {
		return subDivision;
	}



	/**
	 * @param subDivision The subDivision to set.
	 */
	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}



	public int compareTo(Object arg0) {
        return lastName.toLowerCase().compareTo(((ActivityReportCommentaryDriveDataType)arg0).lastName.toLowerCase());
    }

	public String getDivorg11() {
		return divorg11;
	}

	public void setDivorg11(String divorg11) {
		this.divorg11 = divorg11;
	}

	public String getDivorg12() {
		return divorg12;
	}

	public void setDivorg12(String divorg12) {
		this.divorg12 = divorg12;
	}

	public String getDivorg13() {
		return divorg13;
	}

	public void setDivorg13(String divorg13) {
		this.divorg13 = divorg13;
	}

	public String getDivorg21() {
		return divorg21;
	}

	public void setDivorg21(String divorg21) {
		this.divorg21 = divorg21;
	}

	public String getDivorg22() {
		return divorg22;
	}

	public void setDivorg22(String divorg22) {
		this.divorg22 = divorg22;
	}

	public String getDivorg23() {
		return divorg23;
	}

	public void setDivorg23(String divorg23) {
		this.divorg23 = divorg23;
	}

	public String getDivorg31() {
		return divorg31;
	}

	public void setDivorg31(String divorg31) {
		this.divorg31 = divorg31;
	}

	public String getDivorg32() {
		return divorg32;
	}

	public void setDivorg32(String divorg32) {
		this.divorg32 = divorg32;
	}

	public String getDivorg33() {
		return divorg33;
	}

	public void setDivorg33(String divorg33) {
		this.divorg33 = divorg33;
	}

	public DriverReportDataType getDriver() {
		return driver;
	}

	public void setDriver(DriverReportDataType driver) {
		this.driver = driver;
	}

	public String getDivOrg(int index, int j) {
		return driver.getDivOrg(index, j);
	}
}
