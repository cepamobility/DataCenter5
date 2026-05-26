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

/**
 * @author jrodriguez
 */
public class UserResetOwnPasswordAction extends AbstractAction {
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("home");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CreateForm updateData = (CreateForm) form;
        String msg = validate(updateData);
        
        if (msg != null) {
            session.setAttribute("Msg", msg);
            return mapping.findForward("message");
        }else {
            String passwd = updateData.getPassword();
            if(sys.getUserReportFacade().getPasswordType(sys.getUserSessionData().getFilter().getLoginName(),sys.getUserSessionData()) == UserAttributes.DIFFICULT) {
                String valid = UserData.validatePassword(updateData.getPassword());
                if(valid != null) {
                    session.setAttribute("Msg", valid);
                    return mapping.findForward("message");
                }
            }
            UserABMFacade userABM = null;

            userABM = sys.getUserABMFacade();
            userABM.userResetOwnPassword(passwd,sys.getUserSessionData());
        }
        return mapping.findForward("home");
    }

    private String validate(CreateForm createData) {
        
        if(Util.empty(createData.getPassword())){
            return "generic.user.userdata.pass.isempty";
        }else{
            if(Util.empty(createData.getRePassword())){
                return "generic.user.userdata.confirmpass.isempty";
            }else{
                if(createData.getPassword().compareTo(createData.getRePassword())!=0){
                    return "generic.user.userdata.pass.confir.different";
                }
            }
        }
        return null;
    }
}
