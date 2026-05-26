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
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverABMFacade;

import communication.driver.tables.AlcoholControlData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class AddAlcoholControlAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm controlData = (DynaActionForm)form;
        
        Integer driverId = new Integer(controlData.getString("driver"));
        
		String message = validate(controlData);
		if(message != null) {
		    session.setAttribute("Msg", messages.getText(message));
		    return mapping.findForward("back");
		}else {
            AlcoholControlData data = new AlcoholControlData();
            data.setDate(Util.getDate(controlData.getString("controlDate"),controlData.getString("controlMonth"),controlData.getString("controlYear")));
            data.setResult(new Integer(controlData.getString("result")));

            session.setAttribute("id",String.valueOf(driverId));
            session.removeAttribute("AddAlcoholControlForm");
            DriverABMFacade driverABM = sys.getDriverABMFacade();
            driverABM.createAlcoholControl(driverId,data,sys.getUserSessionData());
			return mapping.findForward("next");
        }	
	}

    private String validate(DynaActionForm testData) {
        return null;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }
}
