package presentation.translator.web.queries;

import java.rmi.RemoteException;
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
import presentation.report.queries.ReportOdometerLogFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.fleet.FleetReportFacade;

import communication.language.Text;
import communication.queries.report.ReportOdometerLogData;


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportOdometerLogAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        ReportOdometerLogForm reportForm = (ReportOdometerLogForm)form;
        
        JFreeReport report = getReport(sys, reportForm);
        session.setAttribute("Bookmark",reportForm.getBookmark());

        JFreeReportBoot jfree = JFreeReportBoot.getInstance();
        try{
        	jfree.start();
        }catch(Throwable e){
        	jfree.start();
        }
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"OdometerLog",true);
        return fwd;
    }

    /**
     * @param form
     * @param session
     * @param sys
     * @param reportForm
     * @return
     * @throws Exception
     * @throws RemoteException
     */
    public JFreeReport getReport(SystemAccess sys, ReportOdometerLogForm reportForm) throws Exception {
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        
        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();

        String plate = reportForm.getPlate();
        String use = reportForm.getUse();
        int status = Integer.parseInt(reportForm.getStatus());
        String type = reportForm.getType();
        
        Date start = Util.getDate(reportForm.getStartDay(),reportForm.getStartMonth(),reportForm.getStartYear());
        Date end = Util.getDate(reportForm.getEndDay(),reportForm.getEndMonth(),reportForm.getEndYear());

        FleetReportFacade reportFacade = sys.getFleetReportFacade(); 
        String[] fleets = (Util.empty(reportForm.getFleet())) ? new String[0] : reportFacade.getFleetDescriptions(reportForm.getFleet(),sys.getUserSessionData());
        Object[][] odometers = reportFacade.getOdometersLog(start,end,fromMonth,fromYear,toMonth,toYear,plate,status,use,type,reportForm.getFleet(),sys.getUserSessionData());
        
        ReportOdometerLogData data = new ReportOdometerLogData(odometers,sys.getText());
        ReportOdometerLogFactory factory = new ReportOdometerLogFactory("OdometerLog",data,reportForm,sys,fleets);

        return factory.createReport();
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
