/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;

import communication.administration.user.UserSessionData;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetMainAttributes;

public class AbstractFleet {

    protected Integer id;    
    protected String name;
    protected String description;
    protected boolean active;
    protected Integer period;  
    protected Corporation corporation;
    protected Set divisionSet;    
    protected Date exclusionDate;
    /* To block the data entry before a period (month and year) */
    protected Integer blockMonth;
    protected Integer blockYear;

    
    public boolean equals(Object o)
    {
    	if (AbstractFleet.class.isInstance(o))
    	{
    		AbstractFleet af = (AbstractFleet)o;
    		if (id.equals(af))
    		{
    			return true;
    		}
    		else
    			return false;
    	}else
    		return false;
    }
    public AbstractFleet() {
    }

    
    /**
     * @param id
     * @param name
     * @param description
     * @param active
     * @param period
     * @param divisionSet
     */
    public AbstractFleet(Integer id, String name, String description, boolean active, 
    		Integer period, Collection divs, Session session, UserSessionData userSessionData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.period = period;
        corporation  = (Corporation)session.load(Corporation.class,userSessionData.getFilter().getCorporationId());
        this.exclusionDate = null;
        this.blockMonth = null;
        this.blockYear = null;
        this.divisionSet = null;
    }

    /**
     * @param id
     * @param name
     * @param description
     * @param active
     * @param period
     * @param divisionSet
     */
    public AbstractFleet(Integer id, String name, String description, 
    		boolean active, Integer period, Set divs, Session session, 
    		UserSessionData userSessionData, Date exclusionDate, Integer blockMonth, Integer blockYear) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.period = period;
        this.divisionSet = divs;
        corporation  = (Corporation)session.load(Corporation.class,userSessionData.getFilter().getCorporationId());
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
     * @return Returns the divisionSet.
     */
    public Set getDivisionSet() {
        return divisionSet;
    }
    
    /*
     * Agrega una division en el Set de divisiones de la flota.
     */
    public void addFleetDivision(FleetDivision fleetDivision)
    {
    	if (!divisionSet.contains(fleetDivision))
    	{
    		divisionSet.add(fleetDivision);
    	}
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
        this.description = description;
    }

    /**
     * @param divisionSet The divisionSet to set.
     */
    public void setDivisionSet(Set divisionSet) {
        this.divisionSet = divisionSet;
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

    public FleetMainAttributes getMainAttributes() {
        return new FleetMainAttributes(id,name,description,active,period,null,
        		exclusionDate,this.corporation,this.blockMonth,this.blockYear);
    }


    public FleetData getFleetData() {
    	FleetDivision fleetDivision;
        Collection divisions = new LinkedList();
        if (divisionSet != null && !divisionSet.isEmpty())
        {
        	Iterator it = divisionSet.iterator();
        	while(it.hasNext()) 
        	{
        		fleetDivision = (FleetDivision)it.next();        	
        		divisions.add(fleetDivision.getData());
        	}
        }
        return new FleetData(id,name,description,active,period,null,
        		divisions,exclusionDate,blockMonth,blockYear);
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
	
	public void updateData(FleetData data, Session session, UserSessionData userRequest)
	{
		this.id = data.getId();
	    this.name = data.getName();
	    this.description = data.getDescription();
	    this.active = data.isActive();
        this.period = data.getPeriod();
        corporation  = (Corporation)session.load(Corporation.class,userRequest.getFilter().getCorporationId());     
        this.exclusionDate = data.getExclusionDate();
        this.blockMonth = data.getBlockMonth();
        this.blockYear = data.getBlockYear();
	}

	/*
	 * Devuelve una coleccion con los Ids de las Divisiones
	 * de la Flota.
	 */
    public Collection getDivisionsIds()
    {
    	Collection ids = new ArrayList();
    	FleetDivision fleetDivision;
    	Iterator it = this.divisionSet.iterator();
    	while (it.hasNext())
    	{
    		fleetDivision = (FleetDivision)it.next();
    		ids.add(fleetDivision.getId());
    	}
    	return ids;
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