package communication.activity.tables;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.administration.corporation.util.Util;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class InstructorActivity implements Comparable<InstructorActivity> {

	private Integer id;
	private Date date;
	private String type;
	private String city;
	private String[] divorgs;
	private Collection<DriverActivity> drivers;
	private String state;

	public InstructorActivity(Object[] data) {
		id = (Integer) data[0];
		date = (Date) data[1];
		type = (String) data[2];
		state = (String) data[3];
		city = (String) data[4];
		divorgs = new String[data.length-5];
		for(int i = 0; i < divorgs.length; i++) {
			divorgs[i] = (String) data[i+5];
		}
		drivers = new LinkedList<DriverActivity>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDivOrgs(UserSessionData userSessionData, Text messages) {
		String s = "<tr class='lineone'>";
		String[] principalLabels = userSessionData.getFilter().getPrincipalLabels();
		for (int z = 0; z < principalLabels.length; z++){ 
			if(z == 2) {
				s += "</tr><tr class='lineone'>";
			}
			s += "<th align='left'>"+messages.getText(principalLabels[z])+":</th>";
			s += "<td align='left'>"+divorgs[z]+"</td>";
		}
		s += "</tr>";
		return s;
	}

	public void addDriver(Object[] driver) {
		drivers.add(new DriverActivity(driver));
	}

	public Iterator<DriverActivity> getDriversIterator() {
		Collections.sort((List<DriverActivity>)drivers);
		return drivers.iterator();
	}

	@Override
	public int compareTo(InstructorActivity o) {
		return o.date.compareTo(date);
	}
	
	public boolean hasDrivers() {
		return drivers != null && drivers.size() > 0;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle(Text messages) {
		String s = "<a href='layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?id="+id+"'>";
		s += Util.formatDate(date)+" - "+messages.getText(type);
		s += "</a>";
		return s;
	}

	public int getDriverCount() {
		if(drivers == null)
			return 0;
		return drivers.size();
	}
}
