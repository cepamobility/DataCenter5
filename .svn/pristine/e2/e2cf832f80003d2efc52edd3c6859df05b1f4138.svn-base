/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.util.Date;

public class ProvisionalBTW {

    private ProvisionalBTWPK pk;
    private String result;
    private Date date;
    
    public ProvisionalBTW() {
    }

    public ProvisionalBTW(Integer driver, Date expiry, String result, Date date) {
    	pk = new ProvisionalBTWPK(driver,expiry);
    	this.result = result;
    	this.date = date;
    }

    public ProvisionalBTWPK getPk() {
        return pk;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setPk(ProvisionalBTWPK pk) {
        this.pk = pk;
    }

    public String getResult() {
        return result;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    
}
