/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.util.Collection;

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
import business.vehicle.VehicleABMFacade;

import communication.administration.user.UserSessionData;

/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssociateTrailerAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();
		
        Collection trailers = Util.stringToCollection(((DynaActionForm)form).getString("checkboxParamList"));
        
        VehicleABMFacade vehicleABM = sys.getVehicleABMFacade();				
		vehicleABM.associateTrailer(new Integer(((DynaActionForm)form).getString("vehicle")),trailers,userSessionData);					
					
        session.setAttribute("id",((DynaActionForm)form).getString("vehicle"));
        session.removeAttribute("AssociateTrailerForm");
		return mapping.findForward("success");
	}
			
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("id",((DynaActionForm)form).getString("vehicle"));
        return mapping.findForward("success");
    }    
	

}
