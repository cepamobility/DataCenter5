package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.ComplaintData;

public class VehicleComplaintCreateAction extends AbstractAction {
	
	/**
	 * 
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		UserSessionData userSessionData = sys.getUserSessionData();
		
		VehicleComplaintForm createData = (VehicleComplaintForm)form;
					
		String message = createData.validate(); 

		if(message != null) {				
			session.setAttribute("Msg", messages.getText(message));
			userSessionData.setUrl("vehicle/complaint.jsp?id="+createData.getId());
			return mapping.findForward("layout");		
		}

		ComplaintData maintenance = createData.getCreateData();
		sys.getVehicleABMFacade().createComplaint(maintenance, userSessionData);					
				
		userSessionData.setUrl("vehicle/VehicleView.jsp?id="+createData.getId());
		session.removeAttribute("VehicleComplaintForm");		
					
		return mapping.findForward("layout");
	}
			
	/**
	 * 
	 */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		VehicleMaintenanceForm createData = (VehicleMaintenanceForm)form;
		((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().
			setUrl("vehicle/VehicleView.jsp?id="+createData.getId());
		session.removeAttribute("VehicleMaintenanceForm");
        return mapping.findForward("layout");
    }    
	

}
