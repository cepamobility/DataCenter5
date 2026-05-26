package communication.vehicle.tables;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VehicleDataText implements Comparable ,Serializable {

	private String id;
	private String plate;
	private String country;
	
	private String divOrg1, divOrg2, 
		divOrg3, divOrg4, divOrg5, 
		divOrg6, divOrg7, divOrg8, 
		divOrg9;
	
	private String type;
	private String trade;
	private String model;
	private String year;
	private String colour;
	private String permissionNumber;
	private String use;
	private String permissionExpiry;
	private String suctaExpiry;
	private String mtopExpiry;
	private String ownerCard;
	private String ensuranceType;
	private String ensurance;
	private String observations;
	private String fuel;
	private String purchaseDate;
	private String chasisNumber;
	private String axis;
	private String engine;
	private String hp;
	private String tankCapacity;
	private String tankUnit;
	private String abs;
	private String airBag;
	private String aireAcondicionado;
	private String suspension;
	private String cover;
	private String odometerUnit;
	private String driverFirstName;
	private String driverLastName;
    private String prefix;
	private Integer odometer;
	private String wwwid; // corporationId, id del conductor dentro de la corporacion
	private String centerCost;
	private String monitoringSystem;
	private String renavan;
	private Date exclusionDate;
	
	public VehicleDataText() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param id
	 * @param plate
	 * @param country
	 * @param divOrg1
	 * @param divOrg2
	 * @param divOrg3
	 * @param type
	 * @param trade
	 * @param model
	 * @param year
	 * @param colour
	 * @param permissionNumber
	 * @param use
	 * @param permissionExpiry
	 * @param suctaExpiry
	 * @param mtopExpiry
	 * @param ownerCard
	 * @param ensuranceType
	 * @param ensurance
	 * @param observations
	 * @param fuel
	 * @param purchaseDate
	 * @param chasisNumber
	 * @param axis
	 * @param engine
	 * @param hp
	 * @param tankCapacity
	 * @param tankUnit
	 * @param abs
	 * @param airBag
	 * @param aireAcondicionado
	 * @param cover
	 * @param odometerUnit
	 * @param driver
	 */
	public VehicleDataText(String id, String plate, String country,
			String[] divOrgs,
			String type, String trade, String model, 
			String year, String colour, String permissionNumber, 
			String use, String permissionExpiry, String suctaExpiry, 
			String mtopExpiry, String ownerCard, String ensuranceType, 
			String ensurance, String observations, String fuel, 
			String purchaseDate, String chasisNumber, String axis, 
			String engine, String hp, String tankCapacity, 
			String tankUnit, String abs, String airBag, 
			String aireAcondicionado, String suspension, 
			String cover, String odometerUnit, 
			String driverFirstName, String driverLastName, String wwwid, Date exclusionDate) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.plate = plate;
		this.country = country;
		
		if(divOrgs.length > 0) {
            divOrg1 = divOrgs[0];
        }
        if(divOrgs.length > 1) {
            divOrg2 = divOrgs[1];
        }
        if(divOrgs.length > 2) {
            divOrg3 = divOrgs[2];
        }
        if(divOrgs.length > 3) {
            divOrg4 = divOrgs[3];
        }
        if(divOrgs.length > 4) {
            divOrg5 = divOrgs[4];
        }
        if(divOrgs.length > 5) {
            divOrg6 = divOrgs[5];
        }
        if(divOrgs.length > 6) {
            divOrg7 = divOrgs[6];
        }
        if(divOrgs.length > 7) {
            divOrg8 = divOrgs[7];
        }
        if(divOrgs.length > 8) {
            divOrg9 = divOrgs[8];
        }
		this.type = type;
		this.trade = trade;
		this.model = model;
		this.year = year;
		this.colour = colour;
		this.permissionNumber = permissionNumber;
		this.use = use;
		this.permissionExpiry = permissionExpiry;
		this.suctaExpiry = suctaExpiry;
		this.mtopExpiry = mtopExpiry;
		this.ownerCard = ownerCard;
		this.ensuranceType = ensuranceType;
		this.ensurance = ensurance;
		this.observations = observations;
		this.fuel = fuel;
		this.purchaseDate = purchaseDate;
		this.chasisNumber = chasisNumber;
		this.axis = axis;
		this.engine = engine;
		this.hp = hp;
		this.tankCapacity = tankCapacity;
		this.tankUnit = tankUnit;
		this.abs = abs;
		this.airBag = airBag;
		this.aireAcondicionado = aireAcondicionado;
		this.cover = cover;
		this.odometerUnit = odometerUnit;
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.wwwid = wwwid;
		this.suspension = suspension;
		this.exclusionDate = exclusionDate;
	}



	public VehicleDataText(Object[] data) {
        id = ((Integer)data[0]).toString();
        plate = (String)data[1];
        country = (String)data[2];
        type = (String)data[3];
        trade = (String)data[4];
        model = (String)data[5];
        year = ((Integer)data[6]).toString();
        colour = (String)data[7];
        permissionNumber = (String)data[8];
        use = (String)data[9];
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy");    
        permissionExpiry = (data[10] != null) ? dateFormat.format((Date)data[10]) : null;
        suctaExpiry = (data[11] != null) ? dateFormat.format(data[11]) : null;
        mtopExpiry = (data[12] != null) ? dateFormat.format(data[12]):null; 
        ownerCard = (String)data[13];
        ensuranceType = (String)data[14];
        ensurance = (String)data[15];
        observations = (String)data[16];
        fuel = (String)data[17];
        purchaseDate = (data[18] != null) ? dateFormat.format(data[18]) : null;
        chasisNumber = (String)data[19];
        axis = (data[20] != null) ? data[20].toString() : null;
        engine = (String)data[21];
        hp = (String)data[22];
        tankCapacity = (data[23] != null) ? data[23].toString() : null;
        tankUnit = (String)data[24];
        abs = (data[25] != null) ? data[25].toString() : null;
        airBag = (data[26] != null) ? data[26].toString() : null;
        aireAcondicionado = (data[27] != null) ? data[27].toString() : null;
        cover = (String)data[28];
        odometerUnit = (String)data[29];
        driverFirstName =  (String)data[30];
        driverLastName = (String)data[31];
        prefix = (String)data[32];
        monitoringSystem = (String)data[33];
        renavan = (String)data[34];
        centerCost = (String)data[35];
        wwwid = (String)data[36];
        suspension = (data[37] != null) ? data[37].toString() : null;
        exclusionDate = (Date)data[38];
        setDivOrgs(39,data);
    }

	public void setDivOrgs(int i, Object[] data) {
        if(data.length > i) {
            divOrg1 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg2 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg3 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg4 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg5 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg6 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg7 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg8 = (String) data[i];
        }
        i++;
        if(data.length > i) {
            divOrg9 = (String) data[i];
        }
	}

    /**
	 * @return Returns the abs.
	 */
	public String getAbs() {
		return abs;
	}

	/**
	 * @param abs The abs to set.
	 */
	public void setAbs(String abs) {
		this.abs = abs;
	}

	/**
	 * @return Returns the airBag.
	 */
	public String getAirBag() {
		return airBag;
	}

	/**
	 * @param airBag The airBag to set.
	 */
	public void setAirBag(String airBag) {
		this.airBag = airBag;
	}

	/**
	 * @return Returns the aireAcondicionado.
	 */
	public String getAireAcondicionado() {
		return aireAcondicionado;
	}

	/**
	 * @param aireAcondicionado The aireAcondicionado to set.
	 */
	public void setAireAcondicionado(String aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	/**
	 * @return Returns the axis.
	 */
	public String getAxis() {
		return axis;
	}

	/**
	 * @param axis The axis to set.
	 */
	public void setAxis(String axis) {
		this.axis = axis;
	}

	/**
	 * @return Returns the chasisNumber.
	 */
	public String getChasisNumber() {
		return chasisNumber;
	}

	/**
	 * @param chasisNumber The chasisNumber to set.
	 */
	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	/**
	 * @return Returns the colour.
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour The colour to set.
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Returns the cover.
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover The cover to set.
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	

	/**
	 * @return Returns the driver.
	 */
	public String getDriverFirstName() {
		return driverFirstName;
	}

	/**
	 * @return Returns the driver.
	 */
	public String getDriverLastName() {
		return driverLastName;
	}

	/**
	 * @param driver The driver to set.
	 */
	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}

	/**
	 * @param driver The driver to set.
	 */
	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}

	/**
	 * @return Returns the engine.
	 */
	public String getEngine() {
		return engine;
	}

	/**
	 * @param engine The engine to set.
	 */
	public void setEngine(String engine) {
		this.engine = engine;
	}

	/**
	 * @return Returns the ensurance.
	 */
	public String getEnsurance() {
		return ensurance;
	}

	/**
	 * @param ensurance The ensurance to set.
	 */
	public void setEnsurance(String ensurance) {
		this.ensurance = ensurance;
	}

	/**
	 * @return Returns the ensuranceType.
	 */
	public String getEnsuranceType() {
		return ensuranceType;
	}

	/**
	 * @param ensuranceType The ensuranceType to set.
	 */
	public void setEnsuranceType(String ensuranceType) {
		this.ensuranceType = ensuranceType;
	}

	/**
	 * @return Returns the fuel.
	 */
	public String getFuel() {
		return fuel;
	}

	/**
	 * @param fuel The fuel to set.
	 */
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	/**
	 * @return Returns the hp.
	 */
	public String getHp() {
		return hp;
	}

	/**
	 * @param hp The hp to set.
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the model.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model The model to set.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return Returns the mtopExpiry.
	 */
	public String getMtopExpiry() {
		return mtopExpiry;
	}

	/**
	 * @param mtopExpiry The mtopExpiry to set.
	 */
	public void setMtopExpiry(String mtopExpiry) {
		this.mtopExpiry = mtopExpiry;
	}

	/**
	 * @return Returns the observations.
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations The observations to set.
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return Returns the odometerUnit.
	 */
	public String getOdometerUnit() {
		return odometerUnit;
	}

	/**
	 * @param odometerUnit The odometerUnit to set.
	 */
	public void setOdometerUnit(String odometerUnit) {
		this.odometerUnit = odometerUnit;
	}

	/**
	 * @return Returns the ownerCard.
	 */
	public String getOwnerCard() {
		return ownerCard;
	}

	/**
	 * @param ownerCard The ownerCard to set.
	 */
	public void setOwnerCard(String ownerCard) {
		this.ownerCard = ownerCard;
	}

	/**
	 * @return Returns the permissionExpiry.
	 */
	public String getPermissionExpiry() {
		return permissionExpiry;
	}

	/**
	 * @param permissionExpiry The permissionExpiry to set.
	 */
	public void setPermissionExpiry(String permissionExpiry) {
		this.permissionExpiry = permissionExpiry;
	}

	/**
	 * @return Returns the permissionNumber.
	 */
	public String getPermissionNumber() {
		return permissionNumber;
	}

	/**
	 * @param permissionNumber The permissionNumber to set.
	 */
	public void setPermissionNumber(String permissionNumber) {
		this.permissionNumber = permissionNumber;
	}

	/**
	 * @return Returns the plate.
	 */
	public String getPlate() {
		return plate;
	}

	/**
	 * @param plate The plate to set.
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}

	/**
	 * @return Returns the purchaseDate.
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate The purchaseDate to set.
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return Returns the suctaExpiry.
	 */
	public String getSuctaExpiry() {
		return suctaExpiry;
	}

	/**
	 * @param suctaExpiry The suctaExpiry to set.
	 */
	public void setSuctaExpiry(String suctaExpiry) {
		this.suctaExpiry = suctaExpiry;
	}

	/**
	 * @return Returns the tankCapacity.
	 */
	public String getTankCapacity() {
		return tankCapacity;
	}

	/**
	 * @param tankCapacity The tankCapacity to set.
	 */
	public void setTankCapacity(String tankCapacity) {
		this.tankCapacity = tankCapacity;
	}

	/**
	 * @return Returns the tankUnit.
	 */
	public String getTankUnit() {
		return tankUnit;
	}

	/**
	 * @param tankUnit The tankUnit to set.
	 */
	public void setTankUnit(String tankUnit) {
		this.tankUnit = tankUnit;
	}

	/**
	 * @return Returns the trade.
	 */
	public String getTrade() {
		return trade;
	}

	/**
	 * @param trade The trade to set.
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the use.
	 */
	public String getUse() {
		return use;
	}

	/**
	 * @param use The use to set.
	 */
	public void setUse(String use) {
		this.use = use;
	}

	/**
	 * @return Returns the year.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	 public int compareTo(Object arg0) {
	        return plate.compareTo(((VehicleDataText)arg0).plate);
	 }



	/**
	 * @return Returns the divOrg1.
	 */
	public String getDivOrg1() {
		return divOrg1;
	}



	/**
	 * @param divOrg1 The divOrg1 to set.
	 */
	public void setDivOrg1(String divOrg1) {
		this.divOrg1 = divOrg1;
	}



	/**
	 * @return Returns the divOrg2.
	 */
	public String getDivOrg2() {
		return divOrg2;
	}



	/**
	 * @param divOrg2 The divOrg2 to set.
	 */
	public void setDivOrg2(String divOrg2) {
		this.divOrg2 = divOrg2;
	}



	/**
	 * @return Returns the divOrg3.
	 */
	public String getDivOrg3() {
		return divOrg3;
	}



	/**
	 * @param divOrg3 The divOrg3 to set.
	 */
	public void setDivOrg3(String divOrg3) {
		this.divOrg3 = divOrg3;
	}



	/**
	 * @return Returns the divOrg4.
	 */
	public String getDivOrg4() {
		return divOrg4;
	}



	/**
	 * @param divOrg4 The divOrg4 to set.
	 */
	public void setDivOrg4(String divOrg4) {
		this.divOrg4 = divOrg4;
	}



	/**
	 * @return Returns the divOrg5.
	 */
	public String getDivOrg5() {
		return divOrg5;
	}



	/**
	 * @param divOrg5 The divOrg5 to set.
	 */
	public void setDivOrg5(String divOrg5) {
		this.divOrg5 = divOrg5;
	}



	/**
	 * @return Returns the divOrg6.
	 */
	public String getDivOrg6() {
		return divOrg6;
	}



	/**
	 * @param divOrg6 The divOrg6 to set.
	 */
	public void setDivOrg6(String divOrg6) {
		this.divOrg6 = divOrg6;
	}



	/**
	 * @return Returns the divOrg7.
	 */
	public String getDivOrg7() {
		return divOrg7;
	}



	/**
	 * @param divOrg7 The divOrg7 to set.
	 */
	public void setDivOrg7(String divOrg7) {
		this.divOrg7 = divOrg7;
	}



	/**
	 * @return Returns the divOrg8.
	 */
	public String getDivOrg8() {
		return divOrg8;
	}



	/**
	 * @param divOrg8 The divOrg8 to set.
	 */
	public void setDivOrg8(String divOrg8) {
		this.divOrg8 = divOrg8;
	}



	/**
	 * @return Returns the divOrg9.
	 */
	public String getDivOrg9() {
		return divOrg9;
	}



	/**
	 * @param divOrg9 The divOrg9 to set.
	 */
	public void setDivOrg9(String divOrg9) {
		this.divOrg9 = divOrg9;
	}



    public String getPrefix() {
        return prefix;
    }



    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }



	public Integer getOdometer() {
		return odometer;
	}

	public void setOdometer(Integer odometer) {
		this.odometer = odometer;
	}



	/**
	 * @return the wwwid
	 */
	public String getWwwid() {
		return wwwid;
	}



	/**
	 * @param wwwid the wwwid to set
	 */
	public void setWwwid(String wwwid) {
		this.wwwid = wwwid;
	}



	public String getCenterCost() {
		return centerCost;
	}



	public void setCenterCost(String centerCost) {
		this.centerCost = centerCost;
	}



	public String getMonitoringSystem() {
		return monitoringSystem;
	}



	public void setMonitoringSystem(String monitoringSystem) {
		this.monitoringSystem = monitoringSystem;
	}



	public String getRenavan() {
		return renavan;
	}



	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}



	public String getSuspension() {
		return suspension;
	}



	public void setSuspension(String suspension) {
		this.suspension = suspension;
	}



	public Date getExclusionDate() {
		return exclusionDate;
	}



	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}
	
	
}
