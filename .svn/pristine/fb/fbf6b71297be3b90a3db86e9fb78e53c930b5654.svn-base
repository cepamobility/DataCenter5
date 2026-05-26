/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.AbstractTableModel;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.InductionActivityReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.driver.tables.DriverData;
import communication.language.Text;

/**
 * @author adocarmo
 *
 */
public class InductionTrainingReportAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String idActivity=request.getParameter("idActivity");
    	String idDriver=request.getParameter("idDriver");
     	String formatOutput = request.getParameter("type");
        session.setAttribute("output",formatOutput);
           	
        JFreeReport report;
        ActivityReportFacade facade = sys.getActivityReportFacade();
        CEPAActivityScheduleData activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity)	,sys.getUserSessionData());
        CEPAActivityScheduleRegistryData completeResult = facade.getCompleteResult(new Integer(idActivity),new Integer(idDriver),sys.getUserSessionData());
        DriverData driver = completeResult.getDriver();

        InductionActivityReportFactory factory = new InductionActivityReportFactory(completeResult.getObs(),activitySchedule,driver,"InductionTraining",new ActivityReportData(),form,sys,messages,formatOutput);

        report = factory.createReport();	
        JFreeReportBoot.getInstance().start();
        session.setAttribute("guide","guide");
        return new ReportUtil().executeReport(mapping,response,report,formatOutput,session,"activity",
        		messages.getText("query.activity.generalreport.reporttitle"),
        		messages.getText("query.activity.generalreport.reportsubtitle"),
        		true);
               
    }


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
} 
    


