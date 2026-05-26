/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.AbstractTableModel;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.reporterjj.CommentaryDriveDataSource;
import presentation.report.reporterjj.SelfEvaluationData;
import presentation.report.reporterjj.SelfEvaluationReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityABMFacade;
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityDefinitionData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.DivOrgAttribute;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.MailSender;

/**
 * @author adocarmo
 */
public class ReportRegistryUpdateAction extends AbstractAction {

		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm formData = (DynaActionForm) form;	
		ActivityReportFacade activityReport = sys.getActivityReportFacade();

		String activityType = formData.getString("activity");
		String activity = formData.getString("activityId");

        int checkListId = (activityType.equals(CEPAActivityConstants.REPORTERJJ_SELFEVALUATION)) ? CheckListData.REPORTERJJ_SELF_EVALUATION : CheckListData.COMMENTARY_DRIVE_JJ2;
		CheckListData checkList = activityReport.findCheckList(checkListId, userSessionData);

		Date activityDate = Calendar.getInstance().getTime(); 
		if(activityType.equals(CEPAActivityConstants.REPORTERJJ_COMMENTARYDRIVE)) {
			activityDate = Util.getDate(formData.getString("date"), formData.getString("month"), formData.getString("year"));
			if(activityDate == null) {
				activityDate = Calendar.getInstance().getTime();
			}
		}

        Collection itemValueSet = new LinkedList();
        Collection<PointCommentData> pointObs = new LinkedList<PointCommentData>();
        Iterator points = checkList.getSortPoints();
        while(points.hasNext()) {
        	PointData point = (PointData)points.next();
        	Iterator itemsIt = point.getSortItems();
            while(itemsIt.hasNext()) {
                ItemData item = (ItemData)itemsIt.next();
                String insertedValue = request.getParameter("item-"+String.valueOf(item.getId()));
                itemValueSet.add(new ItemValueData(item,new Integer(insertedValue)));
            }
            String pointObsText = request.getParameter("point-"+String.valueOf(point.getId()));
            if(!Util.empty(pointObsText)) {
            	pointObs.add(new PointCommentData(point,pointObsText));
            }
        }


		CEPAActivityScheduleRegistryData registryData = sys.getActivityReportFacade().getCompleteResult(new Integer(activity), new Integer(formData.getString("driverId")), userSessionData);
 		registryData.setItemValueSet(itemValueSet);
 		registryData.setPointCommentSet(pointObs);
 		registryData.setObs(formData.getString("obs"));
 		sys.getActivityABMFacade().createCEPAScheduleActivityRegistry(registryData,userSessionData,false);

 		userSessionData.setUrl("activity/schedule/CEPAActivityScheduleView.jsp?id="+activity);
		return mapping.findForward("layout");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		DynaActionForm formData = (DynaActionForm) form;	
 		userSessionData.setUrl("activity/schedule/CEPAActivityScheduleView.jsp?id="+formData.getString("activityId"));
       	return mapping.findForward("layout");
    }    
}
