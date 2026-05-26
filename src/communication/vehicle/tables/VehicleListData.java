/**
 * 
 */
package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;

/**
 * @author gbenaderet
 *
 */
public class VehicleListData extends VehicleMainAttributes implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected LabelItemData[] divOrgsLabel = new LabelItemData[3];
    
    
    
    /**
	 * @param id
	 * @param matricula
	 * @param country
	 * @param year
	 * @param exclusionDate
	 * @param divOrgsLabel
	 */
	public VehicleListData(Integer id, String matricula, GeoLabelData country, 
			Integer year, Date exclusionDate, LabelItemData[] divOrgsLabel) {
		super(id, matricula, country, year, exclusionDate);
		this.divOrgsLabel = divOrgsLabel;
	}
	
	/**
	 * @param id
	 * @param matricula
	 * @param country
	 * @param year
	 * @param exclusionDate
	 */
	public VehicleListData(Integer id, String matricula, GeoLabelData country,
			Integer year, Date exclusionDate) {
		super(id, matricula, country, year, exclusionDate);
	}

	/**
     * @return Returns the divOrgs.
     */
    public LabelItemData[] getDivOrgsLabel() {
        return divOrgsLabel;
    }
    
    /**
     * @param divOrgs The divOrgs to set.
     */
    public void setDivOrgsLabel(LabelItemData[] divOrgsLabel) {
        this.divOrgsLabel = divOrgsLabel;
    }

    public void setLabelItemData(int i, LabelItemData data) {
        divOrgsLabel[i] = data;
    }
    
    /*
     * Retuns Div Orgs Labels separated by pipes ("|").
     */
    public String getDivOrgsLabelSeparatedByPipes(int index) {
        String label = "";
        LabelItemData lid = divOrgsLabel[index];
        if(lid != null && lid.getItems() != null) {
            ItemData item1 = (ItemData)lid.getItemIterator().next();
            label += item1.getName();
            lid = item1.getSubItems();
            if(lid != null && lid.getItems() != null) {
                ItemData item2 = (ItemData)lid.getItemIterator().next();
                label += "|"+item2.getName();
                lid = item2.getSubItems();
                if(lid != null && lid.getItems() != null) {
                    label += "|"+((ItemData)lid.getItemIterator().next()).getName();
                }
            }
        }
        return label;
    }
    
    public String[] getDivOrgLabels() {
        String[] labels = new String[9];
        int j = 0;
        for(int i = 0; i < divOrgsLabel.length; i++) {
            if(divOrgsLabel[i] != null) {
                Iterator it = divOrgsLabel[i].getItemIterator();
                if(it.hasNext()) {
                    ItemData item1 = (ItemData) it.next();
                    labels[3*j] = item1.getName();
                    //System.out.println("labels["+3*j+"] = "+labels[3*j]);
                    if(item1.getSubItems() != null) {
                        ItemData item2 = (ItemData) item1.getSubItems().getItemIterator().next();
                        labels[3*j+1] = item2.getName();
                        //System.out.println("labels["+3*j+1+"] = "+labels[3*j+1]);
                        if(item2.getSubItems() != null) {
                            labels[3*j+2] = ((ItemData) item2.getSubItems().getItemIterator().next()).getName();
                            //System.out.println("labels["+3*j+2+"] = "+labels[3*j]);
                        }
                    }
                    j++;
                }                
            }
        }
        return labels;
    }
    
	
}
