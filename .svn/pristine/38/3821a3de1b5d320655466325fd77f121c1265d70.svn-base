/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.driver;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;


/**
 * @author jrodriguez
 */
public class DriverAccessLogForm extends ActionForm {

    private static final long serialVersionUID = 1L;

	private String startDate;
	private String startMonth;
	private String startYear;
	private String endDate;
	private String endMonth;
	private String endYear;
    private String[] divOrg31;
    private String access;

	private String output;

	private String corporationId;

    public DriverAccessLogForm() {
        access = "-1";
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String[] getDivOrg31() {
		return divOrg31;
	}

	public void setDivOrg31(String[] contratists) {
		this.divOrg31 = contratists;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public Date getStart() {
		return Util.getDate(startDate, startMonth, startYear);
	}

	public Date getEnd() {
		return Util.getDate(endDate, endMonth, endYear);
	}

	public String getContratistIds() {
		if(divOrg31 == null || divOrg31.length == 0 || (divOrg31.length == 1 && divOrg31[0].equals("0"))) {
			return null;
		}
		String c = "0";
		for (int i = 0; i < divOrg31.length; i++) {
			c += ","+divOrg31[i];
		}
		return c;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getStartReport() {
		Date start = Util.getDate(startDate, startMonth, startYear);
		if(start == null) {
			return "---";
		}else {
			return Util.formatDate(start);
		}
	}

	public String getEndReport() {
		Date end = Util.getDate(endDate, endMonth, endYear);
		if(end == null) {
			return "---";
		}else {
			return Util.formatDate(end);
		}
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}
}
