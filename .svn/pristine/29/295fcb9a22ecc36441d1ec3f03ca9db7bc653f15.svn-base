/**
 * Created on 27-jun-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.driver;

import presentation.translator.web.util.Util;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class AptitudTestReportForm extends DriverReportForm {

    private String type;
    private String apt;
    private String fromMonth;
    private String fromYear;
    private String toMonth;
    private String toYear;
	private String lastName;
    
    public AptitudTestReportForm(String divOrg11, String divOrg12,
            String divOrg13, String divOrg21, String divOrg22, String divOrg23,
            String divOrg31, String divOrg32, String divOrg33, String country1,
            String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23,
                divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }

    public AptitudTestReportForm() {
        super();
        type = String.valueOf(DriverData.ALL);
        apt = String.valueOf(DriverData.ALL);
    }

    public String getApt() {
        return apt;
    }

    public String getType() {
        return type;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public void setType(String type) {
        this.type = type;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	   public String getFrom(Text messages) {
	        if(Util.empty(fromMonth) || Util.empty(fromYear)) {
	            return "---";
	        }else {
	            return messages.getText("generic.month."+Integer.parseInt(fromMonth))+" / "+fromYear;
	        }
	    }

	    public String getTo(Text messages) {
	        if(Util.empty(toMonth) || Util.empty(toYear)) {
	            return "---";
	        }else {
	            return messages.getText("generic.month."+Integer.parseInt(toMonth))+" / "+toYear;
	        }
	    }
}
