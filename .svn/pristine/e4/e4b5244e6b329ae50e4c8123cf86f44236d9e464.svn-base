/*
 * Created on 22-ago-2005
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
import business.administration.user.UserReportFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.tables.UserData;
import communication.language.Text;

public class UserAddCorporationsAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        UserAddCorporationForm updateData = (UserAddCorporationForm) form;
		String msg = validate(messages,form);

		if (msg != null) {
			session.setAttribute("Msg", msg);
            sys.getUserSessionData().setUrl("/user/UserAddCorporations.jsp?uid="+updateData.getLoginname());
			return mapping.findForward("next");
		}else {

			UserABMFacade userABM = sys.getUserABMFacade();	
			userABM.userAddCorporation(updateData.getLoginname(), Integer.parseInt(updateData.getCorporationId()), updateData.getNewrole(), sys.getUserSessionData());

            sys.getUserSessionData().setUrl("/user/UserAddCorporations.jsp?uid="+updateData.getLoginname());
                      
			return mapping.findForward("next");
		
        }
	}
	
	private String validate(Text messages,ActionForm form) {
		UserAddCorporationForm createData = (UserAddCorporationForm) form;

		if (Util.empty(createData.getCorporationId())) {
		    return messages.getText("user.addcorporation.corporation.isempty");
		}
		
		if (Util.empty(createData.getNewrole())) {
		    return messages.getText("user.addcorporation.role.isempty");
		}
		return null;
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserAddCorporationForm updateData = (UserAddCorporationForm) form;
        sys.getUserSessionData().setUrl("/user/UserView.jsp?uid="+updateData.getLoginname());        
		return mapping.findForward("next");
    }

}