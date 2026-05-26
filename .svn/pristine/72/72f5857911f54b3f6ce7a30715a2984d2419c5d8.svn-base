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

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriverChangeTemporalAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        
        String driverId = ((DynaActionForm)form).getString("driver");
        sys.getDriverABMFacade().changeTemporal(new Integer(driverId), userSessionData);
		    
        String url = "/driver/DriverView.jsp?id="+driverId;
        userSessionData.setUrl(url);
        return mapping.findForward("next");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("next");
    }
}
