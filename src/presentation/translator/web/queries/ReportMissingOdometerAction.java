/*
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.queries;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportMissingOdometerFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.fleet.FleetReportFacade;

import communication.queries.report.MissingOdometerData;
import communication.util.Filter;


public class ReportMissingOdometerAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        ReportMissingOdometerForm reportForm = (ReportMissingOdometerForm)form;
        
        JFreeReport report = getReport(sys, reportForm);
        session.setAttribute("Bookmark",reportForm.getBookmark());

        JFreeReportBoot.getInstance().start();
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,
        		reportForm.getOutput(),session,"MissingOdometer",true);
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
    public JFreeReport getReport(SystemAccess sys, ReportMissingOdometerForm reportForm) throws Exception {

        Integer[][] divOrgs = Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.USER);
        Integer[] divGeos = Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.USER);

        String month = reportForm.getMonth();
        String year = reportForm.getYear();
        
        String plate = reportForm.getPlate();
        int status = Integer.parseInt(reportForm.getStatus());
        
        FleetReportFacade reportFacade = sys.getFleetReportFacade(); 
        String[] fleets = reportFacade.getFleetDescriptions(reportForm.getFleet(),sys.getUserSessionData());
        Collection vehicles = reportFacade.getMissingOdometers(month,year,divOrgs,divGeos,plate,status,reportForm.getFleet(),sys.getUserSessionData());
        
        MissingOdometerData data = new MissingOdometerData(vehicles,sys.getText());
        ReportMissingOdometerFactory factory = new ReportMissingOdometerFactory("MissingOdometer",data,reportForm,sys,fleets);

        return factory.createReport();
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
