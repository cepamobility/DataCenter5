/**
 * 
 */
package persistence.log.fleet;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.fleet.tables.AbstractFleet;
import persistence.fleet.tables.ColectiveFleet;
import persistence.fleet.tables.FleetDivision;

import communication.administration.user.UserSessionData;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetObservationData;
import communication.util.LogActions;

public class AbstractFleetLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected FleetLogPK pk; 
    private Integer action;   
    private String comment; 
     
    protected String name;
    protected String description;
    protected boolean active;
    protected Integer period;
    protected Integer corporation;
    protected Set divisionSet;    
    protected Date exclusionDate;
    protected Integer blockMonth;
    protected Integer blockYear;
    
    private Set vehicleIdSet; 
    /*
     * Este campo vehicleIdSet es para el log de IndividualFleet
     * solo guardamos el id del vehiculo.     	
     */
    
    /*  Para fleetType usar estas Constantes que estan definidas en FleetData.java
     * 
     *  public static final int INDIVIDUAL = 1;
     *  public static final int COLECTIVE = 2;
     */
    private int fleetType;    
    
    public AbstractFleetLog(AbstractFleet fleet,String user,Date date,
    		int action,String comment, Set vehicleIdSet, Set divisionsLog)
    {
    	pk = new FleetLogPK(fleet.getId(),user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;	
        this.name = fleet.getName();
        this.description = fleet.getDescription();
        this.active = fleet.isActive();
        this.period = fleet.getPeriod();
        this.corporation = fleet.getCorporation().getId();
        this.exclusionDate = fleet.getExclusionDate();
        this.blockMonth = fleet.getBlockMonth();
        this.blockYear = fleet.getBlockYear();
        this.divisionSet = divisionsLog; //fleet.getDivisionSet();
        if (ColectiveFleet.class.isInstance(fleet))
        {
        	this.fleetType = FleetData.COLECTIVE;
        	this.vehicleIdSet = null;        	
        }else
        {
        	this.fleetType = FleetData.INDIVIDUAL;
        	if (action == LogActions.ADDVEHICLE || action == LogActions.DELETEVEHICLE)
        		this.vehicleIdSet = vehicleIdSet;
        	else
        		this.vehicleIdSet = null;        	
        }		     
    }    
    
    public AbstractFleetLog() 
    {		
	}


	public AbstractFleetLog(Integer id, String name, String description,
    		boolean active, Integer period, Collection divs, Session session, 
    		UserSessionData userSessionData, String user, Date date, 
    		int action, String comment, Set vehicleIdSet, int fleetType) {
    	pk = new FleetLogPK(id,user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;	
        this.name = name;
        this.description = description;
        this.active = active;
        this.period = period;
        corporation  = userSessionData.getFilter().getCorporationId();
        this.exclusionDate = null;
        this.blockMonth = null;
        this.blockYear = null;
        this.divisionSet = null;
        this.vehicleIdSet = vehicleIdSet;
        this.fleetType = fleetType;
    }

	/**
	 * @return Returns the action.
	 */
	public Integer getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(Integer action) {
		this.action = action;
	}

	/**
	 * @return Returns the active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active The active to set.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return Returns the corporation.
	 */
	public Integer getCorporation() {
		return corporation;
	}

	/**
	 * @param corporation The corporation to set.
	 */
	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the divisionSet.
	 */
	public Set getDivisionSet() {
		return divisionSet;
	}

	/**
	 * @param divisionSet The divisionSet to set.
	 */
	public void setDivisionSet(Set divisionSet) {
		this.divisionSet = divisionSet;
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the period.
	 */
	public Integer getPeriod() {
		return period;
	}

	/**
	 * @param period The period to set.
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}

	/**
	 * @return Returns the pk.
	 */
	public FleetLogPK getPk() {
		return pk;
	}

	/**
	 * @param pk The pk to set.
	 */
	public void setPk(FleetLogPK pk) {
		this.pk = pk;
	}
    	
	 public FleetLogMainAttributes getMainAttributes() {
	        return new FleetLogMainAttributes(pk,name,description,active,period,null,
	        		exclusionDate,vehicleIdSet,fleetType);
	 }

	 public FleetLogData getFleetLogData() {
	        Collection divisions = new LinkedList();
	        Iterator it = divisionSet.iterator();
	        while(it.hasNext()) {
	            divisions.add(((FleetDivision)it.next()).getData());
	        }
	        return new FleetLogData(pk,name,description,active,period,null,
	        		divisions,exclusionDate,vehicleIdSet,fleetType);
	    }

	/**
	 * @return Returns the vehicleIdSet.
	 */
	public Set getVehicleIdSet() {
		return vehicleIdSet;
	}

	/**
	 * @param vehicleIdSet The vehicleIdSet to set.
	 */
	public void setVehicleIdSet(Set vehicleIdSet) {
		this.vehicleIdSet = vehicleIdSet;
	}
	
    public FleetObservationData getFleetObservations() {
        return new FleetObservationData(pk.getFleet(),pk.getDate(),pk.getUserId(),action,comment);
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
