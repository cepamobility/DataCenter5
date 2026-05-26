package persistence.activity.tables;

import java.io.Serializable;


public class ActivityLinkPK implements Serializable {
	
	private Integer activity;
	private Integer driver;
	
	public ActivityLinkPK() {
	}

	public ActivityLinkPK(Integer[] data) {
		activity = data[0];
		driver= data[1];
	}

	public ActivityLinkPK(Integer activity, Integer driver) {
		this.activity = activity;
		this.driver= driver;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getDriver() {
		return driver;
	}

	public void setDriver(Integer driver) {
		this.driver = driver;
	}
}
