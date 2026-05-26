package presentation.translator.web.event;

import java.util.Collection;
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
import presentation.report.driver.DriverInsertedLogFactory;
import presentation.report.event.FinePointReportFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.driver.GenericReportForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.report.DriverInsertedLogData;
import communication.event.report.FinePointReportData;
import communication.language.Text;

public class FinePointReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        FinePointReportForm reportForm = (FinePointReportForm)form;

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm);

        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"DriverInsertedLog",true);
        return fwd;
        
    }
    
    public JFreeReport getReport(SystemAccess sys,FinePointReportForm reportForm) throws Exception {
       	try{
      
       		JFreeReport report = null;
       		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
       		Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
       		Text messages = sys.getText();
       		
       		int rows = 50;
          
       		Date from = Util.getDate(reportForm.getFromDate(), reportForm.getFromMonth(), reportForm.getFromYear());
       		Date to = Util.getDate(reportForm.getToDate(), reportForm.getToMonth(), reportForm.getToYear());

       		Collection result = sys.getEventReportFacade().getFinePoints(from,to,divOrgs,divGeos,reportForm.getCompleteTypes(),Integer.parseInt(reportForm.getStatus()),sys.getUserSessionData());
       		
       		FinePointReportData query = new FinePointReportData(result, messages);
       		
       		FinePointReportFactory factory = new FinePointReportFactory(messages.getText("query.finepoints.title"), query,reportForm,sys,messages);
       		report = factory.createReport();
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
    


