package presentation.translator.web.driver;

import communication.driver.tables.DriverData;
import communication.language.Text;

import presentation.translator.web.util.Util;

public class GenericReportForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    
    private String fromDate;
    private String fromMonth;
    private String fromYear;

    private String toDate;
    private String toMonth;
    private String toYear;
    
    private String status;
    private String output;

    
    public String getLink() {
        String link = "";
        link += "&licExpiry=true";
        link += (!Util.empty(this.firstName)) ? "&fn="+this.firstName : "";       
        link += (!Util.empty(this.lastName)) ? "&ln="+this.lastName : "";
        link += (!Util.empty(this.fromDate)) ? "&fd="+fromDate : "";
        link += (!Util.empty(this.fromMonth)) ? "&fm="+fromMonth : "";
        link += (!Util.empty(this.fromYear)) ? "&fy="+fromYear : "";
        link += (!Util.empty(this.toDate)) ? "&td="+toDate : "";
        link += (!Util.empty(this.toMonth)) ? "&tm="+toMonth : "";
        link += (!Util.empty(this.toYear)) ? "&ty="+toYear : "";
        link += "&status="+status ;
        link += (!Util.empty(output)) ? "&output="+output : "";
        link += super.getLink();
        return link;
    }
    
    public GenericReportForm() {
        status = "all";
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getStatus(Text messages) {
		switch(Integer.parseInt(status)) {
			case DriverData.ACTIVE:
				return messages.getText("generic.messages.active");
			case DriverData.EXCLUDED:
				return messages.getText("generic.messages.excluded");
		}
		return messages.getText("generic.messages.all.pl");
	}

    public String getFrom(Text messages) {
        if(Util.empty(fromDate) || Util.empty(fromMonth) || Util.empty(fromYear)) {
            return "---";
        }else {
            return fromDate + " / " + messages.getText("generic.month."+ String.valueOf(Integer.parseInt(fromMonth))) +" / "+fromYear;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(toDate) || Util.empty(toMonth) || Util.empty(toYear)) {
            return "---";
        }else {
            return toDate + " / " + messages.getText("generic.month."+ String.valueOf(Integer.parseInt(toMonth)))+" / "+toYear;
        }
    }

}