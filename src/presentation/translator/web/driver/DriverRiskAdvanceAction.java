/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.DriverLicenseExpiryFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.driver.report.DriverLicenseExpiryData;
import communication.language.Text;
import communication.util.ListResult;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DriverRiskAdvanceAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();

        LicenseExpiryForm reportForm = (LicenseExpiryForm)form;
        session.setAttribute("Bookmark",reportForm.getBookmark());

   		Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
   		Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);

   		if(Util.empty(reportForm.getFrommonth()) || Util.empty(reportForm.getFromyear()) 
   				|| Util.empty(reportForm.getTomonth()) || Util.empty(reportForm.getToyear())) {
			userSessionData.setMsg(messages.getText("driver.error.dateparameters"));
		    userSessionData.setUrl("/driver/riskAdvanceQuery.jsp");
		    return mapping.findForward("layout");
   			
   		}
   		
   		Calendar from = Calendar.getInstance();
   		from.set(Calendar.MONTH, Integer.parseInt(reportForm.getFrommonth())-1);
   		from.set(Calendar.YEAR, Integer.parseInt(reportForm.getFromyear()));

   		Calendar to = Calendar.getInstance();
   		to.set(Calendar.MONTH, Integer.parseInt(reportForm.getTomonth())-1);
   		to.set(Calendar.YEAR, Integer.parseInt(reportForm.getToyear()));
   		
   		if(from.after(to)) {
			userSessionData.setMsg(messages.getText("driver.error.dateparameters"));
		    userSessionData.setUrl("/driver/riskAdvanceQuery.jsp");
		    return mapping.findForward("layout");
   		}

        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=\"RiskAdvance.xls\"");
        sys.getDriverReportFacade().driverRiskAdvanceToXLS(from, to, divOrgs, divGeos, reportForm.getFirstname(),reportForm.getLastname(), Integer.parseInt(reportForm.getStatus()) , sys.getText(), sys.getUserSessionData(),response.getOutputStream());

   		return null;
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


