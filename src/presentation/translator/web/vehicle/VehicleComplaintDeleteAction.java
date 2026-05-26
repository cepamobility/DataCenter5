/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.language.Text;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class VehicleComplaintDeleteAction extends AbstractAction {
	
	/**
	 * Log this class
	 */
		
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm createData = (DynaActionForm)form;
					
		Collection list = new LinkedList();
		sys.getVehicleABMFacade().deleteComplaint(new Integer(createData.getString("id")), userSessionData);					
				
		userSessionData.setUrl("vehicle/VehicleView.jsp?id="+createData.getString("vehicle"));
		session.removeAttribute("VehicleDeleteComplaintForm");		
					
		return mapping.findForward("success");
	}
			
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		DynaActionForm createData = (DynaActionForm)form;
		((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().setUrl("vehicle/VehicleView.jsp?id="+createData.getString("vehicle"));
		session.removeAttribute("VehicleDeleteComplaintForm");		
		
        return mapping.findForward("success");
    }    
	

}
