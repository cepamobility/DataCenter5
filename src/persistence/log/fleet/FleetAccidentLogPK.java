/**
 * Created on 18-abr-2007
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.io.Serializable;
import java.util.Date;

public class FleetAccidentLogPK implements Serializable {

    private Integer fleetAccident;
    private String userId;
    private Date date;

    public FleetAccidentLogPK() {
    }

    /**
     * @param fleetAccident
     * @param user
     * @param date
     */
    public FleetAccidentLogPK(Integer fleetAccident, String user, Date date) {
        super();
        this.fleetAccident = fleetAccident;
        this.userId = user;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Integer getFleetAccident() {
        return fleetAccident;
    }

    public String getUserId() {
        return userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFleetAccident(Integer fleetAccident) {
        this.fleetAccident = fleetAccident;
    }

    public void setUserId(String user) {
        this.userId = user;
    }
    
    
}
