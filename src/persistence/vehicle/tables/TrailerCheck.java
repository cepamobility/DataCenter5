package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import persistence.activity.tables.Item;
import persistence.activity.tables.ItemValueStr;
import persistence.hibernate.HibernateUtil;

import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.vehicle.tables.TrailerCheckData;

public class TrailerCheck implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer trailerCheckId;
	
	// Contiene el Set de pares (item,valor):
	// 1,2,3 segun corresponda o NULL
	// 1 es correcto, 2 es Falta limpieza/orden y 3 es Con ,arcas,raspones 
	protected Set itemValueSet = null;
	
	protected Date date;
	
	protected String name;
	
	protected String obs;

	protected String serie;	
	
	protected Trailer trailer;
	
	protected String licenseNumber;
	
	protected Date dateLicenseExpiry;

	public void updateData(TrailerCheckData data){
		this.trailerCheckId = data.getTrailerCheckId();
		
		Session session = HibernateUtil.currentSession();
		// itemValueSet
		itemValueSet = new HashSet();
		if (data.getItemValueSet() != null) {
			Iterator it = data.getItemValueSet().iterator();
	    	while(it.hasNext()){
	    		ItemValueStrData itemValueData = (ItemValueStrData)it.next();    		
	    		Item item = (Item)session.load(Item.class,itemValueData.getItem().getId());
	    		String value = itemValueData.getItemValue();
	    		ItemValueStr itemValue = new ItemValueStr(item,value);
	    		itemValueSet.add(itemValue);	    	
	    	}
		}
		this.date = data.getDate();
		this.name = data.getName();
		this.obs = data.getObs();
		this.serie = data.getSerie();
		this.trailer = (Trailer)session.load(Trailer.class,data.getTrailer().getId());
		
		this.licenseNumber = data.getLicenseNumber();
		this.dateLicenseExpiry = data.getDateLicenseExpiry();		
	}
	
	public TrailerCheck() {

	}
	
	public Integer getTrailerCheckId() {
		return trailerCheckId;
	}

	public void setTrailerCheckId(Integer trailerCheckId) {
		this.trailerCheckId = trailerCheckId;
	}

	// Devuelve una CEPAActivityData con los items sin cargar
	public TrailerCheckData getTrailerCheckData() { 
		TrailerCheckData vcd = new TrailerCheckData();
		vcd.setTrailerCheckId(this.getTrailerCheckId());
		vcd.setTrailer(trailer.getTrailerData());
		vcd.setDate(this.date);
		vcd.setName(this.getName());
		vcd.setObs(this.obs);
		vcd.setSerie(this.serie);
        Iterator it = itemValueSet.iterator();
        Collection items = new LinkedList();
        while(it.hasNext()) {
            items.add(((ItemValueStr)it.next()).getData());
        }
		vcd.setItemValueSet(items);
		
		vcd.setLicenseNumber(this.licenseNumber);
		vcd.setDateLicenseExpiry(this.dateLicenseExpiry);
		
		return vcd;
	}	
	
	public TrailerCheck(TrailerCheckData data) throws HibernateException,Exception {
		
		this.date = data.getDate();	
		this.name = data.getName();	
		this.obs = data.getObs();
		this.serie = data.getSerie();		
        Session session = HibernateUtil.currentSession();
		this.trailer = (Trailer)session.load(Trailer.class,data.getTrailer().getId());
		
		this.licenseNumber = data.getLicenseNumber();
		
		this.dateLicenseExpiry = data.getDateLicenseExpiry();

				
		// itemValueSet
		itemValueSet = new HashSet();
		if (data.getItemValueSet() != null) {
			Iterator it = data.getItemValueSet().iterator();
	    	while(it.hasNext()){
	    		ItemValueStrData itemValueData = (ItemValueStrData)it.next();    		
                Item item = (Item)session.load(Item.class,itemValueData.getItem().getId());
	    		String value = itemValueData.getItemValue();
	    		ItemValueStr itemValue = new ItemValueStr(item,value);
	    		itemValueSet.add(itemValue);	    	
	    	}
		}
		else
			itemValueSet = null;		
	}

	/**
	 * @return Returns the itemValueSet.
	 */
	public Set getItemValueSet() {
		return itemValueSet;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param itemValueSet The itemValueSet to set.
	 */
	public void setItemTrailerValueSet(Set itemValueSet) {
		this.itemValueSet= itemValueSet;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

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
	
	public String getValue(int idItem) {
		String value = "";
		Iterator it = itemValueSet.iterator();
		boolean encontre = false;
		ItemValueStr itemValue = null;
		while (it.hasNext() && !encontre) {
			itemValue = (ItemValueStr) it.next();
			encontre = (itemValue.getItem().getId().intValue() == idItem);
			if (encontre) {
				value = itemValue.getItemValue();
			}
		}
		return value;
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

    public void setItemValueSet(Set itemValueSet) {
        this.itemValueSet = itemValueSet;
    }

}
