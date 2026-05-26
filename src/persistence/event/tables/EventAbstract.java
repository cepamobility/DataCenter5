
package persistence.event.tables;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.hibernate.Session;

import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.driver.tables.Driver;
import persistence.vehicle.tables.Vehicle;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;
import communication.event.tables.EventDataType;
import communication.event.tables.EventMainAttributes;
import communication.event.tables.FineDataType;
import communication.vehicle.tables.VehicleData;

public class EventAbstract {
	
    protected Integer id;

    protected Driver driver;

    protected CorporationCountry corpCountry;  
    protected GeoLevel1 geoLevel1; 
    
    protected DivOrgItemLevel1 divOrg1;
    protected DivOrgItemLevel1 divOrg2;
    protected DivOrgItemLevel1 divOrg3;
    
    protected String city;

    protected Vehicle vehicle;
    protected String plate;
    protected String economicNumber; 
    protected String trade;
    protected String model;
    protected String year;
    protected boolean companycar; 
    
    protected Date date;
    protected Date expiry;
	protected Time hour;

    protected String working; 
    
    protected Integer status;

    protected String state;

    protected String evitability;
    protected String recomendation;
    protected boolean nonrecordable;

    protected String supervisionObservations; 
    
   
    protected Date lote;
    protected Integer points = 0;
    
    
	public EventAbstract() {
	}
	
    public EventAbstract(EventDataType eventData, Session session) {
    	this(eventData,session,false);
    }
    
    public EventAbstract(EventDataType eventData, Session session, boolean withDriverData) {
        
        this.id = eventData.getId();
        
        this.points = eventData.getPoints();
        
        this.driver = (Driver)session.load(Driver.class,eventData.getDriver().getId());

        if(withDriverData) {
        	divOrg1 = driver.getDivOrg1();
        	divOrg2 = driver.getDivOrg2();
        	divOrg3 = driver.getDivOrg3();
        	
        	corpCountry = driver.getCorpCountry();
        	geoLevel1 = driver.getGeoLevel1();
        }else {
	        GeoItemData itemData1 = null;
	        GeoItemData itemData2 = null;        
	        if (eventData.getCountry() != null){
	        	itemData1 = (GeoItemData)eventData.getCountry().getItemIterator().next();
	            while(itemData1.getSubItems() != null) {
	            	itemData2 = itemData1;
	            	itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
	            }
	        }
	        
	        Iterator it = eventData.getDivOrgs().iterator();
	        if(it.hasNext()) {
	            DivOrgData divOrg = (DivOrgData)it.next();
	            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	            while(item1.getSubItems() != null) {
	                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	            }
	            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	        }
	        if(it.hasNext()) {
	            DivOrgData divOrg = (DivOrgData)it.next();
	            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	            while(item1.getSubItems() != null) {
	                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	            }
	            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	        }
	        if(it.hasNext()) {
	            DivOrgData divOrg = (DivOrgData)it.next();
	            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	            while(item1.getSubItems() != null) {
	                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	            }
	            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	        }
	        
	        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),eventData.getCorporation().getId()));
	        if(itemData2 != null) {
	            geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
	        }
        }
        
        state = eventData.getState();
        
        this.date = eventData.getDate();
        this.expiry = eventData.getExpiry();
        this.hour = eventData.getHour();
        this.working = eventData.getWorking();
        
        if(eventData.getVehicle() != null) {
            vehicle = (Vehicle)session.load(Vehicle.class,eventData.getVehicle().getId());
        }else {
            plate = eventData.getPlate();
            trade = eventData.getTrade();
            model = eventData.getModel();
            year = eventData.getYear();
            companycar = eventData.getCompanyCar();
            economicNumber = eventData.getEconomicNumber();
        }
        status = eventData.getStatus();
        city = eventData.getCity();
        
        evitability = eventData.getEvitability();
        recomendation = eventData.getRecomendation();
        nonrecordable =eventData.getNonrecordable();
        supervisionObservations = eventData.getSuperivisionObservations();
        
        this.lote = eventData.getLote();
        
    }

    public EventAbstract(FineDataType eventData, Session session) {
    	this(eventData,session,false);
    }
    
    public EventAbstract(FineDataType eventData, Session session, boolean withDriverData) {
        
        this.id = eventData.getId();
        this.points = eventData.getPoints();
        
        this.driver = (Driver)session.load(Driver.class,eventData.getDriver().getId());

        if(withDriverData) {
        	divOrg1 = driver.getDivOrg1();
        	divOrg2 = driver.getDivOrg2();
        	divOrg3 = driver.getDivOrg3();
        	
        	corpCountry = driver.getCorpCountry();
        	geoLevel1 = driver.getGeoLevel1();
        }else {
	        GeoItemData itemData1 = (GeoItemData)eventData.getCountry().getItemIterator().next();
	        GeoItemData itemData2 = null; 
	        while(itemData1.getSubItems() != null) {
	            itemData2 = itemData1;
	            itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
	        }
	        
	        Iterator it = eventData.getDivOrgs().iterator();
	        if(it.hasNext()) {
	            DivOrgData divOrg = (DivOrgData)it.next();
	            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	            while(item1.getSubItems() != null) {
	                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	            }
	            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	        }
	        if(it.hasNext()) {
	            DivOrgData divOrg = (DivOrgData)it.next();
	            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	            while(item1.getSubItems() != null) {
	                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	            }
	            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	        }
	        if(it.hasNext()) {
	            DivOrgData divOrg = (DivOrgData)it.next();
	            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
	            while(item1.getSubItems() != null) {
	                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
	            }
	            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
	        }
	        
	        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),eventData.getCorporation().getId()));
	        if(itemData2 != null) {
	            geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
	        }
        }
        
        state = eventData.getState();
        
        this.date = eventData.getDate();
        this.expiry = eventData.getExpiry();
        this.hour = eventData.getHour();
        this.working = eventData.getWorking();
        if(eventData.getVehicle() != null) {
            vehicle = (Vehicle)session.load(Vehicle.class,eventData.getVehicle().getId());
        }else {
            plate = eventData.getPlate();
            trade = eventData.getTrade();
            model = eventData.getModel();
            year = eventData.getYear();
            companycar = eventData.getCompanyCar();
        }
        status = eventData.getStatus();
        city = eventData.getCity();
        nonrecordable =eventData.getNonrecordable();
        supervisionObservations = eventData.getSuperivisionObservations();
        this.lote = eventData.getLote();
        this.economicNumber = eventData.getEconomicNumber();
    }

    public EventAbstract(EventAbstract event) {
        this.id = event.getId();
        this.points = event.getPoints();
        this.driver = event.getDriver();

        this.divOrg1 = event.getDivOrg1();
        this.divOrg2 = event.getDivOrg2();
        this.divOrg3 = event.getDivOrg3();
        
        this.corpCountry = event.getCorpCountry();
        this.geoLevel1 = event.getGeoLevel1();

        this.date = event.getDate();
        this.expiry = event.getExpiry();
        this.hour = event.getHour();
        this.working = event.getWorking();
        this.vehicle = event.getVehicle();
        this.plate = event.getPlate();
        this.trade = event.getTrade();
        this.model = event.getModel();
        this.year = event.getYear();

        this.status = event.getStatus();
        this.nonrecordable =event.isNonrecordable();
        this.supervisionObservations = event.getSupervisionObservations();
        this.economicNumber = event.getEconomicNumber();
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getHour() {
		return hour;
	}
	
	public void setHour(Time hour) {
		this.hour = hour;
	}
	
    public DivOrgItemLevel1 getDivOrg1() {
        return divOrg1;
    }
                   
    public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
        this.divOrg1 = divOrg1;
    }
    
    public DivOrgItemLevel1 getDivOrg2() {
        return divOrg2;
    }
                   
    public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
        this.divOrg2 = divOrg2;
    }
    
    public DivOrgItemLevel1 getDivOrg3() {
        return divOrg3;
    }
                   
    public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
        this.divOrg3 = divOrg3;
    }
    
	public CorporationCountry getCorpCountry() {
		return corpCountry;
	}
	
	public void setCorpCountry(CorporationCountry corpCountry) {
		this.corpCountry = corpCountry;
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

    public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object arg) {
		if(arg==null){
			return false;
		}
		else{
			EventAbstract ag=(EventAbstract)arg;
	
			if(ag.getDate().equals(this.getDate()) && ag.getHour().equals(this.getHour()))
				{
				return true;
			}
			else{
				return false; 
			}
		}
	}

    /**
     * @return Returns the model.
     */
    public String getModel() {
        return model;
    }

    /**
     * @return Returns the plate.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * @return Returns the trade.
     */
    public String getTrade() {
        return trade;
    }

    /**
     * @return Returns the vehicle.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @return Returns the year.
     */
    public String getYear() {
        return year;
    }

    /**
     * @param model The model to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @param plate The plate to set.
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * @param trade The trade to set.
     */
    public void setTrade(String trade) {
        this.trade = trade;
    }

    /**
     * @param vehicle The vehicle to set.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @param year The year to set.
     */
    public void setYear(String year) {
        this.year = year;
    }

    public EventMainAttributes getMainAttributes() {
        VehicleData vehicleData = null;
        if(vehicle != null) {
            vehicleData = vehicle.getVehicleData(); 
        }
        return new EventMainAttributes(id,driver.getDriverData(),date,status,vehicleData,plate,trade,model,year, lote,points,economicNumber,expiry);
    }

    public EventDataType getEventData() {
        return new EventDataType();
    }
    
    /**
     * @return Returns the status.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @param level1
     * @return
     */
    protected DivOrgData getDivOrgData(DivOrgItemLevel1 level1,CorporationData corporationData) {
        DivOrgData divOrgData = corporationData.getDivOrgData(level1.getDivOrg().getId().intValue());
        LabelItemAttribute[] labelAttrs = divOrgData.getLabels();
        ItemData itemData = new ItemData(level1.getAttributes());
        Collection items1 = new LinkedList();
        items1.add(itemData);
        LabelItemData labelData = null; 
        if(level1.getDivOrgItemLevel2() != null) {
            ItemData item2 = new ItemData(level1.getDivOrgItemLevel2().getAttributes());
            Collection items2 = new LinkedList();
            items2.add(item2);
            if(level1.getDivOrgItemLevel2().getDivOrgItemLevel3() == null) {
                item2.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items1,labelAttrs[1].getShowLevel()));
                labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items2,labelAttrs[0].getShowLevel());
            }else {
                
                item2.setSubItems(new LabelItemData(labelAttrs[2].getId(),labelAttrs[2].getName(),items1,labelAttrs[2].getShowLevel()));
                
                ItemData item3 = new ItemData(level1.getDivOrgItemLevel2().getDivOrgItemLevel3().getAttributes());
                item3.setSubItems(new LabelItemData(labelAttrs[1].getId(),labelAttrs[1].getName(),items2,labelAttrs[1].getShowLevel()));

                Collection items3 = new LinkedList();
                items3.add(item3);
                
                labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items3,labelAttrs[0].getShowLevel());
            }
        }else {
            labelData = new LabelItemData(labelAttrs[0].getId(),labelAttrs[0].getName(),items1,labelAttrs[0].getShowLevel());
        }
        divOrgData.setDivOrgLevels(labelData);
        return divOrgData;
    }

    public void setEventData(EventDataType eventData, Session session) {
        this.id = eventData.getId();
        
        this.driver = (Driver)session.load(Driver.class,eventData.getDriver().getId());

        GeoItemData itemData1 = (GeoItemData)eventData.getCountry().getItemIterator().next();
        GeoItemData itemData2 = null; 
        while(itemData1.getSubItems() != null) {
            itemData2 = itemData1;
            itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
        }
        
        Iterator it = eventData.getDivOrgs().iterator();
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        if(it.hasNext()) {
            DivOrgData divOrg = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrg.getDivOrgLevels().getItemIterator().next();
            while(item1.getSubItems() != null) {
                item1 = (ItemData)item1.getSubItems().getItemIterator().next();
            }
            divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,item1.getId());
        }
        
        corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),eventData.getCorporation().getId()));
        if(itemData2 != null) {
            geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
        }

        this.date = eventData.getDate();
        this.expiry = eventData.getExpiry();
        this.hour = eventData.getHour();
        
        if(eventData.getVehicle() != null) {
            vehicle = (Vehicle)session.load(Vehicle.class,eventData.getVehicle().getId());
        }else {
            plate = eventData.getPlate();
            trade = eventData.getTrade();
            model = eventData.getModel();
            year = eventData.getYear();
            economicNumber = eventData.getEconomicNumber();
        }
        status = eventData.getStatus();
        nonrecordable = eventData.getNonrecordable();
        supervisionObservations = eventData.getSuperivisionObservations();
        lote = eventData.getLote();
        points = eventData.getPoints();
        
    }

    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return Returns the companycar.
     */
    public boolean isCompanycar() {
        return companycar;
    }
    
    /**
     * @return Returns the nonRecordable.
     */
    public boolean isNonrecordable() {
        return nonrecordable;
    }

    /**
     * @param companycar The companycar to set.
     */
    public void setCompanycar(boolean companycar) {
        this.companycar = companycar;
    }

    /**
     * @return Returns the working.
     */
    public String getWorking() {
        return working;
    }

    /**
     * @param working The working to set.
     */
    public void setWorking(String working) {
        this.working = working;
    }
    

    /**
     * @return Returns the unit.
     */
    public String getState() {
        return state;
    }

    /**
     * @param unit The unit to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    public String getEvitability() {
        return evitability;
    }

    public String getRecomendation() {
        return recomendation;
    }

    public void setEvitability(String evitability) {
        this.evitability = evitability;
    }

    public void setRecomendation(String recomendation) {
        this.recomendation = recomendation;
    }
    /**
     * @param nonRecordable The nonRecordable to set.
     */
    public void setNonrecordable(boolean nonrecordable) {
        this.nonrecordable = nonrecordable;
    }

    public String getSupervisionObservations() {
        return supervisionObservations;
    }

    public void setSupervisionObservations(String supervisionObservations) {
        this.supervisionObservations = supervisionObservations;
    }

	/**
	 * @return the lote
	 */
	public Date getLote() {
		return lote;
	}

	/**
	 * @param lote the lote to set
	 */
	public void setLote(Date lote) {
		this.lote = lote;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * @return the economicNumber
	 */
	public String getEconomicNumber() {
		return economicNumber;
	}

	/**
	 * @param economicNumber the economicNumber to set
	 */
	public void setEconomicNumber(String economicNumber) {
		this.economicNumber = economicNumber;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	
	

    
}
