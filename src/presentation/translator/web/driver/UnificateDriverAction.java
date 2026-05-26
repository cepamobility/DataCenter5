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

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;


public class UnificateDriverAction extends AbstractAction {
	
	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm driverForm = (DynaActionForm) form;
        
        // se pasan los datos del conductor origin al conductor destiny
        String origin = driverForm.getString("origin");
        String destiny = driverForm.getString("destiny");
        boolean delete = (Integer.parseInt(driverForm.getString("deleteOrigin")) == 1);
        String comment = driverForm.getString("comment");

        sys.getDriverABMFacade().unificateDrivers(new Integer(origin),new Integer(destiny),delete,comment,userSessionData);
        if(delete) {
        	userSessionData.setUrl("/driver/DriverView.jsp?id="+destiny);
        }else {
        	userSessionData.setUrl("/driver/DriverView.jsp?id="+origin);
        }
        return mapping.findForward("layout");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	
    	String url = null;
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        
        if (userSessionData.getUrl() == null){
        	url = "/driver/DriverList.jsp";
        	userSessionData.setUrl(url);
        }       
        
        return mapping.findForward("DriverList");
    }
}
