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
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.HistoryQueryFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.driver.report.HistoryDataType;
import communication.driver.report.HistoryQueryData;
import communication.language.Text;
import communication.util.ListResult;

/**
 * 
 * @author jrodriguez
 *
 */
public class DriverHistoryQueryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DriverHistoryQueryForm reportForm = (DriverHistoryQueryForm)form;
        
        session.setAttribute("Bookmark",reportForm.getBookmark());
        JFreeReport report = getReport(sys,reportForm,1);
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"DriverHistory",true);
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys,DriverHistoryQueryForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        
        Text messages = sys.getText();
        
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;

        ListResult result = sys.getDriverReportFacade().getHistoryDrivers(reportForm.getFirstname(),reportForm.getLastname(),reportForm.getStatus(),divOrgs,divGeos,first,count,sys.getUserSessionData());
        if (result.getElements().size() > 0){
            reportForm.setLastUpdate(((HistoryDataType)result.getElements().iterator().next()).getLastUpdate());
         }else{
            reportForm.setLastUpdate(Calendar.getInstance().getTime());
         }
        HistoryQueryData hrdQuery = new HistoryQueryData(result, messages, reportForm.getOutput().equals("XLS"));


        int total = (result.getTotal() == -1) ? 0 : result.getTotal();
        boolean flag = (result.getTotal() == -1);
        int pages = (int)(total / 50);
        if(pages*50 < total) {
            pages++;
        }

        HistoryQueryFactory factory = new HistoryQueryFactory(messages.getText("query.history.title"),hrdQuery,reportForm,sys,messages,count,pages,page,flag);
        
        JFreeReportBoot.getInstance().start();
        JFreeReport report = factory.createReport();
        return report;
    }
    
} 
    


