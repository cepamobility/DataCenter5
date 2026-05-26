/**
 * Created on 21-ago-2008
 * CEPA
 * DataCenter 5
 */
package communication.cenapra.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.ActivityStateDataType;
import communication.datatype.tables.GeneralDTData;

public class ActivityData implements Serializable {
   
    private Integer id;
    private Date date;    
    
    protected GeoLabelData country;
    private Collection divOrgs;

    private GeneralDTData type;
    private ActivityStateDataType state;

    private String instructor;
    private String city;
    private String observations;

    public ActivityData() {
        super();
    }

    /**
     * @param date
     * @param country
     * @param divOrgs
     * @param state
     * @param city
     * @param observations
     */
    public ActivityData(Integer id, Date date, GeoLabelData country, Collection divOrgs, GeneralDTData type,ActivityStateDataType state, String instructor, String city, String observations) {
        this.id = id;
        this.date = date;
        this.country = country;
        this.divOrgs = divOrgs;
        this.type = type;
        this.state = state;
        this.instructor = instructor;
        this.city = city;
        this.observations = observations;
    }

    public String getCity() {
        return city;
    }

    public GeoLabelData getCountry() {
        return country;
    }

    public Date getDate() {
        return date;
    }

    public Collection getDivOrgs() {
        return divOrgs;
    }

    public Integer getId() {
        return id;
    }

    public String getObservations() {
        return observations;
    }

    public ActivityStateDataType getState() {
        return state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(GeoLabelData country) {
        this.country = country;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDivOrgs(Collection divOrgs) {
        this.divOrgs = divOrgs;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setState(ActivityStateDataType state) {
        this.state = state;
    }

    public Iterator getDivOrgsIterator() {
        Collections.sort((List) divOrgs);
        return divOrgs.iterator();
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public GeneralDTData getType() {
        return type;
    }

    public void setType(GeneralDTData type) {
        this.type = type;
    }

    
}
