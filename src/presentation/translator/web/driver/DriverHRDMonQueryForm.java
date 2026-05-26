package presentation.translator.web.driver;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriverHRDMonQueryForm  extends DriverDivOrgForm  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String lastname;

    private String frommonth;
    private String fromyear;

    private String tomonth;
    private String toyear;
    
    private int status;
    private String output;
    
    private String avoidable;
    
    private Date lastUpdate = Calendar.getInstance().getTime();
    
    private String reportType;

    public String getLink() {
        String link = "";
        link += "&hrdMonQuery=true";
        link += (!Util.empty(this.firstname)) ? "&fn="+this.firstname : "";
        link += (!Util.empty(lastname)) ? "&ln="+lastname : "";
        link += (!Util.empty(frommonth)) ? "&fm="+frommonth : "";
        link += (!Util.empty(fromyear)) ? "&fy="+fromyear : "";
        link += (!Util.empty(tomonth)) ? "&tm="+tomonth : "";
        link += (!Util.empty(toyear)) ? "&ty="+toyear : "";
        link += "&status="+status ;
        link += (!Util.empty(output)) ? "&output="+output : "";
        Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");	
        link += "&update="+dateFormat.format(lastUpdate);
        link += "&rt="+reportType;
        link += "&avoidable="+avoidable;
        link += super.getLink();
        return link;
    }
    
    public DriverHRDMonQueryForm() {
        status = DriverData.ALL;
        // Seteo Brasil:
        // this.country1 = "5523";
        // this.country2 = "32";
        // this.divOrg21 = "5742";
        // this.divOrg31 = "82704";
    }
    
    public DriverHRDMonQueryForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("firstname")) {
            firstname = (String)parameters.get("firstname"); 
        }
        if(parameters.containsKey("lastname")) {
            lastname = (String)parameters.get("lastname"); 
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
        if(parameters.containsKey("status")) {
            status = Integer.parseInt((String)parameters.get("status")); 
        }
        if(parameters.containsKey("reportType")) {
            reportType = (String)parameters.get("reportType"); 
        }
        if(parameters.containsKey("avoidable")) {
            reportType = (String)parameters.get("avoidable"); 
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
     * @param frommonth
     * @param fromyear
     * @param tomonth
     * @param toyear
     * @param status
     * @param output
     */
    public DriverHRDMonQueryForm(String divOrg11, String divOrg12, String divOrg13,
    		String divOrg21, String divOrg22, String divOrg23, 
    		String divOrg31, String divOrg32, String divOrg33, 
    		String country1, String country2, String country3,
    		String firstname, String lastname, String frommonth,
    		String fromyear, String tomonth, String toyear, 
    		int status, String output,String reportType, String avoidable) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23,
        		divOrg31, divOrg32, divOrg33, country1, country2, country3);
        this.firstname = firstname;
		this.lastname = lastname;
		this.frommonth = frommonth;
		this.fromyear = fromyear;
		this.tomonth = tomonth;
		this.toyear = toyear;
		this.status = status;
		this.output = output;
		this.reportType = reportType;
        this.avoidable = avoidable;
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
    public DriverHRDMonQueryForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }
      

   
	public String getFirstname() {
        return firstname;
    }

    public String getFrommonth() {
        return frommonth;
    }

    public String getFromyear() {
        return fromyear;
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

    public String getTomonth() {
        return tomonth;
    }

    public String getToyear() {
        return toyear;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setFrommonth(String frommonth) {
        this.frommonth = new String(frommonth);
    }


    public void setFromyear(String fromyear) {
        this.fromyear = new String(fromyear);
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

    public void setTomonth(String tomonth) {
        this.tomonth = new String(tomonth);
    }


    public void setToyear(String toyear) {
        this.toyear = new String(toyear);
    }


    public String getFrom(Text messages) {
        if(Util.empty(frommonth) || Util.empty(fromyear)) {
            return "---";
        }else {
            return messages.getText("generic.month."+frommonth)+" / "+fromyear;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(tomonth) || Util.empty(toyear)) {
            return "---";
        }else {
            return messages.getText("generic.month."+tomonth)+" / "+toyear;
        }
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

    public BookmarkData getBookmark() {
        BookmarkData bookmark = super.getBookmark(BookmarkData.HRD_MONSANTO_REPORT);
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
        if(!Util.empty(frommonth)) {
            bookmark.addParameter("frommonth",frommonth);
        }
        if(!Util.empty(fromyear)) {
            bookmark.addParameter("fromyear",fromyear);
        }
        if(!Util.empty(tomonth)) {
            bookmark.addParameter("tomonth",tomonth);
        }
        if(!Util.empty(toyear)) {
            bookmark.addParameter("toyear",toyear);
        }
        if(!Util.empty(reportType)) {
            bookmark.addParameter("reportType",reportType);
        }
        if(!Util.empty(avoidable)) {
            bookmark.addParameter("avoidable",avoidable);
        }
        return bookmark;
    }

    public String getAvoidable() {
        return avoidable;
    }

    public void setAvoidable(String avoidable) {
        this.avoidable = avoidable;
    }

}
