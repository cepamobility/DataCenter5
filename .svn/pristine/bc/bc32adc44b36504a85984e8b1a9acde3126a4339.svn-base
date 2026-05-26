package presentation.translator.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

public class CEPARedirectAction extends Action {

    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String mail = ((DynaActionForm)form).getString("mail");
            if(mail == null || mail.trim().length() == 0) {
                return mapping.findForward("back");
            }else {
                if(mail.contains("monsanto.com") || mail.contains("jnj.com") ) {
                    return mapping.findForward("dc5");
                }
            }
            return mapping.findForward("dc4");
        }catch(Throwable e) {
            throw new Exception(e);
        }
    }
}