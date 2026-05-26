package presentation.translator.web.driver;

import java.util.Hashtable;

import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

public class LicenseExpiryForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

    private String firstname;
    private String lastname;
    
    private String frommonth;
    private String fromyear;

    private String tomonth;
    private String toyear;
    
    private String status;
    private String output;

    
    public String getLink() {
        String link = "";
        link += "&licExpiry=true";
        link += (!Util.empty(this.firstname)) ? "&fn="+this.firstname : "";       
        link += (!Util.empty(this.lastname)) ? "&ln="+this.lastname : "";
        link += (!Util.empty(this.frommonth)) ? "&fm="+frommonth : "";
        link += (!Util.empty(this.fromyear)) ? "&fy="+fromyear : "";
        link += (!Util.empty(tomonth)) ? "&tm="+tomonth : "";
        link += (!Util.empty(toyear)) ? "&ty="+toyear : "";
        link += "&status="+status ;
        link += (!Util.empty(output)) ? "&output="+output : "";
        link += super.getLink();
        return link;
    }
    
    public LicenseExpiryForm() {
        status = "all";
    }
    
    public LicenseExpiryForm(BookmarkData bookmark) {
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
            status = (String)parameters.get("status"); 
        }
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
    public LicenseExpiryForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
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

    public String getStatus() {
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
        this.frommonth = frommonth;
    }

    public void setFromyear(String fromyear) {
        this.fromyear = fromyear;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTomonth(String tomonth) {
        this.tomonth = tomonth;
    }

    public void setToyear(String toyear) {
        this.toyear = toyear;
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

    public BookmarkData getBookmark() {
        BookmarkData bookmark = super.getBookmark(BookmarkData.DRIVER_LICENSE_EXPIRY);
        if(!Util.empty(firstname)) {
            bookmark.addParameter("firstname",firstname);
        }
        if(!Util.empty(lastname)) {
            bookmark.addParameter("lastname",lastname);
        }
        if(!Util.empty(output)) {
            bookmark.addParameter("output",output);
        }
        if(!Util.empty(status)) {
            bookmark.addParameter("status",status);
        }
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
        return bookmark;
    }

}