/*
 * Created on 27-oct-2005
 */
package persistence.log.vehicle;

import java.util.Date;

import persistence.vehicle.tables.Vehicle;

import communication.vehicle.tables.VehicleObservationData;

public class VehicleLog {

    private VehicleLogPK pk;
    
    private Integer action;   
    private String comment;    
    
    private Integer country;
    private Integer geoLevel1;     
    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;
    
	private String type;
	private String use;
	private String ownership;
	private String fuel;
	private String engine;
	private String tankUnit;
	private String odometerUnit;
	private String trade;
	private String cover;	
	
	private Integer year;
	private String colour;
	private String permissionNumber;
	private Date permissionExpiry;
	private Date purchaseDate;
	private String chasisNumber;
	private Integer tankCapacity;
	private boolean abs;
	private boolean airBag;  
    
    private String model;      
	private boolean aireAcondicionado;   
    private String matricula;
    
    private Integer driver;    
    
    private Integer corporationId;  
    private Date exclusionDate;
    private Date inclusionDate;
    private String costCenter;
    
    private Date mtopExpiry;
    private Date suctaExpiry;    
    private boolean ownerCard;   
    private String ensurance;
    private Integer axisCount;
    private Integer hp;
    private String ensuranceType;  
    private String observations;
    private Integer optionalInfo;
    
    
    
    

	public VehicleLog() {
		
	}
	
	public VehicleLog(Vehicle vehicle,String user,Date date,int action,String comment) {
		pk = new VehicleLogPK(vehicle.getId(),user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;		
		this.country = vehicle.getCorpCountry().getId();
		this.divOrg1 = (vehicle.getDivOrg1() != null) ? vehicle.getDivOrg1().getId():null;
		this.divOrg2 = (vehicle.getDivOrg2() != null) ? vehicle.getDivOrg2().getId():null;
		this.divOrg3 = (vehicle.getDivOrg3() != null) ? vehicle.getDivOrg3().getId():null;
		this.driver = (vehicle.getDriver() !=null) ? vehicle.getDriver().getId():null;
		this.geoLevel1 = (vehicle.getGeoLevel1() !=null) ? vehicle.getGeoLevel1().getId():null;		
		
		this.abs = vehicle.getAbs();
		this.airBag = vehicle.getAirBag();
		this.aireAcondicionado = vehicle.getAireAcondicionado();
		this.chasisNumber = vehicle.getChasisNumber();
		this.colour = vehicle.getColour();
		if (vehicle.getCover() != null)
			this.cover = vehicle.getCover().getResourceBundleId();
		if (vehicle.getEngine() != null)
			this.engine = vehicle.getEngine().getResourceBundleId();
		if (vehicle.getFuel() != null)
			this.fuel = vehicle.getFuel().getResourceBundleId();
		this.matricula = vehicle.getMatricula();
		this.model = vehicle.getModel();
		if (vehicle.getOdometerUnit() != null)
			this.odometerUnit = vehicle.getOdometerUnit().getResourceBundleId();
		if (vehicle.getOwnership() != null)
			this.ownership = vehicle.getOwnership().getResourceBundleId();
		this.permissionExpiry = vehicle.getPermissionExpiry();
		this.purchaseDate = vehicle.getPurchaseDate();
		this.trade = vehicle.getTrade().getResourceBundleId();		
		this.type = vehicle.getType().getResourceBundleId();			
		this.year = vehicle.getYear();		
		this.permissionNumber = vehicle.getPermissionNumber();
		this.tankCapacity = vehicle.getTankCapacity();
		
		if (vehicle.getCover() != null)
			this.cover = vehicle.getCover().getResourceBundleId();
		if (vehicle.getTankUnit() != null)
			this.tankUnit = vehicle.getTankUnit().getResourceBundleId();		
		
		this.use = vehicle.getUse().getResourceBundleId();		
        this.corporationId = vehicle.getCorporationId();
        this.inclusionDate = vehicle.getInclusionDate();
        this.exclusionDate = vehicle.getExclusionDate();
        this.costCenter = vehicle.getCostCenter();
        
        
        
        mtopExpiry = vehicle.getMtopExpiry();
        suctaExpiry = vehicle.getSuctaExpiry();
        ownerCard = vehicle.isOwnerCard();
        ensurance = vehicle.getEnsurance();
        axisCount = vehicle.getAxisCount();
        hp = vehicle.getHp();
        ensuranceType = (vehicle.getEnsuranceType()!=null)?vehicle.getEnsuranceType().getResourceBundleId():null;
        observations = vehicle.getObservations();
        if (vehicle.getOptionalInfo() != null){
        	optionalInfo = vehicle.getOptionalInfo().getId();
        }else{
        	optionalInfo = null;
        }
	}
	
    /**
     * @return Returns the pk.
     */
    public VehicleLogPK getPk() {
        return pk;
    }
    /**
     * @return Returns the action.
     */
    public Integer getAction() {
        return action;
    }

	/**
     * @param pk The pk to set.
     */
    public void setPk(VehicleLogPK pk) {
        this.pk = pk;
    }

    /**
     * @param action The action to set.
     */
    public void setAction(Integer action) {
        this.action = action;
    }

	/**
	 * @return Returns the abs.
	 */
	public boolean isAbs() {
		return abs;
	}

	/**
	 * @param abs The abs to set.
	 */
	public void setAbs(boolean abs) {
		this.abs = abs;
	}

	/**
	 * @return Returns the airBag.
	 */
	public boolean isAirBag() {
		return airBag;
	}

	/**
	 * @param airBag The airBag to set.
	 */
	public void setAirBag(boolean airBag) {
		this.airBag = airBag;
	}

	/**
	 * @return Returns the aireAcondicionado.
	 */
	public boolean isAireAcondicionado() {
		return aireAcondicionado;
	}

	/**
	 * @param aireAcondicionado The aireAcondicionado to set.
	 */
	public void setAireAcondicionado(boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
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
	public Integer getCountry() {
		return country;
	}

	/**
	 * @param country The country to set.
	 */
	public void setCountry(Integer country) {
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
	 * @return Returns the divOrg1.
	 */
	public Integer getDivOrg1() {
		return divOrg1;
	}

	/**
	 * @param divOrg1 The divOrg1 to set.
	 */
	public void setDivOrg1(Integer divOrg1) {
		this.divOrg1 = divOrg1;
	}

	/**
	 * @return Returns the divOrg2.
	 */
	public Integer getDivOrg2() {
		return divOrg2;
	}

	/**
	 * @param divOrg2 The divOrg2 to set.
	 */
	public void setDivOrg2(Integer divOrg2) {
		this.divOrg2 = divOrg2;
	}

	/**
	 * @return Returns the divOrg3.
	 */
	public Integer getDivOrg3() {
		return divOrg3;
	}

	/**
	 * @param divOrg3 The divOrg3 to set.
	 */
	public void setDivOrg3(Integer divOrg3) {
		this.divOrg3 = divOrg3;
	}

	/**
	 * @return Returns the driver.
	 */
	public Integer getDriver() {
		return driver;
	}

	/**
	 * @param driver The driver to set.
	 */
	public void setDriver(Integer driver) {
		this.driver = driver;
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
	 * @return Returns the geoLevel1.
	 */
	public Integer getGeoLevel1() {
		return geoLevel1;
	}

	/**
	 * @param geoLevel1 The geoLevel1 to set.
	 */
	public void setGeoLevel1(Integer geoLevel1) {
		this.geoLevel1 = geoLevel1;
	}

	/**
	 * @return Returns the matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula The matricula to set.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	 * @return Returns the ownership.
	 */
	public String getOwnership() {
		return ownership;
	}

	/**
	 * @param ownership The ownership to set.
	 */
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	/**
	 * @return Returns the permissionExpiry.
	 */
	public Date getPermissionExpiry() {
		return permissionExpiry;
	}

	/**
	 * @param permissionExpiry The permissionExpiry to set.
	 */
	public void setPermissionExpiry(Date permissionExpiry) {
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
	 * @return Returns the purchaseDate.
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate The purchaseDate to set.
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return Returns the tankCapacity.
	 */
	public Integer getTankCapacity() {
		return tankCapacity;
	}

	/**
	 * @param tankCapacity The tankCapacity to set.
	 */
	public void setTankCapacity(Integer tankCapacity) {
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
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year The year to set.
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return Returns the corporationId.
	 */
	public Integer getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId The corporationId to set.
	 */
	public void setCorporationId(Integer corporationId) {
		this.corporationId = corporationId;
	}

	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
    public VehicleObservationData getVehicleObservations() {
        return new VehicleObservationData(pk.getVehicle(),pk.getDate(),pk.getUserId(),action,comment);
    }

	/**
	 * @return Returns the costCenter.
	 */
	public String getCostCenter() {
		return costCenter;
	}

	/**
	 * @param costCenter The costCenter to set.
	 */
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	/**
	 * @return Returns the exclusionDate.
	 */
	public Date getExclusionDate() {
		return exclusionDate;
	}

	/**
	 * @param exclusionDate The exclusionDate to set.
	 */
	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}

	/**
	 * @return Returns the inclusionDate.
	 */
	public Date getInclusionDate() {
		return inclusionDate;
	}

	/**
	 * @param inclusionDate The inclusionDate to set.
	 */
	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
	}

	/**
	 * @return Returns the axisCount.
	 */
	public Integer getAxisCount() {
		return axisCount;
	}

	/**
	 * @param axisCount The axisCount to set.
	 */
	public void setAxisCount(Integer axisCount) {
		this.axisCount = axisCount;
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
	 * @return Returns the hp.
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * @param hp The hp to set.
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * @return Returns the mtopExpiry.
	 */
	public Date getMtopExpiry() {
		return mtopExpiry;
	}

	/**
	 * @param mtopExpiry The mtopExpiry to set.
	 */
	public void setMtopExpiry(Date mtopExpiry) {
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
	 * @return Returns the ownerCard.
	 */
	public boolean isOwnerCard() {
		return ownerCard;
	}

	/**
	 * @param ownerCard The ownerCard to set.
	 */
	public void setOwnerCard(boolean ownerCard) {
		this.ownerCard = ownerCard;
	}

	/**
	 * @return Returns the suctaExpiry.
	 */
	public Date getSuctaExpiry() {
		return suctaExpiry;
	}

	/**
	 * @param suctaExpiry The suctaExpiry to set.
	 */
	public void setSuctaExpiry(Date suctaExpiry) {
		this.suctaExpiry = suctaExpiry;
	}

	public Integer getOptionalInfo() {
		return optionalInfo;
	}

	public void setOptionalInfo(Integer optionalInfo) {
		this.optionalInfo = optionalInfo;
	}	
    
	

}
