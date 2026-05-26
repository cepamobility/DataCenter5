/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.user;

import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.user.UserABMFacade;

import communication.administration.user.tables.UserData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserDivOrgFilterAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm formData = (DynaActionForm) form;

        Integer divOrgId = new Integer((String)formData.get("id"));
        int level = Integer.parseInt((String)formData.get("level"));
        
        Hashtable items = new Hashtable();
        StringTokenizer list = new StringTokenizer((String)formData.get("list"),"<");
        while(list.hasMoreTokens()) {
            items.put(new Integer(list.nextToken()),"");
        }
            
        UserData user = (UserData)session.getAttribute("CurrentUser");
        UserABMFacade userABM = sys.getUserABMFacade();
        userABM.userUpdate(user,false,sys.getUserSessionData());
        sys.updateFilter();
        
        return mapping.findForward("level"+String.valueOf(level+1));
    }
	
}
