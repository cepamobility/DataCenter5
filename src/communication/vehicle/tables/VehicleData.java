package communication.vehicle.tables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import persistence.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.datatype.tables.EnsuranceDataType;
import communication.datatype.tables.TypeCenterCostDataType;
import communication.datatype.tables.TypeCoverDataType;
import communication.datatype.tables.TypeDistanceUnitDataType;
import communication.datatype.tables.TypeEngineDataType;
import communication.datatype.tables.TypeFuelDataType;
import communication.datatype.tables.TypeTradeDataType;
import communication.datatype.tables.TypeUseDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.datatype.tables.TypeVolumeUnitDataType;
import communication.driver.tables.DriverData;
import communication.util.LogActions;
import communication.util.Validate;

public class VehicleData extends VehicleMainAttributes implements Comparable , Serializable  {

    public static final int ACTIVE = 0;
    public static final int EXCLUDED = 1;
    public static final int ALL = 2;
    
    public static final int DIV_ORG = 1;
    public static final int DIV_GEO = 2;
	    
    private static final long serialVersionUID = 1L;
	private TypeVehicleDataType type;
	private String colour;
	private String permissionNumber;
	private TypeUseDataType use;
	private Date permissionExpiry;
	private TypeCenterCostDataType ownerShip;
	private TypeFuelDataType fuel;
	private Date purchaseDate;
	private String chasisNumber;
	private Float co2emission;
	private Float consume;
	private TypeEngineDataType engine;
	private Integer tankCapacity;
	private TypeVolumeUnitDataType tankUnit;
	private boolean abs;
	private boolean airBag;
	private boolean aireAcondicionado;	
	private TypeDistanceUnitDataType odometerUnit;
	private CorporationData corporation;
    private ArrayList<DivOrgData> divOrgs;	
    private DriverData driver;
	private String model;	
	private TypeTradeDataType trade;	
	private TypeCoverDataType cover;		
    private String costCenter;
    private Date inclusionDate;
    
    private Date mtopExpiry;
    private Date suctaExpiry;
    private Date greenCardExpiry;

    private EnsuranceDataType ensuranceType;
    private String ensurance;

    private boolean ownerCard;                        

    private Integer axisCount;
    private Integer hp;

    private String observations;
    private VehicleOptionalInfoData optionalInfo;
    
    private Integer cost;
	private Integer odometer;
    
    private Collection unconformities;
	private AssignedVehicleDataType assignedVehicle;
	
	private String comment;
	private boolean suspension;

		
	private String extradata1;
	private String extradata2;
	private String extradata3;
	private String extradata4;
	private String extradata5;
	private String extradata6;
	private String extradata7;
	private String extradata8;
	private String extradata9;
	private String extradata10;
	private String extradata11;
	private String extradata12;
	private String extradata13;
	private String extradata14;
	private String extradata15;
	private String extradata16;
	private String extradata17;
	private String extradata18;
	private String extradata19;
	private String extradata20;
	private String extradata21;

	/**
	 * Contratos de Alquiler de los Vehiculos (Allergan)
	 */
	private Collection contracts;
	
	//VEHICLE SMF
	private VehicleSMFData vehicleSMFData;
	
	private HashMap<String, String> reportMoreInfo;
	private String contractType;
	private String alias;
	
	
	/**
	 * Constructor
	 * @param id
	 * @param typeId
	 * @param year
	 * @param colour
	 * @param permissionNumber
	 * @param useId
	 * @param permissionExpiry
	 * @param centerCostId
	 * @param fuelId
	 * @param purchaseDate
	 * @param chasisNumber
	 * @param engineId
	 * @param tankCapacity
	 * @param tankUnitId
	 * @param abs
	 * @param airBag
	 * @param aireAcondicionado
	 * @param odometerUnitId
	 * @param corporation
	 * @param country
	 * @param divOrgs
	 * @param driver
	 * @param matricula
	 * @param model
	 * @param tradeId
	 * @param coverId
	 * @param costCenter
	 * @param inclusionDate
	 * @param exclusionDate
	 * @param suctaExpiry
	 * @param mtopExpiry
	 * @param ensurance
	 * @param ensuranceType
	 * @param ownerCard
	 * @param axisCount
	 * @param hp
	 * @param observations
	 * @param greenCardExpiry
	 * @param optionalInfo
	 * @param cost
	 * @param unconformities
	 * @param assignedVehicle
	 * @param co2emission
	 * @param contracts
	 */
    public VehicleData(Integer id,String typeId,Integer year,String colour,String permissionNumber,String useId,
               Date permissionExpiry,String centerCostId,String fuelId,Date purchaseDate,String chasisNumber,
               String engineId,Integer tankCapacity,String tankUnitId,boolean abs,boolean airBag,boolean aireAcondicionado,
               boolean suspension,String odometerUnitId,CorporationData corporation,GeoLabelData country,Collection divOrgs,DriverData driver,
               String matricula,String model,String tradeId,String coverId,String costCenter, Date inclusionDate, Date exclusionDate, 
               Date suctaExpiry, Date mtopExpiry, String ensurance,EnsuranceDataType ensuranceType, boolean ownerCard,
               Integer axisCount, Integer hp, String observations, Date greenCardExpiry,
               VehicleOptionalInfoData optionalInfo,Integer cost,Collection unconformities,
               AssignedVehicleDataType assignedVehicle, Float co2emission, Float consume, Collection contracts, VehicleSMFData vehicleSMFData, String alias) {   
        
        super(id,matricula,country,year, exclusionDate);
    	if (!empty(typeId)) {
    		this.type = new TypeVehicleDataType(typeId);
    	}	
    	this.colour = (colour != null)?colour.trim():"";
    	this.permissionNumber = permissionNumber;
    	this.use = new TypeUseDataType(useId);
    	this.permissionExpiry = permissionExpiry;
    	if (!empty(centerCostId)) {
    		this.ownerShip = new TypeCenterCostDataType(centerCostId);
    	}
    	if (!empty(fuelId)) {
    		this.fuel = new TypeFuelDataType(fuelId);
    	}	
    	this.purchaseDate = purchaseDate;
    	this.chasisNumber = chasisNumber;
    	this.co2emission = co2emission;
    	this.consume = consume;
    	
    	if (!empty(engineId)) {
    		this.engine = new TypeEngineDataType(engineId);
    	}	
    	this.tankCapacity = tankCapacity;
    	if (!empty(tankUnitId)) {
    		this.tankUnit = new TypeVolumeUnitDataType(tankUnitId);
    	}	
    	this.abs = abs;
    	this.airBag = airBag;
    	this.aireAcondicionado = aireAcondicionado;
    	this.suspension = suspension;
    	if (!empty(odometerUnitId)) {
    		this.odometerUnit = new TypeDistanceUnitDataType(odometerUnitId);
    	}	
    	this.corporation = corporation;		
    	this.divOrgs = new ArrayList<DivOrgData>(divOrgs);
        if(divOrgs != null) {
	        Iterator iter=divOrgs.iterator();
	        while(iter.hasNext()){//Acceso directo a la primera división
	        	DivOrgData d=(DivOrgData)iter.next();
	        	if(divOrgItem1==null){
	        		Iterator iter2=d.getDivOrgLevels().getItemIterator();
	        		ItemData item=((ItemData)iter2.next());
	        		divOrgItem1=item.getName();
	        		if(item.getSubItems()!=null){
		        		iter2=item.getSubItems().getItemIterator();
		        		if(iter2.hasNext()){
		        			if(item.getSubItems()!=null){
		        				iter2=item.getSubItems().getItemIterator();
		        				item=((ItemData)iter2.next());
			        			divOrgItem2=item.getName();
			        			if(item.getSubItems()!=null){
				        			iter2=item.getSubItems().getItemIterator();
				        			if(iter2.hasNext()){
					        			divOrgItem3=((ItemData)iter2.next()).getName();
					        		}
			        			}
		        			}
		        		}
	        		}
	        		
	        		
	        		break;
	        	}
	        }
        }
    	this.driver = driver;
    	this.model = model.trim();
    	if (!empty(tradeId)) {
    		this.trade = new TypeTradeDataType(tradeId);
    	}
    	if (!empty(coverId)) {    	
    		this.cover = new TypeCoverDataType(coverId);
    	}	
    	if (!empty(costCenter)) 	{
    		this.costCenter = costCenter;
    	}
    	this.inclusionDate = inclusionDate;    	
        this.mtopExpiry = mtopExpiry;
        this.suctaExpiry = suctaExpiry;
        this.greenCardExpiry = greenCardExpiry;
        this.ensuranceType = ensuranceType;
        this.ensurance = ensurance;
        this.ownerCard = ownerCard;
        this.axisCount = axisCount;
        this.hp = hp;
        this.observations = observations;
        this.optionalInfo = optionalInfo;
        this.cost = cost;
        this.unconformities = unconformities;
        this.assignedVehicle = assignedVehicle;
        this.contracts = contracts;
        //VEHICLE SMF
        this.vehicleSMFData = vehicleSMFData;
        this.alias = alias;
    }    
    
    public VehicleData(Integer id) {
        super(id);
    }
        
	public VehicleData(Integer id, String plate, String model, String tradeId) {
        super(id,plate,null,null,null);
        this.model = model;
        if (!empty(tradeId)) {
            this.trade = new TypeTradeDataType(tradeId);
        }
    }
	


	/**
	 * constructor por copia
	 * @param vehicle
	 */
    public VehicleData(VehicleData vehicle) {
    	super(vehicle.getId(),vehicle.getMatricula(),vehicle.getCountry(),vehicle.getYear(),vehicle.getExclusionDate());
    	type = vehicle.getType();
    	colour = vehicle.getColour();
    	permissionNumber = vehicle.getPermissionNumber();
    	use = vehicle.getUse();
    	permissionExpiry = vehicle.getPermissionExpiry();
    	ownerShip = vehicle.getOwnerShip();
    	fuel = vehicle.getFuel();
    	purchaseDate = vehicle.getPurchaseDate();
    	chasisNumber = vehicle.getChasisNumber();
    	co2emission = vehicle.getCo2emission();
    	consume = vehicle.getConsume();
    	engine = vehicle.getEngine();
    	tankCapacity = vehicle.getTankCapacity();
    	tankUnit = vehicle.getTankUnit();
    	abs = vehicle.getAbs();
    	airBag = vehicle.getAirBag();
    	suspension = vehicle.getSuspension();
    	aireAcondicionado = vehicle.getAireAcondicionado();	
    	odometerUnit = vehicle.getOdometerUnit();
    	corporation = vehicle.getCorporation();
        divOrgs = new ArrayList<DivOrgData>(vehicle.getDivOrgs());	
        driver = vehicle.getDriver();
    	model = vehicle.getModel();	
    	trade = vehicle.getTrade();	
    	cover = vehicle.getCover();		
        costCenter = vehicle.getCostCenter();
        inclusionDate = vehicle.getInclusionDate();
        
        mtopExpiry = vehicle.getMtopExpiry();
        suctaExpiry = vehicle.getSuctaExpiry();
        greenCardExpiry = vehicle.getGreenCardExpiry();

        ensuranceType = vehicle.getEnsuranceType();
        ensurance = vehicle.getEnsurance();

        ownerCard = vehicle.isOwnerCard();                      
        axisCount = vehicle.getAxisCount();
        hp = vehicle.getHp();

        observations = vehicle.getObservations();
        optionalInfo = vehicle.getOptionalInfo();
        
        cost = vehicle.getCost();
    	odometer = vehicle.getOdometer();
        
        unconformities = vehicle.getUnconformities();
    	assignedVehicle = vehicle.getAssignedVehicle();
    	
    	/**
    	 * Contratos de Alquiler de los Vehiculos (Allergan)
    	 */
    	contracts = vehicle.getContracts();
    	
    	vehicleSMFData = vehicle.getVehicleSMFData();
	}
    
    

	public HashMap<String, String> getReportMoreInfo() {
		return reportMoreInfo;
	}

	public void setReportMoreInfo(HashMap<String, String> reportMoreInfo) {
		this.reportMoreInfo = reportMoreInfo;
	}

	public VehicleData() {
		super(null);
	}

	/**
	 * @return Returns the abs.
	 */
	public boolean getAbs() {
		return abs;
	}
	/**
	 * @return Returns the airBag.
	 */
	public boolean getAirBag() {
		return airBag;
	}
	/**
	 * @return Returns the aireAcondicionado.
	 */
	public boolean getAireAcondicionado() {
		return aireAcondicionado;
	}	
	/**
	 * @return Returns the ownerShip.
	 */
	public TypeCenterCostDataType getOwnerShip() {
		return ownerShip;
	}
	/**
	 * @return Returns the chasisNumber.
	 */
	public String getChasisNumber() {
		return chasisNumber;
	}
	/**
	 * @return Returns the colour.
	 */
	public String getColour() {
		return colour;
	}
	/**
	 * @return Returns the corporation.
	 */
	public CorporationData getCorporation() {
		return corporation;
	}
	/**
	 * @return Returns the engine.
	 */
	public TypeEngineDataType getEngine() {
		return engine;
	}
	
	/**
	 * @return Returns the fuel.
	 */
	public TypeFuelDataType getFuel() {
		return fuel;
	}
	/**
	 * @return Returns the odometerUnit.
	 */
	public TypeDistanceUnitDataType getOdometerUnit() {
		return odometerUnit;
	}
	/**
	 * @return Returns the permissionExpiry.
	 */
	public Date getPermissionExpiry() {
		return permissionExpiry;
	}
	/**
	 * @return Returns the permissionNumber.
	 */
	public String getPermissionNumber() {
		return permissionNumber;
	}
	/**
	 * @return Returns the purchaseDate.
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @return Returns the tankCapacity.
	 */
	public Integer getTankCapacity() {
		return tankCapacity;
	}
	/**
	 * @return Returns the tankUnit.
	 */
	public TypeVolumeUnitDataType getTankUnit() {
		return tankUnit;
	}
	/**
	 * @return Returns the type.
	 */
	public TypeVehicleDataType getType() {
		return type;
	}
	/**
	 * @return Returns the use.
	 */
	public TypeUseDataType getUse() {
		return use;
	}
	
	/**
	 * @return Returns the divOrgs.
	 */
	public Collection getDivOrgs() {
		return divOrgs;
	}
	/**
	 * @return Returns the driver.
	 */
	public DriverData getDriver() {
		return driver;
	}	
	/**
	 * @return Returns the matricula.
	 */
	public TypeTradeDataType getTrade() {
		return trade;
	}	
	/**
	 * @return Returns the matricula.
	 */
	public TypeCoverDataType getCover() {
		return cover;
	}		
	/**
	 * @return Returns the model.
	 */
	public String getModel() {
		return model;
	}	
	
	/**
	 * @set the id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}		
	
	/**
	 * @set the use.
	 */
	public void setUse(TypeUseDataType use) {
		this.use = use;
	}	
	
	/**
	 * @set the driver.
	 */
	public void setDriver(DriverData driver) {
		this.driver = driver;
	}		
	/**
	 * @set the matricula.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}		
	/**
	 * @set the chasisNumber.
	 */
	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}	
	/**
	 * @set the country.
	 */ 
	public void setCountry(GeoLabelData country) {
		this.country = country;
	}		
		
    /**
     * @return
     */
    public Iterator getDivOrgsIterator() {
        if(divOrgs == null) {
            return null;
        }
        Collections.sort((List)divOrgs);
        return divOrgs.iterator();
    }	
    
	/**
	 * @param data
	 * @return null if is valid, String with message if is invalid
	 */
	public String validate(UserSessionData userRequest) {

	if (empty(matricula)) {
		return "generic.vehicle.vehicledata.regsitrationNumber";
	}
	if (use == null || use.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.useInvalid";
	}	
	if (type == null || type.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.typeInvalid";
	}
	if (trade == null || trade.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.tradeInvalid";
	}	
	if (empty(model)) {
		return "generic.vehicle.vehicledata.model";
	}
/*	if (fuel == null || fuel.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.fuelInvalid";
	}
	if (odometerUnit == null || odometerUnit.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.odometerUnitInvalid";
	}	
	if (ownerShip == null || ownerShip.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.ownershipInvalid";
	}		
	if (engine == null || engine.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.engineInvalid";
	}			
	if (cover == null || cover.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.coverInvalid";
	}				
	if (tankUnit == null || tankUnit.getResourceBundleId() == null) {
		return "generic.vehicle.vehicledata.tankUnitInvalid";
	}					
*/	
	if (year == null) {
		return "generic.vehicle.vehicledata.yearInvalid";
	}
	if (corporation == null) {
		return "generic.vehicle.vehicledata.corporationInvalid";
	}
	if (country == null) {
		return "generic.driver.driverdata.countryInvalid";
	}
/*	if (use.getResourceBundleId().equals("persistence.datatype.typeuse.individual") && driver == null) {
		return "generic.vehicle.vehicledata.driverInvalid";
	}	
	if (use.getResourceBundleId().equals("persistence.datatype.typeuse.institutional") && driver != null) {
		return "generic.vehicle.vehicledata.driverInvalid";
	}
*/				
    Iterator divOrgsIterator = getDivOrgsIterator();
    if(divOrgsIterator == null) {
        return "generic.vehicle.vehicledata.divorgInvalid";
    }else {
        String message = Validate.validateDivOrg(divOrgsIterator,userRequest);
        if(message != null) {
            return message;
        }
    }
    if(!Validate.validateDivGeo(country,userRequest)) {
        return "generic.vehicle.vehicledata.divgeoInvalid";
    }
    
    // Chequeo que las divisiones organizacionales del driver estÃ©n incluidas en las del filtro del usr
/*    if (driver !=null) {
	    Iterator divOrgsIteratorDriver = this.driver.getDivOrgsIterator();
	    if(divOrgsIteratorDriver == null) {
	        return "generic.vehicle.vehicledata.divorgDriverInvalid";
	    }else {
	        String message = Validate.validateDivOrg(divOrgsIteratorDriver,userRequest);
	        if(message != null) {
	            return message;
	        }
	    }
	    if(!Validate.compareDivGeoData(this.driver.getCountry(),userRequest.getFilter().getUserGeoLabelData())) {
	        return "generic.vehicle.vehicledata.divgeoDriverInvalid";
	    }    
    }
*/	
	return null;
}
    
	private boolean empty(String value) {
		return (value == null || value.trim().length() == 0);
	}
	
	/**
	 * 
	 * @param index
	 * @param j
	 * @return
	 */
    public String getData(int index, int j) {
        Iterator it = getDivOrgsIterator();
        DivOrgData data = null;
        for(int i = 0; i < index; i++) {
            data = (DivOrgData)it.next();
        }
        LabelItemData label = data.getDivOrgLevels(); 
        for(int i = 1; i < j; i++) {
            label = ((ItemData)label.getItemIterator().next()).getSubItems();
        }
        return ((ItemData)label.getItemIterator().next()).getName();
    }

    /**
     * 
     * @param index
     * @param j
     * @return
     */
    public Integer getIdData(int index, int j) {
        Iterator it = getDivOrgsIterator();
        DivOrgData data = null;
        for(int i = 0; i < index; i++) {
            data = (DivOrgData)it.next();
        }
        LabelItemData label = data.getDivOrgLevels(); 
        for(int i = 1; i < j; i++) {
            label = ((ItemData)label.getItemIterator().next()).getSubItems();
        }
        return ((ItemData)label.getItemIterator().next()).getId();
    }
    
    public String getCostCenter()
    {
    	return this.costCenter;
    }
    public void setCostCenter(String costCenter)
    {
    	this.costCenter = costCenter;
    }

	
	public Date getInclusionDate() {
		return inclusionDate;
	}


	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
	}


    public int compareTo(Object arg0) {
        return matricula.compareTo(((VehicleData)arg0).matricula);
    }
    
    /**
     * 
     * @param type
     * @param index
     * @param j
     * @return
     */
    public String getData(int type, int index, int j) {
        if(type == DIV_ORG) {
            Iterator it = getDivOrgsIterator();
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
            GeoLabelData label = getCountry(); 
            for(int i = 1; i < j; i++) {
                label = ((GeoItemData)label.getItemIterator().next()).getSubItems();
            }
           return ((GeoItemData)label.getItemIterator().next()).getName();
        }
    }


    public void setAbs(boolean abs) {
        this.abs = abs;
    }


    public void setAirBag(boolean airBag) {
        this.airBag = airBag;
    }


    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }


    public void setColour(String colour) {
        this.colour = colour;
    }


    public void setCorporation(CorporationData corporation) {
        this.corporation = corporation;
    }


    public void setCover(TypeCoverDataType cover) {
        this.cover = cover;
    }


    public void setDivOrgs( ArrayList<DivOrgData>  divOrgs) {
        this.divOrgs = divOrgs;
    }

    public void setEngine(TypeEngineDataType engine) {
        this.engine = engine;
    }

    public void setFuel(TypeFuelDataType fuel) {
        this.fuel = fuel;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOdometerUnit(TypeDistanceUnitDataType odometerUnit) {
        this.odometerUnit = odometerUnit;
    }

    public void setOwnerShip(TypeCenterCostDataType ownerShip) {
        this.ownerShip = ownerShip;
    }

    public void setPermissionExpiry(Date permissionExpiry) {
        this.permissionExpiry = permissionExpiry;
    }

    public void setPermissionNumber(String permissionNumber) {
        this.permissionNumber = permissionNumber;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTankCapacity(Integer tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setTankUnit(TypeVolumeUnitDataType tankUnit) {
        this.tankUnit = tankUnit;
    }

    public void setTrade(TypeTradeDataType trade) {
        this.trade = trade;
    }

    public void setType(TypeVehicleDataType type) {
        this.type = type;
    }

    public Date getMtopExpiry() {
        return mtopExpiry;
    }

    public String getEnsurance() {
        return ensurance;
    }

    public void setEnsurance(String ensurance) {
        this.ensurance = ensurance;
    }

    public void setMtopExpiry(Date mtopExpiry) {
        this.mtopExpiry = mtopExpiry;
    }

    public Integer getAxisCount() {
        return axisCount;
    }

    public EnsuranceDataType getEnsuranceType() {
        return ensuranceType;
    }

    public Integer getHp() {
        return hp;
    }

    public boolean isOwnerCard() {
        return ownerCard;
    }

    public Date getSuctaExpiry() {
        return suctaExpiry;
    }

    public void setAxisCount(Integer axisCount) {
        this.axisCount = axisCount;
    }

    public void setEnsuranceType(EnsuranceDataType ensuranceType) {
        this.ensuranceType = ensuranceType;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public void setOwnerCard(boolean ownerCard) {
        this.ownerCard = ownerCard;
    }

    public void setSuctaExpiry(Date suctaExpiry) {
        this.suctaExpiry = suctaExpiry;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

	public Date getGreenCardExpiry() {
		return greenCardExpiry;
	}

	public void setGreenCardExpiry(Date greenCardExpiry) {
		this.greenCardExpiry = greenCardExpiry;
	}

	
	public VehicleOptionalInfoData getOptionalInfo() {
		return optionalInfo;
	}

	public void setOptionalInfo(VehicleOptionalInfoData optionalInfo) {
		this.optionalInfo = optionalInfo;
	}

    public Integer getDivOrgId(int index) {
        Iterator it = getDivOrgsIterator();
        DivOrgData data = null;
        for(int i = 0; i <= index; i++) {
        	if(it.hasNext()) {
        		data = (DivOrgData)it.next();
        	}else {
        		return 0;
        	}
        }
        ItemData item = (ItemData)data.getDivOrgLevels().getItemIterator().next();
        while(item.getSubItems() != null){
            item = (ItemData)item.getSubItems().getItemIterator().next();
        }
        return item.getId();
    }

    public Integer[] getDivOrgIds() {
    	Integer[] ids = new Integer[3];
        Iterator it = getDivOrgsIterator();
        DivOrgData data = null;
        for(int i = 0; i < 3; i++) {
        	if(it.hasNext()) {
        		data = (DivOrgData)it.next();
                ItemData item = (ItemData)data.getDivOrgLevels().getItemIterator().next();
                while(item.getSubItems() != null){
                    item = (ItemData)item.getSubItems().getItemIterator().next();
                }
                ids[i] = item.getId();
        	}else {
        		ids[i] = 0;
        	}
        }
        return ids;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

	public Integer getOdometer() {
		return odometer;
	}

	public void setOdometer(Integer odometer) {
		this.odometer = odometer;
	}

    public Collection getUnconformities() {
		return unconformities;
	}

	public void setUnconformities(Collection unconformities) {
		this.unconformities = unconformities;
	}

	public AssignedVehicleDataType getAssignedVehicle() {
		return assignedVehicle;
	}

	public void setAssignedVehicle(AssignedVehicleDataType assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
	}

	/**
	 * @return the co2emission
	 */
	public Float getCo2emission() {
		return co2emission;
	}

	/**
	 * @param co2emission the co2emission to set
	 */
	public void setCo2emission(Float co2emission) {
		this.co2emission = co2emission;
	}

	/**
	 * Contratos de Alquiler de los Vehiculos (Allergan)
	 * @return the contracts
	 */
	public Collection getContracts() {
		return contracts;
	}

	/**
	 * Contratos de Alquiler de los Vehiculos (Allergan)
	 * @param contracts the contracts to set
	 */
	public void setContracts(Collection contracts) {
		this.contracts = contracts;
	}

	public Float getConsume() {
		return consume;
	}

	public void setConsume(Float consume) {
		this.consume = consume;
	}

	public VehicleSMFData getVehicleSMFData() {
		return vehicleSMFData;
	}

	public void setVehicleSMFData(VehicleSMFData vehicleSMFData) {
		this.vehicleSMFData = vehicleSMFData;
	}

	public void addComment(Object[] data) {
		String line = "- "+Util.formatDate((Date)data[2]);
		line += " - "+ data[1]+" - ";
		switch(((Integer)data[3]).intValue()) {
			case LogActions.CREATE:
				line += "CREATE";
				break;
			case LogActions.UPDATE:
				line += "UPDATE";
				break;
			case LogActions.DELETE:
				line += "DELETE";
				break;
			case LogActions.INCLUDE:
				line += "INCLUDE";
				break;
			case LogActions.EXCLUDE:
				line += "EXCLUDE";
				break;
		}
		line += " - " + String.valueOf(data[4]);
		if(comment == null) {
			comment = line; 
		}else {
			comment += "\n" + line;
		}
	}

	public String getComment() {
		return comment;
	}

	public boolean getSuspension() {
		return suspension;
	}

	public void setSuspension(boolean suspension) {
		this.suspension = suspension;
	}
	
	private LabelItemData getLabelItemData(Integer item1, Integer item2,
			Integer item3) {
		Collection items1 = new LinkedList();
        if(item2 == null) {
            items1.add(new ItemData(item1,"",true,null));
        }else{
            if(item3 == null) {
                Collection items2 = new LinkedList();
                items2.add(new ItemData(item2,"",true,null));
                LabelItemData level1 = new LabelItemData(null,null,items2,null);
                items1.add(new ItemData(item1,"",true,level1));
            }else {
                Collection items3 = new LinkedList();
                items3.add(new ItemData(item3,"",true,null));
                LabelItemData level2 = new LabelItemData(null,null,items3,null);
                
                Collection items2 = new LinkedList();
                items2.add(new ItemData(item2,"",true,level2));
                LabelItemData level1 = new LabelItemData(null,null,items2,null);
                
                items1.add(new ItemData(item1,"",true,level1));
                
            }
        }
        LabelItemData lid = new LabelItemData(null,null,items1,null);
		return lid;
	}

	public void replaceDivOrg(int index, Integer item1, Integer item2, Integer item3) {
		LabelItemData lid = getLabelItemData(item1, item2, item3);  	

		Iterator<DivOrgData> it = this.getDivOrgsIterator();
		DivOrgData divOrgData = null;
    	for(int i = 1; i <= index && it.hasNext(); i++) {		
    		divOrgData = (DivOrgData)it.next();
    	}
    	divOrgData.setDivOrgLevels(lid);
	}

	public void replaceDivGeo(Integer item1, Integer item2, Integer item3) {
		Collection items1 = new LinkedList();
        if(item2 == null) {
            items1.add(new GeoItemData(item1,"",true,null));
        }else{
            if(item3 == null) {
                Collection items2 = new LinkedList();
                items2.add(new GeoItemData(item2,"",true,null));
                GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
                items1.add(new GeoItemData(item1,"",true,level1));
            }else {
                Collection items3 = new LinkedList();
                items3.add(new GeoItemData(item3,"",true,null));
                GeoLabelData level2 = new GeoLabelData(null,null,null,items3);
                
                Collection items2 = new LinkedList();
                items2.add(new GeoItemData(item2,"",true,level2));
                GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
                
                items1.add(new GeoItemData(item1,"",true,level1));
            }
        }
        setCountry(new GeoLabelData(null,null,null,items1));       		
	}


	public String getExtradata1() {
		return extradata1;
	}

	public void setExtradata1(String extradata1) {
		this.extradata1 = extradata1;
	}

	public String getExtradata2() {
		return extradata2;
	}

	public void setExtradata2(String extradata2) {
		this.extradata2 = extradata2;
	}

	public String getExtradata3() {
		return extradata3;
	}

	public void setExtradata3(String extradata3) {
		this.extradata3 = extradata3;
	}

	public String getExtradata4() {
		return extradata4;
	}

	public void setExtradata4(String extradata4) {
		this.extradata4 = extradata4;
	}

	public String getExtradata5() {
		return extradata5;
	}

	public void setExtradata5(String extradata5) {
		this.extradata5 = extradata5;
	}

	public String getExtradata6() {
		return extradata6;
	}

	public void setExtradata6(String extradata6) {
		this.extradata6 = extradata6;
	}

	public String getExtradata7() {
		return extradata7;
	}

	public void setExtradata7(String extradata7) {
		this.extradata7 = extradata7;
	}

	public String getExtradata8() {
		return extradata8;
	}

	public void setExtradata8(String extradata8) {
		this.extradata8 = extradata8;
	}

	public String getExtradata9() {
		return extradata9;
	}

	public void setExtradata9(String extradata9) {
		this.extradata9 = extradata9;
	}

	public String getExtradata10() {
		return extradata10;
	}

	public void setExtradata10(String extradata10) {
		this.extradata10 = extradata10;
	}

	public String getExtradata11() {
		return extradata11;
	}

	public void setExtradata11(String extradata11) {
		this.extradata11 = extradata11;
	}

	public String getExtradata12() {
		return extradata12;
	}

	public void setExtradata12(String extradata12) {
		this.extradata12 = extradata12;
	}

	public String getExtradata13() {
		return extradata13;
	}

	public void setExtradata13(String extradata13) {
		this.extradata13 = extradata13;
	}

	public String getExtradata14() {
		return extradata14;
	}

	public void setExtradata14(String extradata14) {
		this.extradata14 = extradata14;
	}

	public String getExtradata15() {
		return extradata15;
	}

	public void setExtradata15(String extradata15) {
		this.extradata15 = extradata15;
	}

	public String getExtradata16() {
		return extradata16;
	}

	public void setExtradata16(String extradata16) {
		this.extradata16 = extradata16;
	}

	public String getExtradata17() {
		return extradata17;
	}

	public void setExtradata17(String extradata17) {
		this.extradata17 = extradata17;
	}

	public String getExtradata18() {
		return extradata18;
	}

	public void setExtradata18(String extradata18) {
		this.extradata18 = extradata18;
	}

	public String getExtradata19() {
		return extradata19;
	}

	public void setExtradata19(String extradata19) {
		this.extradata19 = extradata19;
	}

	public String getExtradata20() {
		return extradata20;
	}

	public void setExtradata20(String extradata20) {
		this.extradata20 = extradata20;
	}

	public String getExtradata21() {
		return extradata21;
	}

	public void setExtradata21(String extradata21) {
		this.extradata21 = extradata21;
	}

	public String getExtradata(int i) {
		switch(i) {
			case 1:
				return extradata1;
			case 2:
				return extradata2;
			case 3:
				return extradata3;
			case 4:
				return extradata4;
			case 5:
				return extradata5;
			case 6:
				return extradata6;
			case 7:
				return extradata7;
			case 8:
				return extradata8;
			case 9:
				return extradata9;
			case 10:
				return extradata10;
			case 11:
				return extradata11;
			case 12:
				return extradata12;
			case 13:
				return extradata13;
			case 14:
				return extradata14;
			case 15:
				return extradata15;
			case 16:
				return extradata16;
			case 17:
				return extradata17;
			case 18:
				return extradata18;
			case 19:
				return extradata19;
			case 20:
				return extradata20;
			case 21:
				return extradata21;
		}
		return null;
	}

	public void setExtradata(int i, String value) {
		switch(i) {
			case 1:
				extradata1 = value;
				break;
			case 2:
				extradata2 = value;
				break;
			case 3:
				extradata3 = value;
				break;
			case 4:
				extradata4 = value;
				break;
			case 5:
				extradata5 = value;
				break;
			case 6:
				extradata6 = value;
				break;
			case 7:
				extradata7 = value;
				break;
			case 8:
				extradata8 = value;
				break;
			case 9:
				extradata9 = value;
				break;
			case 10:
				extradata10 = value;
				break;
			case 11:
				extradata11 = value;
				break;
			case 12:
				extradata12 = value;
				break;
			case 13:
				extradata13 = value;
				break;
			case 14:
				extradata14 = value;
				break;
			case 15:
				extradata15 = value;
				break;
			case 16:
				extradata16 = value;
				break;
			case 17:
				extradata17 = value;
				break;
			case 18:
				extradata18 = value;
				break;
			case 19:
				extradata19 = value;
				break;
			case 20:
				extradata20 = value;
				break;
			case 21:
				extradata21 = value;
				break;
		}
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public void removeDivOrgs(int index) {
		Iterator<DivOrgData> it = this.getDivOrgsIterator();
		int i = 1;
		while(it.hasNext()) {
			it.next();
			if(i > index)
				it.remove();
			i++;
		}
	}

	public void addDivOrg(Integer divOrgid, String divOrgName, Integer item1, Integer item2, Integer item3) {
		LabelItemData lid = getLabelItemData(item1, item2, item3);  	
    	getDivOrgs().add(new DivOrgData(divOrgid,divOrgName,lid));
	}
}
