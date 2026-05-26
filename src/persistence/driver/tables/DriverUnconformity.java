/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import persistence.datatype.tables.UnconformityType;

import communication.administration.user.UserSessionData;
import communication.datatype.tables.UnconformityDataType;
import communication.driver.tables.UnconformityData;


public class DriverUnconformity {

    private Integer id;
    private Driver driver;
    private Date date;
    private UnconformityType type;
    private String observations;
    
	public DriverUnconformity() {
	}

	public DriverUnconformity(Integer driverId, Date date, String typeId, String observations, Session session) {
		this.driver = (Driver)session.load(Driver.class, driverId);
		this.date = date;
		this.type = (UnconformityType)session.load(UnconformityType.class, typeId);
		this.observations = observations;
	}

	public DriverUnconformity(Integer driverId, UnconformityData data, Session session,UserSessionData userSessionData) {
		this.driver = (Driver)session.load(Driver.class, driverId);
		this.date = data.getDate();
		Query q = session.createQuery("SELECT type FROM UnconformityType type WHERE resourceBundleId = '"+data.getType().getResourceBundleId()+"' AND corporationId = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
		this.type = (UnconformityType)q.uniqueResult();
		this.observations = data.getObservations();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UnconformityType getType() {
		return type;
	}

	public void setType(UnconformityType type) {
		this.type = type;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public UnconformityData getData() {
		return new UnconformityData(id,date,(UnconformityDataType)type.getData(),observations);
	}
}
