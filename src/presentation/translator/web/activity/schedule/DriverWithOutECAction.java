package presentation.translator.web.activity.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.DriversWithOutECFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.activity.report.DriverWithOutECData;
import communication.language.Text;
import communication.util.ListResult;

/**
 * DriverWithOutECAction
 * Lleva a cabo la generacion del reporte de los Conductores
 * que no hicieron la Evaluación en Cabina en Vehiculos Pesados.
 * 
 * @author gbenaderet
 *
 */
public class DriverWithOutECAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DriverWithOutECForm reportForm = (DriverWithOutECForm)form;
        
        // Restriccion de 2 años  y de campos nulos para las fechas:
        /*Integer fromYear = (Util.empty(reportForm.getFromyear()) || reportForm.getFromyear().equals("0")) ? null : new Integer(reportForm.getFromyear());
        Integer toYear = (Util.empty(reportForm.getToyear()) || reportForm.getToyear().equals("0")) ? null : new Integer(reportForm.getToyear());
        Integer fromMonth = (Util.empty(reportForm.getFrommonth()) || reportForm.getFrommonth().equals("0")) ? null : new Integer(reportForm.getFrommonth());
        Integer toMonth = (Util.empty(reportForm.getTomonth()) || reportForm.getTomonth().equals("0")) ? null : new Integer(reportForm.getTomonth());
        Integer toDay = (Util.empty(reportForm.getToday()) || reportForm.getToday().equals("0")) ? null : new Integer(reportForm.getToday());
        Integer fromDay = (Util.empty(reportForm.getFromday()) || reportForm.getFromday().equals("0")) ? null : new Integer(reportForm.getFromday());
        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
        		toDay == null || fromDay == null || !Util.dateRange(fromYear.toString(),
        		toYear.toString(),fromMonth.toString(),toMonth.toString(),2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
        }
        */ 
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report, reportForm.getOutput(),session,"DriversWithoutEC",true);
        return fwd;

        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,DriverWithOutECForm reportForm,int page)
    	throws Exception {
    	
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        

        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
        
       
        int status = reportForm.getStatus();
        
        ListResult result = sys.getActivityReportFacade().
        		driversWithOutEC(sys.getUserSessionData(),null,null,divOrgs,divGeos,
        				status,first,count,reportForm.getFirstname(),reportForm.getLastname());        
        
        DriverWithOutECData query = new DriverWithOutECData(result.getElements(),
        		result.getTotal(), sys.getUserSessionData());
        query.setMessages(messages);
        

        int pages = (int)(result.getTotal() / 50);
        if(pages*50 < result.getTotal()) {
            pages++;
        }     
        
        DriversWithOutECFactory factory = new DriversWithOutECFactory(
        		messages.getText("query.activity.ecquery.titlepesados"),query,
        		reportForm,sys,messages,pages,page);        
        
        
        report = factory.createReport();

        return report;
    }    
}
