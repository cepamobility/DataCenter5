/*
 * Created on 27-oct-2005
 */
package persistence.log.activity;

import java.util.Date;

import persistence.activity.tables.CEPAScheduleActivity;

import communication.activity.tables.ActivityObservationData;

/**
 * @author adocarmo
 */
public class ActivityLog {

    private ActivityLogPK pk;
    
    private Integer action;   
    private String comment;    
    
    private Integer country;
    private Integer geoLevel1;     
    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;
    
	private String type;
	private String state;
	private Date activityDate; 
    private String city;    
    
    private Integer corporationId;        

	public ActivityLog() {
		
	}
	
	public ActivityLog(CEPAScheduleActivity activity,String user,Date date,int action,String comment) {
		pk = new ActivityLogPK(activity.getActivityId(),user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;		
		this.country = activity.getCorpCountry().getId();
		this.city = activity.getCity();		
		this.divOrg1 = (activity.getDivOrg1() != null) ? activity.getDivOrg1().getId():null;
		this.divOrg2 = (activity.getDivOrg2() != null) ? activity.getDivOrg2().getId():null;
		this.divOrg3 = (activity.getDivOrg3() != null) ? activity.getDivOrg3().getId():null;
		this.geoLevel1 = (activity.getGeoLevel1() !=null) ? activity.getGeoLevel1().getId():null;		
		
		this.activityDate = activity.getDate();	
		this.type = activity.getType().getResourceBundleId();
		this.state = activity.getState().getResourceBundleId();				
        this.corporationId = activity.getCorporation().getId();		
	}
	
    /**
     * @return Returns the pk.
     */
    public ActivityLogPK getPk() {
        return pk;
    }
    /**
     * @return Returns the action.
     */
    public Integer getAction() {
        return action;
    }

	/**
     * @param pk The pk to set.
     */
    public void setPk(ActivityLogPK pk) {
        this.pk = pk;
    }

    /**
     * @param action The action to set.
     */
    public void setAction(Integer action) {
        this.action = action;
    }

	/**
	 * @return Returns the country.
	 */
	public Integer getCountry() {
		return country;
	}

	/**
	 * @param country The country to set.
	 */
	public void setCountry(Integer country) {
		this.country = country;
	}

	/**
	 * @return Returns the divOrg1.
	 */
	public Integer getDivOrg1() {
		return divOrg1;
	}

	/**
	 * @param divOrg1 The divOrg1 to set.
	 */
	public void setDivOrg1(Integer divOrg1) {
		this.divOrg1 = divOrg1;
	}

	/**
	 * @return Returns the divOrg2.
	 */
	public Integer getDivOrg2() {
		return divOrg2;
	}

	/**
	 * @param divOrg2 The divOrg2 to set.
	 */
	public void setDivOrg2(Integer divOrg2) {
		this.divOrg2 = divOrg2;
	}

	/**
	 * @return Returns the divOrg3.
	 */
	public Integer getDivOrg3() {
		return divOrg3;
	}

	/**
	 * @param divOrg3 The divOrg3 to set.
	 */
	public void setDivOrg3(Integer divOrg3) {
		this.divOrg3 = divOrg3;
	}

	/**
	 * @return Returns the geoLevel1.
	 */
	public Integer getGeoLevel1() {
		return geoLevel1;
	}

	/**
	 * @param geoLevel1 The geoLevel1 to set.
	 */
	public void setGeoLevel1(Integer geoLevel1) {
		this.geoLevel1 = geoLevel1;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the corporationId.
	 */
	public Integer getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId The corporationId to set.
	 */
	public void setCorporationId(Integer corporationId) {
		this.corporationId = corporationId;
	}

	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
    public ActivityObservationData getActivityObservations() {
        return new ActivityObservationData(pk.getActivity(),pk.getDate(),pk.getUserId(),action,comment);
    }

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the date.
	 */
	public Date getActivityDate() {
		return activityDate;
	}

	/**
	 * @param date The date to set.
	 */
	public void setActivityDate(Date date) {
		activityDate = date;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}	
    

}
