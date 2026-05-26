/**
 * Created on 14-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.log;

import java.io.Serializable;
import java.util.Date;


public class CENAPRAActivityRegistryLogPK implements Serializable {

    private Integer id;
    private String userId;
    private Date date;

    public CENAPRAActivityRegistryLogPK() {
    }

    public CENAPRAActivityRegistryLogPK(Integer id, String userId) {
        this.id = id;
        this.userId = userId;
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
