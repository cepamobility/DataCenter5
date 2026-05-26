/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorporationCountryPK implements Serializable{
	
    private static final long serialVersionUID = 1L;

    private Country country;
	private Corporation corporation;
	
	/**
	 * 
	 */
	public CorporationCountryPK() {
	}
	
	public CorporationCountryPK(Country country, Corporation corporation) {
		this.country=country;
		this.corporation=corporation;
	}
	
	public CorporationCountryPK(Integer countryId, Integer corporationId) {
		country=new Country();
		country.setId(countryId);
		corporation=new Corporation();
		corporation.setId(corporationId);
	}

	/**
	 * @return Returns the user.
	 */
	public Country getCountry() {
		return country;
	}
	
	/**
	 * @param user The user to set.
	 */
	public void setCountry(Country country) {
		this.country = country;
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
	
	
}
