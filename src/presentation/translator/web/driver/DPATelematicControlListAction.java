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

import presentation.translator.web.driver.DPATelematicControlListForm;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.language.Text;

/**
 * @author fcarriquiry
 */
public class DPATelematicControlListAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

		 HttpSession session = request.getSession();
         SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         Text messages = sys.getText();
        
         DPATelematicControlListForm formDPA=(DPATelematicControlListForm) form;
         response.setHeader("Content-Type", "application/vnd.ms-excel");
    	 response.setHeader("Content-Disposition", "inline; filename=\"DPATelematicControlList.xls");
    	
    	 sys.getDriverReportFacade().exportTelematicControlDPA(sys.getText(),sys.getUserSessionData(), formDPA.getFilter(), response.getOutputStream());
        
    	 
         request.setAttribute("DPATelematicControlListForm", formDPA);
         
	     return null;
        	
	}
	
	

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
