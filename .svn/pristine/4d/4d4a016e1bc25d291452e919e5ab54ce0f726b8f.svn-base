package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import persistence.util.Util;
import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.activity.ActivityReportFactory;
import presentation.report.activity.ActivityReportTabletFactory;
import presentation.report.activity.EBtwReportFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityABMFacade;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportData;
import communication.activity.report.ActivityReportTabletData;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.MailSender;

public class ActivityDivideAction extends AbstractAction { 

	// This action is executed from all services that involve updating of 
	// vehicles (edit vehicle, change driver assigned, assign a driver to vehicle, and 
	// change to institutional
	
	public ActionForward action(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
	
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        UserSessionData userSessionData = sys.getUserSessionData();		
			
		ActivityForm updateData = (ActivityForm) form;
		Integer activityId = new Integer(updateData.getId());
		
		Collection list = sys.getActivityABMFacade().separateActivities(activityId, userSessionData);
		
		// ojo, cuando hago forward a la pag ActivityView el id del vehiculo
		// se guarda en un atributo llamado id				
		// request.setAttribute("id",updateData.getId());
		userSessionData.setValue(list);
		userSessionData.setUrl("divideSuccess.jsp");
		return mapping.findForward("layout");
	}


	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		// ojo, cuando hago forward a la pag VehicleView el id del vehiculo se guarda en un atributo llamado id        	
         // session.setAttribute("id", ((ActivityForm) form).getId());
    	
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         UserSessionData userSessionData = sys.getUserSessionData();
         userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+((ActivityForm) form).getId());
         return mapping.findForward("layout");
    }            
}
