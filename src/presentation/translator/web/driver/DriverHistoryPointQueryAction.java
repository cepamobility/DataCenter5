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
import presentation.report.driver.HistoryPointQueryFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.driver.report.HistoryPointQueryData;
import communication.language.Text;
import communication.util.ListResult;

/**
 * 
 * @author jrodriguez
 *
 */
public class DriverHistoryPointQueryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DriverHistoryPointQueryForm reportForm = (DriverHistoryPointQueryForm)form;
        
        session.setAttribute("Bookmark",reportForm.getBookmark());
        JFreeReport report = getReport(sys,reportForm,1);
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"DriverHistory",true);
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys,DriverHistoryPointQueryForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        
        Text messages = sys.getText();
        
   		String operation = null;
   		int operator = Integer.parseInt(reportForm.getOperator());
   		if(operator > 0) {
   			switch(operator) {
       			case 1:
       				operation = " <= "+reportForm.getValue();
       				break;
       			case 2:
       				operation = " = "+reportForm.getValue();
       				break;
       			case 3:
       				operation = " >= "+reportForm.getValue();
       				break;
   			}
   		}

   		int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;

        ListResult result = null;
        if(sys.isCorporation(new int[]{CorporationData.BAYER,CorporationData.MONSANTO})) {
        	result = sys.getDriverReportFacade().getPointHistory(new Integer(reportForm.getPeriod()),new Integer(reportForm.getYear()),reportForm.getFirstname(),reportForm.getLastname(),reportForm.getStatus(),divOrgs,divGeos,first,count,operation,sys.getUserSessionData());
        }else {
        	result = sys.getDriverReportFacade().getPointHistory(reportForm.getFirstname(),reportForm.getLastname(),reportForm.getStatus(),divOrgs,divGeos,first,count,operation,sys.getUserSessionData());
        }
        reportForm.setLastUpdate(Calendar.getInstance().getTime());
        HistoryPointQueryData hrdQuery = new HistoryPointQueryData(result, messages, reportForm.getOutput().equals("XLS"));

        int total = (result.getTotal() == -1) ? 0 : result.getTotal();
        boolean flag = (result.getTotal() == -1);
        int pages = (int)(total / 50);
        if(pages*50 < total) {
            pages++;
        }

        HistoryPointQueryFactory factory = new HistoryPointQueryFactory(messages.getText("query.history.title"),hrdQuery,reportForm,sys,messages,count,pages,page,flag);
        
        JFreeReportBoot.getInstance().start();
        JFreeReport report = factory.createReport();
        return report;
    }
    
} 
