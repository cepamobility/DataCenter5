/**
 * Created on 06-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.activity.schedule;

import presentation.translator.web.driver.DriverDivOrgForm;

public class ActivityReportFormAllDriversOld extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    //private String output;
    
    private String idActivity;
    
    /**
     * Lista de idDriver separadas por "|".
     */
    private String idDrivers;
    
    //private String reportType;
    
        
    /**
	 * 
	 */
	public ActivityReportFormAllDriversOld() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param output
	 * @param idActivity
	 * @param idDrivers
	 * @param reportType
	 */
	public ActivityReportFormAllDriversOld(/*String output,*/ 
			String idActivity, String idDrivers /*,String reportType*/ ) {
		super();
		//this.output = output;
		this.idActivity = idActivity;
		this.idDrivers = idDrivers;
		//this.reportType = reportType;
	}


	/**
	 * @return Returns the reportType.
	 */
	//public String getReportType() {
	//	return reportType;
	//}


	/**
	 * @param reportType The reportType to set.
	 */
	//public void setReportType(String reportType) {
	//	this.reportType = reportType;
	//}



	public String getIdActivity() {
		return idActivity;
	}


	public void setIdActivity(String idActivity) {
		this.idActivity = idActivity;
	}


	public String getIdDrivers() {
		return idDrivers;
	}


	public void setIdDriver(String idDrivers) {
		this.idDrivers = idDrivers;
	}


	//public String getOutput() {
	//	return output;
	//}


	//public void setOutput(String output) {
	//	this.output = output;
	//}

    
}
