/*
 * Created on 26-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.event.tables;

import java.util.Collection;
import java.util.LinkedList;

import org.hibernate.Session;

import persistence.datatype.tables.FineQualify;
import persistence.datatype.tables.FineType;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.FineTypeDataType;
import communication.datatype.tables.IncidentClasificationDataType;
import communication.event.tables.EventDataType;
import communication.event.tables.EventMainAttributes;
import communication.event.tables.FineDataType;
/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Fine extends EventAbstract {

    private FineType type;
    // private String points;
    private FineQualify qualify;
    private Integer cost;
    private String article;
    private String observations;
    private String infringementNotice;
	private Integer permittedSpeed;
	private Integer speed;
	
	public Fine() {
		super();
	}

    public Fine(FineDataType fine, Session session) {
        this(fine,session,false);

    }
    
    public Fine(FineDataType fine, Session session, boolean withDriverData) {
        super(fine,session,withDriverData);
        if(!withDriverData) {
        	GeoItemData itemData1 = (GeoItemData)fine.getCountry().getItemIterator().next();
        	while(itemData1.getSubItems() != null) {
        		itemData1 = (GeoItemData)itemData1.getSubItems().getItemIterator().next();
        	}
        }
        this.type = (FineType)session.load(FineType.class,fine.getFineType().getResourceBundleId());
        // this.points = fine.getPoints();
        this.qualify = (FineQualify)session.load(FineQualify.class,fine.getQualify().getResourceBundleId());
        this.cost = fine.getCost();
        this.article = fine.getArticle();
        this.observations = fine.getObservations();
        this.infringementNotice = fine.getInfringementNotice();
        this.speed=fine.getSpeed();
        this.permittedSpeed=fine.getPermittedSpeed();
    }

    
    
    public Integer getPermittedSpeed() {
		return permittedSpeed;
	}

	public void setPermittedSpeed(Integer permittedSpeed) {
		this.permittedSpeed = permittedSpeed;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	/**
	 * @return the infringementNotice
	 */
	public String getInfringementNotice() {
		return infringementNotice;
	}

	/**
	 * @param infringementNotice the infringementNotice to set
	 */
	public void setInfringementNotice(String infringementNotice) {
		this.infringementNotice = infringementNotice;
	}

	/**
     * @return Returns the cost.
     */
    public Integer getCost() {
        return cost;
    }


    /**
     * @return Returns the fineType.
     */
    public FineType getType() {
        return type;
    }


    /**
     * @return Returns the observations.
     */
    public String getObservations() {
        return observations;
    }


    /**
     * @return Returns the qualify.
     */
    public FineQualify getQualify() {
        return qualify;
    }


    /**
     * @param cost The cost to set.
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }


    /**
     * @param fineType The fineType to set.
     */
    public void settype(FineType type) {
        this.type = type;
    }


    /**
     * @param observations The observations to set.
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }


    /**
     * @param qualify The qualify to set.
     */
    public void setQualify(FineQualify qualify) {
        this.qualify = qualify;
    }

    public FineDataType getFineData() {
        FineDataType fine = new FineDataType();
        fine.setId(id);
        fine.setPoints(points);
        fine.setCorporation(corpCountry.getPk().getCorporation().getData());
        
        GeoLabelAttributes[] geoAttrs = fine.getCorporation().getGeoLabels();
        GeoItemData countryData = (GeoItemData)corpCountry.getData();
        Collection countries = new LinkedList();
        countries.add(countryData);
        GeoLabelData labelData = null; 
        if(geoLevel1 != null) {
            GeoItemData level1 = (GeoItemData)geoLevel1.getData();
            Collection level1s = new LinkedList();
            level1s.add(level1);
            if(geoLevel1.getGeoLevel2() == null) {
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
        fine.setCountry(labelData);
        
        Collection divOrgs = new LinkedList();
        if(divOrg1 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg1,fine.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg2 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg2,fine.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg3 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg3,fine.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        
        fine.setDriver(driver.getDriverData());
        
        fine.setDivOrgs(divOrgs);
        
        if(vehicle != null) {
            fine.setVehicle(vehicle.getVehicleData()); 
        }else {
            fine.setPlate(plate);
            fine.setModel(model);
            fine.setTrade(trade);
            fine.setYear(year);
        }
        fine.setState(state);
        fine.setStatus(status);
        fine.setCity(city);
        
        fine.setDate(date);
        fine.setHour(hour);
        fine.setExpiry(expiry);
        fine.setFineType((FineTypeDataType)type.getData());
        fine.setPoints(points);
        fine.setQualify((FineQualifyDataType)qualify.getData());
        fine.setCost(cost);
        fine.setArticle(article);
        
        fine.setObservations(observations);
        
        fine.setNonrecordable(nonrecordable);
        fine.setSuperivisionObservations(supervisionObservations);
        fine.setInfringementNotice(infringementNotice);
        fine.setLote(lote);
        fine.setSpeed(speed);
        fine.setPermittedSpeed(permittedSpeed);
        
        return fine;
    }
    
    public EventMainAttributes getMainAttributes() {
        EventMainAttributes main = super.getMainAttributes();
        main.setType(EventDataType.FINE);
        return main;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    } 

    public EventDataType getEventData() {
        EventDataType event = new EventDataType();
        event.setId(id);
        event.setPoints(points);
        event.setStatus(status);
        event.setType(EventDataType.FINE);
        event.setCorporation(corpCountry.getPk().getCorporation().getData());
        //add 2 lines
        event.setStory(observations);
        event.setIncident(new IncidentClasificationDataType(type.getResourceBundleId()));
        event.setAccident(new AccidentClasificationDataType(qualify.getResourceBundleId()));
        GeoLabelAttributes[] geoAttrs = event.getCorporation().getGeoLabels();
        GeoItemData countryData = (GeoItemData)corpCountry.getData();
        Collection countries = new LinkedList();
        countries.add(countryData);
        GeoLabelData labelData = null; 
        if(geoLevel1 != null) {
            GeoItemData level1 = (GeoItemData)geoLevel1.getData();
            Collection level1s = new LinkedList();
            level1s.add(level1);
            if(geoLevel1.getGeoLevel2() == null) {
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
        event.setCountry(labelData);
        
        Collection divOrgs = new LinkedList();
        if(divOrg1 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg1,event.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg2 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg2,event.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg3 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg3,event.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        
        event.setDriver(driver.getDriverData());
        
        event.setDivOrgs(divOrgs);
        
        if(vehicle != null) {
            event.setVehicle(vehicle.getVehicleData()); 
        }else {
            event.setPlate(plate);
            event.setModel(model);
            event.setTrade(trade);
            event.setYear(year);
        }
        event.setState(state);
        event.setCity(city);
        
        event.setDate(date);
        event.setHour(hour);
        event.setLote(lote);
        
        return event;
    }

}
