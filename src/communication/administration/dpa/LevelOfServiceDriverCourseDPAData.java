package communication.administration.dpa;

import java.io.Serializable;
import java.util.Date;

public class LevelOfServiceDriverCourseDPAData implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String course;
	
	private Date date;
	
	private String comment;

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
