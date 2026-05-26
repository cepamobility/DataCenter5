/**
 * Created on 26-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.fleet;

import java.util.Collection;
import java.util.LinkedList;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.TypeDistanceUnitDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.fleet.tables.FleetData;
import communication.fleet.tables.FleetDivisionData;

public class FleetAddDivisionForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;    
    private String odometer;
    private String vehicleType;

    public FleetAddDivisionForm() {
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

    public String validate(int type,UserSessionData sessionData) {
        if(type == FleetData.COLECTIVE) {
            if(Util.empty(vehicleType)) {
                return "fleet.error.emptyvehicletype";            
            }
        }else {
            if(Util.empty(odometer)) {
                return "fleet.error.odometer";            
            }
        }
        for(int j = 0; j < sessionData.getFilter().getDivOrgCount(); j++) {
            for(int i = 1; i <= sessionData.getFilter().getDivOrgLevel(j); i++) {
                if(Util.empty(getDivOrg(j+1,i))) {
                    return "fleet.error.emptydivorg";            
                }
            }
        }
        int count = sessionData.getFilter().getGeoLevelCount(); 
        if(count >= 1) {
            if(Util.empty(getCountry1())) {
                return "fleet.error.emptydivgeo";            
            }
        }
        if(count >= 2) {
            if(Util.empty(getCountry2())) {
                return "fleet.error.emptydivgeo";            
            }
        }
        if(count >= 3) {
            if(Util.empty(getCountry3())) {
                return "fleet.error.emptydivgeo";            
            }
        }
        return null;
    }

    public FleetDivisionData getFleetDivision(int type) {
        FleetDivisionData fleet = new FleetDivisionData();
        
        Collection geoItems3 = new LinkedList();
        GeoLabelData data3 = null;
        if(!Util.empty(country3)) {
            geoItems3.add(new GeoItemData(new Integer(country3),"",true,null));
            data3 = new GeoLabelData(null,null,null,geoItems3);
        }
        Collection geoItems2 = new LinkedList();
        GeoLabelData data2 = null;
        if(!Util.empty(country2)) {
            geoItems2.add(new GeoItemData(new Integer(country2),"",true,data3));
            data2 = new GeoLabelData(null,null,null,geoItems2);
        }
        Collection geoItems1 = new LinkedList();
        geoItems1.add(new GeoItemData(new Integer(country1),"",true,data2));
        fleet.setCountry(new GeoLabelData(null,null,null,geoItems1));
        
        for(int i = 1; i <= 3; i++) {
            if(!Util.empty(getDivOrg(i,1))) {
                Collection divOrgItems3 = new LinkedList();
                LabelItemData labelData3 = null;
                if(!Util.empty(getDivOrg(i,3))) {
                    divOrgItems3.add(new ItemData(new Integer(getDivOrg(i,3)),"",true,null));
                    labelData3 = new LabelItemData(null,null,divOrgItems3,null);
                }
                Collection divOrgItems2 = new LinkedList();
                LabelItemData labelData2 = null;
                if(!Util.empty(getDivOrg(i,2))) {
                    divOrgItems2.add(new ItemData(new Integer(getDivOrg(i,2)),"",true,labelData3));
                    labelData2 = new LabelItemData(null,null,divOrgItems2,null);
                }
                Collection divOrgItems1 = new LinkedList();
                divOrgItems1.add(new ItemData(new Integer(getDivOrg(i,1)),"",true,labelData2));
                fleet.setLabelItemData(i-1,new LabelItemData(null,null,divOrgItems1,null));
            }
        }
        
        if(type == FleetData.COLECTIVE) {
            fleet.setVehicleType(new TypeVehicleDataType(vehicleType));
        }else {
            fleet.setOdometer(new TypeDistanceUnitDataType(odometer));
        }
        return fleet;
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
    
}
