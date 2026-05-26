package presentation.translator.web.driver;

import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.FOSADetailedDriverReportFactory;
import presentation.report.driver.FOSADriverReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.report.FOSADetailedDriverReportData;
import communication.driver.report.FOSADriverReportData;
import communication.language.Text;


/**
 * @author adocarmo
 */
public class FOSADriverReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        FOSADriverReportForm reportForm = (FOSADriverReportForm)form;

        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        
        session.setAttribute("Bookmark",reportForm.getBookmark());
        return new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"driver",true);
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,FOSADriverReportForm reportForm,int page) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        Hashtable parameters = new Hashtable();
        if(!Util.empty(reportForm.getName())) {
            parameters.put("firstName",reportForm.getName());
        }
        if(!Util.empty(reportForm.getLastname())) {
            parameters.put("lastName",reportForm.getLastname());
        }
        if(!Util.empty(reportForm.getId())) {
            parameters.put("corporationId",reportForm.getId());
        }
        Date birth = reportForm.getBirth();
        if(birth != null) {
            parameters.put("birthDate",birth);
        }
        if(!Util.empty(reportForm.getStatus())) {
            parameters.put("status",reportForm.getStatus());
        }
        if(!Util.empty(reportForm.getAccident())) {
            parameters.put("accidents",reportForm.getAccident());
        }
        if(!Util.empty(reportForm.getIncident())) {
            parameters.put("incidents",reportForm.getIncident());
        }
        if(!Util.empty(reportForm.getFine())) {
            parameters.put("fines",reportForm.getFine());
        }
        if(!Util.empty(reportForm.getAccidentCount())) {
            parameters.put("accidentCount",reportForm.getAccidentCount());
        }
        if(!Util.empty(reportForm.getIncidentCount())) {
            parameters.put("incidentCount",reportForm.getIncidentCount());
        }
        if(!Util.empty(reportForm.getFineCount())) {
            parameters.put("fineCount",reportForm.getFineCount());
        }
        if(!Util.empty(reportForm.getBps())) {
            parameters.put("bps",reportForm.getBps());
        }
        if(!Util.empty(reportForm.getAptitudStatus())) {
            parameters.put("aptitudStatus",reportForm.getAptitudStatus());
        }
        if(!Util.empty(reportForm.getAptitudResult())) {
            parameters.put("aptitudResult",reportForm.getAptitudResult());
        }
        if(!Util.empty(reportForm.getAlcoholStatus())) {
            parameters.put("alcoholStatus",reportForm.getAlcoholStatus());
        }
        if(!Util.empty(reportForm.getAlcoholResult())) {
            parameters.put("alcoholResult",reportForm.getAlcoholResult());
        }
        if(!Util.empty(reportForm.getPostiveAlcohol())) {
            parameters.put("postiveAlcohol",reportForm.getPostiveAlcohol());
        }
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        
        if(reportForm.getType().equals("resumed")) {
            parameters.put("type","simple");
            FOSADriverReportData result = sys.getDriverReportFacade().
            	getFOSAReportDrivers(parameters,divOrgs,divGeos,first,count,sys.getUserSessionData());
            
            int pages = 1;
            if (result != null){
            	result.setMessages(messages);
            	pages = (int)(result.getTotal() / 50);
            	if(pages*50 < result.getTotal()) {
            		pages++;
            	}
            }
            
            FOSADriverReportFactory factory = new FOSADriverReportFactory("Drivers",result,reportForm,sys,messages,1,pages,page);
            report = factory.createReport();
        }else {
            parameters.put("type","detailed");
            FOSADetailedDriverReportData result = sys.getDriverReportFacade().
            		getFOSADetailedReportDrivers(parameters,divOrgs,divGeos,first,count,
            				sys.getUserSessionData());
            
            int pages = 1;
            if (result != null){
            	result.setMessages(messages);
            	pages = (int)(result.getTotal() / 50);
            	if(pages*50 < result.getTotal()) {
                    pages++;
                }
            }            
            
            FOSADetailedDriverReportFactory factory = new FOSADetailedDriverReportFactory("Drivers",result,reportForm,sys,messages,pages,page);
            report = factory.createReport();        
        }
        return report;
    }
} 
    


