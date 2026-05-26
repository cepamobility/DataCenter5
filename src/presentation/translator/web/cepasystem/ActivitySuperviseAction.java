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
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ActivitySuperviseAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        Integer activity = new Integer(((DynaActionForm)form).getString("activity")); 
        sys.getActivityABMFacade().superviseCepaSystemActivity(activity,((DynaActionForm)form).getString("comment"),sys.getUserSessionData());
        session.setAttribute("activity",String.valueOf(activity));
        session.removeAttribute("CepaSystemActivitySuperviseForm");
        return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("activity",((DynaActionForm)form).getString("activity"));
        return mapping.findForward("success");
    }
}
