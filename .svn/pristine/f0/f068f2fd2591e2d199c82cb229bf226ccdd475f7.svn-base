package presentation.translator.web.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class EventUpdateAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        EventForm createData = (EventForm) form;
        String message = createData.validate(sys.getUserSessionData());
        if (message != null) {
            session.removeAttribute("EventForm");
            session.setAttribute("eventId", new Integer(createData.getId()));
            session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("back");
        }

        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.eventUpdate(createData.getEventDataType(sys),
        		Integer.parseInt(createData.getStatus()),
        		sys.getUserSessionData(),createData.getComment());

        session.removeAttribute("EventForm");
        session.removeAttribute("EventSuperviseForm");
        session.setAttribute("eventId",new Integer(createData.getId()));
        return mapping.findForward("view");
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("eventId",new Integer(((EventForm) form).getId()));
        return mapping.findForward("view");
    }
}
