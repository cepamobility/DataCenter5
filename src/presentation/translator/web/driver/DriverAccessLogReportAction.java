/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

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
import presentation.report.driver.DriverAccessLogReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.driver.report.DriverAccessLogReportData;


/**
 * @author adocarmo
 */
public class DriverAccessLogReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DriverAccessLogForm reportForm = (DriverAccessLogForm)form;

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm);
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"bpsregistry",true);
        sys.getUserSessionData().setUrl("/driver/reportResult.jsp");
        return fwd;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public static JFreeReport getReport(SystemAccess sys,DriverAccessLogForm reportForm) throws Exception {
        Collection logs = sys.getDriverReportFacade().getDriverAccessLog(reportForm.getCorporationId(),reportForm.getContratistIds(), reportForm.getStart(), reportForm.getEnd(), new Integer(reportForm.getAccess()), sys.getUserSessionData());
        DriverAccessLogReportData data = new DriverAccessLogReportData(logs,sys.getText());
        DriverAccessLogReportFactory factory = new DriverAccessLogReportFactory("Access Log",data,reportForm,sys);
        return factory.createReport();
    }
} 
    


