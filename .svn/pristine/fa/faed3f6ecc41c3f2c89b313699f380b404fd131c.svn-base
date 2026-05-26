package presentation.translator.web.activity.schedule;

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

import communication.administration.user.UserSessionData;
import communication.exception.InvalidDataException;

public class ActivityDictateAction extends AbstractAction { 

	// This action is executed from all services that involve updating of 
	// vehicles (edit vehicle, change driver assigned, assign a driver to vehicle, and 
	// change to institutional
	
	public ActionForward action(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
	
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();		
			
		DynaActionForm listForm = (DynaActionForm) form;
		Collection<String> ids = Util.stringToCollection(listForm.getString("list"));
		String comment = listForm.getString("comment");
		
		try {
			//sys.getActivityABMFacade().dictateActivities(ids,comment,userSessionData,sys.getText());
			userSessionData.setUrl("/activity/schedule/ActivityList.jsp");
		} catch (InvalidDataException e) {
			userSessionData.setValue(listForm.getString("list"));
			userSessionData.setMsg(e.getMessage());
			userSessionData.setUrl("/activity/schedule/dictateActivities.jsp");
		}
		return mapping.findForward("layout");
	}

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
		userSessionData.setUrl("/activity/schedule/ActivityList.jsp");
        return mapping.findForward("layout");
    }            
}
