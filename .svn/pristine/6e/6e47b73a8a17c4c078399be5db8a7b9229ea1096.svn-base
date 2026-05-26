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

public class UpdateMitigationPlanActivityAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        DynaActionForm updateData = (DynaActionForm) form;
        
        int plan = Integer.parseInt(updateData.getString("plan"));
        int activity = Integer.parseInt(updateData.getString("activity"));
        boolean done = updateData.getString("done").equals("0");
        sys.getDriverABMFacade().updateMigrationPlanActivity(plan, activity, done, userSessionData);

        userSessionData.setUrl("/driver/DriverView.jsp?id="+updateData.getString("driver"));
        return mapping.findForward("next");
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	return mapping.findForward("layout");
    }
}
