package communication.alert;

import java.io.Serializable;
import java.util.Collection;

public class Message implements Serializable {
	
	private Collection to;
	private Collection cc;
	private Collection bcc;
	private String from;
	private String fromName;
	private String fromPassword;
	private String subject;
	private String body;
	private String attachment;
	private String name;
	private String replyTo;
	
	/**
	 * Coleccion de Personas a las que se le manda el email
	 * para luego actualizar el alertdate de la persona (driver o usuario)
	 */
	private Collection<PersonData> persons;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Collection getTo() {
		return to;
	}
	public void setTo(Collection to) {
		this.to = to;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Message(String from,String fromName,String fromPassword, Collection to, String subject, String body) {
		this.from = from;
		this.fromName = fromName;
		this.fromPassword = fromPassword;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	public Message(String from,String fromName,String fromPassword, Collection to, 
			String subject, String body, String attachment, String name) {
		this.from = from;
		this.fromName = fromName;
		this.fromPassword = fromPassword;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.attachment = attachment;
		this.name = name;
	}
	
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromPassword() {
		return fromPassword;
	}
	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}
	/**
	 * @return the replyTo
	 */
	public String getReplyTo() {
		return replyTo;
	}
	/**
	 * @param replyTo the replyTo to set
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	/**
	 * @return the persons
	 */
	public Collection<PersonData> getPersons() {
		return persons;
	}
	/**
	 * @param persons the persons to set
	 */
	public void setPersons(Collection<PersonData> persons) {
		this.persons = persons;
	}
	/**
	 * @return the bcc
	 */
	public Collection getBcc() {
		return bcc;
	}
	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(Collection bcc) {
		this.bcc = bcc;
	}
	/**
	 * @return the cc
	 */
	public Collection getCc() {
		return cc;
	}
	/**
	 * @param cc the cc to set
	 */
	public void setCc(Collection cc) {
		this.cc = cc;
	}
	
	
	

}
