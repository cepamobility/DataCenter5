/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.ReportCommentaryDriverLogFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.report.ReportCommentaryDriveLogData;
import communication.language.Text;
import communication.util.Filter;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportCommentaryDriveLogAction extends AbstractAction {
	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportCommentaryDriveLogForm reportForm = (ReportCommentaryDriveLogForm)form;
        
        if(reportForm == null) {
            return mapping.findForward("back");
        }
        
        if(reportForm.getFrommonth().length() == 0 || reportForm.getTomonth().length() == 0 || reportForm.getFromyear().length() == 0 || reportForm.getToyear().length() == 0) {
            session.setAttribute("Msg",messages.getText("generic.error.emptyperiod"));
            return mapping.findForward("back");
        }
        
        JFreeReport report = getReport(sys,reportForm);
        session.setAttribute("Bookmark",reportForm.getBookmark());
       
        JFreeReportBoot.getInstance().start();
        
        session.removeAttribute("ReportCommentaryDriveLogForm");
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"AccidentType",null,true);
        
        return fwd;
        
    }
       
    public JFreeReport getReport(SystemAccess sys, ReportCommentaryDriveLogForm reportForm) throws Exception {
        Text messages = sys.getText();

        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm,Filter.CORPORATION);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm,Filter.CORPORATION);

        int order1 = Integer.parseInt(reportForm.getOrder1());
        int order2 = Integer.parseInt(reportForm.getOrder2());
        int order3 = Integer.parseInt(reportForm.getOrder3());
        int order4 = Integer.parseInt(reportForm.getOrder4());
        
        Collection elements = sys.getActivityReportFacade().findCommentaryDriveLog(sys.getUserSessionData(),fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,order1,order2,order3,order4);
        ReportCommentaryDriveLogData result = new ReportCommentaryDriveLogData(elements,messages,order1,order2,order3,order4);
            
        ReportCommentaryDriverLogFactory factory = new ReportCommentaryDriverLogFactory(messages.getText("query.commentarydriverlog.title"),result,reportForm,sys,messages,reportForm.getOutput().equals("HTML"));
        return factory.createReport();

    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


