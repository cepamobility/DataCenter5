/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.cenapra.tables;

import java.io.Serializable;
import java.util.Date;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ParticipantMainAttributes implements Serializable{

    private static final long serialVersionUID = 1L;

    protected Integer id;
	protected String firstName;
	protected String lastName;
	protected GeoLabelData country;
    protected Date birthDate;
	protected String city;
	private String stateName;

	private String locality;
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param countryy
	 * @param city
	 */
	public ParticipantMainAttributes(Integer id, String firstName, String lastName,	GeoLabelData country, Date birthDate, String city) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
        this.birthDate = birthDate;
		this.city = city;
	}
	
	public ParticipantMainAttributes(Integer id, String firstName, String lastName,	GeoLabelData country, Date birthDate, String city, Date exclusionDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
        this.birthDate = birthDate;
		this.city = city;
	}
	
	/**
	 * @param uid
	 */
	public ParticipantMainAttributes(Integer id) {
		this.id = id;
	}

    public ParticipantMainAttributes() {
    }

    public ParticipantMainAttributes(Object[] objects) {
        id = (Integer)objects[0];
        firstName = (String)objects[1];
        lastName = (String)objects[2];
        birthDate = (Date)objects[3];
        stateName = (String)objects[4];
        city = (String)objects[5];
        if(objects.length > 6) {
        	locality = (String)objects[6];
        }
    }

    /**
     * @return Returns the birthDate.
     */
    public Date getBirthDate() {
        return birthDate;
    }

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return Returns the country.
	 */
	public GeoLabelData getCountry() {
		return country;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	public String getCountryName() {
	    GeoItemData itemData1 = (GeoItemData)country.getItemIterator().next();
	    while(itemData1.getSubItems() != null) {
	        itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
	    }
	    return itemData1.getName();
	}

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(GeoLabelData country) {
        this.country = country;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

}
