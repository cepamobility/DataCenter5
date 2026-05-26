/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.fleet;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;

import communication.fleet.tables.FleetData;



/**
 * @author jrodriguez
 */
public class FleetDataForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String COLECTIVE = "COLECTIVE"; 
    public static final String INDIVIDUAL = "INDIVIDUAL"; 
    
    private String id;
	private String name;
	private String description;
	private String period;
    private String type;
    private String comment;

    public FleetDataForm() {
    }

    public FleetDataForm(FleetData fleet) {
        id = String.valueOf(fleet.getId());
        name = fleet.getName();
        description = fleet.getDescription();
        period = String.valueOf(fleet.getPeriod());
        type = String.valueOf(fleet.getType());
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
    public String getId() {
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
    public String getPeriod() {
        return period;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        // Trunco la descripción hasta 255 caracteres
    	// Cambiar esto cuando se valide por web.
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
    public void setId(String id) {
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
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    public String validate(String type) {
        if(Util.empty(name)) {
            return "fleet.error.emptyname";
        }
        if(Util.empty(description)) {
            return "fleet.error.emptydescription";
        }
        if(Util.empty(period)) {
            return "fleet.error.emptyperiod";
        }
        return null;
    }

    public String getComment() {
        return comment;
    }

    /**
     * @param comment The comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
