/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.activity.report;

import java.io.Serializable;
import java.util.Date;

public class CommentaryDriveLogData implements Comparable, Serializable {

    protected String user;
    protected Date creationDate;
    protected Date activityDate;
    protected String city;
    
    private Integer activity;
    
    private String driverFirstName;
    private String driverLastName;
    private Date driverBirth;

    protected Integer divOrg1Id;
    protected String divOrg1Name;

    protected Integer divOrg2Id;
    protected String divOrg2Name;
    
    protected Integer divOrg3Id;
    protected String divOrg3Name;
    
    protected Integer divOrg4Id;
    protected String divOrg4Name;
    private String driverUser;
    private Date driverDate;

    public CommentaryDriveLogData() {
    }


    /**
     * @param user
     * @param creationDate
     * @param activityDate
     * @param city
     */
    public CommentaryDriveLogData(Object[] data) {
        super();
        this.driverFirstName = (String) data[0];
        this.driverLastName = (String) data[1];
        this.driverBirth = (Date) data[2];
        this.activity = (Integer) data[3];
        this.creationDate = (Date) data[4];
        this.city = (String) data[5];
        this.user = (String) data[6];
        this.activityDate = (Date) data[7];
        this.driverUser = (String) data[8];
        this.driverDate = (Date) data[9];
        if(data.length > 10) {
            this.divOrg1Id = (Integer) data[10];
            this.divOrg1Name = (String) data[11];
        }
        if(data.length > 12) {
            this.divOrg2Id = (Integer) data[12];
            this.divOrg2Name = (String) data[13];
        }
        if(data.length > 14) {
            this.divOrg3Id = (Integer) data[14];
            this.divOrg3Name = (String) data[15];
        }
        if(data.length > 16) {
            this.divOrg4Id = (Integer) data[16];
            this.divOrg4Name = (String) data[17];
        }
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public String getCity() {
        return city;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getUser() {
        return user;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getDivOrg1Id() {
        return divOrg1Id;
    }

    public String getDivOrg1Name() {
        return divOrg1Name;
    }

    public Integer getDivOrg2Id() {
        return divOrg2Id;
    }

    public String getDivOrg2Name() {
        return divOrg2Name;
    }

    public Integer getDivOrg3Id() {
        return divOrg3Id;
    }

    public String getDivOrg3Name() {
        return divOrg3Name;
    }

    public Integer getDivOrg4Id() {
        return divOrg4Id;
    }

    public String getDivOrg4Name() {
        return divOrg4Name;
    }

    public void setDivOrg1Id(Integer divOrg1Id) {
        this.divOrg1Id = divOrg1Id;
    }

    public void setDivOrg1Name(String divOrg1Name) {
        this.divOrg1Name = divOrg1Name;
    }

    public void setDivOrg2Id(Integer divOrg2Id) {
        this.divOrg2Id = divOrg2Id;
    }

    public void setDivOrg2Name(String divOrg2Name) {
        this.divOrg2Name = divOrg2Name;
    }

    public void setDivOrg3Id(Integer divOrg3Id) {
        this.divOrg3Id = divOrg3Id;
    }

    public void setDivOrg3Name(String divOrg3Name) {
        this.divOrg3Name = divOrg3Name;
    }


    public void setDivOrg4Id(Integer divOrg4Id) {
        this.divOrg4Id = divOrg4Id;
    }


    public void setDivOrg4Name(String divOrg4Name) {
        this.divOrg4Name = divOrg4Name;
    }

    public int compareTo(Object arg0) {
        CommentaryDriveLogData data = (CommentaryDriveLogData)arg0;
        if(divOrg1Id != null && !divOrg1Id.equals(data.divOrg1Id)) {
            return divOrg1Id.compareTo(data.divOrg1Id);
        }
        if(divOrg2Id != null && !divOrg2Id.equals(data.divOrg2Id)) {
            return divOrg2Id.compareTo(data.divOrg2Id);
        }
        if(divOrg3Id != null && !divOrg3Id.equals(data.divOrg3Id)) {
            return divOrg3Id.compareTo(data.divOrg3Id);
        }
        if(divOrg4Id != null && !divOrg4Id.equals(data.divOrg4Id)) {
            return divOrg4Id.compareTo(data.divOrg4Id);
        }
        return 0;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public Date getDriverDate() {
        return driverDate;
    }

    public String getDriverUser() {
        return driverUser;
    }

    public void setDriverDate(Date driverDate) {
        this.driverDate = driverDate;
    }

    public void setDriverUser(String driverUser) {
        this.driverUser = driverUser;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public Date getDriverBirth() {
        return driverBirth;
    }
}
