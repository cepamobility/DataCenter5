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

import communication.administration.user.UserSessionData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class DriverEventCreateAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();

        EventForm createData = (EventForm) form;
        
        String message = createData.validate(userSessionData);
        
        if (message != null) {
            session.setAttribute("EventForm", createData);
            session.setAttribute("Msg", messages.getText(message));            
            return mapping.findForward("back");
        }
        
        EventABMFacade eventABM = sys.getEventABMFacade();
        eventABM.eventCreate(createData.getEventDataType(sys),userSessionData,createData.getComment());

        return mapping.findForward("success");
        
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("vehicles");
    }
}
