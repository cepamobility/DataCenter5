package persistence.driver.tables;

import java.io.Serializable;

import org.hibernate.Session;

import persistence.datatype.tables.CEPAActivityType;
import persistence.hibernate.HibernateUtil;

public class DriverLogisticPK implements Serializable {

	private Driver driver;
	private CEPAActivityType activity;
	
	public DriverLogisticPK() {
	}

	public DriverLogisticPK(Integer driverId, String activityType) {
    	Session session = HibernateUtil.currentSession();
    	driver = (Driver)session.load(Driver.class, driverId);
    	activity = (CEPAActivityType)session.load(CEPAActivityType.class, activityType);
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public CEPAActivityType getActivity() {
		return activity;
	}

	public void setActivity(CEPAActivityType activity) {
		this.activity = activity;
	}
}
