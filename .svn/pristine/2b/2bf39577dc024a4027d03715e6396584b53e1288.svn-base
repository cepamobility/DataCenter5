/**
 * Created on 11-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import communication.administration.corporation.tables.DivOrgAttribute;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;

/**
 * @author jrodriguez
 */
public class DivOrg {

    private Integer id;
    private Corporation corporation;
    private String resourceKey;
    private Set itemLevel1Set;
    private Set levelLabelSet;
    
    public DivOrg() {
    }

    public DivOrg(DivOrgData data) {
        this.id = data.getId();
        this.resourceKey = data.getName();
        LabelItemData itemData1 = data.getDivOrgLevels();
        levelLabelSet = new HashSet();
        itemLevel1Set = new HashSet();
        if(itemData1 != null) {
            boolean labels = false;
            Iterator it1 = itemData1.getItems().iterator();
            switch(getMaxLevel(data)) {
                case 1:
                    levelLabelSet.add(new LevelLabel(itemData1.getId(),this,itemData1.getName(),new Integer(1),itemData1.getShowLevel()));
                    while(it1.hasNext()) {
                        ItemData item1 = (ItemData)it1.next();
                        itemLevel1Set.add(new DivOrgItemLevel1(item1.getId(),this,item1.getName(),null,item1.isActive()));
                    }
                    break;
                case 2:
                    while(it1.hasNext()) {
                        ItemData item1 = (ItemData)it1.next();
                        DivOrgItemLevel2 level1 = new DivOrgItemLevel2(item1.getId(),item1.getName(),null,item1.isActive());
                        LabelItemData itemData2 = item1.getSubItems();
                        if(!labels) {
                            levelLabelSet.add(new LevelLabel(itemData1.getId(),this,itemData1.getName(),new Integer(1),itemData1.getShowLevel()));
                            levelLabelSet.add(new LevelLabel(itemData2.getId(),this,itemData2.getName(),new Integer(2),itemData2.getShowLevel()));
                            labels = true;
                        }
                        Iterator it2 = itemData2.getItems().iterator();
                        while(it2.hasNext()) {
                            ItemData item2 = (ItemData)it2.next();
                            itemLevel1Set.add(new DivOrgItemLevel1(item2.getId(),this,item2.getName(),level1,item2.isActive()));
                        }    
                    }
                    break;
                case 3:
                    while(it1.hasNext()) {
                        ItemData item1 = (ItemData)it1.next();
                        DivOrgItemLevel3 level3 = new DivOrgItemLevel3(item1.getId(),item1.getName(),item1.isActive());
                        LabelItemData itemData2 = item1.getSubItems();
                        Iterator it2 = itemData2.getItems().iterator();
                        while(it2.hasNext()) {
                            ItemData item2 = (ItemData)it2.next();
                            LabelItemData itemData3 = item2.getSubItems();
                            if(!labels) {
                                levelLabelSet.add(new LevelLabel(itemData1.getId(),this,itemData1.getName(),new Integer(1),itemData1.getShowLevel()));
                                levelLabelSet.add(new LevelLabel(itemData2.getId(),this,itemData2.getName(),new Integer(2),itemData2.getShowLevel()));
                                levelLabelSet.add(new LevelLabel(itemData3.getId(),this,itemData3.getName(),new Integer(3),itemData3.getShowLevel()));
                                labels = true;
                            }
                            DivOrgItemLevel2 level2 = new DivOrgItemLevel2(item2.getId(),item2.getName(),level3,item2.isActive());
                            Iterator it3 = itemData3.getItems().iterator();
                            
                            while(it3.hasNext()) {
                                ItemData item3 = (ItemData)it3.next();
                                itemLevel1Set.add(new DivOrgItemLevel1(item3.getId(),this,item3.getName(),level2,item3.isActive()));
                            }
                        }    
                    }
                    break;
            }
        }else {
            LabelItemAttribute[] label = data.getLabels();
            for(int i = 0; i < label.length; i++) {
                levelLabelSet.add(new LevelLabel(label[i].getId(),this,label[i].getName(),Integer.valueOf(i+1),label[i].getShowLevel()));
            }
        }
    }
    
    private int getMaxLevel(DivOrgData data) {
        int level = 1;
        LabelItemData itemData1 = data.getDivOrgLevels();
        Iterator it1 = itemData1.getItems().iterator();
        while(it1.hasNext()) {
            ItemData item1 = (ItemData)it1.next();
            if(item1.getSubItems() != null) {
                if(level == 1) {
                    level = 2;
                }
                Iterator it2 = item1.getSubItems().getItems().iterator();
                while(it2.hasNext()) {
                    ItemData item2 = (ItemData)it2.next();
                    if(item2.getSubItems() != null) {
                        return 3;
                    }
                }
            }
        }
        return level;
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
    /**
     * @return Returns the resourceKey.
     */
    public String getResourceKey() {
        return resourceKey;
    }
    
    /**
     * @param resourceKey The resourceKey to set.
     */
    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }
    
    /**
     * @return Returns the items.
     */
    public Collection getItemLevel1Set() {
        return itemLevel1Set;
    }
    
    /**
     * @param items The items to set.
     */
    public void setItemLevel1Set(Set itemLevel1Set) {
        this.itemLevel1Set = itemLevel1Set;
    }
    
    /**
     * @return Returns the labels.
     */
    public Collection getLevelLabelSet() {
        return levelLabelSet;
    }
    
    /**
     * @param labels The labels to set.
     */
    public void setLevelLabelSet(Set levelLabelSet) {
        this.levelLabelSet = levelLabelSet;
    }

    /**
     * @return
     */
    public DivOrgData getData() {
        LabelItemAttribute[] data = new LabelItemAttribute[levelLabelSet.size()];
        Iterator labels = levelLabelSet.iterator();
        while(labels.hasNext()) {
            LevelLabel levelLabel = (LevelLabel) labels.next();
            data[levelLabel.getLevel().intValue()-1] = levelLabel.getAttributes();
        }
        
        Hashtable itemsAux = new Hashtable();
        Iterator items = itemLevel1Set.iterator();
        while(items.hasNext()) {
            DivOrgItemLevel1 level1 = (DivOrgItemLevel1)items.next();
            if(level1.getDivOrgItemLevel2() == null) {
                itemsAux.put(level1.getId(),new ItemAux(level1.getId(),level1.getResourceKey(),level1.isActive()));
            }else {
	            DivOrgItemLevel2 level2 = level1.getDivOrgItemLevel2();
	            if(level2.getDivOrgItemLevel3() == null) {
	                if(itemsAux.containsKey(level2.getId())) {
	                    ItemAux auxLevel2 = (ItemAux)itemsAux.get(level2.getId());
	                    auxLevel2.components.put(level1.getId(),new ItemAux(level1.getId(),level1.getResourceKey(),level1.isActive()));
	                }else {
	                    ItemAux auxLevel2 = new ItemAux(level2.getId(),level2.getResourceKey(),level2.isActive());
	                    auxLevel2.components.put(level1.getId(),new ItemAux(level1.getId(),level1.getResourceKey(),level1.isActive()));
	                    itemsAux.put(level2.getId(),auxLevel2);
	                }
	            }else {
		            DivOrgItemLevel3 level3 = level2.getDivOrgItemLevel3();
		            if(itemsAux.containsKey(level3.getId())) {
		                ItemAux auxLevel3 = (ItemAux)itemsAux.get(level3.getId());
		                if(auxLevel3.components.containsKey(level2.getId())) {
		                    ItemAux auxLevel2 = (ItemAux)auxLevel3.components.get(level2.getId());
		                    auxLevel2.components.put(level1.getId(),new ItemAux(level1.getId(),level1.getResourceKey(),level1.isActive()));
		                }else {
		                    ItemAux auxLevel2 = new ItemAux(level2.getId(),level2.getResourceKey(),level2.isActive());
		                    auxLevel2.components.put(level1.getId(),new ItemAux(level1.getId(),level1.getResourceKey(),level1.isActive()));
		                    auxLevel3.components.put(level2.getId(),auxLevel2);
		                }
		            }else {
		                ItemAux auxLevel3 = new ItemAux(level3.getId(),level3.getResourceKey(),level3.isActive());
		                ItemAux auxLevel2 = new ItemAux(level2.getId(),level2.getResourceKey(),level2.isActive());
		                auxLevel2.components.put(level1.getId(),new ItemAux(level1.getId(),level1.getResourceKey(),level1.isActive()));
		                auxLevel3.components.put(level2.getId(),auxLevel2);
		                itemsAux.put(level3.getId(),auxLevel3);
		            }
	            }
            }
        }
        DivOrgData divOrgData = new DivOrgData(id,resourceKey,getComponent(itemsAux,data,0));
        divOrgData.setLabels(data);
        return divOrgData;
    }
        
    public LabelItemData getComponent(Hashtable components,LabelItemAttribute[] labels,int level) {
        if(components.size() == 0) {
            return null;
        }
        Collection data = new LinkedList(); 
        Enumeration enum1 = components.elements();
        while(enum1.hasMoreElements()) {
            ItemAux child = (ItemAux)enum1.nextElement();
            data.add(new ItemData(child.id,child.name,child.active,getComponent(child.components,labels,level+1)));
        }
        return new LabelItemData(labels[level].getId(),labels[level].getName(),data,labels[level].getShowLevel());
    }

    class ItemAux {
        
        public Integer id;
        public String name;
        public boolean active;
        public Hashtable components;
        
        public ItemAux(Integer id,String name,boolean active) {
            this.id = id;
            this.name = name;
            this.active = active;
            components = new Hashtable();
        }
        
    }

    /**
     * @return
     */
    public DivOrgAttribute getAttributes() {
        return new DivOrgAttribute(id,resourceKey);
    }

    
}
