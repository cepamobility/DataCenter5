/**
 * Created on 21-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cenapra.tables;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.CEPAActivityScheduleState;
import persistence.datatype.tables.CEPAActivityType;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;
import communication.cenapra.tables.ActivityData;
import communication.datatype.tables.ActivityStateDataType;
import communication.datatype.tables.GeneralDTData;

public class CENAPRAActivity {

     private Integer id;
     private Date date;    
     
     private CorporationCountry corpCountry;
     private DivOrgItemLevel1 divOrg1;
     private DivOrgItemLevel1 divOrg2;
     private DivOrgItemLevel1 divOrg3;

     private CEPAActivityType type;
     private CEPAActivityScheduleState state;

     private String instructor;
     private String city;
     private String observations;

     public CENAPRAActivity() {
        super();
    }

    public CENAPRAActivity(ActivityData data, Session session, Integer corporationId) {
        id = data.getId();
        setData(data,session,corporationId);
    }
    
    public void setData(ActivityData data, Session session, Integer corporationId) {
        date = data.getDate();
        
        GeoItemData itemData1 = (GeoItemData)data.getCountry().getItemIterator().next();
        GeoItemData itemData2 = null; 
        while(itemData1.getSubItems() != null) {
            itemData2 = itemData1;
            itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
        }
        
        Iterator it = data.getDivOrgsIterator();
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        
        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),corporationId));
        
        type = (CEPAActivityType)session.load(CEPAActivityType.class,data.getType().getResourceBundleId());
        state = (CEPAActivityScheduleState)session.load(CEPAActivityScheduleState.class,data.getState().getResourceBundleId());
        
        instructor = data.getInstructor();
        city = data.getCity();
        observations = data.getObservations();
    }

    public String getCity() {
        return city;
    }

    public CorporationCountry getCorpCountry() {
        return corpCountry;
    }

    public Date getDate() {
        return date;
    }

    public DivOrgItemLevel1 getDivOrg1() {
        return divOrg1;
    }

    public DivOrgItemLevel1 getDivOrg2() {
        return divOrg2;
    }

    public DivOrgItemLevel1 getDivOrg3() {
        return divOrg3;
    }

    public Integer getId() {
        return id;
    }

    public String getObservations() {
        return observations;
    }

    public CEPAActivityScheduleState getState() {
        return state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCorpCountry(CorporationCountry corpCountry) {
        this.corpCountry = corpCountry;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setState(CEPAActivityScheduleState state) {
        this.state = state;
    }

    public ActivityData getData() {
        CorporationData corporationData = corpCountry.getPk().getCorporation().getData();

        GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
        GeoItemData countryData = (GeoItemData)corpCountry.getData();
        Collection countries = new LinkedList();
        countries.add(countryData);
        GeoLabelData labelData = null; 
        GeoLevel1 geoLevel1 = (corpCountry.getGeoLevel1Set().size() > 0) ? (GeoLevel1)corpCountry.getGeoLevel1Set().iterator().next() : null;
        if(geoLevel1 != null) {
            GeoItemData level1 = (GeoItemData)geoLevel1.getData();
            Collection level1s = new LinkedList();
            level1s.add(level1);
            if(geoLevel1.getGeoLevel2() == null) {
                level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
            }else {
                
                level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
                
                GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
                level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

                Collection level2s = new LinkedList();
                level2s.add(level2);
                
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
            }
        }else {
            labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
        }
        
        Collection divOrgs = new LinkedList();
        if(divOrg1 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg1,corporationData);
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg2 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg2,corporationData);
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg3 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg3,corporationData);
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }

        return new ActivityData(id,date,labelData,divOrgs, (GeneralDTData)type.getData(),(ActivityStateDataType) state.getData(),instructor,city,observations);
    }

    private DivOrgData getDivOrgData(DivOrgItemLevel1 level1,CorporationData corporationData) {
        DivOrgData divOrgData = corporationData.getDivOrgData(level1.getDivOrg().getId().intValue());
        LabelItemAttribute[] labelAttrs = divOrgData.getLabels();
        ItemData itemData = new ItemData(level1.getAttributes());
        Collection items1 = new LinkedList();
        items1.add(itemData);
        LabelItemData labelData = null; 
        if(level1.getDivOrgItemLevel2() != null) {
            ItemData item2 = new ItemData(level1.getDivOrgItemLevel2().getAttributes());
            Collection items2 = new LinkedList();
            items2.add(item2);
            if(level1.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
                item2.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items1,labelAttrs[1].getShowLevel()));
                labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items2,labelAttrs[0].getShowLevel());
            }else {
                
                item2.setSubItems(new LabelItemData(labelAttrs[2].getId(),labelAttrs[2].getName(),items1,labelAttrs[2].getShowLevel()));
                
                ItemData item3 = new ItemData(level1.getDivOrgItemLevel2().getDivOrgItemLevel3().getAttributes());
                item3.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items2,labelAttrs[1].getShowLevel()));

                Collection items3 = new LinkedList();
                items3.add(item3);
                
                labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items3,labelAttrs[0].getShowLevel());
            }
        }else {
            labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items1,labelAttrs[0].getShowLevel());
        }
        divOrgData.setDivOrgLevels(labelData);
        return divOrgData;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public CEPAActivityType getType() {
        return type;
    }

    public void setType(CEPAActivityType type) {
        this.type = type;
    }

    
}
