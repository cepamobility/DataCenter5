/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.vehicle.VehicleABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleMaintenanceData;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VehicleMaintenanceEditAction extends AbstractAction {
	
	/**
	 * Log this class
	 */
		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		UserSessionData userSessionData = sys.getUserSessionData();
		
		VehicleMaintenanceForm createData = (VehicleMaintenanceForm)form;
					
		String message = createData.validate(); 

		if(message != null) {				
			session.setAttribute("Msg", messages.getText(message));
			return mapping.findForward("create");		
		}

		VehicleMaintenanceData maintenance = createData.getUpdateData();
		sys.getVehicleABMFacade().updateVehicleMaintenance(maintenance, userSessionData);					
				
		userSessionData.setUrl("vehicle/VehicleView.jsp?id="+createData.getVehicle());
		session.removeAttribute("VehicleMaintenanceForm");		
					
		return mapping.findForward("success");
	}
			
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		VehicleMaintenanceForm createData = (VehicleMaintenanceForm)form;
		((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().setUrl("vehicle/VehicleView.jsp?id="+createData.getVehicle());
		session.removeAttribute("VehicleMaintenanceForm");		
		
        return mapping.findForward("success");
    }    
	

}
