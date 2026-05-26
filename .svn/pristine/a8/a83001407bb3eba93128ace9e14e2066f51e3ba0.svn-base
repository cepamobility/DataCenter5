package presentation.translator.web.queries;

import presentation.translator.web.util.Util;
import communication.administration.bookmark.tables.BookmarkData;
import communication.event.tables.EventDataType;

/**
 * @author jrodriguez
 */
public class ReportAPMMByPeriodForm extends ReportAPMMForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
    private String dataType;
    private String order1;
    private String order2;
    private String order3;
    private String fleetTypes;
    private String reportType;
    
    public ReportAPMMByPeriodForm() {
        super();
        type = "period";
        dataType = String.valueOf(EventDataType.ACCIDENT);
        fleetTypes = "0";
        reportType = "apmm";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public void setOrder1(String order1) {
        this.order1 = order1;
    }

    public void setOrder2(String order2) {
        this.order2 = order2;
    }

    public void setOrder3(String order3) {
        this.order3 = order3;
    }
    
    public BookmarkData getBookmark(int action) {
        BookmarkData data = super.getBookmark(action);
        if(!Util.empty(order1)) {
            data.addParameter("order1",order1);
        }
        if(!Util.empty(order2)) {
            data.addParameter("order2",order2);
        }
        if(!Util.empty(order3)) {
            data.addParameter("order3",order3);
        }
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        if(!Util.empty(dataType)) {
            data.addParameter("dataType",dataType);
        }
        if(!Util.empty(fleetTypes)) {
            data.addParameter("fleetTypes",fleetTypes);
        }
        return data;
    }

	public String getFleetTypes() {
		return fleetTypes;
	}

	public void setFleetTypes(String fleetTypes) {
		this.fleetTypes = fleetTypes;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}	    
