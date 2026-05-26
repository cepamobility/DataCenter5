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

import communication.administration.user.tables.UserAttributes;
import communication.administration.user.tables.UserData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class UserResetPasswordAction extends AbstractAction {
	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
		String info = null;
			
		String uid=(String)session.getAttribute("uid");
			
		String msg = validate(messages,form);
		if(msg != null) {
			session.setAttribute("Msg", msg);
			return mapping.findForward("resetPassword");
		}else {
		    CreateForm updateData = (CreateForm) form;
            if(sys.getUserReportFacade().getPasswordType(uid,sys.getUserSessionData()) == UserAttributes.DIFFICULT) {
                String valid = UserData.validatePassword(updateData.getPassword());
                if(valid != null) {
                    session.setAttribute("Msg", valid);
                    return mapping.findForward("resetPassword");
                }
            }
            info = updateData.getInfo();
			UserData data = new UserData(updateData.getPassword(), null, null,	null, uid,null, null,null,null,null);
			UserABMFacade userABM = sys.getUserABMFacade();
			userABM.userResetPassword(data,sys.getUserSessionData());
			if(info != null) {
			    return mapping.findForward("home");
			}
            session.setAttribute("uid",(String)session.getAttribute("uid"));
            return mapping.findForward("UserView");
		}
	}

	
	private String validate(Text messages,ActionForm form) {
        CreateForm updateData = (CreateForm) form;
        if (!Util.empty(updateData.getPassword()) && !Util.empty(updateData.getRePassword())) {
            if (updateData.getPassword().compareTo(updateData.getRePassword()) != 0) {
                return messages.getText("generic user.userdata.passconfirm");
            }
        } else {
            return messages.getText("generic.user.userdata.pass.isempty");
        }
        return null;
	}


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("uid",(String)session.getAttribute("uid"));
        return mapping.findForward("UserView");
    }
}
