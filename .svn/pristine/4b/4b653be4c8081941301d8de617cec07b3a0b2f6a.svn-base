package presentation.translator.web.vehicle;
import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class VehicleWithoutPursuitRouteForm  extends DriverDivOrgForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String plate;

    private String frommonth;
    private String fromyear;

    private String tomonth;
    private String toyear;
    
    private int status;
    private String output;
    
    public VehicleWithoutPursuitRouteForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("status")) {
            status = Integer.parseInt((String)parameters.get("status")); 
        }
        if(parameters.containsKey("plate")) {
            plate = (String)parameters.get("plate"); 
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

    public String getLink() {
        String link = "";
        link += "&ecQuery=true";
        link += (!Util.empty(this.plate)) ? "&plate="+this.plate : "";       
        link += (!Util.empty(frommonth)) ? "&fm="+frommonth : "";
        link += (!Util.empty(fromyear)) ? "&fy="+fromyear : "";
        link += (!Util.empty(tomonth)) ? "&tm="+tomonth : "";
        link += (!Util.empty(toyear)) ? "&ty="+toyear : "";
        link += "&status="+status ;
        link += (!Util.empty(output)) ? "&output="+output : "";
        link += super.getLink();
        return link;
    }
    public VehicleWithoutPursuitRouteForm() {
        status = DriverData.ACTIVE;
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
     * @param plate
     * @param frommonth
     * @param fromyear
     * @param tomonth
     * @param toyear
     * @param status
     * @param output
     */
    public VehicleWithoutPursuitRouteForm(String divOrg11, String divOrg12, String divOrg13,
    		String divOrg21, String divOrg22, String divOrg23, 
    		String divOrg31, String divOrg32, String divOrg33, 
    		String country1, String country2, String country3,
    		String plate, String frommonth,
    		String fromyear, String tomonth, String toyear, 
    		int status, String output) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23,
        		divOrg31, divOrg32, divOrg33, country1, country2, country3);
        this.plate = plate;
		this.frommonth = frommonth;
		this.fromyear = fromyear;
		this.tomonth = tomonth;
		this.toyear = toyear;
		this.status = status;
		this.output = output;
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
    public VehicleWithoutPursuitRouteForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }
   

    public String getFrommonth() {
        return frommonth;
    }

    public String getFromyear() {
        return fromyear;
    }

    public String getPlate() {
        return plate;
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

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setFrommonth(String frommonth) {
        this.frommonth = new String(frommonth);
    }


    public void setFromyear(String fromyear) {
        this.fromyear = new String(fromyear);
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
	
    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.VEHICLE_WO_PURSUIT_REPORT);
        data.addParameter("status",String.valueOf(status));
        if(!Util.empty(plate)) {
            data.addParameter("plate",plate);
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

