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
import presentation.report.driver.AptitudTestReportFactory;
import presentation.report.driver.BpsRegistryReportQueryFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.report.AptitudTestReportData;
import communication.language.Text;


/**
 * @author adocarmo
 */
public class AptitudTestReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        AptitudTestReportForm reportForm = (AptitudTestReportForm)form;

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"aptitudtest",true);
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public static JFreeReport getReport(SystemAccess sys,AptitudTestReportForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        JFreeReport report = null;
        Text messages = sys.getText();
        Integer fromMonth = (Util.isNumber(reportForm.getFromMonth())) ? new Integer(reportForm.getFromMonth()) : null;
        Integer fromYear = (Util.isNumber(reportForm.getFromYear())) ? new Integer(reportForm.getFromYear()) : null;
        Integer toMonth = (Util.isNumber(reportForm.getToMonth())) ? new Integer(reportForm.getToMonth()) : null;
        Integer toYear = (Util.isNumber(reportForm.getToYear())) ? new Integer(reportForm.getToYear()) : null;
        AptitudTestReportData result = sys.getDriverReportFacade().getDriversTest(reportForm.getName(),reportForm.getLastName(),Integer.parseInt(reportForm.getStatus()),fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,sys.getUserSessionData());
        result.setMessages(messages);
        int pages = (int)(result.getTotal() / 50);
        if(pages*50 < result.getTotal()) {
            pages++;
        }
        AptitudTestReportFactory factory = new AptitudTestReportFactory("AptitudTest",result,reportForm,sys,messages,1,pages,page);
        report = factory.createReport();
        return report;
    }
} 
    


