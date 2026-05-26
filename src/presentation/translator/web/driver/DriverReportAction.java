package presentation.translator.web.driver;

import java.util.Calendar;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.DetailedDriverReportFactory;
import presentation.report.driver.DetailedDriverReportWithVehiclesFactory;
import presentation.report.driver.DriverReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.MobilTrackEventData;
import communication.driver.report.DetailedDriverReportData;
import communication.driver.report.DetailedDriverReportWithVehiclesData;
import communication.driver.report.DriverReportData;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.Filter;
import persistence.administration.corporation.tables.MobilTrackEvent;


/**
 * @author adocarmo
 */
public class DriverReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        //codigo de prueba
        
        MobilTrackEventData event = new MobilTrackEventData();
        event.setMatricula("RTP3213");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2021);
        c.set(Calendar.MONTH, Calendar.JUNE);
        c.set(Calendar.DATE, 16);
        c.set(Calendar.HOUR_OF_DAY, 18);
        c.set(Calendar.MINUTE, 01);
        event.setFechaHora(c.getTime());
        event.setdEvento("Exceso velocidad 1");

        
        
        try{
            sys.getCorporationABMFacade().deleteMobilTrackEvent(event, sys.getUserSessionData());

            JFreeReportBoot jfreeReportBoot = JFreeReportBoot.getInstance(); 
        	try{
        		jfreeReportBoot.start();
        	}catch(Throwable e){
        		jfreeReportBoot.start(); // porque al ppio el jfree lanza exception la primera vez de invocado.
        	}
        
        if(sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.ALLERGAN) {
        	DriverMultiReportForm reportForm = (DriverMultiReportForm)form;
        	JFreeReport report = getReport(sys,reportForm,1);
        	session.setAttribute("Bookmark",reportForm.getBookmark());
        	ReportUtil reportUtil = new ReportUtil();
        	ActionForward actionForward = reportUtil.executeReport(mapping,response,report,reportForm.getOutput(),session,"driver",true);
        	return actionForward;
        }else {
        	DriverReportForm reportForm = (DriverReportForm)form;
        	JFreeReport report = getReport(sys,reportForm,1);
        	session.setAttribute("Bookmark",reportForm.getBookmark());
        	ReportUtil reportUtil = new ReportUtil();
        	ActionForward actionForward = reportUtil.executeReport(mapping,response,report,reportForm.getOutput(),session,"driver",true);
        	return actionForward;
        }
        
        }catch(Exception e){
        	e.printStackTrace();
        	throw e;
        }
        
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    /**
     * 
     * @param sys
     * @param reportForm
     * @param page
     * @return
     * @throws Exception
     */
    public JFreeReport getReport(SystemAccess sys,DriverReportForm reportForm,int page) throws Exception {
    	
    	try{
    		
    	
    	Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
    	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);       
        
        Hashtable parameters = new Hashtable();
        if(reportForm.getName() != null) {
            parameters.put("name",reportForm.getName());
        }
        if(reportForm.getStatus() != null) {
            parameters.put("status",reportForm.getStatus());
        }
        if(reportForm.getAccident() != null) {
            parameters.put("accidents",reportForm.getAccident());
        }
        if(reportForm.getIncident() != null) {
            parameters.put("incidents",reportForm.getIncident());
        }
        if(reportForm.getFine() != null) {
            parameters.put("fines",reportForm.getFine());
        }
        if(reportForm.getAccidentCount() != null) {
            parameters.put("accidentCount",reportForm.getAccidentCount());
        }
        if(reportForm.getIncidentCount() != null) {
            parameters.put("incidentCount",reportForm.getIncidentCount());
        }
        if(reportForm.getFineCount() != null) {
            parameters.put("fineCount",reportForm.getFineCount());
        }
        
        if(reportForm.getWithVehicles() != null) {
            parameters.put("withVehicles",reportForm.getWithVehicles());
        }else{
        	parameters.put("withVehicles",DriverData.ALL);
        }
        
        if(sys.getUserSessionData().isForest()) {
            if(reportForm.getBps() != null) {
                parameters.put("bps",reportForm.getBps());
            }
            if(reportForm.getAptitudStatus() != null) {
                parameters.put("aptitudStatus",reportForm.getAptitudStatus());
            }
            if(reportForm.getAptitudResult() != null) {
                parameters.put("aptitudResult",reportForm.getAptitudResult());
            }
            if(reportForm.getAlcoholStatus() != null) {
                parameters.put("alcoholStatus",reportForm.getAlcoholStatus());
            }
            if(reportForm.getAlcoholResult() != null) {
                parameters.put("alcoholResult",reportForm.getAlcoholResult());
            }
            if(reportForm.getPostiveAlcohol() != null) {
                parameters.put("postiveAlcohol",reportForm.getPostiveAlcohol());
            }
        }
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        if(reportForm.getType().equals("resumed")) {
            DriverReportData result = sys.getDriverReportFacade().getReportDrivers(parameters,divOrgs,divGeos,first,count,sys.getUserSessionData());
            int pages = 1;
            if (result != null){
            	result.setMessages(messages);
            	pages = (int)(result.getTotal() / 50);
            	if(pages*50 < result.getTotal()) {
            		pages++;
            	}
        	}
            DriverReportFactory factory = new DriverReportFactory("Drivers",result,reportForm,sys,messages,1,pages,page);
            report = factory.createReport();
            
        }else if(reportForm.getType().equals("detailed")) {
            DetailedDriverReportData result = sys.getDriverReportFacade().
            	getDetailedReportDrivers(parameters,divOrgs,divGeos,first,count,sys.getUserSessionData());
            int pages = 1;
            if (result != null){
            	result.setMessages(messages);
            	pages = (int)(result.getTotal() / 50);
            	if(pages*50 < result.getTotal()) {
            		pages++;
            	}
            }
            DetailedDriverReportFactory factory = new DetailedDriverReportFactory("Drivers",result,reportForm,sys,messages,pages,page);
            report = factory.createReport();        
            
        }else if(reportForm.getType().equals("detailedwithvehicles")) {
        	// Detallado con datos vehiculos
            DetailedDriverReportWithVehiclesData result = sys.getDriverReportFacade().
            	getDetailedReportDriversWithVehicles(parameters,divOrgs,divGeos,
            			first,count,sys.getUserSessionData());
            int pages = 1;
            if (result != null){
            	result.setMessages(messages);
            	pages = (int)(result.getTotal() / 50);
            	if(pages*50 < result.getTotal()) {
            		pages++;
            	}
            }
            DetailedDriverReportWithVehiclesFactory factory = new 
            	DetailedDriverReportWithVehiclesFactory("Drivers",result,reportForm,sys,
            		messages,pages,page);
            report = factory.createReport();        	
        }
        
        return report;
    	}catch (Exception e){
    		e.printStackTrace();
    		throw e;
    	}
       
    }

    public JFreeReport getReport(SystemAccess sys,DriverMultiReportForm reportForm,int page) throws Exception {
    	
    	try{
    		
    	
      	Integer[][] divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
        Integer[] geos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
    	Integer[][] divGeos = new Integer[2][geos.length];
        divGeos[0] = geos;
        
        
        Hashtable parameters = new Hashtable();
        if(reportForm.getName() != null) {
            parameters.put("name",reportForm.getName());
        }
        if(reportForm.getStatus() != null) {
            parameters.put("status",reportForm.getStatus());
        }
        if(reportForm.getAccident() != null) {
            parameters.put("accidents",reportForm.getAccident());
        }
        if(reportForm.getIncident() != null) {
            parameters.put("incidents",reportForm.getIncident());
        }
        if(reportForm.getFine() != null) {
            parameters.put("fines",reportForm.getFine());
        }
        if(reportForm.getAccidentCount() != null) {
            parameters.put("accidentCount",reportForm.getAccidentCount());
        }
        if(reportForm.getIncidentCount() != null) {
            parameters.put("incidentCount",reportForm.getIncidentCount());
        }
        if(reportForm.getFineCount() != null) {
            parameters.put("fineCount",reportForm.getFineCount());
        }
        if(sys.getUserSessionData().isForest()) {
            if(reportForm.getBps() != null) {
                parameters.put("bps",reportForm.getBps());
            }
            if(reportForm.getAptitudStatus() != null) {
                parameters.put("aptitudStatus",reportForm.getAptitudStatus());
            }
            if(reportForm.getAptitudResult() != null) {
                parameters.put("aptitudResult",reportForm.getAptitudResult());
            }
            if(reportForm.getAlcoholStatus() != null) {
                parameters.put("alcoholStatus",reportForm.getAlcoholStatus());
            }
            if(reportForm.getAlcoholResult() != null) {
                parameters.put("alcoholResult",reportForm.getAlcoholResult());
            }
            if(reportForm.getPostiveAlcohol() != null) {
                parameters.put("postiveAlcohol",reportForm.getPostiveAlcohol());
            }
        }
        if(reportForm.getWithVehicles() != null) {
            parameters.put("withVehicles",reportForm.getWithVehicles());
        }else{
        	parameters.put("withVehicles",DriverData.ALL);
        }
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        if(reportForm.getType().equals("resumed")) {
            DriverReportData result = sys.getDriverReportFacade().getReportDrivers(parameters,divOrgs,divGeos,first,count,sys.getUserSessionData());
            result.setMessages(messages);
            int pages = (int)(result.getTotal() / 50);
            if(pages*50 < result.getTotal()) {
                pages++;
            }
            DriverReportFactory factory = new DriverReportFactory("Drivers",result,reportForm,sys,messages,1,pages,page);
            report = factory.createReport();
        }else if(reportForm.getType().equals("detailed")) {
            DetailedDriverReportData result = sys.getDriverReportFacade().getDetailedReportDrivers(parameters,divOrgs,divGeos,first,count,sys.getUserSessionData());
            result.setMessages(messages);
            int pages = (int)(result.getTotal() / 50);
            if(pages*50 < result.getTotal()) {
                pages++;
            }
            DetailedDriverReportFactory factory = new DetailedDriverReportFactory("Drivers",result,reportForm,sys,messages,pages,page);
            report = factory.createReport();        
        }else {
            DetailedDriverReportWithVehiclesData result = sys.getDriverReportFacade().
                	getDetailedReportDriversWithVehicles(parameters,divOrgs,divGeos,
                			first,count,sys.getUserSessionData());
                int pages = 1;
                if (result != null){
                	result.setMessages(messages);
                	pages = (int)(result.getTotal() / 50);
                	if(pages*50 < result.getTotal()) {
                		pages++;
                	}
                }
                DetailedDriverReportWithVehiclesFactory factory = new 
                	DetailedDriverReportWithVehiclesFactory("Drivers",result,reportForm,sys,
                		messages,pages,page);
                report = factory.createReport();        	
        }
        return report;
        
        
	    }catch(Exception e){
	    	e.printStackTrace();
	    	throw e;
	    }
	    
    }
} 
    


