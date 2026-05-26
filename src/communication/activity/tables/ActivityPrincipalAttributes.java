package communication.activity.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;

public class ActivityPrincipalAttributes implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DIV_ORG = 1;
    public static final int DIV_GEO = 2;

    protected Integer activityId;
	
	protected CorporationData corpooration;
	
	protected GeoLabelData countrygeo;
	
	//private CountryData country;
	
	protected Date date;
	
	protected Date dateExpiry;
	
	protected String city;
	
	protected boolean isOld;
	protected int drivingExperience = 0;
	
	private Integer participants;		

	public Date getDate() {
		return date;
	}

	public String getCity() {
		return city;
	}


	/**
	 * @param activityId The activityId to set.
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}


	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @param corpooration The corpooration to set.
	 */
	public void setCorpooration(CorporationData corpooration) {
		this.corpooration = corpooration;
	}


	/**
	 * @param country The country to set.
	 */
	/*public void setCountry(CountryData country) {
		this.country = country;
	}*/


	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	public Integer getActivityId() {
		return activityId;
	}

	public CorporationData getCorpooration() {
		return corpooration;
	}
	
	public ActivityPrincipalAttributes(GeoLabelData countrygeo,Integer activityId, 
			CorporationData corpooration, Date date, String city, 
			Date dateExpiry, boolean isOld, int drivingExperience, Integer participants) {
		super();		
		this.activityId = activityId;
		this.corpooration = corpooration;
		this.countrygeo = countrygeo;		
		this.drivingExperience = drivingExperience;
		this.date=date;
		this.city=city;
		this.participants = participants;
		this.dateExpiry=dateExpiry;
		
		this.isOld=isOld;
	}
	
	public ActivityPrincipalAttributes(Integer id) {
		this.activityId = id;
	}
	
	public ActivityPrincipalAttributes() {

	}


	/**
	 * @return Returns the countrygeo.
	 */
	public GeoLabelData getCountrygeo() {
		return countrygeo;
	}


	/**
	 * @param countrygeo The countrygeo to set.
	 */
	public void setCountrygeo(GeoLabelData countrygeo) {
		this.countrygeo = countrygeo;
	}
	
	public String getCountryName() {
	    GeoItemData itemData1 = (GeoItemData)countrygeo.getItemIterator().next();
	    while(itemData1.getSubItems() != null) {
	        itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
	    }
	    return itemData1.getName();
	}
	
	public String getCountryId() {
	    GeoItemData itemData1 = (GeoItemData)countrygeo.getItemIterator().next();
	    while(itemData1.getSubItems() != null) {
	        itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
	    }
	    return itemData1.getId().toString();
	}

	public String getDMY() {
		String dmy = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
		return dmy;
	}
	
	 public String getData(int type, int index, int j) {
	        if(type == DIV_ORG) {
	            Iterator it = corpooration.getDivOrgsIterator();
	            DivOrgData data = null;
	            for(int i = 0; i < index; i++) {
	                data = (DivOrgData)it.next();
	            }
	            LabelItemData label = data.getDivOrgLevels(); 
	            for(int i = 1; i < j; i++) {
	                label = ((ItemData)label.getItemIterator().next()).getSubItems();
	            }
	            return ((ItemData)label.getItemIterator().next()).getName();
	        }else{
	            GeoLabelData label = getCountrygeo(); 
	            for(int i = 1; i < j; i++) {
	                label = ((GeoItemData)label.getItemIterator().next()).getSubItems();
	            }
	           return ((GeoItemData)label.getItemIterator().next()).getName();
	        }
	    }

     public String getLabel(int type, int index, int j) {
            if(type == DIV_ORG) {
                Iterator it = corpooration.getDivOrgsIterator();
                DivOrgData data = null;
                for(int i = 0; i < index; i++) {
                    data = (DivOrgData)it.next();
                }
                LabelItemData label = data.getDivOrgLevels(); 
                for(int i = 1; i < j; i++) {
                    label = ((ItemData)label.getItemIterator().next()).getSubItems();
                }
                return label.getName();
            }else{
                GeoLabelData label = getCountrygeo(); 
                for(int i = 1; i < j; i++) {
                    label = ((GeoItemData)label.getItemIterator().next()).getSubItems();
                }
                return label.getResourceKey();
            }
        }


	public Date getDateExpiry() {
		return dateExpiry;
	}


	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}


	public boolean getIsOld() {
		return isOld;
	}


	public void setIsOld(boolean isOld) {
		this.isOld = isOld;
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
