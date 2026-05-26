/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.driver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.Util;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.language.Text;

/**
 * @author fcarriquiry
 */
public class DPATelematicControlListSendMailAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
        
         DPATelematicControlListSendMailForm formDPA=(DPATelematicControlListSendMailForm) form;
         response.setHeader("Content-Type", "application/vnd.ms-excel");
    	 response.setHeader("Content-Disposition", "inline; filename=\"DPATelematicControlListSendMail.xls");
    	
    	 sys.getDriverReportFacade().exporTelematicControlsSendMail(Util.getDate(formDPA.getStartDay(), formDPA.getStartMonth(), formDPA.getStartYear()), Util.getDate(formDPA.getEndDay(), formDPA.getEndMonth(), formDPA.getEndYear()), sys.getUserSessionData(), messages, response.getOutputStream());
			 
    	 
         request.setAttribute("DPATelematicControlListSendMailForm", formDPA);
         
	     return null;
        	
	}
	
	

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
