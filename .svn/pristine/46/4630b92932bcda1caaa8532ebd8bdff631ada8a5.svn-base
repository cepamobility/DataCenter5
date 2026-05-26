package persistence.log.tables;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Loggings implements Serializable{
	
	private Timestamp   log_date;
	private String location;
	private String message;
	private String log_level;
	private String userID;
	private Integer id;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Loggings() {
		// TODO Auto-generated method stub
		log_date=new Timestamp(GregorianCalendar.getInstance().getTimeInMillis());
	}
	
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getLog_date() {
		return log_date;
	}

	public void setLog_date(Timestamp log_date) {
		this.log_date = log_date;
	}

	public String getLog_level() {
		return log_level;
	}

	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
