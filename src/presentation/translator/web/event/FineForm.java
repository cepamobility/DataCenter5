package presentation.translator.web.event;

import java.sql.Time;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.event.tables.FineDataType;

/**
 * @author jrodriguez
 */
public class FineForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String id;
	private String driverid;

    private String state;
    private String city;
    
    private String vehicleId;

    private String vehicleplate;
    private String vehicletrade;
    private String vehiclemodel;
    private String vehicleyear;
    private String vehicleeconomicnumber; // pepsico
	
    private String date;
	private String month;
	private String year;

    private String nodatahour;  
    private String hour;  
    private String minute;

    private String type;

    private String fineType;
    private String points;
    private String qualify;
    private String cost;

    private String observations;
    
    private String status;
    private String action;
    
    private String working;

    private String article;
    
    private String supervisionObservations;
    
    private String infringementNotice;
    
    // campos añadidos para Allergan
    private String loteMonth;
    private String loteYear;
    
    private String expiryDate;
	private String expiryMonth;
	private String expiryYear;
	
	private Integer speed=0;
	private Integer speedPermitted=0;
    

    public FineForm() {
    }

    public FineForm(FineDataType fine) {
        GeoItemData geoItem1 = (GeoItemData)fine.getCountry().getItemIterator().next();
        setCountry1(String.valueOf(geoItem1.getId()));
        if(geoItem1.getSubItems() != null) {
            GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
            setCountry2(String.valueOf(geoItem2.getId()));
            if(geoItem2.getSubItems() != null) {
                GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
                setCountry3(String.valueOf(geoItem3.getId()));
            }
        }

        Collections.sort((List) fine.getDivOrgs());
        Iterator it = fine.getDivOrgs().iterator();
        int i = 1;
        while(it.hasNext()) {
            DivOrgData divOrgData = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
            setDivOrg(String.valueOf(item1.getId()),i,1);
            if(item1.getSubItems() != null) {
                ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
                setDivOrg(String.valueOf(item2.getId()),i,2);
                if(item2.getSubItems() != null) {
                    ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
                    setDivOrg(String.valueOf(item3.getId()),i,3);
                }
            }
            i++;
        }
        this.id = String.valueOf(fine.getId());
        this.driverid = String.valueOf(fine.getDriver().getId());

        if(fine.getVehicle() != null) {
            this.vehicleId = String.valueOf(fine.getVehicle().getId());
        }else {
            this.vehicleplate = fine.getPlate();
            this.vehicletrade = fine.getTrade();
            this.vehiclemodel = fine.getModel();
            this.vehicleyear = String.valueOf(fine.getYear());
            this.vehicleeconomicnumber = fine.getEconomicNumber();
        }
        
        this.state = fine.getState();
        this.city = fine.getCity();
        
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(fine.getDate());
        this.date = String.valueOf(calendar.get(Calendar.DATE));
        this.month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        this.year = String.valueOf(calendar.get(Calendar.YEAR));
        
        nodatahour = String.valueOf(fine.getHour() == null);
        if(fine.getHour() != null) {
            calendar.setTime(fine.getHour());
            hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));  
            minute = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        
        this.fineType = fine.getFineType().getResourceBundleId();
        if (fine.getPoints() == null) { 
        	this.points = "0";
        }else{
        	this.points = String.valueOf(fine.getPoints().intValue());	
        }
        
        this.qualify = fine.getQualify().getResourceBundleId();
        this.cost = String.valueOf(fine.getCost());

        this.article = fine.getArticle();
        this.observations = fine.getObservations();
        
        this.status = String.valueOf(fine.getStatus());
        this.working = fine.getWorking();
        
        supervisionObservations = fine.getSuperivisionObservations();
        
        infringementNotice = fine.getInfringementNotice();
        
        if (fine.getLote() != null){
        	Calendar c = Calendar.getInstance();
        	c.setTime(fine.getLote());
        	this.loteMonth = String.valueOf(c.get(Calendar.MONTH)+1); // Enero es 0
        	this.loteYear = String.valueOf(c.get(Calendar.YEAR));
        }else{
        	this.loteMonth = null;
        	this.loteYear = null;
        }
        
        if (fine.getExpiry() != null){
        	Calendar c = Calendar.getInstance();
        	c.setTime(fine.getExpiry());
            this.expiryDate = String.valueOf(calendar.get(Calendar.DATE));
        	this.expiryMonth = String.valueOf(c.get(Calendar.MONTH)+1); // Enero es 0
        	this.expiryYear = String.valueOf(c.get(Calendar.YEAR));
        }else{
            this.expiryDate = null;
        	this.expiryMonth = null;
        	this.expiryYear = null;
        }
        
        this.speed=fine.getSpeed();
        this.speedPermitted=fine.getPermittedSpeed();
        
    }
    /**
     * @param driverid
     */
    public FineForm(DriverData driver) {
        this.driverid = String.valueOf(driver.getId());
        
        GeoItemData geoItem1 = (GeoItemData)driver.getCountry().getItemIterator().next();
        setCountry1(String.valueOf(geoItem1.getId()));
        if(geoItem1.getSubItems() != null) {
            GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
            setCountry2(String.valueOf(geoItem2.getId()));
            if(geoItem2.getSubItems() != null) {
                GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
                setCountry3(String.valueOf(geoItem3.getId()));
            }
        }

        Iterator it = driver.getDivOrgsIterator();
        int i = 1;
        while(it.hasNext()) {
            DivOrgData divOrgData = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
            setDivOrg(String.valueOf(item1.getId()),i,1);
            if(item1.getSubItems() != null) {
                ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
                setDivOrg(String.valueOf(item2.getId()),i,2);
                if(item2.getSubItems() != null) {
                    ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
                    setDivOrg(String.valueOf(item3.getId()),i,3);
                }
            }
            i++;
        }
    }

    
    
    public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getSpeedPermitted() {
		return speedPermitted;
	}

	public void setSpeedPermitted(Integer speedPermitted) {
		this.speedPermitted = speedPermitted;
	}

	/**
     * @return Returns the cost.
     */
    public String getCost() {
        return cost;
    }

    /**
     * @return Returns the date.
     */
    public String getDate() {
        return date;
    }

    /**
     * @return Returns the driverid.
     */
    public String getDriverid() {
        return driverid;
    }

    /**
     * @return Returns the hour.
     */
    public String getHour() {
        return hour;
    }

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @return Returns the minute.
     */
    public String getMinute() {
        return minute;
    }

    /**
     * @return Returns the month.
     */
    public String getMonth() {
        return month;
    }


    /**
     * @return Returns the observations.
     */
    public String getObservations() {
        return observations;
    }


    /**
     * @return Returns the points.
     */
    public String getPoints() {
        return points;
    }


    /**
     * @return Returns the qualify.
     */
    public String getQualify() {
        return qualify;
    }


    /**
     * @return Returns the type.
     */
    public String getFineType() {
        return fineType;
    }


    /**
     * @return Returns the vehicleId.
     */
    public String getVehicleId() {
        return vehicleId;
    }


    /**
     * @return Returns the vehiclemodel.
     */
    public String getVehiclemodel() {
        return vehiclemodel;
    }


    /**
     * @return Returns the vehicleplate.
     */
    public String getVehicleplate() {
        return vehicleplate;
    }


    /**
     * @return Returns the vehicletrade.
     */
    public String getVehicletrade() {
        return vehicletrade;
    }


    /**
     * @return Returns the vehicleyear.
     */
    public String getVehicleyear() {
        return vehicleyear;
    }


    /**
     * @return Returns the year.
     */
    public String getYear() {
        return year;
    }


    /**
     * @param cost The cost to set.
     */
    public void setCost(String cost) {
        this.cost = cost;
    }


    /**
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * @param driverid The driverid to set.
     */
    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }


    /**
     * @param hour The hour to set.
     */
    public void setHour(String hour) {
        this.hour = hour;
    }


    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * @param minute The minute to set.
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }


    /**
     * @param month The month to set.
     */
    public void setMonth(String month) {
        this.month = month;
    }


    /**
     * @param observations The observations to set.
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }


    /**
     * @param points The points to set.
     */
    public void setPoints(String points) {
        this.points = points.trim();
    }


    /**
     * @param qualify The qualify to set.
     */
    public void setQualify(String qualify) {
        this.qualify = qualify;
    }


    /**
     * @param fineType The fineType to set.
     */
    public void setFineType(String fineType) {
        this.fineType = fineType;
    }


    /**
     * @param vehicleId The vehicleId to set.
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }


    /**
     * @param vehiclemodel The vehiclemodel to set.
     */
    public void setVehiclemodel(String vehiclemodel) {
        this.vehiclemodel = vehiclemodel;
    }


    /**
     * @param vehicleplate The vehicleplate to set.
     */
    public void setVehicleplate(String vehicleplate) {
        this.vehicleplate = vehicleplate;
    }


    /**
     * @param vehicletrade The vehicletrade to set.
     */
    public void setVehicletrade(String vehicletrade) {
        this.vehicletrade = vehicletrade;
    }


    /**
     * @param vehicleyear The vehicleyear to set.
     */
    public void setVehicleyear(String vehicleyear) {
        this.vehicleyear = vehicleyear;
    }


    /**
     * @param year The year to set.
     */
    public void setYear(String year) {
        this.year = year;
    }


    // TODO
    public String validate(UserSessionData userRequest) {

        if(Util.empty(driverid) || !Util.isNumber(driverid)) {
            return "generic.event.eventdata.driverInvalid";
        }
        
        for(int j = 0; j < userRequest.getFilter().getDivOrgCount(); j++) {
            for(int i = 1; i <= userRequest.getFilter().getDivOrgLevel(j); i++) {
                if(Util.empty(getDivOrg(j+1,i))) {
                    return "generic.event.eventdata.emptyDivOrg";
                }
            }
        }
        
        if(Util.empty(getCountry1())) {
            return "generic.event.eventdata.emptyDivGeo";
        }
        if(userRequest.getFilter().getGeoLevelCount()>1 && Util.empty(getCountry2())) {
            return "generic.event.eventdata.emptyDivGeo";
        }
        if(userRequest.getFilter().getGeoLevelCount()>2 && Util.empty(getCountry3())) {
            return "generic.event.eventdata.emptyDivGeo";
        }

        if(!Util.empty(vehicleId)) {
            if(!Util.isNumber(vehicleId)) {
                return "generic.event.eventdata.vehicleInvalid";
            }
        }else {
            if(Util.empty(vehicleplate)) {
                return "generic.event.eventdata.vehicle.plateEmpty";                
            }
            if(Util.empty(vehicletrade)) {
                return "generic.event.eventdata.vehicle.tradeEmpty";                
            }
            if(Util.empty(vehiclemodel)) {
                return "generic.event.eventdata.vehicle.modelEmpty";                
            }
            if(Util.empty(vehicleyear)) {
                return "generic.event.eventdata.vehicle.yearEmpty";                
            }
        }
        
        try {
            Date eventDate = Util.getDate(date,month,year);
            if(eventDate == null) {
                return "generic.event.eventdata.dateEmpty";
            }
            if(!Util.checkDate(Integer.parseInt(date),Integer.parseInt(month),Integer.parseInt(year))) {
                return "generic.event.eventdata.dateInvalid";
            }
        }catch(Exception e) {
            return "generic.event.eventdata.dateInvalid";
        }
        
        try {
            Date loteDate = Util.getDate("1",loteMonth,loteYear);
            if(loteDate != null && 
            	!Util.checkDate(Integer.parseInt("1"),Integer.parseInt(loteMonth),Integer.parseInt(loteYear))) {
                return "generic.event.eventdata.dateInvalid";
            }
        }catch(Exception e) {
            return "generic.event.eventdata.dateInvalid";
        }
        
        if(!isChecked(nodatahour)) {
            try {
                Time time = Util.getTime(hour,minute);
                if(time == null) {
                    return "generic.event.eventdata.timeEmpty";
                }
                if(!Util.checkHour(Integer.parseInt(hour),Integer.parseInt(minute))) {
                    return "generic.event.eventdata.hourInvalid";
                }
            }catch(Exception e) {
                return "generic.event.eventdata.timeInvalid";
            }
        }

        if(Util.empty(fineType)) {
            return "generic.event.finedata.typeEmpty";                
        }

        if(Util.empty(points)) {
            return "generic.event.finedata.pointsEmpty";                
        }
        if(!Util.isNumber(points)) {
            return "generic.event.finedata.pointsInvalid";                
        }

        if(Util.empty(qualify)) {
            return "generic.event.finedata.qualifyEmpty";                
        }

        if(Util.empty(cost)) {
            return "generic.event.finedata.costEmpty";                
        }
        if(!Util.isNumber(cost)) {
            return "generic.event.finedata.costInvalid";                
        }
        if(speed==null) {
            return "generic.event.fine.speedInvalid";                
        }
        if(speedPermitted==null) {
            return "generic.event.fine.permittedSpeedInvalid";                
        }
        return null;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return Returns the action.
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action The action to set.
     */
    public void setAction(String action) {
        this.action = action;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNodatahour() {
        return nodatahour;
    }

    public void setNodatahour(String nodatahour) {
        this.nodatahour = nodatahour;
    }
    public boolean isChecked(String label) {
        return !Util.empty(label) && label.toLowerCase().equals("on");
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSupervisionObservations() {
        return supervisionObservations;
    }

    public void setSupervisionObservations(String supervisionObservations) {
        this.supervisionObservations = supervisionObservations;
    }

	public void setLoteYear(String loteYear) {
		this.loteYear = loteYear;
	}

	public String getLoteYear() {
		return loteYear;
	}

	public void setLoteMonth(String loteMonth) {
		this.loteMonth = loteMonth;
	}

	public String getLoteMonth() {
		return loteMonth;
	}

	public void setInfringementNotice(String infringementNotice) {
		this.infringementNotice = infringementNotice;
	}

	public String getInfringementNotice() {
		return infringementNotice;
	}

	/**
	 * @return the vehicleeconomicnumber
	 */
	public String getVehicleeconomicnumber() {
		return vehicleeconomicnumber;
	}

	/**
	 * @param vehicleeconomicnumber the vehicleeconomicnumber to set
	 */
	public void setVehicleeconomicnumber(String vehicleeconomicnumber) {
		this.vehicleeconomicnumber = vehicleeconomicnumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
    
    
}
