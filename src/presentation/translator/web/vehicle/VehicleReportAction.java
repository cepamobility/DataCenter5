/*
 * Created on 24-feb-2006
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
import presentation.report.vehicle.VehicleReportExtendedFactory;
import presentation.report.vehicle.VehicleReportFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.util.Filter;
import communication.util.ListResult;
import communication.vehicle.report.VehicleReportCombinedData;
import communication.vehicle.report.VehicleReportCombinedExtendedData;
import communication.vehicle.report.VehicleReportData;
import communication.vehicle.report.VehicleReportExtendedData;


public class VehicleReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        JFreeReport report = null;
        BookmarkData bookmark = null;
        String output = null;

	    JFreeReportBoot jfree = JFreeReportBoot.getInstance(); 
		try{
			jfree.start();	
		}catch (Throwable e){
			e.printStackTrace();
			jfree.start();
		}

		if(sys.getCorporationId() == CorporationData.ALLERGAN) {
	        VehicleMultiReportForm reportForm = (VehicleMultiReportForm)form;
	        report = getReportOnlyVehicles(sys,reportForm,1);
	        bookmark = reportForm.getBookmark();
	        output = reportForm.getOutput();
        }else {
	        VehicleReportForm reportForm = (VehicleReportForm)form;
		    
		    // Validacion para Forestales:
		    if (sys.getUserSessionData().isForest()){
		        String plataformCheck = reportForm.getPlataformCheck();
			    String vehicleCheck = reportForm.getVehicleCheck();
		    	if (plataformCheck == null && vehicleCheck == null){
		    		 session.setAttribute("Msg",messages.getText("generic.error.selectplataformorvehicle"));
			         return mapping.findForward("back");
		    	}
		    }
	        
	        report = getReport(sys,reportForm,1);
	        bookmark = reportForm.getBookmark();
	        output = reportForm.getOutput();
        }
        session.setAttribute("Bookmark",bookmark);
        return new ReportUtil().executeReport(mapping,response,report,output,session,"vehicles",true);
    }

    public JFreeReport getReport(SystemAccess sys,VehicleReportForm reportForm,
		 int pageNumber) throws Exception {

	    JFreeReport report = null;
	    String plataformCheck = reportForm.getPlataformCheck();
	    String vehicleCheck = reportForm.getVehicleCheck();	    
	   
	    if (sys.getUserSessionData().isForest() && 
	    	plataformCheck != null &&
	    	plataformCheck.trim().equalsIgnoreCase("on") && 
	    	vehicleCheck != null &&
	    	vehicleCheck.trim().equalsIgnoreCase("on")){
	    	// Muestro para Forestales: Vehiculos y Plataformas:
	    	report = getReportBothTrailersAndVehicles(sys,reportForm,pageNumber);
	    	
	    }else if (sys.getUserSessionData().isForest() && 
	    	plataformCheck != null &&
		    plataformCheck.trim().equalsIgnoreCase("on")){
	    	// Muestro para Forestales solo Plataformas:
	    	report = getReportOnlyTrailers(sys,reportForm,pageNumber);
	    	
	    }else{
	    	// Muestro para cualquier empresa solo Vehiculos:
	    	// Comportamiento por defecto.
	    	report = getReportOnlyVehicles(sys,reportForm,pageNumber);
		   
	    }
	    return report;
    }
    
    
    /**
     * 
     * @param sys
     * @param reportForm
     * @param pageNumber
     * @return
     * @throws Exception
     */
    private static JFreeReport getReportBothTrailersAndVehicles(SystemAccess sys,VehicleReportForm reportForm,
      		 int pageNumber) throws Exception{
    	try{
	    	ListResult listResult;
	   	 	JFreeReport report = null;
	   	 	Text messages = sys.getText();
	   	 	VehicleReportCombinedData result = null;
	   		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
	     	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
	     	int status = Integer.parseInt(reportForm.getStatus());
	     	VehicleReportCombinedExtendedData extendedResult = null;
	   	 	
	    	int total = 0;
	    	int pages = 1;
	 	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	 	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	 	 
		    if (reportForm.getReportType().equals("Resumido")) 
		    { 
		    	// Reporte Resumido
		     	listResult = sys.getVehicleReportFacade().
		     		getReportVehiclesAndTrailers(reportForm.getType(),reportForm.getTrade(),
		     			reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
		     			reportForm.getCenterCost(),divOrgs,divGeos,first,count,
		     			sys.getUserSessionData(),reportForm.getPlate(), reportForm.getModel(), status);
		
		     	
		     	if (listResult != null){
		     		result = new VehicleReportCombinedData(listResult.getElements(),listResult.getTotal());
		     		total = listResult.getTotal();
		     		result.setMessages(messages);
		     		pages = (int)(total / 50);
		     		if (pages * 50 < total) {
		     			pages++;
		     		}     	   	
		     	}
		     	
		     	VehicleReportFactory factory = new VehicleReportFactory("Vehicles",
		     			result,	reportForm,sys,messages,count,pages,pageNumber);
		     	report = factory.createReport();
		     }	
		     else 
		     {
		    	 // Reporte Detallado
		     	listResult = sys.getVehicleReportFacade().
		     		getReportVehiclesAndTrailers(reportForm.getType(),reportForm.getTrade(),
		     				reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
		     				reportForm.getCenterCost(),divOrgs,divGeos,first,count
		     				,sys.getUserSessionData(), reportForm.getPlate(), reportForm.getModel(),status);
		     	
		     	if (listResult != null){
		     		extendedResult = new VehicleReportCombinedExtendedData(
		     			listResult.getElements(),listResult.getTotal());
		     	
		     		total = listResult.getTotal();
		     		extendedResult.setMessages(messages);
	   		        extendedResult.setCorporation(sys.getCorporationId());
		     		pages = (int)(total / 50);
		     		if (pages * 50 < total) {
		     			pages++;
		     		}
		     	}
		     	VehicleReportExtendedFactory factory = new VehicleReportExtendedFactory("Vehicles",
		     			extendedResult,reportForm,sys,messages,
		     			count,pages,pageNumber);
		     	report = factory.createReport();
		     } 
		    return report;
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
	}
    /**
     * @param sys
     * @param reportForm
     * @param pageNumber
     * @return
     * @throws Exception
     */
    private static JFreeReport getReportOnlyVehicles(SystemAccess sys,VehicleReportForm reportForm,
   		 int pageNumber) throws Exception{
    
    	try{
	    	ListResult listResult;
	   	 	JFreeReport report = null;
	   	 	Text messages = sys.getText();
	   	 	VehicleReportData result;
	   		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
	     	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
	     	int status = Integer.parseInt(reportForm.getStatus());
	   	 	VehicleReportExtendedData extendedResult;
	   	 	
	    	int total = 0;
	 	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	 	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	 	 
		    if (reportForm.getReportType().equals("Resumido")) 
		    { 
		    	// Reporte Resumido
		     	listResult = sys.getVehicleReportFacade().
		     		getReportVehicles(reportForm.getType(),reportForm.getTrade(),
		     			reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
		     			reportForm.getCenterCost(),divOrgs,divGeos,first,count,
		     			sys.getUserSessionData(),reportForm.getPlate(), reportForm.getModel(), status);
		
		     	result = new VehicleReportData(listResult.getElements(),listResult.getTotal());
		
		       	total = listResult.getTotal();
		        result.setMessages(messages);
		        int pages = (int)(total / 50);
		        if (pages * 50 < total) {
		            pages++;
		        }     	   	
		     	
		     	VehicleReportFactory factory = new VehicleReportFactory("Vehicles",
		     			result,
		     			reportForm,sys,messages,count,pages,pageNumber);
		     	report = factory.createReport();
		     }	
		     else 
		     {
		    	 // Reporte Detallado
		     	listResult = sys.getVehicleReportFacade().
		     		getExtendedReportVehicles(reportForm.getType(),reportForm.getTrade(),
		     				reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
		     				reportForm.getCenterCost(),divOrgs,divGeos,first,count
		     				,sys.getUserSessionData(), reportForm.getPlate(), reportForm.getModel(),status);
		     	extendedResult = new VehicleReportExtendedData(listResult.getElements(),listResult.getTotal());
		       	total = listResult.getTotal();
		        extendedResult.setMessages(messages);
   		        extendedResult.setCorporation(sys.getCorporationId());
		        int pages = (int)(total / 50);
		        if (pages * 50 < total) {
		            pages++;
		        }
		     	
		     	VehicleReportExtendedFactory factory = new VehicleReportExtendedFactory("Vehicles",
		     			extendedResult,reportForm,sys,messages,
		     			count,pages,pageNumber);
		     	report = factory.createReport();
		     } 
		    return report;
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
	}
    
    /**
     * @param sys
     * @param reportForm
     * @param pageNumber
     * @return
     * @throws Exception
     */
    private static JFreeReport getReportOnlyVehicles(SystemAccess sys,VehicleMultiReportForm reportForm, int pageNumber) throws Exception{
    
    	try{
	    	ListResult listResult;
	   	 	JFreeReport report = null;
	   	 	Text messages = sys.getText();
	   	 	VehicleReportData result;
	      	
	   	 	Integer[][] divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
	        Integer[] geos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
	    	Integer[][] divGeos = new Integer[2][geos.length];
	        divGeos[0] = geos;

	        int status = Integer.parseInt(reportForm.getStatus());
	   	 	VehicleReportExtendedData extendedResult;
	   	 	
	    	int total = 0;
	 	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	 	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	 	 
		    if (reportForm.getReportType().equals("Resumido")) 
		    { 
		    	// Reporte Resumido
		     	listResult = sys.getVehicleReportFacade().
		     		getReportVehicles(reportForm.getType(),reportForm.getTrade(),
		     			reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
		     			reportForm.getCenterCost(),divOrgs,divGeos,first,count,
		     			sys.getUserSessionData(),reportForm.getPlate(), reportForm.getModel(), status);
		
		     	result = new VehicleReportData(listResult.getElements(),listResult.getTotal());
		
		       	total = listResult.getTotal();
		        result.setMessages(messages);
		        int pages = (int)(total / 50);
		        if (pages * 50 < total) {
		            pages++;
		        }     	   	
		     	
		     	VehicleReportFactory factory = new VehicleReportFactory("Vehicles",
		     			result,
		     			reportForm,sys,messages,count,pages,pageNumber);
		     	report = factory.createReport();
		     }	
		     else 
		     {
		    	 // Reporte Detallado
		     	listResult = sys.getVehicleReportFacade().
		     		getExtendedReportVehicles(reportForm.getType(),reportForm.getTrade(),
		     				reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
		     				reportForm.getCenterCost(),divOrgs,divGeos,first,count
		     				,sys.getUserSessionData(), reportForm.getPlate(), reportForm.getModel(),status);
		     	extendedResult = new VehicleReportExtendedData(listResult.getElements(),listResult.getTotal());
		       	total = listResult.getTotal();
		        extendedResult.setMessages(messages);
   		        extendedResult.setCorporation(sys.getCorporationId());
		        int pages = (int)(total / 50);
		        if (pages * 50 < total) {
		            pages++;
		        }
		     	
		     	VehicleReportExtendedFactory factory = new VehicleReportExtendedFactory("Vehicles",
		     			extendedResult,reportForm,sys,messages,
		     			count,pages,pageNumber);
		     	report = factory.createReport();
		     } 
		    return report;
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
	}
    
    
    /**
     * 
     * @param sys
     * @param reportForm
     * @param pageNumber
     * @return
     * @throws Exception
     */
    private static JFreeReport getReportOnlyTrailers(SystemAccess sys,VehicleReportForm reportForm,
      		 int pageNumber) throws Exception{
       
       	try{
   	    	ListResult listResult;
   	   	 	JFreeReport report = null;
   	   	 	Text messages = sys.getText();
   	   	 	VehicleReportData result;
   	   		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
   	     	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
   	     	int status = Integer.parseInt(reportForm.getStatus());
   	   	 	VehicleReportExtendedData extendedResult;
   	   	 	
   	    	int total = 0;
   	 	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
   	 	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        

   	 	    
   		    if (reportForm.getReportType().equals("Resumido")) 
   		    { 
   		    	// Reporte Resumido
   		     	listResult = sys.getVehicleReportFacade().
   		     		getReportTrailers(reportForm.getType(),reportForm.getTrade(),
   		     			reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
   		     			reportForm.getCenterCost(),divOrgs,divGeos,first,count,
   		     			sys.getUserSessionData(),reportForm.getPlate(), reportForm.getModel(), status);
   		
   		     	result = new VehicleReportData(listResult.getElements(),listResult.getTotal());
   		
   		       	total = listResult.getTotal();
   		        result.setMessages(messages);
   		        int pages = (int)(total / 50);
   		        if (pages * 50 < total) {
   		            pages++;
   		        }     	   	
   		     	
   		     	VehicleReportFactory factory = new VehicleReportFactory("Trailers",
   		     			result,
   		     			reportForm,sys,messages,count,pages,pageNumber);
   		     	report = factory.createReport();
   		     }	
   		     else 
   		     {
   		    	 // Reporte Detallado
   		     	listResult = sys.getVehicleReportFacade().
   		     		getExtendedReportTrailers(reportForm.getType(),reportForm.getTrade(),
   		     				reportForm.getYear(),reportForm.getUse(),reportForm.getFuel(),
   		     				reportForm.getCenterCost(),divOrgs,divGeos,first,count
   		     				,sys.getUserSessionData(), reportForm.getPlate(), reportForm.getModel(),status);
   		     	extendedResult = new VehicleReportExtendedData(listResult.getElements(),listResult.getTotal());
   		       	total = listResult.getTotal();
   		        extendedResult.setMessages(messages);
   		        extendedResult.setCorporation(sys.getCorporationId());
   		        int pages = (int)(total / 50);
   		        if (pages * 50 < total) {
   		            pages++;
   		        }
   		     	
   		     	VehicleReportExtendedFactory factory = new VehicleReportExtendedFactory("Vehicles",
   		     			extendedResult,reportForm,sys,messages,
   		     			count,pages,pageNumber);
   		     	report = factory.createReport();
   		     } 
   		    return report;
       	}catch(Exception e){
       		e.printStackTrace();
       		throw e;
       	}
   	}
    
    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


