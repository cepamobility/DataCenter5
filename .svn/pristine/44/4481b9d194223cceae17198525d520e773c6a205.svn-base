/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.driver;


import java.util.Calendar;
import java.util.Date;

import presentation.translator.web.util.Util;

/**
 * @author jrodriguez
 */
public class DriverAdvancedSearchForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String birthMonth;
	private String birthYear;
    private String status;
    
    private String all;
    private String pageNum;
    private String count;
    
    private String form;
    private String driverId;
    private String name;

    private String nextLink;
    
    private String temporal;
    private String temp;
    private String idActivity;
    
    /**
     * 
     */
    public DriverAdvancedSearchForm() {
        this.count = "10";
    }
    /**
     * 
     */
    public DriverAdvancedSearchForm(String form,String driverId,String name,int pageNum,int count,String status,String link) {
        this.form = form;
        this.driverId = driverId;
        this.name = name;
        this.pageNum = String.valueOf(pageNum);
        this.count = String.valueOf(count);
        this.status = status;
        this.nextLink = link;
    }

    /**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
    
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the birthDate.
	 */
	public String getBirthDate() {
		return birthDate;
	}
    
	/**
	 * @param birthDate The birthDate to set.
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

    /**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
    
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
    
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the birthMonth.
	 */
	public String getBirthMonth() {
		return birthMonth;
	}
	/**
	 * @param birthMonth The birthMonth to set.
	 */
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	/**
	 * @return Returns the birthYear.
	 */
	public String getBirthYear() {
		return birthYear;
	}
	/**
	 * @param birthYear The birthYear to set.
	 */
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	
    public Date getBirth() {
        if(Util.empty(birthDate) || Util.empty(birthMonth) || Util.empty(birthYear)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(birthDate));
        calendar.set(Calendar.MONTH,Integer.parseInt(birthMonth)-1);
        calendar.set(Calendar.YEAR,Integer.parseInt(birthYear));
        return calendar.getTime();
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
     * @return Returns the driver.
     */
    public String getDriverId() {
        return driverId;
    }

    /**
     * @param driver The driver to set.
     */
    public void setDriver(String driverId) {
        this.driverId = driverId;
    }
    /**
     * @return Returns the nextLink.
     */
    public String getNextLink() {
        return nextLink;
    }
    /**
     * @param driverId The driverId to set.
     */
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    /**
     * @param nextLink The nextLink to set.
     */
    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }
	/**
	 * @return the temporal
	 */
	public String getTemporal() {
		return temporal;
	}
	/**
	 * @param temporal the temporal to set
	 */
	public void setTemporal(String temporal) {
		this.temporal = temporal;
	}
	
	public String getTemp() {
		return temp;
	}
	
	public void setTemp(String temp) {
		this.temp = temp;
	}
    
    
}
