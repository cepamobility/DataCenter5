/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.activity.report.GeneratorPDF;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.tables.ActivityPrincipalAttributes;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.EmptyDataSource;

/**
 * @author fcarriquiry
 *
 */
public class ActivityCarnetAction extends AbstractAction {

    private static final String type = null;


	public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData(); 
        Text messages = sys.getText();

    	Integer driverId = new Integer(((DynaActionForm)form).getString("driver"));
    	Integer activityId = new Integer(((DynaActionForm)form).getString("activity"));
    		
    	CEPAActivityScheduleRegistryData completeResult = sys.getActivityReportFacade().getCompleteResult(activityId, driverId, sys.getUserSessionData());
    	if(completeResult.getActivity().getDefinition().isHaveNote() && completeResult.getNote().equals(CEPAActivityConstants.NOTAPRUVED)) {
			userSessionData.setMsg(messages.getText("activity.driver.notapruved"));
			userSessionData.setUrl("/activity/schedule/CEPAActivityScheduleView.jsp?id="+activityId);
			return mapping.findForward("next");

    	} else {
	
	        response.setHeader("Content-Type", "application/pdf");
	   	 	response.setHeader("Content-Disposition", "inline; filename=\"Carnet.pdf\"");
	        OutputStream out = response.getOutputStream();
	    	GeneratorPDF.generateReport(out, completeResult, messages);
    	}
        return null;
	}		
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
} 
    


