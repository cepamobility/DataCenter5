
package persistence.event.tables;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.driver.tables.Driver;
import persistence.vehicle.tables.Vehicle;

import communication.event.tables.ReparationData;

public class Reparation {
	
    protected Integer id;

    protected Date date;

    protected Driver driver;
    protected Vehicle vehicle;
    protected Corporation corporation;

    protected Integer maintenance1;
    protected Integer maintenance2;
    protected String observation; 
    
    protected Integer status;
    protected Integer kms;
    protected String items; 

    protected Date inserted;

	public Reparation() {
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCoporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public Integer getMaintenance1() {
		return maintenance1;
	}

	public void setMaintenance1(Integer maintenance1) {
		this.maintenance1 = maintenance1;
	}

	public Integer getMaintenance2() {
		return maintenance2;
	}

	public void setMaintenance2(Integer maintenance2) {
		this.maintenance2 = maintenance2;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ReparationData getData() {
		ReparationData data = new ReparationData(id);
		data.setVehicle(vehicle.getPrincipalAttributes());
		data.setDriver(driver.getDriverData());
		data.setDate(date);
		data.setStatus(status);
		data.setObservation(observation);
		data.setKms(kms);
		data.setInserted(inserted);
		return data;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public void updateData(ReparationData reparation, Session session, int corporationId, boolean create) {
		if(create) {
			driver = (Driver)session.load(Driver.class, reparation.getDriver().getId());
			vehicle = (Vehicle)session.load(Vehicle.class, reparation.getVehicle().getId());
			corporation = (Corporation) session.load(Corporation.class, vehicle.getCorporation().getId());
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			c.add(Calendar.HOUR, -3);
			c.add(Calendar.MILLISECOND, -c.getTimeZone().getOffset(c.getTimeInMillis()));
			inserted = c.getTime();
		}
		date = reparation.getDate();
		observation = reparation.getObservation();
		status = reparation.getStatus();
		kms = reparation.getKms();
	}

	public Integer getKms() {
		return kms;
	}

	public void setKms(Integer kms) {
		this.kms = kms;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public Date getInserted() {
		return inserted;
	}

	public void setInserted(Date inserted) {
		this.inserted = inserted;
	}
    
	
}
