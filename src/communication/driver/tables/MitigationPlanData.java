/**
 * CEPA
 * DataCenter 5
 */
package communication.driver.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MitigationPlanData implements Serializable{

	public static final Integer CANCELLED = -1;
	public static final Integer PENDING = 0;
	public static final Integer COMPLETE = 1;

	private Integer id;
	
    private DriverMainAttributes driver;

    private Date date;
    private Date expiry;
    private Integer type;
    private Integer status;
    private Integer advance;
	    
    private Collection<MitigationPlanActivityData> activities;
    
    public static Integer BLACK = 3;
    public static Integer RED = 2;
    public static Integer YELLOW = 1;
    public static Integer GREEN = 0;

    public MitigationPlanData() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DriverMainAttributes getDriver() {
		return driver;
	}

	public void setDriver(DriverMainAttributes driver) {
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

	public Collection<MitigationPlanActivityData> getActivities() {
		return activities;
	}

	public void setActivities(Collection<MitigationPlanActivityData> activities) {
		this.activities = activities;
	}

	public Iterator<MitigationPlanActivityData> getActivityIterator() {
		Collections.sort((List)activities);
		return activities.iterator();
	}
}
