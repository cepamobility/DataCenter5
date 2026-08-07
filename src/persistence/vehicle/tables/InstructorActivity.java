package persistence.vehicle.tables;

import java.util.Date;

import org.hibernate.Session;

import communication.vehicle.tables.InstructorActivityData;
import persistence.administration.user.tables.User;
import persistence.hibernate.HibernateUtil;

public class InstructorActivity {
	
	private Integer id;
	private Integer corporation;
	private Integer category;
	private Integer subCategory;
	private String location;
	private Date date;
	private Integer channel;
	private String referent;
	private String observation;
	
	protected User user;
	
	public InstructorActivity() {}
	
	public InstructorActivity(InstructorActivityData data) {
		this.corporation = data.getCorporation();
		this.category = data.getCategory();
		this.subCategory = data.getSubCategory();
		this.location = data.getLocation();
		this.date = data.getDate();
		this.channel = data.getChannel();
		this.referent = data.getReferent();
		this.observation = data.getObservation();
		
		Session session = HibernateUtil.currentSession();
		this.user = (User) session.load(User.class, data.getUser().getFilter().getLoginName());
	}
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCorporation() {
		return corporation;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	public Integer getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getReferent() {
		return referent;
	}

	public void setReferent(String referent) {
		this.referent = referent;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
}
