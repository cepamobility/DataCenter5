package persistence.activity.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;

import persistence.datatype.tables.CEPAActivityType;
import persistence.hibernate.HibernateUtil;

import communication.activity.tables.CEPAActivityData;
import communication.activity.tables.CEPAActivityDefinitionData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;

public class CEPAActivity extends Activity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	protected CEPAActivityType type;
	
	protected CEPAActivityDefinition definition;
	protected String observations;
	protected String theme;
	
	protected Integer duration;
	protected String file;
	
	protected boolean isOld = false;
	
	public CEPAActivity() {
		super();		
	}
	
	
	public CEPAActivity(CEPAActivityScheduleData data)throws HibernateException, Exception {
		super(data);
		
		isOld = data.getIsOld();
		
		Session session = HibernateUtil.currentSession();
	    
		type = (CEPAActivityType)session.load(CEPAActivityType.class,data.getType().getResourceBundleId());			
						
		definition = new CEPAActivityDefinition();		
		CEPAActivityDefinitionData dataDefinition = data.getDefinition();
		definition = (CEPAActivityDefinition)session.load(CEPAActivityDefinition.class,dataDefinition.getResourceKeyName());
		observations = data.getObservations();
		theme = data.getTheme();
		duration = data.getDuration();
		file = data.getFile();
	}

	public CEPAActivityDefinition getDefinition() {
		return definition;		
	}

	public void setDefinition(CEPAActivityDefinition definition) {
		this.definition = definition;		
	}

	/**
	 * @return Returns the type.
	 */
	public CEPAActivityType getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(CEPAActivityType type) {
		this.type = type;
	}
	
	/**
	 * 
	 * @return
	 */
	public CEPAActivityData getCEPAActivityData() {
		
		CorporationData corporationData = corpCountry.getPk().getCorporation().getData();
		
	    GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
	    GeoItemData countryData = (GeoItemData)corpCountry.getData();
        Collection countries = new LinkedList();
	    countries.add(countryData);
	    GeoLabelData labelData = null; 
	    if(geoLevel1 != null) {
	        GeoItemData level1 = (GeoItemData)geoLevel1.getData();
		    Collection level1s = new LinkedList();
		    level1s.add(level1);
	        if(getGeoLevel1().getGeoLevel2() == null) {
		        level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
	        }else {
	            
		        level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
	            
		        GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
		        level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

		        Collection level2s = new LinkedList();
			    level2s.add(level2);
			    
		        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
	        }
	    }else {
	        labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
	    }
		
			return new CEPAActivityData(labelData,
					getDefinition().getCEPAActivityDefinitionData(),
					getType().getResourceBundleId(),getActivityId(),
					getCorporation().getData(),getDate(),getCity()
					,getDateExpiry(),getIsOld(),getDrivingExperience(),
					getObservations(),getParticipants(),theme,duration,file);
			
		}

	public boolean getIsOld() {
		return isOld;
	}

	public void setIsOld(boolean isOld) {
		this.isOld = isOld;
	}


	/**
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}


