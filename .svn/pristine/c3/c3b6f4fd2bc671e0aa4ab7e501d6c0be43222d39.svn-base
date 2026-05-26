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
public class TravelCheckPointAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        TravelCheckPointForm createData = (TravelCheckPointForm) form;
        String message = createData.validate(messages);
        if (message != null) {
            session.setAttribute("Msg", message);
            sys.getUserSessionData().setUrl("travel/TravelCheckPoints.jsp?id="+createData.getTravel());
            return mapping.findForward("success");
        }

        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.updateCheckPoints(createData.getTravelData(sys), createData.getCheckPoints(), sys.getUserSessionData());

        session.removeAttribute("TravelCheckPointForm");
        sys.getUserSessionData().setUrl("/travel/TravelView.jsp?id="+createData.getTravel());
        return mapping.findForward("success");
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        TravelCheckPointForm createData = (TravelCheckPointForm) form;
        sys.getUserSessionData().setUrl("/travel/TravelView.jsp?id="+createData.getTravel());
        return mapping.findForward("success");
    }
}
