/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import org.hibernate.classic.Session;

import communication.driver.tables.AlcoholControlData;

public class AlcoholControl {

    private AlcoholControlPK pk;
    private Integer result;
    
    public AlcoholControl() {
    }

    public AlcoholControl(Integer driverId, AlcoholControlData controlData, Session session) {
        Driver driver=(Driver)session.load(Driver.class, driverId);
        pk = new AlcoholControlPK(controlData.getDate(),driver);
        result = controlData.getResult();
    }

    public AlcoholControlPK getPk() {
        return pk;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setPk(AlcoholControlPK pk) {
        this.pk = pk;
    }

    public AlcoholControlData getData() {
        return new AlcoholControlData(pk.getDate(),result);
    }

    public Integer getResult() {
        return result;
    }

    
}
