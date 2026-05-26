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
import presentation.report.driver.BpsRegistryReportQueryFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.driver.report.DriverReportData;
import communication.language.Text;


/**
 * @author adocarmo
 */
public class BpsRegistryReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DriverReportForm reportForm = (DriverReportForm)form;
        /*if(Util.empty(reportForm.getCountry2())) {
            session.setAttribute("Msg",messages.getText("reportapmm.error.emptycountry"));
            return mapping.findForward("back");
        }*/

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"bpsregistry",true);
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public static JFreeReport getReport(SystemAccess sys,DriverReportForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        DriverReportData result = sys.getDriverReportFacade().getDriversWithoutBpsRegistry(reportForm.getName(),reportForm.getStatus(),divOrgs,divGeos,first,count,sys.getUserSessionData());
        result.setMessages(messages);
        int pages = (int)(result.getTotal() / 50);
        if(pages*50 < result.getTotal()) {
            pages++;
        }
        BpsRegistryReportQueryFactory factory = new BpsRegistryReportQueryFactory("Drivers",result,reportForm,sys,messages,1,pages,page);
        report = factory.createReport();
        return report;
    }
} 
    


