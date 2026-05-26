/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

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
import presentation.report.vehicle.TrailerWithoutCheckListFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.vehicle.report.TrailerWithoutCheckListData;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TrailerWOCheckListAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	Date  ho1= new Date();
    	
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        TrailersWOCheckListForm reportForm = (TrailersWOCheckListForm)form;
        
        // Restriccion de 2 años  y de campos nulos para las fechas:
        Integer fromYear = (Util.empty(reportForm.getFromyear()) || reportForm.getFromyear().equals("0")) ? null : new Integer(reportForm.getFromyear());
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
                
   		String message = reportForm.validate(sys.getUserSessionData());
   		
		if(message != null) {				
		    //Some attribute data is invalid	
			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
			session.setAttribute("Msg", messages.getText(message));
			return mapping.findForward("back");    			
		}   		
        
		     
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1,session);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"TrailersWithoutCL",true);
        
        return fwd;
        
    }
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    public JFreeReport getReport(SystemAccess sys,TrailersWOCheckListForm reportForm,int page,HttpSession session) throws Exception {
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);        

        JFreeReport report = null;
        Text messages = sys.getText();
        
        Date initialDate = reportForm.getInitialDate();
        Date lastDate = reportForm.getLastDate();       
        
        TrailerWithoutCheckListData result = sys.getVehicleReportFacade().getTrailerWithoutCheckList(initialDate,lastDate,Integer.parseInt(reportForm.getStatus()),divOrgs,divGeos[0],sys.getText(),sys.getUserSessionData());        
        //messages.getText("query.trailer.wochl.title"),
        TrailerWithoutCheckListFactory factory = new TrailerWithoutCheckListFactory("trailers",result,reportForm,sys,messages);        
        
        report = factory.createReport();

        return report;
    }    
}