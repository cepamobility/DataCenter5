package presentation.translator.web.driver;


public class DriverListTelematicControlForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String driver;
	private String[] deleteIds;
    

    public DriverListTelematicControlForm() {
    }


	public String[] getDeleteIds() {
		return deleteIds;
	}


	public void setDeleteIds(String[] deleteIds) {
		this.deleteIds = deleteIds;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}
    
    

}
