/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.administration.groups;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.group.GroupABMFacade;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class GroupAddLineAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        GroupAddLineForm groupForm = (GroupAddLineForm) form;
        groupForm.getGroupLine(sys.getUserSessionData());

		String message = groupForm.validate(sys.getUserSessionData());
		if(message != null) {
		    session.setAttribute("GroupAddLineForm",groupForm);
		    session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("back");
		}else {           
            GroupABMFacade groupABM = sys.getGroupABMFacade();
            groupABM.addLines(new Integer(groupForm.getId()),groupForm.getGroupLine(sys.getUserSessionData()),sys.getUserSessionData());
            session.removeAttribute("GroupAddLineForm");
            session.setAttribute("id",groupForm.getId());
			return mapping.findForward("back");
        }	
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        GroupAddLineForm groupForm = (GroupAddLineForm)form;
        session.removeAttribute("GroupAddLineForm");
        session.setAttribute("id",groupForm.getId());
        return mapping.findForward("cancel");
    }
}
