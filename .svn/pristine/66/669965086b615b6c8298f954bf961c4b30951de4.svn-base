/*
 * Created on 27-oct-2005
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
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverABMFacade;
import business.vehicle.VehicleABMFacade;

import communication.language.Text;
import communication.vehicle.tables.UnconformityData;

/**
 * @author jrodriguez
 */
public class AddUnconformityAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm formData = (DynaActionForm)form;
        
        Integer vehicleId = new Integer(formData.getString("vehicle"));
        
		String message = validate(formData);
		if(message != null) {
		    session.setAttribute("Msg", messages.getText(message));
            session.setAttribute("id",String.valueOf(vehicleId));
		    return mapping.findForward("back");
		}else {
            UnconformityData data = new UnconformityData();
            data.setDate(Util.getDate(formData.getString("date"),formData.getString("month"),formData.getString("year")));
            data.setOrigin(formData.getString("origin"));
            data.setDepartment(formData.getString("department"));
            data.setCharge(new Integer(formData.getString("charge")));
            data.setObservations(formData.getString("observations"));

            session.setAttribute("id",String.valueOf(vehicleId));
            session.removeAttribute("AddUnconformityForm");
            VehicleABMFacade vehicleABM = sys.getVehicleABMFacade();
            vehicleABM.createUnconformity(vehicleId,data,sys.getUserSessionData());
			return mapping.findForward("next");
        }	
	}

    private String validate(DynaActionForm formData) {
    	if(Util.empty(formData.getString("origin"))) {
    		return "driver.unconformities.originerror";
    	}
    	if(Util.empty(formData.getString("department"))) {
    		return "driver.unconformities.departmenterror";
    	}
    	if(Util.empty(formData.getString("charge")) || !Util.isNumber(formData.getString("charge"))) {
    		return "driver.unconformities.chargeerror";
    	}
		return null;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        DynaActionForm formData = (DynaActionForm)form;
        session.setAttribute("id",formData.getString("id"));
        return mapping.findForward("cancel");
    }
}
