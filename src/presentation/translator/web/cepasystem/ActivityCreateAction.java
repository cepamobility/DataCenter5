package presentation.translator.web.cepasystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.cepasystem.tables.ActivityData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ActivityCreateAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ActivityForm createData = (ActivityForm)form;

		String message = createData.validate(sys.getUserSessionData());
		if(message != null) {
		    session.setAttribute("Msg", messages.getText(message));
		    return mapping.findForward("error"+createData.getType());
		}else {
		    ActivityData activity = createData.getActivityData(sys);
            sys.getActivityABMFacade().createCepaSystemActivity(activity, sys.getUserSessionData());
            session.removeAttribute("CENAPRAActivityCreateForm");
			return mapping.findForward("success");
        }	
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
