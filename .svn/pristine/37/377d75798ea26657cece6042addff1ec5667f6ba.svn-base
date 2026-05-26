package presentation.translator.web.vehicle;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.user.UserSessionData;

public class VehiclePursuitRouteListDeleteAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        UserSessionData userSessionData = sys.getUserSessionData();		
		
		Integer checkId = new Integer(((DynaActionForm)form).getString("check"));
        sys.getVehicleABMFacade().deleteVehiclePursuitRoute(checkId,"delete",userSessionData);
		
        session.removeAttribute("TruckCheckListDeleteForm");
        
		return mapping.findForward("VehicleView");
	}	

	public Collection stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection col=new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("VehicleView");
    }	

}
