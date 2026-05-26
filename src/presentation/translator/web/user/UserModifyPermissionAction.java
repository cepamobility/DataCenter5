/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.user;

import java.util.StringTokenizer;

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

import communication.administration.user.PermissionData;
import communication.administration.user.tables.UserData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserModifyPermissionAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        DynaActionForm formData = (DynaActionForm) form;
        session.setAttribute("uid",formData.getString("user"));
        return mapping.findForward("UserEdit");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm formData = (DynaActionForm) form;
        UserData user = sys.getUserReportFacade().findUserByPrimaryKey(formData.getString("user"),sys.getUserSessionData());

        if(!Util.empty((String)formData.get("add"))) {
            StringTokenizer items = new StringTokenizer((String)formData.get("add"),"<");
            while(items.hasMoreTokens()) {
                user.addPermission(new PermissionData(items.nextToken(),null,null,null));
            }
        }else {
            StringTokenizer items = new StringTokenizer((String)formData.get("remove"),"<");
            while(items.hasMoreTokens()) {
                user.removePermission(items.nextToken());
            }
        }
        
        UserABMFacade userABM = null;
        userABM = sys.getUserABMFacade();
        userABM.userUpdate(user,sys.getUserSessionData());
        session.setAttribute("user",user.getLoginName());
        return mapping.findForward("permissions");
    }
}