/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.cenapra;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.cenapra.tables.ParticipantData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ParticipantCreateAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        ParticipantForm createData = (ParticipantForm)form;

		String message = createData.validate(sys.getUserSessionData());
		if(message != null) {
		    session.setAttribute("Msg", messages.getText(message));
		    return mapping.findForward("error");
		}else {
		    ParticipantData participant = createData.getParticipantData(sys.getUserSessionData());
            sys.getCENAPRAABMFacade().createParticipant(participant,createData.getComment(),sys.getUserSessionData());
            session.removeAttribute("ParticipantCreateForm");
			return mapping.findForward("success");
        }	
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
