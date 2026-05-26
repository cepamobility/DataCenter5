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
public class DriverForestalesCreateAction extends AbstractAction {

	/**
	 * 
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

	    DriverData driver = (DriverData)userSessionData.getValue();
	    String comment = userSessionData.getMsg();
	    
        DynaActionForm formData = (DynaActionForm)form;
        boolean[] replicate = {Util.isChecked(formData.getString("corporation1")),Util.isChecked(formData.getString("corporation2")),Util.isChecked(formData.getString("corporation3"))};

		DriverABMFacade driverABM = sys.getDriverABMFacade();
        driverABM.driverCreate(driver,comment,replicate,userSessionData);
	    userSessionData.setUrl("/success.jsp?entity=driver.create");
	    return mapping.findForward("success");
	}

	/**
	 * 
	 */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
    	
    	if(((SystemAccess)session.getAttribute("SystemAccess")).getUserSessionData().getFilter().isCEPASystem()) {
		    return mapping.findForward("DriverListCepaSystem");
	    }
	    
	    
	    userSessionData.setUrl("/driver/DriverList.jsp");
	    return mapping.findForward("DriverList");
    }
}
