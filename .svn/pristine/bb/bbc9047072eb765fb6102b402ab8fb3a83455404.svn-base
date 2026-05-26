/*
 * Created on 31-oct-2005
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
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.corporation.CorporationABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;

/**
 * @author jrodriguez
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorporationDeleteAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        UserSessionData userSessionData = sys.getUserSessionData();
		
		DynaActionForm corporationDeleteForm = (DynaActionForm) form;
		String id = (String)corporationDeleteForm.get("id");
			
		CorporationABMFacade corporationABM = sys.getCorporationABMFacade();
		CorporationData corporationData=new CorporationData(new Integer(id),"",null,null,null,null);		
		corporationABM.deleteCorporation(corporationData,userSessionData);
			
		return mapping.findForward("CorporationList");
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("VehicleList");
    }	

}
