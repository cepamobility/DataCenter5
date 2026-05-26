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
public class TravelUpdateAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        TravelForm createData = (TravelForm) form;
        String message = createData.validate();
        if (message != null) {
            session.setAttribute("Msg", messages.getText(message));
            sys.getUserSessionData().setUrl("travel/TravelCreate.jsp?type=edit&id="+createData.getId());
            return mapping.findForward("success");
        }

        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.updateTravel(createData.getTravelData(sys), sys.getUserSessionData());

        session.removeAttribute("TravelEditForm");
        sys.getUserSessionData().setUrl("/travel/TravelView.jsp?id="+createData.getId());
        return mapping.findForward("success");
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        TravelForm createData = (TravelForm) form;
        sys.getUserSessionData().setUrl("/travel/TravelView.jsp?id="+createData.getId());
        return mapping.findForward("success");
    }
}
