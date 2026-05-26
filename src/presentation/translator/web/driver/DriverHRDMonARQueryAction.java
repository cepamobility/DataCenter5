package presentation.translator.web.driver;

import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.driver.DetailedDriverHRDMonARQueryFactory;
import presentation.report.driver.DriverHRDMonARQueryFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.driver.report.DetailedDriverHRDDataType;
import communication.driver.report.DetailedDriverHRDQueryData;
import communication.driver.report.DriverHRDMonARQueryData;
import communication.driver.report.DriverReportDataType;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.ListResult;

/**
 * 
 * @author gbenaderet
 *
 */
public class DriverHRDMonARQueryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DriverHRDMonARQueryForm reportForm = (DriverHRDMonARQueryForm)form;
        session.setAttribute("Bookmark",reportForm.getBookmark());
        
        try{
        	JFreeReportBoot.getInstance().start();
        }catch(java.lang.AbstractMethodError e){
        	JFreeReportBoot.getInstance().start();
        }
        
        JFreeReport report = getReport(sys,reportForm,1);
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"DriverHRD",true);
        return fwd;
        
    }
    
    
    public JFreeReport getReport(SystemAccess sys,DriverHRDMonARQueryForm reportForm,int page) throws Exception {
    	Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
	  	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
   
       
        ListResult result = sys.getDriverReportFacade().getHRDMonsantoARDrivers(divOrgs,divGeos,reportForm.getFirstname(),reportForm.getLastname(),reportForm.getStatus(),first,count,reportForm.getReportType(),Boolean.parseBoolean(reportForm.getAvoidable()),sys.getUserSessionData());
        int total = (result.getTotal() == -1) ? 0 : result.getTotal();
        boolean flag = (result.getTotal() == -1);
        int pages = (int)(total / 50);
        if(pages*50 < total) {
            pages++;
        }

        if(reportForm.getReportType().equals(DriverData.RESUMED)) {
             
             /* Setea la última actualizacion */
             if (result.getElements().size() > 0){
             	Iterator it = result.getElements().iterator();
             	DriverReportDataType drdt = (DriverReportDataType)it.next();
             	reportForm.setLastUpdate(drdt.getLastUpdate());
             }else{
             	reportForm.setLastUpdate(Calendar.getInstance().getTime());
             }
             
             /* Fin ultima actualizacion */
             DriverHRDMonARQueryData hrdQuery = new DriverHRDMonARQueryData(result.getElements(), result.getTotal(), sys.getUserSessionData().getFilter().getPrincipalDivOrgs());
             hrdQuery.setMessages(messages);
             
             DriverHRDMonARQueryFactory factory = new DriverHRDMonARQueryFactory(messages.getText("query.hrdmon.title"),hrdQuery,reportForm,sys,messages,count,pages,page);
             factory.setFlag(flag);
             report = factory.createReport();
             
        }else{
        	
             /* Setea la última actualizacion */
             if (result.getElements().size() > 0){
             	Iterator it = result.getElements().iterator();
             	DetailedDriverHRDDataType drdt = (DetailedDriverHRDDataType)it.next();
             	reportForm.setLastUpdate(drdt.getLastUpdate());
             }else{
             	reportForm.setLastUpdate(Calendar.getInstance().getTime());
             }
             /* Fin ultima actualizacion */
             
             DetailedDriverHRDQueryData hrdQuery = new DetailedDriverHRDQueryData(result.getElements(),result.getTotal(), sys.getUserSessionData().getFilter().getPrincipalDivOrgs());
             hrdQuery.setMessages(messages);
             
         
             DetailedDriverHRDMonARQueryFactory factory = new DetailedDriverHRDMonARQueryFactory(messages.getText("query.hrdmonar.title"),hrdQuery,reportForm,sys,messages,count,pages,page);
             factory.setFlag(flag);
             report = factory.createReport();
        }
        
        return report;
    }

    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


