/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.log.driver;

import java.util.Date;

public class ProvisionalBTWLog {

    private ProvisionalBTWLogPK pk;
    private String result;
    private Integer action;
    private Date btwDate;
    
    public ProvisionalBTWLog() {
    }

    public ProvisionalBTWLog(Integer driver, Date expiry, Date date, String user, String result, Integer action, Date btwDate) {
    	pk = new ProvisionalBTWLogPK(driver,expiry,user,date);
    	this.result = result;
    	this.action = action;
    	this.btwDate = btwDate;
    }

    public ProvisionalBTWLogPK getPk() {
        return pk;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setPk(ProvisionalBTWLogPK pk) {
        this.pk = pk;
    }

    public String getResult() {
        return result;
    }

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Date getBtwDate() {
		return btwDate;
	}

	public void setBtwDate(Date btwDate) {
		this.btwDate = btwDate;
	}
}
