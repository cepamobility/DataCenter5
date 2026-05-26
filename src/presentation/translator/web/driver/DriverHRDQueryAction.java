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
import presentation.report.driver.DetailedDriverHRDQueryFactory;
import presentation.report.driver.DriverHRDQueryFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.report.DetailedDriverHRDDataType;
import communication.driver.report.DetailedDriverHRDQueryData;
import communication.driver.report.DriverHRDQueryData;
import communication.driver.report.DriverReportDataType;
import communication.language.Text;
import communication.util.ListResult;

/**
 * 
 * @author gbenaderet
 *
 */
public class DriverHRDQueryAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DriverHRDQueryForm reportForm = (DriverHRDQueryForm)form;
        
        /*
        Integer fromMonth = Util.empty((String)reportForm.getFrommonth())?null:
        	new Integer((String)reportForm.getFrommonth());
        Integer toMonth = Util.empty((String)reportForm.getTomonth())?null:
        	new Integer((String)reportForm.getFrommonth());
        Integer fromYear = Util.empty((String)reportForm.getFromyear())?null:
        	new Integer((String)reportForm.getFromyear());
        Integer toYear = Util.empty((String)reportForm.getToyear())?null:
        	new Integer((String)reportForm.getToyear());
        Text messages = sys.getText();
        */
        
        // Periodo de 2 años maximo
        /* Se saco esta restriccion por pedido de Emmanuel 
        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
        		!Util.dateRange(fromYear.toString(),toYear.toString(),
        				fromMonth.toString(),toMonth.toString(),2)){
        	session.setAttribute("Msg",messages.getText("report.event.range2"));
            return mapping.findForward("back");
        }
        */
        
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"HRD",true);
        return fwd;
        
    }
    
    
    public JFreeReport getReport(SystemAccess sys,DriverHRDQueryForm reportForm,int page) throws Exception {
    	Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
	  	Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        
        Integer fromMonth = Util.empty((String)reportForm.getFrommonth())?null:
        	new Integer((String)reportForm.getFrommonth());
        Integer toMonth = Util.empty((String)reportForm.getTomonth())?null:
        	new Integer((String)reportForm.getFrommonth());
        Integer fromYear = Util.empty((String)reportForm.getFromyear())?null:
        	new Integer((String)reportForm.getFromyear());
        Integer toYear = Util.empty((String)reportForm.getToyear())?null:
        	new Integer((String)reportForm.getToyear());
        
        JFreeReport report = null;
        Text messages = sys.getText();
        int first = (reportForm.getOutput().equals("HTML")) ? (page-1)*50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;
   
        if (reportForm.getReportType().equals("Resumido")) 
        {
	        
	        ListResult result = sys.getDriverReportFacade().getHRDDrivers(
	        		fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,reportForm.getFirstname(),
	        		reportForm.getLastname(),reportForm.getStatus(),first,count,sys.getUserSessionData());
	        
	        /* Setea la última actualizacion */
	        if (result.getElements().size() > 0){
	        	Iterator it = result.getElements().iterator();
	        	DriverReportDataType drdt = (DriverReportDataType)it.next();
	        	reportForm.setLastUpdate(drdt.getLastUpdate());
	        }else{
	        	reportForm.setLastUpdate(Calendar.getInstance().getTime());
	        }
	        /* Fin ultima actualizacion */
	        
	        DriverHRDQueryData hrdQuery = new DriverHRDQueryData(sys,result.getElements(),
	        		result.getTotal(), sys.getUserSessionData().getFilter().getPrincipalDivOrgs());
	        hrdQuery.setMessages(messages);
	        
	        int total = (result.getTotal() == -1)?0:result.getTotal();
	        boolean flag = (result.getTotal() == -1)?true:false;
	        int pages = (int)(total / 50);
	        if(pages*50 < total) {
	            pages++;
	        }
	    
	        DriverHRDQueryFactory factory = new DriverHRDQueryFactory(messages.getText(
	        		"query.hrd.title"),hrdQuery,reportForm,sys,messages,count,pages,page);
	        factory.setFlag(flag);
	        report = factory.createReport();
        }else{
        	// Detallado
        	ListResult result = sys.getDriverReportFacade().getDetailedHRDDrivers(
   	        		fromMonth,fromYear,toMonth,toYear,divOrgs,divGeos,reportForm.getFirstname(),
   	        		reportForm.getLastname(),reportForm.getStatus(),first,count,sys.getUserSessionData());
   	        
   	        /* Setea la última actualizacion */
   	        if (result.getElements().size() > 0){
   	        	Iterator it = result.getElements().iterator();
   	        	DetailedDriverHRDDataType drdt = (DetailedDriverHRDDataType)it.next();
   	        	reportForm.setLastUpdate(drdt.getLastUpdate());
   	        }else{
   	        	reportForm.setLastUpdate(Calendar.getInstance().getTime());
   	        }
   	        /* Fin ultima actualizacion */
   	        
   	        DetailedDriverHRDQueryData hrdQuery = new DetailedDriverHRDQueryData( result.getElements(),
   	        		result.getTotal(), sys.getUserSessionData().getFilter().getPrincipalDivOrgs());
   	        hrdQuery.setMessages(messages);
   	        
   	        int total = (result.getTotal() == -1)?0:result.getTotal();
   	        boolean flag = (result.getTotal() == -1)?true:false;
   	        int pages = (int)(total / 50);
   	        if(pages*50 < total) {
   	            pages++;
   	        }
   	    
   	        DetailedDriverHRDQueryFactory factory = new DetailedDriverHRDQueryFactory(messages.getText(
   	        		"query.hrd.title"),hrdQuery,reportForm,sys,messages,count,pages,page);
   	        factory.setFlag(flag);
   	        report = factory.createReport();
        	
        }
            
        return report;
    }

    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


