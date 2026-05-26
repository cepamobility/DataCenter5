/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class DriverCheckPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer driver;
    private Integer vehicleCheck;

    public DriverCheckPK() {
    }

	public DriverCheckPK(Integer driver, Integer vehicleCheck) {
		this.driver = driver;
		this.vehicleCheck = vehicleCheck;
	}

	public Integer getDriver() {
		return driver;
	}

	public void setDriver(Integer driver) {
		this.driver = driver;
	}

	public Integer getVehicleCheck() {
		return vehicleCheck;
	}

	public void setVehicleCheck(Integer vehicleCheck) {
		this.vehicleCheck = vehicleCheck;
	}

}
