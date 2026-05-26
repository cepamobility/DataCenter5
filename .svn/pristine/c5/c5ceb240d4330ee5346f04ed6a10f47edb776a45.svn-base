/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.DriverLicenseExpiryFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.report.DriverLicenseExpiryData;
import communication.language.Text;
import communication.util.ListResult;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DriverLicenseExpiryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        LicenseExpiryForm reportForm = (LicenseExpiryForm)form;
        session.setAttribute("Bookmark",reportForm.getBookmark());

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);

        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"LicenseExpiry",true);
        return fwd;
        
    }
    
    public JFreeReport getReport(SystemAccess sys,LicenseExpiryForm reportForm,
   		 int pageNumber) throws Exception {
       	try{
      
       		JFreeReport report = null;
       		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
       		Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
       		Text messages = sys.getText();
       		
       		int rows = 50;
          
       		int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber-1)* rows : 0;
       		int count = (reportForm.getOutput().equals("HTML")) ? rows : -1;
          
     

       		
       		Integer fromYear = (Util.empty(reportForm.getFromyear())) ? null : 
       			new Integer(reportForm.getFromyear()); 
       		Integer toYear = (Util.empty(reportForm.getToyear())) ? null : 
       			new Integer(reportForm.getToyear()); 
       		
       		ListResult result = sys.getDriverReportFacade().
       		getDriverLicenseExpiry(reportForm.getFrommonth(),fromYear,
       				reportForm.getTomonth(),toYear,divOrgs,divGeos,
       				reportForm.getFirstname(),reportForm.getLastname(),
       				reportForm.getStatus(),first,count,sys.getUserSessionData());
       		
       		DriverLicenseExpiryData query = new DriverLicenseExpiryData(
            		result.getElements(),result.getTotal(), sys.getUserSessionData());
       		
       		query.setMessages(messages);
       		int pages = (int)(result.getTotal() / rows);
            if(pages*rows < result.getTotal()) {
                pages++;
            }  
       		
       		DriverLicenseExpiryFactory factory = new DriverLicenseExpiryFactory(
       				messages.getText("query.licenseexpiry.title"),
       				query,reportForm,sys,messages,pages,pageNumber);
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
    


