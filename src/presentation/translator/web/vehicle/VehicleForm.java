/* Created on 24-feb-2006

 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


package presentation.translator.web.vehicle;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.util.Filter;

/**
 * @author adocarmo
 *
 */
public class VehicleForm extends DriverDivOrgForm {

//public class VehicleForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;    
	private String type;
	private String year;
	private String colour;
	private String permissionNumber;
	private String use;
	private String permissionExpiryDay;
	private String permissionExpiryMonth;
	private String permissionExpiryYear;	
	private String fuel;
	private String purchaseDay;
	private String purchaseMonth;
	private String purchaseYear;	
	private String chasisNumber;
	private String co2emission; // J&J
	private String consume; // J&J
	private String engine;
	private String tankCapacity;
	private String tankUnit;
	private String abs;
	private String airBag;
	private String distanceUnit;
	private String ownership;
	private String corporation;	
	private String trade;
	private String cover;	

	private String matricula;	
	private String model;
	private String propietario;	
	private String aireAcondicionado;
	private String suspension;
	
	private String driver;
	
	private String comment;	
	private String costCenter;
	private Date inclusionDate;
	private Date exclusionDate;

    private String suctaExpiryDate;
    private String suctaExpiryMonth;
    private String suctaExpiryYear;
    
    private String mtopExpiryDate;
    private String mtopExpiryMonth;
    private String mtopExpiryYear;
    
    private String greenCardExpiryDate;
    private String greenCardExpiryMonth;
    private String greenCardExpiryYear;
    
    private String ensuranceType;
    
    private String ensurance;
	
    private String ownerCard;
    private String axisCount;
    private String hp;

    private String observations;
    
    /* Vehicle Optional Info (Klabin, Yoli, PEPSICO, CIMSA)*/
    private String idOptionalInfo;
    private String prefix;
    private String totalGrossWeight;
    private String totalCombinedGrossWeight;

    private String monitoringSystem;
    private String tachograph;
    
    private String renavan;
    private String limitLoad;
    private String asr;
    private String currentKm;
    private String dateCurrentKmDate;
    private String dateCurrentKmMonth;
    private String dateCurrentKmYear;
    private String anttExpiryDate;
    private String anttExpiryMonth;
    private String anttExpiryYear;
    private String citvExpiryDate;
    private String citvExpiryMonth;
    private String citvExpiryYear;

    private String cost;

	private String assignedVehicle;
    
    //VEHICLE SMF MONSANTO
	private Integer vehicleSMFid;
	private Integer orderNumber;
	private String corporationSMF; 
	private Date instalationDateSMF;
	private String responsibleTechnical;
	private String localInstallation;
	private String observationsSMF;
	private String ustContact;
	private String yearSMF;
	private String monthSMF;
	private String daySMF;
		
	public Date getExclusionDate() {
		return exclusionDate;
	}
    
	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}
    
	public Date getInclusionDate() {
		return inclusionDate;
	}
    
	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
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
	 * @return Returns the propietario.
	 */
	public String getPropietario() {
		return propietario;
	}
	/**
	 * @param propietario The propietario to set.
	 */
	public void setPropietario(String propietario) {
		this.propietario = propietario;
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
	 * @return Returns the corporation.
	 */
	public String getCorporation() {
		return corporation;
	}
	/**
	 * @param corporation The corporation to set.
	 */
	public void setCorporation(String corporation) {
		this.corporation = corporation;
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
	 * @return Returns the distanceUnit.
	 */
	public String getDistanceUnit() {
		return distanceUnit;
	}
	/**
	 * @param distanceUnit The distanceUnit to set.
	 */
	public void setDistanceUnit(String odometerUnit) {
		this.distanceUnit = odometerUnit;
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
	 * @return Returns the permissionExpiryDay.
	 */
	public String getPermissionExpiryDay() {
		return permissionExpiryDay;
	}

	/**
	 * @param permissionExpiryDay
	 *            The permissionExpiryDay to set.
	 */
	public void setPermissionExpiryDay(String permissionExpiryDay) {
		this.permissionExpiryDay = permissionExpiryDay;
	}
	/**
	 * @return Returns the permissionExpiryMonth.
	 */
	public String getPermissionExpiryMonth() {
		return permissionExpiryMonth;
	}
	/**
	 * @param permissionExpiryMonth The permissionExpiryMonth to set.
	 */
	public void setPermissionExpiryMonth(String permissionExpiryMonth) {
		this.permissionExpiryMonth = permissionExpiryMonth;
	}
	/**
	 * @return Returns the permissionExpiryYear.
	 */
	public String getPermissionExpiryYear() {
		return permissionExpiryYear;
	}
	/**
	 * @param permissionExpiryYear The permissionExpiryYear to set.
	 */
	public void setPermissionExpiryYear(String permissionExpiryYear) {
		this.permissionExpiryYear = permissionExpiryYear;
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
	 * @return Returns the purchaseDateMonth.
	 */
	public String getPurchaseMonth() {
		return purchaseMonth;
	}
	/**
	 * @param purchaseDateMonth The purchaseDateMonth to set.
	 */
	public void setPurchaseMonth(String purchaseMonth) {
		this.purchaseMonth = purchaseMonth;
	}
	/**
	 * @return Returns the purchaseDateYear.
	 */
	public String getPurchaseYear() {
		return purchaseYear;
	}
	/**
	 * @param purchaseDateYear The purchaseDateYear to set.
	 */
	public void setPurchaseYear(String purchaseYear) {
		this.purchaseYear = purchaseYear;
	}

	/**
	 * @return Returns the purchaseDay.
	 */
	public String getPurchaseDay() {
		return purchaseDay;
	}

	/**
	 * @param purchaseDay
	 *            The purchaseDay to set.
	 */
	public void setPurchaseDay(String purchaseDay) {
		this.purchaseDay = purchaseDay;
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
     * @param name
     * @param i
     * @param j
     */
    public void setDivOrg(String name, int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        divOrg11 = name;
                        break;
                    case 2:
                        divOrg12 = name;
                        break;
                    case 3:
                        divOrg13 = name;
                        break;
                }
                break;
            case 2:
                switch(j) {
                    case 1:
                        divOrg21 = name;
                        break;
                    case 2:
                        divOrg22 = name;
                        break;
                    case 3:
                        divOrg23 = name;
                        break;
                }
                break;
            case 3:
                switch(j) {
                    case 1:
                        divOrg31 = name;
                        break;
                    case 2:
                        divOrg32 = name;
                        break;
                    case 3:
                        divOrg33 = name;
                        break;
                }
                break;
        }
    }

    public String getDivOrg(int i, int j) {
        switch(i) {
            case 1:
                switch(j) {
                    case 1:
                        return divOrg11;
                    case 2:
                        return divOrg12;
                    case 3:
                        return divOrg13;
                }
            case 2:
                switch(j) {
                    case 1:
                        return divOrg21;
                    case 2:
                        return divOrg22;
                    case 3:
                        return divOrg23;
                }
            case 3:
                switch(j) {
                    case 1:
                        return divOrg31;
                    case 2:
                        return divOrg32;
                    case 3:
                        return divOrg33;
                }
        }
        return null;
    }
    
	/**
	 * @return Returns the volumeUnit.
	 */
/*	public String getVolumeUnit() {
		return volumeUnit;
	}
	/**
	 * @param volumeUnit The volumeUnit to set.
	 */
/*	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}
*/	
	/**
	 * @return Returns the marca.
	 */
	public String getTrade() {
		return trade;
	}
	/**
	 * @param marca The marca to set.
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}
	/**
	 * @return Returns the model.
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param modelo The model to set.
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return Returns the cover.
	 */
	public String getCover() {
		return cover;
	}
	/**
	 * @param cubierta The cover to set.
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}
	
    public Date getPermissionExpiryDate() {
    	if (isEmpty(permissionExpiryDay) || isEmpty(permissionExpiryMonth) || isEmpty(permissionExpiryYear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(permissionExpiryDay.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(permissionExpiryMonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(permissionExpiryYear.trim()));
            return calendar.getTime();
    	}
    }
    
    
    public Date getPurchaseDate() {
        if(isEmpty(purchaseDay) && isEmpty(purchaseMonth) && isEmpty(purchaseYear)) {
            return null;
        } else { 
        	Calendar calendar = Calendar.getInstance();
        	calendar.set(Calendar.DATE,Integer.parseInt(purchaseDay.trim()));
        	calendar.set(Calendar.MONTH,Integer.parseInt(purchaseMonth.trim())-1);
        	calendar.set(Calendar.YEAR,Integer.parseInt(purchaseYear.trim()));
        	return calendar.getTime();
        }
    }

	/**
	 * @return Returns the driver.
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * @param driver The driver to set.
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}    
    
	public String validate(UserSessionData userRequest) {

	    if(isEmpty(getMatricula())) {
	        return "generic.vehicle.vehicledata.registrationNumberInvalid";
	    }
	    if(isEmpty(getUse())) {
	        return "generic.vehicle.vehicledata.useInvalid";
	    }
	    if(isEmpty(getType())) {
	        return "generic.vehicle.vehicledata.typeInvalid";
	    }	    
	    if(isEmpty(getTrade())) {
	        return "generic.vehicle.vehicledata.tradeInvalid";
	    }	    
	    if(isEmpty(getModel())) {
	        return "generic.vehicle.vehicledata.modelInvalid";
	    }	    	    
	    
	    if(userRequest.getCorporationId() == CorporationData.JJ) {
		    if(!isEmpty(co2emission) && notDecimal(co2emission)) {
		        return "generic.vehicle.vehicledata.co2Invalid";
		    }	    	    
		    if(!isEmpty(consume) && notDecimal(consume)) {
		        return "generic.vehicle.vehicledata.consumeInvalid";
		    }	    	    
	    }
/*	    if(isEmpty(getFuel())) {
	        return "generic.vehicle.vehicledata.fuelInvalid";
	    }
	    if(isEmpty(getDistanceUnit())) {
	        return "generic.vehicle.vehicledata.odometerInvalid";
	    }	    
	    if(isEmpty(getOwnership())) {
	        return "generic.vehicle.vehicledata.ownershipInvalid";
	    }	    
	    if(isEmpty(getEngine())) {
	        return "generic.vehicle.vehicledata.engineInvalid";
	    }	    	    
	    if(isEmpty(getTankUnit())) {
	        return "generic.vehicle.vehicledata.tankUnitInvalid";
	    }	    	    
	    if(isEmpty(getCover())) {
	        return "generic.vehicle.vehicledata.coverInvalid";
	    }	    	    	    
*/	    	    
	    if(isEmpty(getYear()) || notNumber(getYear().trim())) {
	        return "generic.vehicle.vehicledata.yearInvalid";
	    }
	    
/*	    
	    if(! isEmpty(getPermissionNumber()) && notNumber(getPermissionNumber().trim())) {
	        return "generic.vehicle.vehicledata.permissionNumberInvalid";
	    }
	    if(! isEmpty(getChasisNumber()) && notNumber(getChasisNumber().trim())) {
	        return "generic.vehicle.vehicledata.chasisNumberInvalid";
	    }
*/	    
	    	    
	    if(!userRequest.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.DOW}) && (! isEmpty(getTankCapacity()) && notNumber(getTankCapacity().trim()))) {
	        return "generic.vehicle.vehicledata.tankCapacityInvalid";
	    }	    
	    
	    if (anyEmpty(purchaseDay,purchaseMonth,purchaseYear)) 
	    	return "generic.vehicle.vehicledata.purchaseDateInvalid";
	    else {
		    if((! isEmpty(purchaseDay) && notNumber(purchaseDay.trim())) || 
		    		(! isEmpty(purchaseMonth) && notNumber(purchaseMonth.trim()) ||
		    		(! isEmpty(purchaseYear) && notNumber(purchaseYear.trim())))) {
		    	return "generic.vehicle.vehicledata.purchaseDateInvalid";
		    }
	    }

	    if (anyEmpty(permissionExpiryDay,permissionExpiryMonth,permissionExpiryYear))
	    	return "generic.vehicle.vehicledata.permissionExpiryDateInvalid";
	    else {
	    	if((! isEmpty(permissionExpiryDay) && notNumber(permissionExpiryDay.trim())) || 
	    			(! isEmpty(permissionExpiryMonth) && notNumber(permissionExpiryMonth.trim()) ||
	    			(! isEmpty(permissionExpiryYear) && notNumber(permissionExpiryYear.trim())))) {
	    		return "generic.vehicle.vehicledata.permissionExpiryDateInvalid";
	    	}	
	    }	    
        
	    for(int j = 0; j < userRequest.getFilter().getDivOrgCount(); j++) {
            for(int i = 1; i <= userRequest.getFilter().getDivOrgLevel(j); i++) {
            	String divOrg=getDivOrg(j+1,i);
                if(Util.empty(getDivOrg(j+1,i))) {
                    return "generic.vehicle.vehicledata.emptyDivOrg";
                }
            }
        }        
	    
	    //No se permite N/A para DPA
        if(userRequest.getFilter().getCorporationId().intValue() == CorporationData.DPA){
        	Iterator iter=userRequest.getFilter().getLevelItems(1, 0, null, null, Filter.USER);
        	while(iter.hasNext()){
        		ItemData item=(ItemData)iter.next();
	        	if(Integer.valueOf(divOrg11).equals(item.getId())){
	        		if(item.getName().contains("N/A")){ 
	        			return "generic.vehicle.vehicledata.invaliddivorg";
	        		}
	        	}
        	}
        	iter=userRequest.getFilter().getLevelItems(2, 0, divOrg11, null, Filter.USER);
        	while(iter.hasNext()){
        		ItemData item=(ItemData)iter.next();
	        	if(Integer.valueOf(divOrg12).equals(item.getId())){
	        		if(item.getName().contains("N/A")){ 
	        			return "generic.vehicle.vehicledata.invaliddivorg";
	        		}
	        	}
        	}
        	
        	iter=userRequest.getFilter().getLevelItems(3, 0, divOrg11, divOrg12, Filter.USER);
        	while(iter.hasNext()){
        		ItemData item=(ItemData)iter.next();
	        	if(Integer.valueOf(divOrg13).equals(item.getId())){
	        		if(item.getName().contains("N/A")){ 
	        			return "generic.vehicle.vehicledata.invaliddivorg";
	        		}
	        	}
        	}
        }

        if(isEmpty(getCountry1())) {
            return "generic.vehicle.vehicledata.emptyDivGeo";
        }
        if(userRequest.getFilter().getGeoLevelCount()>1 && isEmpty(getCountry2())) {
            return "generic.vehicle.vehicledata.emptyDivGeo";
        }
        if(userRequest.getFilter().getGeoLevelCount()>2 && isEmpty(getCountry3())) {
            return "generic.vehicle.vehicledata.emptyDivGeo";
        }
  
        if(userRequest.getFilter().getCorporationId().intValue() == CorporationData.KLABIN
            || userRequest.getFilter().getCorporationId().intValue() == CorporationData.YOLI) {
            if(notNumber(currentKm)) {
                return "vehicle.error.currentkm";
            }
        }
	    return null;
	}   
	
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}	    
    
    private boolean notNumber(String value) {
    	
	    try {
	    	new Integer(value.trim());
	    }
	    catch (Exception e) {
	    	return true;
	    }	
	    return false;
	}

    private boolean notDecimal(String value) {
	    try {
	    	new Float(value.trim());
	    }catch (Exception e) {
	    	return true;
	    }	
	    return false;
	}

    private boolean anyEmpty(String value1,String value2,String value3) {
    	int any = 0;
    	if ((value1 == null) || (value1.trim().length()==0))
    		any ++;
    	if ((value2 == null) || (value2.trim().length()==0))
    		any ++;
    	if ((value3 == null) || (value3.trim().length()==0))
    		any ++;
	    return ((any > 0 && any < 3));
	}	    

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	suspension = null;
    	airBag = null;
    	abs = null;
    	aireAcondicionado = null;
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
	
	/**
     * @return Returns the costCenter.
     */
    public String getCostCenter() {
        return this.costCenter;
    }
	
	/**
	 * @param costCenter The costs's Center to set 
	 */
	public void setCostCenter(String costCenter)
	{
		this.costCenter = costCenter;
	}
	 
	/**
	 * @return Returns the country1.
	 */
	public String getCountry1() {
		return country1;
	}
	/**
	 * @param country1 The country1 to set.
	 */
	public void setCountry1(String country1) {
		this.country1 = country1;
	}
	/**
	 * @return Returns the country2.
	 */
	public String getCountry2() {
		return country2;
	}
	/**
	 * @param country2 The country2 to set.
	 */
	public void setCountry2(String country2) {
		this.country2 = country2;
	}
	/**
	 * @return Returns the country3.
	 */
	public String getCountry3() {
		return country3;
	}
	/**
	 * @param country3 The country3 to set.
	 */
	public void setCountry3(String country3) {
		this.country3 = country3;
	}
	
	
    /**
     * @param divOrg11
     * @param divOrg12
     * @param divOrg13
     * @param divOrg21
     * @param divOrg22
     * @param divOrg23
     * @param divOrg31
     * @param divOrg32
     * @param divOrg33
     * @param country1
     * @param country2
     * @param country3
     */
    public VehicleForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }
    
    public VehicleForm()
    {
    	super();
    }
    
    /*
	 *  (non-Javadoc)
	 * @see presentation.translator.web.driver.DriverDivOrgForm#getLink()
	 * Genera un link con los parametros del form
	 */
	public String getLink() {
        StringBuffer link = new StringBuffer("");
        link.append((!Util.empty(type)) ? "&type="+type : "");
        link.append((!Util.empty(year)) ? "&year="+year : "");
        link.append((!Util.empty(colour)) ? "&c="+colour : "");
        link.append((!Util.empty(permissionNumber)) ? "&pN="+permissionNumber : "");	        
        link.append((!Util.empty(use)) ? "&use="+use : "");	        
        link.append((!Util.empty(permissionExpiryDay)) ? "&pED="+permissionExpiryDay : "");	        
        link.append((!Util.empty(permissionExpiryMonth)) ? "&pEM="+permissionExpiryMonth : "");	        
        link.append((!Util.empty(permissionExpiryYear)) ? "&pEY="+permissionExpiryYear : "");	        
        link.append((!Util.empty(fuel)) ? "&fuel="+fuel : "");	        
        link.append((!Util.empty(purchaseDay)) ? "&pD="+purchaseDay : "");	        
        link.append((!Util.empty(purchaseMonth)) ? "&pM="+purchaseMonth : "");	        
        link.append((!Util.empty(purchaseYear)) ? "&pY="+purchaseYear : "");	        
        link.append((!Util.empty(chasisNumber)) ? "&cN="+chasisNumber : "");	        
        link.append((!Util.empty(engine)) ? "&e="+engine : "");	        
        link.append((!Util.empty(tankCapacity)) ? "&tC="+tankCapacity : "");	        
        link.append((!Util.empty(tankUnit)) ? "&tU="+tankUnit : "");	        
        link.append((!Util.empty(abs)) ? "&abs="+abs : "");	        
        link.append((!Util.empty(airBag)) ? "&aB="+airBag : "");	        
        link.append((!Util.empty(distanceUnit)) ? "&dU="+distanceUnit : "");	        
        link.append((!Util.empty(ownership)) ? "&os="+ownership : "");	        
        link.append((!Util.empty(corporation)) ? "&corp="+corporation : "");	 
        link.append((!Util.empty(trade)) ? "&tr="+trade : "");	 
        link.append((!Util.empty(cover)) ? "&cv="+cover : "");	 
        link.append((!Util.empty(matricula)) ? "&mat="+matricula : "");	 
        link.append((!Util.empty(model)) ? "&md="+model: "");	 
        link.append((!Util.empty(aireAcondicionado)) ? "&aA="+aireAcondicionado: "");	 
        link.append((!Util.empty(driver)) ? "&dr="+ driver: "");	 	        
        link.append((!Util.empty(comment)) ? "&cm="+ comment: "");
        link.append((!Util.empty(costCenter)) ? "&cC="+ costCenter: "");
        //link.append((!Util.empty(inclusionDate)) ? "&iD="+ inclusionDate: "");
        //link.append((!Util.empty(exclusionDate)) ? "&eD="+ exclusionDate: "");
        link.append(super.getLink());
        return link.toString();
    }
	
	
    
    public Date getSuctaExpiry() {
        if(isEmpty(suctaExpiryDate) || isEmpty(suctaExpiryMonth) || isEmpty(suctaExpiryYear)) {
            return null;
        } else { 
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE,Integer.parseInt(suctaExpiryDate.trim()));
            calendar.set(Calendar.MONTH,Integer.parseInt(suctaExpiryMonth.trim())-1);
            calendar.set(Calendar.YEAR,Integer.parseInt(suctaExpiryYear.trim()));
            return calendar.getTime();
        }
    }

    public Date getMtopExpiry() {
        if(isEmpty(mtopExpiryDate) || isEmpty(mtopExpiryMonth) || isEmpty(mtopExpiryYear)) {
            return null;
        } else { 
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE,Integer.parseInt(mtopExpiryDate.trim()));
            calendar.set(Calendar.MONTH,Integer.parseInt(mtopExpiryMonth.trim())-1);
            calendar.set(Calendar.YEAR,Integer.parseInt(mtopExpiryYear.trim()));
            return calendar.getTime();
        }
    }
    
    
    public Date getGreenCardExpiry() {
    	if (isEmpty(greenCardExpiryDate) || isEmpty(greenCardExpiryMonth) || isEmpty(greenCardExpiryYear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(greenCardExpiryDate.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(greenCardExpiryMonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(greenCardExpiryYear.trim()));
            return calendar.getTime();
    	}
    }
    
    public Date getAnttExpiry() {
    	if (isEmpty(anttExpiryDate) || isEmpty(anttExpiryMonth) || isEmpty(anttExpiryYear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(anttExpiryDate.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(anttExpiryMonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(anttExpiryYear.trim()));
            return calendar.getTime();
    	}
    }
    
    public Date getCitvExpiry() {
    	if (isEmpty(citvExpiryDate) || isEmpty(citvExpiryMonth) || isEmpty(citvExpiryYear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(citvExpiryDate.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(citvExpiryMonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(citvExpiryYear.trim()));
            return calendar.getTime();
    	}
    }
    
    public Date getDateCurrentKm() {
    	if (isEmpty(dateCurrentKmDate) || isEmpty(dateCurrentKmMonth) || isEmpty(dateCurrentKmYear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(dateCurrentKmDate.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(dateCurrentKmMonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(dateCurrentKmYear.trim()));
            return calendar.getTime();
    	}
    }


    public String getSuctaDate() {
        if(!Util.empty(suctaExpiryDate) && !Util.empty(suctaExpiryMonth) && !Util.empty(suctaExpiryYear)) {
            return suctaExpiryDate+"-"+suctaExpiryMonth+"-"+suctaExpiryYear;
        }
        return null;
    }

    public String getMtopDate() {
        if(!Util.empty(mtopExpiryDate) && !Util.empty(mtopExpiryMonth) && !Util.empty(mtopExpiryYear)) {
            return mtopExpiryDate+"-"+mtopExpiryMonth+"-"+mtopExpiryYear;
        }
        return null;
    }
    
    public String getCitvDate() {
        if(!Util.empty(citvExpiryDate) && !Util.empty(citvExpiryMonth) && 
        		!Util.empty(citvExpiryYear)) {
            return citvExpiryDate+"-"+citvExpiryMonth+"-"+citvExpiryYear;
        }
        return null;
    }

    public String getAnttDate() {
        if(!Util.empty(anttExpiryDate) && !Util.empty(anttExpiryMonth) && 
        		!Util.empty(anttExpiryYear)) {
            return anttExpiryDate+"-"+anttExpiryMonth+"-"+anttExpiryYear;
        }
        return null;
    }
    
    public String getCurrentKmDate() {
        if(!Util.empty(dateCurrentKmDate) && !Util.empty(dateCurrentKmMonth) && 
        		!Util.empty(dateCurrentKmYear)) {
            return dateCurrentKmDate+"-"+dateCurrentKmMonth+"-"+dateCurrentKmYear;
        }
        return null;
    }
    
    public String getGreenCardDate() {
        if(!Util.empty(greenCardExpiryDate) && !Util.empty(greenCardExpiryMonth) && !Util.empty(greenCardExpiryYear)) {
            return greenCardExpiryDate+"-"+greenCardExpiryMonth+"-"+greenCardExpiryYear;
        }
        return null;
    }
    
    public String getEnsurance() {
        return ensurance;
    }
    
    public void setEnsurance(String ensurance) {
        this.ensurance = ensurance;
    }

    public String getMtopExpiryDate() {
        return mtopExpiryDate;
    }

    public String getMtopExpiryMonth() {
        return mtopExpiryMonth;
    }

    public String getMtopExpiryYear() {
        return mtopExpiryYear;
    }

    public String getSuctaExpiryDate() {
        return suctaExpiryDate;
    }

    public String getSuctaExpiryMonth() {
        return suctaExpiryMonth;
    }

    public String getSuctaExpiryYear() {
        return suctaExpiryYear;
    }

    public void setMtopExpiryDate(String mtopExpiryDate) {
        this.mtopExpiryDate = mtopExpiryDate;
    }

    public void setMtopExpiryMonth(String mtopExpiryMonth) {
        this.mtopExpiryMonth = mtopExpiryMonth;
    }

    public void setMtopExpiryYear(String mtopExpiryYear) {
        this.mtopExpiryYear = mtopExpiryYear;
    }

    public void setSuctaExpiryDate(String suctaExpiryDate) {
        this.suctaExpiryDate = suctaExpiryDate;
    }

    public void setSuctaExpiryMonth(String suctaExpiryMonth) {
        this.suctaExpiryMonth = suctaExpiryMonth;
    }

    public void setSuctaExpiryYear(String suctaExpiryYear) {
        this.suctaExpiryYear = suctaExpiryYear;
    }

    public String getAxisCount() {
        return axisCount;
    }

    public String getEnsuranceType() {
        return ensuranceType;
    }

    public String getHp() {
        return hp;
    }

    public String getOwnerCard() {
        return ownerCard;
    }

    public void setAxisCount(String axisCount) {
        this.axisCount = axisCount;
    }

    public void setEnsuranceType(String ensuranceType) {
        this.ensuranceType = ensuranceType;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public void setOwnerCard(String ownerCard) {
        this.ownerCard = ownerCard;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

	public String getGreenCardExpiryMonth() {
		return greenCardExpiryMonth;
	}

	public void setGreenCardExpiryMonth(String greenCardExpiryMonth) {
		this.greenCardExpiryMonth = greenCardExpiryMonth;
	}

	public String getGreenCardExpiryYear() {
		return greenCardExpiryYear;
	}

	public void setGreenCardExpiryYear(String greenCardExpiryYear) {
		this.greenCardExpiryYear = greenCardExpiryYear;
	}

	public void setGreenCardExpiryDate(String greenCardExpiryDate) {
		this.greenCardExpiryDate = greenCardExpiryDate;
	}

	public String getGreenCardExpiryDate() {
		return greenCardExpiryDate;
	}

	public String getAnttExpiryDate() {
		return anttExpiryDate;
	}

	public void setAnttExpiryDate(String anttExpiryDate) {
		this.anttExpiryDate = anttExpiryDate;
	}

	public String getAnttExpiryMonth() {
		return anttExpiryMonth;
	}

	public void setAnttExpiryMonth(String anttExpiryMonth) {
		this.anttExpiryMonth = anttExpiryMonth;
	}

	public String getAnttExpiryYear() {
		return anttExpiryYear;
	}

	public void setAnttExpiryYear(String anttExpiryYear) {
		this.anttExpiryYear = anttExpiryYear;
	}

	public String getAsr() {
		return asr;
	}

	public void setAsr(String asr) {
		this.asr = asr;
	}

	public String getCitvExpiryDate() {
		return citvExpiryDate;
	}

	public void setCitvExpiryDate(String citvExpiryDate) {
		this.citvExpiryDate = citvExpiryDate;
	}

	public String getCitvExpiryMonth() {
		return citvExpiryMonth;
	}

	public void setCitvExpiryMonth(String citvExpiryMonth) {
		this.citvExpiryMonth = citvExpiryMonth;
	}

	public String getCitvExpiryYear() {
		return citvExpiryYear;
	}

	public void setCitvExpiryYear(String citvExpiryYear) {
		this.citvExpiryYear = citvExpiryYear;
	}

	public String getCurrentKm() {
		return currentKm;
	}

	public void setCurrentKm(String currentKm) {
		this.currentKm = currentKm;
	}

	public String getDateCurrentKmDate() {
		return dateCurrentKmDate;
	}

	public void setDateCurrentKmDate(String dateCurrentKmDate) {
		this.dateCurrentKmDate = dateCurrentKmDate;
	}

	public String getDateCurrentKmMonth() {
		return dateCurrentKmMonth;
	}

	public void setDateCurrentKmMonth(String dateCurrentKmMonth) {
		this.dateCurrentKmMonth = dateCurrentKmMonth;
	}

	public String getDateCurrentKmYear() {
		return dateCurrentKmYear;
	}

	public void setDateCurrentKmYear(String dateCurrentKmYear) {
		this.dateCurrentKmYear = dateCurrentKmYear;
	}

	public String getLimitLoad() {
		return limitLoad;
	}

	public void setLimitLoad(String limitLoad) {
		this.limitLoad = limitLoad;
	}

	public String getMonitoringSystem() {
		return monitoringSystem;
	}

	public void setMonitoringSystem(String monitoringSystem) {
		this.monitoringSystem = monitoringSystem;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public String getTachograph() {
		return tachograph;
	}

	public void setTachograph(String tachograph) {
		this.tachograph = tachograph;
	}

	public String getTotalCombinedGrossWeight() {
		return totalCombinedGrossWeight;
	}

	public void setTotalCombinedGrossWeight(String totalCombinedGrossWeight) {
		this.totalCombinedGrossWeight = totalCombinedGrossWeight;
	}

	public String getTotalGrossWeight() {
		return totalGrossWeight;
	}

	public void setTotalGrossWeight(String totalGrossWeight) {
		this.totalGrossWeight = totalGrossWeight;
	}

	public String getIdOptionalInfo() {
		return idOptionalInfo;
	}

	public void setIdOptionalInfo(String idOptionalInfo) {
		this.idOptionalInfo = idOptionalInfo;
	}

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

	public AssignedVehicleDataType getAssignedVehicleData() {
		return (Util.empty(assignedVehicle)) ? null : new AssignedVehicleDataType(assignedVehicle);
	}

	public String getAssignedVehicle() {
		return assignedVehicle;
	}
	
	public void setAssignedVehicle(String assignedVehicle) {
		this.assignedVehicle = assignedVehicle;
	}

	/**
	 * @return the co2emission
	 */
	public String getCo2emission() {
		return co2emission;
	}

	/**
	 * @param co2emission the co2emission to set
	 */
	public void setCo2emission(String co2emission) {
		this.co2emission = co2emission;
	}

	public String getConsume() {
		return consume;
	}

	public void setConsume(String consume) {
		this.consume = consume;
	}

	//VEHICLE SMF
	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCorporationSMF() {
		return corporationSMF;
	}

	public void setCorporationSMF(String corporationSMF) {
		this.corporationSMF = corporationSMF;
	}

	public Date getInstalationDateSMF() {
		instalationDateSMF=Util.getDate(daySMF, monthSMF, yearSMF);
		return instalationDateSMF;
	}

	public void setInstalationDateSMF(Date instalationDateSMF) {
		this.instalationDateSMF = instalationDateSMF;
	}

	public String getResponsibleTechnical() {
		return responsibleTechnical;
	}

	public void setResponsibleTechnical(String responsibleTechnical) {
		this.responsibleTechnical = responsibleTechnical;
	}

	public String getLocalInstallation() {
		return localInstallation;
	}

	public void setLocalInstallation(String localInstallation) {
		this.localInstallation = localInstallation;
	}

	public String getObservationsSMF() {
		return observationsSMF;
	}

	public void setObservationsSMF(String observationsSMF) {
		this.observationsSMF = observationsSMF;
	}

	public String getUstContact() {
		return ustContact;
	}

	public void setUstContact(String ustContact) {
		this.ustContact = ustContact;
	}

	public String getYearSMF() {
		return yearSMF;
	}

	public void setYearSMF(String yearSMF) {
		this.yearSMF = yearSMF;
	}

	public String getMonthSMF() {
		return monthSMF;
	}

	public void setMonthSMF(String monthSMF) {
		this.monthSMF = monthSMF;
	}

	public String getDaySMF() {
		return daySMF;
	}

	public void setDaySMF(String daySMF) {
		this.daySMF = daySMF;
	}

	public String getDateInstalation() {
		if(!Util.empty(daySMF) && !Util.empty(monthSMF) && !Util.empty(yearSMF)){
					
		return daySMF+"-"+monthSMF+"-"+yearSMF;
		}
		return null;
	}

	public Integer getVehicleSMFid() {
		return vehicleSMFid;
	}
	

	public void setVehicleSMFid(Integer idVehicleSMF) {
		this.vehicleSMFid = idVehicleSMF;
	}

	public String getSuspension() {
		return suspension;
	}

	public void setSuspension(String suspension) {
		this.suspension = suspension;
	}
	
	
}	