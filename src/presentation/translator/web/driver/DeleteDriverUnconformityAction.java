/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.util.Collection;

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
import business.vehicle.VehicleABMFacade;

import communication.language.Text;

/**
 * @author jrodriguez
 */
public class DeleteDriverUnconformityAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm formData = (DynaActionForm)form;
        
        Collection unconformities = Util.stringToCollection(formData.getString("list"));

        sys.getDriverABMFacade().deleteUnconformity(unconformities, sys.getUserSessionData());

        sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+formData.getString("driver"));
		return mapping.findForward("next");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DynaActionForm formData = (DynaActionForm)form;
        sys.getUserSessionData().setUrl("driver/DriverView.jsp?id="+formData.getString("driver"));
		return mapping.findForward("next");
    }
}
