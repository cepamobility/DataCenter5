/**
 * Created on 06-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.activity.schedule;

import presentation.translator.web.driver.DriverDivOrgForm;

public class ActivityProvisionalBTWForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String output;
    
    
    private String idDriver;
    
    private String result;
    private String date;
 


	 
 

	 


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getIdDriver() {
		return idDriver;
	}


	public void setIdDriver(String idDriver) {
		this.idDriver = idDriver;
	}


	public String getOutput() {
		return output;
	}


	public void setOutput(String output) {
		this.output = output;
	}

    
}
