package presentation.translator.web.vehicle;

import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.user.UserSessionData;
import communication.vehicle.tables.VehicleData;

/**
 * 
 * @author gbenaderet
 *
 */
public class VehicleContractReportForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fromDate;
    private String fromMonth;
    private String fromYear;
    private String toDate;
    private String toMonth;
    private String toYear;
    
    private String vehicleType;
    private String type; // tipo de contrato
    private String status;
    private String output;    

    
	public VehicleContractReportForm() {
		super();
		status = String.valueOf(VehicleData.ACTIVE);
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
	        // link.append((!Util.empty(plate)) ? "&pl="+plate : "");
	        link.append((!Util.empty(vehicleType)) ? "&vt="+vehicleType: "");
	        link.append((!Util.empty(type)) ? "&t="+type : "");
	        link.append((!Util.empty(output)) ? "&o="+output : "");
            link.append((!Util.empty(status)) ? "&status="+status : "");
            link.append((!Util.empty(fromDate)) ? "&fd=" + fromDate : "");
            link.append((!Util.empty(fromMonth)) ? "&fm=" + fromMonth : "");
            link.append((!Util.empty(fromYear)) ? "&fy=" + fromYear : "");
            link.append((!Util.empty(toDate)) ? "&td=" + toDate : "");
            link.append((!Util.empty(toMonth)) ? "&tm=" + toMonth : "");
            link.append((!Util.empty(toYear)) ? "&ty=" + toYear : "");
            
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
    public VehicleContractReportForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
        status = String.valueOf(VehicleData.ACTIVE);
    }

    public VehicleContractReportForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("vehicleType")) {
            vehicleType = (String)parameters.get("vehicleType"); 
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type"); 
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
        }else{
        	status = String.valueOf(VehicleData.ACTIVE);
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
    }
    
    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.VEHICLE_REPORT);
        if(!Util.empty(vehicleType)) {
            data.addParameter("vehicleType",vehicleType);
        }
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }else{
        	status = String.valueOf(VehicleData.ACTIVE);
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


	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}


	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String validate(UserSessionData userRequest) {

	    // Fecha FROM válida
	    if (anyEmpty(fromDate,fromMonth,fromYear)) 
	    	return "generic.activity.activitydata.dateInvalid";
	    else {
		    if((! isEmpty(fromDate) && notNumber(fromDate.trim())) || 
		    		(! isEmpty(fromMonth) && notNumber(fromMonth.trim()) ||
		    		(! isEmpty(fromYear) && notNumber(fromYear.trim())))) {
		    	return "generic.activity.activitydata.dateInvalid";
		    }
	    }
	    if (!Util.checkDate(Integer.parseInt(fromDate),Integer.parseInt(fromMonth),Integer.parseInt(fromYear))) {
	    	return "generic.activity.activitydata.dateInvalid";
	    }	    
	 
	 // Fecha TO válida
	    if (anyEmpty(toDate,toMonth,toYear)) 
	    	return "generic.activity.activitydata.dateInvalid";
	    else {
		    if((! isEmpty(toDate) && notNumber(toDate.trim())) || 
		    		(! isEmpty(toMonth) && notNumber(toMonth.trim()) ||
		    		(! isEmpty(toYear) && notNumber(toYear.trim())))) {
		    	return "generic.activity.activitydata.dateInvalid";
		    }
	    }
	    if (!Util.checkDate(Integer.parseInt(toDate),Integer.parseInt(toMonth),Integer.parseInt(toYear))) {
	    	return "generic.activity.activitydata.dateInvalid";
	    }	    
	 
	    
	    return null;
	}
	
	 private boolean anyEmpty(String value1,String value2,String value3) {
	    	int any = 0;
	    	if ((value1 == null) || (value1.trim().length()==0))
	    		any ++;
	    	if ((value2 == null) || (value2.trim().length()==0))
	    		any ++;
	    	if ((value3 == null) || (value3.trim().length()==0))
	    		any ++;
		    //return ((any > 0 && any < 3));
	    	return (any > 0);
		}
	 
	 /**
	  * 
	  * @param value
	  * @return
	  */
	 private boolean isEmpty(String value) {
	    	System.out.println(value);
		    return ((value == null) || (value.trim().length()==0));
	 }	    
	    
	 /**
	  * 
	  * @param value
	  * @return
	  */
	 private boolean notNumber(String value) {
	    	
		    try {
		    	new Integer(value.trim());
		    }
		    catch (Exception e) {
		    	return true;
		    }	
		    return false;
	 }
	
}
