/**
 * Created on 21-ago-2008
 * CEPA
 * DataCenter 5
 */
package communication.cepasystem.tables;

import java.io.Serializable;
import java.util.Date;

import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.GeneralDTData;

public class ActivityData implements Serializable {
   
    public static final Integer PENDING_SUPERVISION = new Integer(1);
	public static final Integer SUPERVISED = new Integer(2);
    
	private Integer id;
    private Date date;    
    
    protected GeoLabelData country;

    private GeneralDTData type;
    private Integer state;

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
    public ActivityData(Integer id, Date date, GeoLabelData country, GeneralDTData type,Integer state, String instructor, String city, String observations) {
        this.id = id;
        this.date = date;
        this.country = country;
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

    public void setCountry(GeoLabelData country) {
        this.country = country;
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
