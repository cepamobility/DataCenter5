package communication.util.tables;

import communication.language.Text;

public class ResumeData {

	private ResumeItem drivers;
	private ResumeItem vehicles;
	private ResumeItem events;
	private ResumeItem activities;
	
	private ResumeAPMM[] apmm;
	
	public ResumeData() {
	}

	public ResumeItem getDrivers() {
		return drivers;
	}

	public void setDrivers(ResumeItem drivers) {
		this.drivers = drivers;
	}

	public ResumeItem getVehicles() {
		return vehicles;
	}

	public void setVehicles(ResumeItem vehicles) {
		this.vehicles = vehicles;
	}
	
	public ResumeItem getEvents() {
		return events;
	}

	public void setEvents(ResumeItem events) {
		this.events = events;
	}

	public ResumeItem getActivities() {
		return activities;
	}

	public void setActivities(ResumeItem activities) {
		this.activities = activities;
	}

	public String printDrivers(Text messages) {
		String s = "";
		if(drivers != null)
			s += drivers.print("1", 1, "driver", messages, 2, "driverForm");
		return s;
	}
	
	public String printVehicles(Text messages) {
		String s = "";
		if(vehicles != null)
			s += vehicles.print("1", 1, "vehicle", messages, 2, "vehicleForm");
		return s;
	}
	
	public String printEvents(Text messages) {
		String s = "";
		if(events != null)
			s += events.print("1", 1, "event", messages, 4, "eventForm");
		return s;
	}
	
	public String printActivities(Text messages) {
		String s = "";
		if(activities != null)
			s += activities.print("1", 1, "activity", messages, 4, "activityForm");
		return s;
	}

	public ResumeAPMM[] getApmm() {
		return apmm;
	}

	public void setApmm(ResumeAPMM[] apmm) {
		this.apmm = apmm;
	}
}
