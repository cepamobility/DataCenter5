/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.vehicle.ReportCheckListDetailedFactory;
import presentation.report.vehicle.ReportCheckListFactory;
import presentation.report.vehicle.SuctaMTOPQueryFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.util.Filter;
import communication.util.ListResult;
import communication.vehicle.report.ReportCheckListData;
import communication.vehicle.report.ReportCheckListDetailedData;
import communication.vehicle.report.SuctaMTOPQueryData;
import communication.vehicle.report.VehicleCheckReportDataType;


/**
 * @author adocarmo
 *
 */
public class ReportCheckListAction extends AbstractAction {

	
    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

    	try{
	        HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
	
	        ReportCheckListForm reportForm = (ReportCheckListForm)form;
	        
	        String fromMonth = reportForm.getFrommonth();
	        String toMonth = reportForm.getTomonth();
	        String fromYear = reportForm.getFromyear();
	        String toYear = reportForm.getToyear();
	        if(fromMonth.length() == 0 || toMonth.length() == 0 || fromYear.length() == 0 || toYear.length() == 0) {
	            session.setAttribute("Msg",messages.getText("generic.error.emptyperiod"));
	            return mapping.findForward("back");
	        }
	        if((fromMonth.startsWith("Q") && !toMonth.startsWith("Q")) || (!fromMonth.startsWith("Q") && toMonth.startsWith("Q"))) {
	            session.setAttribute("Msg",messages.getText("generic.error.invalidperiod"));
	            return mapping.findForward("back");
	        }
	        if(fromMonth.startsWith("Q")) {
	            fromMonth = String.valueOf(Integer.parseInt(fromMonth.substring(1)) * 3 - 2);
	        }
	        if(toMonth.startsWith("Q")) {
	            toMonth = String.valueOf(Integer.parseInt(toMonth.substring(1)) * 3);
	        }
	         
	        
	        
	        // Inicio el JFreeReport:
	        try{
	        	JFreeReportBoot.getInstance().start();
	        }catch(Exception e){
	        	// La primera vez que se corre puede dar error de cache de Pentahoo.
	        	// asi que lo corremos de nuevo...jeje
	        	JFreeReportBoot.getInstance().start();
	        }
	        
	        
	        session.removeAttribute("ReportCheckListForm");   
	        
	        JFreeReport report = getReport(sys,reportForm,1);
            session.setAttribute("Bookmark",reportForm.getBookmark());
	        // Muestro el reporte:
	        return new ReportUtil().executeReport(mapping,response, report,reportForm.getOutput(),session,"CheckList",true);	  
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    }
 
    
    public JFreeReport getReport(SystemAccess sys,ReportCheckListForm reportForm, int pageNumber) throws Exception {

       	try{
	       	Integer[][] divOrgs = Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.USER);
	        Integer[] divGeos = Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.USER);
	        
	        String fromMonth = reportForm.getFrommonth();
	        String toMonth = reportForm.getTomonth();
	        String fromYear = reportForm.getFromyear();
	        String toYear = reportForm.getToyear();
	        
	        int order = 0;
	        try{
	        	switch(Integer.parseInt(reportForm.getOrder1())) {
	            	case 1:
	            		if(Integer.parseInt(reportForm.getOrder2()) == 2) {
	            			order = VehicleCheckReportDataType.CONTRACT_TYPE;
	            		}else {
	            			order = VehicleCheckReportDataType.CONTRACT;
	            		}
	            		break;
	            	case 2:
	            		if(Integer.parseInt(reportForm.getOrder2()) == 1) {
	            			order = VehicleCheckReportDataType.TYPE_CONTRACT;
	            		}else {
	            			order = VehicleCheckReportDataType.TYPE;
	            		}	
	        	}
	        }catch(Exception e){
	        	// Ordenes equivocados o no seteados, uso el order por defecto: 0
	        }
	        
	        ListResult listResult;
	   	    Text messages = sys.getText();
	   	    int total = 0;
	   	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	   	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	   	
	   	    
	   	    ActionForward fwd = null;        
	        JFreeReport report = null;
	        boolean getAll = false;

            // Vehicle or Trailer
        	String type = "vehicle"; // Camion
        	if (reportForm.getType().trim().equalsIgnoreCase("trailer")){
        		type = "trailer";
        	}
	        
	   	 	if (reportForm.getReportType().trim().equalsIgnoreCase("detailedsimple")){
	        	// Reporte de Checklist con detalle de los puntos en que hay errores1	        	
	        	
	        	// DetailedSimple, solo items erroneos:
	        	getAll = false;
	        	
	        	ReportCheckListDetailedData result = sys.getVehicleReportFacade().
	        	 	getChecksCountDetailed(new Integer(fromMonth),new Integer(fromYear),
	        	 	new Integer(toMonth),new Integer(toYear),divOrgs,divGeos,messages,
	        	 	order,sys.getUserSessionData(),getAll,type,count,first,reportForm.getTypeCheck());
	 	    	
	        	total = result.getTotalChecks();
	        	int pages = (int)(total / 50);
	        	if (pages * 50 < total) {
	               pages++;
	        	}       	

	 	        // Creo el reporte:   
	        	ReportCheckListDetailedFactory factory;
	 	        factory = new ReportCheckListDetailedFactory("CheckList",result,reportForm,
	 	        		sys,messages,order,count,pages,pageNumber);
	 	        report = factory.createReport();
	 	    
	 	       
	        	
	        }else if (reportForm.getReportType().trim().equalsIgnoreCase("detailedcomplete")){
	        	// Reporte de Checklist con TODOS los puntos de los checklist, los que tienen
	        	// errores y los que no.
	        	        	
	        	
	        	// DetailedComplete
	        	getAll = true;
	        	
	        	ReportCheckListDetailedData result = sys.getVehicleReportFacade().
	        	 	getChecksCountDetailed(new Integer(fromMonth),new Integer(fromYear),
	        	 	new Integer(toMonth),new Integer(toYear),divOrgs,divGeos,messages,
	        	 	order,sys.getUserSessionData(),getAll,type,count,first,reportForm.getTypeCheck());
	        	
	        	total = result.getTotalChecks();
	        	int pages = (int)(total / 50);
	        	if (pages * 50 < total) {
	               pages++;
	        	}       	

	        	
	 	        // Creo el reporte:   
	        	ReportCheckListDetailedFactory factory;
	 	        factory = new ReportCheckListDetailedFactory("CheckList",result,
	 	        		reportForm,sys,messages,order,count,pages,pageNumber);
	 	        report = factory.createReport();
	 	    }else {
	        	// Reporte de Checklist Resumido (El de Siempre):        
	 	        
		        ReportCheckListData result = sys.getVehicleReportFacade().
		        	getChecksCount(new Integer(fromMonth),new Integer(fromYear),
		        	new Integer(toMonth),new Integer(toYear),divOrgs,
		        	divGeos,messages,order,sys.getUserSessionData());
		    	
		        // Creo el reporte:  
		        ReportCheckListFactory factory;
		        factory = new ReportCheckListFactory("CheckList",result,reportForm,
		        		sys,messages,order);
		        report = factory.createReport();
		    }
	   	 	
	   	    return report;
	   	    
	    }catch(Exception e){
       		e.printStackTrace();
       		throw e;
       	}
    }
       
    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


