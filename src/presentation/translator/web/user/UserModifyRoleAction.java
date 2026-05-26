/*
 * Created on 31-oct-2005
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

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.administration.user.UserABMFacade;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserModifyRoleAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        RoleForm formData = (RoleForm) form;

		UserABMFacade userABM = null;
	    if(!Util.empty(formData.getNewrole()) && !formData.getOldrole().equals(formData.getNewrole())) {
	        userABM = sys.getUserABMFacade();
	        userABM.userModifyRole(formData.getLoginname(),formData.getNewrole(),sys.getUserSessionData());
	    }
        session.setAttribute("user",formData.getLoginname());
		return mapping.findForward("permissions");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("uid",((RoleForm)form).getLoginname());
        return mapping.findForward("UserEdit");
    }
}