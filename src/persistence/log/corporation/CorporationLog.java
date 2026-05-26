/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.log.corporation;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.DivOrg;
import persistence.administration.corporation.tables.GeoLabel;
import persistence.log.IdLogPK;

/**
 * @author fcarriquiry
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CorporationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private IdLogPK pk;
	private Integer action;
	private String name;
	private Set divOrgSet;
	private Set geoLabelSet;
	private Set countries;
	
	/**
	 *  
	 */
	public CorporationLog() {
	}

	public CorporationLog(Corporation corporation,String user,Date date, Integer action, Session session) {
	    pk = new IdLogPK(corporation.getId(),user,date);
	    name = corporation.getName();
	    this.action = action;
	    
	    divOrgSet = new HashSet();
	    if(corporation.getDivOrgSet() != null) {
	        Iterator it = corporation.getDivOrgSet().iterator();
	        while(it.hasNext()) {
	            divOrgSet.add(new DivOrgLog((DivOrg)it.next(),user,date,this,session));
	        }
	    }

	    geoLabelSet = new HashSet();
	    if(corporation.getGeoLabelSet() != null) {
		    Iterator it = corporation.getGeoLabelSet().iterator();
		    while(it.hasNext()) {
		        geoLabelSet.add(new GeoLabelLog((GeoLabel)it.next(),user,date,this));
		    }
	    }
	    
	    countries = new HashSet();
	    if(corporation.getCountries() != null) {
	        Iterator it = corporation.getCountries().iterator();
	        while(it.hasNext()) {
	            countries.add(new CorporationCountryLog((CorporationCountry)it.next(),user,date,session));
	        }
	    }
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the divisionSet.
	 */
	public Collection getDivOrgSet() {
		return divOrgSet;
	}

	/**
	 * @param divisionSet
	 *            The divisionSet to set.
	 */
	public void setDivOrgSet(Set divOrgSet) {
		this.divOrgSet = divOrgSet;
	}

	public IdLogPK getPk() {
		return pk;
	}
	
	public void setPk(IdLogPK pk) {
		this.pk = pk;
	}
	
    /**
     * @return Returns the getLabels.
     */
    public Set getGeoLabelSet() {
        return geoLabelSet;
    }
    
    /**
     * @param getLabels The getLabels to set.
     */
    public void setGeoLabelSet(Set geoLabelSet) {
        this.geoLabelSet = geoLabelSet;
    }
    
    
    /**
     * @return Returns the countries.
     */
    public Set getCountries() {
        return countries;
    }
    
    /**
     * @param countries The countries to set.
     */
    public void setCountries(Set countries) {
        this.countries = countries;
    }

    /**
     * @return Returns the action.
     */
    public Integer getAction() {
        return action;
    }
    
    /**
     * @param action The action to set.
     */
    public void setAction(Integer action) {
        this.action = action;
    }
}