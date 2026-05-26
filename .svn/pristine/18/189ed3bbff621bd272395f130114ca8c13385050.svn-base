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

import communication.administration.user.tables.UserData;
import communication.language.Text;

/**
 * @author fcarriquiry
 */
public class UserUpdateAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        CreateForm updateData = (CreateForm) form;
		String msg = validate(messages,form);

		if (msg != null) {
			session.setAttribute("Msg", msg);
			session.setAttribute("uid",updateData.getLoginname());
			return mapping.findForward("edit");
		}else {

            UserReportFacade userReport=sys.getUserReportFacade();
			UserData data=userReport.findUserByPrimaryKey(updateData.getLoginname(),sys.getUserSessionData());

			data.setLoginName(updateData.getLoginname());
			data.setFirstName(updateData.getFirstName());
            data.setLastName(updateData.getLastName());
            data.setLanguage(updateData.getLanguage());
            data.setEmail(updateData.getEmail());
				
			UserABMFacade userABM = sys.getUserABMFacade();
			userABM.userUpdate(data,false,sys.getUserSessionData());

            session.setAttribute("uid",data.getLoginName());
			return mapping.findForward("UserView");
        }
	}

	
	private String validate(Text messages,ActionForm form) {
		CreateForm createData = (CreateForm) form;

		if (Util.empty(createData.getLoginname())) {
		    return messages.getText("generic.user.userdata.loginname.isinvalid");
		}

		if (Util.empty(createData.getFirstName())) {
		    return messages.getText("generic.user.userdata.firstname.isempty");
		}

		if (Util.empty(createData.getLastName())) {
		    return messages.getText("generic.user.userdata.lastname.isempty");
		}
		
		if (Util.empty(createData.getLanguage())) {
		    return messages.getText("generic.user.userdata.language.isempty");
		}

		return null;
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("uid",((CreateForm)form).getLoginname());
        return mapping.findForward("UserView");
    }

}