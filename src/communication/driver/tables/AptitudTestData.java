package communication.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class AptitudTestData implements Serializable,Comparable<AptitudTestData> {

    private Date date;
    private boolean apt;
    private boolean pending; // Si el Gabinete esta o no Pendiente de Resultado
    private Date expiry;
    private boolean external;
    
    public AptitudTestData() {
    }

    public AptitudTestData(Date date,boolean apt,Date expiry, boolean pending, boolean external) {
        this.date = date;
        this.apt = apt;
        this.expiry = expiry;
        this.pending = pending;
        this.external = external;
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

    public Date getDate() {
        return date;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setApt(boolean apt) {
        this.apt = apt;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

	public int compareTo(AptitudTestData arg0) {
		return arg0.expiry.compareTo(expiry);
	}

	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

    
}
