/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Date;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.user.UserSessionData;

/**
 * @author adocarmo
 */
public class ActivitySearchForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String type;
    private String state;
    private String country;
    private String city;    
	private String initialDay;
	private String initialMonth;
	private String initialYear;
	private String lastDay;
	private String lastMonth;
	private String lastYear;		

	private String all;
    private String pageNum;
    private String count;
    
    private String resetPN;
    
    private String form;
    private String activityId;
    private String name;

    private String nextLink;
    
    /**
     * 
     */
    public ActivitySearchForm() {
    	this.count = "10";
    	this.resetPN = "false";
    }
    /**
     * 
     */
    public ActivitySearchForm(String form,String activityId,String name,int pageNum,int count,String link) {
        this.form = form;
        this.activityId = activityId;
        this.name = name;
        this.pageNum = String.valueOf(pageNum);
        this.count = String.valueOf(count);
        this.nextLink = link;
        this.resetPN = "false";
    }

    /**
	 * @return Returns the registrationNumber.
	 */
	public String getType() {
		return type;
	}
    
	/**
	 * @param registrationNumber The registrationNumber to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

    
    /**
     * @return Returns the all.
     */
    public String getAll() {
        return all;
    }

    /**
     * @return Returns the count.
     */
    public String getCount() {
        return count;
    }

    /**
     * @return Returns the first.
     */
    public String getPageNum() {
        return pageNum;
    }

    /**
     * @param all The all to set.
     */
    public void setAll(String all) {
        this.all = all;
    }

    /**
     * @param count The count to set.
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @param pageNum The first to set.
     */
    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the test.
     */
    public String getForm() {
        return form;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param test The test to set.
     */
    public void setForm(String form) {
        this.form = form;
    }

    /**
     * @return Returns the vehicle.
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * @param driver The driver to set.
     */
    public void setVehicle(String activityId) {
        this.activityId = activityId;
    }
    /**
     * @return Returns the nextLink.
     */
    public String getNextLink() {
        return nextLink;
    }
    /**
     * @param driverId The vehicleId to set.
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    /**
     * @param nextLink The nextLink to set.
     */
    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }
	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
    
	   public Date getInitialDate() {
	    	if (isEmpty(initialDay) || isEmpty(initialMonth) || isEmpty(initialYear)) {
	    		return null;
	    	} else {
	        	Calendar calendar = Calendar.getInstance();
	    		calendar.set(Calendar.DATE,Integer.parseInt(initialDay.trim()));
	    		//calendar.set(Calendar.MONTH,Integer.parseInt(initialMonth.trim()));
	    		calendar.set(Calendar.MONTH,Integer.parseInt(initialMonth.trim())-1);
	    		calendar.set(Calendar.YEAR,Integer.parseInt(initialYear.trim()));
	            return calendar.getTime();
	    	}
	   } 	
	 	   public Date getLastDate() {
		    	if (isEmpty(lastDay) || isEmpty(lastMonth) || isEmpty(lastYear)) {
		    		return null;
		    	} else {
		        	Calendar calendar = Calendar.getInstance();
		    		calendar.set(Calendar.DATE,Integer.parseInt(lastDay.trim()));
		    		//calendar.set(Calendar.MONTH,Integer.parseInt(lastMonth.trim()));
		    		calendar.set(Calendar.MONTH,Integer.parseInt(lastMonth.trim())-1);
		    		calendar.set(Calendar.YEAR,Integer.parseInt(lastYear.trim()));
		            return calendar.getTime();
		    	}	    	
	 	   }
		/**
		 * @return Returns the initialDay.
		 */
		public String getInitialDay() {
			return initialDay;
		}
		/**
		 * @param initialDay The initialDay to set.
		 */
		public void setInitialDay(String initialDay) {
			this.initialDay = initialDay;
		}
		/**
		 * @return Returns the initialMonth.
		 */
		public String getInitialMonth() {
			return initialMonth;
		}
		/**
		 * @param initialMonth The initialMonth to set.
		 */
		public void setInitialMonth(String initialMonth) {
			this.initialMonth = initialMonth;
		}
		/**
		 * @return Returns the initialYear.
		 */
		public String getInitialYear() {
			return initialYear;
		}
		/**
		 * @param initialYear The initialYear to set.
		 */
		public void setInitialYear(String initialYear) {
			this.initialYear = initialYear;
		}
		/**
		 * @return Returns the lastDay.
		 */
		public String getLastDay() {
			return lastDay;
		}
		/**
		 * @param lastDay The lastDay to set.
		 */
		public void setLastDay(String lastDay) {
			this.lastDay = lastDay;
		}
		/**
		 * @return Returns the lastMonth.
		 */
		public String getLastMonth() {
			return lastMonth;
		}
		/**
		 * @param lastMonth The lastMonth to set.
		 */
		public void setLastMonth(String lastMonth) {
			this.lastMonth = lastMonth;
		}
		/**
		 * @return Returns the lastYear.
		 */
		public String getLastYear() {
			return lastYear;
		}
		/**
		 * @param lastYear The lastYear to set.
		 */
		public void setLastYear(String lastYear) {
			this.lastYear = lastYear;
		}
		
	    private boolean isEmpty(String value) {
		    return ((value == null) || (value.trim().length()==0));
		}
		/**
		 * @return Returns the city.
		 */
		public String getCity() {
			return city;
		}
		/**
		 * @param city The city to set.
		 */
		public void setCity(String city) {
			this.city = city;
		}
		/**
		 * @return Returns the resetPN.
		 */
		public String getResetPN() {
			return resetPN;
		}
		/**
		 * @param resetPN The resetPN to set.
		 */
		public void setResetPN(String resetPN) {
			this.resetPN = resetPN;
		}	    		
		
		public String validate(UserSessionData userRequest) {

		    // Fecha inicial válida		
		    if (anyEmpty(initialDay,initialMonth,initialYear)) 
		    	return "generic.activity.activitydata.dateInvalid";
		    else {
			    if((! isEmpty(initialDay) && notNumber(initialDay.trim())) || 
			    		(! isEmpty(initialMonth) && notNumber(initialMonth.trim()) ||
			    		(! isEmpty(initialYear) && notNumber(initialYear.trim())))) {
			    	return "generic.activity.activitydata.dateInvalid";
			    }
		    }
		    
		    if (!isEmpty(initialDay) && !isEmpty(initialMonth) && !isEmpty(initialYear)) {		    
			    if (!Util.checkDate(Integer.parseInt(initialDay.trim()),Integer.parseInt(initialMonth.trim()),Integer.parseInt(initialYear.trim()))) {
			    	return "generic.activity.activitydata.dateInvalid";
			    }
		    }

		    // Fecha final válida
		    if (anyEmpty(lastDay,lastMonth,lastYear)) 
		    	return "generic.activity.activitydata.dateInvalid";
		    else {
			    if((! isEmpty(lastDay) && notNumber(lastDay.trim())) || 
			    		(! isEmpty(lastMonth) && notNumber(lastMonth.trim()) ||
			    		(! isEmpty(lastYear) && notNumber(lastYear.trim())))) {
			    	return "generic.activity.activitydata.dateInvalid";
			    }
		    }
		    if (!isEmpty(lastDay) && !isEmpty(lastMonth) && !isEmpty(lastYear)) {
		    	if (!Util.checkDate(Integer.parseInt(lastDay.trim()),Integer.parseInt(lastMonth.trim()),Integer.parseInt(lastYear.trim()))) {
		    		return "generic.activity.activitydata.dateInvalid";
		    	}
		    }	
		    	    
		    return null;
		}   
		
	    private boolean notNumber(String value) {
	    	
		    try {
		    	new Integer(value.trim());
		    }
		    catch (Exception e) {
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
		    return ((any > 0 && any < 3));
	    	//return (any > 0);
		}
	    
		
}
