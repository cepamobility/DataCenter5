/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.queries;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportMileageFactory;
import presentation.report.queries.ReportMileageTotalFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.exception.InvalidDataException;
import communication.language.Text;
import communication.queries.report.ReportMileageData;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportMileageAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ReportMileageForm reportForm = (ReportMileageForm)form;
        
        // Restriccion de 2 años  y de campos nulos para las fechas:
        Integer fromYear = (Util.empty(reportForm.getFromyear()) || reportForm.getFromyear().equals("0")) ? null : new Integer(reportForm.getFromyear());
        Integer toYear = (Util.empty(reportForm.getToyear()) || reportForm.getToyear().equals("0")) ? null : new Integer(reportForm.getToyear());
        Integer fromMonth = (Util.empty(reportForm.getFrommonth()) || reportForm.getFrommonth().equals("0")) ? null : new Integer(reportForm.getFrommonth());
        Integer toMonth = (Util.empty(reportForm.getTomonth()) || reportForm.getTomonth().equals("0")) ? null : new Integer(reportForm.getTomonth());
        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
            		!Util.dateRange(fromYear.toString(),toYear.toString(),
            				fromMonth.toString(),toMonth.toString(),2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
        }
        
        JFreeReport report = null;
        try {
        	int monthCount = (12 - Integer.parseInt(fromMonth.toString()) + 1) + ((Integer.parseInt(toYear.toString()) - Integer.parseInt(fromYear.toString()) - 1) * 12) + Integer.parseInt(toMonth.toString());
            
            String[] fleets = sys.getFleetReportFacade().getFleetDescriptions(reportForm.getFleet(),sys.getUserSessionData());
            if(reportForm.getType().equals("period")) {
                ReportMileageData result = sys.getFleetReportFacade().getReportMileage(reportForm.getFrommonth(),new Integer(reportForm.getFromyear()),reportForm.getTomonth(),new Integer(reportForm.getToyear()),reportForm.getFleet(),sys.getUserSessionData(),monthCount);
                result.setMessages(messages);
                ReportMileageFactory factory = new ReportMileageFactory(messages.getText("query.mileage.title"),result,form,sys,messages,fleets,monthCount);
                report = factory.createReport();
            }else {
                ReportMileageData result = sys.getFleetReportFacade().getReportMileageTotal(reportForm.getFrommonth(),new Integer(reportForm.getFromyear()),reportForm.getTomonth(),new Integer(reportForm.getToyear()),reportForm.getFleet(),sys.getUserSessionData(),monthCount);
//              ReportMileageData result = sys.getFleetReportFacade().getReportMileage(reportForm.getFrommonth(),new Integer(reportForm.getFromyear()),reportForm.getTomonth(),new Integer(reportForm.getToyear()),reportForm.getFleet(),sys.getUserSessionData());
                result.setMessages(messages);
                ReportMileageTotalFactory factory = new ReportMileageTotalFactory(messages.getText("query.mileage.title"),result,form,sys,messages,fleets,monthCount);
                report = factory.createReport();
            }
        }catch(Exception e) {
        	e.printStackTrace();
            Throwable thw = (e instanceof ServletException) ? ((ServletException)e).getRootCause() : e;
            while (thw.getCause() != null) {
                thw = thw.getCause();
            }
            if(thw instanceof InvalidDataException && ((InvalidDataException)thw).getSystemMessage().equals("invalid_month")) {
                return mapping.findForward("invalid_month");
            }
        }

        session.setAttribute("Bookmark",reportForm.getBookmark());
        JFreeReportBoot.getInstance().start();

        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"miles_combined",true);
        return fwd;
        
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys, ReportMileageForm reportForm) throws Exception {
        Text messages = sys.getText();

        Integer fromYear = (Util.empty(reportForm.getFromyear()) || reportForm.getFromyear().equals("0")) ? null : new Integer(reportForm.getFromyear());
        Integer toYear = (Util.empty(reportForm.getToyear()) || reportForm.getToyear().equals("0")) ? null : new Integer(reportForm.getToyear());
        Integer fromMonth = (Util.empty(reportForm.getFrommonth()) || reportForm.getFrommonth().equals("0")) ? null : new Integer(reportForm.getFrommonth());
        Integer toMonth = (Util.empty(reportForm.getTomonth()) || reportForm.getTomonth().equals("0")) ? null : new Integer(reportForm.getTomonth());
        
        int monthCount = (12 - Integer.parseInt(fromMonth.toString()) + 1) + ((Integer.parseInt(toYear.toString()) - Integer.parseInt(fromYear.toString()) - 1) * 12) + Integer.parseInt(toMonth.toString());
        
        String[] fleets = sys.getFleetReportFacade().getFleetDescriptions(reportForm.getFleet(),sys.getUserSessionData());
        if(reportForm.getType().equals("period")) {
            ReportMileageData result = sys.getFleetReportFacade().getReportMileage(reportForm.getFrommonth(),new Integer(reportForm.getFromyear()),reportForm.getTomonth(),new Integer(reportForm.getToyear()),reportForm.getFleet(),sys.getUserSessionData(),monthCount);
            result.setMessages(messages);
            ReportMileageFactory factory = new ReportMileageFactory(messages.getText("query.mileage.title"),result,reportForm,sys,messages,fleets,monthCount);
            return factory.createReport();
        }else {
            ReportMileageData result = sys.getFleetReportFacade().getReportMileageTotal(reportForm.getFrommonth(),new Integer(reportForm.getFromyear()),reportForm.getTomonth(),new Integer(reportForm.getToyear()),reportForm.getFleet(),sys.getUserSessionData(),monthCount);
            result.setMessages(messages);
            ReportMileageTotalFactory factory = new ReportMileageTotalFactory(messages.getText("query.mileage.title"),result,reportForm,sys,messages,fleets,monthCount);
            return factory.createReport();
        }
    }
} 
