package communication.travel.tables;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class CheckPointData implements Comparable {

	private Integer id;
	private Date date;
	private String local;
	private String way;
	private String communication;
	private boolean incidents;
	private String comment;
	private Integer number;
	private Time hour;
	
	public CheckPointData(String day, String month, String year,
			String hour, String minute, String local, String way,
			String communication, String result, String comment) {
		this.date = getDate(day,month,year);
		this.hour = getTime(hour, minute);
		this.local = local;
		this.way = way;
		this.communication = communication;
		this.comment = comment;
		this.incidents = (result.equals("2"));
	}

	
	public CheckPointData(Integer id, Date date, Time hour, String local, String way, String communication, boolean incidents, String comment, Integer number) {
		this.id = id;
		this.date = date;
		this.hour = hour;
		this.local = local;
		this.way = way;
		this.communication = communication;
		this.incidents = incidents;
		this.comment = comment;
		this.number = number;
	}


	private boolean isNumber(String value) {
		if(value == null || value.trim().length() == 0) {
			return false;
		}
		try {
			Integer.parseInt(value);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public boolean isIncidents() {
		return incidents;
	}

	public void setIncidents(boolean incidents) {
		this.incidents = incidents;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int compareTo(Object o) {
		return number.compareTo(((CheckPointData)o).number);
	}


	public Time getHour() {
		return hour;
	}


	public void setHour(Time hour) {
		this.hour = hour;
	}

    private Date getDate(String date, String month, String year) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date));
            calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
            calendar.set(Calendar.YEAR,Integer.parseInt(year));
            
            calendar.set(Calendar.HOUR,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            
            return calendar.getTime();
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    
    private Time getTime(String hour, String minute) {
        Calendar calendar = Calendar.getInstance();
        
        if(isNumber(hour)) {
            calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour));
        }
        if(isNumber(minute)) {
            calendar.set(Calendar.MINUTE,Integer.parseInt(minute));
        }
        calendar.set(Calendar.SECOND, 0);
        
        return new Time(calendar.getTimeInMillis());
    }
    
}
