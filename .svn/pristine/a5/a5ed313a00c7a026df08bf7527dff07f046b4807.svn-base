/**
 * Created on 23-may-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.driver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

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

public class InsertDriverDataAction extends AbstractAction{

    static final int EOF = 65535;
    
    /**
     * @param args
     */
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        StringBuffer word = new StringBuffer();
        try {
            FileInputStream input = new FileInputStream(new File("/home/jrodriguez/Desktop/ConductoresLilly.csv"));
            Collection drivers = new LinkedList();
            char car;
            car = 0;
            Collection groupLines = sys.getUserSessionData().getFilter().getCorporationGroupLines();
            while(car != EOF) {
                DriverData driver = new DriverData(null);
                int index = 0;
                car = 0;
                String id = "";
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
                                id = word.toString();                                
                                break;
                            case 1:
                                driver.setCorporationId(word.toString());
                                break;
                            case 2:
                                driver.setFirstName(word.toString());
                                break;
                            case 3:
                                driver.setLastName(word.toString());
                                break;
                            case 4:
                                try {
                                    driver.setBirthDate(Util.getDate(word.toString()).getTime());
                                } catch (Exception e) {
                                    System.out.println("Sin Fecha: "+driver.getFirstName()+" "+driver.getLastName());
                                }
                                break;
                            case 5:
                                if(word.toString().equals("Masculino")) {
                                    driver.setSex(new SexDataType("datatype.sex.male"));
                                }else {
                                    driver.setSex(new SexDataType("datatype.sex.female"));
                                }
                                break;
                            case 6:
                                if(word.length() > 0) {
                                    driver.setNationality(word.toString());
                                }
                                break;
                            case 7:
                                if(word.length() > 0) {
                                    driver.setCity(word.toString());
                                }
                                break;
/*                            case 6:
                                if(word.length() > 0) {
                                    driver.setPersonalAddress(word.toString());
                                }
                                break;
                            case 7:
                                region = word.toString();
                                break;
                            case 8:
                                GeoLabelData country = Util.getCountry(groupLines,region,word.toString(),sys.getText());
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
                                divOrgName2 = word.toString();
                                break;
                            case 12:
                                LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,divOrgName1,divOrgName2.trim(),word.toString());
                                if(divOrg2 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                }else {
                                    System.out.println(driver.getFirstName()+" "+driver.getLastName()+" División organizacional no encontrada: "+divOrgName1+"/"+divOrgName2+"/"+word.toString());
                                   // throw new Exception("División organizacional no encontrada: "+divOrgName1+"/"+divOrgName2+"/"+word.toString());
                                }
                                break;
                            case 13:
                                divOrgName1 = word.toString();
                                break;
                            case 14:
                                LabelItemData divOrg3 = Util.getDivOrg(groupLines,2,divOrgName1,word.toString(),null);
                                if(divOrg3 != null) {
                                    divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[2].getId(),null,divOrg3));
                                }else {
                                    System.out.println(driver.getFirstName()+" "+driver.getLastName()+" División organizacional no encontrada: "+divOrgName1+"/"+word.toString());
                                    //throw new Exception("División organizacional no encontrada: "+divOrgName1+"/"+word.toString());
                                }
                                break;
                            case 15:
                                driver.setDivOrgs(divOrgs);
                                if(word.length() > 0) {
                                    driver.setCorporationId(word.toString());
                                }
                                break;
                            case 16:
                                if(word.length() > 0) {
                                    driver.setInclusionDate(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 17:
                                if(word.length() > 0) {
                                    driver.setEmail(word.toString());
                                }
                                break;
                            case 18:
                                if(word.length() > 0) {
                                    driver.setPhoneNumber(word.toString());
                                }
                                break;
                            case 19:
                                if(word.length() > 0) {
                                    driver.setOfficeAddress(word.toString());
                                }
                                break;
                            case 20:
                                if(word.length() > 0) {
                                    driver.setDrives(word.toString().equals("SIM"));
                                }
                                break;
                            case 21:
                                if(word.length() > 0) {
                                    driver.setEmployee(word.toString().equals("SIM"));
                                }
                                break;
                            case 22:
                                if(word.length() > 0) {
                                    if(word.toString().equals("Permanente")) {
                                        driver.setContract(new ContractDataType("datatype.contract.permanent"));
                                    }else {
                                        throw new Exception("Tipo de contrato desconocido");
                                    }
                                }
                                break;
                            case 23:
                                if(word.length() > 0) {
                                    driver.setLicenseNumber(word.toString());
                                }
                                break;
                            case 24:
                                if(word.length() > 0) {
                                    driver.setLicenseGet(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 25:
                                if(word.length() > 0) {
                                    driver.setLicenseExpiry(Util.getDate(word.toString()).getTime());
                                }
                                break;
                            case 26:
                                if(word.length() > 0) {
                                    driver.setLicenseCategory(word.toString());
                                }
                                break;
                            case 27:
                                if(word.length() > 0) {
                                    if(word.toString().equals("Automóvel") || word.toString().equals("AutomÃ³vel")) {
                                        driver.setVehicleAllowed(new TypeVehicleDataType("datatype.vehicletype.automobile"));
                                    }else if(word.toString().equals("pick up")) {
                                        driver.setVehicleAllowed(new TypeVehicleDataType("datatype.vehicletype.pickup"));
                                    }else if(word.toString().equals("Utilitário")  || word.toString().equals("UtilitÃ¡rio")) {
                                        driver.setVehicleAllowed(new TypeVehicleDataType("datatype.vehicletype.utility"));
                                    }else {
                                        throw new Exception("Tipo de vehículo desconocido");
                                    }

                                }
                                break;*/
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
/*            DriverABMFacade driverABM = null;
            driverABM = sys.getDriverABMFacade();
            driverABM.createDrivers(drivers,sys.getUserSessionData());
  */          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
        return null;
    }

    private Calendar getDate(String word) {
        Calendar c = Calendar.getInstance();
        StringTokenizer tokDate = new StringTokenizer(word,"-");
        String value1 = tokDate.nextToken();
        String value2 = tokDate.nextToken();
        String value3 = tokDate.nextToken();
        if(Util.isNumber(value2)) {
            c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(value3));
            c.set(Calendar.MONTH,Integer.parseInt(value2)-1);
            c.set(Calendar.YEAR,Integer.parseInt(value1));
        }else {
            c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(value1));
            if(value2.equals("ENE")) {
                c.set(Calendar.MONTH,0);
            }else if(value2.equals("FEB")) {
                c.set(Calendar.MONTH,1);
            }else if(value2.equals("MAR")) {
                c.set(Calendar.MONTH,2);
            }else if(value2.equals("ABR")) {
                c.set(Calendar.MONTH,3);
            }else if(value2.equals("MAY")) {
                c.set(Calendar.MONTH,4);
            }else if(value2.equals("JUN")) {
                c.set(Calendar.MONTH,5);
            }else if(value2.equals("JUL")) {
                c.set(Calendar.MONTH,6);
            }else if(value2.equals("AGO")) {
                c.set(Calendar.MONTH,7);
            }else if(value2.equals("SET")) {
                c.set(Calendar.MONTH,8);
            }else if(value2.equals("OCT")) {
                c.set(Calendar.MONTH,9);
            }else if(value2.equals("NOV")) {
                c.set(Calendar.MONTH,10);
            }else {
                c.set(Calendar.MONTH,11);
            }
            c.set(Calendar.YEAR,Integer.parseInt(value3));
        }
        return c;
    }
}
