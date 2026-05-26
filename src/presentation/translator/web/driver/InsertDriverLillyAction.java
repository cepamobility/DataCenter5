/**
 * Created on 23-may-2007
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
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

import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.LabelItemData;
import communication.datatype.tables.SexDataType;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class InsertDriverLillyAction extends AbstractAction{

    static final int EOF = 65535;
    
    /**
     * @param args
     */
        public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

            HttpSession session = request.getSession();
            SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
            // Text messages = sys.getText();
            DriverABMFacade driverABM = sys.getDriverABMFacade();
            
            StringBuffer word = new StringBuffer();
            try {
                char car;

                FileInputStream inputAux = new FileInputStream(new File("/home/jrodriguez/Desktop/ConductoresLilly_2.csv"));
                car = 0;
                String id = "";
                String name = "";
                Hashtable keys = new Hashtable();
                while(car != EOF) {
                    int index = 0;
                    car = 0;
                    while(car != '\n' && car != EOF) {
                        car = (char)inputAux.read();
                        if(car == '\t' || car == '\n') {
                            switch(index) {
                                case 0:
                                    id = word.toString();
                                    index++;
                                    break;
                                case 1:
                                    name = word.toString().toUpperCase();
                                    index++;
                                    break;
                                case 2:
                                    name = name+" "+word.toString().toUpperCase();
                                    index++;
                                    break;
                                case 3:    
                                    keys.put(name.trim(),id);
                                    index = 0;
                            }
                            word = new StringBuffer();
                        }else {
                            word.append(car);
                        }
                    }
                }

                FileInputStream input = new FileInputStream(new File("/home/jrodriguez/Desktop/ConductoresLilly.csv"));
                Hashtable drivers = new Hashtable();
                car = 0;
                Collection groupLines = sys.getUserSessionData().getFilter().getCorporationGroupLines();
                while(car != EOF) {
                    DriverData driver = new DriverData(null);
                    int index = 0;
                    car = 0;
                    id = "";
                    String idVehiculo = "";
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
                                    idVehiculo = word.toString();
                                    break;
                                case 1:
                                    driver.setCorporationId(replace(word.toString()));
                                    break;
                                case 2:
                                    driver.setFirstName(replace(word.toString()));
                                    break;
                                case 3:
                                    id = word.toString();
                                    driver.setLastName(replace(id));
                                    if(id.endsWith(" B.A.") || id.endsWith(" MBA")) {
                                        id = id.substring(0,id.length()-4).trim();
                                    }
                                    if(keys.containsKey(id.toUpperCase())) {
                                        String value = (String)keys.get(id.toUpperCase());
                                        if(value.equals("Primary")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Áreas Comerciales",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"Primary","N/A","N/A");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else if(value.equals("Specialty")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Áreas Comerciales",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"Specialty","N/A","N/A");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else if(value.equals("Manufactura")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Oficinas",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"Manufactura","Manufactura","Manufactura");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else if(value.startsWith("Medica") || value.startsWith("MÃ©dica")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Oficinas",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"Médica","Médica","Médica");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else if(value.equals("MSO")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Oficinas",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"MSO","MSO","MSO");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else if(value.equals("Recuros Humanos")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Oficinas",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"RH","RH","RH");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else if(value.equals("Gobierno")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Áreas Comerciales",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"Gobierno","Gobierno","N/A");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else if(value.equals("Finanzas")) {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"Oficinas",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"Finanzas","Finanzas","Finanzas");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }else {
                                            LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,"N/A",null,null);
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                                            LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,"N/A","N/A","N/A");
                                            divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                                            if(divOrg1 == null || divOrg2 == null) {
                                                System.out.println("ERRROR");
                                            }
                                        }
                                        driver.setDivOrgs(divOrgs);
                                        GeoLabelData country = Util.getCountry(groupLines,"LA&C","Mexico",sys.getText());
                                        driver.setCountry(country);
                                    }else {
                                        System.out.println("Nombre desconocido "+id);
                                    }
                                    break;
                                case 4:
                                    try {
                                        driver.setBirthDate(getDate(word.toString()).getTime());
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
                                        driver.setCity(replace(word.toString()));
                                    }
                                    break;
                                case 7:
                                    if(word.length() > 0) {
                                        driver.setPersonalAddress(replace(word.toString()));
                                    }
                                    break;
                                case 8:
                                    if(word.length() > 0) {
                                        driver.setEmail(word.toString());
                                    }
                                    break;
                                case 9:
                                    int years = Integer.parseInt(word.toString());
                                    Calendar start = Calendar.getInstance();
                                    start.set(Calendar.DATE,1);
                                    start.set(Calendar.MONTH,0);
                                    start.set(Calendar.YEAR,2008 - years);
                                    driver.setInclusionDate(start.getTime());
                                    break;
                            }
                            word = new StringBuffer();
                            index++;
                        }else {
                            word.append(car);
                        }
                    }
                    if(!Util.empty(driver.getFirstName())) {
                        if(driver.getDivOrgs() != null && driver.getDivOrgs().size() > 0) {
                            drivers.put(idVehiculo,driver);
                        }
                    }
                }
                drivers = driverABM.createDrivers(drivers,sys.getUserSessionData());

                File f = new File("/home/jrodriguez/vehiclesLilly.sql");
                FileOutputStream fos = new FileOutputStream(f);

                FileInputStream inputVechicels = new FileInputStream(new File("/home/jrodriguez/Desktop/vehiculosLilly_2.csv"));
                car = 0;
                while(car != EOF) {
                    int index = 0;
                    car = 0;
                    while(car != '\n' && car != EOF) {
                        car = (char)inputVechicels.read();
                        if(car == '\t' || car == '\n') {
                            switch(index) {
                                case 0:
                                    id = word.toString();
                                    index++;
                                    break;
                                case 1:
                                    String matricula = word.toString();
                                    if(!id.startsWith("COMODIN") && !id.startsWith("N/A")  && !id.startsWith("PROMOTOR") && !matricula.startsWith("N/A") && !matricula.equals("0")) {  
                                        if(drivers.containsKey(id)){
                                            DriverData driver = (DriverData)drivers.get(id);
                                            Iterator it = driver.getDivOrgsIterator();
                                            if(it != null && it.hasNext()) {
                                                DivOrgData divOrgData1 = (DivOrgData)it.next();
                                                DivOrgData divOrgData2 = (DivOrgData)it.next();
                                                                                                
                                                String update = "UPDATE vehicles SET divorg1 = "+((ItemData)divOrgData1.getDivOrgLevels().getItemIterator().next()).getId()+" , divorg2 = "+((ItemData)((ItemData)((ItemData)divOrgData2.getDivOrgLevels().getItemIterator().next()).getSubItems().getItemIterator().next()).getSubItems().getItemIterator().next()).getId()+", driver = "+driver.getId()+" WHERE matricula = '"+matricula+"' AND corporation = 10;\n";
                                                fos.write(update.getBytes());
                                            }
                                        }else {
                                            //System.out.println("NO "+id);
                                        }
                                    }
                                    index = 0;
                            }
                            word = new StringBuffer();
                        }else {
                            word.append(car);
                        }
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private String replace(String string) {
            string = string.replaceAll("Ã¡","á");
            string = string.replaceAll("Ã©","é");
            string = string.replaceAll("Ã­","í");
            string = string.replaceAll("Ã³","ó");
            string = string.replaceAll("Ãº","ú");
            return string;
        }

        public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
            return null;
        }

        private Calendar getDate(String word) {
            Calendar c = Calendar.getInstance();
            if(word.contains("/")) {
                StringTokenizer tokDate = new StringTokenizer(word,"/");
                c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(tokDate.nextToken()));
                c.set(Calendar.MONTH,Integer.parseInt(tokDate.nextToken())-1);
                c.set(Calendar.YEAR,Integer.parseInt(tokDate.nextToken()));
            }else {
                StringTokenizer tokDate = new StringTokenizer(word,"-");
                String value1 = tokDate.nextToken();
                String value2 = tokDate.nextToken();
                String value3 = tokDate.nextToken();
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
