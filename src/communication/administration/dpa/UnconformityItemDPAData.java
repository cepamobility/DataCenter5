package communication.administration.dpa;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author fcarriquiry
 *
 */
public class UnconformityItemDPAData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private String type;
	private String comment;
	private Boolean notify;
	private Date expirity;
	
	public Boolean getNotify() {
		return notify;
	}
	public void setNotify(Boolean notify) {
		this.notify = notify;
	}
	private Integer correctiveActionId;
	
	public Integer getCorrectiveActionId() {
		return correctiveActionId;
	}
	public void setCorrectiveActionId(Integer correctiveActionId) {
		this.correctiveActionId = correctiveActionId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getExpirity() {
		return expirity;
	}
	public void setExpirity(Date expirity) {
		this.expirity = expirity;
	}
	
	
	 
	
	
	
	
}
