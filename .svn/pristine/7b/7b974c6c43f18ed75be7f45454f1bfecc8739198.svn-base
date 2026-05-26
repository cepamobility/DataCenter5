/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.user;

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
import business.administration.user.UserABMFacade;

/**
 * @author jrodriguez
 */
public class UserDeleteGroupAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm groupForm = (DynaActionForm)form;

        UserABMFacade userABM = sys.getUserABMFacade();
        userABM.deleteUserGroup(groupForm.getString("user"),Util.stringToCollection(groupForm.getString("list")),sys.getUserSessionData());
        session.removeAttribute("UserDeleteGroupForm");
        session.setAttribute("uid",groupForm.getString("user"));
    	return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
