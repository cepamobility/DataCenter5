/**
 * Created on 19-may-2008
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;


public class CommentaryDriveReportForm extends DriverDivOrgForm{

    private String firstName;
    private String lastName;

    private String startDay;
    private String startMonth;
    private String startYear;
    
    private String endDay;
    private String endMonth;
    private String endYear;        

    private String status;
    private String vehicleAllowed;
    
    private String conductionType;
    
    private String type;
    private String output;
    private String commentaryDrive;
    
    public CommentaryDriveReportForm() {
    }

    public CommentaryDriveReportForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status");
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
        if(parameters.containsKey("firstName")) {
            firstName = (String)parameters.get("firstName");
        }
        if(parameters.containsKey("lastName")) {
            lastName = (String)parameters.get("lastName");
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type");
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output");
        }
        if(parameters.containsKey("vehicleAllowed")) {
            vehicleAllowed = (String)parameters.get("vehicleAllowed");
        }
        if(parameters.containsKey("conductionType")) {
            conductionType = (String)parameters.get("conductionType");
        }
        if(parameters.containsKey("commentaryDrive")) {
            commentaryDrive = (String)parameters.get("commentaryDrive");
        }
    }    

    public String getConductionType() {
        return conductionType;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getType() {
        return type;
    }

    public void setConductionType(String conductionType) {
        this.conductionType = conductionType;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom(Text messages) {
        return startDay+" / "+messages.getText("generic.month."+startMonth)+" / "+startYear;
    }

    public String getTo(Text messages) {
        return endDay+" / "+messages.getText("generic.month."+endMonth)+" / "+endYear;
    }

    public Date getInitialDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,Integer.parseInt(startYear));
        c.set(Calendar.MONTH,Integer.parseInt(startMonth)-1);
        c.set(Calendar.DATE,Integer.parseInt(startDay));
        return c.getTime();
    }
    
    public Date getLastDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,Integer.parseInt(endYear));
        c.set(Calendar.MONTH,Integer.parseInt(endMonth)-1);
        c.set(Calendar.DATE,Integer.parseInt(endDay));
        return c.getTime();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getVehicleAllowed() {
        return vehicleAllowed;
    }

    public void setVehicleAllowed(String vehicleAllowed) {
        this.vehicleAllowed = vehicleAllowed;
    }

    public String getCommentaryDrive() {
        return commentaryDrive;
    }

    public void setCommentaryDrive(String commentaryDrive) {
        this.commentaryDrive = commentaryDrive;
    }

    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.COMMENTARY_DRIVE_REPORT_2);
        if(!Util.empty(status)) {
            data.addParameter("status",status);
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
        if(!Util.empty(firstName)) {
            data.addParameter("firstName",firstName);
        }
        if(!Util.empty(lastName)) {
            data.addParameter("lastName",lastName);
        }
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(vehicleAllowed)) {
            data.addParameter("vehicleAllowed",vehicleAllowed);
        }
        if(!Util.empty(conductionType)) {
            data.addParameter("conductionType",conductionType);
        }
        if(!Util.empty(commentaryDrive)) {
            data.addParameter("commentaryDrive",commentaryDrive);
        }
        return data;
    }    
    
}
