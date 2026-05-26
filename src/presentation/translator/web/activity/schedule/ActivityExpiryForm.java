package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class ActivityExpiryForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private String type;
	
	private String[] type;
	
	private String status;
    
	private String fromday;
	private String frommonth;
    private String fromyear;

    private String today;
    private String tomonth;
    private String toyear;
       
    private String[] vehicleAllowed;
    private String third;
    
    private String output;

    public ActivityExpiryForm() {
    	super();
    }
    
    public ActivityExpiryForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }    

    public ActivityExpiryForm(BookmarkData data) {
        super(data);
        Hashtable parameters = data.getParameters();
        if(parameters.containsKey("fromday")) {
            fromday = (String)parameters.get("fromday");
        }
        if(parameters.containsKey("frommonth")) {
            frommonth = (String)parameters.get("frommonth");
        }
        if(parameters.containsKey("fromyear")) {
            fromyear = (String)parameters.get("fromyear");
        }
        if(parameters.containsKey("today")) {
            today = (String)parameters.get("today");
        }
        if(parameters.containsKey("tomonth")) {
            tomonth = (String)parameters.get("tomonth");
        }
        if(parameters.containsKey("toyear")) {
            toyear = (String)parameters.get("toyear");
        }
        type = getList(data,"type");
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output");
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status");
        }
        if(parameters.containsKey("vehicleAllowed")) {
            vehicleAllowed = stringToArray((String)parameters.get("vehicleAllowed"));
        }
        if(parameters.containsKey("third")) {
        	third = (String)parameters.get("third");
        }
    }   

    public String getFrommonth() {
        return frommonth;
    }

    public String getFromyear() {
        return fromyear;
    }

    public String[] getType() {
		return type;
	}    

	public String getOutput() {
        return output;
    }

    public String getTomonth() {
        return tomonth;
    }

    public String getToyear() {
        return toyear;
    }

    public void setType(String type[]) {
        this.type = type;
    }    
    
    public void setFrommonth(String frommonth) {
        this.frommonth = frommonth;
    }

    public void setFromyear(String fromyear) {
        this.fromyear = fromyear;
    }


    public void setOutput(String output) {
        this.output = output;
    }

    public void setTomonth(String tomonth) {
        this.tomonth = tomonth;
    }

    public void setToyear(String toyear) {
        this.toyear = toyear;
    }

    public String getFrom(Text messages) {
        if(Util.empty(frommonth) || Util.empty(fromyear)) {
            return "---";
        }else {
            return messages.getText("generic.month."+frommonth)+" / "+fromyear;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(tomonth) || Util.empty(toyear)) {
            return "---";
        }else {
            return messages.getText("generic.month."+tomonth)+" / "+toyear;
        }
    }

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getFromday() {
		return fromday;
	}

	public void setFromday(String fromday) {
		this.fromday = fromday;
	}
	
	public Date getInitialDate() {
	    if (isEmpty(fromday) || isEmpty(frommonth) || isEmpty(fromyear)) {
	        return null;
	    } else {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.DATE,Integer.parseInt(fromday.trim()));
	        calendar.set(Calendar.MONTH,Integer.parseInt(frommonth.trim())-1);
	        calendar.set(Calendar.YEAR,Integer.parseInt(fromyear.trim()));
	        return calendar.getTime();
	    }
	} 	
	public Date getLastDate() {
	    if (isEmpty(today) || isEmpty(tomonth) || isEmpty(toyear)) {
	        return null;
	    } else {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.DATE,Integer.parseInt(today.trim()));
	        calendar.set(Calendar.MONTH,Integer.parseInt(tomonth.trim())-1);
	        calendar.set(Calendar.YEAR,Integer.parseInt(toyear.trim()));
	        return calendar.getTime();
	    }	    	
	}	
	
	private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}	 	   
	
	public String validate(UserSessionData userRequest) {
	    
	    // Tipo inválido
	    String[] type = getType();
	    if((type== null) || (type.length == 0) || ((type.length == 1) && type[0].equals("")) ) {
	        return "activityexpiryquery.error.emptytype";
	    }				
	    
	    if(Util.empty(getStatus())) {
	        return "activityexpiryquery.error.emptystatus";
	    }   		
	    
	    // Fecha inicial válida		
	    if (anyEmpty(fromday,tomonth,toyear)) 
	        return "generic.activity.activitydata.dateInvalid";
	    else {
	        if((! isEmpty(fromday) && notNumber(fromday.trim())) || 
	                (! isEmpty(frommonth) && notNumber(frommonth.trim()) ||
	                        (! isEmpty(fromyear) && notNumber(fromyear.trim())))) {
	            return "generic.activity.activitydata.dateInvalid";
	        }
	    }
	    
	    if (!isEmpty(fromday) && !isEmpty(frommonth) && !isEmpty(fromyear)) {		    
	        if (!Util.checkDate(Integer.parseInt(fromday.trim()),Integer.parseInt(frommonth.trim()),Integer.parseInt(fromyear.trim()))) {
	            return "generic.activity.activitydata.dateInvalid";
	        }
	    }
	    
	    // Fecha final válida
	    if (anyEmpty(today,tomonth,toyear)) 
	        return "generic.activity.activitydata.dateInvalid";
	    else {
	        if((! isEmpty(today) && notNumber(today.trim())) || 
	                (! isEmpty(tomonth) && notNumber(tomonth.trim()) ||
	                        (! isEmpty(toyear) && notNumber(toyear.trim())))) {
	            return "generic.activity.activitydata.dateInvalid";
	        }
	    }
	    if (!isEmpty(today) && !isEmpty(tomonth) && !isEmpty(toyear)) {
	        if (!Util.checkDate(Integer.parseInt(today.trim()),Integer.parseInt(tomonth.trim()),Integer.parseInt(toyear.trim()))) {
	            return "generic.activity.activitydata.dateInvalid";
	        }
	        
	    }	
	    
	    Calendar lastDate =Calendar.getInstance();
	    lastDate.setTime(getLastDate());
	    Calendar initialDate = Calendar.getInstance();
	    initialDate.setTime(getInitialDate());
	    if (lastDate.compareTo(initialDate) < 0) {
	        return "activityexpiryquery.error.periodInvalid";
	    }
	    
	    return null;
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
	
	public String getLink() {
	    String link = "";
	    
	    link += (!Util.empty(fromday)) ? "&fromday="+fromday : "";
	    link += (!Util.empty(frommonth)) ? "&frommonth="+frommonth : "";
	    link += (!Util.empty(fromyear)) ? "&fromyear="+fromyear : "";
	    link += (!Util.empty(today)) ? "&today="+today : "";
	    link += (!Util.empty(tomonth)) ? "&tomonth="+tomonth : "";
	    link += (!Util.empty(toyear)) ? "&toyear="+toyear : "";
	    
	    link += (vehicleAllowed != null && vehicleAllowed.length != 0) ? "&vehicleAllowed="+arrayToString(vehicleAllowed) : "";
	    link += (!Util.empty(third)) ? "&third="+third : "";
	    
	    String strType ="";
	    int i = 0;
	    while (i< (type.length -1)) {
	        strType = strType + type[i] + ",";
	        i++;
	    }
	    strType += type[i];
	    
	    link += "&type="+strType;
	    
	    link += "&status="+status;
	    
	    link += (!Util.empty(output)) ? "&output="+output : "";
	    link += super.getLink();
	    return link;
	}
	
	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	
	public String[] getVehicleAllowed() {
	    return vehicleAllowed;
	}
	
	public void setVehicleAllowed(String[] vehicleAllowed) {
	    this.vehicleAllowed = vehicleAllowed;
	}
	
   public BookmarkData getBookmark(int action) {
       BookmarkData data = super.getBookmark(action);
       if(!Util.empty(status)) {
           data.addParameter("status",status);
       }
       if(!Util.empty(fromday)) {
           data.addParameter("fromday",fromday);
       }
       if(!Util.empty(frommonth)) {
           data.addParameter("frommonth",frommonth);
       }
       if(!Util.empty(fromyear)) {
           data.addParameter("fromyear",fromyear);
       }
       if(!Util.empty(today)) {
           data.addParameter("today",today);
       }
       if(!Util.empty(tomonth)) {
           data.addParameter("tomonth",tomonth);
       }
       if(!Util.empty(toyear)) {
           data.addParameter("toyear",toyear);
       }
       if(type != null && type.length > 0) {
           data.addParameter("type",getString(type));
       }
       if(!Util.empty(output)) {
           data.addParameter("output",output);
       }
       if(vehicleAllowed != null && vehicleAllowed.length > 0) {
           data.addParameter("vehicleAllowed",arrayToString(vehicleAllowed));
       }
       if(!Util.empty(third)) {
           data.addParameter("third",third);
       }
       return data;
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

	public String getThird() {
		return third;
	}
	
	public void setThird(String third) {
		this.third = third;
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

	public void setVehicles(String value) {
		vehicleAllowed = stringToArray(value);
	}

	public String getVehicles(Text messages) {
		if (vehicleAllowed == null || vehicleAllowed.length == 0) 
			return messages.getText("generic.messages.all");
    	String result = "";
    	for(int i = 0; i < vehicleAllowed.length; i++) {
    		result += messages.getText(vehicleAllowed[i]);
    		if(i < vehicleAllowed.length-1) 
        		result += ",";
    	}
    	return result;
	}
}