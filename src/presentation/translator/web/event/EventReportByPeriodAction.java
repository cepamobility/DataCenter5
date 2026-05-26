/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.event.EventReportByPeriodFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.bookmark.tables.BookmarkData;
import communication.event.report.EventReportByPeriodData;
import communication.language.Text;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EventReportByPeriodAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        JFreeReport report = null;
        JFreeReportBoot.getInstance().start();
        int order1, order2, order3, order4;
        
        EventReportForm reportForm = (EventReportForm)form;
        
        String fromMonth = reportForm.getMonth1();
        String toMonth = reportForm.getMonth2();
        String fromYear = reportForm.getYear1();
        String toYear = reportForm.getYear2();
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

        order1 = Integer.parseInt(reportForm.getOrder1());
        order2 = 4;
        order3 = 4;
        order4 = 4;
        if (order1 == 4) {
        	order2 = order3 = order4 = 4;
        }else{
        	order2 = Integer.parseInt(reportForm.getOrder2());
        	if (order2 == 4){
        		order3 = order4 = 4;
        	}else{
                if(sys.getUserSessionData().getFilter().getPrincipalCount() > 1) {
                    order3 = Integer.parseInt(reportForm.getOrder3());        		
                    if (order3 == 4 && sys.getUserSessionData().getFilter().getPrincipalCount() > 2){
                    	order4 = 4;
                    }else{
                        order4 = Integer.parseInt(reportForm.getOrder4());;                	
                    }
                }
        	}        	
        }
       
        
        if (!Util.dateRange(fromYear,toYear,fromMonth,toMonth,2)){
        	session.setAttribute("Msg",messages.getText("report.event.range2"));
            return mapping.findForward("back");
        }

        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
    	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
    
        EventReportByPeriodData result = sys.getEventReportFacade().getReportEventsByPeriod(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,sys.getUserSessionData(),order1,order2,order3,order4,messages);
        result.setMessages(messages);
        
    	EventReportByPeriodFactory factory = new EventReportByPeriodFactory("Events", result, reportForm,sys,messages,new Integer(fromMonth),new Integer(fromYear),new Integer(toMonth),new Integer(toYear));
    	
    	report = factory.createReport(); 
        session.removeAttribute("EventReportByPeriodForm");
        session.setAttribute("Bookmark",reportForm.getBookmark(BookmarkData.EVENT_BYPERIOD_REPORT));
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"event",true);
    }


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys, EventReportForm reportForm) throws Exception {
        Text messages = sys.getText();

        String fromMonth = reportForm.getMonth1();
        String toMonth = reportForm.getMonth2();
        String fromYear = reportForm.getYear1();
        String toYear = reportForm.getYear2();

        int order1 = Integer.parseInt(reportForm.getOrder1());
        int order2 = 4;
        int order3 = 4;
        int order4 = 4;
        if (order1 == 4) {
            order2 = order3 = order4 = 4;
        }else{
            order2 = Integer.parseInt(reportForm.getOrder2());
            if (order2 == 4){
                order3 = order4 = 4;
            }else{
                if(sys.getUserSessionData().getFilter().getPrincipalCount() > 1) {
                    order3 = Integer.parseInt(reportForm.getOrder3());              
                    if (order3 == 4 && sys.getUserSessionData().getFilter().getPrincipalCount() > 2){
                        order4 = 4;
                    }else{
                        order4 = Integer.parseInt(reportForm.getOrder4());;                 
                    }
                }
            }           
        }

        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
    
        EventReportByPeriodData result = sys.getEventReportFacade().getReportEventsByPeriod(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,sys.getUserSessionData(),order1,order2,order3,order4,messages);
        result.setMessages(messages);
        
        EventReportByPeriodFactory factory = new EventReportByPeriodFactory("Events", result, reportForm,sys,messages,new Integer(fromMonth),new Integer(fromYear),new Integer(toMonth),new Integer(toYear));
        return factory.createReport(); 
    }
} 
    


