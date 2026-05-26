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
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.vehicle.VehicleABMFacade;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.TrailerData;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateTrailerAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		UserSessionData userSessionData = sys.getUserSessionData();
		
		TrailerForm createData = (TrailerForm)form;
								
		String message = createData.validate(userSessionData); 

		if(message != null) {				
		    session.setAttribute("TrailerForm",createData);			
			session.setAttribute("Msg", messages.getText(message));
			session.setAttribute("trailerId",createData.getId());
			return mapping.findForward("back");		
		}
		
		TrailerData trailer = createData.createTrailerData(userSessionData);
        sys.getVehicleABMFacade().updateTrailer(trailer,userSessionData);					
				
		// 	Clean session
		session.removeAttribute("TrailerForm");
        session.setAttribute("trailer",trailer.getId());
        
		return mapping.findForward("success");
	}
			
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }    
	

}
