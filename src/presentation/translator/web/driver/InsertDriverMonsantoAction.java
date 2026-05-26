/**
 * Created on 23-may-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.driver;

import java.io.File;
import java.io.FileInputStream;
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
import business.driver.DriverABMFacade;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.LabelItemData;
import communication.datatype.tables.ContractDataType;
import communication.datatype.tables.SexDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class InsertDriverMonsantoAction extends AbstractAction{

    static final int EOF = 65535;
    
    /**
     * @param args
     */
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        String countryName = "";
        StringBuffer word = new StringBuffer();
        try {
            FileInputStream input = new FileInputStream(new File("/home/jrodriguez/Desktop/driversMonsanto.csv"));
            Collection drivers = new LinkedList();
            char car;
            car = 0;
            Collection groupLines = sys.getUserSessionData().getFilter().getCorporationGroupLines();
            while(car != EOF) {
                DriverData driver = new DriverData(null);
                int index = 0;
                car = 0;
                String firstName = "";
                String lastName = "";
                String region = "";
                String divOrgName1 = "";
                String divOrgName2 = "";
                Collection divOrgs = new LinkedList();
                while(car != '\n' && car != EOF) {
                    car = (char)input.read();
                    if(car == '\t' || car == '\n') {
                        switch(index) {
                            case 0:
                                driver.setFirstName(word.toString());
                                break;
                            case 1:
                                driver.setLastName(word.toString());
                                break;
                            case 2:
                                try {
                                    driver.setBirthDate(Util.getDate(word.toString()).getTime());
                                } catch (Exception e) {
                                    System.out.println("Sin Fecha: "+driver.getFirstName()+" "+driver.getLastName());
                                    //e.printStackTrace();
                                }
                                break;
                            case 3:
                                if(word.toString().equals("Masculino")) {
                                    driver.setSex(new SexDataType("datatype.sex.male"));
                                }else {
                                    driver.setSex(new SexDataType("datatype.sex.female"));
                                }
                                break;
                            case 4:
                                if(word.length() > 0) {
                                    driver.setNationality(word.toString());
                                }
                                break;
                            case 5:
                                if(word.length() > 0) {
                                    driver.setCity(word.toString());
                                }
                                break;
                            case 6:
                                if(word.length() > 0) {
                                    driver.setPersonalAddress(word.toString());
                                }
                                break;
                            case 7:
                                region = word.toString();
                                break;
                            case 8:
                                countryName = word.toString();
                                GeoLabelData country = Util.getCountry(groupLines,region,countryName,sys.getText());
                                if(country != null) {
                                    driver.setCountry(country);
                                }else {
                                    throw new Exception("Región/País no encontrado: "+region+"/"+word.toString());
                                }
                                break;
                            case 9:
                                LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,word.toString(),null,null);
                                if(divOrg1 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                }else {
                                    throw new Exception("División organizacional no encontrada: "+word.toString());
                                }
                                break;
                            case 10:
                                divOrgName1 = word.toString();
                                break;
                            case 11:
                                LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,countryName,divOrgName1.trim(),word.toString());
                                if(divOrg2 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                }else {
                                    System.out.println(driver.getFirstName()+" "+driver.getLastName()+" División organizacional no encontrada: "+divOrgName1+"/"+divOrgName2+"/"+word.toString());
                                   // throw new Exception("División organizacional no encontrada: "+divOrgName1+"/"+divOrgName2+"/"+word.toString());
                                }
                                break;
                            case 12:
                                String s = word.toString();
                                if(s.startsWith("Xanx")) {
                                    s = "Xanxerê";
                                }
                                LabelItemData divOrg3 = Util.getDivOrg(groupLines,2,countryName,s,null);
                                if(divOrg3 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[2].getId(),null,divOrg3));
                                }else {
                                    System.out.println(driver.getFirstName()+" "+driver.getLastName()+" División organizacional no encontrada: "+divOrgName1+"/"+word.toString());
                                    //throw new Exception("División organizacional no encontrada: "+divOrgName1+"/"+word.toString());
                                }
                                break;
                            case 13:
                                driver.setDivOrgs(divOrgs);
                                if(word.length() > 0) {
                                    driver.setCorporationId(word.toString());
                                }
                                break;
                            case 14:
                                if(word.length() > 0) {
                                    driver.setInclusionDate(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 15:
                                if(word.length() > 0) {
                                    driver.setEmail(word.toString());
                                }
                                break;
                            case 16:
                                if(word.length() > 0) {
                                    driver.setPhoneNumber(word.toString());
                                }
                                break;
                            case 17:
                                if(word.length() > 0) {
                                    driver.setOfficeAddress(word.toString());
                                }
                                break;
                            case 18:
                                if(word.length() > 0) {
                                    driver.setLicenseNumber(word.toString());
                                }
                                break;
                            case 19:
                                if(word.length() > 0) {
                                    driver.setLicenseGet(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 20:
                                if(word.length() > 0) {
                                    driver.setLicenseExpiry(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 21:
                                if(word.length() > 0) {
                                    driver.setLicenseCategory(word.toString());
                                }
                                break;
                            case 22:
                                if(word.length() > 0) {
                                    if(word.toString().equals("AUTOMÓVEL") || word.toString().equals("AUTOMÃVEL")) {
                                        driver.setVehicleAllowed(new TypeVehicleDataType("datatype.vehicletype.automobile"));
                                    }else if(word.toString().equals("pick up") || word.toString().equals("PICK-UP")) {
                                        driver.setVehicleAllowed(new TypeVehicleDataType("datatype.vehicletype.pickup"));
                                    }else if(word.toString().equals("Utilitário")  || word.toString().equals("UtilitÃ¡rio")) {
                                        driver.setVehicleAllowed(new TypeVehicleDataType("datatype.vehicletype.utility"));
                                    }else if(word.toString().trim().equals("CAMINHÃO") || word.toString().equals("CAMINHÃO")) {
                                        driver.setVehicleAllowed(new TypeVehicleDataType("datatype.vehicletype.truck"));
                                    }else {
                                        throw new Exception("Tipo de vehículo desconocido");
                                    }

                                }
                                break;
                        }
                        word = new StringBuffer();
                        index++;
                    }else {
                        word.append(car);
                    }
                }
                if(!Util.empty(driver.getFirstName())) {
                    drivers.add(driver);
                }
            }
            DriverABMFacade driverABM = null;
            driverABM = sys.getDriverABMFacade();
            driverABM.createDrivers(drivers,sys.getUserSessionData(),"");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
        return null;
    }
}
