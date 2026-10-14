/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.dpa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import business.SystemAccess;
import communication.administration.dpa.CorrectiveActionDPAData;
import communication.language.Text;
import communication.security.SecurityConstants;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;

/**
 * @author fcarriquiry
 */
public class DPACorrectiveActionAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

			
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
         if(request.getParameter("delete")!=null){
        	 return deleteAction(mapping, form, request, response);
         }
         DPACorrectiveActionForm formDPA=(DPACorrectiveActionForm)form;
         
         CorrectiveActionDPAData correctiveAction = formDPA.makeCorrectiveActionDPAData(sys, request);
         
         Integer vehicleId = (Integer) session.getAttribute("vehicle");
         session.removeAttribute("vehicle");
         if((correctiveAction.getId() == null || correctiveAction.getId().intValue() == 0) && correctiveAction.getItems().size() == 0) {
        	 sys.getUserSessionData().setUrl("/pendingChecklist.jsp?checklist=80&id="+vehicleId);
        	 return mapping.findForward("back");
         }
         sys.getCorporationABMFacade().createOrUpdateCorrectiveActionDPA(correctiveAction, sys.getText(), sys.getUserSessionData());
         
         response.sendRedirect(request.getContextPath()+"/pendingChecklist.jsp?checklist=80&id="+vehicleId);
         
	     return null;
        	
	}
	
	public ActionForward deleteAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
         
		String check = Util.checkPermission(sys,SecurityConstants.EDIT_VEHICLE);
		String check1 = Util.checkPermission(sys,SecurityConstants.MODIFY_VEHICLE);	
		String check2 = Util.checkPermission(sys,SecurityConstants.CHECKLIST_VEHICLE);	
		
		
		if (check!=null) { 
			response.sendRedirect(request.getContextPath()+check);	
		}else if (check1!=null) { 
			response.sendRedirect(request.getContextPath()+check1);	
		} else if (check2!=null) { 
			response.sendRedirect(request.getContextPath()+check2);	
		}else {
			sys.getCorporationABMFacade().deleteCorrectiveActionDPA((String) request.getParameter("ids"), sys.getUserSessionData());
		}
		
		return null;
	}



	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
