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

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.ComplaintData;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VehicleComplaintEditAction extends AbstractAction {
	
	/**
	 * Log this class
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
			return mapping.findForward("create");		
		}

		ComplaintData complaint = createData.getUpdateData();
		sys.getVehicleABMFacade().updateComplaint(complaint, userSessionData);					
				
		userSessionData.setUrl("vehicle/VehicleView.jsp?id="+createData.getVehicle());
		session.removeAttribute("VehicleComplaintForm");		
					
		return mapping.findForward("success");
	}
			
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		VehicleComplaintForm createData = (VehicleComplaintForm)form;
		((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().setUrl("vehicle/VehicleView.jsp?id="+createData.getVehicle());
		session.removeAttribute("VehicleComplaintForm");		
		
        return mapping.findForward("success");
    }    
	

}
