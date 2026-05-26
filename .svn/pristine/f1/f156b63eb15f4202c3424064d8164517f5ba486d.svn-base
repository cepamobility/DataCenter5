/**
 * Created on 21-ago-2008
 * CEPA
 * DataCenter 5
 */
package persistence.cepasystem.tables;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.CEPAActivityScheduleState;
import persistence.datatype.tables.CEPAActivityType;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.cepasystem.tables.ActivityData;
import communication.datatype.tables.ActivityStateDataType;
import communication.datatype.tables.GeneralDTData;

public class CepaSystemActivity {

     private Integer id;
     private Date date;    
     
     private CorporationCountry corpCountry;
     private DivOrgItemLevel1 divOrg1;
     private DivOrgItemLevel1 divOrg2;
     private DivOrgItemLevel1 divOrg3;

     private CEPAActivityType type;
     private Integer state;

     private String instructor;
     private String city;
     private String observations;

     public CepaSystemActivity() {
        super();
    }

    public CepaSystemActivity(ActivityData data, Session session, Integer corporationId) {
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
        
        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),corporationId));
        
        type = (CEPAActivityType)session.load(CEPAActivityType.class,data.getType().getResourceBundleId());
        state = data.getState();
        
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

    public Integer getId() {
        return id;
    }

    public String getObservations() {
        return observations;
    }

    public Integer getState() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setState(Integer state) {
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
        
        return new ActivityData(id,date,labelData,(GeneralDTData)type.getData(),state,instructor,city,observations);
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
