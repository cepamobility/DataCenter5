package persistence.activity.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.Country;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.hibernate.HibernateUtil;

import communication.activity.tables.ActivityData;
import communication.administration.corporation.tables.GeoItemData;

public class Activity implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer activityId;	
	private String discriminator;	
	protected CorporationCountry corpCountry;
	protected GeoLevel1 geoLevel1;	
	private Date date;	
	private Date dateExpiry;	
	private int drivingExperience;
	private String city;
	protected Integer participants;
	

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activity(ActivityData data)throws HibernateException{
		
		this.activityId=data.getActivityId();
		this.date=data.getDate();
		this.city=data.getCity();
		this.participants = data.getParticipants();
		this.drivingExperience = data.getDrivingExperience();
		this.dateExpiry=data.getDateExpiry();
		
		
		Session session = HibernateUtil.currentSession();
		//CountryData itemData = (CountryData)data.getCountrygeo();
	    //corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData.getId(),data.getCorpooration().getId() ));
		
		// Recorro arbol(más precisamente rama) de divisiones geográficas para llegar hasta una hoja (que es el pais)
		// En item2 voy llevando el padre
	    GeoItemData itemData1 = (GeoItemData)data.getCountrygeo().getItemIterator().next();
	    GeoItemData itemData2 = null; 
	    while(itemData1.getSubItems() != null) {
	        itemData2 = itemData1;
	        itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
	    }
        
        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),data.getCorpooration().getId()));
	    
	    // Creo que en GeoLevel1 queda el arbol de div geografica de nivel 1 (por ej LA,Mercosur,etc)
	    // GeoLevel1 contiene un GeoLevel2; de esta manera se soportan hasta 3 niveles de profundidad
	    // en el arbol de divisiones geográficas
	    if(itemData2 != null) {
	        geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
	    }
	    //System.out.println("GEO LEVEL2:" + geoLevel1.getGeoLevel2().getResourceKey());
	    
      
	    
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public String getDiscriminator() {
		return discriminator;
	}

	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}

	

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public CorporationCountry getCorpCountry() {
		return corpCountry;
	}

	public void setCorpCountry(CorporationCountry corpCountry) {
		this.corpCountry = corpCountry;
	}

	public Corporation getCorporation(){
		return getCorpCountry().getPk().getCorporation();
	}
	
	public Country getCountry(){
		return getCorpCountry().getPk().getCountry();
	}
	
/*	public ActivityData getActivityData(){
        CorporationData corporationData = getCorporation().getData();
	    GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
	    CountryData countryData = (CountryData)getCorpCountry().getPk().getCountry().getData();
	     
	   return new ActivityData(activityId,getCorporation().getData(),countryData,getDate(),city);
	}
*/	
	public boolean equals(Object obj) {
		if(obj!=null){
			Activity a=(Activity)obj;
			if(a.getActivityId()!=null && this.getActivityId()!=null){
				return a.getActivityId().equals(this.getActivityId());
			}
			else{
			  
				return false;
			}
		}
		return super.equals(obj);
	}

	/**
	 * @return Returns the geoLevel1.
	 */
	public GeoLevel1 getGeoLevel1() {
		return geoLevel1;
	}

	/**
	 * @param geoLevel1 The geoLevel1 to set.
	 */
	public void setGeoLevel1(GeoLevel1 geoLevel1) {
		this.geoLevel1 = geoLevel1;
	}

	public Date getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	/**
	 * @return Returns the drivingExperience.
	 */
	public int getDrivingExperience() {
		return drivingExperience;
	}

	/**
	 * @param drivingExperience The drivingExperience to set.
	 */
	public void setDrivingExperience(int drivingExperience) {
		this.drivingExperience = drivingExperience;
	}

	public Integer getParticipants() {
		return participants;
	}

	public void setParticipants(Integer participants) {
		this.participants = participants;
	}

	
}
