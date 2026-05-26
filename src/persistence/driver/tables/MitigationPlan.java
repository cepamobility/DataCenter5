/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import communication.administration.user.UserSessionData;
import communication.driver.tables.MitigationPlanActivityData;
import communication.driver.tables.MitigationPlanData;

public class MitigationPlan implements Serializable{


	private static final long serialVersionUID = 8235161610226719402L;
	
	private Integer id;
	
    private Driver driver;

    private Date date;
    private Date expiry;
    private Integer type;
    private Integer status;
    private Integer advance;
	    
    private Set<MitigationPlanActivity> activitySet;

    public MitigationPlan() {
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

	public Integer getAdvance() {
		return advance;
	}

	public void setAdvance(Integer advance) {
		this.advance = advance;
	}

	public Set getActivitySet() {
		return activitySet;
	}

	public void setActivitySet(Set activitySet) {
		this.activitySet = activitySet;
	}

	public MitigationPlanData getData(UserSessionData userSessionData) {
		MitigationPlanData data = new MitigationPlanData();
		data.setDriver(driver.getPrincipalAttributes(userSessionData));
		data.setAdvance(advance);
		data.setDate(date);
		data.setExpiry(expiry);
		data.setId(id);
		data.setStatus(status);
		data.setType(type);
		Collection<MitigationPlanActivityData> activities = new LinkedList<MitigationPlanActivityData>();
		Iterator<MitigationPlanActivity> it = activitySet.iterator();
		while(it.hasNext()) {
			activities.add(it.next().getData());
		}
		data.setActivities(activities);
		return data;
	}
}
