package communication.activity.tables;

import java.util.Date;

import persistence.util.Util;

import communication.driver.tables.DriverMainAttributes;

public class DriverActivity implements Comparable<DriverActivity> {

	public DriverMainAttributes driver;
	public String instructor;
	public String user;
	public Date date;
	
	public DriverActivity(Object[] data) {
		this.driver = new DriverMainAttributes((Integer)data[2],(String)data[3],(String)data[4],null,null,null,null);
		this.instructor = (String)data[1];
		this.user = (String)data[5];
		this.date = (Date)data[6];
	}

	public DriverMainAttributes getDriver() {
		return driver;
	}

	public void setDriver(DriverMainAttributes driver) {
		this.driver = driver;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
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
	
	public String getTableLine(boolean lineone) {
		String line = (lineone) ? "<tr class='lineone'>" : "<tr class='linetwo'>";
		line += "<td><a href='layout.jsp?refer=/driver/DriverView.jsp?id="+driver.getId()+"'>"+driver.getFirstName()+ " " + driver.getLastName() +"</a></td>";
		line += "<td>"+instructor +"</td>";
		line += (user == null) ? "<td>---</td>" : "<td>"+user+"</td>";
		line += (date == null) ? "<td>---</td>" : "<td>"+Util.formatDate(date)+"</td>";
		line += "</tr>";
		return line;
	}


	@Override
	public int compareTo(DriverActivity o) {
		return new String(driver.getFirstName()+" "+driver.getLastName()).compareTo(o.driver.getFirstName()+" "+o.driver.getLastName());
	}
}
