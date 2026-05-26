/*
 * Created on 30-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.administration.corporation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorporationUpdateAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("error");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CorporationCreateAction crpCreateAction=new CorporationCreateAction();
//        String msg=crpCreateAction.validate((CorporationForm)form);
//        
//        if(msg==null){
//            CorporationData data=crpCreateAction.getCorporationData((CorporationForm)form,session);
//            CorporationABMFacade crpABM=null;
//            crpABM=sys.getCorporationABMFacade();
//            crpABM.corporationUpdate(data,sys.getUserSessionData());
//        }
        return mapping.findForward("update");
    }
}
