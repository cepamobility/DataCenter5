/**
 * Created on 06-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.vehicle.tables.VehicleData;

public class ComplaintReportForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String plate;
    private String fromDate;
    private String fromMonth;
    private String fromYear;
    private String toDate;
    private String toMonth;
    private String toYear;
    private String status;
    private String output;    

    
	public ComplaintReportForm() {
		super();
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
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

	/*
	 *  (non-Javadoc)
	 * @see presentation.translator.web.driver.DriverDivOrgForm#getLink()
	 * Genera un link con los parametros del form
	 */
	public String getLink() {
	        StringBuffer link = new StringBuffer("");
	        link.append((!Util.empty(plate)) ? "&pl="+plate : "");
	        link.append((!Util.empty(output)) ? "&o="+output : "");
            link.append((!Util.empty(status)) ? "&status="+status : "");
	        link.append(super.getLink());
	        return link.toString();
	    }
	
    /**
     * @param divOrg11
     * @param divOrg12
     * @param divOrg13
     * @param divOrg21
     * @param divOrg22
     * @param divOrg23
     * @param divOrg31
     * @param divOrg32
     * @param divOrg33
     * @param country1
     * @param country2
     * @param country3
     */
    public ComplaintReportForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }

    public ComplaintReportForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("plate")) {
            plate = (String)parameters.get("plate"); 
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
    }
    
    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.VEHICLE_REPORT);
        if(!Util.empty(plate)) {
            data.addParameter("plate",plate);
        }
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        return data;
    }

	public String getFrom() {
		return Util.formatDate(Util.getDate(fromDate, fromMonth, fromYear));
	}
      
	public String getTo() {
		return Util.formatDate(Util.getDate(toDate, toMonth, toYear));
	}

	public String getVehicleStatus() {
		switch(Integer.parseInt(status)) {
			case VehicleData.ACTIVE:
				return "generic.messages.active";
			case VehicleData.EXCLUDED:
				return "generic.messages.excluded";
			default:
				return "generic.messages.all.pl";
		}
	}
}
