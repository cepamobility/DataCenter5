/**
 * Created on 28-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import org.apache.struts.action.ActionForm;

/**
 * @author jrodriguez
 */
public class CorporationCountryForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String level;
    private String level1;
    private String label1;
    private String level2;
    private String label2;
    private String level3;
    private String label3;
    
    public CorporationCountryForm() {
    }
    
    /**
     * @param id
     * @param level1
     * @param level2
     * @param level3
     */
    public CorporationCountryForm(String level, String level1, String label1, String level2, String label2, String level3) {
        super();
        this.level = level;
        this.level1 = level1;
        this.label1 = label1;
        this.level2 = level2;
        this.label2 = label2;
        this.level3 = level3;
    }

    /**
     * @return Returns the level.
     */
    public String getLevel() {
        return level;
    }
    
    /**
     * @param level The level to set.
     */
    public void setLevel(String level) {
        this.level = level;
    }
    
    /**
     * @return Returns the level1.
     */
    public String getLevel1() {
        return level1;
    }
    
    /**
     * @param level1 The level1 to set.
     */
    public void setLevel1(String level1) {
        this.level1 = level1;
    }
    
    /**
     * @return Returns the level2.
     */
    public String getLevel2() {
        return level2;
    }
    
    /**
     * @param level2 The level2 to set.
     */
    public void setLevel2(String level2) {
        this.level2 = level2;
    }
    
    /**
     * @return Returns the label1.
     */
    public String getLabel1() {
        return label1;
    }
    
    /**
     * @param label1 The label1 to set.
     */
    public void setLabel1(String label1) {
        this.label1 = label1;
    }
    
    /**
     * @return Returns the label2.
     */
    public String getLabel2() {
        return label2;
    }
    
    /**
     * @param label2 The label2 to set.
     */
    public void setLabel2(String label2) {
        this.label2 = label2;
    }
    
    /**
     * @return Returns the label3.
     */
    public String getLabel3() {
        return label3;
    }
    
    /**
     * @param label3 The label3 to set.
     */
    public void setLabel3(String label3) {
        this.label3 = label3;
    }
    
    /**
     * @return Returns the level3.
     */
    public String getLevel3() {
        return level3;
    }
    
    /**
     * @param label3 The label3 to set.
     */
    public void setLevel3(String level3) {
        this.level3 = level3;
    }
}
