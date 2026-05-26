/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import persistence.util.LabelValue;
import persistence.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;

/**
 * @author fcarriquiry
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Corporation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
	private String name;
    private Integer type;
	private Set divOrgSet;
	private Set geoLabelSet;
	private Set countries;
	
	/**
	 *  
	 */
	public Corporation() {
	}

	public Corporation(CorporationData data) {
	    this.id = data.getId();
	    this.name = data.getName();
	    this.type = data.getType();
	    divOrgSet = new HashSet();
	    Iterator it = data.getDivOrgDataSet().iterator();
	    while(it.hasNext()) {
	        DivOrg divOrg = new DivOrg(((DivOrgData)it.next()));
	        divOrg.setCorporation(this);
	        divOrgSet.add(divOrg);
	    }
        geoLabelSet = new HashSet();
        for(int i = 0; i < data.getGeoLabels().length; i++) {
            GeoLabelAttributes attr = data.getGeoLabels()[i];
            geoLabelSet.add(new GeoLabel(attr.getId(),this,attr.getResourceKey(),attr.getLevel()));
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

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public CorporationData getData() {
	    
	    Collection divOrgs = new LinkedList();
	    if(divOrgSet != null) {
		    Iterator it = divOrgSet.iterator();
		    while(it.hasNext()) {
		    	DivOrg divOrg = (DivOrg)it.next();
		        divOrgs.add(divOrg.getData());
		    }
	    }
	    
	    LabelValue[] geoLabels = null;
	    GeoLabelAttributes[] labelAttributes = null;
	    if(geoLabelSet != null) {
	        geoLabels = new GeoLabel[geoLabelSet.size()];
	        labelAttributes = new GeoLabelAttributes[geoLabelSet.size()];
	        Iterator it = geoLabelSet.iterator();
	        while(it.hasNext()) {
	            GeoLabel geoLabel =(GeoLabel)it.next();
	            geoLabels[geoLabel.getLevel().intValue()-1] = geoLabel;
	            labelAttributes[geoLabel.getLevel().intValue()-1] = geoLabel.getAttributes();
	        }
	    }

	    Util util = new Util();
	    return new CorporationData(id,name,type,divOrgs,labelAttributes,(GeoLabelData) util.makeNode(countries,geoLabels,id));
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    class ItemAux {
        
        public Integer id;
        public String name;
        public boolean active;
        public Hashtable components;
        
        public ItemAux(Integer id,String name,boolean active) {
            this.id = id;
            this.name = name;
            this.active = active;
            components = new Hashtable();
        }
        
    }

}