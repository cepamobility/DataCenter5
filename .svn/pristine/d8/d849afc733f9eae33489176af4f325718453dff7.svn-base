/*
 * Created on 01-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import communication.administration.corporation.tables.CountryData;

/**
 * @author jrodriguez
 */
public class Country {

	final public static int MEXICO = 42;
	final public static int ARGENTINA = 33;
	final public static int BRASIL = 32;
	final public static int URUGUAY = 31;
	
    private Integer id;
    private String resourceKey;
    
    public boolean equals(Object o)
    {
    	if (Country.class.isInstance(o))
    	{
    		Country c = (Country)o;
    		if (id.equals(c.id) && this.resourceKey.equals(c.resourceKey))
    		{
    			return true;
    		}
    		else
    			return false;
    	}else
    		return false;
    }
	public Country() {
	}

	public Country(Integer id,String name) {
	    this.id = id;
	    resourceKey = name;
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
     * @return Returns the name.
     */
    public String getResourceKey() {
        return resourceKey;
    }
    
    /**
     * @param name The name to set.
     */
    public void setResourceKey(String name) {
        resourceKey = name;
    }

    /**
     * @return
     */
    public CountryData getData() {
        return new CountryData(id,resourceKey);
    }

}
