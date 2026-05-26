package communication.driver.report;

import java.io.Serializable;
import java.util.Date;

public class DPATelematicControlFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date start;
	private Date end;
	private String type;
	private String text;
	
	public DPATelematicControlFilter(Date start,Date end, String type,String text){
		this.start=start;
		this.end=end;
		this.type=type;
		this.text=text;
		
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
