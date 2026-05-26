/**
 * Created on 20-ago-2008
 * CEPA
 * DataCenter 5
 */
package communication.cepasystem.tables;

import java.io.Serializable;

import communication.driver.tables.DriverMainAttributes;


public class CepaSystemRegistryData implements Serializable {

    private Integer id;
    private ActivityData activityData;
    private DriverMainAttributes driverData;
    private String observations;

    public CepaSystemRegistryData() {
        super();
    }

    public CepaSystemRegistryData(Integer id, ActivityData activityData, DriverMainAttributes driverData, String observations) {
        this.id = id;
        this.activityData = activityData;
        this.driverData = driverData;
        this.observations = observations;
    }

    public ActivityData getActivityData() {
        return activityData;
    }

    public Integer getId() {
        return id;
    }

    public String getObservations() {
        return observations;
    }

    public DriverMainAttributes getDriverData() {
        return driverData;
    }

    public void setActivityData(ActivityData activityData) {
        this.activityData = activityData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setDriverData(DriverMainAttributes driverData) {
        this.driverData = driverData;
    }
}
