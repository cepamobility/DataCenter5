/**
 * Created on 27-jul-2007
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.datatype.tables.EnsuranceDataType;
import communication.datatype.tables.PlatformDataType;
import communication.datatype.tables.TypeCenterCostDataType;
import communication.datatype.tables.TypeCoverDataType;
import communication.datatype.tables.TypeTradeDataType;

public class TrailerData extends TrailerMainData implements Serializable {

    private Integer year;
    private String colour;
    private Date purchaseDate;
    private String chasisNumber;
    private TypeCoverDataType cover;

    private String permissionNumber;
    private Date permissionExpiry;
    private TypeCenterCostDataType ownerShip;
    private CorporationData corporation;
    private GeoLabelData country;
    private Collection divOrgs; 
    private String costCenter;
    private Date inclusionDate;
    private Date exclusionDate;
    
    private EnsuranceDataType ensuranceType;
    private String ensurance;
    private Integer axisCount;
    
    
    private Date mtopExpiry;
    private Date suctaExpiry;
    private PlatformDataType platformType;
    
    private String type;
    private boolean suspension;
    private boolean abs;
    private boolean weightControl;
    
    public TrailerData() {
        super();
    }


    /**
     * @param id
     * @param plate
     * @param year
     * @param colour
     * @param purchaseDate
     * @param chasisNumber
     * @param model
     * @param trade
     * @param cover
     * @param permissionNumber
     * @param permissionExpiry
     * @param ownerShip
     * @param fuel
     * @param corporation
     * @param country
     * @param divOrgs
     * @param costCenter
     * @param inclusionDate
     * @param exclusionDate
     * @param ensuranceType
     * @param ensurance
     * @param axisCount
     */
    public TrailerData(Integer id, String plate, Integer year, String colour, Date purchaseDate, String chasisNumber, String model, 
            TypeTradeDataType trade, TypeCoverDataType cover, String permissionNumber, Date permissionExpiry, 
            TypeCenterCostDataType ownerShip, CorporationData corporation, GeoLabelData country, Collection divOrgs, String costCenter, 
            Date inclusionDate, Date exclusionDate, EnsuranceDataType ensuranceType, String ensurance, Integer axisCount,
            Date mtopExpiry, Date suctaExpiry, PlatformDataType platformType) {
        super(id,plate,model,trade);
        this.year = year;
        this.colour = colour;
        this.purchaseDate = purchaseDate;
        this.chasisNumber = chasisNumber;
        this.cover = cover;
        this.permissionNumber = permissionNumber;
        this.permissionExpiry = permissionExpiry;
        this.ownerShip = ownerShip;
        this.corporation = corporation;
        this.country = country;
        this.divOrgs = divOrgs;
        this.costCenter = costCenter;
        this.inclusionDate = inclusionDate;
        this.exclusionDate = exclusionDate;
        this.ensuranceType = ensuranceType;
        this.ensurance = ensurance;
        this.axisCount = axisCount;
        this.mtopExpiry = mtopExpiry;
        this.suctaExpiry = suctaExpiry;
        this.platformType = platformType;
    }


    public TrailerData(Integer id, Integer year, String colour, String permissionNumber, Date permissionExpiry, 
            String ownershipId, Date purchaseDate, String chasisNumber, CorporationData data, GeoLabelData country, 
            Collection divOrgs, String plate, String model, String tradeId, String coverId, String costCenter, Date inclusionDate, 
            Date exclusionDate, String ensuranceTypeId, String ensurance, Integer axisCount, 
            Date mtopExpiry, Date suctaExpiry, String  platformTypeId) {
        super(id,plate,model,null);
        this.year = year;
        this.colour = colour;
        this.purchaseDate = purchaseDate;
        this.chasisNumber = chasisNumber;
        
        if (!empty(platformTypeId)){
        	this.platformType = new PlatformDataType(platformTypeId);
        }
        
        if (!empty(tradeId)) {
            this.trade = new TypeTradeDataType(tradeId);
        }
        if (!empty(coverId)) {      
            this.cover = new TypeCoverDataType(coverId);
        }   
        this.costCenter = costCenter;
        this.permissionNumber = permissionNumber;
        this.permissionExpiry = permissionExpiry;
        if (!empty(ownershipId)) {      
            this.ownerShip = new TypeCenterCostDataType(ownershipId);
        }
        this.corporation = data;
        this.country = country;
        this.divOrgs = divOrgs;
        this.costCenter = costCenter;
        this.inclusionDate = inclusionDate;
        this.exclusionDate = exclusionDate;
        if (!empty(ensuranceTypeId)) {
            this.ensuranceType = new EnsuranceDataType(ensuranceTypeId);
        }
        this.ensurance = ensurance;
        this.axisCount = axisCount;
        this.mtopExpiry = mtopExpiry;
        this.suctaExpiry = suctaExpiry;
    }



    public TrailerData(TrailerMainData trailer) {
        super(trailer.getId(),trailer.getPlate(),trailer.getModel(),trailer.getTrade());
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


	public String getChasisNumber() {
        return chasisNumber;
    }

    public String getColour() {
        return colour;
    }

    public TypeCoverDataType getCover() {
        return cover;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setChasisNumber(String chasisNumber) {
        this.chasisNumber = chasisNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setCover(TypeCoverDataType cover) {
        this.cover = cover;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPermissionNumber() {
        return permissionNumber;
    }

    public Date getPermissionExpiry() {
        return permissionExpiry;
    }

    public TypeCenterCostDataType getOwnerShip() {
        return ownerShip;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public Date getExclusionDate() {
        return exclusionDate;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public CorporationData getCorporation() {
        return corporation;
    }

    public Collection getDivOrgs() {
        return divOrgs;
    }

    public String getEnsurance() {
        return ensurance;
    }

    public EnsuranceDataType getEnsuranceType() {
        return ensuranceType;
    }

    public Integer getAxisCount() {
        return axisCount;
    }


    public GeoLabelData getCountry() {
        return country;
    }


    public void setAxisCount(Integer axisCount) {
        this.axisCount = axisCount;
    }


    public void setCorporation(CorporationData corporation) {
        this.corporation = corporation;
    }


    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }


    public void setCountry(GeoLabelData country) {
        this.country = country;
    }


    public void setDivOrgs(Collection divOrgs) {
        this.divOrgs = divOrgs;
    }


    public void setEnsurance(String ensurance) {
        this.ensurance = ensurance;
    }


    public void setEnsuranceType(EnsuranceDataType ensuranceType) {
        this.ensuranceType = ensuranceType;
    }


    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
    }


    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
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

    private boolean empty(String value) {
        return (value == null || value.trim().length() == 0);
    }
    
    public Iterator getDivOrgsIterator() {
        Collections.sort((List) divOrgs);
        return divOrgs.iterator();
    }


	public Date getMtopExpiry() {
		return mtopExpiry;
	}


	public void setMtopExpiry(Date mtopExpiry) {
		this.mtopExpiry = mtopExpiry;
	}


	public Date getSuctaExpiry() {
		return suctaExpiry;
	}


	public void setSuctaExpiry(Date suctaExpiry) {
		this.suctaExpiry = suctaExpiry;
	}


	public PlatformDataType getPlatformType() {
		return platformType;
	}


	public void setPlatformType(PlatformDataType platformType) {
		this.platformType = platformType;
	}

	
    
}
