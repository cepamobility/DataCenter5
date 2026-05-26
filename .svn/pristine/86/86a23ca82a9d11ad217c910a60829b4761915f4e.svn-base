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
import presentation.report.event.DetailedEventReportFactory;
import presentation.report.event.EventReportFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.event.report.DetailedEventReportData;
import communication.event.report.EventReportData;
import communication.language.Text;


public class EventReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        EventReportForm reportForm = (EventReportForm)form;

        boolean accidents = !Util.empty(reportForm.getAccidents()) && reportForm.getAccidents().toLowerCase().equals("on");
        boolean incidents = !Util.empty(reportForm.getIncidents()) && reportForm.getIncidents().toLowerCase().equals("on");
        boolean fines = !Util.empty(reportForm.getFines()) && reportForm.getFines().toLowerCase().equals("on");
        boolean nearmiss = !Util.empty(reportForm.getNearmiss()) && reportForm.getNearmiss().toLowerCase().equals("on");
        if (!accidents&&!incidents&&!fines){
        	if(!sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.WEYERHAEUSER}) || !nearmiss) {
        		session.setAttribute("Msg",messages.getText("report.event.typeerror"));
        		return mapping.findForward("back");
        	}
        }
        /*if (reportForm.getYear1().equals("0") || reportForm.getYear2().equals("0") ||
        	reportForm.getMonth1().equals("0") || reportForm.getMonth2().equals("0") ||
        		!Util.dateRange(reportForm.getYear1(),reportForm.getYear2(),
        				reportForm.getMonth1(),reportForm.getMonth2(),2)){
        	session.setAttribute("Msg",messages.getText("report.event.range2"));
            return mapping.findForward("back");
        }*/
        
        JFreeReportBoot jfree = JFreeReportBoot.getInstance();
        try{
        	jfree.start();
        }catch (Throwable e){
        	jfree.start(); // tiro de nuevo el reporte porque la primera vez salta error de trutypefonts en jfree report.
        }
        JFreeReport report = getReport(sys,reportForm,session,1);
        session.setAttribute("Bookmark",reportForm.getBookmark(BookmarkData.EVENT_REPORT));
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"event",true);
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,EventReportForm reportForm,HttpSession session,int page) throws Exception {
  
    	Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        boolean accidents = !Util.empty(reportForm.getAccidents()) && reportForm.getAccidents().toLowerCase().equals("on");
        boolean incidents = !Util.empty(reportForm.getIncidents()) && reportForm.getIncidents().toLowerCase().equals("on");
        boolean fines = !Util.empty(reportForm.getFines()) && reportForm.getFines().toLowerCase().equals("on");
        boolean nearmiss = !Util.empty(reportForm.getNearmiss()) && reportForm.getNearmiss().toLowerCase().equals("on");
        
        String working = "";
        if(Util.isChecked(reportForm.getWorkingYes())) {
        	working += "'generic.messages.yes'";
        }
        if(Util.isChecked(reportForm.getWorkingNo())) {
        	if(!Util.empty(working)) {
        		working += ",";
        	}
        	working += "'generic.messages.no'";
        }
        if(Util.isChecked(reportForm.getWorkingNoData())) {
        	if(!Util.empty(working)) {
        		working += ",";
        	}
        	working += "'generic.messages.nodata'";
        }
       
        //String record = reportForm.getRecord();
        //por ahora
        String record ="all";
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        
        
        if(reportForm.getType().equals("resumed")){
        	EventReportData result = sys.getEventReportFacade().getReportEvents(accidents,incidents,fines,nearmiss,new Integer(reportForm.getMonth1()),new Integer(reportForm.getYear1()),new Integer(reportForm.getMonth2()),new Integer(reportForm.getYear2()),divOrgs,divGeos,working,first,count,sys.getUserSessionData(),record,reportForm.getDriverStatus());
        	int pages = 1;
        	if (result != null){
        		result.setMessages(messages);
        		pages = (int)(result.getTotal() / 50);
        		if(pages*50 < result.getTotal()) {
        			pages++;
        		}
        	}
        	EventReportFactory factory = new EventReportFactory("Events",result,reportForm,sys,messages,pages, page);
        	report = factory.createReport();
        }else{
        	DetailedEventReportData result = sys.getEventReportFacade().
        		getDetailedReportEvents(accidents,incidents,fines,nearmiss,new Integer(reportForm.getMonth1()),
        		new Integer(reportForm.getYear1()),new Integer(reportForm.getMonth2()),
        		new Integer(reportForm.getYear2()),divOrgs,divGeos,working,first,count,sys.getUserSessionData(),
        		record,reportForm.getDriverStatus());
        	int pages = 1;
        	if (result != null){
        		result.setMessages(messages);
        		pages = (int)(result.getTotal() / 50);
        		if(pages*50 < result.getTotal()) {
        			pages++;
        		}
        	}
            
        	DetailedEventReportFactory factory = new DetailedEventReportFactory("Events",result,reportForm,sys,messages,pages, page);
            report = factory.createReport();

        }
        
        return report;
        
    }
         
}
    


