/**
 * Created on 14-nov-2005
 * CEPA
 * DataCenter 5
 */
package communication.administration.corporation.tables;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author jrodriguez
 */
public class DivOrgData extends DivOrgAttribute implements Serializable{

    private static final long serialVersionUID = 1L;

	public static final String CEPA_LOCATION_DEFAULT = "364322";

    private LabelItemData divOrgLevels;
    LabelItemAttribute[] st = null;

    public DivOrgData() {
        super();
    }

    /**
     * @param id
     * @param name
     */
    public DivOrgData(Integer id, String name, LabelItemData divOrgLevels) {
        super(id, name);
        this.divOrgLevels = divOrgLevels;
    }

    public DivOrgData(String name) {
        super(null, name);
    }

    public DivOrgData(String name, int levelCount) {
        super(null, name);
        st = new LabelItemAttribute[levelCount];
    }

    /**
     * @return Returns the divOrgLevels.
     */
    public LabelItemData getDivOrgLevels() {
        return divOrgLevels;
        
    }
    
    public LabelItemAttribute[] getLabels() {
        return st;
    }

    /**
     * @param data
     */
    public void setLabels(LabelItemAttribute[] data) {
        st = data;
    }

    public LinkedList[] getItems(int level,String idLevel1,String idLevel2) {
        LinkedList activeData = new LinkedList();
        LinkedList inactiveData = new LinkedList();
        if(divOrgLevels != null) {
            Iterator it = divOrgLevels.getItemIterator();
            while(it.hasNext()){
                ItemData item = (ItemData)it.next();
                if(level == 1) {
                    if(item.isActive()) {
                        activeData.add(item);
                    }else {
                        inactiveData.add(item);
                    }                                          
                }else {
                    Iterator it2 = item.getSubItems().getItemIterator();
                    while(it2.hasNext()){
                        ItemData item2 = (ItemData)it2.next();
                        if(String.valueOf(item.getId()).equals(idLevel1)) {
                            if(level == 2) {
                                if(item2.isActive()) {
                                    activeData.add(item2);
                                }else {
                                    inactiveData.add(item2);
                                }
                            }else {
                                if(String.valueOf(item2.getId()).equals(idLevel2)) {
	                                Iterator it3 = item2.getSubItems().getItemIterator();
	                                while(it3.hasNext()){
	                                    ItemData item3 = (ItemData)it3.next();
	                                    if(item3.isActive()) {
	                                        activeData.add(item3);
	                                    }else {
	                                        inactiveData.add(item3);
	                                    }
	                                }
                                }    
                            }
                        }
                    }
                }
            }
        }
        LinkedList[] total = {activeData,inactiveData};
        return total;
    }

    /**
     * @param data
     */
    public void setDivOrgLevels(LabelItemData data) {
        divOrgLevels = data;
    }

    public String getLabel() {
        String label = "";
        Iterator it = divOrgLevels.getItemIterator();
        while(it.hasNext()){
            ItemData item = (ItemData)it.next();
            label = item.getName();
            if(item.getSubItems() != null) {
                Iterator it2 = item.getSubItems().getItemIterator();
                while(it2.hasNext()){
                    ItemData item2 = (ItemData)it2.next();
                    label += "/"+item2.getName();
                    if(item2.getSubItems() != null) {
                        Iterator it3 = item2.getSubItems().getItemIterator();
                        while(it3.hasNext()){
                            label += "/"+((ItemData)it3.next()).getName();
                        }
                    }
                }
            }
        }
        return label;
    }
    
    /**
     * Devuelve las etiquetas de las Divisiones Organizacionales
     * separadas por | (pipes).
     * 
     */
    public String getDivOrgLabelSeparatedByPipes() {
        String label = "";
        Iterator it = divOrgLevels.getItemIterator();
        while(it.hasNext()){
            ItemData item = (ItemData)it.next();
            label = item.getName();
            if(item.getSubItems() != null) {
                Iterator it2 = item.getSubItems().getItemIterator();
                while(it2.hasNext()){
                    ItemData item2 = (ItemData)it2.next();
                    label += "|"+item2.getName();
                    if(item2.getSubItems() != null) {
                        Iterator it3 = item2.getSubItems().getItemIterator();
                        while(it3.hasNext()){
                            label += "|"+((ItemData)it3.next()).getName();
                        }
                    }
                }
            }
        }
        return label;
    }
    
    /*
     * Devuelve los Ids de las Divisiones Organizacionales
     */
    public Integer[] getDivOrgIds() {
        Integer[] labels = new Integer[9];
        int j = 0;       
        Iterator it = divOrgLevels.getItemIterator();
        while(it.hasNext()){
            ItemData item = (ItemData)it.next();
            labels[j]=item.getId();
            j++;
            if(item.getSubItems() != null) {
                Iterator it2 = item.getSubItems().getItemIterator();
                while(it2.hasNext()){
                    ItemData item2 = (ItemData)it2.next();
                    labels[j] = item2.getId();
                    j++;
                    if(item2.getSubItems() != null) {
                        Iterator it3 = item2.getSubItems().getItemIterator();
                        while(it3.hasNext()){
                            labels[j] = ((ItemData)it3.next()).getId();
                            j++;
                        }
                    }
                }
            }
        }
        return labels;
    }

    public DivOrgAttribute getDivOrgAttribute() {
        return new DivOrgAttribute(id,name);
    }
}
