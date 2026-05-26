/**
 * Created on 27-jul-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.vehicle.tables.TrailerData;

/**
 * 
 * @author gbenaderet
 *
 * Nota: 
 *   Para Klabin:
 *           plate = prefijo
 *           Vencimiento Sucta = vencimiento de antt
 *           Vencimiento MTOP = vencimiento de citv
 */
public class TrailerForm  extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
    
    private String plate;
    private String trade;
    private String model;
    private String year;

    private String costCenter;
    private String permissionNumber;
    private String permissionExpiryDay;
    private String permissionExpiryMonth;
    private String permissionExpiryYear;    
    private String purchaseDay;
    private String purchaseMonth;
    private String purchaseYear;    
    private String ownership;
    private String colour;
    private String chasisNumber;
    private String cover;   
    
    
    private Date inclusionDate;
    private Date exclusionDate;

    private String ensuranceType;    
    private String ensurance;
    private String platformType;
    
    private String axisCount;
    
    private String vehicleId;
    
    private String suctaExpiryDate;
    private String suctaExpiryMonth;
    private String suctaExpiryYear;
    
    private String mtopExpiryDate;
    private String mtopExpiryMonth;
    private String mtopExpiryYear;

    private String type;
    private boolean suspension;
    private boolean abs;
    private boolean weightControl;

    public TrailerForm() {
    }

    public TrailerForm(String vehicle) {
        vehicleId = vehicle;
    }

    public TrailerForm(TrailerData data) {
        id = String.valueOf(data.getId());
        chasisNumber = data.getChasisNumber();
        costCenter = data.getCostCenter();

        colour = data.getColour();
        plate = data.getPlate();
        model = data.getModel();      
        permissionNumber = data.getPermissionNumber();
        year = String.valueOf(data.getYear());    
        
        if(data.getPermissionExpiry() != null) {
            Calendar permissionExpiryDate = Calendar.getInstance();
            permissionExpiryDate.setTime(data.getPermissionExpiry());
            permissionExpiryDay = String.valueOf(permissionExpiryDate.get(Calendar.DATE));
            permissionExpiryMonth = String.valueOf(permissionExpiryDate.get(Calendar.MONTH)+1);
            permissionExpiryYear = String.valueOf(permissionExpiryDate.get(Calendar.YEAR));
        }
        if(data.getPurchaseDate() != null) {
            Calendar purchaseDate = Calendar.getInstance();
            purchaseDate.setTime(data.getPurchaseDate());
            purchaseDay = String.valueOf(purchaseDate.get(Calendar.DATE));
            purchaseMonth = String.valueOf(purchaseDate.get(Calendar.MONTH)+1);
            purchaseYear = String.valueOf(purchaseDate.get(Calendar.YEAR));
        } 
        
        if(data.getSuctaExpiry() != null) {
            Calendar suctaDate = Calendar.getInstance();
            suctaDate.setTime(data.getSuctaExpiry());
            this.suctaExpiryDate = String.valueOf(suctaDate.get(Calendar.DATE));
            suctaExpiryMonth = String.valueOf(suctaDate.get(Calendar.MONTH)+1);
            suctaExpiryYear = String.valueOf(suctaDate.get(Calendar.YEAR));
        } 
        
        if(data.getMtopExpiry() != null) {
            Calendar mtopDate = Calendar.getInstance();
            mtopDate.setTime(data.getMtopExpiry());
            this.mtopExpiryDate = String.valueOf(mtopDate.get(Calendar.DATE));
            mtopExpiryMonth = String.valueOf(mtopDate.get(Calendar.MONTH)+1);
            mtopExpiryYear = String.valueOf(mtopDate.get(Calendar.YEAR));
        } 
        
        GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
        setCountry1(String.valueOf(geoItem1.getId()));
        if(geoItem1.getSubItems() != null) {
            GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
            setCountry2(String.valueOf(geoItem2.getId()));
            if(geoItem2.getSubItems() != null) {
                GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
                setCountry3(String.valueOf(geoItem3.getId()));
            }
        }
        
            
        Iterator it = data.getDivOrgsIterator();
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
        
        if (data.getOwnerShip() != null) 
            ownership = data.getOwnerShip().getResourceBundleId();
        
        trade = data.getTrade().getResourceBundleId();
        
        if (data.getCover() != null) 
            cover = data.getCover().getResourceBundleId();                    

        ensurance = data.getEnsurance();
        if(data.getEnsuranceType() != null) {
            ensuranceType = data.getEnsuranceType().getResourceBundleId();
        }
        
        if (data.getPlatformType() != null){
        	platformType = data.getPlatformType().getResourceBundleId();
        }
        
        axisCount = String.valueOf(data.getAxisCount());
        
        //CAMPOS MDP
        if(data.getType()!=null){
        	type=data.getType();
        }
        
        suspension=data.isSuspension();
        abs=data.isAbs();
        weightControl=data.isWeightControl();

    }
        
    public String getChasisNumber() {
        return chasisNumber;
    }

    public String getColour() {
        return colour;
    }

    public String getCover() {
        return cover;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSuspension() {
		return suspension;
	}

	public void setSuspension(boolean suspension) {
		this.suspension = suspension;
	}

	public boolean isAbs() {
		return abs;
	}

	public void setAbs(boolean abs) {
		this.abs = abs;
	}

	public boolean isWeightControl() {
		return weightControl;
	}

	public void setWeightControl(boolean weightControl) {
		this.weightControl = weightControl;
	}

	public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public String getPurchaseDay() {
        return purchaseDay;
    }

    public String getPurchaseMonth() {
        return purchaseMonth;
    }

    public String getPurchaseYear() {
        return purchaseYear;
    }

    public String getTrade() {
        return trade;
    }

    public String getYear() {
        return year;
    }
    
    

    public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public void setChasisNumber(String chasisNumber) {
        this.chasisNumber = chasisNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setPurchaseDay(String purchaseDay) {
        this.purchaseDay = purchaseDay;
    }

    public void setPurchaseMonth(String purchaseMonth) {
        this.purchaseMonth = purchaseMonth;
    }

    public void setPurchaseYear(String purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public TrailerData createTrailerData(UserSessionData userSession) {
        
        GeoLabelData country = Util.getGeoLabelData(this);
        Collection list = Util.getDivOrgs(this,userSession);
        Integer ident = (id != null) ? new Integer(id) : null;
               
        Integer axis = null;
        if(!Util.empty(axisCount)) {
            axis = new Integer(axisCount.trim());
        }

        TrailerData data= new TrailerData(ident,new Integer(year.trim()),
                colour,permissionNumber,getPermissionExpiryDate(),ownership,
                getPurchaseDate(),chasisNumber,new CorporationData(userSession.getFilter().getCorporationId(),"",null,null,null,null),
                country,list,plate,model,trade,cover, costCenter,getInclusionDate(), getExclusionDate(),ensuranceType,ensurance,
                axis,getMtopExpiry(),getSuctaExpiry(),platformType);
        
        if(type!=null){
        	data.setType(type);	
        }
        
        data.setAbs(abs);
        data.setSuspension(suspension);
        data.setWeightControl(weightControl);
        
        return data;
    }

    private Date getPurchaseDate() {
        if (Util.empty(purchaseDay) || Util.empty(purchaseMonth) || Util.empty(purchaseYear)) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE,Integer.parseInt(purchaseDay.trim()));
            calendar.set(Calendar.MONTH,Integer.parseInt(purchaseMonth.trim())-1);
            calendar.set(Calendar.YEAR,Integer.parseInt(purchaseYear.trim()));
            return calendar.getTime();
        }
    }

    private Date getPermissionExpiryDate() {
        if (Util.empty(permissionExpiryDay) || Util.empty(permissionExpiryMonth) || Util.empty(permissionExpiryYear)) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE,Integer.parseInt(permissionExpiryDay.trim()));
            calendar.set(Calendar.MONTH,Integer.parseInt(permissionExpiryMonth.trim())-1);
            calendar.set(Calendar.YEAR,Integer.parseInt(permissionExpiryYear.trim()));
            return calendar.getTime();
        }
    }

    public String validate(UserSessionData userRequest) {

	    if(isEmpty(getPlate())) {
	        return "generic.vehicle.vehicledata.registrationNumberInvalid";
	    }
/*	    if(isEmpty(getUse())) {
	        return "generic.vehicle.vehicledata.useInvalid";
	    }
	    if(isEmpty(getType())) {
	        return "generic.vehicle.vehicledata.typeInvalid";
	    }*/	    
	    if(isEmpty(getTrade())) {
	        return "generic.vehicle.vehicledata.tradeInvalid";
	    }	    
	    if(isEmpty(getModel())) {
	        return "generic.vehicle.vehicledata.modelInvalid";
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
    
	    	    
	    if(! isEmpty(getTankCapacity()) && notNumber(getTankCapacity().trim())) {
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
     */
	    
	    for(int j = 0; j < userRequest.getFilter().getDivOrgCount(); j++) {
            for(int i = 1; i <= userRequest.getFilter().getDivOrgLevel(j); i++) {
                if(Util.empty(getDivOrg(j+1,i))) {
                    return "generic.vehicle.vehicledata.emptyDivOrg";
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
  
	    return null;
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
    
    private boolean notNumber(String value) {
    	
	    try {
	    	new Integer(value.trim());
	    }
	    catch (Exception e) {
	    	return true;
	    }	
	    return false;
    }
    
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getAxisCount() {
        return axisCount;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public String getEnsurance() {
        return ensurance;
    }

    public String getEnsuranceType() {
        return ensuranceType;
    }

    public Date getExclusionDate() {
        return exclusionDate;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public String getOwnership() {
        return ownership;
    }

    public String getPermissionExpiryDay() {
        return permissionExpiryDay;
    }

    public String getPermissionExpiryMonth() {
        return permissionExpiryMonth;
    }

    public String getPermissionExpiryYear() {
        return permissionExpiryYear;
    }

    public String getPermissionNumber() {
        return permissionNumber;
    }

    public void setAxisCount(String axisCount) {
        this.axisCount = axisCount;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public void setEnsurance(String ensurance) {
        this.ensurance = ensurance;
    }

    public void setEnsuranceType(String ensuranceType) {
        this.ensuranceType = ensuranceType;
    }

    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public void setPermissionExpiryDay(String permissionExpiryDay) {
        this.permissionExpiryDay = permissionExpiryDay;
    }

    public void setPermissionExpiryMonth(String permissionExpiryMonth) {
        this.permissionExpiryMonth = permissionExpiryMonth;
    }

    public void setPermissionExpiryYear(String permissionExpiryYear) {
        this.permissionExpiryYear = permissionExpiryYear;
    }

    public void setPermissionNumber(String permissionNumber) {
        this.permissionNumber = permissionNumber;
    }
    
    public Date getSuctaExpiry() {
        if(isEmpty(suctaExpiryDate) && isEmpty(suctaExpiryMonth) && isEmpty(suctaExpiryYear)) {
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
        if(isEmpty(mtopExpiryDate) && isEmpty(mtopExpiryMonth) && isEmpty(mtopExpiryYear)) {
            return null;
        } else { 
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE,Integer.parseInt(mtopExpiryDate.trim()));
            calendar.set(Calendar.MONTH,Integer.parseInt(mtopExpiryMonth.trim())-1);
            calendar.set(Calendar.YEAR,Integer.parseInt(mtopExpiryYear.trim()));
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

	public String getMtopExpiryDate() {
		return mtopExpiryDate;
	}

	public void setMtopExpiryDate(String mtopExpiryDate) {
		this.mtopExpiryDate = mtopExpiryDate;
	}

	public String getMtopExpiryMonth() {
		return mtopExpiryMonth;
	}

	public void setMtopExpiryMonth(String mtopExpiryMonth) {
		this.mtopExpiryMonth = mtopExpiryMonth;
	}

	public String getMtopExpiryYear() {
		return mtopExpiryYear;
	}

	public void setMtopExpiryYear(String mtopExpiryYear) {
		this.mtopExpiryYear = mtopExpiryYear;
	}

	public String getSuctaExpiryDate() {
		return suctaExpiryDate;
	}

	public void setSuctaExpiryDate(String suctaExpiryDate) {
		this.suctaExpiryDate = suctaExpiryDate;
	}

	public String getSuctaExpiryMonth() {
		return suctaExpiryMonth;
	}

	public void setSuctaExpiryMonth(String suctaExpiryMonth) {
		this.suctaExpiryMonth = suctaExpiryMonth;
	}

	public String getSuctaExpiryYear() {
		return suctaExpiryYear;
	}

	public void setSuctaExpiryYear(String suctaExpiryYear) {
		this.suctaExpiryYear = suctaExpiryYear;
	}
    
    

}
