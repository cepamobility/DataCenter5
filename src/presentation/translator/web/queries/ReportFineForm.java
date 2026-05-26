package presentation.translator.web.queries;

import java.util.Calendar;

import presentation.translator.web.driver.DriverDivOrgForm;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ReportFineForm extends DriverDivOrgForm {

    private String state;
    private String type;
    private String frommonth;
    private String fromyear;
    private String tomonth;
    private String toyear;
    private String output;

    public ReportFineForm() {
        Calendar calendar = Calendar.getInstance();
        frommonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        tomonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        fromyear = String.valueOf(calendar.get(Calendar.YEAR) - 1);
        toyear = String.valueOf(calendar.get(Calendar.YEAR));
        type = "1";
    }

    public String getFrommonth() {
        return frommonth;
    }

    public String getFromyear() {
        return fromyear;
    }

    public String getTomonth() {
        return tomonth;
    }

    public String getToyear() {
        return toyear;
    }

    public void setFrommonth(String frommonth) {
        this.frommonth = frommonth;
    }

    public void setFromyear(String fromyear) {
        this.fromyear = fromyear;
    }

    public void setTomonth(String tomonth) {
        this.tomonth = tomonth;
    }

    public void setToyear(String toyear) {
        this.toyear = toyear;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom(Text messages) {
        return messages.getText("generic.month."+frommonth)+"/"+fromyear;
    }

    public String getTo(Text messages) {
        return messages.getText("generic.month."+tomonth)+"/"+toyear;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
}	    
