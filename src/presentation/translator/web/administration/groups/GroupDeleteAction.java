/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.administration.groups;

import java.util.Collection;

import javax.servlet.ServletException;
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
import business.administration.group.GroupABMFacade;

import communication.exception.InvalidDeleteException;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class GroupDeleteAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm deleteData = (DynaActionForm)form;
        try {
            Collection groups = Util.stringToCollection(deleteData.getString("list"));
        
            GroupABMFacade groupABM = sys.getGroupABMFacade();
            groupABM.deleteGroups(groups,sys.getUserSessionData());
            session.removeAttribute("GroupDeleteForm");
            return mapping.findForward("success");
        }catch(Exception e) {
            Throwable thw = (e instanceof ServletException) ? ((ServletException)e).getRootCause() : e;
            while (thw.getCause() != null) {
                thw = thw.getCause();
            }
            if (thw.getClass().toString().indexOf("InvalidDeleteException") > 0) {
                session.setAttribute("Msg",messages.getText(((InvalidDeleteException)thw).getKey()));
                if(((String)session.getAttribute("url")).startsWith("groups/groupView.jsp")) {
                    session.setAttribute("id",deleteData.getString("list"));
                    return mapping.findForward("back");
                }else {
                    return mapping.findForward("success");
                }
            }else {
                throw e;
            }
        }
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
