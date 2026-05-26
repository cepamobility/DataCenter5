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

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.queries.ReportDriverUnconformityFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.bookmark.tables.BookmarkData;
import communication.driver.report.ReportDriverUnconformityData;
import communication.language.Text;
import communication.util.Filter;

public class ReportUnconformityAction extends AbstractAction {

	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportUnconformityForm reportForm = (ReportUnconformityForm)form;
        
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
        
      	Integer[][] divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
        Integer[] geos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
        
        ReportFactory factory = null;
    	Object[][] unconformities = sys.getDriverReportFacade().getUncoformities(new Integer(fromMonth),new Integer(fromYear),
        		new Integer(toMonth),new Integer(toYear),new Integer(reportForm.getStatus()),divOrgs,geos,sys.getUserSessionData());
       	ReportDriverUnconformityData data = new ReportDriverUnconformityData(unconformities,messages);          		
       	factory = new ReportDriverUnconformityFactory("Unconformities",data,form,sys,messages);
       
        JFreeReport report = factory.createReport();
    
        // Inicio el JFreeReport:
        JFreeReportBoot.getInstance().start();
        
        session.removeAttribute("ReportDriverUnconformityForm");
        session.setAttribute("Bookmark",reportForm.getBookmark(BookmarkData.UNCONFORMITIES_REPORT));
        // Muestro el reporte:
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"unconformity",true);
        sys.getUserSessionData().setUrl("/driver/reportResult.jsp");
        return fwd;
        
    }
 
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys,ReportUnconformityForm reportForm) throws Exception {
        Text messages = sys.getText();

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
        
        Integer[][] divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
        Integer[] divGeos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
        
        return null;
    }
} 
    


