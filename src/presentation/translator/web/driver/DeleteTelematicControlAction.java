/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.driver.DriverABMFacade;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class DeleteTelematicControlAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm controlData = (DynaActionForm)form;
        String[] controlId = {controlData.getString("id")};
        String driverId = controlData.getString("driver");

        DriverABMFacade driverABM = sys.getDriverABMFacade();
        driverABM.deleteTelematicControl(controlId,sys.getUserSessionData());
        if(request.getParameter("vehicle")==null){
        	sys.getUserSessionData().setUrl("/driver/DriverView.jsp?id="+driverId);
        }else{
        	sys.getUserSessionData().setUrl("/vehicle/VehicleView.jsp?id="+request.getParameter("vehicle"));
        }
        session.removeAttribute("TelematicControlForm");

        return mapping.findForward("next");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
