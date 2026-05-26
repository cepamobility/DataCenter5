/*
 * Created on 24-feb-2006
 */
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
import presentation.report.vehicle.ComplaintReportFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.util.ListResult;
import communication.vehicle.report.ComplaintReportData;


public class ComplaintReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        ComplaintReportForm reportForm = (ComplaintReportForm)form;
        
        
    	JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"vehicle",true);
    }

    public static JFreeReport getReport(SystemAccess sys,ComplaintReportForm reportForm, int pageNumber) throws Exception{
    
    	try{
	   	 	JFreeReport report = null;
	   	 	Text messages = sys.getText();
	   		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
	     	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
	     	int status = Integer.parseInt(reportForm.getStatus());
	   	 	
	    	int total = 0;
	 	    int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
	 	    int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
	 	 
	 	    Date from = Util.getDate(reportForm.getFromDate(), reportForm.getFromMonth(), reportForm.getFromYear());
	 	    Date to = Util.getDate(reportForm.getToDate(), reportForm.getToMonth(), reportForm.getToYear());
	 	   
	 	    ListResult listResult = sys.getVehicleReportFacade().
		     		getComplaintsReport(reportForm.getPlate(),from,to,divOrgs,divGeos,first,count, status,
		     			sys.getUserSessionData());
		
		     ComplaintReportData result= new ComplaintReportData(listResult.getElements(),listResult.getTotal(),messages);
		
		     total = listResult.getTotal();
		     int pages = (int)(total / 50);
		     if (pages * 50 < total) {
		    	 pages++;
		     }     	   	
		     	
		     ComplaintReportFactory factory = new ComplaintReportFactory("Complaints",
		     			result,
		     			reportForm,sys,messages,count,pages,pageNumber);
		     report = factory.createReport();
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
    


