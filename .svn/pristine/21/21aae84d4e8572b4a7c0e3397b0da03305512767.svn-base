/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.classic.Session;

import communication.administration.user.UserSessionData;
import communication.driver.tables.AnualPlanData;
import communication.driver.tables.MitigationPlanActivityData;
import communication.driver.tables.MitigationPlanData;

public class AnualPlan implements Serializable{


	private static final long serialVersionUID = 8235161610226719402L;
	
	private Integer id;
	
    private Driver driver;

    private Integer year;
    private Date date;
    private Date dateStart;
    private Date dateSchedule;
    private Date expiry;
    private Integer type;
    private Integer status;
    private Integer resullt;
	
    private Date alert1;
    private Date alert2;
    private Date alert3;

    private boolean expired;
    
    public AnualPlan() {
    }

    public AnualPlan(Driver driver, Integer activity, Date start, Integer days, Integer alert1Days, Integer alert2Days, Integer alert3Days) {
		this.driver = driver;
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		c.setTime(start);
		dateStart = start;
		expiry = getTime(start,days);
		alert1 = getTime(start, alert1Days);
		alert2 = getTime(start, alert2Days);
		alert3 = getTime(start, alert3Days);
		type = activity;
		status = 0;
	}

	private Date getTime(Date start, Integer days) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateSchedule() {
		return dateSchedule;
	}

	public void setDateSchedule(Date dateSchedule) {
		this.dateSchedule = dateSchedule;
	}

	public Integer getResullt() {
		return resullt;
	}

	public void setResullt(Integer resullt) {
		this.resullt = resullt;
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

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public AnualPlanData getData(UserSessionData userSessionData) {
		AnualPlanData data=new AnualPlanData();
		data.setDate(date);
		data.setDateSchedule(dateSchedule);
		data.setDateStart(dateStart);
		data.setDriver(driver.getDriverData());
		data.setExpiry(expiry);
		data.setId(id);
		data.setResullt(resullt);
		data.setStatus(status);
		data.setType(type);
		data.setYear(year);
		
		return data;
	}

	public Date getAlert1() {
		return alert1;
	}

	public void setAlert1(Date alert1) {
		this.alert1 = alert1;
	}

	public Date getAlert2() {
		return alert2;
	}

	public void setAlert2(Date alert2) {
		this.alert2 = alert2;
	}

	public Date getAlert3() {
		return alert3;
	}

	public void setAlert3(Date alert3) {
		this.alert3 = alert3;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

}
