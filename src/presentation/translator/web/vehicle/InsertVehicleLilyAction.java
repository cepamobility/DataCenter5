/**
 * Created on 23-may-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.vehicle.VehicleABMFacade;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.LabelItemData;
import communication.datatype.tables.TypeCenterCostDataType;
import communication.datatype.tables.TypeTradeDataType;
import communication.datatype.tables.TypeUseDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.vehicle.tables.VehicleData;

public class InsertVehicleLilyAction extends AbstractAction{

    static final int EOF = 65535;
    
    /**
     * @param args
     */
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        
        try {
            FileInputStream input = new FileInputStream(new File("/home/jrodriguez/Desktop/lily.csv"));
            char car;
            StringBuffer word = new StringBuffer();
            car = 0;
            
            Collection groupLines = sys.getUserSessionData().getFilter().getCorporationGroupLines();
            GeoLabelData country = Util.getCountry(groupLines,"LA&C","México",sys.getText());
            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"N/A",null,null);
            DivOrgData divOrgData1 = new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1);
            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"N/A","N/A","N/A");
            DivOrgData divOrgData2 = new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2);
            
            VehicleABMFacade vehicleABM = null;
            vehicleABM = sys.getVehicleABMFacade();
            
            while(car != EOF) {
                Collection vehicles = new LinkedList();

                Integer id = null;
                VehicleData vehicle = new VehicleData(id);
                vehicle.setUse(new TypeUseDataType("persistence.datatype.typeuse.institutional"));   
                vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.automobile"));
                vehicle.setCountry(country);
                ArrayList<DivOrgData> divOrgs = new ArrayList<DivOrgData>();
                divOrgs.add(divOrgData1);
                divOrgs.add(divOrgData2);
                vehicle.setDivOrgs(divOrgs);

                int index = 0;
                car = 0;
                while(car != '\n' && car != EOF) {
                    car = (char)input.read();
                    if(car == '\t' || car == '\n') {
                        switch(index) {
                            case 0:
                                if(word.length() > 0) {
                                    vehicle.setMatricula(word.toString());
                                }
                                break;
                            case 1:
                                if(word.length() > 0) {
                                    vehicle.setCostCenter(word.toString());
                                }
                                break;
                            case 2:
                                if(word.length() > 0) {
                                    vehicle.setPurchaseDate(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 3:
                                if(word.length() > 0) {
                                    if(word.toString().equals("Company Car")) {
                                        vehicle.setOwnerShip(new TypeCenterCostDataType("datatype.ownership.companycar"));
                                    }else {
                                        throw new Exception("Ownership desconocido: "+word.toString());
                                    }
                                }
                                break;
                            case 4:
                                if(word.length() > 0) {
                                    if(word.toString().equals("N/A")) {
                                        vehicle.setTrade(new TypeTradeDataType("generic.messages.nodata"));
                                    }else if(word.toString().equals("0")) {
                                        vehicle.setTrade(new TypeTradeDataType("generic.messages.nodata"));
                                    } else {
                                        vehicle.setTrade(new TypeTradeDataType(word.toString().trim()));
                                    }
                                }else {
                                    vehicle.setTrade(new TypeTradeDataType("generic.messages.nodata"));
                                }
                                break;
                            case 5:
                                if(word.length() > 0) {
                                    vehicle.setModel(word.toString());
                                }else {
                                    vehicle.setModel("generic.messages.nodata");
                                }
                                break;
                            case 6:
                                if(word.length() > 0) {
                                    try {
                                        vehicle.setYear(new Integer(word.toString()));
                                    }catch(Exception e) {
                                        throw new Exception("Año incorrecto: "+word.toString());
                                    }
                                }else {
                                    vehicle.setYear(new Integer(0));
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
                vehicleABM.createVehicles(vehicles, null, sys.getUserSessionData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
        return null;
    }
}
