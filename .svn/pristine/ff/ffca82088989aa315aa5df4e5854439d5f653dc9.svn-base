package presentation.translator.web.fleet;

import org.apache.struts.action.ActionForm;

public class InsertedOdometerForm extends ActionForm {

	private static final long serialVersionUID = 1L;

    private String fleet;
    
    private String fromMonth;
    private String fromYear;

	private String toMonth;
	private String toYear;

    public InsertedOdometerForm() {
    	super();
    }
    
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}	 	   
    
	private boolean notNumber(String value) {
	    try {
	        new Integer(value.trim());
	    } catch (Exception e) {
	        return true;
	    }	
	    return false;
	}		
	
	private boolean anyEmpty(String value1,String value2,String value3) {
	    int any = 0;
	    if ((value1 == null) || (value1.trim().length()==0))
	        any ++;
	    if ((value2 == null) || (value2.trim().length()==0))
	        any ++;
	    if ((value3 == null) || (value3.trim().length()==0))
	        any ++;
	    return (any > 0);
	}

	public String getFleet() {
		return fleet;
	}

	public void setFleet(String fleet) {
		this.fleet = fleet;
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
	
}