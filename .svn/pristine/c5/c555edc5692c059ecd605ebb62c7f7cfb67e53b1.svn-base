/**
 * Created on 21-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.log;

import java.io.Serializable;
import java.util.Date;

public class CENAPRAActivityLogPK implements Serializable {

     private Integer activity;
     private String userId;
     private Date date;    

     public CENAPRAActivityLogPK() {
    }

    public CENAPRAActivityLogPK(Integer activity,String userId) {
        this.activity = activity;
        this.userId = userId;
        this.date = new Date();
    }

    public Integer getActivity() {
        return activity;
    }

    public Date getDate() {
        return date;
    }

    public String getUserId() {
        return userId;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
