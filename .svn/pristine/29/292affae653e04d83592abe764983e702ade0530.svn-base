/**
 * Created on 18-abr-2007
 * CEPA
 * DataCenter 5
 */
package persistence.log.fleet;

import java.io.Serializable;
import java.util.Date;

public class ConsumedFuelLogPK implements Serializable {

    private Integer consumedId;
    private String userId;
    private Date date;

    public ConsumedFuelLogPK() {
    }

    /**
     * @param fleetAccident
     * @param user
     * @param date
     */
    public ConsumedFuelLogPK(Integer consumedId, String user, Date date) {
        super();
        this.consumedId = consumedId;
        this.userId = user;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getUserId() {
        return userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(String user) {
        this.userId = user;
    }

    public Integer getConsumedId() {
        return consumedId;
    }

    public void setConsumedId(Integer consumedId) {
        this.consumedId = consumedId;
    }
    
    
}
