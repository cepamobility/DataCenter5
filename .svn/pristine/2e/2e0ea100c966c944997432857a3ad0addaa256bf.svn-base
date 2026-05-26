package presentation.translator.web.driver;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverABMFacade;

import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.language.Text;


public class TemporalDriverAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        String url = null;
        String msg = null;
        Text messages = sys.getText();

        DynaActionForm driverForm = (DynaActionForm) form;
        
        // 	target: De donde viene: TemporalDriverView o TemporalDriverList
        String target = (String) driverForm.get("target"); 
        String action = (String) driverForm.get("action");
        String idDriver = (String) driverForm.get("idDriver");
        String idTemporalDriver = (String) driverForm.get("idTemporalDriver");
        String idActivity = (String) driverForm.get("idActivity");
        

        
        if (action.equalsIgnoreCase("accept")){
        	/* Acepta el conductor temporal como conductor definitivo.
        	 * 
        	 * 1. Le saco la flag de temporal
        	 * 2. Genero un msg de "Condcutor temporal aceptado como definitivo"
        	 * 3. Vuelvo a la actividad        	 * 
        	 */

        	DriverData temporalDriverData  =	sys.getDriverReportFacade().findDriverByPrimaryKey(
        			new Integer(idTemporalDriver.trim()), userSessionData);
        	        	
        	if (temporalDriverData != null){
        		temporalDriverData.setTemporal(false);
        		sys.getDriverABMFacade().driverUpdate(temporalDriverData, 
        				"Temporal Driver accepted.", userSessionData);
        		
        		url = "/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity;
                msg = messages.getText("driver.temporal.accepted");
                userSessionData.setUrl(url);
                userSessionData.setMsg(msg);
                return mapping.findForward("layout");
        	}        	
        }
        
        if (action.equalsIgnoreCase("link")) {
        	/* Vinculo idTemporalDriver con idDriver:
        	 
        	 Alerta mostrando msg:
        	 "Si usted vincula estos usuarios se borrará el conductor temporal
        	 Esta seguro (SI/NO) ? "
        	 1. Vincular este conductor a la actividad: 
        	 		update activityregistry set driver = VIEJO where driver = TEMPORAL;
        	 2. Borrar el conductor temporal
        	 		delete from drivers wher driver= TEMPORAL;
        	 3. Generar un msg de "conductor temporal vinculado a conductor existente."
        	 4.  Redirigir a la pagina de la actividad.
        	 
        	*/
        	sys.getDriverABMFacade().unificateDrivers(new Integer(idTemporalDriver.trim()), new Integer(idDriver.trim()), true, "Temporal Driver "+idTemporalDriver+" linked with existing driver: "+idDriver, userSessionData);
    		url = "/activity/schedule/CEPAActivityScheduleView.jsp?idActivity="+idActivity;
            msg = messages.getText("driver.temporal.linked");
            userSessionData.setUrl(url);
            userSessionData.setMsg(msg);
            return mapping.findForward("layout");
                
        }
            
        url = userSessionData.getUrl();
        msg = "";
        userSessionData.setUrl(url);
        userSessionData.setMsg(msg);
        return mapping.findForward("layout");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	
    	String url = null;
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        
        if (userSessionData.getUrl() == null){
        	url = "/driver/DriverList.jsp";
        	userSessionData.setUrl(url);
        }       
        
        return mapping.findForward("DriverList");
    }
}
