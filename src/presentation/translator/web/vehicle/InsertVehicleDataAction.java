/**
 * Created on 23-may-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.vehicle.VehicleReportFacade;

public class InsertVehicleDataAction extends AbstractAction{

    static final int EOF = 65535;
    
    /**
     * @param args
     */
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        
        try {
/*            FileInputStream input = new FileInputStream(new File("/home/jrodriguez/Desktop/vehicles.csv"));
            Collection vehicles = new LinkedList();
            char car;
            StringBuffer word = new StringBuffer();
            car = 0;
            Collection groupLines = sys.getUserSessionData().getFilter().getCorporationGroupLines();
            while(car != EOF) {
                VehicleData vehicle = new VehicleData(null);
                Collection divOrgs = new LinkedList();
                int index = 0;
                car = 0;
                String firstName = "";
                String lastName = "";
                String region = "";
                String divOrgName1 = "";
                String divOrgName2 = "";
                while(car != '\n' && car != EOF) {
                    car = (char)input.read();
                    if(car == '\t' || car == '\n') {
                        switch(index) {
                            case 0:
                                if(word.toString().equals("INDIVIDUAL")) {
                                    vehicle.setUse(new TypeUseDataType("persistence.datatype.typeuse.individual"));   
                                }else {
                                    vehicle.setUse(new TypeUseDataType("persistence.datatype.typeuse.institutional"));   
                                }
                                break;
                            case 1:
                                firstName = word.toString().trim();
                                break;
                            case 2:
                                if(vehicle.getUse().getResourceBundleId().equals("persistence.datatype.typeuse.individual")) {
                                    lastName = word.toString().trim();
                                    ListResult list = sys.getDriverReportFacade().advancedSearch(null,firstName,lastName,null,null,null,0,-1,DriverData.ALL,sys.getUserSessionData());
                                    switch(list.getTotal()) {
                                        case 0:
                                            throw new Exception("Conductor desconocido :"+firstName+" "+lastName);
                                        case 1:
                                            vehicle.setDriver(new DriverData(((DriverMainAttributes)list.getElements().iterator().next()).getId()));
                                            break;
                                        default:
                                            throw new Exception("Hay más de un conductor de nombre :"+firstName+" "+lastName);
                                    }
                                }
                                break;
                            case 3:
                                region = word.toString();
                                break;
                            case 4:
                                GeoLabelData country = Util.getCountry(groupLines,region,word.toString(),sys.getText());
                                if(country != null) {
                                    vehicle.setCountry(country);
                                }else {
                                    throw new Exception("Región/País no encontrado: "+region+"/"+word.toString());
                                }
                                break;
                            case 5:
                                LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,word.toString(),null,null);
                                if(divOrg1 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                }else {
                                    throw new Exception("División organizacional no encontrada: "+word.toString());
                                }
                                break;
                            case 6:
                                divOrgName1 = word.toString();
                                break;
                            case 7:
                                divOrgName2 = word.toString();
                                break;
                            case 8:
                                LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,divOrgName1,divOrgName2,word.toString());
                                if(divOrg2 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                }else {
                                    throw new Exception("División organizacional no encontrada: "+divOrgName1+"/"+divOrgName2+"/"+word.toString());
                                }
                                break;
                            case 9:
                                divOrgName1 = word.toString();
                                break;
                            case 10:
                                LabelItemData divOrg3 = Util.getDivOrg(groupLines,2,divOrgName1,word.toString(),null);
                                if(divOrg3 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[2].getId(),null,divOrg3));
                                }else {
                                    throw new Exception("División organizacional no encontrada: "+divOrgName1+"/"+word.toString());
                                }
                                break;
                            case 11:
                                vehicle.setDivOrgs(divOrgs);
                                if(word.toString().equals("Automóvel") || word.toString().equals("AutomÃ³vel")) {
                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.automobile"));
                                }else if(word.toString().equals("pick up") || word.toString().equals("Pick up")) {
                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.pickup"));
                                }else if(word.toString().equals("Utilitário")  || word.toString().equals("UtilitÃ¡rio")) {
                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.utility"));
                                }else {
                                    throw new Exception("Tipo de vehículo desconocido");
                                }
                                break;
                            case 12:
                                if(word.length() > 0) {
                                    vehicle.setMatricula(word.toString());
                                }
                                break;
                            case 13:
                                if(word.length() > 0) {
                                    vehicle.setCostCenter(word.toString());
                                }
                                break;
                            case 14:
                                if(word.length() > 0) {
                                    vehicle.setPermissionNumber(word.toString());
                                }
                                break;
                            case 15:
                                if(word.length() > 0) {
                                    vehicle.setPermissionExpiry(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 16:
                                if(word.length() > 0) {
                                    vehicle.setPurchaseDate(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 17:
                                if(word.length() > 0) {
                                    if(word.toString().equals("Company Car")) {
                                        vehicle.setOwnerShip(new TypeCenterCostDataType("datatype.ownership.companycar"));
                                    }else {
                                        throw new Exception("Ownership desconocido: "+word.toString());
                                    }
                                }
                                break;
                            case 18:
                                if(word.length() > 0) {
                                    vehicle.setTrade(new TypeTradeDataType(word.toString()));
                                }
                                break;
                            case 19:
                                if(word.length() > 0) {
                                    vehicle.setModel(word.toString());
                                }
                                break;
                            case 20:
                                if(word.length() > 0) {
                                    try {
                                        vehicle.setYear(new Integer(word.toString()));
                                    }catch(Exception e) {
                                        throw new Exception("Año incorrecto: "+word.toString());
                                    }
                                }
                                break;
                            case 21:
                                if(word.length() > 0) {
                                    vehicle.setColour(word.toString());
                                }
                                break;
                            case 22:
                                if(word.length() > 0) {
                                    vehicle.setChasisNumber(word.toString());
                                }
                                break;
                            case 23:
                                if(word.length() > 0) {
                                    throw new Exception("Motor desconocido :"+word.toString());
                                    //vehicle.setEngine(word.toString());
                                }
                                break;
                            case 24:
                                if(word.length() > 0) {
                                    throw new Exception("Tipo de combustible desconocido :"+word.toString());
                                    //vehicle.setFuel(word.toString());
                                }
                                break;
                            case 25:
                                if(word.length() > 0) {
                                    try {
                                        vehicle.setTankCapacity(new Integer(word.toString()));
                                    }catch(Exception e) {
                                        throw new Exception("Capacidad del tanque incorrecta: "+word.toString());
                                    }
                                }
                                break;
                            case 26:
                                if(word.length() > 0) {
                                    throw new Exception("Unidad de volumen desconcida :"+word.toString());
                                    //vehicle.setTankUnit(word.toString());
                                }
                                break;
                            case 27:
                                if(word.length() > 0) {
                                    vehicle.setCover(new TypeCoverDataType(word.toString()));
                                }
                                break;
                            case 28:
                                if(word.length() > 0) {
                                    if(word.toString().equals("Kilometros")) {
                                        vehicle.setOdometerUnit(new TypeDistanceUnitDataType("datatype.distanceunit.kilometers"));
                                    }else {
                                        throw new Exception("Odometro desconcido :"+word.toString());
                                    }
                                }
                                break;
                            case 29:
                                if(word.length() > 0) {
                                    vehicle.setAbs(word.toString().equals("1"));
                                }
                                break;
                            case 30:
                                if(word.length() > 0) {
                                    vehicle.setAirBag(word.toString().equals("1"));
                                }
                                break;
                            case 31:
                                if(word.length() > 0) {
                                    vehicle.setAireAcondicionado(word.toString().equals("1"));
                                }
                                break;
                        }
                        word = new StringBuffer();
                        index++;
                    }else {
                        word.append(car);
                    }
                }
                if(!Util.empty(vehicle.getMatricula())) {
                    vehicles.add(vehicle);
                }
            }
            VehicleABMFacade vehicleABM = null;
            vehicleABM = sys.getVehicleABMFacade();
            vehicleABM.createVehicles(vehicles, sys.getUserSessionData());
            */
            VehicleReportFacade vehicleReport = null;
            vehicleReport = sys.getVehicleReportFacade();
            vehicleReport.getChecksCount(new Integer(1),new Integer(2000),new Integer(1),new Integer(2010),null,null,sys.getText(),0, sys.getUserSessionData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
        return null;
    }
}
