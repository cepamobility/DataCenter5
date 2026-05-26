package communication.activity.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import persistence.util.Util;

import communication.driver.tables.DriverLogisticData;
import communication.language.Text;

public class DriverMaxExpiry implements Comparable {

	private Integer id;
	private String firstName;
	private boolean director;
	private String email;
	private String allowed;
	private String licenseObs;
    private String country;
    private String city;
    private String phoneNumber;
    
    private Date[] expiry;
    private String[] divorgs;
    
    private Collection<DriverLogisticData> logistics;
	
	public DriverMaxExpiry(Object[] data,int types,int index) {
		id = (Integer)data[0];
		firstName = (String)data[1] + " " + (String)data[2];
		director = ((Boolean)data[3]).booleanValue();
		email = (String)data[4];
		allowed = (String)data[5];
		licenseObs = (String)data[6];
		country = (String)data[8];
		city = (String)data[10];
		phoneNumber = (String)data[11];
		expiry = new Date[types];
		expiry[index] = (Date)data[7];
		divorgs = new String[11];
		for(int i = 12; i < data.length; i++)
			divorgs[i-12] = (String)data[i];
		logistics = new LinkedList<DriverLogisticData>(); 
	}

	public DriverMaxExpiry(Object[] data) {
		id = (Integer)data[0];
		firstName = (String)data[1] + " " + (String)data[2];
		director = ((Boolean)data[3]).booleanValue();
		email = (String)data[4];
		allowed = (String)data[5];
		licenseObs = (String)data[6];
		country = (String)data[7];
		city = (String)data[8];
		phoneNumber = (String)data[9];
		divorgs = new String[11];
		expiry = new Date[0];
		for(int i = 10; i < data.length; i++)
			divorgs[i-10] = (String)data[i];
		logistics = new LinkedList<DriverLogisticData>(); 
	}

	public void setMaxExpiry(int index,Object[] data) {
		if(expiry[index] == null)
			expiry[index] = (Date)data[7];
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAllowed() {
		return allowed;
	}

	public void setAllowed(String allowed) {
		this.allowed = allowed;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date[] getExpiry() {
		return expiry;
	}

	public void setExpiry(Date[] expiry) {
		this.expiry = expiry;
	}

	public String[] getDivorgs() {
		return divorgs;
	}

	public void setDivorgs(String[] divorgs) {
		this.divorgs = divorgs;
	}

	public ArrayList<ArrayList<Object>> getLineJJ(Text messages, int length) {
		ArrayList<ArrayList<Object>> values = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> line = new ArrayList<Object>();
		line.add(firstName);
		line.add(messages.getText(country));
		line.add(email);
	    for(int i = 0; i < length; i++) {
	    	if(divorgs[i] != null)
	    		line.add(divorgs[i]);
	    }
	    for(int i = 0; i < expiry.length; i++) {
	    	line.add(expiry[i]);
	    }
	    values.add(line);
		return values;
	}
	
	public ArrayList<ArrayList<Object>> getLine(Text messages, int length) {
		ArrayList<ArrayList<Object>> values = new ArrayList<ArrayList<Object>>();
		if(logistics.size() == 0) {
			ArrayList<Object> line = new ArrayList<Object>();
			line.add(firstName);
			line.add((director) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no"));
			line.add(email);
			line.add(messages.getText(allowed));
			line.add(licenseObs);
			line.add(messages.getText(country));
			line.add(city);
			line.add(phoneNumber);
			for(int i = 0; i < 5; i++)
				line.add("");
		    for(int i = 0; i < length; i++) {
		    	if(divorgs[i] != null)
		    		line.add(divorgs[i]);
		    }
		    for(int i = 0; i < expiry.length; i++) {
		    	line.add(expiry[i]);
		    }
		    values.add(line);
		}else {
			Iterator<DriverLogisticData> it = logistics.iterator();
			while(it.hasNext()) {
				DriverLogisticData dld = it.next();
				ArrayList<Object> line = new ArrayList<Object>();
				line.add(firstName);
				line.add((director) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no"));
				line.add(email);
				line.add(messages.getText(allowed));
				line.add(licenseObs);
				line.add(messages.getText(country));
				line.add(city);
				line.add(phoneNumber);
				line.add(messages.getText(dld.getActivity()));
				line.add(Util.formatDate(dld.getDateLastSchedule()));
				line.add(dld.getCity());
				line.add(messages.getText("driverlogistic.status."+dld.getStatus()));
				line.add(dld.getObservations());
			    for(int i = 0; i < length; i++) {
			    	if(divorgs[i] != null)
			    		line.add(divorgs[i]);
			    }
			    for(int i = 0; i < expiry.length; i++) {
			    	line.add(expiry[i]);
			    }
			    values.add(line);
			}
		}
		return values;
	}

	public void setLogisticData(Object[] data) {
 	}

	public void addLogisticData(Object[] data) {
		DriverLogisticData dld = new DriverLogisticData();
		dld.setDriverId((Integer)data[0]);
		dld.setActivity((String)data[1]);
		dld.setDateLastSchedule((Date)data[2]);
		dld.setCity((String)data[3]);
		dld.setStatus((Integer)data[4]);
		dld.setObservations((String)data[5]);
		logistics.add(dld);
	}

	@Override
	public int compareTo(Object o) {
		return firstName.compareTo(((DriverMaxExpiry)o).firstName);
	}

}
