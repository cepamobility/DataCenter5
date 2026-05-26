package persistence.vehicle.tables;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.hibernate.Session;

import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.EnsuranceType;
import persistence.datatype.tables.PlatformType;
import persistence.datatype.tables.TypeCenterCost;
import persistence.datatype.tables.TypeCover;
import persistence.datatype.tables.TypeTrade;
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
import communication.administration.user.UserSessionData;
import communication.datatype.tables.EnsuranceDataType;
import communication.datatype.tables.PlatformDataType;
import communication.datatype.tables.TypeCenterCostDataType;
import communication.datatype.tables.TypeCoverDataType;
import communication.datatype.tables.TypeTradeDataType;
import communication.driver.tables.DriverData;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleOptionalInfoData;

/**
 * @author adocarmo 
 */
public class Trailer {

	private Integer id;

    private String plate;
    private String model;
    private TypeTrade trade;       
    private TypeCover cover;
	
    private Integer year;
	private String colour;
	private Date purchaseDate;
	private String chasisNumber;
		
    private String permissionNumber;
    private Date permissionExpiry;
    private TypeCenterCost ownership;
    
    private CorporationCountry corpCountry;
    private GeoLevel1 geoLevel1; 

    private DivOrgItemLevel1 divOrg1;
    private DivOrgItemLevel1 divOrg2;
    private DivOrgItemLevel1 divOrg3;    

    private String costCenter;
    
    private Date inclusionDate;
    private Date exclusionDate;
    
    private EnsuranceType ensuranceType;
    private String ensurance;
    
    private Date mtopExpiry;
    private Date suctaExpiry;

    private Integer axisCount;
    private PlatformType platformType;

    private String type;
    private boolean suspension;
    private boolean abs;
    private boolean weightControl;
    
    
    public Trailer() {
	}
	
	
	/**
     * @param id
     * @param plate
     * @param model
     * @param trade
     * @param cover
     * @param year
     * @param colour
     * @param purchaseDate
     * @param chasisNumber
     * @param permissionNumber
     * @param use
     * @param permissionExpiry
     * @param ownership
     * @param corpCountry
     * @param geoLevel1
     * @param divOrg1
     * @param divOrg2
     * @param divOrg3
     * @param costCenter
     * @param inclusionDate
     * @param exclusionDate
     * @param ensuranceType
     * @param ensurance
     * @param axisCount
     */
    public Trailer(Integer id, String plate, String model, TypeTrade trade, 
    		TypeCover cover, Integer year, String colour, Date purchaseDate, 
    		String chasisNumber, String permissionNumber, Date permissionExpiry, 
    		TypeCenterCost ownership, CorporationCountry corpCountry, 
    		GeoLevel1 geoLevel1, DivOrgItemLevel1 divOrg1, DivOrgItemLevel1 divOrg2, 
    		DivOrgItemLevel1 divOrg3, String costCenter, Date inclusionDate, 
    		Date exclusionDate, EnsuranceType ensuranceType, String ensurance, 
    		Integer axisCount, Date mtopExpiry, Date suctaExpiry, PlatformType platformType) {
        super();
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.trade = trade;
        this.cover = cover;
        this.year = year;
        this.colour = colour;
        this.purchaseDate = purchaseDate;
        this.chasisNumber = chasisNumber;
        this.permissionNumber = permissionNumber;
        this.permissionExpiry = permissionExpiry;
        this.ownership = ownership;
        this.corpCountry = corpCountry;
        this.geoLevel1 = geoLevel1;
        this.divOrg1 = divOrg1;
        this.divOrg2 = divOrg2;
        this.divOrg3 = divOrg3;
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


    public Trailer(TrailerData data, UserSessionData userSessionData) {
        id = data.getId(); 
        setData(data);
    }

    public Trailer(TrailerData data) {
        id = data.getId(); 
        setData(data);
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
	 * @return Returns the matricula.
	 */
	public String getPlate() {
		return plate;
	}
    
	/**
	 * @param matricula The matricula to set.
	 */
	public void setPlate(String plate) {
		this.plate = plate;
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
	
	
	public TrailerData getTrailerData() {  

        CorporationData corporationData = corpCountry.getPk().getCorporation().getData();

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

        TypeTradeDataType tradeData = null;
        if(trade != null) {
            tradeData = (TypeTradeDataType)trade.getData();
        }
        TypeCoverDataType coverData = null;
        if(cover != null) {
            coverData = (TypeCoverDataType)cover.getData();
        }
        TypeCenterCostDataType ownershipData = null;
        if(ownership != null) {
            ownershipData = (TypeCenterCostDataType)ownership.getData();
        }
        EnsuranceDataType ensuranceTypeData = null;
        if(ensuranceType != null) {
            ensuranceTypeData = (EnsuranceDataType)ensuranceType.getData();
        }
        
        PlatformDataType platformDataType = null;
        if (platformType != null){
        	platformDataType = (PlatformDataType)platformType.getData();
        }
        TrailerData data=new TrailerData(id,plate,year,colour,purchaseDate,chasisNumber,model,
                tradeData,coverData,permissionNumber,permissionExpiry, 
                ownershipData,corporationData,labelData,divOrgs, 
                costCenter, inclusionDate, exclusionDate, ensuranceTypeData,
                ensurance, axisCount, mtopExpiry,suctaExpiry,platformDataType);
        data.setType(type);
        data.setAbs(abs);
        data.setSuspension(suspension);
        data.setWeightControl(weightControl);
        
        return data; 			       
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

    public CorporationCountry getCorpCountry() {
        return corpCountry;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public DivOrgItemLevel1 getDivOrg1() {
        return divOrg1;
    }

    public DivOrgItemLevel1 getDivOrg2() {
        return divOrg2;
    }

    public DivOrgItemLevel1 getDivOrg3() {
        return divOrg3;
    }

    public String getEnsurance() {
        return ensurance;
    }

    public GeoLevel1 getGeoLevel1() {
        return geoLevel1;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public TypeCenterCost getOwnership() {
        return ownership;
    }

    public Date getPermissionExpiry() {
        return permissionExpiry;
    }

    public String getPermissionNumber() {
        return permissionNumber;
    }

    public void setCorpCountry(CorporationCountry corpCountry) {
        this.corpCountry = corpCountry;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public void setDivOrg1(DivOrgItemLevel1 divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(DivOrgItemLevel1 divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(DivOrgItemLevel1 divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setEnsurance(String ensurance) {
        this.ensurance = ensurance;
    }

    public void setGeoLevel1(GeoLevel1 geoLevel1) {
        this.geoLevel1 = geoLevel1;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setOwnership(TypeCenterCost ownership) {
        this.ownership = ownership;
    }

    public void setPermissionExpiry(Date permissionExpiry) {
        this.permissionExpiry = permissionExpiry;
    }

    public void setPermissionNumber(String permissionNumber) {
        this.permissionNumber = permissionNumber;
    }

    public Date getExclusionDate() {
        return exclusionDate;
    }

    public void setExclusionDate(Date exclusionDate) {
        this.exclusionDate = exclusionDate;
    }


    public Integer getAxisCount() {
        return axisCount;
    }


    public EnsuranceType getEnsuranceType() {
        return ensuranceType;
    }


    public void setAxisCount(Integer axisCount) {
        this.axisCount = axisCount;
    }


    public void setEnsuranceType(EnsuranceType ensuranceType) {
        this.ensuranceType = ensuranceType;
    }


    public void setData(TrailerData data) {
        Session session = HibernateUtil.currentSession();
        
        year = data.getYear();
        colour = data.getColour();
        permissionNumber = data.getPermissionNumber();
        
        permissionExpiry = data.getPermissionExpiry();
        
        ownership = null;
        if (data.getOwnerShip() != null) {
            ownership = (TypeCenterCost)session.load(TypeCenterCost.class,data.getOwnerShip().getResourceBundleId());
        }
        

            
        purchaseDate = data.getPurchaseDate();
        chasisNumber = data.getChasisNumber();
        
        plate = data.getPlate();
        costCenter = data.getCostCenter();
        exclusionDate = data.getExclusionDate();
        inclusionDate = data.getInclusionDate();
        mtopExpiry = data.getMtopExpiry();
        suctaExpiry = data.getSuctaExpiry();
        model = data.getModel();
        
        trade = null;
        if (data.getTrade() != null && !Util.empty(data.getTrade().getResourceBundleId())){
        	trade = (TypeTrade)session.load(TypeTrade.class,data.getTrade().getResourceBundleId());
        }

        cover = null;
        if (data.getCover() != null && !Util.empty(data.getCover().getResourceBundleId())) {
            cover = (TypeCover)session.load(TypeCover.class,data.getCover().getResourceBundleId());
        }   
        
        platformType = null;
        if (data.getPlatformType() != null && !Util.empty(data.getPlatformType().getResourceBundleId())){
        	platformType = (PlatformType)session.load(PlatformType.class,data.getPlatformType().getResourceBundleId());
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

        ensurance = data.getEnsurance();
        
        if(data.getEnsuranceType() != null && !Util.empty(data.getEnsuranceType().getResourceBundleId())) {
            ensuranceType = (EnsuranceType)session.load(EnsuranceType.class,data.getEnsuranceType().getResourceBundleId());
        }

        axisCount = data.getAxisCount();
        
        type=data.getType();
        weightControl=data.isWeightControl();
        suspension=data.isSuspension();
        abs=data.isAbs();
        
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


	public PlatformType getPlatformType() {
		return platformType;
	}


	public void setPlatformType(PlatformType platformType) {
		this.platformType = platformType;
	}

	 /**
     * Genera un objeto VehicleData a partir de un Trailer con los datos
     * comunes entre el Trailer y el Vehicle. Para mostrar en el reporte
     * de Vehiculos y Plataformas de Carga.
     * @param trailer
     * @param userSessionData
     * @return
     */
	public VehicleData getVehicleData() {  
		
		CorporationData corporationData = corpCountry.getPk().getCorporation().getData();
		
		DriverData driverData = null;
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
	    String engineId = null;
	    String tankUnitId = null;
	    String odometerUnitId = null;
	    String coverId = null;
	    if (cover != null)
	    	coverId = cover.getResourceBundleId();
        EnsuranceDataType ensuranceTypeData = null;
        if(ensuranceType != null) {
            ensuranceTypeData = (EnsuranceDataType) ensuranceType.getData();
        }
        String observations = null;
        Integer hp = null;
        Date greenCardExpiry = null;
        VehicleOptionalInfoData optionalInfoData = null;
        
	    VehicleData a = new VehicleData(id,null,year,colour,permissionNumber,
	    		null,permissionExpiry,ownershipId,fuelId,purchaseDate,chasisNumber,engineId,null,
	    		tankUnitId,false,false,false,false,odometerUnitId,corporationData,labelData,divOrgs,driverData,
	    		plate,model,trade.getResourceBundleId(),coverId,costCenter,
	    		inclusionDate,exclusionDate, suctaExpiry,mtopExpiry,ensurance,ensuranceTypeData, false,
	    		axisCount,hp,observations,greenCardExpiry,optionalInfoData,null,new LinkedList(),null,
	    		null,null,null,null,null);
	    
	    a.setReportMoreInfo(new HashMap<String, String>());
	    a.getReportMoreInfo().put("type", type);
	    a.getReportMoreInfo().put("suspension", (suspension?"SI":"NO"));
	    a.getReportMoreInfo().put("abs", (abs?"SI":"NO"));
	    a.getReportMoreInfo().put("weightControl", (weightControl?"SI":"NO"));
	    
	    return a;
	    
	}



	
	
	
}
