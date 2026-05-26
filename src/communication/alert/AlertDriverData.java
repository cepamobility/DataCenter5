package communication.alert;

import java.io.Serializable;
import java.util.Date;

import communication.driver.report.DetailedDriverReportDataType;
import communication.driver.report.DriverReportDataType;
import communication.driver.tables.DriverData;

public class AlertDriverData implements Serializable{
	
	private DriverReportDataType  driverReportDataType ;
	
	private Date expiry;
	
	public AlertDriverData(DriverReportDataType  driver,Date expiry){
		this.driverReportDataType=driver;
		this.expiry=expiry;
	}

 

	 


	public DriverReportDataType getDriverReportDataType() {
		return driverReportDataType;
	}






	public void setDriverReportDataType(DriverReportDataType driverReportDataType) {
		this.driverReportDataType = driverReportDataType;
	}






	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	
	

}
