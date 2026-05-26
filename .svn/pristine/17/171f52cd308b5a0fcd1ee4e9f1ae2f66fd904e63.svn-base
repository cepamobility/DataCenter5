package presentation.translator.web.driver;

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
import presentation.report.driver.TelematicControlReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.report.TelematicControlReportData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;

public class TelematicControlReportAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * @see presentation.translator.web.util.AbstractAction#action(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        TelematicControlQueryForm reportForm = (TelematicControlQueryForm)form;
        session.setAttribute("Bookmark",reportForm.getBookmark());

        JFreeReportBoot jfree;
        jfree = JFreeReportBoot.getInstance();
        try{        	
        	jfree.start();
        }catch(Throwable e){
        	jfree.start();
        }
        
        JFreeReport report = getReport(sys,reportForm,1);

        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"TelematicControl",true);
        return fwd;
        
    }
    
    /**
     * 
     * @param sys
     * @param reportForm
     * @param pageNumber
     * @return
     * @throws Exception
     */
    public JFreeReport getReport(SystemAccess sys,TelematicControlQueryForm reportForm,
   		 int pageNumber) throws Exception {
       	try{
      
       		JFreeReport report = null;
       		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
       		Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
       		Text messages = sys.getText();
       		
       		Date from = Util.getDate(reportForm.getFromday(), reportForm.getFrommonth(), reportForm.getFromyear());
       		Date to = Util.getDate(reportForm.getToday(), reportForm.getTomonth(), reportForm.getToyear());
       		
       		Integer type = (Util.empty(reportForm.getType())) ? null : new Integer(reportForm.getType());
       		String firstName = (Util.empty(reportForm.getFirstname())) ? null : reportForm.getFirstname().trim();
       		String lastName = (Util.empty(reportForm.getLastname())) ? null : reportForm.getLastname().trim(); 
       		
       		Collection<TelematicControlData> result = sys.getDriverReportFacade().findTelematicControls(firstName, lastName, from, to, type, Integer.parseInt(reportForm.getStatus()), divOrgs, divGeos, sys.getUserSessionData());
       		
       		TelematicControlReportData query = new TelematicControlReportData(result, sys.getUserSessionData());
       		query.setMessages(messages);
            TelematicControlReportFactory factory = new TelematicControlReportFactory(messages.getText("query.telematiccontrolquery.title"), query, reportForm, sys);
       		
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
    


