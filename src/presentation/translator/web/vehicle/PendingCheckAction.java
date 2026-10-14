package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import business.SystemAccess;
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleReportFacade;
import communication.activity.tables.ItemValueStrData;
import communication.administration.user.UserSessionData;
import communication.vehicle.tables.VehicleCheckData;
import presentation.translator.web.util.AbstractAction;

public class PendingCheckAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaActionForm formData = (DynaActionForm)form;
		
        response.sendRedirect(
                request.getContextPath() +
                "/checklistUPM.jsp?id=" + formData.getString("id") +
                "&checklist=" + formData.getString("checklist")
            );
        
        return null;
	}
			
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("id",((DynaActionForm)form).getString("vehicle"));
        return mapping.findForward("success");
    }    

}
