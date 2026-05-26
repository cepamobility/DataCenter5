/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.queries;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportKmLtrFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;
import communication.queries.report.ReportKmLtrData;
import communication.security.SecurityConstants;
import communication.util.Filter;


/**
 * @author adocarmo
 *
 */
public class ReportKmLtrAction extends AbstractAction {

	// Constants
	private final String PATH = SecurityConstants.IMAGES_WAR_DIRECTORY; // "/tmp/datacenter/images/";
	
	
    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportConsumedFuelForm reportForm = (ReportConsumedFuelForm)form;
        
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
        
        Integer[][] divOrgs = Util.getDivOrgs(sys,reportForm,Filter.USER);
        Integer[] divGeos = Util.getDivGeos(sys,reportForm,Filter.USER);
        
        if (!Util.dateRange(fromYear,toYear,fromMonth,toMonth,2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
        }
        
        ReportKmLtrData result = sys.getFleetReportFacade().getKmLtrReport(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,sys.getUserSessionData(),messages);
    	
        ReportKmLtrFactory factory = new ReportKmLtrFactory("km-ltr",result,form,sys,messages);
        JFreeReport report = factory.createReport();      
        JFreeReportBoot.getInstance().start();

        session.setAttribute("Bookmark",reportForm.getBookmark(BookmarkData.KM_LTR_CONSUMED_REPORT));
        session.removeAttribute("ReportKmLtrForm");
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"fuel",true);
        return fwd;
    }
 
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys, ReportConsumedFuelForm reportForm) throws Exception {
        Text messages = sys.getText();

        Integer[][] divOrgs = Util.getDivOrgs(sys,reportForm,Filter.USER);
        Integer[] divGeos = Util.getDivGeos(sys,reportForm,Filter.USER);
        
        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        if(fromMonth.startsWith("Q")) {
            fromMonth = String.valueOf(Integer.parseInt(fromMonth.substring(1)) * 3 - 2);
        }
        if(toMonth.startsWith("Q")) {
            toMonth = String.valueOf(Integer.parseInt(toMonth.substring(1)) * 3);
        }
                
        ReportKmLtrData result = sys.getFleetReportFacade().getKmLtrReport(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,sys.getUserSessionData(),messages);
        
        ReportKmLtrFactory factory = new ReportKmLtrFactory("km-ltr",result,reportForm,sys,messages);
        return factory.createReport();      
    }
} 
    


