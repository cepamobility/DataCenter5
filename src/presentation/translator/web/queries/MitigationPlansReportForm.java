package presentation.translator.web.queries;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class MitigationPlansReportForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String corporationId;

    private String status;

    private String output;
    
    public MitigationPlansReportForm() {
    }


    public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getCorporationId() {
		return corporationId;
	}


	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}


	public String getOutput() {
		return output;
	}


	public void setOutput(String output) {
		this.output = output;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public BookmarkData getBookmark(int action) {
        BookmarkData data = super.getBookmark(action);
        if(!Util.empty(firstName)) {
            data.addParameter("firstName",firstName);
        }
        if(!Util.empty(lastName)) {
            data.addParameter("lastName",lastName);
        }
        if(!Util.empty(corporationId)) {
            data.addParameter("corporationId",corporationId);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }
        return data;
    }
}	    
