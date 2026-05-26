package communication.driver.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DriverMainAttributes implements Comparable, Serializable{

    private static final long serialVersionUID = 1L;

    public static final int CHANGE_DRIVER = 1;
    
    protected Integer id;
	protected String firstName;
	protected String lastName;
	protected GeoLabelData country;
    protected Date birthDate;
	protected String city;
    protected Date exclusionDate;
    protected String divOrgItem1;
    protected String divOrgItem2;
    protected String divOrgItem3;
    protected String divOrgItem4;
    protected String corporationId;
    protected Date licenceExpirity;

    public DriverMainAttributes(){}
    
    /**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param countryy
	 * @param city
	 */
	public DriverMainAttributes(Integer id, String firstName, String lastName,	GeoLabelData country, Date birthDate, String city, Date exclusionDate,String divOrgItem1,String divOrgItem2,String divOrgItem3,String divOrgItem4) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
        this.birthDate = birthDate;
		this.city = city;
        this.exclusionDate = exclusionDate;
        this.divOrgItem1 = divOrgItem1;
        this.divOrgItem2 = divOrgItem2;
        this.divOrgItem3 = divOrgItem3;
        this.divOrgItem4 = divOrgItem4;
	}
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param countryy
	 * @param city
	 */
	public DriverMainAttributes(Integer id, String firstName, String lastName,	GeoLabelData country, Date birthDate, String city, Date exclusionDate,String divOrgItem1,String divOrgItem2,String divOrgItem3,String divOrgItem4, String corpId, Date licence) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
        this.birthDate = birthDate;
		this.city = city;
        this.exclusionDate = exclusionDate;
        this.divOrgItem1 = divOrgItem1;
        this.divOrgItem2 = divOrgItem2;
        this.divOrgItem3 = divOrgItem3;
        this.divOrgItem4 = divOrgItem4;
        this.corporationId=corpId;
        this.licenceExpirity=licence;
	}
	
	public DriverMainAttributes(Integer id, String firstName, String lastName,	GeoLabelData country, Date birthDate, String city, Date exclusionDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
        this.birthDate = birthDate;
		this.city = city;
        this.exclusionDate = exclusionDate;
        
	}
	
	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public Date getLicenceExpirity() {
		return licenceExpirity;
	}

	public void setLicenceExpirity(Date licenceExpirity) {
		this.licenceExpirity = licenceExpirity;
	}

	/**
	 * @param uid
	 */
	public DriverMainAttributes(Integer id) {
		this.id = id;
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
	    if (country != null){
	    	Iterator it = country.getItemIterator();
	    	if (it.hasNext()){
	    		GeoItemData itemData1 = (GeoItemData)country.getItemIterator().next();
	    		while(itemData1.getSubItems() != null) {
	    			itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
	    		}
	    		return itemData1.getName();
	    	}
	    }
	    return null;
	}

    public Integer getCountryId() {
        GeoItemData itemData1 = (GeoItemData)country.getItemIterator().next();
        while(itemData1.getSubItems() != null) {
            itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
        }
        return itemData1.getId();
    }

    public Date getExclusionDate() {
        return exclusionDate;
    }
    
    public String getDivOrgItem1(){
    	return divOrgItem1;
    }
    
    public String getDivOrgItem(int i){
    	switch(i){
    		case 1: return getDivOrgItem1();
    		case 2: return getDivOrgItem2();
    		case 3: return getDivOrgItem3();    		
    		case 4: return getDivOrgItem4();    		
    	}
    	return null;
    }
    
    private String getDivOrgItem4() {
		return divOrgItem4;
	}

	public String getDivOrgItem2(){
    	return divOrgItem2;
    }
    
    public String getDivOrgItem3(){
    	return divOrgItem3;
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

    public void setDivOrgItem1(String divOrgItem1) {
        this.divOrgItem1 = divOrgItem1;
    }

    public void setDivOrgItem2(String divOrgItem2) {
        this.divOrgItem2 = divOrgItem2;
    }

    public void setDivOrgItem3(String divOrgItem3) {
        this.divOrgItem3 = divOrgItem3;
    }

    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
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

    /**
     * 
     * @param arg0
     * @return
     */
	public int compareTo(Object arg0) {
		if (DriverMainAttributes.class.isInstance(arg0)){
			DriverMainAttributes dma = (DriverMainAttributes) arg0;
			if (firstName.compareTo(dma.getFirstName()) == 0){
				if (lastName.compareTo(dma.getLastName()) == 0){
					if (birthDate.compareTo(dma.getBirthDate()) == 0){
						return 0;
					}else{
						return birthDate.compareTo(dma.getBirthDate());
					}
				}else{
					return lastName.compareTo(dma.getLastName());
				}
			}else{
				return firstName.compareTo(dma.getFirstName());
			}
		}
		return 0;
	}

	public boolean isMercosur() {
		Integer countryId = getCountryId();
		return (countryId.equals(CountryData.URUGUAY_ID) || countryId.equals(CountryData.PARAGUAY_ID) || countryId.equals(CountryData.ARGENTINA_ID));
	}

	public String getFullName() {
		return firstName+" "+lastName;
	}
}
