/**
 * Created on 21-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.log;

import java.util.Date;

import persistence.cenapra.tables.CENAPRAActivity;
import persistence.datatype.tables.CEPAActivityType;

public class CENAPRAActivityLog {

     private CENAPRAActivityLogPK pk;
     
     private Integer action;
     private String comment;
     
     private Date activityDate;    
     
     private Integer corporation;
     private Integer country;
     private Integer divOrg1;
     private Integer divOrg2;
     private Integer divOrg3;

     private String type;
     private String state;

     private String instructor;
     private String city;
     private String observations;

     
     public CENAPRAActivityLog() {
     }

     public CENAPRAActivityLog(CENAPRAActivity activity,int action,String comment,String user) {
         pk = new CENAPRAActivityLogPK(activity.getId(),user);
         this.action = action;
         this.comment = comment;
         this.activityDate = activity.getDate();
         this.corporation = activity.getCorpCountry().getPk().getCorporation().getId();
         this.country = activity.getCorpCountry().getPk().getCountry().getId();
         this.divOrg1 = (activity.getDivOrg1() == null) ? null : activity.getDivOrg1().getId();
         this.divOrg2 = (activity.getDivOrg2() == null) ? null : activity.getDivOrg2().getId();
         this.divOrg3 = (activity.getDivOrg3() == null) ? null : activity.getDivOrg3().getId();
         this.type = (activity.getType() == null) ? null : activity.getType().getResourceBundleId();
         this.state = (activity.getState() == null) ? null : activity.getState().getResourceBundleId();
         this.instructor = activity.getInstructor();
         this.city = activity.getCity();
         this.observations = activity.getObservations();
     }

     public Integer getAction() {
        return action;
     }

    public Date getActivityDate() {
        return activityDate;
    }

    public String getCity() {
        return city;
    }

    public String getComment() {
        return comment;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public Integer getCountry() {
        return country;
    }

    public Integer getDivOrg1() {
        return divOrg1;
    }

    public Integer getDivOrg2() {
        return divOrg2;
    }

    public Integer getDivOrg3() {
        return divOrg3;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getObservations() {
        return observations;
    }

    public CENAPRAActivityLogPK getPk() {
        return pk;
    }

    public String getState() {
        return state;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public void setDivOrg1(Integer divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(Integer divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(Integer divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setPk(CENAPRAActivityLogPK pk) {
        this.pk = pk;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
