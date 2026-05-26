/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.cenapra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.exception.InvalidDeleteException;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ParticipantDeleteAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        String list = ((DynaActionForm)form).getString("list");
        String comment = ((DynaActionForm)form).getString("comment");

        try {
            session.removeAttribute("ParticipantDeleteForm");
            sys.getCENAPRAABMFacade().deleteParticipants(list,comment,sys.getUserSessionData());
        }catch(Exception e) {
            Throwable thw = (e instanceof ServletException) ? ((ServletException)e).getRootCause() : e;
            while (thw.getCause() != null) {
                thw = thw.getCause();
            }
            if (thw.getClass().toString().indexOf("InvalidDeleteException") > 0) {
                session.setAttribute("Msg",((InvalidDeleteException)thw).getKey());
                if(((DynaActionForm)form).getString("type").equals("list")) {
                    return mapping.findForward("success2");
                }else {
                    session.setAttribute("id",list);
                    return mapping.findForward("success1");
                }
            }else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/exception.jsp");
                dispatcher.forward(request, response);
            }
        }
        
        return mapping.findForward("success2");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        if(((DynaActionForm)form).getString("type").equals("list")) {
            return mapping.findForward("success2");
        }else {
            session.setAttribute("id",((DynaActionForm)form).getString("list"));
            return mapping.findForward("success1");
        }
    }
}
