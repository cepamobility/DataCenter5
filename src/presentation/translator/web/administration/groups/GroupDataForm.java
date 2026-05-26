/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.administration.groups;

import org.apache.struts.action.ActionForm;

import presentation.translator.web.util.Util;

import communication.administration.group.tables.GroupMainAttributes;

/**
 * @author jrodriguez
 */
public class GroupDataForm extends ActionForm {

    private String id;
	private String name;
	private String description;
	private String period;
    private String type;

    public GroupDataForm() {
        type = String.valueOf(GroupMainAttributes.INDIVIDUAL);
    }

    public GroupDataForm(GroupMainAttributes attributes) {
        id = String.valueOf(attributes.getId());
        name = attributes.getName();
        description = attributes.getDescription();
        period = String.valueOf(attributes.getPeriod());
        type = String.valueOf(attributes.getType());
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
        this.description = description;
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

    public String validate() {
        if(Util.empty(name)) {
            return "group.error.emptyname";
        }
        if(Util.empty(description)) {
            return "group.error.emptydescription";
        }
        if(Util.empty(period)) {
            return "group.error.emptyperiod";
        }
        return null;
    }

}
