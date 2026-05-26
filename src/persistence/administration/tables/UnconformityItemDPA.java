package persistence.administration.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;

import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityItemTypeDPADataType;
import communication.administration.user.UserSessionData;

import persistence.datatype.tables.UnconformityItemTypeDPA;
import persistence.hibernate.HibernateUtil;

/**
 * 
 * @author fcarriquiry
 *
 */
public class UnconformityItemDPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String type;
	
	private String comment;
	
	private Boolean notify; 
	
	private Date expirity;
	
	private Integer correctiveActionId;
	
	public UnconformityItemDPA(){
		
	}
	
	public UnconformityItemDPA(UnconformityItemDPAData data){
		Session session = HibernateUtil.currentSession();
		type= data.getType();
		notify=data.getNotify();
		id=data.getId();
		comment=data.getComment();
		correctiveActionId=data.getCorrectiveActionId();
		expirity=data.getExpirity();
	}
	
	public UnconformityItemDPAData getUnconformityItemDPAData(UserSessionData userSessionData){
		UnconformityItemDPAData data=new UnconformityItemDPAData();
		data.setId(id);
		data.setNotify(notify);
		data.setExpirity(expirity); 
		data.setComment(comment);
		data.setType(type);
		data.setCorrectiveActionId(correctiveActionId);
		
		return data;
	}
	

	public Boolean getNotify() {
		return notify;
	}

	public void setNotify(Boolean notify) {
		this.notify = notify;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCorrectiveActionId() {
		return correctiveActionId;
	}

	public void setCorrectiveActionId(Integer correctiveActionId) {
		this.correctiveActionId = correctiveActionId;
	}

	public Date getExpirity() {
		return expirity;
	}

	public void setExpirity(Date expirity) {
		this.expirity = expirity;
	}
	
	
	
}
