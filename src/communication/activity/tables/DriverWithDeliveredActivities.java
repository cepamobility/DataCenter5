package communication.activity.tables;

import java.io.Serializable;
import java.util.Date;

import communication.driver.report.DriverReportDataType;

//public class DriverWithExpiredActivities extends ActivityPrincipalAttributes implements Serializable {

public class DriverWithDeliveredActivities implements Serializable {
    
	protected DriverReportDataType driver;
	protected Date date;
	protected String type;
	protected String note;
	protected String city;
	protected String instructor;
	protected String theme;
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DriverWithDeliveredActivities(DriverReportDataType driver,String type,String note,String city, String instructor, String theme, Date date) {
		this.driver = driver;
		this.date = date;
		this.type = type;
		this.note = note;
		this.city = city;
		this.instructor = instructor;
		this.theme = theme;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DriverReportDataType getDriver() {
		return driver;
	}

	public void setDriver(DriverReportDataType driver) {
		this.driver = driver;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDivOrg(int index, int j) {
		return driver.getDivOrg(index, j);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
