package presentation.translator.web.driver;

import communication.driver.tables.DriverData;
import communication.language.Text;

import presentation.translator.web.util.Util;

public class DriverPointReportForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    
    private String operator;
    private String value;
    
    private String status;
    private String output;

	private String type;
    
    public String getLink() {
        String link = "";
        link += "&licExpiry=true";
        link += (!Util.empty(this.firstName)) ? "&fn="+this.firstName : "";       
        link += (!Util.empty(this.lastName)) ? "&ln="+this.lastName : "";
        link += (!Util.empty(this.operator)) ? "&op="+operator : "";
        link += (!Util.empty(this.value)) ? "&v="+value : "";
        link += "&status="+status ;
        link += (!Util.empty(output)) ? "&output="+output : "";
        link += super.getLink();
        return link;
    }
    
    public DriverPointReportForm() {
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isResumed() {
		return Integer.parseInt(type) == 0;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}