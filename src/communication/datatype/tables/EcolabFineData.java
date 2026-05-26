package communication.datatype.tables;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import communication.administration.corporation.util.Util;
import communication.language.Text;

public class EcolabFineData {

	private String driver;
	private String type;
	private String qualify;
	private Date date;
	private Time time;
	private String plate;
	
	public EcolabFineData(String driver, String type, String qualify, Date date, Time time, String plate) {
		this.driver = driver;
		this.type = type;
		this.qualify = qualify;
		this.date = date;
		this.time = time;
		this.plate = plate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQualify() {
		return qualify;
	}
	public void setQualify(String qualify) {
		this.qualify = qualify;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	public String getEcolabLine(int index, Text messages) {
		String line = "data.setCell("+index+", 0, '"+driver+"', '"+driver+"', {style: 'color:black;'});"; 
		line += "data.setCell("+index+", 1, '"+plate+"', '"+plate+"', {style: 'color:black;'});";
		if(date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			line += "data.setCell("+index+", 2, new Date("+c.get(Calendar.YEAR)+","+String.valueOf(c.get(Calendar.MONTH)+1)+","+c.get(Calendar.DATE)+"), '"+c.get(Calendar.DATE)+"/"+String.valueOf(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+"', {style: 'color:black;'});";
		}
		if(time != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			line += "data.setCell("+index+", 3, ["+c.get(Calendar.HOUR_OF_DAY)+","+c.get(Calendar.MINUTE)+",0,0], '"+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+"', {style: 'color:black;'});";				
		}
		line += "data.setCell("+index+", 4, '"+messages.getText(type)+"', '"+messages.getText(type)+"', {style: 'color:black;'});";
		line += "data.setCell("+index+", 5, '"+messages.getText(qualify)+"', '"+messages.getText(qualify)+"', {style: 'color:black;'});";
		return line;
	}
	
}
