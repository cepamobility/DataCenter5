/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.tables;

import java.io.Serializable;
import java.util.Date;

import persistence.administration.corporation.tables.Corporation;

public class FleetMainAttributes implements Serializable {

    public static final Integer MONTHLY = new Integer(1);
    public static final Integer BY_QUARTER = new Integer(2);

    protected Integer id;
    protected String name;
    protected String description;
    protected boolean active;
    protected Corporation corporation;
    protected Integer period;
    protected Integer type;
    protected Date exclusionDate;
    protected Integer blockMonth;
    protected Integer blockYear;
    
    public FleetMainAttributes() {
    }

    
    /**
     * @param id
     * @param name
     * @param description
     * @param active
     * @param period
     */
    public FleetMainAttributes(Integer id, String name, String description,
    		boolean active, Integer period, Integer type, Date exclusionDate,    		
    		Corporation corporation, Integer blockMonth, Integer blockYear) 
    {
        this.id = id;
        this.name = name;
        this.corporation = corporation;
        if (description.length() > 255)
        {
        	this.description = description.substring(0,254);
        }
        else
        {
            this.description = description;
        }
        this.active = active;
        this.period = period;
        this.type = type;
        this.exclusionDate = exclusionDate;
        this.blockMonth = blockMonth;
        this.blockYear = blockYear;
    }

    public FleetMainAttributes(Integer id, String name, String description,
    		boolean active, Integer period, Integer type, Date exclusionDate, Integer blockMonth, Integer blockYear)
    {
        this.id = id;
        this.name = name;
       
        if (description.length() > 255)
        {
        	this.description = description.substring(0,254);
        }
        else
        {
            this.description = description;
        }
        this.active = active;
        this.period = period;
        this.type = type;
        this.exclusionDate = exclusionDate;
        this.blockMonth = blockMonth;
        this.blockYear = blockYear;
    }


    /**
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the period.
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
   	   if (description.length() > 255)
       {
   		   this.description = description.substring(0,254);
       }
       else
       {
    	   this.description = description;
       }
    }

    /**
     * @param id The id to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param period The period to set.
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }


    /**
     * @return Returns the type.
     */
    public Integer getType() {
        return type;
    }


    /**
     * @param type The type to set.
     */
    public void setType(Integer type) {
        this.type = type;
    }


	/**
	 * @return Returns the exclusionDate.
	 */
	public Date getExclusionDate() {
		return exclusionDate;
	}


	/**
	 * @param exclusionDate The exclusionDate to set.
	 */
	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}


	/**
	 * @return Returns the corporation.
	 */
	public Corporation getCorporation() {
		return corporation;
	}


	/**
	 * @param corporation The corporation to set.
	 */
	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}


	/**
	 * @return Returns the blockMonth.
	 */
	public Integer getBlockMonth() {
		return blockMonth;
	}


	/**
	 * @param blockMonth The blockMonth to set.
	 */
	public void setBlockMonth(Integer blockMonth) {
		this.blockMonth = blockMonth;
	}


	/**
	 * @return Returns the blockYear.
	 */
	public Integer getBlockYear() {
		return blockYear;
	}


	/**
	 * @param blockYear The blockYear to set.
	 */
	public void setBlockYear(Integer blockYear) {
		this.blockYear = blockYear;
	}


	
    
    
}
