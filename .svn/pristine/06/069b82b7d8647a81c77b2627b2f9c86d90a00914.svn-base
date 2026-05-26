package communication.util.tables;

import communication.administration.corporation.util.Util;
import communication.language.Text;

public class ResumeAPMM {

	private Integer period;
	private Integer year;
	private Integer vehicles;
	private Integer miles;
	private Integer accidents;
	private Integer incidents;

	public ResumeAPMM() {
	}

	public ResumeAPMM(Object[] data) {
		this.period = (Integer) data[1];
		this.year = (Integer) data[0];
		this.vehicles = (Integer) data[2];
		this.miles = (Integer) data[3];
		this.accidents = (Integer) data[4];
		this.incidents = (Integer) data[5];
	}

	public String getCharLine(Text messages) {
		String apmm = (accidents != null && miles != null && miles.intValue() != 0) ? Util.formatDouble(new Double(accidents.doubleValue() * 1000000 / miles.doubleValue()),2) : "0.00";
		return "['"+messages.getText("generic.shortmonth."+period)+"/"+year+"',"+apmm+"]";
	}

	public String getTableLine(Text messages) {
		String value = "<div class='rTableRow'> ";
		value += "<div class='rTableCell'>"+messages.getText("generic.month."+period)+"/"+year+"</div>";
		value += "<div class='rTableCell'>"+vehicles+"</div>";
		value += "<div class='rTableCell'>"+miles+"</div>";
		value += "<div class='rTableCell'>"+accidents+"</div>";
		String apmm = (accidents != null && miles != null && miles.intValue() != 0) ? Util.formatDouble(new Double(accidents.doubleValue() * 1000000 / miles.doubleValue()),2) : "0.00";
		value += "<div class='rTableCell'>"+apmm+"</div>";
		value += "<div class='rTableCell'>"+incidents+"</div>";
		String ipmm = (incidents != null && miles != null && miles.intValue() != 0) ? Util.formatDouble(new Double(incidents.doubleValue() * 1000000 / miles.doubleValue()),2) : "0.00";
		value += "<div class='rTableCell'>"+ipmm+"</div>";
		value += "</div>";
		return value;
	}
}
