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

/**
 * @author jrodriguez
 */
public class DriverAsociateForestalAction extends AbstractAction {

	/**
	 * 
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm formData = (DynaActionForm)form;
	    DriverData driver = sys.getDriverReportFacade().findDriverByPrimaryKey(new Integer(formData.getString("id")),userSessionData);
	    String comment = "Asociado";
	    

		DriverABMFacade driverABM = sys.getDriverABMFacade();
        driverABM.copyDriver(driver,comment,new Integer(formData.getString("corporation")),userSessionData);
	    userSessionData.setUrl("/driver/DriverView.jsp?id="+formData.getString("id"));
	    return mapping.findForward("success");
	}

	/**
	 * 
	 */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	return null;
    }
}
