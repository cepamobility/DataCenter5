package presentation.translator.web.activity.schedule;

import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ReportCommentaryDriveLogForm extends DriverDivOrgForm {

    private String frommonth;
    private String fromyear;
    private String tomonth;
    private String toyear;
    private String output;
    private String order1;
    private String order2;
    private String order3;
    private String order4;

    public ReportCommentaryDriveLogForm() {
    }

    public ReportCommentaryDriveLogForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("order1")) {
            order1 = (String)parameters.get("order1"); 
        }
        if(parameters.containsKey("order2")) {
            order2 = (String)parameters.get("order2"); 
        }
        if(parameters.containsKey("order3")) {
            order3 = (String)parameters.get("order3"); 
        }
        if(parameters.containsKey("order4")) {
            order4 = (String)parameters.get("order4"); 
        }
        if(parameters.containsKey("frommonth")) {
            frommonth = (String)parameters.get("frommonth"); 
        }
        if(parameters.containsKey("fromyear")) {
            fromyear = (String)parameters.get("fromyear"); 
        }
        if(parameters.containsKey("tomonth")) {
            tomonth = (String)parameters.get("tomonth"); 
        }
        if(parameters.containsKey("toyear")) {
            toyear = (String)parameters.get("toyear"); 
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
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

    public String getPeriod(Text messages) {
        return messages.getText("generic.month."+frommonth)+"/"+fromyear+" a "+messages.getText("generic.month."+tomonth)+"/"+toyear;
    }

    public String getFrom(Text messages) {
        if(Util.empty(frommonth) || Util.empty(fromyear)) {
            return "---";
        }else {
            if(frommonth.startsWith("Q")) {
                return frommonth+" / "+fromyear;
            }
            return messages.getText("generic.month."+frommonth)+" / "+fromyear;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(tomonth) || Util.empty(toyear)) {
            return "---";
        }else {
            if(frommonth.startsWith("Q")) {
                return tomonth+" / "+toyear;
            }
            return messages.getText("generic.month."+tomonth)+" / "+toyear;
        }
    }
    
    public String getOrder1() {
        return order1;
    }

    public String getOrder2() {
        if(order1 != null && order1.equals("4")) {
            return "4"; 
        }
        return order2;
    }

    public String getOrder3() {
        if(getOrder2() != null && getOrder2().equals("4")) {
            return "4"; 
        }
        return order3;
    }

    public void setOrder1(String order1) {
        this.order1 = order1;
    }

    public void setOrder2(String order2) {
        this.order2 = order2;
    }

    public void setOrder3(String order3) {
        this.order3 = order3;
    }

    public String getOrder4() {
        if(getOrder3() != null && getOrder3().equals("4")) {
            return "4"; 
        }
        return order4;
    }

    public void setOrder4(String order4) {
        this.order4 = order4;
    }

    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.COMMENTARY_DRIVE_LOG_REPORT);
        if(!Util.empty(order1)) {
            data.addParameter("order1",order1);
        }
        if(!Util.empty(order2)) {
            data.addParameter("order2",order2);
        }
        if(!Util.empty(order3)) {
            data.addParameter("order3",order3);
        }
        if(!Util.empty(order4)) {
            data.addParameter("order4",order4);
        }
        if(!Util.empty(frommonth)) {
            data.addParameter("frommonth",frommonth);
        }
        if(!Util.empty(fromyear)) {
            data.addParameter("fromyear",fromyear);
        }
        if(!Util.empty(tomonth)) {
            data.addParameter("tomonth",tomonth);
        }
        if(!Util.empty(toyear)) {
            data.addParameter("toyear",toyear);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        return data;
    }
}	    
