package persistence.log.fleet;

import java.util.Date;
import java.util.Set;

public class FleetLogMainAttributes {

    public static final Integer MONTHLY = new Integer(1);
    public static final Integer BY_QUARTER = new Integer(2);

    protected FleetLogPK pk;
    protected String name;
    protected String description;
    protected boolean active;
    protected Integer period;
    protected Integer type;
    protected Date exclusionDate;
    protected Set vehicleIdSet; // IndividualFleet
    protected int fleetType;
    
    public FleetLogMainAttributes() {
    }

    
    /**
     * @param id
     * @param name
     * @param description
     * @param active
     * @param period
     */
    public FleetLogMainAttributes(FleetLogPK pk, String name, String description,
    		boolean active, Integer period, Integer type, Date exclusionDate, 
    		Set vehicleSet, int fleetType) {
        this.pk = pk;
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
        //this.vehicleIdSet = vehicleIdSet;
        this.fleetType = fleetType;
    }


    /**
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /*
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Returns the id.
     */
    public FleetLogPK getPk() {
        return this.pk;
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
    public void setPk(FleetLogPK pk) {
        this.pk = pk;
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
	 * @return Returns the fleetType.
	 */
	public int getFleetType() {
		return fleetType;
	}


	/**
	 * @param fleetType The fleetType to set.
	 */
	public void setFleetType(int fleetType) {
		this.fleetType = fleetType;
	}


	/**
	 * @return Returns the vehicleSet.
	 */
	public Set getVehicleIdSet() {
		return vehicleIdSet;
	}


	/**
	 * @param vehicleSet The vehicleSet to set.
	 */
	public void setVehicleIdSet(Set vehicleIdSet) {
		this.vehicleIdSet = vehicleIdSet;
	}
    
    
}
