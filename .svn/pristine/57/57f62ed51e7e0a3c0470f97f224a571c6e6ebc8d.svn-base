/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.queries;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.queries.ReportNewAPMMByPeriodFactory;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.DivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.RoleData;
import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;
import communication.queries.report.ReportNewAPMMByPeriodData;
import communication.security.SecurityConstants;
import communication.util.Filter;


/**
 * @author adocarmo
 *
 */
public class ReportAPMKByPeriodAction extends AbstractAction {

	// Constants
	private final String PATH = SecurityConstants.IMAGES_WAR_DIRECTORY; // "/tmp/datacenter/images/";
	
    public ActionForward action(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        APMMReport reportForm = (APMMReport)form;
        
        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        if(fromMonth.length() == 0 || toMonth.length() == 0 || fromYear.length() == 0 || toYear.length() == 0) {
            session.setAttribute("Msg",messages.getText("generic.error.emptyperiod"));
            return mapping.findForward("back");
        }
        if((fromMonth.startsWith("Q") && !toMonth.startsWith("Q")) || (!fromMonth.startsWith("Q") && toMonth.startsWith("Q"))) {
            session.setAttribute("Msg",messages.getText("generic.error.invalidperiod"));
            return mapping.findForward("back");
        }
        if(fromMonth.startsWith("Q")) {
            fromMonth = String.valueOf(Integer.parseInt(fromMonth.substring(1)) * 3 - 2);
        }
        if(toMonth.startsWith("Q")) {
            toMonth = String.valueOf(Integer.parseInt(toMonth.substring(1)) * 3);
        }
        
        Integer[][] divOrgs = null;
        Integer[] divGeos = null;

        // Los usuarios de Rol Contratista solo pueden ver divisiones de sus contratistas:
        if (sys.getUserSessionData().getRole().getName().equals(RoleData.CONTRACTOR)){
        	divOrgs = (reportForm instanceof DriverDivOrgForm) ? Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.USER) : Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
        	divGeos = (reportForm instanceof DriverDivOrgForm) ? Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.USER) : Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
        }else{
        	divOrgs = (reportForm instanceof DriverDivOrgForm) ? Util.getDivOrgs(sys,(DriverDivOrgForm)reportForm,Filter.CORPORATION) : Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        	divGeos = (reportForm instanceof DriverDivOrgForm) ? Util.getDivGeos(sys,(DriverDivOrgForm)reportForm,Filter.CORPORATION) : Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        }
        
        int order1 = Integer.parseInt(reportForm.getOrder1());
        int order2 = (order1 == 4) ? 4 : Integer.parseInt(reportForm.getOrder2());
        int order3 = (order2 == 4) ? 4 : Integer.parseInt(reportForm.getOrder3());
        int order4 = (order3 == 4) ? 4 : Integer.parseInt(reportForm.getOrder4());

        if ( fromYear == null ||  toYear == null || fromMonth == null || toMonth == null ||
        		!Util.dateRange(fromYear.toString(),
        		toYear.toString(),fromMonth.toString(),toMonth.toString(),2)){
            	session.setAttribute("Msg",messages.getText("report.event.range2"));
                return mapping.findForward("back");
        }

        JFreeReport report = null;
        
        String period = (reportForm instanceof ReportAPMMByPeriodForm) ? ((ReportAPMMByPeriodForm)reportForm).getType() : ((ReportAPMMByPeriodFormJJ)reportForm).getType(); 
        String dataType = (reportForm instanceof ReportAPMMByPeriodForm) ? ((ReportAPMMByPeriodForm)reportForm).getDataType() : ((ReportAPMMByPeriodFormJJ)reportForm).getDataType(); 
        String fleetType = (reportForm instanceof ReportAPMMByPeriodForm) ? ((ReportAPMMByPeriodForm)reportForm).getFleetTypes() : ((ReportAPMMByPeriodFormJJ)reportForm).getFleetTypes(); 
        if(period.equals("period")) {
           ReportNewAPMMByPeriodData result = sys.getFleetReportFacade().getNewReportAPMMByPeriod(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,Integer.parseInt(dataType),order1,order2,order3,order4,messages,Integer.parseInt(fleetType),sys.getUserSessionData());
            ReportNewAPMMByPeriodFactory factory = new ReportNewAPMMByPeriodFactory("APMM",result,(ActionForm) reportForm,sys,messages,new Integer(fromMonth),new Integer(fromYear),new Integer(toMonth),new Integer(toYear),false,Integer.parseInt(dataType),order1,order2,order3,order4);
            report = factory.createReport();
        }else {
            ReportNewAPMMByPeriodData result = sys.getFleetReportFacade().getAcumulatedReportAPMMByPeriod(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,Integer.parseInt(dataType),order1,order2,order3,order4,messages,Integer.parseInt(fleetType),sys.getUserSessionData());
            ReportNewAPMMByPeriodFactory factory = new ReportNewAPMMByPeriodFactory("APMM",result,(ActionForm) reportForm,sys,messages,new Integer(fromMonth),new Integer(fromYear),new Integer(toMonth),new Integer(toYear),true,Integer.parseInt(dataType),order1,order2,order3,order4);
            report = factory.createReport();
        }

        ArrayList arrayGraficas = new ArrayList();

        JFreeReportBoot.getInstance().start();
        ActionForward fwd = new ReportUtil().executeReport(mapping,response,report,reportForm.getOutput(),session,"apmm",arrayGraficas,true);
        session.removeAttribute("ReportAPMMByPeriodForm");
        session.setAttribute("Bookmark",reportForm.getBookmark(BookmarkData.APMM_BYPERIOD_REPORT));
        return fwd;
        
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public JFreeReport getReport(SystemAccess sys,ReportAPMMByPeriodFormJJ reportForm) throws Exception {
        Text messages = sys.getText();
        String fromMonth = reportForm.getFrommonth();
        String toMonth = reportForm.getTomonth();
        String fromYear = reportForm.getFromyear();
        String toYear = reportForm.getToyear();
        if(fromMonth.startsWith("Q")) {
            fromMonth = String.valueOf(Integer.parseInt(fromMonth.substring(1)) * 3 - 2);
        }
        if(toMonth.startsWith("Q")) {
            toMonth = String.valueOf(Integer.parseInt(toMonth.substring(1)) * 3);
        }
        
        Integer[][] divOrgs = null;
        Integer[] divGeos = null;

        // Los usuarios de Rol Contratista solo pueden ver divisiones de sus contratistas:
        if (sys.getUserSessionData().getRole().getName().equals(RoleData.CONTRACTOR)){
        	divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.USER);
        	divGeos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.USER);
        }else{
        	divOrgs =  Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        	divGeos =  Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        }
        //Integer[][] divOrgs = Util.getDivOrgs(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        //Integer[] divGeos = Util.getDivGeos(sys,(DivOrgForm)reportForm,Filter.CORPORATION);
        
        int order1 = Integer.parseInt(reportForm.getOrder1());
        int order2 = (order1 == 4) ? 4 : Integer.parseInt(reportForm.getOrder2());
        int order3 = (order2 == 4) ? 4 : Integer.parseInt(reportForm.getOrder3());
        int order4 = (order3 == 4) ? 4 : Integer.parseInt(reportForm.getOrder4());


        JFreeReport report = null;
        
        String period = reportForm.getType(); 
        String dataType = reportForm.getDataType(); 
        if(period.equals("period")) {
           ReportNewAPMMByPeriodData result = sys.getFleetReportFacade().getNewReportAPMMByPeriod(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,Integer.parseInt(dataType),order1,order2,order3,order4,messages,Integer.parseInt(reportForm.getFleetTypes()),sys.getUserSessionData());
            ReportNewAPMMByPeriodFactory factory = new ReportNewAPMMByPeriodFactory("APMM",result,(ActionForm) reportForm,sys,messages,new Integer(fromMonth),new Integer(fromYear),new Integer(toMonth),new Integer(toYear),false,Integer.parseInt(dataType),order1,order2,order3,order4);
            report = factory.createReport();
        }else {
            ReportNewAPMMByPeriodData result = sys.getFleetReportFacade().getAcumulatedReportAPMMByPeriod(fromMonth,new Integer(fromYear),toMonth,new Integer(toYear),divOrgs,divGeos,Integer.parseInt(dataType),order1,order2,order3,order4,messages,Integer.parseInt(reportForm.getFleetTypes()),sys.getUserSessionData());
            ReportNewAPMMByPeriodFactory factory = new ReportNewAPMMByPeriodFactory("APMM",result,(ActionForm) reportForm,sys,messages,new Integer(fromMonth),new Integer(fromYear),new Integer(toMonth),new Integer(toYear),true,Integer.parseInt(dataType),order1,order2,order3,order4);
            report = factory.createReport();
        }
        return report;
    }
} 
    


