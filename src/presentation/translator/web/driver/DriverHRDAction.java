/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;

import java.util.Date;

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

import communication.administration.user.UserSessionData;
/**
 * @author jrodriguez
 */
public class DriverHRDAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm driverForm = (DynaActionForm) form;
        String driverId = driverForm.getString("driver");
        Date date = Util.getDate(driverForm.getString("date"), driverForm.getString("month"), driverForm.getString("year"));
        String comment = driverForm.getString("comment");    
        String type = driverForm.getString("type");
        sys.getDriverABMFacade().driverHRD(new Integer(driverId), type, date, comment, Integer.parseInt(driverForm.getString("action")) ,userSessionData);
        userSessionData.setUrl("/driver/DriverView.jsp?id="+driverId);
        session.removeAttribute("DriverHRDForm");
        return mapping.findForward("next");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        DynaActionForm driverForm = (DynaActionForm) form;
        String driverId = driverForm.getString("id");
        userSessionData.setUrl("/driver/DriverView.jsp?id="+driverId);
        return mapping.findForward("next");
    }
}
