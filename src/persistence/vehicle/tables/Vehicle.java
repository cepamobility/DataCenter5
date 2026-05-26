package persistence.vehicle.tables;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.AssignedVehicle;
import persistence.datatype.tables.EnsuranceType;
import persistence.datatype.tables.TypeCenterCost;
import persistence.datatype.tables.TypeCover;
import persistence.datatype.tables.TypeDistanceUnit;
import persistence.datatype.tables.TypeEngine;
import persistence.datatype.tables.TypeFuel;
import persistence.datatype.tables.TypeTrade;
import persistence.datatype.tables.TypeUse;
import persistence.datatype.tables.TypeVehicle;
import persistence.datatype.tables.TypeVolumeUnit;
import persistence.driver.tables.Driver;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.corporation.tables.LabelItemData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.datatype.tables.EnsuranceDataType;
import communication.driver.tables.DriverData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleOptionalInfoData;
import communication.vehicle.tables.VehicleSMFData;


public class Vehicle {

	private Integer id;
	private TypeVehicle type;
	private Integer year;
	private String colour;
	private String permissionNumber;
	private TypeUse use;
	private Date permissionExpiry;
	private TypeCenterCost ownership;
	private TypeFuel fuel;
	private Date purchaseDate;
	private String chasisNumber;
	private Float co2emission;
	private Float consume;
	private TypeEngine engine;
	private Integer tankCapacity;
	private TypeVolumeUnit tankUnit;
	private boolean abs;
	private boolean airBag;
	private TypeDistanceUnit odometerUnit;
    
    private GeoItemData country;
    private GeoLevel1 geoLevel1; 

    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;    
    private Driver driver;
    
    private String model;
	private TypeTrade trade;       
	private boolean aireAcondicionado;   
	private TypeCover cover;
	private String costCenter;
	
    private Date inclusionDate;
    private Date exclusionDate;
    
    private Date mtopExpiry;
    private Date suctaExpiry;
    private Date greenCardExpiry;

    private EnsuranceType ensuranceType;
    private String ensurance;

    private boolean ownerCard;                        

    private Integer axisCount;
    private Integer hp;

    private String matricula;
    private CorporationCountry corpCountry;

    private String observations;
    
    private Integer cost;
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
    
    private String contractType;

	private VehicleOptionalInfo optionalInfo;
    
    private Set unconformitySet;
    private AssignedVehicle assignedVehicle;
    
    //VEHICLE SMF
    private VehicleSMF vehicleSMF;
    private String alias;    
    	
    /**
     * Contratos de Alquiler (Allergan)
     */
    private Set contractSet; 
    
    public Vehicle() {
	}
	
    /**
     * 
     * @param id
     * @param type
     * @param year
     * @param colour
     * @param permissionNumber
     * @param use
     * @param permissionExpiry
     * @param centerCost
     * @param fuel
     * @param purchaseDate
     * @param chasisNumber
     * @param engine
     * @param tankCapacity
     * @param tankUnit
     * @param abs
     * @param airBag
     * @param aireAcondicionado
     * @param odometerUnit
     * @param corporation
     * @param country
     * @param geoLevel1
     * @param divOrg1
     * @param divOrg2
     * @param divOrg3
     * @param driver
     * @param matricula
     * @param model
     * @param trade
     * @param costCenter
     * @param inclusionDate
     * @param exclusionDate
     * @param observations
     * @param greenCardExpiry
     * @param cost
     * @param co2emission
     * @param consume
     */
	public Vehicle(Integer id,TypeVehicle type,Integer year,
			   String colour,String permissionNumber,TypeUse use,
	           Date permissionExpiry,TypeCenterCost centerCost,
			   TypeFuel fuel,Date purchaseDate,String chasisNumber,
			   TypeEngine engine,Integer tankCapacity,TypeVolumeUnit tankUnit,
			   boolean abs,boolean airBag,boolean aireAcondicionado,boolean suspension,
			   TypeDistanceUnit odometerUnit,
			   Corporation corporation,GeoItemData country,
			   GeoLevel1 geoLevel1,DivOrgItemLevel1 divOrg1,
			   DivOrgItemLevel1 divOrg2,DivOrgItemLevel1 divOrg3,
			   Driver driver,String matricula,
			   String model,TypeTrade trade, String costCenter, 
			   Date inclusionDate, Date exclusionDate, String observations, 
			   Date greenCardExpiry, Integer cost, Float co2emission, Float consume, VehicleSMF vehicleSMF) {  
		this.id = id; 
		this.type = type;
		this.year = year;
		this.colour = colour;
		this.permissionNumber = permissionNumber;
		this.use = use;
		this.permissionExpiry = permissionExpiry;
		this.ownership = centerCost;
		this.fuel = fuel;
		this.purchaseDate = purchaseDate;
		this.chasisNumber = chasisNumber;
		this.co2emission = co2emission;
		this.consume = consume;
		this.engine = engine;
		this.tankCapacity = tankCapacity;
		this.tankUnit = tankUnit;
		this.abs = abs;
		this.airBag = airBag;
		this.suspension = suspension;
		this.odometerUnit = odometerUnit;
		this.country = country;
		this.geoLevel1 = geoLevel1;		
		this.divOrg1 = divOrg1;
		this.divOrg2 = divOrg2;		
		this.divOrg3 = divOrg3;
		
		this.model = model;
		this.trade = trade;
		
		this.matricula = matricula;
		this.aireAcondicionado = aireAcondicionado;		
		this.costCenter = costCenter;
		this.inclusionDate = inclusionDate;
		this.exclusionDate = exclusionDate;
		this.greenCardExpiry = greenCardExpiry;

        this.observations = observations;
        this.cost = cost;
        //VEHICLE SMF
        this.vehicleSMF = vehicleSMF;
     
	}	
	
	public Vehicle(VehicleData data) {
		id = data.getId(); 
		
		Session session = HibernateUtil.currentSession();
				
	    type = (TypeVehicle)session.load(TypeVehicle.class,data.getType().getResourceBundleId());
				
		year = data.getYear();
		colour = data.getColour();
		permissionNumber = data.getPermissionNumber();
		
		use = (TypeUse)session.load(TypeUse.class,data.getUse().getResourceBundleId());
		
		
		permissionExpiry = data.getPermissionExpiry();
		
		ownership = null;
		if (data.getOwnerShip() != null) {
			ownership = (TypeCenterCost)session.load(TypeCenterCost.class,data.getOwnerShip().getResourceBundleId());
		}
			
		fuel = null;
		if (data.getFuel() != null) {		
			fuel = (TypeFuel)session.load(TypeFuel.class,data.getFuel().getResourceBundleId());
		}	
		
		purchaseDate = data.getPurchaseDate();
		chasisNumber = data.getChasisNumber();
		co2emission = data.getCo2emission();
		consume = data.getConsume();
		
		engine = null;
		if (data.getEngine() != null) {
			engine = (TypeEngine)session.load(TypeEngine.class,data.getEngine().getResourceBundleId());
		}	
		
		tankCapacity = data.getTankCapacity();
		
		tankUnit = null;
		if (data.getTankUnit() != null) {
			tankUnit = (TypeVolumeUnit)session.load(TypeVolumeUnit.class,data.getTankUnit().getResourceBundleId());
		}	
		
		abs = data.getAbs();
		airBag = data.getAirBag();
		aireAcondicionado = data.getAireAcondicionado();		
		suspension = data.getSuspension();		
		
		odometerUnit = null;
		if (data.getOdometerUnit() != null) {
			odometerUnit = (TypeDistanceUnit)session.load(TypeDistanceUnit.class,data.getOdometerUnit().getResourceBundleId());
		}	

	    // Cargo corporacion
		//corporation = (Corporation)session.load(Corporation.class,new Integer(2));
		//corporation = (Corporation)session.load(Corporation.class,data.getCorporation().getId());

	    // Cargo driver
		driver = null;
		if (data.getDriver()!=null) {
			driver = (Driver)session.load(Driver.class,data.getDriver().getId());
		}	
		
		matricula = data.getMatricula();
		costCenter = data.getCostCenter();
		exclusionDate = data.getExclusionDate();
		inclusionDate = data.getInclusionDate();
		greenCardExpiry = data.getGreenCardExpiry();
		
		model = data.getModel();
		
		trade = (TypeTrade)session.load(TypeTrade.class,data.getTrade().getResourceBundleId());
		if(trade != null) {
		    System.out.println("--> "+trade.getResourceBundleId());
        }
		cover = null;
		if (data.getCover() != null) {
			cover = (TypeCover)session.load(TypeCover.class,data.getCover().getResourceBundleId());
		}	
	
		// Recorro arbol(más precisamente rama) de divisiones geográficas para llegar hasta una hoja (que es el pais)
		// En item2 voy llevando el padre
	    GeoItemData itemData1 = (GeoItemData)data.getCountry().getItemIterator().next();
	    GeoItemData itemData2 = null; 
	    while(itemData1.getSubItems() != null) {
	        itemData2 = itemData1;
	        itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
	    }
	    corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),data.getCorporation().getId()));	    	   
	    
	    // Creo que en GeoLevel1 queda el arbol de div geografica de nivel 1 (por ej LA,Mercosur,etc)
	    // GeoLevel1 contiene un GeoLevel2; de esta manera se soportan hasta 3 niveles de profundidad
	    // en el arbol de divisiones geográficas
	    if(itemData2 != null) {
	        geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
	    }
	    //System.out.println("GEO LEVEL2:" + geoLevel1.getGeoLevel2().getResourceKey());
	    
	    Iterator it = data.getDivOrgs().iterator();
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
        
        observations = data.getObservations();
        cost = data.getCost();
        this.optionalInfo = (data.getOptionalInfo() != null)? 
        		new VehicleOptionalInfo(data.getOptionalInfo()):null;
       this.assignedVehicle = (data.getAssignedVehicle() == null) ? null : (AssignedVehicle) session.load(AssignedVehicle.class, data.getAssignedVehicle().getResourceBundleId());
              
       //VEHICLE SMF
       this.vehicleSMF = (data.getVehicleSMFData()!=null)?
    		   new VehicleSMF (data.getVehicleSMFData()):null ;
    	ensurance = data.getEnsurance();
    	hp = data.getHp();
    	
    	for(int i = 1; i <= 21; i++)
    		setExtradata(i, data.getExtradata(i));
    	
    	contractType = data.getContractType();

	}

	
    public void setForestalData(VehicleData data) {

        Session session = HibernateUtil.currentSession();

        suctaExpiry = data.getSuctaExpiry();
        mtopExpiry = data.getMtopExpiry();
        greenCardExpiry = data.getGreenCardExpiry();
        
        ensurance = data.getEnsurance();
        
        if(data.getEnsuranceType() != null && !Util.empty(data.getEnsuranceType().getResourceBundleId())) {
            ensuranceType = (EnsuranceType)session.load(EnsuranceType.class,data.getEnsuranceType().getResourceBundleId());
        }

        ownerCard = data.isOwnerCard();
        axisCount = data.getAxisCount();
        hp = data.getHp();
            
    	
    	for(int i = 1; i <= 21; i++)
    		setExtradata(i, data.getExtradata(i));

    	contractType = data.getContractType();
    }
    
    
    
	public VehicleOptionalInfo getOptionalInfo() {
		return optionalInfo;
	}

	public void setOptionalInfo(VehicleOptionalInfo optionalInfo) {
		this.optionalInfo = optionalInfo;
	}

	/**
	 * @return Returns the abs.
	 */
	public boolean getAbs() {
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
	public boolean getAirBag() {
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
	public boolean getAireAcondicionado() {
		return aireAcondicionado;
	}
	/**
	 * @param airBag The aireAcondicionado to set.
	 */
	public void setAireAcondicionado(boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}	
	/**
	 * @return Returns the ownerShip.
	 */
	public TypeCenterCost getOwnership() {
		return ownership;
	}
	/**
	 * @param ownerShip The ownerShip to set. 
	 */
	public void setOwnership(TypeCenterCost centerCost) {
		this.ownership = centerCost;
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
	 * @return Returns the engine.
	 */
	public TypeEngine getEngine() {
		return engine;
	}
	/**
	 * @param engine The engine to set.
	 */
	public void setEngine(TypeEngine engine) {
		this.engine = engine;
	}
	
	/**
	 * @return Returns the fuel.
	 */
	public TypeFuel getFuel() {
		return fuel;
	}
	/**
	 * @param fuel The fuel to set.
	 */
	public void setFuel(TypeFuel fuel) {
		this.fuel = fuel;
	}
	/**
	 * @return Returns the odometerUnit.
	 */
	public TypeDistanceUnit getOdometerUnit() {
		return odometerUnit;
	}
	/**
	 * @param odometerUnit The odometerUnit to set.
	 */
	public void setOdometerUnit(TypeDistanceUnit odometerUnit) {
		this.odometerUnit = odometerUnit;
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
	 * @return Returns the trade.
	 */
	public TypeTrade getTrade() {
		return trade;
	}
	/**
	 * @param model The trade to set.
	 */
	public void setTrade(TypeTrade trade) {
		this.trade = trade;
	}		
	/**
	 * @return Returns the cover.
	 */
	public TypeCover getCover() {
		return cover;
	}
	/**
	 * @param model The cover to set.
	 */
	public void setCover(TypeCover cover) {
		this.cover = cover;
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
	public TypeVolumeUnit getTankUnit() {
		return tankUnit;
	}
	/**
	 * @param tankUnit The tankUnit to set.
	 */
	public void setTankUnit(TypeVolumeUnit tankUnit) {
		this.tankUnit = tankUnit;
	}
	/**
	 * @return Returns the trie.
	 */
	/*public TypeTrie getTypeTrie() {
		return trie;
	}
	/**
	 * @param trie The trie to set.
	 */
	/*public void setTypeTrie(TypeTrie trie) {
		this.trie = trie;
	}
	*/
	/**
	 * @return Returns the type.
	 */
	public TypeVehicle getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(TypeVehicle type) {
		this.type = type;
	}
	/**
	 * @return Returns the use.
	 */
	public TypeUse getUse() {
		return use;
	}
	/**
	 * @param use The use to set.
	 */
	public void setUse(TypeUse use) {
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
	 * @return Returns the year.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param year The year to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return Returns the year.
	 */
	public Corporation getCorporation() {
		return corpCountry.getPk().getCorporation();
	}
	/**
	 * @param year The year to set.
	 */
	public void setCorporation(Corporation corporation) {
		corpCountry.getPk().setCorporation(corporation);
	}	

	/**
	 * @return Returns the corpCountry.
	 */
	public CorporationCountry getCorpCountry() {
		return corpCountry;
	}
	/**
	 * @param corpCountry The corpCountry to set.
	 */
	public void setCorpCountry(CorporationCountry country) {
		this.corpCountry = country;
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

	/**
	 * @return Returns the divOrgSet.
	 */
	public Driver getDriver() {
		return driver;
	}
	/**
	 * @param divOrgSet The divOrgSet to set.
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}	
	
	public VehicleData getPrincipalAttributes(){
		return getVehicleData();
	}
	
	public void updateData(VehicleData data, Session session)	{
		id = data.getId(); 
		
		//Session session = HibernateUtil.currentSession();
				
	    type = new TypeVehicle();
		type = (TypeVehicle) session.load(type.getClass(),data.getType().getResourceBundleId());
				
		year = data.getYear();
		colour = data.getColour();
		permissionNumber = data.getPermissionNumber();
		
		use = new TypeUse();
		use = (TypeUse) session.load(use.getClass(),data.getUse().getResourceBundleId());
		
		permissionExpiry = data.getPermissionExpiry();
		greenCardExpiry = data.getGreenCardExpiry();
		
		ownership = null;
		System.out.println("OWNERSHIP:" + data.getOwnerShip());
		if (data.getOwnerShip() != null) {
			ownership = new TypeCenterCost();
			ownership = (TypeCenterCost) session.load(ownership.getClass(),data.getOwnerShip().getResourceBundleId());
		}
			
		fuel = null;
		if (data.getFuel() != null) {		
			fuel = new TypeFuel();
			fuel = (TypeFuel) session.load(fuel.getClass(),data.getFuel().getResourceBundleId());
		}	
		
		purchaseDate = data.getPurchaseDate();
		chasisNumber = data.getChasisNumber();
		co2emission = data.getCo2emission();
		consume = data.getConsume();
		
		engine = null;
		if (data.getEngine() != null) {
			engine = new TypeEngine();
			engine = (TypeEngine) session.load(engine.getClass(),data.getEngine().getResourceBundleId());
		}	
		
		tankCapacity = data.getTankCapacity();
		
		tankUnit = null;
		if (data.getTankUnit() != null) {
			tankUnit = new TypeVolumeUnit();
			tankUnit = (TypeVolumeUnit) session.load(tankUnit.getClass(),data.getTankUnit().getResourceBundleId());
		}	
		
		abs = data.getAbs();
		airBag = data.getAirBag();
		aireAcondicionado = data.getAireAcondicionado();		
		suspension = data.getSuspension();		
		
		odometerUnit = null;
		if (data.getOdometerUnit() != null) {
			odometerUnit = new TypeDistanceUnit();
			odometerUnit = (TypeDistanceUnit) session.load(odometerUnit.getClass(),data.getOdometerUnit().getResourceBundleId());
		}	

	    // Cargo corporacion
		//corporation = (Corporation)session.load(Corporation.class,new Integer(2));
		//corporation = (Corporation)session.load(Corporation.class,data.getCorporation().getId());

	    // Cargo driver
		driver = null;
		if (data.getDriver()!=null) {
			driver = (Driver)session.load(Driver.class,data.getDriver().getId());
		}	
		
		// Cargo optional data, por ejmplo pra Klabin
		optionalInfo = null;
		if (data.getOptionalInfo() != null && data.getOptionalInfo().getId() != null){
			optionalInfo = (VehicleOptionalInfo)session.load(VehicleOptionalInfo.class,data.getOptionalInfo().getId());			
		}
		
		matricula = data.getMatricula();
		costCenter = data.getCostCenter();
		exclusionDate = data.getExclusionDate();
		inclusionDate = data.getInclusionDate();
		model = data.getModel();
		
		trade = new TypeTrade();
		trade = (TypeTrade) session.load(trade.getClass(),data.getTrade().getResourceBundleId());
		
		cover = null;
		if (data.getCover() != null) {
			cover = new TypeCover();
			cover = (TypeCover) session.load(cover.getClass(),data.getCover().getResourceBundleId());
		}	
	
		// Recorro arbol(más precisamente rama) de divisiones geográficas para llegar hasta una hoja (que es el pais)
		// En item2 voy llevando el padre
	    GeoItemData itemData1 = (GeoItemData)data.getCountry().getItemIterator().next();
	    GeoItemData itemData2 = null; 
	    while(itemData1.getSubItems() != null) {
	        itemData2 = itemData1;
	        itemData1 = (GeoItemData)itemData2.getSubItems().getItemIterator().next();
	    }
	    corpCountry = (CorporationCountry)session.load(CorporationCountry.class,new CorporationCountryPK(itemData1.getId(),data.getCorporation().getId()));	    	   
	    
	    // Creo que en GeoLevel1 queda el arbol de div geografica de nivel 1 (por ej LA,Mercosur,etc)
	    // GeoLevel1 contiene un GeoLevel2; de esta manera se soportan hasta 3 niveles de profundidad
	    // en el arbol de divisiones geográficas
	    if(itemData2 != null) {
	        geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,itemData2.getId());
	    }
	    //System.out.println("GEO LEVEL2:" + geoLevel1.getGeoLevel2().getResourceKey());
	    
	    Iterator it = data.getDivOrgs().iterator();
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
		    
	    cost = data.getCost();
	    this.assignedVehicle = (data.getAssignedVehicle() == null) ? null : (AssignedVehicle) session.load(AssignedVehicle.class, data.getAssignedVehicle().getResourceBundleId());
	    
	    //VEHICLE SMF
	    this.vehicleSMF = null;
	   
	    if (data.getVehicleSMFData() != null && data.getVehicleSMFData().getVehicleSMFId() != null){	    
		    vehicleSMF = (VehicleSMF)session.load(VehicleSMF.class,data.getVehicleSMFData().getVehicleSMFId()); 	    	
	    }  
	    
        hp = data.getHp();
        ensurance = data.getEnsurance();

    	for(int i = 1; i <= 21; i++)
    		setExtradata(i, data.getExtradata(i));

    	data.setContractType(contractType);
}
	
	/**
	 * 
	 * @return VehicleData
	 */
	public VehicleData getVehicleData() {  
		
		CorporationData corporationData = corpCountry.getPk().getCorporation().getData();
		
		DriverData driverData = null;
		if (driver != null) {
			driverData = driver.getDriverData();
		}
    
	    GeoLabelAttributes[] geoAttrs = corporationData.getGeoLabels();
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
	    
	    Collection divOrgs = new LinkedList();
	    if(divOrg1 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg1,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
	    if(divOrg2 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg2,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
	    if(divOrg3 != null) {
	        DivOrgData divOrgData = getDivOrgData(divOrg3,corporationData);
	        if(divOrgData != null) {
	            divOrgs.add(divOrgData);
	        }
	    }
	    String ownershipId = null;
	    if (ownership != null)
	    	ownershipId = ownership.getResourceBundleId();
	    String fuelId = null;
	    if (fuel != null)
	    	fuelId = fuel.getResourceBundleId();
	    String engineId = null;
	    if (engine != null)
	    	engineId = engine.getResourceBundleId();
	    String tankUnitId = null;
	    if (tankUnit != null)
	    	tankUnitId = tankUnit.getResourceBundleId();
	    String odometerUnitId = null;
	    if (odometerUnit != null)
	    	odometerUnitId = odometerUnit.getResourceBundleId();
	    String coverId = null;
	    if (cover != null)
	    	coverId = cover.getResourceBundleId();
        EnsuranceDataType ensuranceTypeData = null;
        if(ensuranceType != null) {
            ensuranceTypeData = (EnsuranceDataType) ensuranceType.getData();
        }
        
        VehicleOptionalInfoData optionalInfoData = null;
        if (optionalInfo != null){
        	optionalInfoData = optionalInfo.getVehicleOptionalInfoData();
		}
        
        Collection unconformities = new LinkedList();
        if (unconformitySet != null){
        	Iterator unconformitiesIt = unconformitySet.iterator();
            while(unconformitiesIt.hasNext()) {
            	Unconformity u = (Unconformity)unconformitiesIt.next();
            	if (u != null){
            		unconformities.add(u.getData());
            	}
            }
        }
        
        Collection contracts = new LinkedList();
        if (this.contractSet != null && this.contractSet.size() > 0){
        	Iterator contractIt = contractSet.iterator();
        	while(contractIt.hasNext()){
        		VehicleContract contract = (VehicleContract)contractIt.next();
        		if (contract != null){
        			contracts.add(contract.getData());
        		}
        	}
        }

        AssignedVehicleDataType assignedVehicleData = (assignedVehicle == null) ? null : 
        	(AssignedVehicleDataType)assignedVehicle.getData();
        
        //VEHICLE SMF
        VehicleSMFData vehicleSMFData = null;
        if(vehicleSMF!=null && vehicleSMF.getVehicleSMFid() != null){
        	vehicleSMFData = vehicleSMF.getVehicleSMFData(); 
        }
        
        VehicleData v = new VehicleData(id,type.getResourceBundleId(),year,colour,
		   permissionNumber,use.getResourceBundleId(),permissionExpiry,
		   ownershipId,fuelId,purchaseDate,chasisNumber,engineId,tankCapacity,
		   tankUnitId,abs,airBag,aireAcondicionado,suspension,odometerUnitId,
           corporationData,labelData,divOrgs,driverData,matricula,model,
           trade.getResourceBundleId(),coverId, costCenter, inclusionDate, 
           exclusionDate,suctaExpiry,mtopExpiry,ensurance,ensuranceTypeData,
           ownerCard,axisCount,hp,observations,greenCardExpiry,optionalInfoData,
           cost,unconformities,assignedVehicleData, co2emission,consume,contracts,vehicleSMFData,alias);	       
        for(int i = 1; i <= 21; i++)
    		v.setExtradata(i, getExtradata(i));

        v.setContractType(contractType);

        return v;
	}
	
    private DivOrgData getDivOrgData(DivOrgItemLevel1 level1,CorporationData corporationData) {
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

	/**
	 * @return Returns the divOrg1.
	 */
	public DivOrgItemLevel1 getDivOrg1() {
		return divOrg1;
	}

	/**
	 * @param divOrg1 The divOrg1 to set.
	 */
	public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
		this.divOrg1 = divOrg1;
	}

	/**
	 * @return Returns the divOrg2.
	 */
	public DivOrgItemLevel1 getDivOrg2() {
		return divOrg2;
	}

	/**
	 * @param divOrg2 The divOrg2 to set.
	 */
	public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
		this.divOrg2 = divOrg2;
	}

	/**
	 * @return Returns the divOrg3.
	 */
	public DivOrgItemLevel1 getDivOrg3() {
		return divOrg3;
	}

	/**
	 * @param divOrg3 The divOrg3 to set.
	 */
	public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
		this.divOrg3 = divOrg3;
	}
	
	/**
	 * @return Returns the corporationId.
	 */
	public Integer getCorporationId() {
		return corpCountry.getPk().getCorporation().getId();
	}

    /**
     * @return Returns the costCenter.
     */
    public String getCostCenter() {
        return costCenter;
    }

    /**
     * @param costCenter to set the CostCenter.
     */
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }	
    
    /**
     * @return Returns the inclusionDate
     */
    public Date getInclusionDate() {
        return this.inclusionDate;
    }

    /**
     * @param inclusionDate to set the inclusionDate.
     */
    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }	
    /**
     * @return Returns the exclusionDate
     */
    public Date getExclusionDate() {
        return this.exclusionDate;
    }

    /**
     * @param exclusionDate to set the exclusionDate.
     */
    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
    }

    public String getEnsurance() {
        return ensurance;
    }

    public Date getMtopExpiry() {
        return mtopExpiry;
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

    public EnsuranceType getEnsuranceType() {
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

    public void setEnsuranceType(EnsuranceType ensuranceType) {
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

    public GeoItemData getCountry() {
        return country;
    }

    public void setCountry(GeoItemData country) {
        this.country = country;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

	public Set getUnconformitySet() {
		return unconformitySet;
	}

	public void setUnconformitySet(Set unconformitySet) {
		this.unconformitySet = unconformitySet;
	}

	public AssignedVehicle getAssignedVehicle() {
		return assignedVehicle;
	}

	public void setAssignedVehicle(AssignedVehicle assignedVehicle) {
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

	public Float getConsume() {
		return consume;
	}

	public void setConsume(Float consume) {
		this.consume = consume;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * @return the contractSet
	 */
	public Set getContractSet() {
		return contractSet;
	}

	/**
	 * @param contractSet the contractSet to set
	 */
	public void setContractSet(Set contractSet) {
		this.contractSet = contractSet;
	}
	
	public void updateDivisions(Integer[] divOrgs,Session session) {
		if(divOrgs[0] != 0) {
	        divOrg1 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,divOrgs[0]);
		}
		if(divOrgs[1] != 0) {
	        divOrg2 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,divOrgs[1]);
		}
		if(divOrgs[2] != 0) {
	        divOrg3 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,divOrgs[2]);
		}
	}

	public VehicleSMF getVehicleSMF() {
		return vehicleSMF;
	}

	public void setVehicleSMF(VehicleSMF vehicleSMF) {
		this.vehicleSMF = vehicleSMF;
	}

	public boolean isSuspension() {
		return suspension;
	}

	public void setSuspension(boolean suspension) {
		this.suspension = suspension;
	}
	
	public void updateSimpleData(VehicleData data, Session session)	{
		
	    type = new TypeVehicle();
		type = (TypeVehicle) session.load(type.getClass(),data.getType().getResourceBundleId());
				
		year = data.getYear();
		colour = data.getColour();
		permissionNumber = data.getPermissionNumber();
		
		permissionExpiry = data.getPermissionExpiry();
		greenCardExpiry = data.getGreenCardExpiry();
		
		ownership = null;
		System.out.println("OWNERSHIP:" + data.getOwnerShip());
		if (data.getOwnerShip() != null) {
			ownership = new TypeCenterCost();
			ownership = (TypeCenterCost) session.load(ownership.getClass(),data.getOwnerShip().getResourceBundleId());
		}
			
		fuel = null;
		if (data.getFuel() != null) {		
			fuel = new TypeFuel();
			fuel = (TypeFuel) session.load(fuel.getClass(),data.getFuel().getResourceBundleId());
		}	
		
		purchaseDate = data.getPurchaseDate();
		chasisNumber = data.getChasisNumber();
		co2emission = data.getCo2emission();
		consume = data.getConsume();
		
		engine = null;
		if (data.getEngine() != null) {
			engine = new TypeEngine();
			engine = (TypeEngine) session.load(engine.getClass(),data.getEngine().getResourceBundleId());
		}	
		
		tankCapacity = data.getTankCapacity();
		
		tankUnit = null;
		if (data.getTankUnit() != null) {
			tankUnit = new TypeVolumeUnit();
			tankUnit = (TypeVolumeUnit) session.load(tankUnit.getClass(),data.getTankUnit().getResourceBundleId());
		}	
		
		abs = data.getAbs();
		airBag = data.getAirBag();
		aireAcondicionado = data.getAireAcondicionado();		
		suspension = data.getSuspension();		
		
		odometerUnit = null;
		if (data.getOdometerUnit() != null) {
			odometerUnit = new TypeDistanceUnit();
			odometerUnit = (TypeDistanceUnit) session.load(odometerUnit.getClass(),data.getOdometerUnit().getResourceBundleId());
		}	
		
		matricula = data.getMatricula();
		costCenter = data.getCostCenter();
		inclusionDate = data.getInclusionDate();
		model = data.getModel();
		
		trade = new TypeTrade();
		trade = (TypeTrade) session.load(trade.getClass(),data.getTrade().getResourceBundleId());
		
		cover = null;
		if (data.getCover() != null) {
			cover = new TypeCover();
			cover = (TypeCover) session.load(cover.getClass(),data.getCover().getResourceBundleId());
		}	
	    cost = data.getCost();
	    
        hp = data.getHp();
        ensurance = data.getEnsurance();
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

}
