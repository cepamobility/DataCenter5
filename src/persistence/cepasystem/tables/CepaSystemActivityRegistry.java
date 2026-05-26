/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cepasystem.tables;

import org.hibernate.classic.Session;

import persistence.driver.tables.Driver;

import communication.administration.user.UserSessionData;
import communication.cepasystem.tables.CepaSystemRegistryData;


public class CepaSystemActivityRegistry {

    private Integer id;

    private CepaSystemActivity activity;
    private Driver driver;

    private String observations;

    public CepaSystemActivityRegistry() {
    }

    public CepaSystemActivityRegistry(Integer activityId, Integer driverId, String observations, Session session) {
        activity = (CepaSystemActivity)session.load(CepaSystemActivity.class,activityId);
        driver = (Driver)session.load(Driver.class,driverId);
        this.observations = observations;
    }

    public Integer getId() {
        return id;
    }

    public String getObservations() {
        return observations;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public CepaSystemActivity getActivity() {
        return activity;
    }

    public void setActivity(CepaSystemActivity activity) {
        this.activity = activity;
    }

    public CepaSystemRegistryData getData(UserSessionData userSessionData) {
        return new CepaSystemRegistryData(id,activity.getData(),driver.getPrincipalAttributes(userSessionData),observations);
    }

}
