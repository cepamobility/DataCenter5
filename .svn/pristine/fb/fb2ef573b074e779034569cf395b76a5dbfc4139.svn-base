package communication.activity.tables;

import communication.driver.tables.DriverData;

public class ActivityRegistryData {

	private DriverData driver;
	private boolean hasResult;
	private String note;
	private String obs;
	
	public ActivityRegistryData() {
	}
	
	public ActivityRegistryData(Object[] data) {
		driver = new DriverData((Integer)data[0]);
		driver.setFirstName((String)data[1]);
		driver.setLastName((String)data[2]);
		driver.setTemporal(((Boolean)data[3]).booleanValue());
		note = (String)data[4];
		obs = (String)data[5];
		hasResult = ((Boolean)data[4]).booleanValue();
	}

	public DriverData getDriver() {
		return driver;
	}

	public boolean isHasResult() {
		return hasResult;
	}

	public String getNote() {
		return note;
	}

	public String getObs() {
		return obs;
	}

	
}
