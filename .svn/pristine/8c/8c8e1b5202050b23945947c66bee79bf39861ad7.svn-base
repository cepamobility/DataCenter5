/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleReportFacade;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class VehicleChangeUseAction extends AbstractAction {

	// This action is executed from all services that involve updating of vehicles (edit vehicle, change driver assigned, assign a driver to vehicle, and 
	// change to institutional
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        VehicleForm vehicleForm = (VehicleForm)form;
        sys.getVehicleABMFacade().changeVehicleUse(new Integer(vehicleForm.getId()),new Integer(vehicleForm.getDriver()),vehicleForm.getComment(),sys.getUserSessionData());
        
		session.setAttribute("id",vehicleForm.getId());
		return mapping.findForward("view");
	}	
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	session.setAttribute("id", ((VehicleForm) form).getId());
        return mapping.findForward("view");
    }            
}
