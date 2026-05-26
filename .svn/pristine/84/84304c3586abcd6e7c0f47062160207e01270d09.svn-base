package communication.administration.corporation.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DPAUnconformityItemFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int EXPIRY = 1;
	public static final int UNCONFORMITY = 2;
	
	private Date start;
	private Date end;
	private String type;
	private String text;
	private Integer id;
	
	private Integer dateType;
	
	public DPAUnconformityItemFilter(){
		
	}
	public DPAUnconformityItemFilter(Date start,Date end, String type,String text){
		this.start=start;
		this.end=end;
		this.type=type;
		this.text=text;
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getStart() {
		return floorDate(start);
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return topDate(end);
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
	
	

	private static Date floorDate(Date d){
		if(d == null) {
			return null;
		}
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(d);
    	cal.add(Calendar.HOUR, 0);
    	cal.add(Calendar.MINUTE, 0);
    	cal.add(Calendar.SECOND, 0);
    	
    	return cal.getTime();
    }
    
    private static Date topDate(Date d){
		if(d == null) {
			return null;
		}
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(d);
    	cal.add(Calendar.HOUR, 23);
    	cal.add(Calendar.MINUTE, 59);
    	cal.add(Calendar.SECOND, 59);
    	
    	return cal.getTime();
    }
    
	public Integer getDateType() {
		return dateType;
	}
	
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
    
}
