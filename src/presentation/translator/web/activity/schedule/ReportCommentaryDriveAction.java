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
import presentation.report.activity.ActivityReportCommentaryDriveFactory;
import presentation.translator.web.driver.DriverAdvancedSearchAction;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.report.ActivityReportCommentaryDriveData;
import communication.language.Text;
import communication.util.ListResult;

/**
 * @author gbenaderet
*/

public class ReportCommentaryDriveAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportCommentaryDriveForm reportForm = (ReportCommentaryDriveForm)form;
    	
        // Valido que si se puso commentary drives, se haya indicado cuantos se muestran:
    	if (reportForm.getWithCommentaryDrive().equalsIgnoreCase("wcd")) {
    	    if (!Util.isChecked(reportForm.getOneCommentaryDrive()) &&
   				!Util.isChecked(reportForm.getTwoCommentaryDrive()) &&
   				!Util.isChecked(reportForm.getMoreThanTwoCommentaryDrive()))	{
    		    //Some attribute data is invalid	
    			//Si en el struts config indico que el form es de sesion la línea de abajo es inneseraria
    			session.setAttribute("Msg", messages.getText("query.activity.commentarydrive.selectamount"));
    			return mapping.findForward("back");		
    		}
    	}
    	
    	 // Restriccion de 2 años  y de campos nulos para las fechas:
        Integer fromYear = (Util.empty(reportForm.getFromyear()) || reportForm.getFromyear().equals("0")) ? null : new Integer(reportForm.getFromyear());
        Integer toYear = (Util.empty(reportForm.getToyear()) || reportForm.getToyear().equals("0")) ? null : new Integer(reportForm.getToyear());
        Integer fromMonth = (Util.empty(reportForm.getFrommonth()) || reportForm.getFrommonth().equals("0")) ? null : new Integer(reportForm.getFrommonth());
        Integer toMonth = (Util.empty(reportForm.getTomonth()) || reportForm.getTomonth().equals("0")) ? null : new Integer(reportForm.getTomonth());
        Integer toDay = (Util.empty(reportForm.getToday()) || reportForm.getToday().equals("0")) ? null : new Integer(reportForm.getToday());
        Integer fromDay = (Util.empty(reportForm.getFromday()) || reportForm.getFromday().equals("0")) ? null : new Integer(reportForm.getFromday());
        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
        		toDay == null || fromDay == null || !Util.dateRange(fromYear.toString(),toYear.toString(),fromMonth.toString(),toMonth.toString(),2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
        }
        
    	// Valido que fecha from sea menor que fecha to:
    	try {
    		Date initialDate = reportForm.getInitialDate();
    		Date lastDate = reportForm.getLastDate();
    		if (initialDate.compareTo(lastDate) > 0) {
    			session.setAttribute("Msg",messages.getText("query.activity.commentarydrive.initialdatelastdate"));
    			return mapping.findForward("back");
    		}
    	}catch( Exception nfe) {
			session.setAttribute("Msg",messages.getText("query.activity.commentarydrive.initialdatelastdate"));
			return mapping.findForward("back");    		
    	}
    	
        JFreeReportBoot.getInstance().start();
        JFreeReport report = getReport(sys,reportForm,1);
        session.setAttribute("Bookmark",reportForm.getBookmark());
        
        String title = (((ReportCommentaryDriveForm)form).getCommentaryDriveKind().equals("datatype.scheduledactivitytype.commentarydriverisk")) ? "query.activity.commentarydriverisk.result" : "query.activity.commentarydrive.result";
        return new ReportUtil().executeReport(mapping,response,report, reportForm.getOutput(),session,"CommentaryDriveQuery",messages.getText(title),"",true);
    }
    	
    	
    public JFreeReport getReport(SystemAccess sys,ReportCommentaryDriveForm reportForm,int pageNumber) throws Exception {
    	
    	String countCD = "0";
    	ActivityReportCommentaryDriveData result;
    	ListResult listResult;
    	int total = 0;
        Integer[][] divOrgs = DriverAdvancedSearchAction.getDivOrgs(sys,reportForm);
        Integer[][] divGeos = DriverAdvancedSearchAction.getDivGeos(sys,reportForm);
        JFreeReport report = null;
        Text messages = sys.getText();
        /* Cantidad de Commentary Drives de los conductores */
        if (reportForm.getWithCommentaryDrive()!= null && reportForm.getWithCommentaryDrive().trim().equals("wcd")) {
        	if (reportForm.getOneCommentaryDrive() != null && reportForm.getOneCommentaryDrive().trim().equals("on"))
        		countCD = countCD + "1"; // 1
        	if (reportForm.getTwoCommentaryDrive() != null && reportForm.getTwoCommentaryDrive().trim().equals("on"))
        		countCD = countCD + "2"; // 2
        	if (reportForm.getMoreThanTwoCommentaryDrive() != null && reportForm.getMoreThanTwoCommentaryDrive().trim().equals("on"))
        		countCD = countCD + "3"; // mas de 2
            if(countCD.equals("0")) {
                countCD = "0123";
            }
        }else {
        	countCD = "0";
        }
        /* Fin Cantidad de Commentary Drives */
        Date initialDate = reportForm.getInitialDate();
        Date lastDate = reportForm.getLastDate();
        
        int first = (reportForm.getOutput().equals("HTML")) ? (pageNumber - 1) * 50 : 0;
        int count = (reportForm.getOutput().equals("HTML")) ? 50 : -1;        
       
        String kind = reportForm.getCommentaryDriveKind();
        String type = (kind.equals("datatype.scheduledactivitytype.commentarydriverisk")) ? "0" : reportForm.getCommentaryDriveType(); 
    	if (countCD.equals("0")) {
    		// Sin commentary drives:
    		listResult = sys.getActivityReportFacade().getReportWithOutCommentaryDrive(sys.getUserSessionData(),initialDate,lastDate,divOrgs,divGeos,first,count,type,reportForm.getDriverState(),reportForm.getVehicleAllowed(),kind);  
    	}else {
    		// Con 1 o mas commentary drives:
    		listResult = sys.getActivityReportFacade().getReportCommentaryDrive(sys.getUserSessionData(),initialDate,lastDate,divOrgs,divGeos,first,count,type,countCD,reportForm.getDriverState(),reportForm.getVehicleAllowed(),kind); 	
    	}
    	
    	result = null;
    	total = 0;
    	
    	if (listResult != null){
    		result = new ActivityReportCommentaryDriveData(listResult.getElements(),listResult.getTotal(),sys.getUserSessionData());
    		total = listResult.getTotal();
    		result.setMessages(messages);
    	}
		
        
        int pages = (int)(total / 50);
        if (pages * 50 < total) {
            pages++;
        }
        ActivityReportCommentaryDriveFactory factory = new ActivityReportCommentaryDriveFactory(
        		result,"CommentaryDrive",result,reportForm,sys
        		,pages,pageNumber,count); 
        report = factory.createReport();
        
        return report;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


