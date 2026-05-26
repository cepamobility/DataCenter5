package persistence.administration.tables;

import java.io.Serializable;
import java.util.Date;

import communication.administration.dpa.LevelOfServiceDriverCourseDPAData;
import communication.administration.user.UserSessionData;


/**
 * 
 * @author fcarriquiry
 *
 */
public class LevelOfServiceDriverCourseDPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String course;
	
	private Date date;
	
	private String comment;
	 

	public LevelOfServiceDriverCourseDPA(){
		
	}	
	
	
	public LevelOfServiceDriverCourseDPA(LevelOfServiceDriverCourseDPAData data){
		
		this.id=data.getId();
		
		this.comment=data.getComment();
		
		this.date=data.getDate();
		
		this.course=data.getCourse();
		
		
	}
	
	public void updateData(LevelOfServiceDriverCourseDPAData data){
		

		this.date=data.getDate();
		
		this.comment=data.getComment();
		
		this.course=data.getCourse();
		
	}
	
	public LevelOfServiceDriverCourseDPAData getLevelOfServiceDriverCourseDPAData(UserSessionData userSessionData){
		LevelOfServiceDriverCourseDPAData data=new LevelOfServiceDriverCourseDPAData();
		
		data.setId(this.id);
		
		data.setComment(this.comment);
		
		data.setDate(this.date);
		
		data.setCourse(this.course);
			
		return data;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
}
