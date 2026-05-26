package communication.driver.tables;

import communication.administration.corporation.tables.PointAccountData;

public class DriverStatusData  {

	private DriverData driver;
    private PointAccountData pointAccount;
    
	public DriverData getDriver() {
		return driver;
	}
	public void setDriver(DriverData driver) {
		this.driver = driver;
	}
	public PointAccountData getPointAccount() {
		return pointAccount;
	}
	public void setPointAccount(PointAccountData pointAccount) {
		this.pointAccount = pointAccount;
	}
    
 
}
