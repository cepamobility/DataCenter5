package presentation.translator.web.activity.schedule;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.DriversBTWAssistFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import presentation.translator.web.util.Util;

import communication.activity.report.DriversBTWAssistData;
import communication.language.Text;

public class DriversBTWAssistAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DriversBTWAssistForm reportForm = (DriversBTWAssistForm)form;      

        // Valido tipo de conductor
        if (Util.empty(reportForm.getType())){
        	session.setAttribute("Msg",messages.getText("generic.messages.typenotselected"));
        	return mapping.findForward("back");
        }
        
        // Valido si asisitio al teorico o practico
        if (Util.empty(reportForm.getAssistence())){
        	session.setAttribute("Msg",messages.getText("generic.messages.assistencenotselected"));
        	return mapping.findForward("back");
        }
        
        // Status
        if (Util.empty(reportForm.getStatus())){
        	session.setAttribute("Msg",messages.getText("generic.messages.statusnotselected"));
        	return mapping.findForward("back");
        }  		
   			
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"ActivityAssist",true);
        return fwd;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,DriversBTWAssistForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        

        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        
        String type = (reportForm.getType());
        String assistence = (reportForm.getAssistence());
        
        Date initialDate = reportForm.getInitialDate();
        Date lastDate = reportForm.getLastDate();
        
        boolean notPracticoAsistence = assistence.equalsIgnoreCase("generic.messages.notpracticoasistence")?true:false;
        boolean notTeoricoAsistence = assistence.equalsIgnoreCase("generic.messages.notteoricoasistence")?true:false;
        
        String status = reportForm.getStatus();
        String vehicleAllowed = reportForm.getVehicleAllowed();
        
        DriversBTWAssistData result = sys.getActivityReportFacade().driversBTWAssist(sys.getUserSessionData(),initialDate,lastDate,divOrgs,divGeos,status,first,count,notPracticoAsistence,notTeoricoAsistence,type,vehicleAllowed);     
        
        result.setMessages(messages);
        int pages = (int)(result.getTotal() / 50);
        if(pages*50 < result.getTotal()) {
            pages++;
        }
        
        DriversBTWAssistFactory factory = new DriversBTWAssistFactory("query.driversbtwassist.title",result,reportForm,sys,messages,pages,page);        
        report = factory.createReport();

        return report;
    }    
}