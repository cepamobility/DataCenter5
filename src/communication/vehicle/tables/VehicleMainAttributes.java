/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;

/**
 * @author jrodriguez
 */
public class VehicleMainAttributes implements Serializable{

    private static final long serialVersionUID = 1L;

    protected Integer id;
	protected String matricula;
	protected GeoLabelData country;
	protected Integer year;
    protected Date exclusionDate;
    protected String divOrgItem1;
    protected String divOrgItem2;
    protected String divOrgItem3;
    protected String divOrgItem4;
   
    public VehicleMainAttributes(){
    	super();
    }
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param countryy
	 * @param city
	 * @param exclusionDate
	 */
	public VehicleMainAttributes(Integer id, String matricula, 
			GeoLabelData country, Integer year, Date exclusionDate) {
		super();
		this.id = id;
		this.matricula = matricula.trim();
		this.year = year;
		this.country = country;
		this.exclusionDate = exclusionDate;
		
	}
	
	/**
	 * @param uid
	 */
	public VehicleMainAttributes(Integer id) {
		this.id = id;
	}

    /**
     * @return Returns the birthDate.
     */
    public String getMatricula() {
        return matricula;
    }

   
    
	/**
	 * @return Returns the city.
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @return Returns the country.
	 */
	public GeoLabelData getCountry() {
		return country;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	public String getCountryName() {
	    GeoItemData itemData1 = (GeoItemData)country.getItemIterator().next();
	    while(itemData1.getSubItems() != null) {
	        itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
	    }
	    return itemData1.getName();
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


	
    
	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

    public void setCountry(GeoLabelData country) {
        this.country = country;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

	public String getDivOrgItem1() {
		return divOrgItem1;
	}

	public void setDivOrgItem1(String divOrgItem1) {
		this.divOrgItem1 = divOrgItem1;
	}

	public String getDivOrgItem2() {
		return divOrgItem2;
	}

	public void setDivOrgItem2(String divOrgItem2) {
		this.divOrgItem2 = divOrgItem2;
	}

	public String getDivOrgItem3() {
		return divOrgItem3;
	}

	public void setDivOrgItem3(String divOrgItem3) {
		this.divOrgItem3 = divOrgItem3;
	}

	public String getDivOrgItem4() {
		return divOrgItem4;
	}

	public void setDivOrgItem4(String divOrgItem4) {
		this.divOrgItem4 = divOrgItem4;
	}

	
    

	
}
