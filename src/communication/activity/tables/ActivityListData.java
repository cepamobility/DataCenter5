/**
 * Created on 25-abr-2008
 * CEPA
 * DataCenter 5
 */
package communication.activity.tables;

import java.io.Serializable;
import java.util.Date;

public class ActivityListData implements Serializable {

    private Integer activityId;
    private Date date;
    private String type;
    private String country;
    private String city;
    private String status;
    private String divorg1;
    private String divorg2;
    private String divorg3;
    private String divorg4;

    public ActivityListData() {
    }

    public ActivityListData(Object[] data) {
        activityId = (Integer) data[0];
        type = (String) data[1];
        country = (String) data[2];
        date = (Date) data[3];
        city = (String) data[4];
        status = (String) data[5];
        if(data.length > 6) {
            divorg1 = (String) data[6];
        }
        if(data.length > 7) {
            divorg2 = (String) data[7];
        }
        if(data.length > 8) {
            divorg3 = (String) data[8];
        }
        if(data.length > 9) {
            divorg4 = (String) data[9];
        }
    }
    
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Date getDate() {
        return date;
    }

    public String getDivorg1() {
        return divorg1;
    }

    public String getDivorg2() {
        return divorg2;
    }

    public String getDivorg3() {
        return divorg3;
    }

    public String getDivorg(int i){
    	switch (i){
    		case 1: return divorg1; 
    		case 2: return divorg2; 
    		case 3: return divorg3; 
    		case 4: return divorg4; 
    	}
    	return null;
    }
    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDivorg1(String divorg1) {
        this.divorg1 = divorg1;
    }

    public void setDivorg2(String divorg2) {
        this.divorg2 = divorg2;
    }

    public void setDivorg3(String divorg3) {
        this.divorg3 = divorg3;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

	public String getDivorg4() {
		return divorg4;
	}

	public void setDivorg4(String divorg4) {
		this.divorg4 = divorg4;
	}
    
    
}
