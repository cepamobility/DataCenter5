package persistence.log.activity;

import java.io.Serializable;
import java.util.Date;

import persistence.activity.tables.CEPAScheduleActivityRegistry;

public class ActivityRegistryLog implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ActivityRegistryLogPK pk;
    
    private Integer action;  
    private String comment;    
	
	protected Integer activity;
	
	protected Integer driver;	
	
	protected String obs;
	
	protected String note;
	
	protected String instructor;
	
	public ActivityRegistryLog() {

	}
	
	public ActivityRegistryLog(CEPAScheduleActivityRegistry registry,String user,Date date,int action,String comment) {
		pk = new ActivityRegistryLogPK(registry.getActivityRegistryId(),user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;		
        this.activity = registry.getActivity().getActivityId();
        this.driver = registry.getDriver().getId();
        this.obs = registry.getObs();
        this.note = registry.getNote();
        this.instructor = registry.getInstructor();
			
	}
		

	/**
	 * @return Returns the activity.
	 */
	public Integer getActivity() {
		return activity;
	}

	/**
	 * @param activity The activity to set.
	 */
	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	/**
	 * @return Returns the driver.
	 */
	public Integer getDriver() {
		return driver;
	}

	/**
	 * @param driver The driver to set.
	 */
	public void setDriver(Integer driver) {
		this.driver = driver;
	}

	/**
	 * @return Returns the instructor.
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor The instructor to set.
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	/**
	 * @return Returns the note.
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note The note to set.
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return Returns the obs.
	 */
	public String getObs() {
		return obs;
	}

	/**
	 * @param obs The obs to set.
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * @return Returns the pk.
	 */
	public ActivityRegistryLogPK getPk() {
		return pk;
	}

	/**
	 * @param pk The pk to set.
	 */
	public void setPk(ActivityRegistryLogPK pk) {
		this.pk = pk;
	}

	/**
	 * @return Returns the action.
	 */
	public Integer getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(Integer action) {
		this.action = action;
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
	
		
}
