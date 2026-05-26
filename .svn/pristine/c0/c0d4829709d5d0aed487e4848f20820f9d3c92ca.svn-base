/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import org.hibernate.classic.Session;

import communication.driver.tables.DriverPrizeData;

public class DriverPrize {

    private DriverPrizePK pk;
    private Integer type;
    private Integer mount;
    
    public DriverPrize() {
    }

    public DriverPrize(Integer driverId, DriverPrizeData prizeData, Session session) {
        Driver driver=(Driver)session.load(Driver.class, driverId);
        pk = new DriverPrizePK(prizeData.getDate(),driver);
        type = prizeData.getType();
        mount = prizeData.getMount();
    }

    public Integer getMount() {
        return mount;
    }

    public DriverPrizePK getPk() {
        return pk;
    }

    public Integer getType() {
        return type;
    }

    public void setMount(Integer mount) {
        this.mount = mount;
    }

    public void setPk(DriverPrizePK pk) {
        this.pk = pk;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public DriverPrizeData getData() {
        return new DriverPrizeData(pk.getDate(),type,mount);
    }

}
