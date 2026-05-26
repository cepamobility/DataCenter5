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

import communication.administration.group.tables.GroupMainAttributes;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class GroupUpdateAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        GroupDataForm updateData = (GroupDataForm)form;

		String message = updateData.validate();
		if(message != null) {
		    session.setAttribute("Msg", messages.getText(message));
            return mapping.findForward("back");
		}
        
        GroupMainAttributes group = new GroupMainAttributes(new Integer(updateData.getId()),updateData.getName(),updateData.getDescription(),true,new Integer(updateData.getPeriod()),new Integer(updateData.getType()));
		GroupABMFacade groupABM = sys.getGroupABMFacade();
        groupABM.updateGroupData(group,sys.getUserSessionData());
        session.removeAttribute("GroupUpdateForm");
		return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.removeAttribute("GroupCreateForm");
        return mapping.findForward("cancel");
    }
}
