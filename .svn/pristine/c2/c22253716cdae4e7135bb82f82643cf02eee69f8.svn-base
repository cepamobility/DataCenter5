package presentation.translator.web.queries;

import java.util.Hashtable;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class ReportOdometerLogForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String fleet;
    private String frommonth;
    private String fromyear;
    private String tomonth;
    private String toyear;
    private String output;
    
    private String startDay;
    private String startMonth;
    private String startYear;

    private String endDay;
    private String endMonth;
    private String endYear;

    private String plate;
    private String type;
    private String use;
    private String status;

    public ReportOdometerLogForm() {
        status = String.valueOf(VehicleData.ALL);
    }

    public ReportOdometerLogForm(BookmarkData bookmark) {
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("fleet")) {
            fleet = (String)parameters.get("fleet");
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
        if(parameters.containsKey("startDay")) {
            startDay = (String)parameters.get("startDay"); 
        }
        if(parameters.containsKey("startMonth")) {
            startMonth = (String)parameters.get("startMonth"); 
        }
        if(parameters.containsKey("startYear")) {
            startYear = (String)parameters.get("startYear"); 
        }
        if(parameters.containsKey("endDay")) {
            endDay = (String)parameters.get("endDay"); 
        }
        if(parameters.containsKey("endMonth")) {
            endMonth = (String)parameters.get("endMonth"); 
        }
        if(parameters.containsKey("endYear")) {
            endYear = (String)parameters.get("endYear"); 
        }
        if(parameters.containsKey("plate")) {
            plate = (String)parameters.get("plate"); 
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type"); 
        }
        if(parameters.containsKey("use")) {
            use = (String)parameters.get("use"); 
        }else {
            use = "";
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeriodFrom(Text messages) {
        if(frommonth.equals("0") || fromyear.equals("0")) {
            return "---";
        }
        return messages.getText("generic.month."+frommonth)+"/"+fromyear;
    }

    public String getPeriodTo(Text messages) {
        if(tomonth.equals("0") || toyear.equals("0")) {
            return "---";
        }
        return messages.getText("generic.month."+tomonth)+"/"+toyear;
    }

    public String getDateFrom(Text messages) {
        if(Util.empty(startDay) || Util.empty(startMonth) || Util.empty(startYear)) {
            return "---";
        }
        return startDay + " / " + startMonth + " / " + startYear;
    }

    public String getDateTo(Text messages) {
        if(Util.empty(endDay) || Util.empty(endMonth) || Util.empty(endYear)) {
            return "---";
        }
        return endDay + " / " + endMonth + " / " + endYear;
    }

    public String getFleet() {
        return fleet;
    }

    public void setFleet(String fleet) {
        this.fleet = fleet;
    }

    public String getPlate() {
        return plate;
    }

    public String getStatus() {
        return status;
    }

    public String getUse() {
        return use;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getEndDay() {
        return endDay;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public String getEndYear() {
        return endYear;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public BookmarkData getBookmark() {
        BookmarkData data = new BookmarkData(BookmarkData.ODOMETER_LOG_REPORT);
        if(!Util.empty(fleet)) {
            data.addParameter("fleet",fleet);
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
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        if(!Util.empty(startDay)) {
            data.addParameter("startDay",startDay);
        }
        if(!Util.empty(startMonth)) {
            data.addParameter("startMonth",startMonth);
        }
        if(!Util.empty(startYear)) {
            data.addParameter("startYear",startYear);
        }
        if(!Util.empty(endDay)) {
            data.addParameter("endDay",endDay);
        }
        if(!Util.empty(endMonth)) {
            data.addParameter("endMonth",endMonth);
        }
        if(!Util.empty(endYear)) {
            data.addParameter("endYear",endYear);
        }
        if(!Util.empty(plate)) {
            data.addParameter("plate",plate);
        }
        if(!Util.empty(use)) {
            data.addParameter("use",use);
        }
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }
        return data;
    }

    
}	    
