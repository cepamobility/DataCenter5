/**
 * Created on 06-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.event;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

public class EventReportForm extends DriverDivOrgForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accidents;
	private String incidents;
	private String nearmiss;
	private String fines;
	private String month1;
	private String year1;
	private String month2;
	private String year2;
	private String Use;
	private String type;
	private String order1;
    private String order2;
    private String order3;
    private String order4;
	
    private String output;
    private String reportType;
    private String record;
    private String driverStatus;
    
    private String workingYes;
    private String workingNo;
    private String workingNoData;
    
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
    public EventReportForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }

	public EventReportForm() {
        // Calendar calendar = Calendar.getInstance();
        month1 = ""; // String.valueOf(calendar.get(Calendar.MONTH) + 1);
        month2 = ""; // String.valueOf(calendar.get(Calendar.MONTH) + 1);
        year1 = ""; // String.valueOf(calendar.get(Calendar.YEAR) - 1);
        year2 = ""; // String.valueOf(calendar.get(Calendar.YEAR));
        order1 = "Unit";
        order2 = "Division";
        order3 = "Subdivision";
        record = "all";
    }
	
    public EventReportForm(BookmarkData data) {
        super(data);
        Hashtable parameters = data.getParameters();
        if(parameters.containsKey("accidents")) {
            accidents = (String)parameters.get("accidents"); 
        }
        if(parameters.containsKey("incidents")) {
            incidents = (String)parameters.get("incidents"); 
        }
        if(parameters.containsKey("nearmiss")) {
        	nearmiss = (String)parameters.get("nearmiss"); 
        }
        if(parameters.containsKey("fines")) {
            fines = (String)parameters.get("fines"); 
        }
        if(parameters.containsKey("month1")) {
            month1 = (String)parameters.get("month1"); 
        }
        if(parameters.containsKey("year1")) {
            year1 = (String)parameters.get("year1"); 
        }
        if(parameters.containsKey("month2")) {
            month2 = (String)parameters.get("month2"); 
        }
        if(parameters.containsKey("year2")) {
            year2 = (String)parameters.get("year2"); 
        }
        if(parameters.containsKey("Use")) {
            Use = (String)parameters.get("Use"); 
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type"); 
        }
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
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
        if(parameters.containsKey("reportType")) {
            reportType = (String)parameters.get("reportType"); 
        }
        if(parameters.containsKey("record")) {
            record = (String)parameters.get("record"); 
        }
        if(parameters.containsKey("driverStatus")) {
            driverStatus = (String)parameters.get("driverStatus"); 
        }
    }
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	accidents = null;
    	incidents = null;
    	nearmiss = null;
    	fines = null;
    	workingNo = null;
    	workingYes = null;
    	workingNoData = null;
    }
    /**
     * @return Returns the output.
     */
    public String getOutput() {
        return output;
    }


	public String getAccidents() {
		return accidents;
	}
	


	public void setAccidents(String accidents) {
		this.accidents = accidents;
	}


	public String getFines() {
		return fines;
	}


	public void setFines(String fines) {
		this.fines = fines;
	}


	public String getIncidents() {
		return incidents;
	}


	public void setIncidents(String incidents) {
		this.incidents = incidents;
	}


	public String getMonth1() {
		return month1;
	}


	public void setMonth1(String month1) {
		this.month1 = month1;
	}


	public String getMonth2() {
		return month2;
	}


	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	
	
	public String getYear1() {
		return year1;
	}


	public void setYear1(String year1) {
		this.year1 = year1;
	}


	public String getYear2() {
		return year2;
	}


	public void setYear2(String year2) {
		this.year2 = year2;
	}


	public void setOutput(String output) {
		this.output = output;
	}


	public String getUse() {
		return Use;
	}


	public void setUse(String use) {
		this.Use = use;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public String getFrom(Text messages) {
        if(Util.empty(month1) || Util.empty(year1)) {
            return "---";
        }else {
            if(month1.startsWith("Q")) {
                return month1+" / "+year1;
            }
            return messages.getText("generic.month."+month1)+" / "+year1;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(month2) || Util.empty(year2)) {
            return "---";
        }else {
            if(month2.startsWith("Q")) {
                return month2+" / "+year2;
            }
            return messages.getText("generic.month."+month2)+" / "+year2;
        }
    }
    
    public String getOrder1() {
        return order1;
    }
    
    public String getOrder2() {
        return order2;
    }
    
    public String getOrder3() {
        return order3;
    }
    public String getOrder4() {
        return order4;
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
    
    public void setOrder4(String order4) {
        this.order4 = order4;
    }
    
    public String getRecord() {
		return record;
	}
    public void setRecord(String record) {
		this.record=record;
	}
    
    public String getLink() {
        String link = "";
        link += (!Util.empty(month1)) ? "&month1="+month1 : "";
        link += (!Util.empty(month2)) ? "&month2="+month2 : "";
        link += (!Util.empty(reportType)) ? "&reportType="+reportType : "";
        link += (!Util.empty(year1)) ? "&year1="+year1 : "";
        link += (!Util.empty(year2)) ? "&year2="+year2 : "";
        link += (!Util.empty(accidents)) ? "&accident="+accidents : "";
        link += (!Util.empty(incidents)) ? "&incident="+incidents : "";
        link += (!Util.empty(nearmiss)) ? "&nearmiss="+nearmiss : "";
        link += (!Util.empty(fines)) ? "&fine="+fines : "";
        link += (!Util.empty(type)) ? "&type="+type : "";
        link += (!Util.empty(output)) ? "&output="+output : "";
        link += (!Util.empty(driverStatus)) ? "&driverStatus="+driverStatus : "";
        link += super.getLink();
        return link;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }


    public BookmarkData getBookmark(int action) {
        BookmarkData data = super.getBookmark(action);
        if(!Util.empty(accidents)) {
            data.addParameter("accidents",accidents);
        }
        if(!Util.empty(incidents)) {
            data.addParameter("incidents",incidents);
        }
        if(!Util.empty(nearmiss)) {
            data.addParameter("nearmiss",nearmiss);
        }
        if(!Util.empty(fines)) {
            data.addParameter("fines",fines);
        }
        if(!Util.empty(month1)) {
            data.addParameter("month1",month1);
        }
        if(!Util.empty(year1)) {
            data.addParameter("year1",year1);
        }
        if(!Util.empty(month2)) {
            data.addParameter("month2",month2);
        }
        if(!Util.empty(year2)) {
            data.addParameter("year2",year2);
        }
        if(!Util.empty(Use)) {
            data.addParameter("Use",Use);
        }
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
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
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(reportType)) {
            data.addParameter("reportType",reportType);
        }
        if(!Util.empty(record)) {
            data.addParameter("record",record);
        }
        if(!Util.empty(driverStatus)) {
            data.addParameter("driverStatus",driverStatus);
        }
        return data;
    }


	public String getNearmiss() {
		return nearmiss;
	}


	public void setNearmiss(String nearmiss) {
		this.nearmiss = nearmiss;
	}


	public String getWorkingYes() {
		return workingYes;
	}


	public void setWorkingYes(String workingYes) {
		this.workingYes = workingYes;
	}


	public String getWorkingNo() {
		return workingNo;
	}


	public void setWorkingNo(String workingNo) {
		this.workingNo = workingNo;
	}


	public String getWorkingNoData() {
		return workingNoData;
	}


	public void setWorkingNoData(String workingNoData) {
		this.workingNoData = workingNoData;
	}

}
