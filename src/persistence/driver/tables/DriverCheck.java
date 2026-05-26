/**
 * CEPA
 * FDM
 */
package persistence.driver.tables;


public class DriverCheck {

    private DriverCheckPK pk;
    
    public DriverCheck() {
    }

    public DriverCheck(Integer driverId, Integer checkId) {
        pk = new DriverCheckPK(driverId, checkId);
    }

	public DriverCheckPK getPk() {
		return pk;
	}

	public void setPk(DriverCheckPK pk) {
		this.pk = pk;
	}

}
