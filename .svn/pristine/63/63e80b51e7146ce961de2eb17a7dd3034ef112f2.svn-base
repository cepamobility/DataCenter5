package presentation.translator.web.queries;

import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class ReportMissingOdometerForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

	private String fleet;
    private String month;
    private String year;
    
    private String plate;
    private String status;

    private String output;

    public ReportMissingOdometerForm() {
        status = String.valueOf(VehicleData.ALL);
    }

    public ReportMissingOdometerForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("fleet")) {
            fleet = (String)parameters.get("fleet");
        }
        if(parameters.containsKey("month")) {
            month = (String)parameters.get("month"); 
        }
        if(parameters.containsKey("year")) {
            year = (String)parameters.get("year"); 
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
        if(parameters.containsKey("plate")) {
            plate = (String)parameters.get("plate"); 
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
        }
    }

    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.MISSING_ODOMETER_REPORT);
        if(!Util.empty(fleet)) {
            data.addParameter("fleet",fleet);
        }
        if(!Util.empty(month)) {
            data.addParameter("month",month);
        }
        if(!Util.empty(year)) {
            data.addParameter("year",year);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(plate)) {
            data.addParameter("plate",plate);
        }
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }
        return data;
    }

    public String getPeriod(Text messages) {
        if(month.equals("0") || year.equals("0")) {
            return "---";
        }
        return messages.getText("generic.month."+month)+"/"+year;
    }

	public String getFleet() {
		return fleet;
	}

	public void setFleet(String fleet) {
		this.fleet = fleet;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
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

    
}	    
