/**
 * Created on 28-NOV-2006
 * CEPA
 * DataCenter 5
 */
package communication.administration.group.tables;

import java.io.Serializable;

import persistence.util.Util;

public class GroupMainAttributes implements Comparable,Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer MONTHLY = new Integer(1);
    public static final Integer BY_QUARTER = new Integer(2);

    public static final Integer INDIVIDUAL = new Integer(1);
    public static final Integer COMPOSED = new Integer(2);

    protected Integer id;
    protected String name;
    protected String description;
    protected boolean active;
    protected Integer period;
    protected Integer type;
    
    public GroupMainAttributes() {
    }

    
    /**
     * @param id
     * @param name
     * @param description
     * @param active
     * @param period
     */
    public GroupMainAttributes(Integer id, String name, String description, boolean active, Integer period, Integer type) {
        this.id = id;
        this.name = name;
       	this.description = description;
        this.active = active;
        this.period = period;
        this.type = type;
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
	   this.description = description;
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

    public String validate() {
        if(Util.empty(name)) {
            return "group.error.emptyname";
        }
        if(Util.empty(description)) {
            return "group.error.emptydescription";
        }
        if(period == null) {
            return "group.error.emptyperiod";
        }
        return null;
    }


    public int compareTo(Object o) {
        return name.compareTo(((GroupMainAttributes)o).name);
    }

}
