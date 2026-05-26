/**
 * Created on 26-abr-2006
 * CEPA
 * DataCenter 5
 */
package communication.event.tables;

import java.io.Serializable;
import java.util.Date;

public class EventListData implements Serializable, Comparable {

    private Integer id;
    private String driverName;
    private String driverLastName;
    private Date date;
    private int type;
    private Integer status;
    private String country;
    private String divOrg;

    public EventListData() {
    }

    public EventListData(Integer id, String driverName, String driverLastName, Date date, int type, Integer status, String country) {
        super();
        this.id = id;
        this.driverName = driverName;
        this.driverLastName = driverLastName;
        this.date = date;
        this.type = type;
        this.status = status;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public Date getDate() {
        return date;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public String getDriverName() {
        return driverName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int compareTo(Object o) {
        return ((EventListData)o).date.compareTo(date);
    }

    public void setDivOrg(String divOrg) {
        this.divOrg = divOrg;
    }

    public String getDivOrg() {
        return divOrg;
    }

}
