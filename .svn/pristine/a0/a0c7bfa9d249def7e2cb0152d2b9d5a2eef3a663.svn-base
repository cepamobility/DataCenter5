/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.administration.group.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class GroupLineData implements Comparable,Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private LabelItemData[] divOrgs = new LabelItemData[3];
    private GeoLabelData country;

    public GroupLineData() {
    }

    public GroupLineData(Integer id) {
        this.id = id;
    }

    public GroupLineData(Object[] data,UserSessionData userSessionData) {

        for(int i = 0; i < 3; i++) {
            if(data[(i+1)*6+4] != null) {
                Collection divs1 = new LinkedList();
                divs1.add(new ItemData((Integer)data[(i+1)*6+4],(String)data[(i+1)*6+5],true,null));
                LabelItemData ld1 = new LabelItemData(null,null,divs1,null);
                if(data[(i+1)*6+2] != null) {
                    Collection divs2 = new LinkedList();
                    divs2.add(new ItemData((Integer)data[(i+1)*6+2],(String)data[(i+1)*6+3],true,ld1));
                    LabelItemData ld2 = new LabelItemData(null,null,divs2,null);
                    if(data[(i+1)*6] != null) {
                        Collection divs3 = new LinkedList();
                        divs3.add(new ItemData((Integer)data[(i+1)*6],(String)data[(i+1)*6+1],true,ld2));
                        divOrgs[i] = new LabelItemData(null,null,divs3,null);
                    }else {
                        divOrgs[i] = ld2;
                    }
                }else {
                    divOrgs[i] = ld1;
                }
            }
        }

        Collection countries = new LinkedList();
        countries.add(new GeoItemData((Integer)data[4],(String)data[5],true,null));
        GeoLabelData ld1 = new GeoLabelData(null,null,2,countries);
        if(data[2] != null) {
            Collection geo1 = new LinkedList();
            geo1.add(new GeoItemData((Integer)data[2],(String)data[3],true,ld1));
            GeoLabelData ld2 = new GeoLabelData(null,null,1,geo1);
            if(data[0] != null) {
                Collection geo2 = new LinkedList();
                geo2.add(new GeoItemData((Integer)data[0],(String)data[1],true,ld2));
                country = new GeoLabelData(null,null,0,geo2);
            }else {
                country = ld2;
            }
        }else {
            country = ld1;
        }
        
    }

    /**
     * @return Returns the country.
     */
    public GeoLabelData getCountry() {
        return country;
    }

    /**
     * @return Returns the divOrgs.
     */
    public LabelItemData[] getDivOrgs() {
        return divOrgs;
    }

    /**
     * @param country The country to set.
     */
    public void setCountry(GeoLabelData country) {
        this.country = country;
    }

    /**
     * @param divOrgs The divOrgs to set.
     */
    public void setDivOrgs(LabelItemData[] divOrgs) {
        this.divOrgs = divOrgs;
    }

    public void setLabelItemData(int i, LabelItemData data) {
        divOrgs[i] = data;
    }

    
    /**
     * @return Returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel(int index) {
        String label = "";
        LabelItemData lid = divOrgs[index];
        if(lid != null && lid.getItems() != null) {
            ItemData item1 = (ItemData)lid.getItemIterator().next();
            label += item1.getName();
            lid = item1.getSubItems();
            if(lid != null && lid.getItems() != null) {
                ItemData item2 = (ItemData)lid.getItemIterator().next();
                label += "/"+item2.getName();
                lid = item2.getSubItems();
                if(lid != null && lid.getItems() != null) {
                    label += "/"+((ItemData)lid.getItemIterator().next()).getName();
                }
            }
        }
        return label;
    }
    
    public String getGeoLabel(Text messages) {
        String label = "";
        if(country != null && country.getItems() != null) {
            GeoItemData item1 = (GeoItemData)country.getItemIterator().next();
            GeoLabelData ld = item1.getSubItems();
            if(ld != null && ld.getItems() != null) {
                GeoItemData item2 = (GeoItemData)ld.getItemIterator().next();
                ld = item2.getSubItems();
                if(ld != null && ld.getItems() != null) {
                    GeoItemData item3 = (GeoItemData)ld.getItemIterator().next();
                    label = messages.getText(item3.getName());
                }else {
                    label = messages.getText(item2.getName());
                }
            }else {
                label = messages.getText(item1.getName());
            }
        }
        return label;
    }

    public String[] getDivOrgLabels() {
        String[] labels = new String[9];
        int j = 0;
        for(int i = 0; i < divOrgs.length; i++) {
            if(divOrgs[i] != null) {
                Iterator it = divOrgs[i].getItemIterator();
                if(it.hasNext()) {
                    ItemData item1 = (ItemData) it.next();
                    labels[3*j] = item1.getName();
                    if(item1.getSubItems() != null) {
                        ItemData item2 = (ItemData) item1.getSubItems().getItemIterator().next();
                        labels[3*j+1] = item2.getName();
                        if(item2.getSubItems() != null) {
                            labels[3*j+2] = ((ItemData) item2.getSubItems().getItemIterator().next()).getName();
                        }
                    }
                    j++;
                }                
            }
        }
        return labels;
    }

    public String getCountryName() {
        if(country != null && country.getItems() != null) {
            GeoItemData item1 = (GeoItemData)country.getItemIterator().next();
            GeoLabelData ld = item1.getSubItems();
            if(ld != null && ld.getItems() != null) {
                GeoItemData item2 = (GeoItemData)ld.getItemIterator().next();
                ld = item2.getSubItems();
                if(ld != null && ld.getItems() != null) {
                    GeoItemData item3 = (GeoItemData)ld.getItemIterator().next();
                    return item3.getName();
                }else {
                    return item2.getName();
                }
            }else {
                return item1.getName();
            }
        }
        return null;
    }

    public Integer getCountryId() {
        if(country != null && country.getItems() != null) {
            GeoItemData item1 = (GeoItemData)country.getItemIterator().next();
            GeoLabelData ld = item1.getSubItems();
            if(ld != null && ld.getItems() != null) {
                GeoItemData item2 = (GeoItemData)ld.getItemIterator().next();
                ld = item2.getSubItems();
                if(ld != null && ld.getItems() != null) {
                    GeoItemData item3 = (GeoItemData)ld.getItemIterator().next();
                    return item3.getId();
                }else {
                    return item2.getId();
                }
            }else {
                return item1.getId();
            }
        }
        return null;
    }

    public int compareTo(Object obj) {
        GroupLineData division = (GroupLineData)obj;
        String[] labels1 = getDivOrgLabels();
        String[] labels2 = division.getDivOrgLabels();
        if(labels1[0] != null && labels2[0] != null && !labels1[0].equals(labels2[0]) ) {
            return labels1[0].compareTo(labels2[0]);
        }else {
            if(labels1[4] != null && labels2[4] != null && !labels1[4].equals(labels2[4]) ) {
                return labels1[4].compareTo(labels2[4]);
            }else {
                if(labels1[5] != null && labels2[5] != null && !labels1[5].equals(labels2[5]) ) {
                    return labels1[5].compareTo(labels2[5]);
                }
            }
        }
        return 0;
    }

    public String validate() {
        return null;
    }

}
