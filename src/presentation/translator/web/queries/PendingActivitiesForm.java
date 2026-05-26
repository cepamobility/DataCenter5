package presentation.translator.web.queries;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class PendingActivitiesForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;
    
	private String fromday;
	private String frommonth;
    private String fromyear;
	private String today;
    private String tomonth;
    private String toyear;
    private String output;
    private String status;
    private String[] anual;
    private String[] mitigation;
    
    public PendingActivitiesForm() {
        frommonth = ""; //String.valueOf(calendar.get(Calendar.MONTH) + 1);
        tomonth = ""; //String.valueOf(calendar.get(Calendar.MONTH) + 1);
        fromyear = ""; // String.valueOf(calendar.get(Calendar.YEAR) - 1);
        toyear = ""; //String.valueOf(calendar.get(Calendar.YEAR));
    }

    public String getFromday() {
		return fromday;
	}

	public void setFromday(String fromday) {
		this.fromday = fromday;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getFrommonth() {
        return frommonth;
    }

    public String getFromyear() {
        return fromyear;
    }

    public String getTomonth() {
        return tomonth;
    }

    public String getToyear() {
        return toyear;
    }

    public void setFrommonth(String frommonth) {
        this.frommonth = frommonth;
    }

    public void setFromyear(String fromyear) {
        this.fromyear = fromyear;
    }

    public void setTomonth(String tomonth) {
        this.tomonth = tomonth;
    }

    public void setToyear(String toyear) {
        this.toyear = toyear;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getFrom(Text messages) {
        if(Util.empty(frommonth) || Util.empty(fromyear)) {
            return "---";
        }else {
            if(frommonth.startsWith("Q")) {
                return frommonth+" / "+fromyear;
            }
            return messages.getText("generic.month."+frommonth)+" / "+fromyear;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(tomonth) || Util.empty(toyear)) {
            return "---";
        }else {
            if(tomonth.startsWith("Q")) {
                return tomonth+" / "+toyear;
            }
            return messages.getText("generic.month."+tomonth)+" / "+toyear;
        }
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getAnual() {
        return anual;
    }

    public void setAnual(String[] anual) {
        this.anual = anual;
    }

    public BookmarkData getBookmark(int action) {
        BookmarkData data = super.getBookmark(action);
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
        if(!Util.empty(status)) {
            data.addParameter("status",status);
        }
        if(anual != null && anual.length > 0) {
            data.addParameter("anual",getString(anual));
        }
        if(mitigation != null && mitigation.length > 0) {
            data.addParameter("mitigation",getString(mitigation));
        }
        return data;
    }

	public String[] getMitigation() {
		return mitigation;
	}

	public void setMitigation(String[] mitigation) {
		this.mitigation = mitigation;
	}

    protected String getString(String[] list) {
        String s = "";
        for(int i = 0; i < list.length; i++) {
            s += list[i] + " ";
        }
        return s.trim();
    }
}	    
