package communication.fleet.tables;

import java.io.Serializable;
import java.util.Date;

public class FleetObservationData implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer fleetId;
    private Date date;
    private String user;
    private Integer action;
    private String comment;

    public FleetObservationData(Integer fleetId, Date date, String user, Integer action, String comment) {
        this.fleetId = fleetId;
        this.date = date;
        this.user = user;
        this.action = action;
        this.comment = comment;
    }

    /**
     * @return Returns the action.
     */
    public Integer getAction() {
        return action;
    }

    /**
     * @return Returns the comment.
     */
    public String getComment() {
        return comment;
    }


    public Date getDate() {
        return date;
    }

    public Integer getFleetId() {
        return fleetId;
    }


    public String getUser() {
        return user;
    }


    public void setAction(Integer action) {
        this.action = action;
    }

    /**
     * @param comment The comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param driverId The driverId to set.
     */
    public void setFleetId(Integer fleetId) {
        this.fleetId = fleetId;
    }

    /**
     * @param user The user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

}
