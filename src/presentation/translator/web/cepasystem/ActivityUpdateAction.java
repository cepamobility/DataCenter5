/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.cepasystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ActivityUpdateAction extends AbstractAction {

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
		    communication.cepasystem.tables.ActivityData activity = createData.getActivityData(sys);
            sys.getActivityABMFacade().updateCepaSystemActivity(activity,sys.getUserSessionData());
            session.setAttribute("activity",((ActivityForm)form).getId());
            session.removeAttribute("CepaSystemActivityEditForm");
			return mapping.findForward("success");
        }	
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("activity",((ActivityForm)form).getId());
        return mapping.findForward("success");
    }
}
