package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import presentation.translator.web.driver.DriverDivOrgForm;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

public class ActivityMaxExpiryForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private String type;
	
	private String firstName;
	private String lastName;
	
	private String status;
           
    private String vehicleAllowed;
    
    private String fromDay;
    private String fromMonth;
    private String fromYear;

	private String toYear;
	private String toMonth;
	private String toDay;

    public ActivityMaxExpiryForm() {
    	super();
    }
    
    public ActivityMaxExpiryForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
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
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	
	public String getVehicleAllowed() {
	    return vehicleAllowed;
	}
	
	public void setVehicleAllowed(String vehicleAllowed) {
	    this.vehicleAllowed = vehicleAllowed;
	}
           
   private String getString(String[] list) {
       String s = "";
       for(int i = 0; i < list.length; i++) {
           s += list[i] + " ";
       }
       return s.trim();
   }

   private String[] getList(BookmarkData bookmark, String key) {
       Hashtable parameters = bookmark.getParameters(); 
       if(!parameters.containsKey(key)) {
          return new String[0]; 
       }else {
           Collection list = new LinkedList();
           StringTokenizer tokenizer = new StringTokenizer((String)parameters.get(key));
           while(tokenizer.hasMoreTokens()) {
               list.add(tokenizer.nextToken());
           }
           return (String[])list.toArray(new String[0]);
       }
   }

    public String arrayToString(String[] value) {
    	String result = "";
    	for(int i = 0; i < value.length; i++) {
    		result += value[i];
    		if(i < value.length-1) 
        		result += "-";
    	}
    	return result;
    }
    
    public String[] stringToArray(String value) {
    	StringTokenizer tokenizer = new StringTokenizer(value,"-");
    	String[] result = new String[tokenizer.countTokens()];
    	for(int i = 0; i < result.length; i++) {
    		result[i] = tokenizer.nextToken();
    	}
    	return result;
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

    public Date getFrom() {
        if(notNumber(fromDay) || notNumber(fromMonth) || notNumber(fromYear)) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE,Integer.parseInt(fromDay));
        c.set(Calendar.MONTH,Integer.parseInt(fromMonth)-1);
        c.set(Calendar.YEAR,Integer.parseInt(fromYear));
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        return c.getTime();
    }

    public Date getTo() {
        if(notNumber(toDay) || notNumber(toMonth) || notNumber(toYear)) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH,Integer.parseInt(toMonth)-1);
        c.set(Calendar.YEAR,Integer.parseInt(toYear));
        c.set(Calendar.DATE,Integer.parseInt(toDay));
        c.set(Calendar.HOUR,23);
        c.set(Calendar.MINUTE,59);
        return c.getTime();
    }

	public String getFromDay() {
		return fromDay;
	}

	public void setFromDay(String fromDay) {
		this.fromDay = fromDay;
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

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getToDay() {
		return toDay;
	}

	public void setToDay(String toDay) {
		this.toDay = toDay;
	}

}