package communication.util;

import java.util.Date;

public class DriverAccessLogData implements Comparable {

	private String firstName;
	private String lastName;
	private String corporationId;
	private String contratist;
	private String user;
	private Date date;
	private Integer access;
	
	public DriverAccessLogData(Object[] data) {
		firstName = (String)data[0];
		lastName = (String)data[1];
		corporationId = (String)data[2];
		contratist = (String)data[3];
		user = (String)data[4];
		date = (Date)data[5];
		access = (Integer)data[6];
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

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getContratist() {
		return contratist;
	}

	public void setContratist(String contratist) {
		this.contratist = contratist;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public int compareTo(Object o) {
		return date.compareTo(((DriverAccessLogData)o).date);
	}

	
}
