package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Date;

import presentation.translator.web.driver.DriverDivOrgForm;

/**
 * @author fperez
 */


public class ReportGeneralTrainingForm extends DriverDivOrgForm  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String frommonth;
    private String fromyear;
    private String tomonth;
    private String toyear;
    private String output;
    private String state;
    private String type;

    public ReportGeneralTrainingForm() {
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
    public String getState() {
        return state;
    }
    public String getType() {
        return type;
    }
    

    public Date getInitialDate() {
    	if (isEmpty(frommonth) || isEmpty(fromyear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,1);
    		//calendar.set(Calendar.MONTH,Integer.parseInt(initialMonth.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(frommonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(fromyear.trim()));
            return calendar.getTime();
    	}
   } 	

   public Date getLastDate() {
		   
		   if (isEmpty(tomonth) || isEmpty(toyear)) {
    		return null;
    	} else {
    		int year= Integer.parseInt(toyear.trim());
	 		int month=Integer.parseInt(tomonth.trim())-1;
	 		if (month==12){
	 			month=1;
	 			year=year+1;
	 		}else{
	 			month=month+1;
	 		}
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,1);
    		calendar.set(Calendar.MONTH,month);
    		calendar.set(Calendar.YEAR,year);
            return calendar.getTime();
    	}	    	
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
    public void setState(String state) {
        this.state = state;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
   
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}
    
}	    

