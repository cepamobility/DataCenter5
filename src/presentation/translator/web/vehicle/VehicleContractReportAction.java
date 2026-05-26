package presentation.translator.web.vehicle;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.vehicle.VehicleContractReportFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.util.ListResult;
import communication.vehicle.report.VehicleContractReportData;


public class VehicleContractReportAction extends AbstractAction {

	/**
	 * 
	 */
    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();
        
        VehicleContractReportForm reportForm = (VehicleContractReportForm)form;
        String message = null;
        
		message = reportForm.validate(userSessionData);
		if(message != null){ 
			//	Some attribute data is invalid	
			userSessionData.setUrl("/vehicle/ContractReport.jsp");
			userSessionData.setMsg(messages.getText(message));
			return mapping.findForward("layout");		
		}      

		JFreeReportBoot jfree = JFreeReportBoot.getInstance(); 
		try{
			jfree.start();	
		}catch(java.lang.AbstractMethodError a){
			a.printStackTrace();
			jfree.start();
		}catch (Throwable e){
			e.printStackTrace();
			jfree.start();
		}
		
		JFreeReport report = getReport(sys,reportForm,1);
		session.setAttribute("Bookmark",reportForm.getBookmark());
		return new ReportUtil().executeReport(mapping,response,report,
				reportForm.getOutput(),session,"VehicleContracts",true);

		/*
	    userSessionData.setUrl("/vehicle/ContractReportResult.jsp");
	    userSessionData.setValue(vehicles);
	    return mapping.findForward("layout");
	    */
    }
    
    /**
     * 
     * @param sys
     * @param from
     * @return
     * @throws Throwable 
     */
    public JFreeReport getReport(SystemAccess sys, VehicleContractReportForm reportForm, int pageNumber) throws Exception{
    	
    	
    	JFreeReport report = null;
    	try{
    		
    		UserSessionData userSessionData = sys.getUserSessionData();
    		Text messages = sys.getText();
    		
    		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
         	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
         	String type = reportForm.getType(); // tipo de contrato
         	int status = Integer.parseInt(reportForm.getStatus());
         	String vehicleType = reportForm.getVehicleType();
         	Date from = (reportForm.getFrom() != null && reportForm.getFrom().trim() != "")?
         			Util.getDate(reportForm.getFrom().trim()).getTime():null;
        	Date to = (reportForm.getTo() != null && reportForm.getTo().trim() != "")?
     	     		Util.getDate(reportForm.getTo()).getTime():null;
     	    

	    	int total = 0;
	    	int pages = 1;
	 	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	 	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	 	    
     	    ListResult listResult = sys.getVehicleReportFacade().
     				getVehiclesWithDriverAndContracts(type, status, divOrgs, divGeos, vehicleType,
     						from, to, first,count,userSessionData);
     	   
     	    VehicleContractReportData result = null;
     	    if (listResult != null){
     	    	result = new VehicleContractReportData(listResult.getElements(),listResult.getTotal());
     	    	total = listResult.getTotal();
     	    }
     	    if (result != null){
     	    	result.setMessages(messages);
     	    	pages = (int)(total / 50);
     	    	if (pages * 50 < total) {
     	    		pages++;
     	    	}
     	    }
     	    
     	    VehicleContractReportFactory factory = new VehicleContractReportFactory("VehiclesContract",
    			result,reportForm,sys,messages,count,pages,pageNumber);
     	    report = factory.createReport();
     	    
     	   
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    	return report;
    }
    
    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


