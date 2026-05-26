package presentation.translator.web.driver;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;

public class DriverHistoryQueryForm  extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String lastname;
	
    private int status;
    private String output;
    
    private Date lastUpdate = Calendar.getInstance().getTime();
    
    private String reportType;

    public String getLink() {
        String link = super.getLink();
        link += "&historyQuery=true";
        link += (!Util.empty(this.firstname)) ? "&fn="+this.firstname : "";
        link += (!Util.empty(lastname)) ? "&ln="+lastname : "";
        link += "&status="+status ;
        link += (!Util.empty(output)) ? "&output="+output : "";
        Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");	
        link += "&update="+dateFormat.format(lastUpdate);
        link += "&reportType="+reportType;
        return link;
    }
    
    public DriverHistoryQueryForm() {
        status = DriverData.ACTIVE;
    }
    
    public DriverHistoryQueryForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("firstname")) {
            firstname = (String)parameters.get("firstname"); 
        }
        if(parameters.containsKey("lastname")) {
            lastname = (String)parameters.get("lastname"); 
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
        if(parameters.containsKey("status")) {
            status = Integer.parseInt((String)parameters.get("status")); 
        }
        if(parameters.containsKey("reportType")) {
            reportType = (String)parameters.get("reportType"); 
        }
    }
    
    /**
     * 
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
     * @param firstname
     * @param lastname
     * @param status
     * @param output
     */
    public DriverHistoryQueryForm(String divOrg11, String divOrg12, String divOrg13,
    		String divOrg21, String divOrg22, String divOrg23, 
    		String divOrg31, String divOrg32, String divOrg33, 
    		String country1, String country2, String country3,
    		String firstname, String lastname, 
    		String status, String output,String reportType) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23,
        		divOrg31, divOrg32, divOrg33, country1, country2, country3);
        this.firstname = firstname;
		this.lastname = lastname;
		this.status = Integer.parseInt(status);
		this.output = output;
		this.reportType = reportType;
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
    public DriverHistoryQueryForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }
   
	public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getOutput() {
        return output;
    }

    public int getStatus() {
        return status;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
	 * @return Returns the lastUpdate.
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate The lastUpdate to set.
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
    

	/**
	 * @param lastUpdate The lastUpdate to set.
	 */
	public void setLastUpdate(String lastUpdate) {
        // Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");	        
		Calendar c = Calendar.getInstance();
		int day= (new Integer(lastUpdate.substring(0,2))).intValue();
		int month = (new Integer(lastUpdate.substring(3,5))).intValue();
		int year = (new Integer(lastUpdate.substring(6,10))).intValue();
		c.set(year,month,day);
		this.lastUpdate = c.getTime();
	}

	 /**
	 * @return Returns the reportType.
	 */
	public String getReportType() {
		return reportType;
	}


	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public static String getCountry1(int corporation) {
        switch(corporation) {
            case CorporationData.ASTRAZENECA:
                return "391626";
            case CorporationData.KLABIN:
                return "3000";
            case CorporationData.MONSANTO:
                return "5523";
            case CorporationData.ORGANON:
                return "390596";
        }
	    return "";
    }
    
    public BookmarkData getBookmark() {
        BookmarkData bookmark = super.getBookmark(BookmarkData.HISTORY_REPORT);
        if(!Util.empty(firstname)) {
            bookmark.addParameter("firstname",firstname);
        }
        if(!Util.empty(lastname)) {
            bookmark.addParameter("lastname",lastname);
        }
        if(!Util.empty(output)) {
            bookmark.addParameter("output",output);
        }
        bookmark.addParameter("status",String.valueOf(status));
        if(!Util.empty(reportType)) {
            bookmark.addParameter("reportType",reportType);
        }
        return bookmark;
    }
}
