package presentation.translator.web.vehicle;

import java.util.Hashtable;

import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ReportUnconformityForm extends DivOrgForm  {

	private static final long serialVersionUID = 1L;
    
	private String frommonth;
    private String fromyear;
    private String tomonth;
    private String toyear;
    private String output;
    private String type;
    
    
    public ReportUnconformityForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("frommonth")) {
        	frommonth = (String)parameters.get("frommonth");
        }
        if(parameters.containsKey("fromyear")) {
        	fromyear = (String)parameters.get("fromyear"); 
        }
        if(parameters.containsKey("tomonth")) {
        	tomonth= (String)parameters.get("tomonth"); 
        }
        if(parameters.containsKey("toyear")) {
        	toyear = (String)parameters.get("toyear"); 
        }
        if(parameters.containsKey("output")) {
        	output= (String)parameters.get("output"); 
        }
        if(parameters.containsKey("type")) {
        	type = (String)parameters.get("type"); 
        }
    }
    
    public ReportUnconformityForm() {
        //Calendar calendar = Calendar.getInstance();
        frommonth = ""; //String.valueOf(calendar.get(Calendar.MONTH) + 1);
        tomonth = ""; //String.valueOf(calendar.get(Calendar.MONTH) + 1);
        fromyear = ""; // String.valueOf(calendar.get(Calendar.YEAR) - 1);
        toyear = ""; //String.valueOf(calendar.get(Calendar.YEAR));
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
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        return data;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}	    
