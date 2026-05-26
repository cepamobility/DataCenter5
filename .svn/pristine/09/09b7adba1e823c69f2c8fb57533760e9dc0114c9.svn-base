package presentation.translator.web.travel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class TravelCreateAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        TravelForm createData = (TravelForm) form;
        String message = createData.validate();
        if (message != null) {
            session.setAttribute("Msg", messages.getText(message));
            String link = "travel/TravelCreate.jsp?type=create&id="+createData.getDriverid();
            link += (Util.empty(createData.getVehicleId())) ? "&vehicle=-1" : "&vehicle="+createData.getVehicleId();
            sys.getUserSessionData().setUrl(link);
            return mapping.findForward("success");
        }
            
        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.travelCreate(createData.getTravelData(sys),sys.getUserSessionData());

        session.removeAttribute("TravelCloseForm");
        sys.getUserSessionData().setUrl("success.jsp?entity=travel.create");
        return mapping.findForward("success");
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("success");
    }
}
