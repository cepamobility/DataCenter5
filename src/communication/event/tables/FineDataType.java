package communication.event.tables;


import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.util.Util;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.FineTypeDataType;
import communication.driver.tables.DriverData;
import communication.vehicle.tables.VehicleData;

public class FineDataType extends EventMainAttributes implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CorporationData corporation;  
    private GeoLabelData country;  
    private Collection divOrgs;
    
    private Time hour;
    
    private String city;

    private FineTypeDataType fineType;
    // private String points;
    private FineQualifyDataType qualify;
    private Integer cost;
    private String article;

    private String observations;
    private String working;
    private String state;
    private String superivisionObservations;
    private String infringementNotice;
    
    private Integer permittedSpeed;
	private Integer speed;
    
    public FineDataType() {
        super();
    }

    public FineDataType(Integer id, DriverData driver, Integer status,
            CorporationData corporation, GeoLabelData country,
            Collection divOrgs, String city, String state, VehicleData vehicle, String plate,
            String trade, String model, String year, Date date, Time hour,
            FineTypeDataType fineType, Integer points, FineQualifyDataType qualify, Integer cost,String article,
            String observations, String working, String superivisionObservations,
            String infringementNotice, Date lote, String economicNumber, Date expiry) {
        super(id, driver, date, status, vehicle, plate, trade, model, year, lote, points,economicNumber,expiry);
        this.country = country;
        this.corporation = corporation;
        this.divOrgs = divOrgs;
        this.state = state;
        this.city = city;
        this.hour = hour;
        this.fineType = fineType;
        // this.points = points;
        this.qualify = qualify;
        this.cost = cost;
        this.article = article;
        this.observations = observations;
        this.working = working;
        this.superivisionObservations = superivisionObservations;
        this.infringementNotice = infringementNotice;
    }

    
    public FineDataType(Integer id, DriverData driver, Integer status,
            CorporationData corporation, GeoLabelData country,
            Collection divOrgs, String city, String state, VehicleData vehicle, String plate,
            String trade, String model, String year, Date date, Time hour,
            FineTypeDataType fineType, Integer points, FineQualifyDataType qualify, Integer cost,String article,
            String observations, String working, String superivisionObservations,
            String infringementNotice, Date lote, String economicNumber, Date expiry, Integer speed, Integer permittedSpeed) {
        super(id, driver, date, status, vehicle, plate, trade, model, year, lote, points,economicNumber,expiry);
        this.country = country;
        this.corporation = corporation;
        this.divOrgs = divOrgs;
        this.state = state;
        this.city = city;
        this.hour = hour;
        this.fineType = fineType;
        // this.points = points;
        this.qualify = qualify;
        this.cost = cost;
        this.article = article;
        this.observations = observations;
        this.working = working;
        this.superivisionObservations = superivisionObservations;
        this.infringementNotice = infringementNotice;
        this.speed=speed;
    	this.permittedSpeed=permittedSpeed;
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
     * @return Returns the cost.
     */
    public Integer getCost() {
        return cost;
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
    public FineQualifyDataType getQualify() {
        return qualify;
    }

    /**
     * @return Returns the type.
     */
    public FineTypeDataType getFineType() {
        return fineType;
    }

    /**
     * @param cost The cost to set.
     */
    public void setCost(Integer cost) {
        this.cost = cost;
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
    public void setQualify(FineQualifyDataType qualify) {
        this.qualify = qualify;
    }

    

    public boolean isFine() {
        return true;
    }

    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * @return Returns the corporation.
     */
    public CorporationData getCorporation() {
        return corporation;
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
    public Collection getDivOrgs() {
        return divOrgs;
    }

    /**
     * @return Returns the hour.
     */
    public Time getHour() {
        return hour;
    }

    /**
     * @param city The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param corporation The corporation to set.
     */
    public void setCorporation(CorporationData corporation) {
        this.corporation = corporation;
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
    public void setDivOrgs(Collection divOrgs) {
        this.divOrgs = divOrgs;
    }

    /**
     * @param hour The hour to set.
     */
    public void setHour(Time hour) {
        this.hour = hour;
    }

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

    public void setFineType(FineTypeDataType type) {
        fineType = type;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSuperivisionObservations() {
        return superivisionObservations;
    }

    public void setSuperivisionObservations(String superivisionObservations) {
        this.superivisionObservations = superivisionObservations;
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

	public int getAge() {
		return Util.getAge(date);
	}
    
    

}
