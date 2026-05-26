package communication.activity.tables;

import java.io.Serializable;
import java.util.Date;

import communication.driver.report.DriverReportDataType;

public class DriversBTWData implements Serializable {
		
	private DriverReportDataType driverReport;
    private Date lastDate;
    private String type;
    
	public DriversBTWData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param driverReport
	 * @param lastBTWdate
	 * @param btwType
	 */
	public DriversBTWData(DriverReportDataType driverReport, Date lastDate, String type) {
		super();
		// TODO Auto-generated constructor stub
		this.driverReport = driverReport;
		this.lastDate = lastDate;
		this.type = type;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DriverReportDataType getDriverReport() {
		return driverReport;
	}

	public void setDriverReport(DriverReportDataType driverReport) {
		this.driverReport = driverReport;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}



	public String getDivOrg(int i, int j) {
		// TODO Auto-generated method stub
		return driverReport.getDivOrg(i, j);
	}
	

}
