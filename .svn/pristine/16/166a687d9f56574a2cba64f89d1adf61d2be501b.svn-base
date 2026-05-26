/**
 * Created on 26-abr-2006
 * CEPA
 * DataCenter 5
 */
package communication.event.tables;

import java.io.Serializable;
import java.util.Date;

import communication.driver.tables.DriverData;
import communication.vehicle.tables.VehicleData;

public class EventMainAttributes implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int PENDING_SUPERVISION = 1;
    public static final int SUPERVISED = 2; 
    public static final int PENDING_RECOMENDATION = 3;

    protected Integer id;
    protected DriverData driver;
    protected Date date;
    protected int type;
    protected Integer status;
    
    protected VehicleData vehicle;
    protected String plate;
    protected String economicNumber; // Pepsico
    protected String trade;
    protected String model;
    protected String year;
    protected boolean companycar;
    protected boolean nonrecordable;
    
    protected Date lote; // Agregado para Allergan
    protected Integer points;
    
	private Date expiry;

    public EventMainAttributes() {
    }
    
    /**
     * @param driver
     * @param date
     * @param expiry 
     */
    public EventMainAttributes(Integer id,DriverData driver, Date date, Integer status, 
            VehicleData vehicle, String plate, String trade, String model, String year, Date lote,
            Integer points, String economicNumber, Date expiry) {
        super();
        this.id = id;
        this.driver = driver;
        this.date = date;
        this.status = status;
        this.vehicle = vehicle;
        this.plate = plate;
        this.trade = trade;
        this.model = model;
        this.year = year;
        this.lote = lote;
        if (points == null){ 
        	this.points = 0;
        }else{
        	this.points = points;
        }
        this.economicNumber = economicNumber;
        this.expiry = expiry;
    }

    public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
     * @return Returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Returns the driver.
     */
    public DriverData getDriver() {
        return driver;
    }

    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
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
    public VehicleData getVehicle() {
        return vehicle;
    }

    /**
     * @return Returns the year.
     */
    public String getYear() {
        return year;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param driver The driver to set.
     */
    public void setDriver(DriverData driver) {
        this.driver = driver;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return Returns the type.
     */
    public int getType() {
        return type;
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
    public void setVehicle(VehicleData vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @param year The year to set.
     */
    public void setYear(String year) {
        this.year = year;
    }
        
    public void setCompanyCar(boolean companycar) {
        this.companycar = companycar;
    }

    public boolean getCompanyCar() {
        return companycar;
    }
    
    public void setNonrecordable(boolean nonrecordable) {
        this.nonrecordable = nonrecordable;
    }
    
    public boolean getNonrecordable() {
        return nonrecordable;
    }

	/**
	 * @return the companycar
	 */
	public boolean isCompanycar() {
		return companycar;
	}

	/**
	 * @param companycar the companycar to set
	 */
	public void setCompanycar(boolean companycar) {
		this.companycar = companycar;
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
