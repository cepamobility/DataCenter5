package communication.driver.report;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import communication.event.tables.EventDataType;

public class CombinedHistoryDataType {

    protected Integer id;
    protected String firstname;
    protected String lastname;
    protected String country;
    protected Date birthdate;
    protected Date exclusiondate;
    protected String contract;
    protected String divorg1;
    protected String divorg2;
    protected String divorg3;
	
    protected Date lastUpdate = null;
    
    protected Collection<EventCombinedDataType> events;

    public CombinedHistoryDataType(Object[] data, Date update) {
        id = (Integer)data[0];
        firstname = (String)data[1];
        lastname = (String)data[2];
        birthdate = (Date)data[3];
        exclusiondate = (Date)data[4];
        contract = (String)data[5];
        country = (String)data[6];

        if(data.length > 7) {
            divorg1 = (String)data[7];
        }
        if(data.length > 8) {
            divorg2 = (String)data[8];
        }
        if(data.length > 9) {
            divorg3 = (String)data[9];
        }
        lastUpdate = update;
        events = new LinkedList<EventCombinedDataType>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getExclusiondate() {
		return exclusiondate;
	}

	public void setExclusiondate(Date exclusiondate) {
		this.exclusiondate = exclusiondate;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getDivorg1() {
		return divorg1;
	}

	public void setDivorg1(String divorg1) {
		this.divorg1 = divorg1;
	}

	public String getDivorg2() {
		return divorg2;
	}

	public void setDivorg2(String divorg2) {
		this.divorg2 = divorg2;
	}

	public String getDivorg3() {
		return divorg3;
	}

	public void setDivorg3(String divorg3) {
		this.divorg3 = divorg3;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Collection<EventCombinedDataType> getEvents() {
		return events;
	}

	public void setEvents(Collection<EventCombinedDataType> events) {
		this.events = events;
	}

	public void addEvent(Object[] data) {
		events.add(new EventCombinedDataType(data));
	}

	public int getEventCount() {
		if(events.size() == 0) {
			return 1;
		}else {
			return events.size();
		}
	}
}
