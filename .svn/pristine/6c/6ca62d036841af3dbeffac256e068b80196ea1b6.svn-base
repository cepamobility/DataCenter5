/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.hibernate.Session;

import persistence.administration.corporation.tables.Country;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.DivOrgItemLevel2;
import persistence.administration.corporation.tables.DivOrgItemLevel3;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.administration.corporation.tables.GeoLevel2;
import persistence.datatype.tables.TypeDistanceUnit;
import persistence.datatype.tables.TypeVehicle;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.TypeDistanceUnitDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.fleet.tables.FleetDivisionData;

public class FleetDivision{

    private Integer id;

    private TypeVehicle vehicleType;
    private TypeDistanceUnit odometer;

    private GeoLevel2 geoLevel2;
    private GeoLevel1 geoLevel1;
    private Country country;
    
    private AbstractFleet fleet;

    private DivOrgItemLevel1 divOrg11;
    private DivOrgItemLevel2 divOrg12;
    private DivOrgItemLevel3 divOrg13;

    private DivOrgItemLevel1 divOrg21;
    private DivOrgItemLevel2 divOrg22;
    private DivOrgItemLevel3 divOrg23;

    private DivOrgItemLevel1 divOrg31;
    private DivOrgItemLevel2 divOrg32;
    private DivOrgItemLevel3 divOrg33;
    
    private Date exclusionDate;

    public FleetDivision() {
    }

    public FleetDivision(AbstractFleet fleet,FleetDivisionData data,
    		Session session, UserSessionData userSessionData, Date exclusionDate)
    {
    	this(fleet,data,session,userSessionData);
    	this.exclusionDate = exclusionDate;
    }
    
    public FleetDivision(AbstractFleet fleet,FleetDivisionData data,
    		Session session, UserSessionData userSessionData) {
        this.id = data.getId();
        this.fleet = fleet;
        if(data.getOdometer() != null) {
            this.odometer = (TypeDistanceUnit) session.load(TypeDistanceUnit.class,data.getOdometer().getResourceBundleId());
        }        
        if(data.getVehicleType() != null) {
            this.vehicleType = (TypeVehicle) session.load(TypeVehicle.class,data.getVehicleType().getResourceBundleId());
        }
        GeoItemData item = (GeoItemData)data.getCountry().getItemIterator().next();
        switch(userSessionData.getFilter().getGeoLevelCount()) {
            case 1:
                country = (Country)session.load(Country.class,item.getId());
                break;
            case 2:
                geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,item.getId());
                if(item.getSubItems() != null) {
                    Iterator it = item.getSubItems().getItemIterator();
                    if(it.hasNext()) {
                        GeoItemData item2 = (GeoItemData)it.next();
                        country = (Country)session.load(Country.class,item2.getId());
                    }
                }
                break;
            case 3:
                geoLevel2 = (GeoLevel2)session.load(GeoLevel2.class,item.getId());
                if(item.getSubItems() != null) {
                    Iterator it = item.getSubItems().getItemIterator();
                    if(it.hasNext()) {
                        GeoItemData item2 = (GeoItemData)it.next();
                        geoLevel1 = (GeoLevel1)session.load(GeoLevel1.class,item2.getId());
                        if(item2.getSubItems() != null) {
                            Iterator it2 = item2.getSubItems().getItemIterator();
                            if(it2.hasNext()) {
                                GeoItemData item3 = (GeoItemData)it.next();
                                geoLevel2 = (GeoLevel2)session.load(GeoLevel2.class,item3.getId());
                            }
                        }
                    }
                }
                break;
        }
        int index = 0;
        for(int i = 0; i < 3; i++) {
            LabelItemData labelItemData = data.getDivOrgs()[index];
            if(labelItemData != null) {
                ItemData itemData1 = (ItemData)labelItemData.getItemIterator().next();
                switch(userSessionData.getFilter().getDivOrgLevel(i)) {
                    case 1:
                        switch(index) {
                            case 0:
                                divOrg11 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData1.getId());
                                break;
                            case 1:
                                divOrg21 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData1.getId());
                                break;
                            case 2:
                                divOrg31 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData1.getId());
                                break;
                        }
                        break;
                    case 2:
                        switch(index) {
                            case 0:
                                divOrg12 = (DivOrgItemLevel2)session.load(DivOrgItemLevel2.class,itemData1.getId());
                                break;
                            case 1:
                                divOrg22 = (DivOrgItemLevel2)session.load(DivOrgItemLevel2.class,itemData1.getId());
                                break;
                            case 2:
                                divOrg32 = (DivOrgItemLevel2)session.load(DivOrgItemLevel2.class,itemData1.getId());
                                break;
                        }
                        if(itemData1.getSubItems() != null) {
                            Iterator it1 = itemData1.getSubItems().getItemIterator();
                            if(it1.hasNext()) {
                                ItemData itemData2 = (ItemData)it1.next();
                                switch(index) {
                                    case 0:
                                        divOrg11 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData2.getId());
                                        break;
                                    case 1:
                                        divOrg21 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData2.getId());
                                        break;
                                    case 2:
                                        divOrg31 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData2.getId());
                                        break;
                                }
                            }
                        }
                        break;
                    case 3:
                        switch(index) {
                            case 0:
                                divOrg13 = (DivOrgItemLevel3)session.load(DivOrgItemLevel3.class,itemData1.getId());
                                break;
                            case 1:
                                divOrg23 = (DivOrgItemLevel3)session.load(DivOrgItemLevel3.class,itemData1.getId());
                                break;
                            case 2:
                                divOrg33 = (DivOrgItemLevel3)session.load(DivOrgItemLevel3.class,itemData1.getId());
                                break;
                        }
                        if(itemData1.getSubItems() != null) {
                            Iterator it1 = itemData1.getSubItems().getItemIterator();
                            if(it1.hasNext()) {
                                ItemData itemData2 = (ItemData)it1.next();
                                switch(index) {
                                    case 0:
                                        divOrg12 = (DivOrgItemLevel2)session.load(DivOrgItemLevel2.class,itemData2.getId());
                                        break;
                                    case 1:
                                        divOrg22 = (DivOrgItemLevel2)session.load(DivOrgItemLevel2.class,itemData2.getId());
                                        break;
                                    case 2:
                                        divOrg32 = (DivOrgItemLevel2)session.load(DivOrgItemLevel2.class,itemData2.getId());
                                        break;
                                }
                                if(itemData2.getSubItems() != null) {
                                    Iterator it3 = itemData2.getSubItems().getItemIterator();
                                    if(it3.hasNext()) {
                                        ItemData itemData3 = (ItemData)it3.next();
                                        switch(index) {
                                            case 0:
                                                divOrg11 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData3.getId());
                                                break;
                                            case 1:
                                                divOrg21 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData3.getId());
                                                break;
                                            case 2:
                                                divOrg31 = (DivOrgItemLevel1)session.load(DivOrgItemLevel1.class,itemData3.getId());
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
            }
            index++;
        }

    }

    /**
     * @return Returns the fleet.
     */
    public AbstractFleet getFleet() {
        return fleet;
    }

    /**
     * @param fleet The fleet to set.
     */
    public void setFleet(AbstractFleet fleet) {
        this.fleet = fleet;
    }

    /**
     * @return Returns the corpCountry.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @return Returns the divOrg11.
     */
    public DivOrgItemLevel1 getDivOrg11() {
        return divOrg11;
    }

    /**
     * @return Returns the divOrg12.
     */
    public DivOrgItemLevel2 getDivOrg12() {
        return divOrg12;
    }

    /**
     * @return Returns the divOrg13.
     */
    public DivOrgItemLevel3 getDivOrg13() {
        return divOrg13;
    }

    /**
     * @return Returns the divOrg21.
     */
    public DivOrgItemLevel1 getDivOrg21() {
        return divOrg21;
    }

    /**
     * @return Returns the divOrg22.
     */
    public DivOrgItemLevel2 getDivOrg22() {
        return divOrg22;
    }

    /**
     * @return Returns the divOrg23.
     */
    public DivOrgItemLevel3 getDivOrg23() {
        return divOrg23;
    }

    /**
     * @return Returns the divOrg31.
     */
    public DivOrgItemLevel1 getDivOrg31() {
        return divOrg31;
    }

    /**
     * @return Returns the divOrg32.
     */
    public DivOrgItemLevel2 getDivOrg32() {
        return divOrg32;
    }

    /**
     * @return Returns the divOrg33.
     */
    public DivOrgItemLevel3 getDivOrg33() {
        return divOrg33;
    }

    /**
     * @return Returns the geoLevel1.
     */
    public GeoLevel1 getGeoLevel1() {
        return geoLevel1;
    }

    /**
     * @return Returns the geoLevel2.
     */
    public GeoLevel2 getGeoLevel2() {
        return geoLevel2;
    }

    /**
     * @return Returns the id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param corpCountry The corpCountry to set.
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @param divOrg11 The divOrg11 to set.
     */
    public void setDivOrg11(DivOrgItemLevel1 divOrg11) {
        this.divOrg11 = divOrg11;
    }

    /**
     * @param divOrg12 The divOrg12 to set.
     */
    public void setDivOrg12(DivOrgItemLevel2 divOrg12) {
        this.divOrg12 = divOrg12;
    }

    /**
     * @param divOrg13 The divOrg13 to set.
     */
    public void setDivOrg13(DivOrgItemLevel3 divOrg13) {
        this.divOrg13 = divOrg13;
    }

    /**
     * @param divOrg21 The divOrg21 to set.
     */
    public void setDivOrg21(DivOrgItemLevel1 divOrg21) {
        this.divOrg21 = divOrg21;
    }

    /**
     * @param divOrg22 The divOrg22 to set.
     */
    public void setDivOrg22(DivOrgItemLevel2 divOrg22) {
        this.divOrg22 = divOrg22;
    }

    /**
     * @param divOrg23 The divOrg23 to set.
     */
    public void setDivOrg23(DivOrgItemLevel3 divOrg23) {
        this.divOrg23 = divOrg23;
    }

    /**
     * @param divOrg31 The divOrg31 to set.
     */
    public void setDivOrg31(DivOrgItemLevel1 divOrg31) {
        this.divOrg31 = divOrg31;
    }

    /**
     * @param divOrg32 The divOrg32 to set.
     */
    public void setDivOrg32(DivOrgItemLevel2 divOrg32) {
        this.divOrg32 = divOrg32;
    }

    /**
     * @param divOrg33 The divOrg33 to set.
     */
    public void setDivOrg33(DivOrgItemLevel3 divOrg33) {
        this.divOrg33 = divOrg33;
    }

    /**
     * @param geoLevel1 The geoLevel1 to set.
     */
    public void setGeoLevel1(GeoLevel1 geoLevel1) {
        this.geoLevel1 = geoLevel1;
    }

    /**
     * @param geoLevel2 The geoLevel2 to set.
     */
    public void setGeoLevel2(GeoLevel2 geoLevel2) {
        this.geoLevel2 = geoLevel2;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    public FleetDivisionData getData() {
        
        FleetDivisionData fleetDivisionData = new FleetDivisionData();
        fleetDivisionData.setId(id);

        if(odometer != null) {
            fleetDivisionData.setOdometer((TypeDistanceUnitDataType) odometer.getData());
        }
        if(vehicleType != null) {
            fleetDivisionData.setVehicleType((TypeVehicleDataType) vehicleType.getData());
        }

        fleetDivisionData.setExclusionDate(this.exclusionDate);        
        GeoLabelData labelData1 = null;
        if(country != null) {
            Collection countries = new LinkedList();
            countries.add(new GeoItemData(country.getId(),country.getResourceKey(),true,null));
            labelData1 = new GeoLabelData(null,null,null,countries);
        }
        
        GeoLabelData labelData2 = null;
        if(geoLevel1 != null) {
            Collection geoLevel1s = new LinkedList();
            geoLevel1s.add(new GeoItemData(geoLevel1.getId(),geoLevel1.getResourceKey(),true,labelData1));
            labelData2 = new GeoLabelData(null,null,null,geoLevel1s);
        }
        
        GeoLabelData labelData3 = null;
        if(geoLevel2 != null) {
            Collection geoLevel2s = new LinkedList();
            geoLevel2s.add(new GeoItemData(geoLevel2.getId(),geoLevel2.getResourceKey(),true,labelData2));
            labelData3 = new GeoLabelData(null,null,null,geoLevel2s);
        }
        
        if(labelData3 != null) {
            fleetDivisionData.setCountry(labelData2);
        }else{
            if(labelData2 != null) {
                fleetDivisionData.setCountry(labelData2);
            }else {
                fleetDivisionData.setCountry(labelData1);
            }
        }
        
        fleetDivisionData.setLabelItemData(0,getLabelItemData(divOrg11,divOrg12,divOrg13));
        fleetDivisionData.setLabelItemData(1,getLabelItemData(divOrg21,divOrg22,divOrg23));
        fleetDivisionData.setLabelItemData(2,getLabelItemData(divOrg31,divOrg32,divOrg33));
        
        return fleetDivisionData;
    }
    
    private LabelItemData getLabelItemData(DivOrgItemLevel1 divOrg1,DivOrgItemLevel2 divOrg2,DivOrgItemLevel3 divOrg3) {
        LabelItemData labelItemData1 = null;
        if(divOrg1 != null) {
            Collection level1s = new LinkedList();
            level1s.add(new ItemData(divOrg1.getId(),divOrg1.getResourceKey(),true,null));
            labelItemData1 = new LabelItemData(null,null,level1s,null);
        }
        
        LabelItemData labelItemData2 = null;
        if(divOrg2 != null) {
            Collection level2s = new LinkedList();
            level2s.add(new ItemData(divOrg2.getId(),divOrg2.getResourceKey(),true,labelItemData1));
            labelItemData2 = new LabelItemData(null,null,level2s,null);
        }
        
        LabelItemData labelItemData3 = null;
        if(divOrg3 != null) {
            Collection level3s = new LinkedList();
            level3s.add(new ItemData(divOrg3.getId(),divOrg3.getResourceKey(),true,labelItemData2));
            labelItemData3 = new LabelItemData(null,null,level3s,null);
        }
        
        if(labelItemData3 != null) {
            return labelItemData3;
        }else{
            if(labelItemData2 != null) {
               return labelItemData2;
            }else {
                return labelItemData1;
            }
        }
    }

    public String getDivGeoString(UserSessionData userSessionData) {
        if(country != null) {
            return String.valueOf(country.getId());
        }else {
            Integer level1 = (geoLevel1 == null) ? null : geoLevel1.getId();
            Integer level2 = (geoLevel2 == null) ? null : geoLevel2.getId();
            return userSessionData.getFilter().getDivGeoLeaves(level1,level2);
        }
    }

    public String getDivOrgString(int i,UserSessionData userSessionData) {
        switch(i) {
            case 1:
                return getDivOrgString(i,divOrg11,divOrg12,divOrg13,userSessionData);
            case 2:
                return getDivOrgString(i,divOrg21,divOrg22,divOrg23,userSessionData);
            default:
                return getDivOrgString(i,divOrg31,divOrg32,divOrg33,userSessionData);
        }
    }
    
   private String getDivOrgString(int index,DivOrgItemLevel1 divOrg1,DivOrgItemLevel2 divOrg2,DivOrgItemLevel3 divOrg3,UserSessionData userSessionData) {
       if(divOrg1 != null) {
           return String.valueOf(divOrg1.getId());
       }else {
           Integer level1 = (divOrg2 == null) ? null : divOrg2.getId();
           Integer level2 = (divOrg3 == null) ? null : divOrg3.getId();
           return userSessionData.getFilter().getDivOrgLeaves(index,level1,level2);
       }
    }

    public TypeDistanceUnit getOdometer() {
        return odometer;
    }
    
    public TypeVehicle getVehicleType() {
        return vehicleType;
    }
    
    public void setOdometer(TypeDistanceUnit odometer) {
        this.odometer = odometer;
    }
    
    public void setVehicleType(TypeVehicle vehicleType) {
        this.vehicleType = vehicleType;
    }
    


	public boolean equals(Object o)
	{
		if (FleetDivision.class.isInstance(o))
		{
			FleetDivision fd = (FleetDivision)o;
			if (id.equals(fd.id))
			{
				return true;
			}
			else if (vehicleType.equals(fd.vehicleType) &&
				odometer.equals(fd.odometer) &&
				geoLevel2.equals(fd.geoLevel2) &&
				geoLevel1.equals(fd.geoLevel1) &&
				country.equals(fd.country) &&
				fleet.equals(fd.fleet) &&
				divOrg11.equals(fd.divOrg11) &&
				divOrg12.equals(fd.divOrg12) &&
				divOrg13.equals(fd.divOrg13) &&
				divOrg21.equals(fd.divOrg21) &&
				divOrg22.equals(fd.divOrg22) &&
				divOrg23.equals(fd.divOrg23) &&
				divOrg31.equals(fd.divOrg31) &&
				divOrg32.equals(fd.divOrg32) &&
				divOrg33.equals(fd.divOrg33))
			{
				return true;
			}
			else
				return false;
			
		}else
			return false;
			
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
	
}
