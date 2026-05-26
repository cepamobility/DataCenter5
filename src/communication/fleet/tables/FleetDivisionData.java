/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import persistence.fleet.tables.FleetDivision;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.datatype.tables.TypeDistanceUnitDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class FleetDivisionData implements Comparable, Serializable {

    private Integer id;
    private LabelItemData[] divOrgs = new LabelItemData[3];
    private GeoLabelData country;
    private TypeVehicleDataType vehicleType;
    private TypeDistanceUnitDataType odometer;
    private Date exclusionDate;

   
    /*
     * Constructor por Defecto.
     */
    public FleetDivisionData() {
    }
    
    /*
     * Constructor que recibe solo el id.
     */
    public FleetDivisionData(Integer id) {
        this.id = id;
    }    
    
    /*
     * Constructor por Copia
     */
    public FleetDivisionData(FleetDivision fleetDivision)
    {
    	this.id = fleetDivision.getData().getId();
    	this.divOrgs = fleetDivision.getData().getDivOrgs();
    	this.country = fleetDivision.getData().getCountry();
    	this.vehicleType = fleetDivision.getData().getVehicleType();
    	this.odometer = fleetDivision.getData().getOdometer();
    	this.exclusionDate = fleetDivision.getData().getExclusionDate();
    }

    /**
     * @return Returns the country.
     */
    public GeoLabelData getCountry() {
        return country;
    }

    /**
     * @return Returns the divOrgs.
     */
    public LabelItemData[] getDivOrgs() {
        return divOrgs;
    }

    /**
     * @param country The country to set.
     */
    public void setCountry(GeoLabelData country) {
        this.country = country;
    }

    /**
     * @param divOrgs The divOrgs to set.
     */
    public void setDivOrgs(LabelItemData[] divOrgs) {
        this.divOrgs = divOrgs;
    }

    public void setLabelItemData(int i, LabelItemData data) {
        divOrgs[i] = data;
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

    /*
     * Retuns Div Orgs Labels separated by "/".
     */
    public String getLabel(int index) {
        String label = "";
        LabelItemData lid = divOrgs[index];
        if(lid != null && lid.getItems() != null) {
            ItemData item1 = (ItemData)lid.getItemIterator().next();
            label += item1.getName();
            lid = item1.getSubItems();
            if(lid != null && lid.getItems() != null) {
                ItemData item2 = (ItemData)lid.getItemIterator().next();
                label += "/"+item2.getName();
                lid = item2.getSubItems();
                if(lid != null && lid.getItems() != null) {
                    label += "/"+((ItemData)lid.getItemIterator().next()).getName();
                }
            }
        }
        return label;
    }
    
    /*
     * Retuns Div Orgs Labels separated by pipes ("|").
     */
    public String getDivOrgsLabelSeparatedByPipes(int divOrg,int level) {
        LabelItemData lid = divOrgs[divOrg];
        if(lid != null && lid.getItems() != null) {
            ItemData item1 = (ItemData)lid.getItemIterator().next();
            if(level == 1) {
                return item1.getName();
            }else {
                lid = item1.getSubItems();
                if(lid != null && lid.getItems() != null) {
                    ItemData item2 = (ItemData)lid.getItemIterator().next();
                    if(level == 2) {
                        return item2.getName();
                    }else {
                        return ((ItemData)lid.getItemIterator().next()).getName();
                    }
                }
            }
        }
        return null;
    }
    
    public String getGeoLabel(Text messages) {
        String label = "";
        if(country != null && country.getItems() != null) {
            GeoItemData item1 = (GeoItemData)country.getItemIterator().next();
            label = messages.getText(item1.getName());
            GeoLabelData ld = item1.getSubItems();
            if(ld != null && ld.getItems() != null) {
                GeoItemData item2 = (GeoItemData)ld.getItemIterator().next();
                label = messages.getText(item2.getName());
                ld = item2.getSubItems();
                if(ld != null && ld.getItems() != null) {
                    GeoItemData item3 = (GeoItemData)ld.getItemIterator().next();
                    label = messages.getText(item3.getName());
                }
            }
        }
        return label;
    }

    /*
     * Devuelve el valor de las etiquetas de las Divisiones Organizacionales.
     */
    public String[] getDivOrgLabels() {
        String[] labels = new String[9];
        int j = 0;
        for(int i = 0; i < divOrgs.length; i++) {
            if(divOrgs[i] != null) {
                Iterator it = divOrgs[i].getItemIterator();
                if(it.hasNext()) {
                    ItemData item1 = (ItemData) it.next();
                    labels[3*j] = item1.getName();
                    //System.out.println("labels["+3*j+"] = "+labels[3*j]);
                    if(item1.getSubItems() != null) {
                        ItemData item2 = (ItemData) item1.getSubItems().getItemIterator().next();
                        labels[3*j+1] = item2.getName();
                        //System.out.println("labels["+3*j+1+"] = "+labels[3*j+1]);
                        if(item2.getSubItems() != null) {
                            labels[3*j+2] = ((ItemData) item2.getSubItems().getItemIterator().next()).getName();
                            //System.out.println("labels["+3*j+2+"] = "+labels[3*j]);
                        }
                    }
                    j++;
                }                
            }
        }
        return labels;
    }
    
    /*
     * Devuelve los Ids de las Divisiones Organizacionales de la FleetDivision 
     */
    public Integer[] getDivOrgIds() {
        Integer[] labels = new Integer[9];
        int j = 0;
        for(int i = 0; i < divOrgs.length; i++) {
            if(divOrgs[i] != null) {
                Iterator it = divOrgs[i].getItemIterator();
                if(it.hasNext()) {
                    ItemData item1 = (ItemData) it.next();
                    labels[3*j] = item1.getId();
                    //System.out.println("labels["+3*j+"] = "+labels[3*j]);
                    if(item1.getSubItems() != null) {
                        ItemData item2 = (ItemData) item1.getSubItems().getItemIterator().next();
                        labels[3*j+1] = item2.getId();
                        //System.out.println("labels["+3*j+1+"] = "+labels[3*j+1]);
                        if(item2.getSubItems() != null) {
                            labels[3*j+2] = ((ItemData) item2.getSubItems().getItemIterator().next()).getId();
                            //System.out.println("labels["+3*j+2+"] = "+labels[3*j]);
                        }
                    }
                    j++;
                }                
            }
        }
        return labels;
    }
    
    /*
     * Devuelve los Ids de los Items de las Divisiones Geograficas de la FleetDivision 
     */
    public Integer[] getCountryItemsIds() {
        Integer[] labels = new Integer[3];
        if(country.getItems() != null) {
    	Iterator it = country.getItemIterator();
            if(it.hasNext()) {
                GeoItemData item1 = (GeoItemData) it.next();
                labels[0] = item1.getId();              
                if(item1.getSubItems() != null) {
                    GeoItemData item2 = (GeoItemData) item1.getSubItems().getItemIterator().next();
                    labels[1] = item2.getId();
                    if(item2.getSubItems() != null) {
                        labels[2] = ((ItemData) item2.getSubItems().getItemIterator().next()).getId();
                    }
                }              
            }                
        }    
        return labels;
    }

    public void setVehicleType(TypeVehicleDataType type) {
        vehicleType = type;
    }

    public TypeVehicleDataType getVehicleType() {
        return vehicleType;
    }

    public void setOdometer(TypeDistanceUnitDataType type) {
        odometer = type;
    }

    public TypeDistanceUnitDataType getOdometer() {
        return odometer;
    }

    public String getCountryName() {
        if(country != null && country.getItems() != null) {
            GeoItemData item1 = (GeoItemData)country.getItemIterator().next();
            GeoLabelData ld = item1.getSubItems();
            if(ld != null && ld.getItems() != null) {
                GeoItemData item2 = (GeoItemData)ld.getItemIterator().next();
                ld = item2.getSubItems();
                if(ld != null && ld.getItems() != null) {
                    GeoItemData item3 = (GeoItemData)ld.getItemIterator().next();
                    return item3.getName();
                }else {
                    return item2.getName();
                }
            }else {
                return item1.getName();
            }
        }
        return null;
    }

    public boolean containsVehicle(VehicleData vehicle) {
        int index = 0;
        if(!vehicle.getCountryName().equals(getCountryName())) {
            return false;
        }
        Iterator it = vehicle.getDivOrgsIterator();
        while(it.hasNext()) {
            DivOrgData data = (DivOrgData)it.next();
            if(!data.getLabel().startsWith((getLabel(index)))) {
                return false;
            }
            index++;
        }
        return true;
    }

    public int compareTo(Object obj) {
        FleetDivisionData division = (FleetDivisionData)obj;
        String[] labels1 = getDivOrgLabels();
        String[] labels2 = division.getDivOrgLabels();
        for(int i = 0; i < labels1.length; i++) {
            if(labels1[i] != null && labels2[i] != null && !labels1[i].equals(labels2[i]) ) {
                return labels1[i].compareTo(labels2[i]);
            }
        }
        return 0;
    }

	/**
	 * @return Returns the exclusionDate.
	 */
	public Date getExclusionDate() {
		return exclusionDate;
	}

	/**
	 * @param exclusionDate The exclusionDate to set.
	 */
	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	 /*
     * Devuelve true si un objeto es igual a este FleetDivisionData
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
    	Integer [] labels1, labels2;
    	FleetDivisionData fdd;
    	if (FleetDivisionData.class.isInstance(o))
    	{
    		fdd = (FleetDivisionData)o;
    		if (this.id == fdd.id)
    		{
    			return true;
    		}
    		else 
    		{    		
                if  ((this.vehicleType == null && fdd.vehicleType != null) ||
                        (this.vehicleType != null && fdd.vehicleType == null)) {
                    return false;
                }
                if  ((this.odometer == null && fdd.odometer != null) ||
                        (this.odometer != null && fdd.odometer == null)) {
                    return false;
                }

               if  ( ( 
    					(this.vehicleType == null && fdd.vehicleType == null) ||
    					(this.vehicleType.equals(fdd.vehicleType))
    				) && ( 					
    					(this.odometer == null && fdd.odometer == null) ||
    					(this.odometer.equals(fdd.odometer))
    				) )
    			{
    				// Verifico que los ids de todas las divisiones geograficas
	    			// sean iguales en ambos objetos:
					labels1 = this.getCountryItemsIds();
	    	        labels2 = fdd.getCountryItemsIds();
	    	        if(labels1[0] != null && labels2[0] != null && !labels1[0].equals(labels2[0]) ) {
	    	            return false;
	    	        }else {
	    	            if(labels1[1] != null && labels2[1] != null && !labels1[1].equals(labels2[1]) ) {
	    	                return false;
	    	            }else {
	    	                if(labels1[2] != null && labels2[2] != null && !labels1[2].equals(labels2[2]) ) {
	    	                    return false;
	    	                }
	    	            }
	    	        }   				
	    			
	    			// Verifico que los ids de todas las divisiones organizacionales
	    			// sean iguales en ambos objetos.
	    			labels1 = this.getDivOrgIds();
	    	        labels2 = fdd.getDivOrgIds();
                    for(int i = 0; i < 9; i++) {
                        if(labels1[i] != null && labels2[i] != null && !labels1[i].equals(labels2[i]) ) {
                            return false;
                        }
	    	        }   			
	    			return true;
    			}
    			else
    			{
    				return false;    			
    			}
    		} 
    	}
    	else
    	{
    		return false;
    	}
    }

    public Integer getCountryId() {
        if(country != null && country.getItems() != null) {
            GeoItemData item1 = (GeoItemData)country.getItemIterator().next();
            GeoLabelData ld = item1.getSubItems();
            if(ld != null && ld.getItems() != null) {
                GeoItemData item2 = (GeoItemData)ld.getItemIterator().next();
                ld = item2.getSubItems();
                if(ld != null && ld.getItems() != null) {
                    GeoItemData item3 = (GeoItemData)ld.getItemIterator().next();
                    return item3.getId();
                }else {
                    return item2.getId();
                }
            }else {
                return item1.getId();
            }
        }
        return null;
    }

    public Integer[] getDivOrgLastIds() {
        Integer[] labels = new Integer[3];
        int j = 0;
        for(int i = 0; i < divOrgs.length; i++) {
            if(divOrgs[i] != null) {
                Iterator it = divOrgs[i].getItemIterator();
                if(it.hasNext()) {
                    ItemData item1 = (ItemData) it.next();
                    if(item1.getSubItems() != null) {
                        ItemData item2 = (ItemData) item1.getSubItems().getItemIterator().next();
                        if(item2.getSubItems() != null) {
                            labels[j] = ((ItemData) item2.getSubItems().getItemIterator().next()).getId();
                        }else {
                            labels[j] = item2.getId();
                        }
                    }else {
                        labels[j] = item1.getId();
                    }
                    j++;
                }                
            }
        }
        return labels;
    }

	public String getKey(Integer corp) {
		String key = String.valueOf(getCountryId());
		Integer[] values = getDivOrgLastIds();
		switch(corp.intValue()) {
			case CorporationData.MONSANTO:
				key += ";"+values[1]+";"+values[2];
				break;
			case CorporationData.JJ: case CorporationData.ASTRAZENECA: case CorporationData.SYNGENTA: case CorporationData.ALLERGAN:
				key += ";"+values[0]+";"+values[1];
				break;
			case CorporationData.BAYER: case CorporationData.DOW:
				key += ";"+values[1];
				break;
			default:
				for(int i = 0; i < values.length; i++) {
					if(values[i] != null) {
						key += ";"+values[i];
					}
				}
		}
		return key;
	}
    
}
