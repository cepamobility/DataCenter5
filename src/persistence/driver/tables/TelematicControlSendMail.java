/**
 * @author fcarriquiry
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;

import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;
import communication.driver.tables.TelematicControlData;
import communication.driver.tables.TelematicControlSendMailData;

import persistence.administration.user.tables.User;

public class TelematicControlSendMail implements Serializable{

	private Integer id;

	private static final long serialVersionUID = 8235161610226719412L;
	
	private TelematicControl telematicControl;
	
	private String subject;
	
	private String body;
	
	private String to;
    
    private User user;
    
    private Date date;
    
    
    public TelematicControlSendMail() {
    }

    public TelematicControlSendMail(Integer id, TelematicControlData telematicControlData,String body,String to,String subject, UserSessionData userSessionData,Session session){
    	this.id=id;
    	telematicControl=(TelematicControl)session.get(TelematicControl.class, telematicControlData.getId());
    	user=(User)session.get(User.class, userSessionData.getFilter().getLoginName());
    	date=new Date();
    	this.body=body;
    	this.to=to;
    	this.subject=subject;
    }
    
    public TelematicControlSendMailData getData(UserSessionData userSessionData){
    	TelematicControlSendMailData data=new TelematicControlSendMailData();
    	
    	data.setBody(body);
    	data.setId(id);
    	data.setTelematicControlData(telematicControl.getData(userSessionData));
    	data.setDate(date);
    	data.setTo(to);
    	data.setSubject(subject);
    	UserData userData=new UserData( user.getPassword(), user.getFirstName(),user.getLanguage(), user.getLastName(), user.getLoginName(), user.getEmail(), user.getContractorId(), null, null, null);
    	data.setUserData(userData);
    	
    	return data;
    }
    
    
	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TelematicControl getTelematicControl() {
		return telematicControl;
	}

	public void setTelematicControl(TelematicControl telematicControl) {
		this.telematicControl = telematicControl;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

     
	
	
	

}
