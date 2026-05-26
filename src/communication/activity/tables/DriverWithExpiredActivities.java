package communication.activity.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.StringTokenizer;

import communication.driver.report.DriverReportDataType;

public class DriverWithExpiredActivities implements Serializable {
    
	protected DriverReportDataType driver;
	protected Date date;
	protected String type;
	protected String note;
	protected Date dateExpiry;
    private String divOrg1;
    private String divOrg2;
    private String divOrg3;
    private String divOrg4;
    private String divOrg5;
    private String divOrg6;
    private String divOrg7;
    private String divOrg8;
    private String divOrg9;	
	
	private boolean hasBTW2 = false;
	private boolean hasBTW3 = false;
	
	private String lastThird;
	private Date thirdExpiry;
	private boolean eBTW;
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DriverWithExpiredActivities(DriverReportDataType driver,String type,String note,Date date,Date dateExpiry,String[] divOrgs) {
		this.driver = driver;
		this.date = date;
		this.type = type;
		this.note = note;
		this.dateExpiry = dateExpiry;
		
		switch(divOrgs.length) {
			case 9:
				divOrg9 = divOrgs[8];
			case 8:
				divOrg8 = divOrgs[7];
			case 7:
				divOrg7 = divOrgs[6];
			case 6:
				divOrg6 = divOrgs[5];
			case 5:
				divOrg5 = divOrgs[4];
			case 4:
				divOrg4 = divOrgs[3];
			case 3:
				divOrg3 = divOrgs[2];
			case 2:
				divOrg2 = divOrgs[1];
			case 1:
				divOrg1 = divOrgs[0];
		}
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	//public DriverData getDriver() {
	public DriverReportDataType getDriver() {
		return driver;
	}

	//public void setDriver(DriverData driver) {
	public void setDriver(DriverReportDataType driver) {
		this.driver = driver;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public String getDivOrg1() {
		return divOrg1;
	}

	public void setDivOrg1(String divOrg1) {
		this.divOrg1 = divOrg1;
	}

	public String getDivOrg2() {
		return divOrg2;
	}

	public void setDivOrg2(String divOrg2) {
		this.divOrg2 = divOrg2;
	}

	public String getDivOrg3() {
		return divOrg3;
	}

	public void setDivOrg3(String divOrg3) {
		this.divOrg3 = divOrg3;
	}

	public String getDivOrg4() {
		return divOrg4;
	}

	public void setDivOrg4(String divOrg4) {
		this.divOrg4 = divOrg4;
	}

	public String getDivOrg5() {
		return divOrg5;
	}

	public void setDivOrg5(String divOrg5) {
		this.divOrg5 = divOrg5;
	}

	public String getDivOrg6() {
		return divOrg6;
	}

	public void setDivOrg6(String divOrg6) {
		this.divOrg6 = divOrg6;
	}

	public String getDivOrg7() {
		return divOrg7;
	}

	public void setDivOrg7(String divOrg7) {
		this.divOrg7 = divOrg7;
	}

	public String getDivOrg8() {
		return divOrg8;
	}

	public void setDivOrg8(String divOrg8) {
		this.divOrg8 = divOrg8;
	}

	public String getDivOrg9() {
		return divOrg9;
	}

	public void setDivOrg9(String divOrg9) {
		this.divOrg9 = divOrg9;
	}
    
	
	public String getDivOrg(int index, int j) {
        if (index == 1){
        	switch (j) {
        		case 3:return divOrg3;
        		case 2:return divOrg2;
        		case 1:return divOrg1;
        	}
        }else if (index == 2){
        	switch (j) {
        		case 3:return divOrg6;
    			case 2:return divOrg5;
    			case 1:return divOrg4;
        	}
    	}else if (index == 3){
    		switch (j) {
    			case 3:return divOrg9;
    			case 2:return divOrg8;
    			case 1:return divOrg7;
    		}
    	}
        return null;
	}

	public boolean isHasBTW2() {
		return hasBTW2;
	}

	public boolean isHasBTW3() {
		return hasBTW3;
	}

	public void setBTW2() {
		hasBTW2 = true;
	}

	public void setBTW3() {
		hasBTW3 = true;
	}

	public String getLastThird() {
		return lastThird;
	}

	public void setLastThird(String lastThird) {
		this.lastThird = lastThird;
	}

	public Date getThirdExpiry() {
		return thirdExpiry;
	}

	public void setThirdExpiry(Date thirdExpiry) {
		this.thirdExpiry = thirdExpiry;
	}

	public void setEBTW() {
		eBTW = true;
	}

	public boolean isEBTW() {
		return eBTW;
	}

	public void setEBTW(boolean ebtw) {
		eBTW = ebtw;
	}

}
