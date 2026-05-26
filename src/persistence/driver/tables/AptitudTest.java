/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.util.Date;

import org.hibernate.classic.Session;

import communication.driver.tables.AptitudTestData;

public class AptitudTest {

    private AptitudTestPK pk;
    private boolean apt;
    private boolean pending; // Pendiente de Resultado
    private Date expiry;
    private boolean external;
    
    public AptitudTest() {
    }

    public AptitudTest(Integer driverId, AptitudTestData testData, Session session) {
        Driver driver=(Driver)session.load(Driver.class, driverId);
        pk = new AptitudTestPK(testData.getDate(),driver);
        apt = testData.isApt();
        expiry = testData.getExpiry();
        pending = testData.isPending();
        external = testData.isExternal();
    }

    
    /**
	 * @return the pending
	 */
	public boolean isPending() {
		return pending;
	}

	/**
	 * @param pending the pending to set
	 */
	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public boolean isApt() {
        return apt;
    }

    public Date getExpiry() {
        return expiry;
    }

    public AptitudTestPK getPk() {
        return pk;
    }

    public void setApt(boolean apt) {
        this.apt = apt;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setPk(AptitudTestPK pk) {
        this.pk = pk;
    }

    public AptitudTestData getData() {
        return new AptitudTestData(pk.getDate(),apt,expiry,pending,external);
    }

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

    
}
