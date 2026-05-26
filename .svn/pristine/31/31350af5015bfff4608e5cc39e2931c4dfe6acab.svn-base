package communication.vehicle.tables;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.util.DateValue;


public class TrailerCheckData implements Serializable,DateValue,Comparable {

	/*
	 * Because implements Serializable it needs a serialVersionUID
	 */
	
	private Integer trailerCheckId;
	
    private static final long serialVersionUID = 1L;

    // representan el resultado(son solo los items del formulario que fueron seleccionados)
	//private Set itemSet;
	private Collection itemValueSet = null;
	
	private String name;
	
	private Date date;

    protected String obs;
    
    protected String serie;
    
    protected TrailerData trailer;
    
    private String licenseNumber;
    
    private Date dateLicenseExpiry;    

   
    public String getObs() {
		return obs;
	}



	public void setObs(String obs) {
		this.obs = obs;
	}



	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public TrailerCheckData(){
		
	}
	
	
	public TrailerCheckData(Integer id,TrailerData vehicle,Date date,String name,String obs, String serie, Collection itemValueSet,String licenseNumber,Date dateLicenseExpiry) 
	{
		this.trailerCheckId = id;
		this.trailer = vehicle;
		this.date = date;
		this.itemValueSet = itemValueSet;
		this.name = name;
		this.obs = obs;
		this.serie = serie;
		this.licenseNumber = licenseNumber;
		this.dateLicenseExpiry = dateLicenseExpiry; 
	}	
	
		
	
	/**
	 * @return Returns the instructor.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param instructor The instructor to set.
	 */
	public void setName(String name) {
		this.name = name;
	}


	public Collection getItemValueSet() {
        return itemValueSet;
    }



    public TrailerData getTrailer() {
        return trailer;
    }



    public Integer getTrailerCheckId() {
        return trailerCheckId;
    }



    public void setItemTrailerValueSet(Collection itemValueSet) {
        this.itemValueSet = itemValueSet;
    }



    public void setTrailer(TrailerData trailer) {
        this.trailer = trailer;
    }



    public void setTrailerCheckId(Integer trailerCheckId) {
        this.trailerCheckId = trailerCheckId;
    }



    public int compareTo(Object anotherobject){
		DateValue another = (DateValue)anotherobject; 
    	Date element1=another.getDate();
    	Date element2=this.getDate();
    	if (element1 instanceof Timestamp)
    		element1= new Date(element1.getTime());
    	if (element2 instanceof Timestamp)
    		element2= new Date(element2.getTime());
        return element1.compareTo(element2);    
      }

	/**
	 * Redefine el metodo equals. Dos checklist son iguales
	 * si y solo si tienen el mismo vehiculo (id del vehiculo)
	 * y tienen la misma fecha.
	 * @param o - Objeto
	 * @return boolean - True si son iguales, false en caso contrario.
	 * @author gbenaderet
	 */
	public boolean equals(Object o){
		TrailerCheckData vcd;
		if (o instanceof TrailerCheckData){
			vcd = (TrailerCheckData)o;
			// Asumo que es la misma checklist si esta asociada
			// al mismo vehiculo para la misma fecha.
			// Si las fechas son diferentes son checklist diferentes
			if (	
				(vcd.trailerCheckId != null && this.trailer != null
				&& vcd.trailerCheckId.equals(this.trailer) ) 
				||
				(vcd.getTrailer().getId().equals(this.getTrailer().getId())
				&& vcd.getDate().equals(this.getDate()))
				){
				return true;
			}else{
				return false;
			}
		}else
			return false;
	}


	/**
	 * @param itemValueSet The itemValueSet to set.
	 */
	public void setItemValueSet(Collection itemValueSet) {
		this.itemValueSet = itemValueSet;
	}
	
	/* Devuelve el valor (1,2,3 o null) de un item.
	 * Esto se utiliza para ls Commentary Drive donde los items
	 * pueden tomar valores 1 2 o 3 (segun el radiobuttom 
	 * que se indique: SI, NO, Necesita Mejorar.
	 * Para los demás casos devuelve null; 
	 */
	public String getValueFromItem(ItemData item)
	{
		if (this.itemValueSet != null)
		{
			Iterator it = this.itemValueSet.iterator();
			while (it.hasNext())
			{
				ItemValueStrData itemValue = (ItemValueStrData)it.next();
				if (itemValue.getItem().equals(item))
					return itemValue.getItemValue();
			}
		}
		return null;
		
	}

    public String getValueFromItem(Integer item) {
        if (itemValueSet != null) {
            Iterator it = itemValueSet.iterator();
            while (it.hasNext()) {
                ItemValueStrData itemValue = (ItemValueStrData)it.next();
                if(itemValue.getItem().getId().equals(item)) {
                    return itemValue.getItemValue();
                }
            }
        }
        return null;
    }

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public Date getDateLicenseExpiry() {
		return dateLicenseExpiry;
	}



	public void setDateLicenseExpiry(Date dateLicenseExpiry) {
		this.dateLicenseExpiry = dateLicenseExpiry;
	}



	public String getLicenseNumber() {
		return licenseNumber;
	}



	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	
	
}
