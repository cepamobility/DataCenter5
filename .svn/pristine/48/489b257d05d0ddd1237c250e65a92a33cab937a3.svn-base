/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.AbstractTableModel;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.ElementAlignment;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;
import org.jfree.report.elementfactory.TextFieldElementFactory;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.activity.ActivityDataSource;
import presentation.report.activity.ActivityReportFactory;
import presentation.report.activity.ActivityReportTabletFactory;
import presentation.report.activity.EBtwReportFactory;
import presentation.report.reporterjj.CommentaryDriveDataSource;
import presentation.report.reporterjj.SelfEvaluationData;
import presentation.report.reporterjj.SelfEvaluationReportFactory;
import presentation.translator.web.activity.report.ActivityReportBTW;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportData;
import communication.activity.report.ActivityReportTabletData;
import communication.activity.report.ActivityReportTabletDataType;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.security.SecurityConstants;

/**
 * @author adocarmo
 *
 */
public class ActivityReportActionVersion2 extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String idActivity=request.getParameter("activity");
    	String idDriver=request.getParameter("driver");
           	
        ActivityReportFacade facade = sys.getActivityReportFacade();
        CEPAActivityScheduleData activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity)	, false, sys.getUserSessionData());
        CEPAActivityScheduleRegistryData completeResult = facade.getCompleteResult(new Integer(idActivity), new Integer(idDriver),messages,sys.getUserSessionData());        

        ActivityReportTabletDataType dataType = sys.getActivityReportFacade().getReportTabletActivity(new Integer(idActivity), new Integer(idDriver),messages, sys.getUserSessionData());
            
   	    final ServletOutputStream out = response.getOutputStream();
   	    response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Disposition","inline; filename=\"btw.pdf\"");           

        HashMap<String,Integer> media = facade.getMediaActivity(activitySchedule.getType().getResourceBundleId(), new Integer(sys.getCorporationId()), new Integer(activitySchedule.getCountryId()), sys.getUserSessionData());

   	    ActivityReportBTW reportBTW2 = new ActivityReportBTW(completeResult,dataType,sys,media);
   	    System.out.println(reportBTW2);
   	    reportBTW2.executeReport(out, Integer.parseInt(request.getParameter("type")));
        out.flush();
        out.close();
        return null;
    }


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
} 
    


