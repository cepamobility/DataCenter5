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
import communication.driver.tables.AnualPlanData;
import communication.driver.tables.MitigationPlanActivityData;
import communication.driver.tables.MitigationPlanData;

public class AnualPlanDefinition implements Serializable{

	private static final long serialVersionUID = 8235161610226719402L;
	
	private Integer id;
    private Integer plan;
    private Integer activity;
    private Integer startType;
    private Date start;
    private Integer days;
  
    private Integer alert1;
    private Integer alert2;
    private Integer alert3;

    public AnualPlanDefinition() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlan() {
		return plan;
	}

	public void setPlan(Integer plan) {
		this.plan = plan;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getStartType() {
		return startType;
	}

	public void setStartType(Integer startType) {
		this.startType = startType;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getAlert1() {
		return alert1;
	}

	public void setAlert1(Integer alert1) {
		this.alert1 = alert1;
	}

	public Integer getAlert2() {
		return alert2;
	}

	public void setAlert2(Integer alert2) {
		this.alert2 = alert2;
	}

	public Integer getAlert3() {
		return alert3;
	}

	public void setAlert3(Integer alert3) {
		this.alert3 = alert3;
	}

}
