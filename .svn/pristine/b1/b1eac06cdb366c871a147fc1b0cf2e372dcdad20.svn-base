package presentation.translator.web.driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

public class DeleteListTelematicControlAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        DriverListTelematicControlForm deleteForm = (DriverListTelematicControlForm)form;
        //  Obtengo los id de los checkbox seleccionados  
    	String[] deleteids = deleteForm.getDeleteIds();
		if(deleteids != null){
	        sys.getDriverABMFacade().deleteTelematicControl(deleteids, sys.getUserSessionData());
		}
    	
 		sys.getUserSessionData().setUrl("/driver/DriverView.jsp?id="+deleteForm.getDriver());
        return mapping.findForward("DriverView");

 		
    }

	
	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

}
