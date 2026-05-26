package persistence.log.fleet;

import java.io.Serializable;
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

public class FleetDivisionLog implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AbstractFleetLog fleetLog;
    
    private String vehicleType;
    private String odometer;

    private Integer geoLevel2;
    private Integer geoLevel1;
    private Integer country;
    
    private Integer divOrg11;
    private Integer divOrg12;
    private Integer divOrg13;

    private Integer divOrg21;
    private Integer divOrg22;
    private Integer divOrg23;

    private Integer divOrg31;
    private Integer divOrg32;
    private Integer divOrg33;

    private Date exclusionDate;
    
    public FleetDivisionLog() {
    }

    public FleetDivisionLog(AbstractFleetLog fleetLog,FleetDivisionData data,
    		Session session,UserSessionData userSessionData)
    {
        this.fleetLog = fleetLog;
        if(data.getOdometer() != null) {
            this.odometer = data.getOdometer().getResourceBundleId();
        }
        if (data.getExclusionDate() != null){
        	this.exclusionDate = data.getExclusionDate();
        }
        if(data.getVehicleType() != null) {
            this.vehicleType = data.getVehicleType().getResourceBundleId();
        }
        GeoItemData item = (GeoItemData)data.getCountry().getItemIterator().next();
        switch(userSessionData.getFilter().getGeoLevelCount()) {
            case 1:
                country = item.getId();
                break;
            case 2:
                geoLevel1 = item.getId();
                if(item.getSubItems() != null) {
                    Iterator it = item.getSubItems().getItemIterator();
                    if(it.hasNext()) {
                        GeoItemData item2 = (GeoItemData)it.next();
                        country = item2.getId();
                    }
                }
                break;
            case 3:
                geoLevel2 = item.getId();
                if(item.getSubItems() != null) {
                    Iterator it = item.getSubItems().getItemIterator();
                    if(it.hasNext()) {
                        GeoItemData item2 = (GeoItemData)it.next();
                        geoLevel1 = item2.getId();
                        if(item2.getSubItems() != null) {
                            Iterator it2 = item2.getSubItems().getItemIterator();
                            if(it2.hasNext()) {
                                GeoItemData item3 = (GeoItemData)it.next();
                                geoLevel2 = item3.getId();
                            }
                        }
                    }
                }
                break;
        }
        int index = 0;  for(int i = 0; i < 3; i++) {
            LabelItemData labelItemData = data.getDivOrgs()[index];
            if(labelItemData != null) {
                ItemData itemData1 = (ItemData)labelItemData.getItemIterator().next();
                switch(userSessionData.getFilter().getDivOrgLevel(i)) {
                    case 1:
                        switch(index) {
                            case 0:
                                divOrg11 = itemData1.getId();
                                break;
                            case 1:
                                divOrg21 = itemData1.getId();
                                break;
                            case 2:
                                divOrg31 = itemData1.getId();
                                break;
                        }
                        break;
                    case 2:
                        switch(index) {
                            case 0:
                                divOrg12 = itemData1.getId();
                                break;
                            case 1:
                                divOrg22 = itemData1.getId();
                                break;
                            case 2:
                                divOrg32 = itemData1.getId();
                                break;
                        }
                        if(itemData1.getSubItems() != null) {
                            Iterator it1 = itemData1.getSubItems().getItemIterator();
                            if(it1.hasNext()) {
                                ItemData itemData2 = (ItemData)it1.next();
                                switch(index) {
                                    case 0:
                                        divOrg11 = itemData2.getId();
                                        break;
                                    case 1:
                                        divOrg21 = itemData2.getId();
                                        break;
                                    case 2:
                                        divOrg31 = itemData2.getId();
                                        break;
                                }
                            }
                        }
                        break;
                    case 3:
                        switch(index) {
                            case 0:
                                divOrg13 = itemData1.getId();
                                break;
                            case 1:
                                divOrg23 = itemData1.getId();
                                break;
                            case 2:
                                divOrg33 = itemData1.getId();
                                break;
                        }
                        if(itemData1.getSubItems() != null) {
                            Iterator it1 = itemData1.getSubItems().getItemIterator();
                            if(it1.hasNext()) {
                                ItemData itemData2 = (ItemData)it1.next();
                                switch(index) {
                                    case 0:
                                        divOrg12 = itemData2.getId();
                                        break;
                                    case 1:
                                        divOrg22 = itemData2.getId();
                                        break;
                                    case 2:
                                        divOrg32 = itemData2.getId();
                                        break;
                                }
                                if(itemData2.getSubItems() != null) {
                                    Iterator it3 = itemData2.getSubItems().getItemIterator();
                                    if(it3.hasNext()) {
                                        ItemData itemData3 = (ItemData)it3.next();
                                        switch(index) {
                                            case 0:
                                                divOrg11 = itemData3.getId();
                                                break;
                                            case 1:
                                                divOrg21 = itemData3.getId();
                                                break;
                                            case 2:
                                                divOrg31 = itemData3.getId();
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
    public AbstractFleetLog getFleetLog() {
        return fleetLog;
    }

    /**
     * @param fleet The fleet to set.
     */
    public void setFleetLog(AbstractFleetLog fleetLog) {
        this.fleetLog = fleetLog;
    }

    /**
     * @return Returns the corpCountry.
     */
    public Integer getCountry() {
        return country;
    }

    /**
     * @return Returns the divOrg11.
     */
    public Integer getDivOrg11() {
        return divOrg11;
    }

    /**
     * @return Returns the divOrg12.
     */
    public Integer getDivOrg12() {
        return divOrg12;
    }

    /**
     * @return Returns the divOrg13.
     */
    public Integer getDivOrg13() {
        return divOrg13;
    }

    /**
     * @return Returns the divOrg21.
     */
    public Integer getDivOrg21() {
        return divOrg21;
    }

    /**
     * @return Returns the divOrg22.
     */
    public Integer getDivOrg22() {
        return divOrg22;
    }

    /**
     * @return Returns the divOrg23.
     */
    public Integer getDivOrg23() {
        return divOrg23;
    }

    /**
     * @return Returns the divOrg31.
     */
    public Integer getDivOrg31() {
        return divOrg31;
    }

    /**
     * @return Returns the divOrg32.
     */
    public Integer getDivOrg32() {
        return divOrg32;
    }

    /**
     * @return Returns the divOrg33.
     */
    public Integer getDivOrg33() {
        return divOrg33;
    }

    /**
     * @return Returns the geoLevel1.
     */
    public Integer getGeoLevel1() {
        return geoLevel1;
    }

    /**
     * @return Returns the geoLevel2.
     */
    public Integer getGeoLevel2() {
        return geoLevel2;
    }

       /**
     * @param corpCountry The corpCountry to set.
     */
    public void setCountry(Integer country) {
        this.country = country;
    }

    /**
     * @param divOrg11 The divOrg11 to set.
     */
    public void setDivOrg11(Integer divOrg11) {
        this.divOrg11 = divOrg11;
    }

    /**
     * @param divOrg12 The divOrg12 to set.
     */
    public void setDivOrg12(Integer divOrg12) {
        this.divOrg12 = divOrg12;
    }

    /**
     * @param divOrg13 The divOrg13 to set.
     */
    public void setDivOrg13(Integer divOrg13) {
        this.divOrg13 = divOrg13;
    }

    /**
     * @param divOrg21 The divOrg21 to set.
     */
    public void setDivOrg21(Integer divOrg21) {
        this.divOrg21 = divOrg21;
    }

    /**
     * @param divOrg22 The divOrg22 to set.
     */
    public void setDivOrg22(Integer divOrg22) {
        this.divOrg22 = divOrg22;
    }

    /**
     * @param divOrg23 The divOrg23 to set.
     */
    public void setDivOrg23(Integer divOrg23) {
        this.divOrg23 = divOrg23;
    }

    /**
     * @param divOrg31 The divOrg31 to set.
     */
    public void setDivOrg31(Integer divOrg31) {
        this.divOrg31 = divOrg31;
    }

    /**
     * @param divOrg32 The divOrg32 to set.
     */
    public void setDivOrg32(Integer divOrg32) {
        this.divOrg32 = divOrg32;
    }

    /**
     * @param divOrg33 The divOrg33 to set.
     */
    public void setDivOrg33(Integer divOrg33) {
        this.divOrg33 = divOrg33;
    }

    /**
     * @param geoLevel1 The geoLevel1 to set.
     */
    public void setGeoLevel1(Integer geoLevel1) {
        this.geoLevel1 = geoLevel1;
    }

    /**
     * @param geoLevel2 The geoLevel2 to set.
     */
    public void setGeoLevel2(Integer geoLevel2) {
        this.geoLevel2 = geoLevel2;
    }


    /*
    public FleetDivisionData getData() {
        
        FleetDivisionData fleetDivisionData = new FleetDivisionData();
        //fleetDivisionData.setId(pk.getFleet());
        

        if(odometer != null) {
            fleetDivisionData.setOdometer((TypeDistanceUnitDataType) odometer.getData());
        }
        if(vehicleType != null) {
            fleetDivisionData.setVehicleType((TypeVehicleDataType) vehicleType.getData());
        }
        if (exclusionDate != null){
        	fleetDivisionData.setExclusionDate(exclusionDate);
        }	
        
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
    }*/
    
    /*
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
*/
    public String getDivGeoString(UserSessionData userSessionData) {
        if(country != null) {
            return String.valueOf(country);
        }else {
            Integer level1 = (geoLevel1 == null) ? null : geoLevel1;
            Integer level2 = (geoLevel2 == null) ? null : geoLevel2;
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
    
    private String getDivOrgString(int index,Integer divOrg1,Integer divOrg2,
    		Integer divOrg3,UserSessionData userSessionData) {
        if(divOrg1 != null) {
            return String.valueOf(divOrg1);
        }else {
            Integer level1 = (divOrg2 == null) ? null : divOrg2;
            Integer level2 = (divOrg3 == null) ? null : divOrg3;
            return userSessionData.getFilter().getDivOrgLeaves(index,level1,level2);
        }
     }

    public String getOdometer() {
        return odometer;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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
